package ch.ma3.plant.verticles;

import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.sockjs.SockJSServer;
import org.vertx.java.deploy.Verticle;

import ch.ma3.plant.factories.DatabaseFactory;
import ch.ma3.plant.factories.EventbusFactory;
import ch.ma3.plant.factories.HTMLServerFactory;
import ch.ma3.plant.factories.SocksJSFactory;

public class PlantVerticle extends Verticle {

	private HttpServer httpServer;
	private SockJSServer socksJSServer;
	private EventBus eb;
	private DatabaseFactory db;

	public void start() {
		db = new DatabaseFactory();
		httpServer = HTMLServerFactory.getHTMLServer(vertx);
		eb = EventbusFactory.createEventBus(vertx);
		socksJSServer = SocksJSFactory.getSockJSServer(vertx, httpServer);
		httpServer.listen(8080);
	}

	

}
