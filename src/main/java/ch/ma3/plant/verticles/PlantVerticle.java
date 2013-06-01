package ch.ma3.plant.verticles;

import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.sockjs.SockJSServer;
import org.vertx.java.deploy.Verticle;

import ch.ma3.plant.factories.*;

public class PlantVerticle extends Verticle {

	private HttpServer httpServer;
	private SockJSServer socksJSServer;
	private EventBus eb;
	private Database db;

	public void start() {
		httpServer = HTMLServerFactory.getHTMLServer(vertx);
		eb = EventBusFactory.createEventBus(vertx);
		socksJSServer = SocksJSFactory.getSockJSServer(vertx, httpServer);
		httpServer.listen(8080);
	}

}
