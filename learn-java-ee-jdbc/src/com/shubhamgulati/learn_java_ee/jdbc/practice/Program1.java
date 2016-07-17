package com.shubhamgulati.learn_java_ee.jdbc.practice;

import java.sql.SQLException;

//import com.mysql.jdbc.Driver;

public class Program1 {
	public static void main(String[] args) {
		try {
			com.mysql.jdbc.Driver driverRef = new com.mysql.jdbc.Driver();
			java.sql.DriverManager.registerDriver(driverRef);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
