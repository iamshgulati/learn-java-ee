<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result</title>
</head>
<body>
	<h1 align="center">Beer Recommendations JSP</h1>
	<p>
		<%
			List<String> styles = (List) request.getAttribute("styles");
			Iterator<String> i = styles.iterator();
			while (i.hasNext()) {
				out.print("<br>try: " + i.next());
			}
		%>
	</p>
</body>
</html>