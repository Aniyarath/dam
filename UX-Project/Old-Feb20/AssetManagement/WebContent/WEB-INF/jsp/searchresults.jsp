
<%@include file="/WEB-INF/jsp/include.jsp"%>
<link rel="stylesheet" type="text/css"
	href="./resources/css/createservicetemplate.css">
<span style="color: red">${errorMessage}</span>
<c:if test="${not empty notitle}">
	<div id="resultsmessage" class="success">${notitle}</div>
</c:if>
<div>
	<div id="titleForm" class="resultsLeftInnerDiv" align="center">
	  
		<table   id="table_advance_search" width="100%">
					<tr>
					
						<td width="32%" height="32" class="titleFormFieldStyle">PackageName:&nbsp;&nbsp;&nbsp;<input type="text" size="20"></td>
						<td width="32%" height="32" class="titleFormFieldStyle">Status:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
							<select name="status">
							<option value="INGESTED">INGESTED</option>
							<option value="TRANSCODED">TRANSCODED</option>
							<option value="PUBLISHED">PUBLISHED</option>
							<option value="ARCHIVED">ARCHIVED</option>
							<option value="QUALITY">QUALITY</option>
							<option value="MEDIA_INGEST">MEDIA_INGEST</option>
							<option value="METADATA_INGEST">METADATA_INGEST</option>
							<option value="FAILED">FAILED</option>
							<option value="START">START</option>
							</select></td>
						</tr>
						<tr><td width="32%" height="32" class="titleFormFieldStyle">Category:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="text" size="20"></td>
						<td width="32%" height="32" class="titleFormFieldStyle">Director:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				              <input type="text" size="20"></td>
						</tr>	
					
						<tr><td width="32%" height="32" class="titleFormFieldStyle">Producer:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="text" size="20"></td>
						<td width="32%" height="32" class="titleFormFieldStyle">MediaType:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
							<select name="mediatyp">
							<option value="Audio">AudioType</option>
							<option value="Video">VideoType</option>
							</select></td>	
						</tr>
                
					<tr align="center">					
					<td  colspan="2" align="center"><input type="image" src="resources/images/searchButton.png" style="padding-left:200px;"/></td>
					</tr>
						
				</table>
			</div>	 


	</div>


