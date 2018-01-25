package org.andrii.examples

import javax.inject.Inject

import akka.actor.Actor
import akka.event.LoggingReceive
import play.api.libs.concurrent.InjectedActorSupport

object Parent {

  val unknownMessageString = "Unknown Message"

  case class Message(name: String)

  case class UnknownMessageException(message: Any) extends ParentException(unknownMessageString)

  sealed class ParentException(message: String) extends RuntimeException(message)

}

class Parent @Inject()(
                        childFactory: Child.Factory
                      ) extends Actor
  with InjectedActorSupport {
  import Parent._

  override def receive = LoggingReceive {
    case message: Message =>
      getContextWorkerChild(message) tell(message, sender)
    case e =>
      sender ! UnknownMessageException(e)
  }

  private def getContextWorkerChild(message: Message) =
    context.child(message.name).getOrElse({
      injectedChild(
        childFactory(message.name), message.name)
    })
}
