package org.andrii.examples

import akka.actor.{ActorSystem, PoisonPill, Props}
import akka.testkit.{ImplicitSender, TestKit}
import org.andrii.TestModule
import org.andrii.examples.Child.Factory
import org.andrii.examples.Parent.{Message, UnknownMessageException}
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
import play.api.inject.guice.GuiceInjectorBuilder

class ParentTest extends TestKit(ActorSystem("MySpec"))
  with ImplicitSender
  with WordSpecLike
  with Matchers
  with MockitoSugar
  with BeforeAndAfterAll {

  val injector = new GuiceInjectorBuilder()
    .bindings(new TestModule)
    .injector()

  val factory = injector.instanceOf[Factory]

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "cut" must {
    "return UnknownMessageException" in {
      val cut = system.actorOf(Props(classOf[Parent], factory))

      cut ! "test"
      expectMsg(UnknownMessageException("test"))
      cut ! PoisonPill
    }
  }

  "cut" must {
    "create new context and forward message" in {
      val id = "key"
      val message = Message(id)
      val cut = system.actorOf(Props(classOf[Parent], factory))

      cut ! message

      expectMsg(message)
      cut ! PoisonPill
    }
  }
}
