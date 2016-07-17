package com.shubhamgulati.learn_java_ee.jdbc.statements;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.shubhamgulati.learn_java_ee.jdbc.common.DBProperties;

public class CallableStatementExample3 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		Connection con = null;
		CallableStatement cstmt = null;
		
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
			String query = "call studentUpSert(?,?,?,?)";
			cstmt = con.prepareCall(query);
			
			System.out.print("Enter registration number: ");
			int regno = scn.nextInt();
			cstmt.setInt(1, regno);
			
			System.out.print("Enter First Name: ");
			String firstname = scn.next();
			cstmt.setString(2, firstname);
			
			System.out.print("Enter Middle Name: ");
			String middlename = scn.next();
			cstmt.setString(3, middlename);
			
			System.out.print("Enter Last Name: ");
			String lastname = scn.next();
			cstmt.setString(4, lastname);
			
			int count = cstmt.executeUpdate();

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
			scn.close();
			
			/*
			 * 5. Close all JDBC objects
			 */
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				cstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // end of inner try-catch blocks
		} // end of outer try-catch-finally block
	} // end of main() method
} // end of the class