<div id="titleResults" class="resultsLeftInnerDiv">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="left" valign="top">
				<table id="title_results_table" align="center">
					</tbody>
					<tbody>
						<%int progressbar=10; %>
					 	<c:forEach items="${packagesList}" var="package" varStatus="itrStatus">
							<tr>
								<td><a href="loadmetadata.obj?id=${package.media_package_id}&package=${package.media_package_name}">${package.media_package_name}</a></td>
								<td>${package.masterStatus.statusName}</td>
									<div id="divnonFunctionalTitleItems">								
										<%
											int comparisionvalue = 1;
										%>
										<%
											int comparisionvalue1 = 1;
										%>
										<c:forEach items="${package.packageFileList }" var="packfileList">
											<c:if test="${package.media_package_id==packfileList.media_package_id}">												
												<%
													if (comparisionvalue == 1) {
																	comparisionvalue++;
																	comparisionvalue1 += 1;
												%>

												<td align="center"><a href="javascript:open_win('${packfileList.media_package_file_name}');">${packfileList.media_package_file_name
														}</a></td>
												<td style="text-align: center;">
												 <c:choose>
								                     <c:when test="${package.masterStatus.statusName=='TRANSCODE'}">
								                     <img src="resources/images/ingestImage.png" width="20" height="20" title="Ingest"/>
								                     <img hspace="15" src="resources/images/transcode_image.jpg" width="30" height="20" title="Transcode"/>
								                     <a href="<c:url value="searchprofiles.obj?id=${vendorId}" />">
								                      <img hspace="15" src="resources/images/publish.png" width="20" height="20" title="Publish"/>
								                     </a>
								                     
								                     <td>  <div id="<%=progressbar %>" class="progressbar"><div class="progress_label_<%=progressbar %>" style="width:100px;height:8px;"></div></div></td>
								                     </c:when>
								    
								                 <c:otherwise>
								                   
								                     <img src="resources/images/ingestImage.png" width="20" height="20" title="Ingest"/>
								                     <img hspace="15" src="resources/images/transcode_image.jpg" width="30" height="20" title="Transcode"/>
								                     <a href="#" onclick="this.removeAttribute('href');this.className='disabled'">
								                      <img src="resources/images/publish.png" width="20" height="20" title="Publish"/>
								                     </a>
								                 </c:otherwise>
								                </c:choose>
												</td>
												<script>
							$(function() {
							var progressbar = $( "#"+<%=progressbar %> ),
							progressLabel = $( ".progress_label_"+<%=progressbar %> );
							progressbar.progressbar({
							value: false,
							change: function() {
							progressLabel.text( progressbar.progressbar( "value" ) + "%" );
							}
							/* complete: function() {
							progressLabel.text( "Complete!" );
							} */
							});
							
							function progress() {
								var val = parseFloat(progressbar.progressbar( "value" )) ||parseInt(progressbar.attr('id'));
							
							progressbar.progressbar( "value", val + 0.25 );
							if ( val < 99.75 ) {
							setTimeout( progress, 100 );
							}
							}
							setTimeout( progress, 3000 );
							});
							</script>
					<%progressbar+=10; %>	
								</tr>
								<%
									} else {
													comparisionvalue++;
								%>
								
								<tr>
									<td><h4></h4></td>
									<td><h4></h4></td>
									<td align="center"><a
										href="javascript:open_win('${packfileList.media_package_file_name}');">${packfileList.media_package_file_name
											}</a></td>
									<td><h4></h4></td>
								</tr>
								<%
									}
								%>
								</c:if>
							</c:forEach>

							
							</div>


							<%
								if (comparisionvalue1 == 1) {
							%>
							<td><a
								href="searchallpackages.obj?packageId=${package.media_package_id}">${packfileList.media_package_file_name}</a>
							</td>
							<td style="text-align: center;">
							 <c:choose>
								<c:when test="${package.masterStatus.statusName=='TRANSCODE'}">
								<img src="resources/images/ingestImage.png" width="20" height="20" title="Ingest"/>
								<img hspace="15" src="resources/images/transcode_image.jpg" width="30" height="20" title="Transcode"/>
								<a href="<c:url value="searchprofiles.obj?id=${vendorId}" />">
								 <img hspace="15" src="resources/images/publish.png" width="20" height="20" title="Publish"/>
								</a> </td>
			 
								  <td><div id="<%=progressbar %>" class="progressbar"><div class="progress_label_<%=progressbar %>" style="width:100px;height:8px;"></div></div></td>
								</c:when>
								    
							    <c:otherwise>
							       
								   <img src="resources/images/ingestImage.png" width="20" height="20" title="Ingest"/>
								   <img hspace="15" src="resources/images/transcode_image.jpg" width="30" height="20" title="Transcode"/>
								   <a href="#" onclick="this.removeAttribute('href');this.className='disabled'">
								    
								    <img src="resources/images/publish.png" width="20" height="20" title="Publish"/>
								   </a>
							    </c:otherwise>
							</c:choose>
							</td>
							</tr>
							<%
								}
							%>
							</c:forEach></tbody>
					</table></td>
			</tr>


		</table>

	</div>
</div>

<script>
 function submitSearchForm(){
	
			document.getElementById("advanced_search_form").submit();	
 }
</script>

