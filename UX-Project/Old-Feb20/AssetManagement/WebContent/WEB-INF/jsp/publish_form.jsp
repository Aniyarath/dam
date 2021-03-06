<%@include file="/WEB-INF/jsp/include.jsp"%>
<div id="tabs_container" class="tabsContainer">
	<div id="tabs">
		<ul>
			<li style="width:120px; float:left;"><a href="#tabs-1">Create Profile</a></li>
			<li style="width:120px; float:left;"><a href="#tabs-2">Display Profile</a></li>
		</ul>
			
		
		
		<div id="tabs-1">	
		<form:form action="addpublishprofile.obj?id=${vendorName}" method="get" modelAttribute="profile" id="title_form">		
			
				<form:errors path="*" cssClass="error"  element="div"/>
				<span class="msg" style="color:green">${successMsg}</span>
				<span class="msg" style="color:red">${errorMessage}</span>
				  <div id="titleForm" class="leftInnerDivStyle">
					<div class="subItemDetails">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">								  
					  <tr>
						<td align="left" valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
						  <tr>
							<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
							  <tr>
							  
							  
								<td width="25%" height="32" class="titleFormLabelStyle" >Publish Profile Name</td>
								<td width="3%" class="titleFormColonStyle"><span style="font-size:11px; color:#ed1c24">*</span>: </td>
								<td width="40%" height="32" class="titleFormFieldStyle" >
								<form:input path="publish_profile_name" size="32" id="profileName"/>
								</td>
								<td width="40%" height="32" class="titleFormFieldStyle"><span id="profilenameErrmsg" style="font-size:12px; color:#ed1c24"></span></td>
								
							  </tr>
							 
							  

							  
							  <tr>
								<td width="25%" height="32" class="titleFormLabelStyle" >Vendor</td>
								<td width="3%" class="titleFormColonStyle" ><span style="font-size:11px; color:#ed1c24">*</span>:</td>
								<td width="40%" height="32" class="titleFormFieldStyle" >
									
									<form:select id="aspectRatioSelect" path="" name="vendorName" style="max-width:210px;height:18px;">
									<form:option value="select" label="Select" />	
								<c:forEach items="${vendorList}" var="name">  
                                     <option value="${name}"><c:out value="${name}"/></option>  
                                  </c:forEach>  
						</form:select>
									
									
								</td>
								<td width="40%" height="32" class="titleFormFieldStyle"><span id="vendorErrmsg" style="font-size:12px; color:#ed1c24"></span></td>
							  </tr>	
							  

							  
							  					  												  							 							  
							
							  <tr>
								<td width="25%" height="32" class="titleFormLabelStyle" >Publish Profile Path:</td>
								<td width="3%" class="titleFormColonStyle" ><span style="font-size:11px; color:#ed1c24">*</span>:</td>
								<td width="40%" height="32" class="titleFormFieldStyle" >
								<form:input path="publish_profile_path" size="32" id="profilePath"/>
								</td>
								<td width="40%" height="32" class="titleFormFieldStyle"><span id="profilepathErrmsg" style="font-size:12px; color:#ed1c24"></span></td>
							  </tr>						  						  						 
							</table>
						   </td>
						  </tr>
						</table>
						</td>
					  </tr>
					</table>
					</div>
				
			</form:form>
			
			
		
			
			<div align="center">
				<table>
					<tr>	
					<c:choose>
						<c:when test="${not empty vendorName.profileName}">
						<td style="padding: 5px; text-align:center" colspan="6" >
							<a href="#" onclick="submitTitleForm();">
							<img src="resources/images/Save.png"" width="70" height="30"/></a></td>
						<td style="padding: 5px; text-align:center" colspan="6" ><img src="resources/images/Cancel.png" width="70" height="30"/></a></td>	 
						
						</c:when>
						<c:otherwise>
						<td style="padding: 5px; text-align:center" colspan="6" ><a href="#" onclick="submitTitleForm();"><img src="resources/images/Create.png" width="70" height="30"/></a></td>
						<td style="padding: 5px; text-align:center" colspan="6" ><img src="resources/images/Cancel.png" width="70" height="30"/></a></td>	 
						</c:otherwise>
					</c:choose>                
					</tr>	
				</table>
			</div>
			</div>
		</div>
		
		<div id="tabs-2">
			<div style="min-height:600px">
				<span class="msg" style="color:green">${deletesuccessMsg}</span>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="left" valign="top">
				<table id="title_results_table">
			
