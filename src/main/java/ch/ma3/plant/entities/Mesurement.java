package ch.ma3.plant.entities;

import java.sql.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mesurements")
public class Mesurement {
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField()
	private float value;
	@DatabaseField()
	private Date date;

	public Mesurement() {
		
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