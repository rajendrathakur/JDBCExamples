package com.practice.jdbc.callablestatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class Type4InvokeProcedureOut {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_USER = "thakur";
	private static final String DB_PASSWORD = "thakur";
	private static final String insertStoreProc = "{call getDBUSERByUserId(?,?,?,?)}";

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
			callableStatement.setInt(1, 104	);
			callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(4, java.sql.Types.DATE);


			// execute getDBUSERByUserId store procedure
			callableStatement.executeUpdate();

			String userName = callableStatement.getString(2);
			String createdBy = callableStatement.getString(3);
			Date createdDate = callableStatement.getDate(4);

			System.out.println("UserName : " + userName);
			System.out.println("CreatedBy : " + createdBy);
			System.out.println("CreatedDate : " + createdDate);
			
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
