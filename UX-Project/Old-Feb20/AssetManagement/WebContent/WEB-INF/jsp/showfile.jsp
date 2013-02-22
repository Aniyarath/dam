<!--<%@include file="/WEB-INF/jsp/include.jsp"%>
<link rel="stylesheet" type="text/css"
	href="./resources/css/createservicetemplate.css">
	
	<script type="text/javascript" src="<c:url value="/resources/js/multizoom.js"/>"></script>
	<div id="titleResults" class="resultsLeftInnerDiv">
	<form:form action="profile.obj" method="get"  id="title_form">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr >
		<td><img src="resources/images/tableLable.png" width="100%" /> </td>
		</tr>
		<tr>
			<td align="left" valign="top">
				<table id="title_results_table" width="100%" border="1">
				<tr>
				<td width="150px" valign="middle" align="left" class="overlayRow"><h3>Package Name</h3></td>
				<td width="200px" valign="middle" align="left" class="overlayRow"><h3>Profile Path</h3></td>
				<td width="150px" valign="middle" align="left" class="overlayRow"><h3>Video Preview</h3></td>
				<td width="120px" valign="middle" align="left" class="overlayRow"><h3>Thumbnail</h3></td>
				</tr>
<c:if test="${mediaList!=null}">

<c:forEach var="media" items="${mediaList}">
<tr class="commonTrailersClass">

			<td width="150px" valign="middle" align="left" class="overlayRow">${media.media_package_name}</td>
			<td width="200px" valign="middle" align="left" class="overlayRow"><a href="<c:url value="movefile.obj?id=${media.media_package_id}"/>">Publish</a></td>
			<%-- <td width="16%" valign="middle" align="left" class="overlayRow"> <a href="<c:url value="previewvideo.obj?name=${media.media_package_name}"/>">Preview</a></td> --%>
			
			<td width="180px" valign="middle" align="left" class="overlayRow"> 
				<a href="#" id="file:///<c:url value='${lowresNameMap[media.media_package_name]}'/>" class="previewVideo">Preview</a>
			</td>
			<td width="120px" valign="middle" align="left" class="overlayRow"> 
					<img id="${media.media_package_name}" src="imagesServlet/${media.media_package_name}.jpg" width="50px" height="30px"/>
					<script type="text/javascript">jQuery(document).ready(function($) {$("#${media.media_package_name}").addimagezoom({speed: 1500,zoomrange: [6, 6],magnifiersize: [300,180]});});</script>
				
			</td>
</tr>
</c:forEach>


</c:if>

</table></td>
		</tr>
		<tr>
		<td style="padding: 5px; text-align:center" colspan="6" ><a href="#" onclick="submitTitleForm();"><img src="resources/images/Back.png" width="70" height="30"/></a></td>	
		</tr>
		
	</table>
	</form:form>
</div>

<a href="goto.obj">Back to Search</a>


<script type="text/javascript">

function submitTitleForm(){
	document.getElementById("title_form").submit();	
}
 jQuery(document).ready(function($) {
	$('.previewVideo').click(function (){
		
		var video=$(this).attr('id');
		
		
		var newWindow = window.open ("","Video","width=350px,height=250px,left=45px, top=15px, scrollbars=no, menubar=no,resizable=no,directories=no,location=no,Navigation Toolbar=no");
		newWindow.document.close();
		newWindow.document.open();
		newWindow.document.write("<object id='MediaPlayer1' width='330px' height='230px' classid='CLSID:22D6F312-B0F6-11D0-94AB-0080C74C7E95'"+
            "codebase='http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701'"+
            "standby='Loading Microsoft® Windows® Media Player components...' type='application/x-oleobject'>"+
            "<param name='FileName' value='"+video+"' />"+
            "<param name='AutoStart' value='false' />"+
            "<param name='DefaultFrame' value='mainFrame' />"+
            "<param name='ShowStatusBar' value='0' />"+
            "<param name='ShowPositionControls' value='1' />"+
            "<param name='showcontrols' value='1' />"+
            "<param name='ShowAudioControls' value='1' />"+
            "<param name='ShowTracker' value='1' />"+
            "<param name='EnablePositionControls' value='1' />"+

            "<embed  type='application/x-mplayer2' pluginspage='http://www.microsoft.com/Windows/MediaPlayer/'"+
                "src='"+video+"' align='middle' width='350px' height='250px' defaultframe='rightFrame'"+
                " id='MediaPlayer2' />"+

			"</embed>"+
			"</object>"
			);
		
		});
	  
 }); 
	
