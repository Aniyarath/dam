<%@include file="/WEB-INF/jsp/include.jsp"%>
<div id="tabs_container" class="tabsContainer">
	<div id="tabs">
		<ul>
			<li style="width: 120px; float: left;"><a href="#tabs-1">Create Attribute</a>
			</li>
			<li style="width: 120px; float: left;"><a href="#tabs-2">Add Attribute</a>
			</li>
		</ul>
		
		<div id="tabs-1">
			<div style="min-height: 600px" align="center">
			<span style="color: green" class="msg">${createSuccessMessage}</span>
			<span style="color:red " class="msg">${errorMessage}</span>
				 <form:form method="post" 
					modelAttribute="masterMetadata" id="title_form" action="addAttribute">
					<form:errors path="*" cssClass="error" element="div" />
					<div id="titleForm" class="leftInnerDivStyle">
						<div class="subItemDetails">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td align="left" valign="top">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td>
													<table width="100%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td width="25%" height="32" class="titleFormLabelStyle">
															AttributeName
															</td>
															<td width="3%" class="titleFormColonStyle"><span
																style="font-size: 11px; color: #ed1c24">*</span>:</td>
															<td width="40%" height="32" class="titleFormFieldStyle">
															  <form:input path="master_metadata_name" size="32" id="attributeName"/>
															</td>
															<td width="40%" height="32" class="titleFormFieldStyle"><span id="attrnameErrmsg" style="font-size:12px; color:#ed1c24"></span></td>
														</tr>
														<tr>
															<td width="25%" height="32" class="titleFormLabelStyle">AttributeType</td>
															<td width="3%" class="titleFormColonStyle"><span
																style="font-size: 11px; color: #ed1c24">*</span>:</td>
															<td width="40%" height="32" class="titleFormFieldStyle">
															<form:select path="metadata_attr_type_id" onchange="addValues()" id="typeId">
																	<option value="0">Select</option>
																	<c:forEach items="${metadataList}" var="name">

																		<option value="${name.metadata_attr_type_id}">
																			<c:out value="${name.metadata_attr_type}" />
																		</option>
																	</c:forEach>
															</form:select>
															</td>
															<td width="40%" height="32" class="titleFormFieldStyle"><span id="attrtypeErrmsg" style="font-size:12px; color:#ed1c24"></span></td>
														</tr>
														<tr>
															<td width="25%" height="32" class="titleFormLabelStyle">
														Values</td>
															<td width="3%" class="titleFormColonStyle">:</td>
															<td width="40%" height="32" class="titleFormFieldStyle"><form:input
																	path="metadataValues" id="typevalue" size="32" title="Enter as comma separated values" /></td>
																	
																	<td width="40%" height="32" class="titleFormFieldStyle"><span id="attrvalueErrmsg" style="font-size:12px; color:#ed1c24"></span></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
						<div align="center">
							<table>
								<tr>
									
									<td><a href="#" onclick="submitTitleForm();return false;"><input type="image" src="resources/images/Submit.png" /></a>
									</td>
								</tr>
							</table>
						</div>
					</div>					
				</form:form>
 
			</div>
		</div>
		
		<div id="tabs-2">
		<form:form method="post" 
					modelAttribute="vendor" id="get_vendor_form" >
					<form:errors path="*" cssClass="error" element="div" />
					<div id="titleForm" class="leftInnerDivStyle">
						<div class="subItemDetails">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td align="left" valign="top">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td>
													<table  border="0" cellspacing="0"
														cellpadding="0" align="center">
														<%-- <tr>
															<td width="25%" height="32" class="titleFormLabelStyle">
															<form:label path="master_metadata_name">AttributeName</form:label>
															</td>
															<td width="3%" class="titleFormColonStyle"><span
																style="font-size: 11px; color: #ed1c24">*</span>:</td>
															<td width="72%" height="32" class="titleFormFieldStyle"><form:input
																	path="master_metadata_name" size="32" /></td>
														</tr> --%>
														<tr>
															<td width="25%" height="32" class="titleFormLabelStyle">Vendor</td>
															<td width="3%" class="titleFormColonStyle"><span
																style="font-size: 11px; color: #ed1c24">*</span>:</td>
															<td width="72%" height="32" class="titleFormFieldStyle">
															<form:select path="vendor_id" id="vendorName" >
															        <option value="select">Select</option>
																	<c:forEach items="${vendorList}" var="vendor">

																		<option value="${vendor.vendor_id}">
																			<c:out value="${vendor.vendor_name}" />
																		</option>
																	</c:forEach>
															</form:select>
															</td>
															<!--<td><input type="image" src="resources/images/Submit.png" /></td>
														--></tr>
														<%-- <tr>
															<td width="25%" height="32" class="titleFormLabelStyle">
															<form:label path="metadataValues">Values</form:label></td>
															<td width="3%" class="titleFormColonStyle"></td>
															<td width="72%" height="32" class="titleFormFieldStyle"><form:input
																	path="metadataValues" id="typevalue" size="32" /></td>
														</tr> --%>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
						<div align="center">
							<table>
								<tr>
									<td>
									</td>
								</tr>
								
							</table>
						</div>
						<div align="center">
						<table id="myTable">
						   <%-- <c:if test="${masterMetadataList!=null}">
                           <c:forEach items="${masterMetadataList}" var="master">
                           <tr><td>${master.master_metadata_name }</td></tr>
                           
                           </c:forEach>			
                           <tr><td><a href="useraddattribute.obj"><img src="resources/images/Btn_AddReceiver1.JPG" width="70" height="25"/></a></td></tr>
				
				           </c:if>	 --%>
						</table>
						</div>
					</div>					
				</form:form>
		</div>


		
	</div>
