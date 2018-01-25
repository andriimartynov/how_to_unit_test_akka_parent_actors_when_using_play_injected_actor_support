package org.andrii.examples

import akka.actor.Actor

object Child {
  trait Factory {
    def apply(name: String): Actor
  }
}

class Child extends Actor {
  override def receive: Receive = {
    case e =>
  }
}
