package com.shubhamgulati.jee.jdbc.introduction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.mariadb.jdbc.Driver;

public class MyFirstJDBCProgram {
	
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			/*
			 * 1. Load the driver
			 * Driver class: org.mariadb.jdbc.Driver
			 */
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			
			/*
			 * 2. Get the DB Connection via Driver
			 */
			String dbUrl = "jdbc:mysql://localhost:3306/BECE67_DB?user=root&password=root";
			
			con = DriverManager.getConnection(dbUrl);
			
			/*
			 * 3. Issue SQL queries via connection
			 */
			String query = "select * from students_info";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			/*
			 * 4. Process the results returned by SQL queries
			 */
			while(rs.next()) {
				int regNum = rs.getInt("regno");
				String fnm = rs.getString("firstname");
				String mnm = rs.getString("middlename");
				String lnm = rs.getString("lastname");
				
				System.out.println("Reg. No.: " + regNum);
				System.out.println("First Name: " + fnm);
				System.out.println("Middle Name: " + mnm);
				System.out.println("Last Name: " + lnm);
				System.out.println("==========================================");
			} //end of while loop
			
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
			} //end of inner try-catch block
		} //end of outer try-catch block
	} //end of main() method
} //end of the class

/*
 * SQL Statements:
 * 
 * create database BECE67_DB;
 * use BECE67_DB;
 * 
 * create table students_info (
 * regno int(10) not null,
 * firstname varchar(50),
 * middlename varchar(50),
 * lastname varchar(50),
 * primary key(regno)
 * );
 * 
 * insert into students_info
 * values (1,'Shubham', 'NA', 'Gulati'), (2, 'Ankit', 'NA', 'Gulati');
 * 
 */



















