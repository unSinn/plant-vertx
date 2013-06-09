package ch.ma3.plant.verticles;

import ch.ma3.plant.entities.Measurement;


public interface DataCollector {
	void saveData(Measurement measurement);
}
