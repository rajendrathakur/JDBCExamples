package com.practice.jdbc.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Type4BatchUpdate {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_USER = "thakur";
	private static final String DB_PASSWORD = "thakur";
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public static void main(String[] args) throws SQLException {

		Connection connection = null;
		Statement statement = null;

		String insertTableSQL1 = "INSERT INTO DBUSER" + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) " + "VALUES"
				+ "(101,'mkyong','system', " + "to_date('" + getCurrentTimeStamp() + "', 'yyyy/mm/dd hh24:mi:ss'))";

		String insertTableSQL2 = "INSERT INTO DBUSER" + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) " + "VALUES"
				+ "(102,'mkyong','system', " + "to_date('" + getCurrentTimeStamp() + "', 'yyyy/mm/dd hh24:mi:ss'))";

		String insertTableSQL3 = "INSERT INTO DBUSER" + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) " + "VALUES"
				+ "(103,'mkyong','system', " + "to_date('" + getCurrentTimeStamp() + "', 'yyyy/mm/dd hh24:mi:ss'))";

		try {

			Class.forName(DB_DRIVER);

			connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			if (connection != null) {
				System.out.println("Connected to Database");
			}
			statement = connection.createStatement();
			connection.setAutoCommit(false);

			statement.addBatch(insertTableSQL1);
			statement.addBatch(insertTableSQL2);
			statement.addBatch(insertTableSQL3);

			statement.executeBatch();

			connection.commit();

			System.out.println("Records are inserted into DBUSER table!");

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

	private static String getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return dateFormat.format(today.getTime());

	}

}
