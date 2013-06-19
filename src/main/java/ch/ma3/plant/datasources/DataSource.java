package ch.ma3.plant.datasources;

import ch.ma3.plant.verticles.DataCollector;

public interface DataSource {
	DataSource setDataCollector(DataCollector collector);
}
