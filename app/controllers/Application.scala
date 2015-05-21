package controllers

import play.Logger
import play.api.Play.current
import play.api.libs.iteratee.{Enumerator, Iteratee}
import play.api.mvc.{Action, Controller, WebSocket}
import play.sockjs.api._

import scala.concurrent.ExecutionContext.Implicits.global

object Application extends Controller {

    def index = Action {
        Ok(views.html.index("Your new application is ready."))
    }

    //    def socket = WebSocket.acceptWithActor[JsValue, JsValue] { request => out =>
    //        MyWebSocketActor.props(out)
    //    }

    def socket = WebSocket.acceptWithActor[String, String] { request => out =>
        MyWebSocketActor.props(out)
    }

    import akka.actor._

    object MyWebSocketActor {
        def props(out: ActorRef) = Props(new MyWebSocketActor(out))
    }

    class MyWebSocketActor(out: ActorRef) extends Actor {
        def receive = {
            case msg: String if msg == "exit" => println("sending poison"); self ! PoisonPill
            case msg: String => out ! ("I received your message: " + msg)
        }
    }

    lazy val sockjs = SockJSRouter.using[String] { request =>


        // Log events to the console
        val in = Iteratee.foreach[String]((a: String) => {
                Logger.info(a)
        })

        // Send a single 'Hello!' message and close

        val out = Enumerator("Hello SockJS!")

        (in, out)
    }


    def jadu = Action{
        Ok(views.html.indexTwo("hello atmos"))
    }


}