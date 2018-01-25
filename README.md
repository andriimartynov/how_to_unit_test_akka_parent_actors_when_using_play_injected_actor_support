# How to unit test akka parent actors when using play InjectedActorSupport.


[Code sample:](https://github.com/andriimartynov/how_to_unit_test_akka_parent_actors_when_using_play_injected_actor_support/blob/master/test/org/andrii/examples/ParentTest.scala)

```
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
```