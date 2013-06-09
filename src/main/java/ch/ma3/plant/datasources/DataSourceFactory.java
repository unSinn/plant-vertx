package ch.ma3.plant.datasources;

import org.vertx.java.core.Vertx;
import org.vertx.java.deploy.Verticle;

public class DataSourceFactory {

	private static MockDataSource mockInstance;
	private static ArduinoDataSource arduinoInstance;

	public static DataSource getMockDataSource(Vertx vertx) throws Exception {
		if (mockInstance == null) {
			mockInstance = new MockDataSource();
			setUpVerticle(vertx, mockInstance);
		}
		return mockInstance;
	}

	public static DataSource getArduinoDataSource(Vertx vertx) throws Exception {
		if (arduinoInstance == null) {
			arduinoInstance = new ArduinoDataSource();
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
