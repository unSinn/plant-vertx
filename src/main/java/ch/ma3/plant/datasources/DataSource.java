package ch.ma3.plant.datasources;

import ch.ma3.plant.verticles.DataCollector;



public interface DataSource {
	void setDataCollector(DataCollector collector);
}
