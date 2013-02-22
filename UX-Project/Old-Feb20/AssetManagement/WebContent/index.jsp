<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Digital Asset Management</title>
</head>
<body>
<!-- <form action="home.obj" method="get">
Welcome!!!!
<input type="submit" value="Click"/>
</form> -->

<a href="home">home</a>
</body>
</html> --%>

<%   
    String redirectURL = "./login";
    response.sendRedirect(redirectURL);
%>