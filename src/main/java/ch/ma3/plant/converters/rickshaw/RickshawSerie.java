package ch.ma3.plant.converters.rickshaw;

import java.util.ArrayList;

class RickshawSerie {

	private ArrayList<RickshawValue> data;
	private String color;
	private String name;

	public RickshawSerie() {
		data = new ArrayList<>();
		color = "steelblue";
	}

	public String getName() {
		return name;
	}

	public void setName(String sensorName) {
		this.name = sensorName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public ArrayList<RickshawValue> getData() {
		return data;
	}

	public void addData(RickshawValue v) {
		data.add(v);
	}

}
