package com.practice.jdbc.callablestatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Type4InvokeProcedure {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_USER = "thakur";
	private static final String DB_PASSWORD = "thakur";
	private static final String insertStoreProc = "{call insertDBUSER(?,?,?,?)}";

	public static void main(String[] args) throws SQLException {

		Connection connection = null;
		CallableStatement callableStatement = null;

		try {

			Class.forName(DB_DRIVER);

			connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

			if (connection != null) {
				System.out.println("Connected to Database");
			}

			callableStatement = connection.prepareCall(insertStoreProc);
			callableStatement.setInt(1, 104);
			callableStatement.setString(2, "Narendra");
			callableStatement.setString(3, "system");
			callableStatement.setDate(4, getCurrentDate());

			// execute insertDBUSER store procedure
			callableStatement.executeUpdate();

			System.out.println("Record is inserted into DBUSER table!");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			return;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (callableStatement  != null) {
				callableStatement .close();
			}

			if (connection != null) {
				connection.close();
			}

		}
	}

	private static java.sql.Date getCurrentDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}

}
