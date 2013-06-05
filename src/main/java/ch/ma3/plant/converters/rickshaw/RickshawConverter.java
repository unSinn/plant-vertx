package ch.ma3.plant.converters.rickshaw;

import java.util.ArrayList;
import java.util.List;

import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import ch.ma3.plant.entities.Measurement;
import ch.ma3.plant.entities.Sensor;

public class RickshawConverter {

	public static JsonObject sensorDataToRickshaw(List<Sensor> sensors) {

		RickshawCollection collection = new RickshawCollection();

		for (Sensor s : sensors) {
			RickshawSerie serie = convertMeasurmentDataToChartValue(s);
			serie.setName(s.getName());
			serie.setColor(s.getColor());
			collection.addSerie(serie);
		}

		return new JsonObject(Json.encode(collection));
	}

	private static RickshawSerie convertMeasurmentDataToChartValue(Sensor s) {
		RickshawSerie serie = new RickshawSerie();
		ArrayList<Measurement> measurements = s.getMeasurements();
		for (int i = 0; i < measurements.size(); i++) {
			Measurement m = measurements.get(i);
			RickshawValue value = new RickshawValue();
			value.x = m.getDate().getTime() / 1000;
			value.y = m.getValue();
			serie.addData(value);
		}
		return serie;
	}

}
