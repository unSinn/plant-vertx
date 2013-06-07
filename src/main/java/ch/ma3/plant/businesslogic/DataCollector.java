package ch.ma3.plant.businesslogic;

import ch.ma3.plant.entities.Measurement;


public interface DataCollector {

	void logData(Measurement measurement);
}
