<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AudioProfile</title>
</head>
<body>
<h2 align="center"> Create Transcoding Profile</h2>
<form:form action="addprofile.obj?id=${vendorName}" method="get" modelAttribute="profile">
<table align="center" border="1">
<tr>
<td align="center"> </td>
</tr>

<tr>
<td><form:label path="profileName">Profile Display Name</form:label></td>
<td><form:input path="profileName" /></td>
</tr>

<tr>
<td><form:label path="transcoder">Transcoder</form:label></td>
<td><form:select path="transcoder">
<form:option value="ffmpeg">FFMPEG</form:option>
<form:option value="rhozet">Rhozet</form:option>
<form:option value="DigitalRapid">Digital Rapids</form:option>
</form:select></td>
</tr>

<tr>
<td>Vendor:</td>
 <td>   <select name="vendorName">  
       <c:forEach items="${profileList}" var="name">  
           <option value="${name}"><c:out value="${name}"/></option>  
       </c:forEach>  
    </select>  
</td>
</tr>

<tr>
<td><form:label path="profilePath">Profile Path:</form:label></td>
<td><form:input path="profilePath"/></td>
</tr>

<tr>
<td colspan="2"><input type="submit" value="Create Profile" align="middle"></td>
</tr>

</table>

</form:form>
<a href="profilemgmnt.obj">Home Page</a>
</body>

</html>