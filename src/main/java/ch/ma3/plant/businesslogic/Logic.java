package ch.ma3.plant.businesslogic;

import java.util.List;

import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.logging.impl.LoggerFactory;

import ch.ma3.plant.converters.rickshaw.RickshawConverter;
import ch.ma3.plant.entities.Measurement;
import ch.ma3.plant.entities.Sensor;
import ch.ma3.plant.factories.EventBusFactory;
import ch.ma3.plant.verticles.Database;

public class Logic implements DataCollector {

	Logger log = LoggerFactory.getLogger(Logic.class);
	private EventBus eb;
	private Database db;

	public Logic(EventBus eb) {
		this.eb = eb;
		db = Database.getDatabaseInstance();
	}

	public void gotDataRequestFromClient() {
		log.info("Got DataRequest From Client.");

		List<Sensor> sensors = db.getSensors();

		JsonObject response = RickshawConverter.sensorDataToRickshaw(sensors);

		eb.publish(EventBusFactory.CLIENT, response);
		log.info("Data sent.");
	}

	@Override
	public void logData(Measurement measurement) {
		db.saveMesurement(measurement);
	}
}
