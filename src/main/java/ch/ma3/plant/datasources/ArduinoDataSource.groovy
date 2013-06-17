package ch.ma3.plant.datasources

import org.vertx.java.deploy.Verticle;

import ch.ma3.plant.verticles.DataCollector;


class ArduinoDataSource extends Verticle implements DataSource{

	def dataCollector

	ArduinoDataSource(){
		// hiding Constructor
	}

	@Override
	void setDataCollector(DataCollector collector) {
		dataCollector = collector
	}

	@Override
	void start() throws Exception {
		// start listening to SerialEvents
	}
}
