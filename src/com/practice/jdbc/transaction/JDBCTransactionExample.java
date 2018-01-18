package com.practice.jdbc.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCTransactionExample {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_USER = "thakur";
	private static final String DB_PASSWORD = "thakur";
	private static final String insertTableSQL = "INSERT INTO DBUSER"
			+ "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) VALUES" + "(?,?,?,?)";

	private static final String updateTableSQL = "UPDATE DBUSER SET USERNAME =? " + "WHERE USER_ID = ?";

	public static void main(String[] args) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatementInsert = null;
		PreparedStatement preparedStatementUpdate = null;

		try {

			Class.forName(DB_DRIVER);

			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			if (dbConnection != null) {
				System.out.println("Connected to Database");
			}
			dbConnection.setAutoCommit(false);

			preparedStatementInsert = dbConnection.prepareStatement(insertTableSQL);
			preparedStatementInsert.setInt(1, 121);
			preparedStatementInsert.setString(2, "Mohan");
			preparedStatementInsert.setString(3, "system");
			preparedStatementInsert.setTimestamp(4, getCurrentTimeStamp());
			preparedStatementInsert.executeUpdate();

			preparedStatementUpdate = dbConnection.prepareStatement(updateTableSQL);
			
			preparedStatementUpdate.setString(1, "NareshMaheshSureshRajesh");
			preparedStatementUpdate.setInt(2, 121);
			preparedStatementUpdate.executeUpdate();

			dbConnection.commit();

			System.out.println("Done!");

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		} catch (SQLException e) {
			System.out.println("Rollback will start");
			System.out.println(e.getMessage());
			dbConnection.rollback();
		} catch (Exception e) {
			System.out.println("Rollback will start now");
			System.out.println(e.getMessage());
			dbConnection.rollback();
		} finally {
			System.out.println("Finally block will start");
			if (preparedStatementInsert != null) {
				preparedStatementInsert.close();
			}

			if (preparedStatementUpdate != null) {
				preparedStatementUpdate.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
	}

	private static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}

}
