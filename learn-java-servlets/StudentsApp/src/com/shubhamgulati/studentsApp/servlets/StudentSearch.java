package com.shubhamgulati.studentsApp.servlets;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shubhamgulati.studentsApp.common.DBProperties;

/**
 * Servlet implementation class StudentSearch
 */
@WebServlet("/StudentSearch")
public class StudentSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentSearch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// 1. Get form data
		int form_reg_no = Integer.parseInt(request.getParameter("reg_no"));

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

			String query = "SELECT * FROM students_info AS si, guardian_info AS gi, students_other_info AS soi WHERE si.reg_no=gi.reg_no AND si.reg_no=soi.reg_no AND si.reg_no=?";
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, form_reg_no);

			rs = pstmt.executeQuery();

			// 2.4 Process the result

			if(rs.next()) {
				int reg_no = rs.getInt("reg_no");
				String first_name = rs.getString("first_name");
				String middle_name = rs.getString("middle_name");
				String last_name = rs.getString("last_name");
				String guardian_first_name = rs.getString("guardian_first_name");
				String guardian_middle_name = rs.getString("guardian_middle_name");
				String guardian_last_name = rs.getString("guardian_last_name");

				// 2. Send the response to Browser via Web Server
				out.print("<!DOCTYPE html>");
				out.print("<html>");
				out.print("<head>");
				out.print("<meta charset=\"ISO-8859-1\">");
				out.print("<title>Search Student</title>");
				out.print("</head>");
				out.print("<body>");
				out.print("<table border=\"1\" style=\"border: 1px solid black; border-collapse: collapse;\">");
				out.print("<caption>Student Details</caption>");
				out.print("<tr>");
				out.print("<th style=\"padding: 8px\">Registration Number</th>");
				out.print("<th style=\"padding: 8px\">First Name</th>");
				out.print("<th style=\"padding: 8px\">Middle Name</th>");
				out.print("<th style=\"padding: 8px\">Last Name</th>");
				out.print("<th style=\"padding: 8px\">Guardian First Name</th>");
				out.print("<th style=\"padding: 8px\">Guardian Middle Name</th>");
				out.print("<th style=\"padding: 8px\">Guardian Last Name</th>");
				out.print("</tr>");
				out.print("<tr>");
				out.print("<td style=\"padding: 8px\">" + reg_no + "</td>");
				out.print("<td style=\"padding: 8px\">" + first_name + "</td>");
				out.print("<td style=\"padding: 8px\">" + middle_name + "</td>");
				out.print("<td style=\"padding: 8px\">" + last_name + "</td>");
				out.print("<td style=\"padding: 8px\">" + guardian_first_name + "</td>");
				out.print("<td style=\"padding: 8px\">" + guardian_middle_name + "</td>");
				out.print("<td style=\"padding: 8px\">" + guardian_last_name + "</td>");
				out.print("</tr>");
				out.print("</table>");
				out.print("</body>");
				out.print("</html>");
			} else {
				out.print("<!DOCTYPE html>");
				out.print("<html>");
				out.print("<head>");
				out.print("<meta charset=\"ISO-8859-1\">");
				out.print("<title>Search Student</title>");
				out.print("</head>");
				out.print("<body>");
				out.print("<table border=\"1\" style=\"border: 1px solid black; border-collapse: collapse;\">");
				out.print("<caption>Student Details</caption>");
				out.print("<tr><td style=\"color: red; padding: 8px;\">Registration number not found</td></tr>");
				out.print("</body>");
				out.print("</html>");
			} // end of if-else block
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
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} // end of inner try-catch blocks
		} // end of outer try-catch-finally
	} // end of doGet()

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
