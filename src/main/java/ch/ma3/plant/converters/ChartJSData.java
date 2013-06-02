package ch.ma3.plant.converters;

import java.util.ArrayList;
import java.util.List;

public class ChartJSData {

	private List<Float> data;

	public ChartJSData() {
		data = new ArrayList<>();
	}

	public List<Float> getData() {
		return data;
	}

	public void setData(List<Float> data) {
		this.data = data;
	}

}
