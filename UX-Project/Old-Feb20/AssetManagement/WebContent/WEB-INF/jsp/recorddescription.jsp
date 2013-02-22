
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html>
<title>Record Description</title>
<link rel="stylesheet" type="text/css"
	href="./resources/css/style.css">
</head>
<body>
<span style="color:red">${errorMessage }</span>
<div id="titleResults" class="resultsLeftInnerDiv">
<h3 align="center">Description of the Record</h3>
<br>
<table class="recordDescriptionTable" align="center" border="1" style="border-collapse:collapse;" >
<c:forEach var="recordList" items="${recordList}">
<tr>
<th> FileName</th>
 <th> FileType</th>
 </tr>
 <tr>
<td>
 ${recordList.media_package_file_name}</td>
 <td>                 
 ${recordList.file_type}</td>
 </tr>
</c:forEach>
</table>
</div>
</body>
</html>

 
  
  
  