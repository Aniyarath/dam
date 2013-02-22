<%@include file="/WEB-INF/jsp/include.jsp"%>
<%@ page import="java.util.*"%>

<div class="tabsContainer">
	<form:form modelAttribute="metaDataModel" action="updateMetadata.obj"
		id="save_metadata_form" method="POST">


		<div id="viewTitleDetails" style="min-width: 780px;">
			<table width="93%" border="0" cellspacing="0" cellpadding="0"
				padding="5px">
				<tr>
					<td align="left" valign="top"><table width="100%" border="0"
							cellspacing="2" cellpadding="2">
							<tr>
								<td class="heading">METADATA - ${packageName}</td>
							</tr>
							<tr>
							
							<td><span style="color:green">${SuccessMessage }</span></td>
							</tr>
							<tr>
								<td><table width="100%" border="0" cellspacing="0"
										cellpadding="0">
										<tr>
											<td><table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<!--<tr>
														<td width="15%" class="headingbutton1">View Metadata</td>
														<td width="85%">&nbsp;</td>
													</tr>
												--></table>
											</td>
										</tr>

										<c:forEach items="${vendorList }" var="vendor">
											<c:forEach items="${metaDataModel.metadataMap }"
												var="metadataMap">
												<c:choose>
													<c:when
														test="${vendor.metadata_attr_type=='Text Box' && vendor.master_metadata_id == metadataMap.key   }">
														<tr>
															<td height="30" align="left" valign="top" class="table1">
																<div class="completeTitleDetails">
																	<div class="rowTitleDetails">
																		<div class="viewTitleColumn1" style="float: left;">${vendor.master_metadata_name}</div>
																		<div class="viewTitleColumn2" style="float: left;">:</div>
																		<div class="viewTitleColumn3">





																			<form:input
																				path="metadataMap['${vendor.master_metadata_id}']"
																				value="${metadataMap.value}" />

																		</div>
																	</div>
																</div></td>
														</tr>
													</c:when>

													<c:when
														test="${vendor.metadata_attr_type=='Select Box'&& vendor.master_metadata_id == metadataMap.key  }">
														<c:forEach items="${valuemodel }" var="valueMap">
															<c:choose>
																<c:when
																	test="${vendor.master_metadata_id==valueMap.key}">
																	<tr>
																		<td height="30" align="left" valign="top"
																			class="table1">
																			<div class="completeTitleDetails">
																				<div class="rowTitleDetails">
																					<div class="viewTitleColumn1" style="float: left;">${vendor.master_metadata_name}</div>
																					<div class="viewTitleColumn2" style="float: left;">:</div>
																					<div class="viewTitleColumn3">
																						<form:select size="1" name="category"
																							path="metadataMap['${metadataMap.key}']">
																							<form:option value=" "></form:option>
																							<c:forEach var="values"
																								items="${fn:split(valueMap.value, ',')}">
																								<c:choose>
																									<c:when test="${metadataMap.value==values}">

																										<option value="${values}" selected>${values}</option>
																									</c:when>
																									<c:otherwise>
																										<option value="${values}">${values}</option>
																									</c:otherwise>
																								</c:choose>
																							</c:forEach>

																						</form:select>
																					</div>
																				</div>
																			</div></td>
																	</tr>
																</c:when>
															</c:choose>
														</c:forEach>
													</c:when>
													<c:when
														test="${vendor.metadata_attr_type=='Check Box'&& vendor.master_metadata_id == metadataMap.key}">
														<c:forEach items="${valuemodel }" var="valueMap">
															<c:choose>
																<c:when
																	test="${vendor.master_metadata_id==valueMap.key}">
																	<tr>
																		<td height="30" align="left" valign="top"
																			class="table1">
																			<div class="completeTitleDetails">
																				<div class="rowTitleDetails">
																					<div class="viewTitleColumn1" style="float: left;">${vendor.master_metadata_name}</div>
																					<div class="viewTitleColumn2" style="float: left;">:</div>
																					<div class="viewTitleColumn3">
																						<c:forEach var="values"
																							items="${fn:split(valueMap.value, ',')}">
																							<option value="${values}" selected>${values}</option>
																							<c:choose>
																								<c:when test="${metadataMap.value==values}">

																									<form:checkbox
																										path="metadataMap['${metadataMap.key}']"
																										value="${metadataMap.value}" />
																								</c:when>
																								<c:otherwise>

																									<form:checkbox
																										path="metadataMap['${metadataMap.key}']"
																										value="${values}" />
																								</c:otherwise>
																							</c:choose>
																						</c:forEach>



																					</div>
																				</div>
																			</div></td>
																	</tr>
																</c:when>
															</c:choose>
														</c:forEach>
													</c:when>
													<c:when
														test="${vendor.metadata_attr_type=='MultiSelect Box'&& vendor.master_metadata_id == metadataMap.key  }">
														<c:forEach items="${valuemodel }" var="valueMap">
															<c:choose>
																<c:when
																	test="${vendor.master_metadata_id==valueMap.key}">
																	<tr>
																		<td height="30" align="left" valign="top"
																			class="table1">
																			<div class="completeTitleDetails">
																				<div class="rowTitleDetails">
																					<div class="viewTitleColumn1" style="float: left;">${vendor.master_metadata_name}</div>
																					<div class="viewTitleColumn2" style="float: left;">:</div>
																					<div class="viewTitleColumn3">
																						<form:select multiple="true"
																							path="metadataMap['${metadataMap.key}']"
																							style="max-width:210px; min-width:95px;">

                                                                                                                     <c:forEach var="values"
																								items="${fn:split(valueMap.value, ',')}">
																								<c:choose>
																									<c:when test="${metadataMap.value==values}">

																										<option value="${values}" selected>

																											<c:if test="${metadataMap.value==values}"></c:if>
																											<c:out value="${values}" />


																										</option>
																									</c:when>
																									<c:otherwise>
																										<option value="${values}" >${values}</option>
																									</c:otherwise>
																								</c:choose>
																							</c:forEach>
                                                                                                                     

																							<form:options items="${values}"
																								itemValue="titleFormatId"
																								itemLabel="titleFormateName" />
																						</form:select>

																						
																					</div>
																				</div>
																			</div></td>
																	</tr>
																</c:when>
															</c:choose>
														</c:forEach>
													</c:when>
													<c:when
														test="${vendor.metadata_attr_type=='Radio Button'&& vendor.master_metadata_id == metadataMap.key  }">
														<c:forEach items="${valuemodel }" var="valueMap">
															<c:choose>
																<c:when
																	test="${vendor.master_metadata_id==valueMap.key}">
																	<tr>
																		<td height="30" align="left" valign="top"
																			class="table1">
																			<div class="completeTitleDetails">
																				<div class="rowTitleDetails">
																					<div class="viewTitleColumn1" style="float: left;">${vendor.master_metadata_name}</div>
																					<div class="viewTitleColumn2" style="float: left;">:</div>
																					<div class="viewTitleColumn3">
																						<c:forEach var="values"
																							items="${fn:split(valueMap.value, ',')}">
																							<option value="${values}" selected>${values}</option>
																							<c:choose>
																								<c:when test="${metadataMap.value==values}">

																									<form:radiobutton
																										path="metadataMap['${metadataMap.key}']"
																										value="${metadataMap.value}" />
																								</c:when>
																								<c:otherwise>

																									<form:radiobutton
																										path="metadataMap['${metadataMap.key}']"
																										value="${values}" />
																								</c:otherwise>
																							</c:choose>
																						</c:forEach>
																					</div>
																				</div>
																			</div></td>
																	</tr>
																</c:when>
															</c:choose>
														</c:forEach>
													</c:when>
												</c:choose>


											</c:forEach>

										</c:forEach>



									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<input type="hidden" id="titleId" value="${title.titleId}" />
		</div>
		<!-- <div class="editTitle"><a href="addattribute.obj"><img src="resources/images/Btn_AddReceiver1.JPG" width="70" height="25"/></a></div> -->
		<div class="editTitle">
			<!--<a href="useraddattribute.obj"><img
				src="resources/images/Btn_AddReceiver1.JPG" width="70" height="25" />
			</a> --><a href="#" onclick="submitForm()"><img
				src="resources/images/Submit.png" width="70" height="25" /> </a>
		</div>


	</form:form>
</div>
<script type="text/javascript">
	function submitForm() {

		document.getElementById("save_metadata_form").submit();
	}
</script>

