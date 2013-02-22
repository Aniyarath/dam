<%@include file="/WEB-INF/jsp/include.jsp"%>
<h3>Add Attributes</h3>
<form:form method="post" action="addAttribute"
					modelAttribute="masterMetadata">
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
															<form:label path="master_metadata_name">AttributeName</form:label>
															</td>
															<td width="3%" class="titleFormColonStyle"><span
																style="font-size: 11px; color: #ed1c24">*</span>:</td>
															<td width="72%" height="32" class="titleFormFieldStyle"><form:input
																	path="master_metadata_name" size="32" /></td>
														</tr>
														<tr>
															<td width="25%" height="32" class="titleFormLabelStyle">AttributeType</td>
															<td width="3%" class="titleFormColonStyle"><span
																style="font-size: 11px; color: #ed1c24">*</span>:</td>
															<td width="72%" height="32" class="titleFormFieldStyle">
															<form:select path="metadata_attr_type_id" onchange="addValues()" id="typeId">
																	<c:forEach items="${metadataList}" var="name">

																		<option value="${name.metadata_attr_type_id}">
																			<c:out value="${name.metadata_attr_type}" />
																		</option>
																	</c:forEach>
															</form:select>
															</td>
														</tr>
														<tr>
															<td width="25%" height="32" class="titleFormLabelStyle">
															<form:label path="metadataValues">Values</form:label></td>
															<td width="3%" class="titleFormColonStyle"></td>
															<td width="72%" height="32" class="titleFormFieldStyle"><form:input
																	path="metadataValues" id="typevalue" size="32" /></td>
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
									<td><input type="image" src="resources/images/Submit.png" />
									</td>
								</tr>
							</table>
						</div>
					</div>					
				</form:form>
				<script type="text/javascript">
 $(document).ready(function(){
 $('#adminMenuButton').css({'background-color':'#989898','font-weight': 'bold'});
 $('#metadataManagementSubMenuButton').css({'background-color':'#989898','font-weight': 'bold'});
 });
 </script>