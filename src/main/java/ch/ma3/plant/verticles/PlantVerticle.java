package ch.ma3.plant.verticles;

import org.vertx.java.core.http.HttpServer;
import org.vertx.java.deploy.Verticle;

import ch.ma3.plant.factories.EventBusFactory;
import ch.ma3.plant.factories.HTMLServerFactory;
import ch.ma3.plant.factories.SocksJSFactory;

public class PlantVerticle extends Verticle {

	public void start() {
		HttpServer httpServer = HTMLServerFactory.getHTMLServer(vertx);
		EventBusFactory.createEventBus(vertx);
		SocksJSFactory.getSockJSServer(vertx, httpServer);
		httpServer.listen(8080);
	}

}
