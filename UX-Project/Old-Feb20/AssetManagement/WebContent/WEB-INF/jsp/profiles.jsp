<%@include file="/WEB-INF/jsp/include.jsp"%>
<link rel="stylesheet" type="text/css"
	href="./resources/css/createservicetemplate.css">
	
	<div id="titleResults" class="resultsLeftInnerDiv">
	
	<span style="color:red">${errorMessage }</span>
	<form:form action="./searchallpackages" method="get"  id="title_form">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="left" valign="top">
				<table id="title_results_table">
<c:choose>


<c:when test="${profiles!=null}">
<!--<h3>Profiles</h3>
<table class="tableborder1" width="100%" style="padding:5px;" cellspacing="0" cellpadding="0" border="0">
		<tbody>
		<tr>
		 <td width="16%" valign="middle" align="left" class="overlayHeader">Profile Id</td> 
		<td width="16%" valign="middle" align="left" class="overlayHeader">Profile Name</td>
		<td width="16%" valign="middle" align="left" class="overlayHeader">Profile Path</td>
			<td width="16%" valign="middle" align="left" class="overlayHeader">Action</td>
		</tr>


--><c:forEach var="profile" items="${profiles}">
	
		<tr class="commonTrailersClass">

			<%-- <td width="16%" valign="middle" align="left" class="overlayRow">${profile.publish_profile_id}</td> --%>
			<td width="16%" valign="middle" align="left" class="overlayRow">${profile.publish_profile_name}</td>
			<td width="16%" valign="middle" align="left" class="overlayRow">${profile.publish_profile_path}</td>
			<td width="16%" valign="middle" align="left" class="overlayRow">
			<a href="<c:url value="select.obj?id=${profile.publish_profile_name}" />">Select</a> </td>
			
		</tr>
		</c:forEach> 

</c:when>
</c:choose>
</table></td>
		</tr>
		<tr>
		<td style="padding: 5px; text-align:center" colspan="6" ><a href="#" onclick="submitTitleForm();"><img src="resources/images/Back.png" width="70" height="30" /></a></td>
		</tr>
	</table>
	</form:form>
</div>
<!--<a href="goto.obj">Back to Search</a> 



-->

<script type="text/javascript">


function submitTitleForm(){
	document.getElementById("title_form").submit();	
}

 jQuery(document).ready(function($) {
	 $('#publishMenuButton').css({'background-color':'#989898','font-weight': 'bold'});
		$("#title_results_table").flexigrid({
			dataType: 'json',
			colModel : [				
				
				
				{display: 'Profile Name ', name : 'statusName', width : 200, sortable : true, align: 'center'} ,				
				{display: 'Profile Path', name : 'metadata_ingest_status', width : 300, sortable : true, align: 'center'},
				{display: 'Action', name : 'metadata_ingest_status', width : 180, sortable : true, align: 'center'}
				
			],
			title: "Publishing Profiles",
			//sortname: "language",
			sortorder: "asc",
			showTableToggleBtn: true,
			width: 725,
			height: 'auto',
			searchitems : [
				{display: 'media_package_id', name : 'iso'},
				{display: 'media_package_name', name : 'name', isdefault: true}
			],								
		});
	
	
 }
	); 
	
</script>
