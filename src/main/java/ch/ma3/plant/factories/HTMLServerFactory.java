package ch.ma3.plant.factories;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;

public class HTMLServerFactory {

	public static HttpServer getHTMLServer(Vertx vertx) {
		HttpServer httpServer = vertx.createHttpServer();
		httpServer.requestHandler(getHTMLHandler());
		return httpServer;
	}

	private static Handler<HttpServerRequest> getHTMLHandler() {
		return new Handler<HttpServerRequest>() {
			public void handle(HttpServerRequest req) {
				String file = "";
				if (req.path.equals("/")) {
					file = "index.html";
				} else {
					file = req.path;
				}
				req.response.sendFile("webroot/" + file);
			}
		};
	}

}
