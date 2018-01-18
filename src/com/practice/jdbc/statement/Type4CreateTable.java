package com.practice.jdbc.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Type4CreateTable {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_USER = "thakur";
	private static final String DB_PASSWORD = "thakur";
	private static final String createTableSQL = "CREATE TABLE DBUSER(" + "USER_ID NUMBER(5) NOT NULL, "
			+ "USERNAME VARCHAR(20) NOT NULL, " + "CREATED_BY VARCHAR(20) NOT NULL, " + "CREATED_DATE DATE NOT NULL, "
			+ "PRIMARY KEY (USER_ID) " + ")";

	public static void main(String[] args) throws SQLException {

		Connection connection = null;
		Statement statement = null;

		try {

			Class.forName(DB_DRIVER);

			connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			if (connection != null) {
				System.out.println("Connected to Database");
			}
			statement = connection.createStatement();
			boolean result = statement.execute(createTableSQL);

			System.out.println("Table is created successfully " + result);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {

			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}

		}
	}

}
