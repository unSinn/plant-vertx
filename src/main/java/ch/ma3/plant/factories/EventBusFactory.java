package ch.ma3.plant.factories;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;

import businesslogic.Logic;

public class EventBusFactory {

	public static final String CLIENT = "CLIENT";
	public static final String SERVER = "SERVER";

	public static void addDataHandlerTo(final EventBus eb) {
		Handler<Message<JsonObject>> serverDataHandler = new Handler<Message<JsonObject>>() {
			public void handle(Message<JsonObject> message) {
				Logic dl = new Logic(eb);
				dl.gotDataRequestFromClient();
			}
		};
		eb.registerHandler(SERVER, serverDataHandler);
	}

	public static EventBus createEventBus(Vertx vertx) {
		EventBus eb = vertx.eventBus();

		addDataHandlerTo(eb);

		return eb;
	}

}
