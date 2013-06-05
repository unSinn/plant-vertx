package ch.ma3.plant.converters.chartjs;

import java.util.ArrayList;
import java.util.List;

public class ChartJSData {

	private List<ChartJSValue> datasets;
	private List<String> labels;

	public ChartJSData() {
		datasets = new ArrayList<>();
		labels = new ArrayList<>();
	}

	public List<ChartJSValue> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<ChartJSValue> datasets) {
		this.datasets = datasets;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

}
