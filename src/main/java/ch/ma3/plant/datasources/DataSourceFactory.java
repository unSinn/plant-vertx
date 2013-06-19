package ch.ma3.plant.datasources;

import org.vertx.java.core.Vertx;
import org.vertx.java.deploy.Verticle;

import ch.ma3.plant.verticles.DataCollector;

public class DataSourceFactory {

	private static MockDataSource mockInstance;
	private static ArduinoDataSource arduinoInstance;

	public static DataSource getMockDataSource(Vertx vertx,
			DataCollector collector) throws Exception {
		if (mockInstance == null) {
			mockInstance = new MockDataSource();
			mockInstance.setDataCollector(collector);
			setUpVerticle(vertx, mockInstance);
		}
		return mockInstance;
	}

	public static DataSource getArduinoDataSource(Vertx vertx,
			DataCollector collector) throws Exception {
		if (arduinoInstance == null) {
			arduinoInstance = new ArduinoDataSource();
			arduinoInstance.setDataCollector(collector);
			setUpVerticle(vertx, arduinoInstance);
		}
		return arduinoInstance;
	}

	private static void setUpVerticle(Vertx vertx, Verticle verticleInstance)
			throws Exception {
		verticleInstance.setVertx(vertx);
		verticleInstance.start();
	}
}
