<%@include file="/WEB-INF/jsp/include.jsp"%>
<h3>Add Attributes</h3>
<link rel="stylesheet" type="text/css"
	href="./resources/css/createservicetemplate.css">

<c:if test="${not empty notitle}">
	<div id="resultsmessage" class="success">${notitle}</div>
</c:if>
<span style="color:red">${errorMessage }</span>
<form:form method="post" action="metadata.obj"
					modelAttribute="vendor" id="get_vendor_form">
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
															<form:select path="vendor_id" id="vendorId" >
																	<c:forEach items="${vendorList}" var="vendor">

																		<option value="${vendor.vendor_id}">
																			<c:out value="${vendor.vendor_name}" />
																		</option>
																	</c:forEach>
															</form:select>
															</td>
															<td><input type="image" src="resources/images/Submit.png" /></td>
														</tr>
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
						<table>
						   <c:if test="${masterMetadataList!=null}">
                           <c:forEach items="${masterMetadataList}" var="master">
                           <tr><td>${master.master_metadata_name }</td></tr>
                           
                           </c:forEach>				
                           <tr><td><a href="useraddattribute.obj"><img src="resources/images/Btn_AddReceiver1.JPG" width="70" height="25"/></a></td></tr>
				
				           </c:if>
						</table>
						</div>
					</div>					
				</form:form>
				
	<script type="text/javascript">
	
	function submitVendor(){
		
		 document.getElementById("get_vendor_form").submit();
	}
	$(document).ready(function(){
		$('#adminMenuButton').css({'background-color':'#989898','font-weight': 'bold'});
		$('#metadataManagementSubMenuButton').css({'background-color':'#989898','font-weight': 'bold'});
		});
	
	</script>
				