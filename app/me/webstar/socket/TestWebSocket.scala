package me.webstar.socket

import akka.actor.{Actor, ActorRef, Props}
import play.api.mvc.WebSocket
import play.api.Play.current

/**
 * @author sasajib
 */
class TestWebSocket {

    def socket: WebSocket[String, String] = {
        WebSocket.acceptWithActor[String, String] { request => out => MyWebSocketServer.getSocketActor(out) }
    }

    
}

class MyWebSocketServer(client: ActorRef) extends Actor {
    override def receive = {
        case msg: String => client ! "hey I got the message from myWebSocket Actor"
    }
}

object MyWebSocketServer {
    def getSocketActor(clientActor: ActorRef): Props = Props(new MyWebSocketServer(clientActor))
}



