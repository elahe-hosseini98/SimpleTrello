package domain.Task

case class Ticket(name: String,
                  description: String,
                  tag: String) {

  import Tag._

  require(name.nonEmpty, "No name for the ticket!")
  require(TAGS.contains(tag), "incorrect tag!")

  def editTag(newTag: String): Ticket = {
    copy(tag = newTag)
  }

}

object Tag {
  final val TAGS = List("todo", "inprogress", "test", "done")
}
