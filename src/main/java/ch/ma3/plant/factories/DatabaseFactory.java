package ch.ma3.plant.factories;

import java.sql.SQLException;

import ch.ma3.plant.entities.Mesurement;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseFactory {

	private Dao<Mesurement, Integer> mesurementDao;

	public DatabaseFactory() {
		try {
			Class.forName("org.sqlite.JDBC");
			String databaseUrl = "jdbc:sqlite:plant.db";

			ConnectionSource connectionSource = new JdbcConnectionSource(
					databaseUrl);

			mesurementDao = DaoManager.createDao(connectionSource,
					Mesurement.class);

			TableUtils.createTable(connectionSource, Mesurement.class);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void addMesurement(Mesurement m) {
		try {
			mesurementDao.create(m);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