<script type="text/javascript">
	function open_win(fileName) {
		alert("fileName::" + fileName);
		var newWindow = window
				.open(
						"recorddescription?fileName=" + fileName,
						'_blank',
						'width=300,height=250,left=45, top=15, scrollbars=no, menubar=no,resizable=,directories=no,location=no,Navigation Toolbar=no');

		//document.write("<p>Welcome to popup button</p>")
		window.focus()
	}
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
		jQuery.cookie = function() {
		};
	}
	jQuery(document).ready(function($) {
		$('#packageSearchMenuButton').css({
			'background-color' : '#989898',
			'font-weight' : 'bolder'
		});
		$("#title_results_table").flexigrid({
			dataType : 'json',
			colModel : [

			{
				display : 'Package Name',
				name : 'media_package_name',
				width : 150,
				sortable : true,
				align : 'center'
			}, {
				display : 'Package Status',
				name : 'statusName',
				width : 150,
				sortable : true,
				align : 'center'
			}, {
				display : 'Package Files',
				name : 'metadata_ingest_status',
				width : 120,
				sortable : true,
				align : 'center'
			}, {
				display : 'Action',
				name : 'metadata_ingest_status',
				width : 160,
				sortable : true,
				align : 'center'
			},{
				display : 'Transcoding progress',
				name : 'metadata_ingest_status',
				width : 180,
				sortable : true,
				align : 'center'
			}

			],
			title : "Package Results",
			//sortname: "language",
			sortorder : "asc",
			showTableToggleBtn : true,
			width : 725,
			height : 325,
			searchitems : [ {
				display : 'media_package_id',
				name : 'iso'
			}, {
				display : 'media_package_name',
				name : 'name',
				isdefault : true
			} ]
		});

		$("#clickToExpand").click(function() {
			if ($("#divnonFunctionalTitleItems").is(":hidden")) {
				$("#divnonFunctionalTitleItems").slideDown("slow");
				var imgsrc = "resources/images/bar_up.png";
			} else {
				$("#divnonFunctionalTitleItems").slideUp("slow");
				var imgsrc = "resources/images/bar.png";
			}
			$(this).attr('src', imgsrc);
		});

	});
</script>
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
		jQuery.cookie = function() {
		};
	}
	jQuery(document).ready(function($) {

		$("#table_advance_search").flexigrid({
			dataType : 'json',
			/*colModel : [				
				
				{display: 'Package Name', name : 'media_package_name', width : 120, sortable : true, align: 'center'},
				{display: 'Package Status', name : 'statusName', width : 150, sortable : true, align: 'center'} ,				
				{display: 'Package Files', name : 'metadata_ingest_status', width : 180, sortable : true, align: 'center'},
				{display: 'Action', name : 'metadata_ingest_status', width : 180, sortable : true, align: 'center'}
				
			],*/
			title : "Advanced Search",
			//sortname: "language",
			sortorder : "asc",
			showTableToggleBtn : true,
			width : 725,
			height : 155
		/* searchitems : [
			{display: 'media_package_id', name : 'iso'},
			{display: 'media_package_name', name : 'name', isdefault: true}
		],	*/
		});

		$("#clickToExpand").click(function() {
			if ($("#divnonFunctionalTitleItems").is(":hidden")) {
				$("#divnonFunctionalTitleItems").slideDown("slow");
				var imgsrc = "resources/images/bar_up.png";
			} else {
				$("#divnonFunctionalTitleItems").slideUp("slow");
				var imgsrc = "resources/images/bar.png";
			}
			$(this).attr('src', imgsrc);
		});

	});
</script>






<%-- <%@include file="/WEB-INF/jsp/include.jsp"%>
<link rel="stylesheet" type="text/css"
	href="./resources/css/createservicetemplate.css">
<!-- <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" /> -->
<c:if test="${not empty notitle}">
	<div id="resultsmessage" class="success">${notitle}</div>
</c:if>
<div>
<div id="titleForm" class="resultsLeftInnerDiv" align="center">
				<table   id="table_advance_search" width="100%">
					<tr>
					
						<td width="32%" height="32" class="titleFormFieldStyle">PackageName:<input type="text" size="20"></td>
						<td width="32%" height="32" class="titleFormFieldStyle">Status:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
							<select name="status">
							<option value="INGESTED">INGESTED</option>
							<option value="TRANSCODED">TRANSCODED</option>
							<option value="PUBLISHED">PUBLISHED</option>
							<option value="ARCHIVED">ARCHIVED</option>
							<option value="QUALITY">QUALITY</option>
							<option value="MEDIA_INGEST">MEDIA_INGEST</option>
							<option value="METADATA_INGEST">METADATA_INGEST</option>
							<option value="FAILED">FAILED</option>
							<option value="START">START</option>
							</select></td>
						</tr>
						<tr><td width="32%" height="32" class="titleFormFieldStyle">Category:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="text" size="20"></td>
						<td width="32%" height="32" class="titleFormFieldStyle">Director:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				              <input type="text" size="20"></td>
						</tr>	
					
						<tr><td width="32%" height="32" class="titleFormFieldStyle">Producer:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="text" size="20"></td>
						<td width="32%" height="32" class="titleFormFieldStyle">MediaType:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
							<select name="mediatyp">
							<option value="Audio">AudioType</option>
							<option value="Video">VideoType</option>
							</select></td>	
						</tr>
                
					<tr align="center">					
					<td  colspan="2" align="center"><input type="image" src="resources/images/searchButton.png" style="padding-left:200px;"/></td>
					</tr>
						
				</table>
			</div>	 



