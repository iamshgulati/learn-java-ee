package com.shubhamgulati.jee.jdbc.statements;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.shubhamgulati.jee.jdbc.common.DBProperties;

public class CallableStatementExample1 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		Connection con = null;
		CallableStatement cstmt = null;
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
			String query = "call getAllStudents()";
			cstmt = con.prepareCall(query);
			rs = cstmt.executeQuery();
			
			/*
			 * 4. Process the results returned by SQL queries
			 */
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
			}
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // end of inner try-catch blocks
		} // end of outer try-catch-finally block
	} // end of main() method
} // end of the class
