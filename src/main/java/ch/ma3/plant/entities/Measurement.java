package ch.ma3.plant.entities;

import java.sql.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mesurements")
public class Measurement {
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField()
	private float value;
	@DatabaseField()
	private Date date;
	@DatabaseField(canBeNull = false, foreign = true)
	private Sensor sensor;

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}