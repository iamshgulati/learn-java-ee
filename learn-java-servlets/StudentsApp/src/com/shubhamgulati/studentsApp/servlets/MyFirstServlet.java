package com.shubhamgulati.studentsApp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyFirstServlet
 */
@WebServlet("/MyFirstServlet")
public class MyFirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyFirstServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. Java code to generate current date and time
		Date dateRef = new Date();
		String currDate = dateRef.toString();
		
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		// 2. Generate HTML Response
		String htmlResponse =""
				+ "<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<meta charset=\"ISO-8859-1\">"
				+ "<title>index</title>"
				+ "</head>"
				+ "<body>"
				+ "<h1 align=\"center\">"
				+ "Current Date and Time<br>"
				+ "<font color=\"red\">"
				+ currDate
				+ "</font>"
				+ "</h1>"
				+ "First Name: "
				+ firstname
				+ "<br>"
				+ "Last Name: "
				+ lastname
				+ "</body>"
				+ "</html>";
		
		// 3. Send the response to Browser via Web Server
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(htmlResponse);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
