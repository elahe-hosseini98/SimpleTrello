package application.Task

import contract.callback.Task.TicketCallback
import contract.service.Task.UpdateTicketService
import domain.Task.Ticket

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class UpdateTicketUseCase(ticketCallback: TicketCallback) extends UpdateTicketService{

  override def call(request: UpdateTicketService.Request)(implicit ec: ExecutionContext): Future[Ticket] = for {
    ticketOption <- ticketCallback get request.name
    ticket <- ticketOption match {
      case Some(ticket) => Future successful ticket
      case None => Future failed new Exception(s"ticket name: ${request.name}, not found!")
    }
    newTicket = ticket.editTag(request.newTag)
    _ <- ticketCallback update newTicket
  }yield newTicket
}
