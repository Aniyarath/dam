<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>publishing profile</title>
</head>
<body>
<h2> Create Publishing Profile</h2>
<form:form action="addpublishprofile.obj?id=${vendorName}" method="get" modelAttribute="profile">
<table>
<tr>
<td align="center"> </td>
</tr>

<tr>
<td><form:label path="publish_profile_name">Publish Profile Name</form:label></td>
<td><form:input path="publish_profile_name" /></td>
</tr>



<tr>
<td>Vendor Name:</td>
 <td>   <select name="vendorName">  
       <c:forEach items="${vendorList}" var="name">  
           <option value="${name}"><c:out value="${name}"/></option>  
       </c:forEach>  
    </select>  
</td>
</tr>

<tr>
<td><form:label path="publish_profile_path">Publish Profile Path:</form:label></td>
<td><form:input path="publish_profile_path"/></td>
</tr>

<tr>
<td><input type="submit" value="Create Profile"></td>
</tr>

</table>

</form:form>
<a href="profile.jsp">Home Page</a>
</body>
</html>