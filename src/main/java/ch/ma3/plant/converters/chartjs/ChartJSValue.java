package ch.ma3.plant.converters.chartjs;

import java.util.ArrayList;
import java.util.List;

public class ChartJSValue {

	private List<Float> data;
	private String fillColor;
	private String strokeColor;
	private String pointColor;
	private String pointStrokeColor;

	public ChartJSValue() {
		data = new ArrayList<>();
		String color = genRandomRGB();
		fillColor = "rgba(" + color + ",0.1)";
		strokeColor = "rgba(" + color + ",1)";
		pointColor = "rgba(" + color + ",1)";
		pointStrokeColor = "#fff";
	}

	private String genRandomRGB() {

		return genColor() + "," + genColor() + "," + genColor();
	}

	private int genColor() {
		return (int) (Math.random() * 100 + 100);
	}

	public String getFillColor() {
		return fillColor;
	}

	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}

	public String getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(String strokeColor) {
		this.strokeColor = strokeColor;
	}

	public String getPointColor() {
		return pointColor;
	}

	public void setPointColor(String pointColor) {
		this.pointColor = pointColor;
	}

	public String getPointStrokeColor() {
		return pointStrokeColor;
	}

	public void setPointStrokeColor(String pointStrokeColor) {
		this.pointStrokeColor = pointStrokeColor;
	}

	public List<Float> getData() {
		return data;
	}

	public void setData(List<Float> data) {
		this.data = data;
	}

}
