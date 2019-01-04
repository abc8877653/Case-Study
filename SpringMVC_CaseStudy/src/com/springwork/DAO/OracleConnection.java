package com.springwork.DAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public abstract class OracleConnection {
	protected Connection conn = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;

	/**
	 * Uses custom getConnection() method (NOT from DriverManager class) method to
	 * make the connection. <br>
	 *
	 */
	public void connect() {
		conn = getConnection();
	}

	/**
	 * For ResultSet, PreparedStatement and Connection objects, check each to see if
	 * they are not null and not closed. <br>
	 * If so, close each. If not, do nothing for each respectively. <br>
	 * No need to do anything with the exceptions.
	 */
	public void dispose() {
		try {
			if (!rs.equals(null))
				if (!rs.isClosed())
					rs.close();
			if (!ps.equals(null))
				if (!ps.isClosed())
					ps.close();
			if (!conn.equals(null))
				if (!rs.isClosed())
					rs.close();
		} catch (SQLException e) {
		} catch (NullPointerException e) {
		}
	}

	/**
	 * From the Properties file provided, create and return the connection. <br>
	 * <b>Note:</b> Doesn't use register driver since the
	 * DriverManager.getConnection() method does not need it. <br>
	 * <b>Beneficial</b> as opposed to final variables including the password not
	 * being exposed in this program.
	 * 
	 * @return On success return Connection object for the database.<br>
	 *         Should there be a failure, return null.
	 */
	public Connection getConnection() {
		/*
		 * Create Properties object.
		 */
		final Properties properties = new Properties();
		/*
		 * Create an input stream from the file db.properties in the CoreJava.Resources
		 * package.
		 */
		final InputStream inputStream = OracleConnection.class.getClassLoader()
				.getResourceAsStream("com/springwork/resources/db.properties");
		/*
		 * Load the contents of the db.properties file into the Properties object.
		 */
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		/*
		 * Get the contents of db.properties and throw them into strings to be used to
		 * create the connection.
		 */
		final String url = properties.getProperty("url");
		final String user = properties.getProperty("user");
		final String password = properties.getProperty("password");

		/*
		 * Create connection.
		 */
		try {
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			final Connection connection = DriverManager.getConnection(url, user, password);
			return connection;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}
