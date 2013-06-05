package ch.ma3.plant.converters.chartjs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import ch.ma3.plant.entities.Measurement;
import ch.ma3.plant.entities.Sensor;

public class ChartJSDataConverter {

	private static final int NR_OF_LABELS = 5;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("d-M-Y h:m:s");

	/**
	 * 
	 * Should construct a JSON-String like here http://www.chartjs.org/docs/.
	 * 
	 * array of Strings named "labels"
	 * 
	 * array of Object named "datasets" containing array of values named "data".
	 * 
	 * @param dataset
	 * @return
	 */
	public static JsonObject sensorDataToCharJSData(List<Sensor> sensors) {

		ChartJSData data = new ChartJSData();
		List<ChartJSValue> dataSets = new ArrayList<>(sensors.size());
		for (Sensor s : sensors) {
			ChartJSValue value = convertMeasurmentDataToChartValue(s);
			dataSets.add(value);
		}
		data.setDatasets(dataSets);

		ArrayList<String> labels = addLabels(sensors.get(0));

		data.setLabels(labels);

		return new JsonObject(Json.encode(data));
	}

	private static ArrayList<String> addLabels(Sensor sensor) {
		ArrayList<String> labels = new ArrayList<>();
		ArrayList<Measurement> measurements = sensor.getMeasurements();

		float f = 1 / measurements.size();
		float labelDistance = 1 / NR_OF_LABELS;

		for (int i = 0; i < NR_OF_LABELS; i++) {
			labels.add(dateFormat.format(measurements.get(
					(int) (f * labelDistance * i)).getDate()));
		}

		return labels;
	}

	private static ChartJSValue convertMeasurmentDataToChartValue(Sensor s) {
		ChartJSValue value = new ChartJSValue();
		LinkedList<Float> values = new LinkedList<>();
		for (Measurement m : s.getMeasurements()) {
			values.add(m.getValue());
		}
		value.setData(values);
		return value;
	}
}