</div>

<script>



function submitTitleForm(){
	
	var attributeName=document.getElementById("attributeName").value;
	
	var attributeType=document.getElementById("typeId").value;

	var attributeValue=document.getElementById("typevalue").value;
	
	var result=validateAttributeName(attributeName);
	if(result){
		if(validateAttributeType(attributeType)){			
            if(attributeType>1){
            	if(validateAttributeValues(attributeValue)){
                 
			      document.getElementById("title_form").submit();	
			      return true;
            	}
            } 
            else if(attributeType==1){
            	document.getElementById("title_form").submit();	
			    return true; 
            }  
               	   
		}else{
			return false;
			}
	}
	else{
		return false;
	} 
}

function addValues(){
	
	var type=document.getElementById("typeId").value;
	
	if(type=='1'){
		document.getElementById("typevalue").disabled=true;
		document.getElementById("typevalue").title=" ";
	}
	else{
		document.getElementById("typevalue").disabled=false;
		document.getElementById("typevalue").title="Enter as comma separated values";
	}
}




function validateAttributeName(attributeName){
	
	if(attributeName==''){
		document.getElementById('attrnameErrmsg').innerHTML = 'Enter Attribute name';
		return false;
	}
	else{
		document.getElementById('attrnameErrmsg').innerHTML = '';
		return true;
	}
}

function validateAttributeType(attributeType){
	
	if(attributeType=='0'){
		document.getElementById('attrtypeErrmsg').innerHTML = 'Select Attribute Type';
		
		return false;
	}
	else{
		document.getElementById('attrtypeErrmsg').innerHTML = '';
		return true;
	}
}


function validateAttributeValues(attributeValue){
	
	if(attributeValue==''){
		document.getElementById('attrvalueErrmsg').innerHTML = 'Enter Attribute values';
		return false;
	}
	else{
		document.getElementById('attrvalueErrmsg').innerHTML = '';
		return true;
	}
}
jQuery(document).ready(function() {
	jQuery( "#tabs" ).tabs();

	$('#attributeName').val('');
	$('#typevalue').val('');
	$("#typeId").val($("#typeId option:first").val());
	$("#tabs ul li a").click(function()
	{
		$("span.msg").remove();
	
	});
	
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

	$('#vendorName').change(function(){
		   
		 $.ajax( {

			 
				type : 'get',
				url : '/DAMUX/getAllMasterMetadata',
				async : false,
				//dataType : 'json',
				data:{vendorName:${'vendorName'}.value},
				
				success:function(response) {

			    	
				   var availableTags=response;
				  
				   $( "#myTable" ).html( availableTags);

			    	
			    		
			    		  
				}
			
		 });
	 });
	
});

</script>
<style>
table.tableborder1 td {
	border-bottom: 1px solid #E8E8E8;
	border-left: 1px solid #E8E8E8;
}
</style>
