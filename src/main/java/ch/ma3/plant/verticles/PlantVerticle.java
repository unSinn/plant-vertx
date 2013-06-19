package ch.ma3.plant.verticles;

import java.util.List;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.logging.impl.LoggerFactory;
import org.vertx.java.deploy.Verticle;

import ch.ma3.plant.converters.rickshaw.RickshawConverter;
import ch.ma3.plant.datasources.DataSource;
import ch.ma3.plant.datasources.DataSourceFactory;
import ch.ma3.plant.entities.Measurement;
import ch.ma3.plant.entities.Sensor;
import ch.ma3.plant.factories.HTMLServerFactory;
import ch.ma3.plant.factories.SocksJSFactory;

public class PlantVerticle extends Verticle implements DataCollector {

	public static final String CLIENT = "CLIENT";
	public static final String SERVER = "SERVER";
	private Logger log = LoggerFactory.getLogger(PlantVerticle.class);
	private Database db;
	private EventBus eb;
	private HttpServer httpServer;

	public void start() {
		eb = vertx.eventBus();
		db = Database.getDatabaseInstance();
		httpServer = HTMLServerFactory.getHTMLServer(vertx);
		setupEventBus();
		SocksJSFactory.getSockJSServer(vertx, httpServer);

		try {
			DataSource dataSource = DataSourceFactory.getArduinoDataSource(
					vertx, this);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}

		httpServer.listen(8080);
	}

	private void setupEventBus() {
		Handler<Message<JsonObject>> serverDataHandler = new Handler<Message<JsonObject>>() {
			public void handle(Message<JsonObject> message) {
				gotDataRequestFromClient();
			}
		};
		eb.registerHandler(SERVER, serverDataHandler);
	}

	private void gotDataRequestFromClient() {
		log.info("Got DataRequest From Client.");

		List<Sensor> sensors = db.getSensors();

		JsonObject response = RickshawConverter.sensorDataToRickshaw(sensors);

		eb.publish(CLIENT, response);
		log.info("Data sent.");
	}

	@Override
	public void saveData(Measurement measurement) {
		db.saveMesurement(measurement);
	}
}
