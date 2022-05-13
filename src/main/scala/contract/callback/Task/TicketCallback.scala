package contract.callback.Task

import domain.Task.Ticket
import scala.concurrent.Future

abstract class TicketCallback() {
  def add(
           name: String,
           description: String,
           tag: String
         ): Future[Ticket]

  def get(name: String): Future[Option[Ticket]]

  def remove(name: String): Future[Unit]

  def update(ticket: Ticket): Future[Unit]
}

