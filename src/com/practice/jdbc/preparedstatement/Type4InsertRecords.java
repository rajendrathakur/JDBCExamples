package com.practice.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Type4InsertRecords {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_USER = "thakur";
	private static final String DB_PASSWORD = "thakur";
	private static final String insertRecordSQL = "insert into product (product_id,product_name,price,quantity)"
			+ "values (?,?,?,?)";

	public static void main(String[] args) throws SQLException {

		Connection connection = null;
		PreparedStatement prepareStatement = null;

		try {

			Class.forName(DB_DRIVER);

			connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			if (connection != null) {
				System.out.println("Connected to Database");
			}
			prepareStatement = connection.prepareStatement(insertRecordSQL);
			
			prepareStatement.setInt(1 , 104);
			prepareStatement.setString(2 , "Fan");
			prepareStatement.setInt(3 , 15);
			prepareStatement.setInt(4 , 22);
			
			prepareStatement.executeUpdate();
			
			prepareStatement.setInt(1 , 105);
			prepareStatement.setString(2 , "Pen");
			prepareStatement.setInt(3 , 18);
			prepareStatement.setInt(4 , 22);
			
			prepareStatement.executeUpdate();
			
			prepareStatement.setInt(1 , 106);
			prepareStatement.setString(2 , "Pencil");
			prepareStatement.setInt(3 , 14);
			prepareStatement.setInt(4 , 25);
			
			prepareStatement.executeUpdate();
			

			System.out.println("Records are inserted successfully");

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {

			if (prepareStatement != null) {
				prepareStatement.close();
			}

			if (connection != null) {
				connection.close();
			}

		}
	}

}
