package module

import contract.service
import module.CallbackModule.Ticket._

object ServiceModule {
  object Ticket {
    import application.Task._
    import service.Task._

    val createTicketService: CreateTicketService = new CreateTicketUseCase(ticketCallback)
    val updateTicketService: UpdateTicketService = new UpdateTicketUseCase(ticketCallback)
  }
}
