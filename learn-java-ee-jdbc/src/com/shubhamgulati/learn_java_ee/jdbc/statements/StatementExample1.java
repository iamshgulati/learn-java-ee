package com.shubhamgulati.learn_java_ee.jdbc.statements;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.shubhamgulati.learn_java_ee.jdbc.common.DBProperties;

public class StatementExample1 {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		
		try {
						
			/*
			 * 1. Preferred way to load the driver
			 */
			Class.forName(DBProperties.DRIVER_CLASS_NAME);
			
			/*
			 * 2. Get the DB Connection via Driver
			 */
			FileReader fReader = new FileReader(DBProperties.DB_PROPERTIES_FILE_PATH);
			Properties dbProperties = new Properties();
			dbProperties.load(fReader);
			String dbUrl = DBProperties.DB_CONNECTION_URL;
			
			con = DriverManager.getConnection(dbUrl, dbProperties);
			
			/*
			 * 3. Issue SQL queries via connection
			 */
			String query = "INSERT INTO students_info VALUES (6,'Sansa','Eddard','Stark')";
			stmt = con.createStatement();
			int count = stmt.executeUpdate(query);
			
			/*
			 * 4. Process the results returned by SQL queries
			 */
			System.out.println("Rows affected: " + count);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			/*
			 * 5. Close all JDBC objects
			 */
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // end of inner try-catch blocks
		} // end of outer try-catch-finally block
	} // end of main() method
} // end of the class
