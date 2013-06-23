package ch.ma3.plant.verticles;

import java.util.Map;

import ch.ma3.plant.entities.Measurement;
import ch.ma3.plant.entities.Sensor;

public interface DataCollector {
	void saveData(Measurement m);

	void addSensor(Sensor s);

	Map<String, Sensor> getSensors();
}
