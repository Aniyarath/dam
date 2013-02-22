<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<link rel="stylesheet" type="text/css"
	href="./resources/css/createservicetemplate.css">
	
	<div id="titleResults" class="resultsLeftInnerDiv">
<form:form action="updateMetadata.obj">
Operation has been performed successfully....
<a href="titlehome.obj">Home</a>
</form:form>
</div>
<script type="text/javascript">
 $(document).ready(function(){
 $('#adminMenuButton').css({'background-color':'#989898','font-weight': 'bold'});
 $('#metadataManagementSubMenuButton').css({'background-color':'#989898','font-weight': 'bold'});
 });
 </script>
