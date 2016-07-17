/**
 * Write a Java program which accepts registration number and gets the data corresponding to that registration number from the database and print on the console.
 * If the registration number is not present then print an error message.
*/

package com.shubhamgulati.learn_java_ee.jdbc.assignments;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.shubhamgulati.learn_java_ee.jdbc.common.DBProperties;

public class Assignment6 {
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DBProperties.DRIVER_CLASS_NAME);
			FileReader fReader = new FileReader(DBProperties.DB_PROPERTIES_FILE_PATH);
			Properties dbProperties = new Properties();
			dbProperties.load(fReader);
			String dbUrl = DBProperties.DB_CONNECTION_URL;
			
			con = DriverManager.getConnection(dbUrl, dbProperties);
			
			String query = "SELECT students_info.reg_no, first_name, middle_name, last_name, guardian_first_name, guardian_middle_name, guardian_last_name, is_admin, password FROM students_info, guardian_info, students_other_info WHERE students_info.reg_no=?";
			pstmt = con.prepareStatement(query);
			
			System.out.print("Enter registration number: ");
			int reg_no = scn.nextInt();
			pstmt.setInt(1, reg_no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("==========================================");
				System.out.println("Reg. No.: " + rs.getString("reg_no"));
				System.out.println("First Name: " + rs.getString("first_name"));
				System.out.println("Middle Name: " + rs.getString("middle_name"));
				System.out.println("Last Name: " + rs.getString("last_name"));
				System.out.println("==========================================");
				System.out.println("Guardian's First Name: " + rs.getString("guardian_first_name"));
				System.out.println("Guardian's Middle Name: " + rs.getString("guardian_middle_name"));
				System.out.println("Guardian's Last Name: " + rs.getString("guardian_last_name"));
				System.out.println("==========================================");
				System.out.println("Is this administrator?: " + rs.getString("is_admin"));
				System.out.println("Password: " + rs.getString("password"));
				System.out.println("==========================================");
			} else {
				System.err.println("Registration number not found in DB.");
			}
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
			
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // end of inner try-catch blocks
		} // end of outer try-catch-finally block
	} // end of main method
} // end of the class
