package ch.ma3.plant.converters;

import java.util.ArrayList;
import java.util.List;

public class ChartJSDataSet {

	private List<ChartJSData> datasets;
	private List<String> labels;

	public ChartJSDataSet() {
		datasets = new ArrayList<>();
		labels = new ArrayList<>();
	}

	public List<ChartJSData> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<ChartJSData> datasets) {
		this.datasets = datasets;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

}
