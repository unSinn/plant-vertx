package ch.ma3.plant.entities;

import java.util.Collection;
import java.util.LinkedList;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "sensors")
public class Sensor {

	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField()
	private String name;
	@ForeignCollectionField(eager = false)
	private Collection<Measurement> measurements;

	public Sensor() {
		measurements = new LinkedList<>();
	}

	public Collection<Measurement> getMeasurements() {
		return measurements;
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

}
