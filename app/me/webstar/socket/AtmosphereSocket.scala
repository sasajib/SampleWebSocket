package me.webstar.socket

import org.atmosphere.config.service.{ManagedService, Disconnect, Message, Ready}
import org.atmosphere.cpr.{AtmosphereResource, AtmosphereResourceEvent}
import play.Logger

/**
 * @author sasajib
 */
@ManagedService(path="/atmos")
class AtmosphereSocket {

    @Ready
    def onReady(r: AtmosphereResource) = Logger.debug("socket connected : {}", r.uuid())

    @Disconnect
    def onDisconnect(e: AtmosphereResourceEvent): Unit = {
        if (e.isCancelled) {
            Logger.debug("unexpected disconnect of : {}", e.getResource.uuid())
        }
        else if (e.isClosedByClient) {
            Logger.debug("connection disconnected by : {}", e.getResource.uuid())
        }
    }


    @Message
    def onMessage(s: String): String = {
        Logger.debug("message received {}", s )
        s
    }
}
