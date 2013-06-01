package ch.ma3.plant.factories;

import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.sockjs.SockJSServer;

public class SocksJSFactory {

	public static SockJSServer getSockJSServer(Vertx vertx,
			HttpServer httpServer) {

		JsonArray permitted = new JsonArray();
		permitted.add(new JsonObject()); // Let everything through
		SockJSServer sockJSServer = vertx.createSockJSServer(httpServer);
		sockJSServer.bridge(new JsonObject().putString("prefix", "/eventbus"),
				permitted, permitted);
		return sockJSServer;
		
	}

}
