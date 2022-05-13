package contract.service.Task

import domain.Task.Ticket
import contract.service.Service

abstract class CreateTicketService extends Service[CreateTicketService.Request, Ticket]

object CreateTicketService {
  case class Request(name: String, description: String, tag: String)
}
