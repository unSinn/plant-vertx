package ch.ma3.plant.factories;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;

public class EventbusFactory {

	public static final String CLIENT_LOG_HANDLER = "CLIENT_LOG_HANDLER";

	public static void addEventBusHandlerTo(EventBus eb) {
		Handler<Message<JsonObject>> myHandler = new Handler<Message<JsonObject>>() {
			public void handle(Message<JsonObject> message) {
				System.out.println("I received a message " + message.body);
			}
		};
		eb.registerHandler("server-handler", myHandler);
	}

	public static EventBus createEventBus(Vertx vertx) {
		EventBus eb = vertx.eventBus();
		
		addEventBusHandlerTo(eb);

		eb.publish(CLIENT_LOG_HANDLER,
				new JsonObject().putString("message", "Eventbus connected."));
		return eb;
	}

}
