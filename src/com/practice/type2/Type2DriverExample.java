package com.practice.type2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Type2DriverExample {

	private static final String DB_DRIVER = "oracle.jdbc.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:oci8:@xe";
	private static final String DB_USER = "thakur";
	private static final String DB_PASSWORD = "thakur";
	private static final String selectTableSQL = "SELECT * from product";


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
			ResultSet rs = statement.executeQuery(selectTableSQL);

			while (rs.next()) {

				String product_id = rs.getString("product_id");
				String product_name = rs.getString("product_name");
				int  price = rs.getInt("price");
				int  quantity = rs.getInt("quantity");

				System.out.println(product_id + "\t"+ product_name + "\t" + price + "\t"+ quantity );

			}
			
			

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			return;

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}

		}
	}

}
