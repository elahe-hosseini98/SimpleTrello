package module

import contract.callback
import repository.inmem.{repository => inmem}

object CallbackModule {

  object Ticket {
    import callback.Task._
    import inmem.Task._

    val ticketCallback:TicketCallback = new TicketRepository()
  }

}
