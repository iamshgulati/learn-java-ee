package com.shubhamgulati.beerAdvisor.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shubhamgulati.beerAdvisor.model.BeerExpert;

public class SelectBeer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SelectBeer() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("Beer Selection Advice<br>");*/
		String color = request.getParameter("color");
		BeerExpert expert = new BeerExpert();
		List<String> beerList = expert.getBrands(color);
		
		/*Iterator<String> i = beerList.iterator();
		while(i.hasNext()) {
			out.print("<br>try: " + i.next());
		}*/
		
		request.setAttribute("styles", beerList);
		RequestDispatcher view = request.getRequestDispatcher("result.jsp");
		view.forward(request, response);
	}
}
