/**
 * Write a Java program which accepts the following input arguments in the same order and invokes sttudentUpSert2 procedure stored in the database by passing these arguments:
 * 1. Reg No.
 * 2. First Name
 * 3. Middle Name
 * 4. Last Name
 * 5. Guardian First Name
 * 6. Guardian Middle Name
 * 7. GUardian Last Name
 * 8. Is Admin
 * 9. Password
*/

package com.shubhamgulati.jee.jdbc.assignments;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.shubhamgulati.jee.jdbc.common.DBProperties;

public class Assignment5 {
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		Connection con = null;
		CallableStatement cstmt = null;
		
		try {
			Class.forName(DBProperties.DRIVER_CLASS_NAME);
			FileReader fReader = new FileReader(DBProperties.DB_PROPERTIES_FILE_PATH);
			Properties dbProperties = new Properties();
			dbProperties.load(fReader);
			String dbUrl = DBProperties.DB_CONNECTION_URL;
			
			con = DriverManager.getConnection(dbUrl, dbProperties);
			
			String query = "call studentUpSert2(?,?,?,?,?,?,?,?,?)";
			cstmt = con.prepareCall(query);
			
			System.out.print("Enter registration number: ");
			int reg_no = scn.nextInt();
			cstmt.setInt(1, reg_no);
			
			System.out.print("Enter first name: ");
			String first_name = scn.next();
			cstmt.setString(2, first_name);
			
			System.out.print("Enter middle name: ");
			String middle_name = scn.next();
			cstmt.setString(3, middle_name);
			
			System.out.print("Enter last name: ");
			String last_name = scn.next();
			cstmt.setString(4, last_name);
			
			System.out.print("Enter guardian's first name: ");
			String guardian_first_name = scn.next();
			cstmt.setString(5, guardian_first_name);
			
			System.out.print("Enter guardian's middle name: ");
			String guardian_middle_name = scn.next();
			cstmt.setString(6, guardian_middle_name);
			
			System.out.print("Enter guardian's last name: ");
			String guardian_last_name = scn.next();
			cstmt.setString(7, guardian_last_name);
			
			System.out.print("Is this an administrator (y/n): ");
			String is_admin = scn.next();
			cstmt.setString(8, is_admin);
			
			System.out.print("Enter password: ");
			String password = scn.next();
			cstmt.setString(9, password);
			
			cstmt.executeUpdate();			
			
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
				cstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // end of inner try-catch blocks
		} // end of outer try-catch-finally block
	} // end of main method
} // end of the class