</script>
			  
-->

<%@include file="/WEB-INF/jsp/include.jsp"%>
<link rel="stylesheet" type="text/css"
	href="./resources/css/createservicetemplate.css">
	
	<script type="text/javascript" src="<c:url value="/resources/js/multizoom.js"/>"></script>
	<div id="titleResults" class="resultsLeftInnerDiv">
	<span style="color:red">${errorMessage}</span>
	<form:form action="profile.obj" method="get"  id="title_form">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr >
		<td><img src="resources/images/tableLable.png" width="100%" /> </td>
		</tr>
		<tr>
			<td align="left" valign="top">
				<table id="title_results_table" width="100%" border="1">
				<tr>
				<td width="200px" valign="middle" align="left" class="overlayRow"><h3>Package Name</h3></td>
				<td width="150px" valign="middle" align="left" class="overlayRow"><h3>Action</h3></td>
				<td width="150px" valign="middle" align="left" class="overlayRow"><h3>Thumbnail</h3></td>
				</tr>
<c:if test="${mediaList!=null}">

<c:forEach var="media" items="${mediaList}">
<tr class="commonTrailersClass">

			<td  valign="middle" align="left" class="overlayRow">${media.media_package_name}</td>
			<td  valign="middle" align="left" class="overlayRow"><a href="<c:url value="movefile.obj?id=${media.media_package_id}"/>"><img src="resources/images/publish.png" width="50px" height="30px" alt="Publish"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="<c:url value="previewvideo.obj?name=${media.media_package_name}&&id=${media.media_package_id}"/>"><img src="resources/images/preview.jpg" width="50px" height="30px" alt="Preview"/></a></td>
			<%-- <td width="16%" valign="middle" align="left" class="overlayRow"> <a href="<c:url value="previewvideo.obj?name=${media.media_package_name}"/>">Preview</a></td> --%>
			
			
			<td  valign="middle" align="left" class="overlayRow"> 
					<img id="${media.media_package_name}" src="fileServlet/${media.media_package_name}.jpg" width="100px" height="50px"/>
					<script type="text/javascript">jQuery(document).ready(function($) {$("#${media.media_package_name}").addimagezoom({speed: 1500,zoomrange: [3, 3],magnifiersize: [300,150]});});</script>
				
			</td>
</tr>
</c:forEach>


</c:if>

</table></td>
		</tr>
		<tr>
		<td style="padding: 5px; text-align:center" colspan="6" ><a href="#" onclick="submitTitleForm();"><img src="resources/images/Back.png" width="70" height="30"/></a></td>	
		</tr>
		
	</table>
	</form:form>
</div>

<!--<a href="goto.obj">Back to Search</a>

-->
<script type="text/javascript">

function submitTitleForm(){
	document.getElementById("title_form").submit();	
}
 jQuery(document).ready(function($) {
	$('.previewVideo').click(function (){
		
		var video=$(this).attr('id');
		
		
		var newWindow = window.open ("","Video","width=350px,height=250px,left=45px, top=15px, scrollbars=no, menubar=no,resizable=no,directories=no,location=no,Navigation Toolbar=no");
		newWindow.document.close();
		newWindow.document.open();
		newWindow.document.write("<object id='MediaPlayer1' width='330px' height='230px' classid='CLSID:22D6F312-B0F6-11D0-94AB-0080C74C7E95'"+
            "codebase='http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701'"+
            "standby='Loading Microsoft® Windows® Media Player components...' type='application/x-oleobject'>"+
            "<param name='FileName' value='"+video+"' />"+
            "<param name='AutoStart' value='false' />"+
            "<param name='DefaultFrame' value='mainFrame' />"+
            "<param name='ShowStatusBar' value='0' />"+
            "<param name='ShowPositionControls' value='1' />"+
            "<param name='showcontrols' value='1' />"+
            "<param name='ShowAudioControls' value='1' />"+
            "<param name='ShowTracker' value='1' />"+
            "<param name='EnablePositionControls' value='1' />"+

            "<embed  type='application/x-mplayer2' pluginspage='http://www.microsoft.com/Windows/MediaPlayer/'"+
                "src='"+video+"' align='middle' width='350px' height='250px' defaultframe='rightFrame'"+
                " id='MediaPlayer2' />"+

			"</embed>"+
			"</object>"
			);
		
		});
	  
 }); 
	
</script>
			  
