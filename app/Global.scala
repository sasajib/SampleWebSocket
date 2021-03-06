import me.webstar.socket.AtmosphereSocket
import org.atmosphere.play.AtmosphereCoordinator._
import org.atmosphere.play.Router
import play.api.mvc.{Handler, RequestHeader}
import play.api.{Application, GlobalSettings}

/**
 * @author sasajib
 */
object Global extends GlobalSettings {
    override def onStart(app: Application): Unit = instance().discover(classOf[AtmosphereSocket]).ready()

    override def onStop(app: Application): Unit = instance().shutdown()

    override def onRouteRequest(request: RequestHeader): Option[Handler] = Router.dispatch(request) match {
        case Some(result) => Some(result)
        case None => super.onRouteRequest(request)
    }
}
