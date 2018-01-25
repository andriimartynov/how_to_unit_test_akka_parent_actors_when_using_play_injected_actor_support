package org.andrii

import com.google.inject.AbstractModule
import org.andrii.examples.Child
import play.api.libs.concurrent.AkkaGuiceSupport

class TestModule extends AbstractModule
  with AkkaGuiceSupport {

  override def configure(): Unit = {
    bindActorFactory[ParentProbeActor, Child.Factory]
  }
}
