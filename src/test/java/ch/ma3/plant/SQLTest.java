package ch.ma3.plant;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import ch.ma3.plant.entities.Measurement;
import ch.ma3.plant.entities.Sensor;
import ch.ma3.plant.verticles.Database;

public class SQLTest {

	@Test
	public void test() throws SQLException {
		int numberOfMeasurements = 100;

		Database db = Database.getDatabaseInstance();
		db.wipeData();

		Sensor sensor = new Sensor();
		sensor.setName("TestSensor");
		db.saveSensor(sensor);

		for (int i = 0; i < numberOfMeasurements; i++) {
			Measurement m = new Measurement();
			// One measurement each 10 Seconds
			m.setDate(new Date(System.currentTimeMillis() + 10000 * i));
			m.setValue(i + 1.23f);
			m.setSensor(sensor);
			sensor.addMeasurement(m);
			db.saveMesurement(m);
		}

		Assert.assertEquals(numberOfMeasurements, db.getMeasurements().size());
		Assert.assertEquals(1, db.getSensors().size());
	}

	@Test
	public void testMultiSensor() throws SQLException {
		int numberOfSensors = 5;
		int numberOfMeasurements = 100;

		Database db = Database.getDatabaseInstance();
		db.wipeData();

		for (int i = 0; i < numberOfSensors; i++) {
			Sensor sensor = new Sensor();
			sensor.setName("TestSensor" + i);
			float sensorRange = i;
			db.saveSensor(sensor);

			for (int j = 0; j < numberOfMeasurements; j++) {
				Measurement m = new Measurement();
				// One measurement each 10 Seconds
				m.setDate(new Date(System.currentTimeMillis() + (10000 * j)));
				m.setValue((float) (Math.pow(i + 1, sensorRange) * Math
						.random()));
				m.setSensor(sensor);
				sensor.addMeasurement(m);
				db.saveMesurement(m);
			}
		}

		Assert.assertEquals(numberOfMeasurements * numberOfSensors, db
				.getMeasurements().size());
		Assert.assertEquals(numberOfSensors, db.getSensors().size());
	}
}