<!--<table class="tableborder1" width="100%" style="padding:5px;" cellspacing="0" cellpadding="0" border="0">
		<tbody>
		<tr>
		
		<td width="16%" valign="middle" align="left" class="overlayHeader">Profile Name</td>
		<td width="16%" valign="middle" align="left" class="overlayHeader">Profile Path</td>
		<td width="16%" valign="middle" align="left" class="overlayHeader">Action</td>
		<td width="16%" valign="middle" align="left" class="overlayHeader">Edit</td>
		
		
		
		</tr>
		
		--><c:forEach var="profile" items="${profileList}">
		<tr class="commonTrailersClass">

			<td width="16%" valign="middle" align="left" class="overlayRow">${profile.publish_profile_name}</td>
			
			<td width="16%" valign="middle" align="left" class="overlayRow">${profile.publish_profile_path}</td>
			<td width="16%" valign="middle" align="left" class="overlayRow">
				<a href="<c:url value="deletepublishprofile.obj?id=${profile.publish_profile_id}" />" onclick="return confirm('Are you sure to delete this profile?');"><img src="resources/images/Delete_button.png" width="20" height="20" title="Delete" /></a>
			<!--<td width="16%" valign="middle" align="left" class="overlayRow">
				--><a href="<c:url value="updatepublishprofile.obj?id=${profile.publish_profile_id}" />"><img src="resources/images/EditIcon.jpg" width="20" height="20" title="Edit"/></a> </td>
		</tr>
			</c:forEach>
		
		
		
		
		
	</table></td>
		</tr>
		
		
	</table>

			</div>
		</div>
		
		
		<div id="tabs-3">
			
		</div>
	</div>
</div>

<script>
function submitTitleForm(){
	
	var profileName=document.getElementById("profileName").value;
	var vendor=document.getElementById("aspectRatioSelect").value;
	var profilePath=document.getElementById("profilePath").value;
	
	if(validateProfileName(profileName)){
		if(validateVendor(vendor)){
				if(validateProfilePath(profilePath)){
					document.getElementById("title_form").submit();	
				}
			
		}
		
	}
	else{
		return false;
	}
}

function validateProfileName(profileName){
	
	if(profileName==''){
		document.getElementById('profilenameErrmsg').innerHTML = 'Enter Profile name';
		return false;
	}
	else{
		document.getElementById('profilenameErrmsg').innerHTML = '';
		return true;
	}
}

function validateVendor(vendor){
		
	if(vendor=='select'){
		document.getElementById('vendorErrmsg').innerHTML = 'Select Vendor';
		return false;
	}
	else{
		document.getElementById('vendorErrmsg').innerHTML = '';
		return true;
	}
}

function validateProfilePath(profilePath){
	
	if(profilePath==''){
		document.getElementById('profilepathErrmsg').innerHTML = 'Enter Profile path';
		return false;
	}
	else{
		document.getElementById('profilepathErrmsg').innerHTML ='';
		return true;
	}
}

jQuery(document).ready(function() {


	var tabId=2;
	if(!('${tabId}'==''||'${tabId}'==null)){
		tabId='${tabId}';
	}
	jQuery( "#tabs" ).tabs({
		selected:tabId
	});
	 $('#publish_profile_name').val('');
		$('#publish_profile_path').val('');
		$("#aspectRatioSelect").val($("#countrySelect option:first").val());
		$("#tabs ul li a").click(function(){
			$("span.msg").remove();
		
		});



	
	$('#adminMenuButton').css({'background-color':'#989898','font-weight': 'bold'});
	 $('#publishManagementSubMenuButton').css({'background-color':'#989898','font-weight': 'bold'});
	jQuery( "#tabs" ).tabs();
	
$("#clickToExpand").click(function () {
	if ($("#divnonFunctionalTitleItems").is(":hidden")) {
		$("#divnonFunctionalTitleItems").slideDown("slow");
		var imgsrc = "resources/images/bar_up.png";		
	} else {
		$("#divnonFunctionalTitleItems").slideUp("slow");
		var imgsrc = "resources/images/bar.png";		
	}
	$(this).attr('src', imgsrc);
});

$("#title_results_table").flexigrid({
	dataType: 'json',
	colModel : [				
		
		
		{display: 'Profile Name ', name : 'statusName', width : 250, sortable : true, align: 'left'} ,				
		{display: 'Profile Path', name : 'metadata_ingest_status', width : 300, sortable : true, align: 'left'},
		{display: 'Action', name : 'metadata_ingest_status', width : 130, sortable : true, align: 'center'}
		
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


var release_date = $("#releaseDate").val();
$("#releaseDate").datepicker({
			showOn: "button",
			buttonImage: "resources/images/calendar.gif",
			buttonImageOnly: true,			
	}); 
	$("#releaseDate").datepicker("option", "showAnim", "bounce");
	$("#releaseDate").val(release_date);
	if($("#releaseDate").val() == null || $("#releaseDate").val() == ''){
		$("#releaseDate").datepicker("setDate", new Date());
	}
	
});


/* $(document).ready(function()
        {
        $("#delprofile").click(function()
                {
                prompt("Are you sure you want to delete?",{buttons:
{Ok:true,Cancel:false}});
                }
        );
        }
);
 */


</script>
<style>
table.tableborder1 td {
    border-bottom: 1px solid #E8E8E8;
    border-left: 1px solid #E8E8E8;
}
</style>