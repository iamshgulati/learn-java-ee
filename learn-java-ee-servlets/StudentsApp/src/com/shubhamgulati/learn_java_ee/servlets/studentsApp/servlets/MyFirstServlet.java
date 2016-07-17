package com.shubhamgulati.learn_java_ee.servlets.studentsApp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFirstServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		// 1. Java code to generate current date and time
		Date dateRef = new Date();
		String currDate = dateRef.toString();

		// 2. Generate HTML response
		String html = "<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<meta charset=\"ISO-8859-1\">"
				+ "<title>Current Date</title>"
				+ "</head>"
				+ "<body>"
				+ "<h1 align=\"center\">"
				+ "Current Date and Time<br>"
				+ "<font color=\"red\">"
				+ currDate
				+ "</font>"
				+ "</h1>"
				+ "</body>"
				+ "</html>";

		// 3. Send the response to Browser via Web Server
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(html);
	}
}
