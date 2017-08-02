package edu.knoldus

import akka.routing.{ActorRefRoutee, RoundRobinRoutingLogic, Router}
import akka.actor.{Actor, ActorLogging, Props, Terminated}

class Worker extends Actor with ActorLogging{

  override def receive = {
    case Customer(name,address,creditCardNumber,mobileNumber) => {
      log.info(s"Request from Customer name: $name is processed ")
      sender() ! "Hurrraaaahhhhh!!"
    }
    case msg => log.info("Sorry !!")
  }

}

class PurchaseActor extends Actor with ActorLogging{
  var router = {
    val routees = Vector.fill(5) {
      val r = context.actorOf(Props[Worker])
      context watch r
      ActorRefRoutee(r)
    }
    Router(RoundRobinRoutingLogic(), routees)
  }

  override def receive = {
    case Customer(name,address,creditCardNumber,mobileNumber) =>
      router.route(Customer(name,address,creditCardNumber,mobileNumber), sender())
    case Terminated(a) =>
      router = router.removeRoutee(a)
      val r = context.actorOf(Props[Worker])
      context watch r
      router = router.addRoutee(r)
  }
}

object PurchaseActor{

  def props = Props[PurchaseActor]

}