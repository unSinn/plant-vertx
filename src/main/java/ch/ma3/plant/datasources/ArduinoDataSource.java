package ch.ma3.plant.datasources;

import org.vertx.java.deploy.Verticle;

import ch.ma3.plant.verticles.DataCollector;

public class ArduinoDataSource extends Verticle implements DataSource {

	ArduinoDataSource() {
		// hiding Constructor
	}

	@Override
	public void setDataCollector(DataCollector collector) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() throws Exception {
		// TODO Auto-generated method stub

	}

}
