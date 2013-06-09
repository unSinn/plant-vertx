package ch.ma3.plant.datasources;

import java.sql.Date;

import org.vertx.java.core.Handler;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.logging.impl.LoggerFactory;
import org.vertx.java.deploy.Verticle;

import ch.ma3.plant.entities.Measurement;
import ch.ma3.plant.entities.Sensor;
import ch.ma3.plant.verticles.DataCollector;
import ch.ma3.plant.verticles.Database;

public class MockDataSource extends Verticle implements DataSource {

	private Database db = Database.getDatabaseInstance();
	private DataCollector dataCollector;
	private Logger log = LoggerFactory.getLogger(MockDataSource.class);

	MockDataSource() {
		super();
		// hiding Constructor
	}

	@Override
	public void start() throws Exception {
		vertx.setPeriodic(10000, new Handler<Long>() {
			@Override
			public void handle(Long event) {
				int i = 0;
				for (Sensor sensor : db.getSensors()) {
					Measurement m = createFakeMeasurement(i, sensor);
					dataCollector.saveData(m);
					i++;
				}

			}
		});
	}

	private Measurement createFakeMeasurement(int i, Sensor sensor) {
		Measurement m = new Measurement();
		m.setDate(new Date(System.currentTimeMillis()));
		m.setValue((float) (Math.pow(i + 1, i) * Math.random()));
		m.setSensor(sensor);
		sensor.addMeasurement(m);
		log.info("MockMeasurement created:" + m.toString());
		return m;
	}

	@Override
	public void setDataCollector(DataCollector dataCollector) {
		this.dataCollector = dataCollector;

	}
}
