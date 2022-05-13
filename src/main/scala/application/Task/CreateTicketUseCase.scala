package application.Task

import contract.callback.Task.TicketCallback
import contract.service.Task.CreateTicketService
import domain.Task.Ticket

import scala.concurrent.ExecutionContext
import scala.concurrent.Future


class CreateTicketUseCase(ticketCallback: TicketCallback) extends CreateTicketService{

  override def call(request: CreateTicketService.Request)(implicit ec: ExecutionContext): Future[Ticket] = for {
    ticket <- ticketCallback.add(request.name, request.description, request.tag)
  } yield ticket
}
