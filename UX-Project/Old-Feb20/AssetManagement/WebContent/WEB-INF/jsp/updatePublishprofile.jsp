<%@include file="/WEB-INF/jsp/include.jsp"%>
<div id="tabs_container" class="tabsContainer">
<span style="color:green">${successMsg}</span>
<span style="color:red">${errorMessage}</span>
<form:form action="publishprofileupdate.obj" method="post" modelAttribute="updatePublishprofile" id="title_form">
	<div id="titleForm" class="leftInnerDivStyle">
		<div class="subItemDetails">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="left" valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-top:5px;">
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td align="center"></td>
										</tr>

										<tr >
											<td width="25%" height="32" class="titleFormLabelStyle" >Profile  Name</td>
											<td width="3%" class="titleFormColonStyle"><span style="font-size:11px; color:#ed1c24">*</span>: </td>
											<td width="40%" height="32" class="titleFormFieldStyle">
											   <form:input path="publish_profile_name" size="32" id="profileName"/>
											</td>
											<td width="40%" height="32" class="titleFormFieldStyle"><span id="profilenameErrmsg" style="font-size:12px; color:#ed1c24"></span></td>
										</tr>

									

										<tr>
											<td width="25%" height="32" class="titleFormLabelStyle" >Profile Path</td>
											<td width="3%" class="titleFormColonStyle" ><span style="font-size:11px; color:#ed1c24">*</span>:</td>
											<td width="40%" height="32" class="titleFormFieldStyle">
											  <form:input path="publish_profile_path" size="32" id="profilePath"/>
											</td>
											<td width="40%" height="32" class="titleFormFieldStyle"><span id="profilepathErrmsg" style="font-size:12px; color:#ed1c24"></span></td>
										</tr>

										<tr>
											<td><form:hidden path="publish_profile_id" /></td>
										</tr>
										<tr>
											<td><form:hidden path="vendor_id" /></td>
										</tr>

										<tr>
											<td height="32" colspan="4" align="center" style="padding-top:10px;"><a href="#" onclick="submitTitleForm();"><img src="resources/images/updateProfile.png" width="70" height="30"/></a>
											</td>
										</tr>

									</table></td>
							</tr>
						</table></td>
				</tr>
			</table>
		</div>
		</div>
</form:form>
</div>

<script>
function submitTitleForm(){
	var profileName=document.getElementById("profileName").value;
	var profilePath=document.getElementById("profilePath").value;
	
	if(validateProfileName(profileName)){
		
		if(validateProfilePath(profilePath)){
				document.getElementById("title_form").submit();	
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

</script>