<div id="titleResults" class="resultsLeftInnerDiv">
<input type='hidden' id='fileName' name='fileName' value=''/> 

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="left" valign="top">
				<table id="title_results_table" align="center">
					</tbody>
					<tbody>
						<c:forEach items="${packagesList}" var="package" varStatus="itrStatus">
							<tr>
								<td><a href="loadmetadata.obj?id=${package.media_package_id}&package=${package.media_package_name}">${package.media_package_name}</a>
								</td>
								<td>${package.masterStatus.statusName}</td>
							<td><a href="searchallpackages.obj?packageId=${package.media_package_id}?fileName=${packfileList.media_package_file_name }">${packfileList.media_package_file_name}</a>																
										<div id="divnonFunctionalTitleItems">
																													<div class="accordion" style="z-index:9999;">
											<c:forEach items="${package.packageFileList }" var="packfileList">
											<c:if test="${package.media_package_id==packfileList.media_package_id}">
												
				
													<h5>${packfileList.media_package_file_name }</h5><div>
													<p><h5>FileName:${packfileList.media_package_file_name }</h5>													
													<h5>FileType:${packfileList.file_type}</h5>
													<h5>FileSize:123kb</h5>
													</div>
												
												
												</c:if>
                                           </c:forEach>
                                           </div>
										
										
									</div>
											
									</td>
									
								<td style="text-align:center;">
								   <c:choose>
								    <c:when test="${package.status_id=='TRA'}">
								        <a href="goto" style="text-align:center;">Publish</a>
								    </c:when>
								    
								    <c:otherwise>
								         <a href="" onclick="this.removeAttribute('href');this.className='disabled'">Publish</a>
								    </c:otherwise>
								   </c:choose>
								</td>
							</tr>
						</c:forEach>
						
				</table></td>
		</tr>
		
		
	</table>
	
</div></div>
  




<script type="text/javascript">
function open_win(fileName)
{
	alert("fileName::"+fileName);
	window.open("recorddescription?fileName="+fileName,'width=200,height=100');
//document.write("<p>Welcome to popup button</p>")
window.focus()
}
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
		$('#packageSearchMenuButton').css({'background-color':'#989898','font-weight': 'bolder'});
		$("#title_results_table").flexigrid({
			dataType: 'json',
			colModel : [				
				
				{display: 'Package Name', name : 'media_package_name', width : 150, sortable : true, align: 'center'},
				{display: 'Package Status', name : 'statusName', width : 150, sortable : true, align: 'center'} ,				
				{display: 'Package Files', name : 'metadata_ingest_status', width : 200, sortable : true, align: 'center'},
				{display: 'Action', name : 'metadata_ingest_status', width : 150, sortable : true, align: 'center'}
				
			],
			title: "Package Results",
			//sortname: "language",
			sortorder: "asc",
			showTableToggleBtn: true,
			width: 725,
			height: 325,
			searchitems : [
				{display: 'media_package_id', name : 'iso'},
				{display: 'media_package_name', name : 'name', isdefault: true}
			],								
		});
	
		$(function() {    
			$( ".accordion" ).accordion();  
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
		
		$("#table_advance_search").flexigrid({
			dataType: 'json',
			/*colModel : [				
				
				{display: 'Package Name', name : 'media_package_name', width : 120, sortable : true, align: 'center'},
				{display: 'Package Status', name : 'statusName', width : 150, sortable : true, align: 'center'} ,				
				{display: 'Package Files', name : 'metadata_ingest_status', width : 180, sortable : true, align: 'center'},
				{display: 'Action', name : 'metadata_ingest_status', width : 180, sortable : true, align: 'center'}
				
			],*/
			title: "Advaced Search",
			//sortname: "language",
			sortorder: "asc",
			showTableToggleBtn: true,
			width: 725,
			height: 155,
			/* searchitems : [
				{display: 'media_package_id', name : 'iso'},
				{display: 'media_package_name', name : 'name', isdefault: true}
			],	*/							
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

 --%>