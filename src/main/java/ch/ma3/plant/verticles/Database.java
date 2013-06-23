package ch.ma3.plant.verticles;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.ma3.plant.entities.Measurement;
import ch.ma3.plant.entities.Sensor;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class Database {

	private Dao<Measurement, Integer> measurementDao;
	private Dao<Sensor, Integer> sensorDao;
	private static Database instance;
	private static final String databaseUrl = "jdbc:sqlite:plant.db";
	private ConnectionSource connectionSource;

	private Database() {
		try {
			Class.forName("org.sqlite.JDBC");

			connectionSource = new JdbcConnectionSource(databaseUrl);

			createTables();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static Database getDatabaseInstance() {
		synchronized (Database.class) {
			if (instance == null) {
				instance = new Database();
			}
			return instance;
		}
	}

	private void createTables() throws SQLException {
		measurementDao = DaoManager.createDao(connectionSource,
				Measurement.class);
		sensorDao = DaoManager.createDao(connectionSource, Sensor.class);
		TableUtils.createTableIfNotExists(connectionSource, Measurement.class);
		TableUtils.createTableIfNotExists(connectionSource, Sensor.class);
	}

	public void wipeData() throws SQLException {
		TableUtils.dropTable(connectionSource, Measurement.class, false);
		TableUtils.dropTable(connectionSource, Sensor.class, false);
		createTables();
	}

	public void saveMesurement(Measurement m) {
		try {
			measurementDao.create(m);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveSensor(Sensor s) {
		try {
			sensorDao.create(s);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Measurement> getMeasurements() {
		try {
			List<Measurement> mesurements = measurementDao.queryForAll();
			return mesurements;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public Map<String, Sensor> getSensors() {
		HashMap<String, Sensor> map = new HashMap<>();
		try {
			List<Sensor> sensors = sensorDao.queryForAll();
			for (Sensor s : sensors) {
				map.put(s.getName(), s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
}
