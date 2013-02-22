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
<h3>Profiles</h3>
<table border=1 bordercolor="black">
<tr>
<th>profile Name</th>
<th>Profile Path</th>
</tr>
<c:forEach var="profile" items="${profileList}">
<tr>

<td>${profile.publish_profile_name}</td>
<td>${profile.publish_profile_path}</td>

<td> <!-- Actions for the individual item -->
   <a href="<c:url value="deletepublishprofile.obj?id=${profile.publish_profile_id}" />">Delete</a>
    </td>
<td> 
     <a href="<c:url value="updatepublishprofile.obj?id=${profile.publish_profile_id}" />">Update</a> </td> 
</tr>
</c:forEach>

</table>
</c:if>
<a href="profile.jsp">Home Page</a>
</body>
</html>