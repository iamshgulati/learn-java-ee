package com.shubhamgulati.jee.jdbc.introduction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class MyFirstJDBCProgram_v1 {
	
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			/*
			 * 1. Load the driver
			 * Driver class: com.mysql.jdbc.Driver
			 */
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			
			/*
			 * 2. Get the DB Connection via Driver
			 */
			String dbUrl = "jdbc:mysql://127.0.0.1:3306/bece6_db?user=root&password=root";
			
			con = DriverManager.getConnection(dbUrl);
			
			/*
			 * 3. Issue SQL queries via connection
			 */
			String query = "SELECT * FROM students_info";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
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
		} finally {
			
			/*
			 * 5. Close all JDBC objects
			 */
			try {
				if(con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} // end of inner try-catch block
		} // end of outer try-catch-finally block
	} // end of main() method
} // end of the class