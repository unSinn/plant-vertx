package ch.ma3.plant.verticles;

import java.sql.SQLException;

import org.vertx.java.core.json.JsonArray;

import ch.ma3.plant.entities.Mesurement;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class Database {

	private Dao<Mesurement, Integer> measurementDao;
	private static Database instance;

	private Database() {
		try {
			Class.forName("org.sqlite.JDBC");
			String databaseUrl = "jdbc:sqlite:plant.db";

			ConnectionSource connectionSource = new JdbcConnectionSource(
					databaseUrl);

			measurementDao = DaoManager.createDao(connectionSource,
					Mesurement.class);

			TableUtils.createTableIfNotExists(connectionSource,
					Mesurement.class);

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

	public void addMesurement(Mesurement m) {
		try {
			measurementDao.create(m);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public JsonArray getMeasurements() {
		try {
			Object[] mesurements = measurementDao.queryForAll().toArray();
			return new JsonArray(mesurements);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new JsonArray();
	}
}
