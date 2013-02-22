<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	

	<form:form modelAttribute="metaDataModel" action="save.obj"
		id="save_metadata_form" method="POST">
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
			<c:forEach items="${vendorList }" var="vendor">
			
				<c:forEach items="${metaDataModel.metadataMap }" var="metadataMap">
				
					<%-- <c:forEach items="${valuemodel }" var="valueMap"> --%>


					<c:choose>
						<%-- <c:when test="${vendor.metadata_attr_type=='Text Box' && vendor.master_metadata_id == package.master_metadata_id}"> --%>
						<c:when
							test="${vendor.metadata_attr_type=='Text Box' && vendor.master_metadata_id == metadataMap.key}">
							<tr>
								<td width="25%" height="32" class="titleFormLabelStyle">
								${vendor.master_metadata_name}
								</td>
                                <td width="3%" class="titleFormColonStyle"><span style="font-size: 11px; color: #ed1c24"></span>:</td>

								<td><form:input path="metadataMap['${metadataMap.key}']"
										name="${metadataMap.value}" value="${metadataMap.value}" />
								</td>


							</tr>

						</c:when>

						<c:when
							test="${vendor.metadata_attr_type=='Select Box'&& vendor.master_metadata_id == metadataMap.key  }">
							<c:forEach items="${valuemodel }" var="valueMap">
								<c:choose>
									<c:when test="${vendor.master_metadata_id==valueMap.key}">
										<%-- <c:forEach items="${valuemodel }" var="valueMap"> --%>
										<%--  <c:when test="${vendor.master_metadata_id==valueMap.key}">  --%>


										<tr>
											<td width="25%" height="32" class="titleFormLabelStyle">${vendor.master_metadata_name}</td>
<td width="3%" class="titleFormColonStyle"><span style="font-size: 11px; color: #ed1c24"></span>:</td>
											<td ><form:select size="1" name="category"
													path="metadataMap['${metadataMap.key}']">
													<form:option value="${metadataMap.value}">${metadataMap.value}</form:option>


													<%-- <c:set var="dateParts" value="${fn:split(valueMap.value, ',')}" /> --%>
													<c:forEach var="values"
														items="${fn:split(valueMap.value, ',')}">
														<form:option value="${values}">${values}</form:option>
													</c:forEach>

												</form:select>
											</td>
										</tr>
									</c:when>
								</c:choose>
							</c:forEach>
						</c:when>
						<%--    </c:forEach> --%>
						<%--  </c:when> --%>

						<c:when
							test="${vendor.metadata_attr_type=='Check Box'&& vendor.master_metadata_id == metadataMap.key}">
							<c:forEach items="${valuemodel }" var="valueMap">
								<c:choose>
									<c:when test="${vendor.master_metadata_id==valueMap.key}">
										<tr>
											<td>${vendor.master_metadata_name}</td>

											<td><form:checkbox
													path="metadataMap['${metadataMap.key}']"
													value="${metadataMap.value}" /> ${metadataMap.value} <c:forEach
													var="values" items="${fn:split(valueMap.value, ',')}">
													<form:option value="${values}">${values}</form:option>
												</c:forEach> <%-- 	</form:checkbox> --%></td>
										</tr>
									</c:when>
								</c:choose>
							</c:forEach>
						</c:when>



					</c:choose>
				</c:forEach>
			</c:forEach>
			<%-- 	</c:forEach> --%>


			<!-- <tr>
				<td><input type="submit" value="Submit"/>
				</td>
			</tr> -->
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
	<%-- <form action="addattribute.obj" method="POST">
<input type ="submit"   value="Add Attribute">
</form> --%>
	<a href="useraddattribute.obj?vendor=1">Vendor Add Attribute</a>
	<br />
	<br />
	<br />


</body>
</html>