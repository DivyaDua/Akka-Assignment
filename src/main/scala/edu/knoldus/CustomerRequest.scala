package edu.knoldus

import akka.actor.{ActorLogging, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

object CustomerRequest extends App{

  val system = ActorSystem("CustomerActorSystem")

  val purchaseActorRef = system.actorOf(PurchaseActor.props)

  val validationActorRef = system.actorOf(ValidationActor.props(purchaseActorRef))

  val purchaseHandlerRef = system.actorOf(PurchaseRequestHandler.props(validationActorRef))

  implicit val timeout = Timeout(100 seconds)
  val answer = purchaseHandlerRef ? Customer("Divya", "Sector 36", 1234, 987654321)
  answer.foreach(print)

  val answer2 = purchaseHandlerRef ? "Hello"
  answer2.foreach(print)

}
