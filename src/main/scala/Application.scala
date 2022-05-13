import domain.Task.Ticket
import repository.inmem.repository.Task.TicketRepository

object Application extends App{

  val trelloApp = new TicketRepository
  trelloApp.add("task1", "this is the first ticket", "todo")
  trelloApp.add("task2", "this is the first ticket", "todo")
  trelloApp.add("task3", "this is the first ticket", "todo")
  trelloApp.add("task4", "this is the first ticket", "todo")
  //trelloApp.remove("task1")
  trelloApp.update("task19", "done")
}
