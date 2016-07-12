/**
 * Write a Java program which accepts the following input arguments in the same order and inserts into corresponding tables:
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

package com.shubhamgulati.jee.jdbc.transactions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.shubhamgulati.jee.jdbc.common.DBProperties;

public class TransactionExample1 {
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		
		try {
			Class.forName(DBProperties.DRIVER_CLASS_NAME);
			FileReader fReader = new FileReader(DBProperties.DB_PROPERTIES_FILE_PATH);
			Properties dbProperties = new Properties();
			dbProperties.load(fReader);
			String dbUrl = DBProperties.DB_CONNECTION_URL;
			
			con = DriverManager.getConnection(dbUrl, dbProperties);
			
			// begin the transaction
			con.setAutoCommit(false);
			
			String query1 = "INSERT INTO students_info VALUES (?,?,?,?)";
			pstmt1 = con.prepareStatement(query1);
			
			System.out.print("Enter registration number: ");
			int reg_no = scn.nextInt();
			pstmt1.setInt(1, reg_no);
			
			System.out.print("Enter first name: ");
			String first_name = scn.next();
			pstmt1.setString(2, first_name);
			
			System.out.print("Enter middle name: ");
			String middle_name = scn.next();
			pstmt1.setString(3, middle_name);
			
			System.out.print("Enter last name: ");
			String last_name = scn.next();
			pstmt1.setString(4, last_name);
			
			pstmt1.executeUpdate();
			
			String query2 = "INSERT INTO guardian_info VALUES (?,?,?,?)";
			pstmt2 = con.prepareStatement(query2);
			
			pstmt2.setInt(1, reg_no);
			
			System.out.print("Enter guardian's first name: ");
			String guardian_first_name = scn.next();
			pstmt2.setString(2, guardian_first_name);
			
			System.out.print("Enter guardian's middle name: ");
			String guardian_middle_name = scn.next();
			pstmt2.setString(3, guardian_middle_name);
			
			System.out.print("Enter guardian's last name: ");
			String guardian_last_name = scn.next();
			pstmt2.setString(4, guardian_last_name);
			
			pstmt2.executeUpdate();
			
			String query3 = "INSERT INTO students_other_info VALUES (?,?,?)";
			pstmt3 = con.prepareStatement(query3);
			
			pstmt3.setInt(1, reg_no);
			
			System.out.print("Is this an administrator (y/n): ");
			String is_admin = scn.next();
			pstmt3.setString(2, is_admin);
			
			System.out.print("Enter password: ");
			String password = scn.next();
			pstmt3.setString(3, password);
			
			pstmt3.executeUpdate();
			
			// No exception, so commit the updates
			con.commit();
			
			System.out.println("Records stored. DB updated.");
			
		} catch (SQLException e) {
			// Exception occurred, rollback!!
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.err.println("Records not updated.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// Exception occurred, rollback!!
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.err.println("Records not updated.");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// Exception occurred, rollback!!
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.err.println("Records not updated.");
			e.printStackTrace();
		} catch (IOException e) {
			// Exception occurred, rollback!!
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.err.println("Records not updated.");
			e.printStackTrace();
		} finally {
			scn.close();
			
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // end of inner try-catch blocks
		} // end of outer try-catch-finally block
	} // end of main method
} // end of the class
