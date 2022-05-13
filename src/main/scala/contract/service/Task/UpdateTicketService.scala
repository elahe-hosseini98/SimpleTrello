package contract.service.Task

import domain.Task.Ticket
import contract.service.Service

abstract class UpdateTicketService extends Service[UpdateTicketService.Request, Ticket]

object UpdateTicketService {
  case class Request(name: String, newTag: String)
}