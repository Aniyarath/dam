<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="./resources/css/createservicetemplate.css">
<title>Start Page</title>
</head>
<body>
<span style="color:red">${errorMessage }</span>
<form:form action="search.obj?id=${vendorName}" method="get" modelAttribute="profile" id="title_form">
<div id="titleResults" class="resultsLeftInnerDiv">

<table id="title_results_table" class="tableborder1" width="100%" style="padding:5px;" cellspacing="0" cellpadding="0" border="0">
   <tbody>
    <tr>
		<td width="16%" valign="middle" align="left" class="overlayHeader" height="10%">Select the vendor name:</td>
		
		  <td width="16%" valign="middle" align="left" class="overlayHeader" height="10%"> <select name="vendorName">  
               <c:forEach items="${vendorList}" var="name">  
               <option value="${name}"><c:out value="${name}"/></option>  
               </c:forEach>  													
           </select></td>
     
     <tr> 
    
      <td colspan="2">
       <c:if test="${not empty notitle}">
	 <div id="resultsmessage" class="success">${notitle}</div>
     </c:if>
      
       <%-- <%@include file="/WEB-INF/jsp/profiles.jsp"%></td> --%>
     </tr>
       
   <tr>	 <td width="16%" valign="middle" align="center" class="overlayHeader" colspan="2" height="100">
                <a href="#" onclick="submitTitleForm();"><img src="resources/images/searchButton.png" width="70" height="30"/></a></td> </tr>

  </tbody>
</table>
</div>
</form:form>


<%-- <%@include file="/WEB-INF/jsp/profiles.jsp"%> --%>
</body>

<style>
table.tableborder1 td {
    border-bottom: 1px solid #E8E8E8;
    border-left: 1px solid #E8E8E8;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
$('#publishMenuButton').css({'background-color':'#989898','font-weight': 'bold'});
});


function submitTitleForm(){
	document.getElementById("title_form").submit();	
}


</script>
</html>