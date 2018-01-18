package com.practice.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Type4UpdateRecords {
	
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_USER = "thakur";
	private static final String DB_PASSWORD = "thakur";
	private static final String updateRecordSQL = "update product set product_name = ? where product_id=?";


	public static void main(String[] args) throws SQLException {

		Connection connection = null;
		PreparedStatement prepareStatement = null;

		try {

			Class.forName(DB_DRIVER);

			connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			if (connection != null) {
				System.out.println("Connected to Database");
			}
			prepareStatement = connection.prepareStatement(updateRecordSQL);
			prepareStatement.setString(1 , "Battery");
			prepareStatement.setInt(2 , 104);
			
			prepareStatement.executeUpdate();
			
			prepareStatement.setString(1 , "Chip");
			prepareStatement.setInt(2 , 105);
			
			prepareStatement.executeUpdate();
			
			prepareStatement.setString(1 , "Sim");
			prepareStatement.setInt(2 , 106);
			
			prepareStatement.executeUpdate();

			System.out.println("Record is updated successfully");
			
		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {

			if (prepareStatement != null) {
				prepareStatement.close();
			}

			if (connection != null) {
				connection.close();
			}

		}
	}


}
