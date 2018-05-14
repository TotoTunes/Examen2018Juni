package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

import model.User;

/**
 * @author Bart Taelemans & Thomas Vanden Bossche
 * @date 1 jan. 2018
 * @project Afstandsbediening
 * @purpose Class voor aanmaken DB-connectie
 *
 */

public class DAO {

	private String Query = "Select * from afstandsbediening.bewoners";

	private Properties properties;
	private Connection conn;

	/**
	 * @return the conn
	 */
	public Connection getConn() {
		return conn;
	}

	/**
	 * @param conn
	 *            the conn to set
	 */
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	private Properties GetDBproperties(String filename) throws IOException {
		properties = new Properties();
		try {
			FileInputStream fileInputStream = new FileInputStream(filename);
			properties.load(fileInputStream);
			fileInputStream.close();
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "verbinding niet gelukt");
			e1.printStackTrace();
		}

		return properties;

	}

	public Connection ConnectDB() throws IOException {
		Connection connection;
		GetDBproperties("DBproperties.properties");
		try {
			connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"),
					properties.getProperty("password"));
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
		return connection;
	}

	public ArrayList<User> loadUserFromDB() throws IOException, SQLException {
		conn = ConnectDB();
		Statement statement = (Statement) conn.createStatement();
		ResultSet resultSet = statement.executeQuery(Query);
		User user = null;
		ArrayList<User> personen = new ArrayList<User>();
		while (resultSet.next()) {
			String voornaam = resultSet.getString("voornaam");
			String achternaam = resultSet.getString("achternaam");
			double frequency = resultSet.getDouble("frequency");
			boolean acces = resultSet.getBoolean("acces");
			user = new User(acces, frequency, achternaam, voornaam);
			personen.add(user);
		}
		statement.close();
		resultSet.close();
		return personen;
	}

	public void insertDB(User user) throws IOException, SQLException {
		conn = ConnectDB();
		Statement statement = (Statement) conn.createStatement();

		String insert = "insert into bewoners(voornaam, achternaam, frequency, acces)  values ('" + user.getFirstName()
				+ "','" + user.getLastName() + "', " + user.getFrequency() + ", " + user.isAcces() + ")";
		statement.execute(insert);
		statement.close();
	}

	public void removeDB(User user) throws IOException, SQLException {
		conn = ConnectDB();
		Statement statement = (Statement) conn.createStatement();

		String remove = "UPDATE bewoners SET acces = '" + 0 + "' WHERE voornaam ='" + user.getFirstName()
				+ "' AND achternaam = '" + user.getLastName()+"'";
		statement.execute(remove);
		statement.close();
	}
	
	public void setAccessTrue(User user) throws IOException, SQLException {
		conn = ConnectDB();
		Statement statement = (Statement) conn.createStatement();

		String accessTrue = "UPDATE bewoners SET acces = '" + 1 + "' WHERE voornaam ='" + user.getFirstName()
				+ "' AND achternaam = '" + user.getLastName()+"'";
		statement.execute(accessTrue);
		statement.close();
	}

	public void updateDB(double frequency) throws SQLException, IOException {
		conn = ConnectDB();
		Statement statement = (Statement) conn.createStatement();
		String update = "UPDATE bewoners SET frequency = " + frequency + " WHERE acces= 1";
		statement.execute(update);
		statement.close();
	}
}