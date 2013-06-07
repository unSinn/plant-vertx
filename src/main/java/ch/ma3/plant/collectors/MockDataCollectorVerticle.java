package ch.ma3.plant.collectors;

import java.sql.Date;

import org.vertx.java.deploy.Verticle;

import ch.ma3.plant.businesslogic.DataCollector;
import ch.ma3.plant.entities.Measurement;
import ch.ma3.plant.entities.Sensor;
import ch.ma3.plant.verticles.Database;

public class MockDataCollectorVerticle extends Verticle implements DataSource {

	private boolean running;
	private Database db = Database.getDatabaseInstance();
	private DataCollector dataCollector;

	@Override
	public void start() throws Exception {
		while (running) {

			int i = 0;
			for (Sensor sensor : db.getSensors()) {
				Measurement m = createMockMeasurement(i, sensor);
				dataCollector.logData(m);
				i++;
			}
		}
	}

	private Measurement createMockMeasurement(int i, Sensor sensor) {
		Measurement m = new Measurement();
		// One measurement each 10 Seconds
		m.setDate(new Date(System.currentTimeMillis()));
		m.setValue((float) (Math.pow(i + 1, i) * Math.random()));
		m.setSensor(sensor);
		sensor.addMeasurement(m);
		return m;
	}

	@Override
	public void setDataCollector(DataCollector dataCollector) {
		this.dataCollector = dataCollector;

	}
}
