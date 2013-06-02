package ch.ma3.plant.businesslogic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.logging.impl.LoggerFactory;

import ch.ma3.plant.converters.ChartJSData;
import ch.ma3.plant.converters.ChartJSDataConverter;
import ch.ma3.plant.converters.ChartJSDataSet;
import ch.ma3.plant.entities.Measurement;
import ch.ma3.plant.entities.Sensor;
import ch.ma3.plant.factories.EventBusFactory;
import ch.ma3.plant.verticles.Database;

public class Logic {

	Logger log = LoggerFactory.getLogger(Logic.class);
	private EventBus eb;
	private Database db;

	public Logic(EventBus eb) {
		this.eb = eb;
		db = Database.getDatabaseInstance();
	}

	public void gotDataRequestFromClient() {
		log.info("Got DataRequest From Client.");
		
		ChartJSDataSet dataSet = new ChartJSDataSet();
		List<Sensor> sensors = db.getSensors();
		List<ChartJSData> dataSets =new ArrayList<>(sensors.size());
		for(Sensor s : sensors){
			ChartJSData data = new ChartJSData();
			LinkedList<Float> values = new LinkedList<>();
			for(Measurement m : s.getMeasurements()){
				values.add(m.getValue());
			}
			data.setData(values);
			dataSets.add(data);
		}
		dataSet.setDatasets(dataSets);
		
		eb.publish(EventBusFactory.CLIENT, ChartJSDataConverter.getChartJSData(dataSet));
		log.info("Data sent.");
	}
}
