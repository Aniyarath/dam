<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:if test="${profileList!=null}">
<h3 align="center">Profiles</h3>
<table border=1 bordercolor="black" width="100%">
<tr>
<th>Profile Name</th>
<th>Transcoder</th>
<th>Profile Path</th>
</tr>
<c:forEach var="profile" items="${profileList}">
<tr>

<td>${profile.profileName}</td>
<td>${profile.transcoder}</td>
<td>${profile.profilePath }</td>
<td> <!-- Actions for the individual item -->
   <a href="<c:url value="deleteprofile.obj?id=${profile.profileId}" />">Delete</a>
    </td>
<td> 
     <a href="<c:url value="updateprofile.obj?id=${profile.profileId}" />">Update</a> </td>
</tr>
</c:forEach>

</table>
</c:if>
<a href="profile.jsp">Home Page</a>
</body>
</html>