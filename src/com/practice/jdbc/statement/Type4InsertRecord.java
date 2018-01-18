package com.practice.jdbc.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Type4InsertRecord {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_USER = "thakur";
	private static final String DB_PASSWORD = "thakur";
	private static final String insertRecordSQL = "insert into product (product_id,product_name,price,quantity)"
			+ "values (104,'IPhone',55000,14)";

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
			int rowCount = statement.executeUpdate(insertRecordSQL);

			System.out.println(rowCount + " " + "Record is inserted successfully");

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
