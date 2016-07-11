package com.shubhamgulati.jee.jdbc.statements;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.shubhamgulati.jee.jdbc.common.DBProperties;

public class StatementExample2 {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
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
			String query = "CREATE DATABASE temp";
			stmt = con.createStatement();
			boolean dbResult = stmt.execute(query);
			
			/*
			 * 4. Process the results returned by SQL queries
			 */
			if(dbResult) {
				System.out.println("Result is of type DB Result.");
				rs = stmt.getResultSet();
				while(rs.next()) {
					int regNum = rs.getInt("reg_no");
					String fnm = rs.getString("first_name");
					String mnm = rs.getString("middle_name");
					String lnm = rs.getString("last_name");
					
					System.out.println("Reg. No.: " + regNum);
					System.out.println("First Name: " + fnm);
					System.out.println("Middle Name: " + mnm);
					System.out.println("Last Name: " + lnm);
					System.out.println("==========================================");
				} // end of while loop
			} else {
				System.out.println("Result is of type integer.");
				int count = stmt.getUpdateCount();
				System.out.println("Rows affected: " + count);
			} // end of if-else block
			
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
			}
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // end of inner try-catch blocks
		} // end of outer try-catch-finally block
	} // end of main() method
} // end of the class
