<%@include file="/WEB-INF/jsp/include.jsp"%>
<div class="tabsContainer">
<span style="color:red">${errorMessage}</span>
	<div id="viewTitleDetails" style="min-width: 780px;">
		<table width="93%" border="0" cellspacing="0" cellpadding="0" padding="5px">
		  <tr>
			<td align="left" valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="2">
			  <tr>
				<td class="heading">METADATA - ${packageName}</td>
			  </tr>
			  <tr>
				<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <!--<tr>
						<td width="15%" class="headingbutton1">View Metadata</td>              
						<td width="85%">&nbsp;</td>
					  </tr>
					--></table></td>
				  </tr>
				  <form:form modelAttribute="metaDataModel" 
		              id="save_metadata_form" method="POST">
		            
		            <c:forEach items="${vendorList }" var="vendor">
			        <c:forEach items="${metaDataModel.metadataMap }" var="metadataMap">
				      <c:choose>
				         <c:when
							test="${vendor.metadata_attr_type=='Text Box'&& vendor.master_metadata_id == metadataMap.key }">
							 <tr>
					           <td height="30" align="left" valign="top" class="table1">
					            <div class="completeTitleDetails" >
						          <div class="rowTitleDetails" >
						          <div class="viewTitleColumn1" style="float:left;" >${vendor.master_metadata_name}</div>
							      <div class="viewTitleColumn2" style="float:left;" >:</div>
							      
							      <div class="viewTitleColumn3" >
							       
							     
							      ${metadataMap.value}
							      
							      </div>
						         </div>
						        </div>
					           
				             </tr>
						  </c:when>
						  
						  <c:when
							test="${vendor.metadata_attr_type=='Select Box'&& vendor.master_metadata_id == metadataMap.key  }">
							<c:forEach items="${valuemodel }" var="valueMap">
								<c:choose>
								<c:when test="${vendor.master_metadata_id==valueMap.key}">
								<tr>
					            <td height="30" align="left" valign="top" class="table1">
					            <div class="completeTitleDetails" >
						        <div class="rowTitleDetails" >
						        <div class="viewTitleColumn1" style="float:left;" >${vendor.master_metadata_name}</div>
						        <div class="viewTitleColumn2" style="float:left;" >:</div>
							    <div class="viewTitleColumn3" >
							    ${metadataMap.value}
							    
							    
							    
							    
							    </div>
						        </div>
						        </div>
												
								</td>
								</tr>
								</c:when>
								</c:choose>
							</c:forEach>
						   </c:when>
				           <c:when
							test="${vendor.metadata_attr_type=='Check Box'&& vendor.master_metadata_id == metadataMap.key}">
							<c:forEach items="${valuemodel }" var="valueMap">
							<c:choose>
							<c:when test="${vendor.master_metadata_id==valueMap.key}">
							<tr>
					            <td height="30" align="left" valign="top" class="table1">
					            <div class="completeTitleDetails" >
						        <div class="rowTitleDetails" >
						        <div class="viewTitleColumn1" style="float:left;" >${vendor.master_metadata_name}</div>
						        <div class="viewTitleColumn2" style="float:left;" >:</div>
							    <div class="viewTitleColumn3" >${metadataMap.value}</div>
						        </div>
						        </div>
												
								</td>
								</tr>
							</c:when>
				            </c:choose>  
			                </c:forEach>
			                </c:when> 
			                <c:when
							test="${vendor.metadata_attr_type=='Radio Button'&& vendor.master_metadata_id == metadataMap.key}">
							<c:forEach items="${valuemodel }" var="valueMap">
							<c:choose>
							<c:when test="${vendor.master_metadata_id==valueMap.key}">
							<tr>
					            <td height="30" align="left" valign="top" class="table1">
					            <div class="completeTitleDetails" >
						        <div class="rowTitleDetails" >
						        <div class="viewTitleColumn1" style="float:left;" >${vendor.master_metadata_name}</div>
						        <div class="viewTitleColumn2" style="float:left;" >:</div>
							    <div class="viewTitleColumn3" >${metadataMap.value}</div>
						        </div>
						        </div>
												
								</td>
								</tr>
							</c:when>
				            </c:choose>  
			                </c:forEach>
			                </c:when>  
			                <c:when
							test="${vendor.metadata_attr_type=='MultiSelect Box'&& vendor.master_metadata_id == metadataMap.key}">
							<c:forEach items="${valuemodel }" var="valueMap">
							<c:choose>
							<c:when test="${vendor.master_metadata_id==valueMap.key}">
							<tr>
					            <td height="30" align="left" valign="top" class="table1">
					            <div class="completeTitleDetails" >
						        <div class="rowTitleDetails" >
						        <div class="viewTitleColumn1" style="float:left;" >${vendor.master_metadata_name}</div>
						        <div class="viewTitleColumn2" style="float:left;" >:</div>
							    <div class="viewTitleColumn3" >${metadataMap.value}</div>
						        </div>
						        </div>
												
								</td>
								</tr>
							</c:when>
				            </c:choose>  
			                </c:forEach>
			                </c:when>       
						</c:choose>
						
				
				  
			     </c:forEach>
			   </c:forEach>
			
			  </form:form>			
				  
				</table></td>
			  </tr>
			</table></td>
		  </tr>
		</table>	
		<input type="hidden" id="titleId" value="${title.titleId}"/>		
	</div>
	<div class="editTitle"><a href="./editmetadata.obj"><img src="resources/images/Edit-btn.PNG" width="70" height="25"/></a></div>
</div>

