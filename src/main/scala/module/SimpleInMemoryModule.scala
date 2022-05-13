package module
import module._
import org.apache.hadoop.shaded.org.eclipse.jetty.websocket.common.message.SimpleBinaryMessage

import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicLong
import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.Future

trait SimpleInMemoryModule[T] {
  val name: String

  implicit val ec: ExecutionContext = SimpleInMemoryModule.ec

  var data: Vector[T] = Vector.empty

  def addToMemory(t: T): Future[Unit] = Future {
    data synchronized {
      data = data :+ t
      println("data added successfully!")
      showData()
    }
  }

  def removeFromMemory(predicate: T => Boolean): Future[Unit] = {
    val index = data indexWhere predicate
    if (index == -1) {
      Future failed new Exception(s"$name Not Found!")
    } else {
      Future {
        data synchronized {
          data = data.take(index) ++ data.drop(index + 1)
          println("data removed successfully!")
          showData()
        }
      }
    }
  }

  def updateInMemory(t: T, predicate: T => Boolean): Future[Unit] = {
    data synchronized {
      val index = data indexWhere predicate
      println(index + " = index")
      if (index == -1) {
        Future failed new Exception(s"$name Not Found!")
      } else {
        Future {
          data = (data.take(index) :+ t) ++ data.drop(index + 1)
          showData()
        }
      }
    }
  }

  def showData(): Unit = {
    data synchronized {
    data.foreach(row =>
      println(row)
    )
    println("_" * 50)
    }
  }

}

object SimpleInMemoryModule {
  private val ec: ExecutionContextExecutor = ExecutionContext fromExecutor Executors.newCachedThreadPool()
}
