package com.shubhamgulati.studentsApp.servlets;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shubhamgulati.studentsApp.common.DBProperties;

/**
 * Servlet implementation class StudentProfileManagement
 */
@WebServlet("/StudentProfileManagement")
public class StudentProfileManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentProfileManagement() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection con = null;
		CallableStatement cstmt = null;
		String message = null;

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// 1. Get form data
		int reg_no = Integer.parseInt(request.getParameter("reg_no"));
		String first_name = request.getParameter("first_name");
		String middle_name = request.getParameter("middle_name");
		String last_name = request.getParameter("last_name");
		String guardian_first_name = request.getParameter("guardian_first_name");
		String guardian_middle_name = request.getParameter("guardian_middle_name");
		String guardian_last_name = request.getParameter("guardian_last_name");
		String password = request.getParameter("password");
		String is_admin = request.getParameter("is_admin");
		String action = request.getParameter("action");

		// 2. Process the form data
		try {
			// 2.1 Load the driver
			Class.forName(DBProperties.DRIVER_CLASS_NAME);

			// 2.2 Get the connection
			FileReader fReader = new FileReader(DBProperties.DB_PROPERTIES_FILE_PATH);
			Properties dbProperties = new Properties();
			dbProperties.load(fReader);
			String dbUrl = DBProperties.DB_CONNECTION_URL;

			con = DriverManager.getConnection(dbUrl, dbProperties);

			// 2.3 Issue SQL queries via Connection

			String query = "call studentProfileManagement(?,?,?,?,?,?,?,?,?,?)";
			cstmt = con.prepareCall(query);

			cstmt.setInt(1, reg_no);
			cstmt.setString(2, first_name);
			cstmt.setString(3, middle_name);
			cstmt.setString(4, last_name);
			cstmt.setString(5, guardian_first_name);
			cstmt.setString(6, guardian_middle_name);
			cstmt.setString(7, guardian_last_name);
			cstmt.setString(8, is_admin);
			cstmt.setString(9, password);
			cstmt.setString(10, action);

			int count = cstmt.executeUpdate();

			// 2.4 Process the result

			if(count >= 1) {
				message = "Successfully created profile with registration number " + reg_no;
			} else {
				message = "Unable to create profile";
			}

			// 2. Send the response to Browser via Web Server
			out.print("<!DOCTYPE html>");
			out.print("<html>");
			out.print("<head>");
			out.print("<meta charset=\"ISO-8859-1\">");
			out.print("<title>Create Profile</title>");
			out.print("</head>");
			out.print("<body>");
			out.print("<h3>");
			out.print(message);
			out.print("</h3>");
			out.print("Student Name: " + first_name + " " + middle_name + " " + last_name);
			out.print("<br>");
			out.print("Guardian Name: " + guardian_first_name + " " + guardian_middle_name + " " + guardian_last_name);
			out.print("<br>");
			out.print("Is this administrator? " + is_admin);
			out.print("<br>");
			out.print("Password " + password);
			out.print("</body>");
			out.print("</html>");

		} catch (ClassNotFoundException e) {
			out.print("Internal server error occured");
			e.printStackTrace();
		} catch (SQLException e) {
			out.print("Internal server error occured");
			e.printStackTrace();
		} finally {

			// 2.5 Close all JDBC Objects
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (cstmt != null) {
					cstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} // end of inner try-catch blocks
		} // end of outer try-catch-finally
	} // end of doPost()
} // end of class
