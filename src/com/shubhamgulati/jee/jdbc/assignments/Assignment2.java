/**
 * Write a Java program which accepts the following input arguments in the same order:
 * 1. Reg No.
 * 2. Current Password
 * 3. New Password
 * 
 * This program must first check whether Reg No. and current password is matching.
 * - If NO, then print an "Error message" in the console.
 * - If YES, then print a "Success message" in the console and update the password for that particular Reg No.
*/

package com.shubhamgulati.jee.jdbc.assignments;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.shubhamgulati.jee.jdbc.common.DBProperties;

public class Assignment2 {
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DBProperties.DRIVER_CLASS_NAME);
			FileReader fReader = new FileReader(DBProperties.DB_PROPERTIES_FILE_PATH);
			Properties dbProperties = new Properties();
			dbProperties.load(fReader);
			String dbUrl = DBProperties.DB_CONNECTION_URL;
			
			con = DriverManager.getConnection(dbUrl, dbProperties);
			
			String query = "UPDATE students_other_info SET password=? WHERE regno=? AND password=?";
			pstmt = con.prepareStatement(query);
			
			System.out.print("Enter registration password: ");
			int regno = scn.nextInt();
			pstmt.setInt(2, regno);
			
			System.out.print("Enter current password: ");
			String current_password = scn.next();
			pstmt.setString(3, current_password);
			
			System.out.print("Enter new password: ");
			String new_password = scn.next();
			pstmt.setString(1, new_password);
			
			if(pstmt.execute()) {
				System.out.println("Password changed. Record successfully updated.");
			} else {
				System.err.println("Reg No. or password does not match. Could not update the record.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
			} // end of try-catch blocks
		} // end of outer try-catch-finally block
	} // end of main method
} // end of the class
