package ch.ma3.plant;

import java.sql.Date;

import org.junit.Test;

import ch.ma3.plant.entities.Mesurement;
import ch.ma3.plant.verticles.Database;

public class SQLTest {

	@Test
	public void test() {
		Database db = Database.getDatabaseInstance();

		Mesurement m = new Mesurement();
		m.setDate(new Date(System.currentTimeMillis()));
		m.setValue(1.23f);

		db.addMesurement(m);
	}
}
