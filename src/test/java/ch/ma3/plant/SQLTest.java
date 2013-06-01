package ch.ma3.plant;
import java.sql.Date;

import org.junit.Test;

import ch.ma3.plant.entities.Mesurement;
import ch.ma3.plant.factories.DatabaseFactory;

public class SQLTest {

	@Test
	public void test() {
		DatabaseFactory db = new DatabaseFactory();

		Mesurement m = new Mesurement();
		m.setDate(new Date(System.currentTimeMillis()));
		m.setValue(1);

		db.addMesurement(m);
	}
}
