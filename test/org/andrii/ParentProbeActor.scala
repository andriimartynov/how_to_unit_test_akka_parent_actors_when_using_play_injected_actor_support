package org.andrii

import akka.actor.Actor

class ParentProbeActor extends Actor {
  def receive = {
    case e => {
      sender() ! e
    }
  }
}
