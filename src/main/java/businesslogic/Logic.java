package businesslogic;

import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.logging.impl.LoggerFactory;

import ch.ma3.plant.factories.EventBusFactory;
import ch.ma3.plant.verticles.Database;

public class Logic {

	Logger log = LoggerFactory.getLogger(Logic.class);
	private EventBus eb;
	private Database db;

	public Logic(EventBus eb) {
		this.eb = eb;
		db = Database.getDatabaseInstance();
	}

	public void gotDataRequestFromClient() {
		JsonObject response = new JsonObject();
		response.putString("message", "Some Message	");
		response.putArray("data", db.getMeasurements());
		log.info("Got DataRequest From Client.");
		eb.publish(EventBusFactory.CLIENT, response);
		log.info("Data sent.");
	}
}
