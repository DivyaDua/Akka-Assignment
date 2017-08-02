package edu.knoldus

import akka.actor.{Actor, ActorLogging, ActorRef, Props}

class PurchaseRequestHandler(validationActor: ActorRef) extends Actor with ActorLogging{

  override def receive = {
    case Customer(name,address,creditCardNumber,mobileNumber) => {
      log.info(s"Request from Customer name: $name has received")
      validationActor.forward(Customer(name,address,creditCardNumber,mobileNumber))
    }
    case _ => log.error("Sorry !! Can't process your request ")
  }

}

object PurchaseRequestHandler{

  def props(ref: ActorRef) = Props(classOf[PurchaseRequestHandler],ref)

}