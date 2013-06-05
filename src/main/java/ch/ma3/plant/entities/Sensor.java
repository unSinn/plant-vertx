package ch.ma3.plant.entities;

import java.util.ArrayList;
import java.util.Collection;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "sensors")
public class Sensor {

	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField()
	private String name;
	@DatabaseField()
	private String color;
	@ForeignCollectionField(eager = false)
	private Collection<Measurement> measurements;

	public Sensor() {
		measurements = new ArrayList<>();
	}

	public ArrayList<Measurement> getMeasurements() {
		return new ArrayList<Measurement>(measurements);
	}

	public void addMeasurement(Measurement m) {
		measurements.add(m);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
