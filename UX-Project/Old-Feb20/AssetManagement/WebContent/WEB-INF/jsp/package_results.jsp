<%@include file="/WEB-INF/jsp/include.jsp"%>
<link rel="stylesheet" type="text/css"
	href="./resources/css/createservicetemplate.css">

<c:if test="${not empty notitle}">
	<div id="resultsmessage" class="success">${notitle}</div>
</c:if>


<div id="titleResults" class="resultsLeftInnerDiv">

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="left" valign="top">
				<table id="title_results_table">
					<!-- thead>
					<tr>
						<th name ="title_id" width="156">Title Id</th>
						<th name ="title_name" width="156">Title Name</th>
						<th name ="wpr_number" width="156">WPR Number</th>
						<th name ="product_number" width="156">Product Number</th>
						<th name ="edi_code"  width="156">EDI Code</th>
					</tr>
				</thead-->
					</tbody>
					<tbody>
						<c:forEach items="${packagesList}" var="package"
							varStatus="itrStatus">
							<tr>

								<td><a href="loadmetadata.obj?id=${package.media_package_id}&package=${package.media_package_name}">${package.media_package_name}</a>
								</td>
								<td>${package.masterStatus.statusName}</td>
								<td><a
									href="<c:url value="getFileList?packageId=${package.media_package_id}" />"><img
										id="clickToExpand" src="resources/images/bar.png" width="50px"
										height="23" /> </a>
										
										<div id="divnonFunctionalTitleItems">
										<table>
										
											<c:forEach items="${FileList}" var="packagefile"
												varStatus="itrStatus">
												<c:if test="${package.media_package_id==packagefile.media_package_id}">
												<tr>
													<td>${packagefile.media_package_file_name}</td>
													<%-- <td>${packagefile.file_type}</td>
													<td>${packagefile.isarrived}</td> --%>
												</tr>
												</c:if>
											</c:forEach>
											
										</table>
									</div>
										
										
									</td>
									
								<td>
								   <c:choose>
								    <c:when test="${package.status_id=='TRA'}">
								        <a href="goto">Publish</a>
								    </c:when>
								    
								    <c:otherwise>
								         <a href="" onclick="this.removeAttribute('href');this.className='disabled'">Publish</a>
								    </c:otherwise>
								   </c:choose>
								</td>
								<%-- <c:forEach items="${package.metadatalist}" var="metaPackage" varStatus="itrStatus">
							 <td>${metaPackage.metadata_attr_value}</td> 
							<c:if test=" ${metaPackage.media_package_id == package.media_package_id}"> 
								 <td>${metaPackage.metadata_id }hi</td>	 
							 </c:if> 
							 
						</c:forEach> --%>

							</tr>
						</c:forEach>
						
				</table></td>
		</tr>
		
		
	</table>
	
</div>





<%-- <form:form modelAttribute="metadatamodel"  action="./getmetadata" id="save_metadata_form" method="POST">
<table>
<c:forEach items="${metadatamodel}" var="metadataList" varStatus="status">

<tr>
<td>Package Name<td>
<td>${metadataList.media_package_name}</td><td><a href="./loadmetadata">Get MetadataDetails</a></td>
</tr>
</c:forEach>

</table>
</form:form> --%>
<script type="text/javascript">
/* 	jQuery(document).ready(function($) {
		$("#title_results_table").flexigrid({			
			title: "Title Results",
			sortname: "title_id",
			sortorder: "asc",
			width: 850,
			height: 400,
			//sortable: true,			
		});
	}); */ 
	if (!jQuery.cookie) {
		jQuery.cookie = function(){};
	}
	jQuery(document).ready(function($) {
		$("#title_results_table").flexigrid({
			dataType: 'json',
			colModel : [				
				
				{display: 'Package Name', name : 'media_package_name', width : 120, sortable : true, align: 'center'},
				{display: 'Package Status', name : 'statusName', width : 150, sortable : true, align: 'center'} ,				
				{display: 'Package Files', name : 'metadata_ingest_status', width : 180, sortable : true, align: 'center'},
				{display: 'Action', name : 'metadata_ingest_status', width : 180, sortable : true, align: 'center'}
				
			],
			title: "Package Results",
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
	
	}
	); 
</script>
