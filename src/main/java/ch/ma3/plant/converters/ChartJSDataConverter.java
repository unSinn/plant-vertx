package ch.ma3.plant.converters;

import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

public class ChartJSDataConverter {
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
	public static JsonObject getChartJSData(ChartJSDataSet dataset) {
		JsonObject json = new JsonObject(Json.encode(dataset));
		return json;
	}
}
