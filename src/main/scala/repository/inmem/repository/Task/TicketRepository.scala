package repository.inmem.repository.Task

import contract.callback.Task.TicketCallback
import module.SimpleInMemoryModule
import domain.Task.Ticket

import scala.concurrent.Future

class TicketRepository extends TicketCallback with SimpleInMemoryModule[Ticket]{
  override val name: String = "Ticket"

  override def add(name: String, description: String, tag: String): Future[Ticket] = {
    val ticket = Ticket(name, description, tag)
    addToMemory(ticket).map(_ => ticket)
  }

  override def get(name: String): Future[Option[Ticket]] = Future {
    data.find(_.name == name)
  }

  override def remove(name: String): Future[Unit] = Future {
    removeFromMemory(_.name == name)
  }

  override def update(ticket: Ticket): Future[Unit] = {
    updateInMemory(ticket, _.name == ticket.name)
  }

  def update(name: String, newTag: String): Future[Unit] = {
    val ticket = Ticket(name, "", newTag)
    update(ticket)
  }

}
