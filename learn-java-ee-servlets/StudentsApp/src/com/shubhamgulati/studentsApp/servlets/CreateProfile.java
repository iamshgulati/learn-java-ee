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
 * Servlet implementation class CreateProfile
 */
@WebServlet("/CreateProfile")
public class CreateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con = null;
	CallableStatement cstmt = null;
	String message = null;
	StringBuilder htmlResponse = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProfile() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. Get form data
		int reg_no = Integer.parseInt(request.getParameter("reg_no"));
		String first_name = request.getParameter("first_name");
		String middle_name = request.getParameter("first_name");
		String last_name = request.getParameter("first_name");
		String guardian_first_name = request.getParameter("guardian_first_name");
		String guardian_middle_name = request.getParameter("guardian_middle_name");
		String guardian_last_name = request.getParameter("guardian_last_name");
		String password = request.getParameter("password");
		String is_admin = request.getParameter("is_admin");
		
		// Process the form data
		
		try {
			// 1.1 Load the driver
			Class.forName(DBProperties.DRIVER_CLASS_NAME);
			
			// 1.2 Get the connection
			FileReader fReader = new FileReader(DBProperties.DB_PROPERTIES_FILE_PATH);
			Properties dbProperties = new Properties();
			dbProperties.load(fReader);
			String dbUrl = DBProperties.DB_CONNECTION_URL;
			
			con = DriverManager.getConnection(dbUrl, dbProperties);
			
			// 1.3 Issue SQL queries via Connection
			
			String query = "call studentUpSert2(?,?,?,?,?,?,?,?,?)";
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
			
			int count = cstmt.executeUpdate();
			
			// 1.4 Process the result
			
			if(count >= 1) {
				message = "Successfully created profile with registration number " + reg_no;
			} else {
				message = "Unable to create profile";
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 1.5 Close all JDBC Objects
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
		
		// 2. Generate the HTML response
		htmlResponse = new StringBuilder();
		htmlResponse.append("<!DOCTYPE html>");
		htmlResponse.append("<html>");
		htmlResponse.append("<head>");
		htmlResponse.append("<meta charset=\"ISO-8859-1\">");
		htmlResponse.append("<title>Create Profile</title>");
		htmlResponse.append("</head>");
		htmlResponse.append("<body>");
		htmlResponse.append("<h3>");
		htmlResponse.append(message);
		htmlResponse.append("</h3>");
		htmlResponse.append("</body>");
		htmlResponse.append("</html>");

		// 3. Send the response to Browser via Web Server
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(htmlResponse);

	} // end of doPost()
} // end of class
