import me.webstar.socket.AtmosphereSocket
import org.atmosphere.play.AtmosphereCoordinator._
import org.atmosphere.play.Router
import play.api.mvc.{Handler, RequestHeader}
import play.api.{Application, GlobalSettings}

/**
 * @author sasajib
 */
class Global extends GlobalSettings {
    override def onStart(app: Application): Unit = instance().discover(Class[AtmosphereSocket]).ready()

    override def onStop(app: Application): Unit = instance().shutdown()

    override def onRouteRequest(request: RequestHeader): Option[Handler] = Router.dispatch(request)
}
