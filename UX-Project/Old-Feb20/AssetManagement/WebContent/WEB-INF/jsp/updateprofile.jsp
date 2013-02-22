<%@include file="/WEB-INF/jsp/include.jsp"%>
<div id="tabs_container" class="tabsContainer">
<span style="color:green">${successMsg}</span>
<span style="color:red">${errorMessage}</span>
<form:form action="profileupdate.obj" method="post"
	modelAttribute="updateprofile" id="title_form">
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
											<td width="25%" height="32" class="titleFormLabelStyle" >Profile Display Name</td>
											<td width="3%" class="titleFormColonStyle"><span style="font-size:11px; color:#ed1c24">*</span>: </td>
											<td width="40%" height="32" class="titleFormFieldStyle"><form:input path="profileName" size="32"/></td>
											<td width="40%" height="32" class="titleFormFieldStyle"><span id="profilenameErrmsg" style="font-size:12px; color:#ed1c24"></span></td>
										</tr>

										<tr>
											<td width="25%" height="32" class="titleFormLabelStyle" >Transcoder</td>
											<td width="3%" class="titleFormColonStyle" ><span style="font-size:11px; color:#ed1c24">*</span>:</td>
											<td width="40%" height="32" class="titleFormFieldStyle"><form:select path="transcoder">
													<form:option value="ffmpeg">FFMPEG</form:option>
													<form:option value="rhozet">Rhozet</form:option>
													<form:option value="DigitalRapid">Digital Rapids</form:option>
												</form:select></td>
										</tr>

										<tr>
											<td width="25%" height="32" class="titleFormLabelStyle" >Profile Path</td>
											<td width="3%" class="titleFormColonStyle" ><span style="font-size:11px; color:#ed1c24">*</span>:</td>
											<td width="40%" height="32" class="titleFormFieldStyle"><form:input path="profilePath" size="32" /></td>
											<td width="40%" height="32" class="titleFormFieldStyle"><span id="profilepathErrmsg" style="font-size:12px; color:#ed1c24"></span></td>
										</tr>

										<tr>
											<td><form:hidden path="profileId" /></td>
										</tr>
										<tr>
											<td><form:hidden path="vendorid" /></td>
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

<script type="text/javascript">
$(document).ready(function(){
	 $('#adminMenuButton').css({'background-color':'#989898','font-weight': 'bold'});
	 $('#transcodeManagementSubMenuButton').css({'background-color':'#989898','font-weight': 'bold'});
	 });
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

