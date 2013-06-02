package ch.ma3.plant;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.Test;

import ch.ma3.plant.entities.Measurement;
import ch.ma3.plant.entities.Sensor;
import ch.ma3.plant.verticles.Database;

public class SQLTest {

	@Test
	public void test() throws SQLException {
		Database db = Database.getDatabaseInstance();
		db.wipeData();

		Sensor sensor = new Sensor();
		sensor.setName("TestSensor");

		Measurement m = new Measurement();
		m.setDate(new Date(System.currentTimeMillis()));
		m.setValue(1.23f);
		m.setSensor(sensor);
		sensor.addMeasurement(m);

		db.saveMesurement(m);
	}
}
