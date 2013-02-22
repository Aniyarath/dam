<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.apache.velocity.runtime.directive.Include"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link href="resources/css/sideMenu.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>


<div class="contentContainer">
<span style="color: red">${errorMessage}</span>
<div class="videoPreviewContainer">

	<c:choose>
	<c:when test="${lowresfile==''||empty lowresfile}">
	<img src="fileServlet/<c:out value='${imagefile}'/>" height="240" width="320"></img>
	</c:when>
	<c:otherwise>
	<video class="mejs-wmp" width="320" height="240" id="player2" poster="fileServlet/<c:out value='${imagefile}'/>" controls="controls" preload="none">
	<!-- Only firfox and chrome support available for this format. -->
	<source type="video/ogg" src="fileServlet/<c:out value='${lowresfile}'/>" />
	</video>
	</c:otherwise>
	</c:choose>	

</div>
<div class="menuAndTableContainer">
<div class="falseMenuContainer"><div id="de-side-nav" class="open">
<ul id="assetmenu" class="sidebar-list">
<li class="menuHeading">
<div class="headingText">${packageName}</div>
<div id="selectedMenu" class="text">/Preview</div>
</li>
<li class="divider"></li>
<li id="detaileditor-viewmediaviewer" class=" current viewmediaviewer">
<a class="ajax tooltip"  href="<c:url value="previewvideo.obj?name=${sessionScope.packageName}&&id=${sessionScope.packageId}"/>" title="Preview">
<span class="icon"></span>
<span class="text">Preview</span>
</a>
</li>

<li id="detaileditor-viewmetadatageneral" class=" viewmetadatageneral">
<a id="editMetadataLink" class="ajax tooltip" title="Edit Metadata" href="#">
<span class="icon"></span>
<span class="text">Edit Metadata</span>
</a>
</li>
<li id="detaileditor-comment" class=" comment">
<a class="ajax tooltip"  href="#" title="Comments (0)">
<span class="icon"></span>
<span class="text">Comments (0)</span>
</a>
</li>


<li id="detaileditor-editassettags" class=" editassettags">
<a class="ajax tooltip"  href="#" title="Tags">
<span class="icon"></span>
<span class="text">Tags</span>
</a>
</li>

<li id="detaileditor-viewmap" class=" viewmap">
<a class=" tooltip"  href="#" title="User Action">
<span class="icon"></span>
<span class="text">User Action</span>
</a>
</li>

<li id="detaileditor-assetpermissions" class=" assetpermissions">
<a class="ajax tooltip"  href="#" title="Permissions">
<span class="icon"></span>
<span class="text">Permissions</span>
</a>
</li>

<li class="divider"></li>
<li id="opener" class="open">
<a class="tooltip propertyset" propertyname="expand_media_sidebar" propertyvalue="true" title=" Expand ">
<span class="icon"></span>

<span class="text">Collapse</span>
</a>
</li>
</ul>
</div>
</div>
	<div id="tableOuterDiv">
	
	
	
	<h1 class="heading">METADATA - ${packageName}</h1>
	<div  class="tableInnerDiv " style="display: block;">
	<c:set var="count" value="1" scope="page" />
		<table class="tableForm zebraTable" cellspacing="0" cellpadding="0">
					<tbody>
					<c:forEach items="${vendorList }" var="vendor">
					
						<c:choose>
							<c:when test="${vendor.metadata_attr_type=='Text Box' }">
									<tr>
									<th>${vendor.master_metadata_name}:</th>
									<c:choose>
										<c:when test="${count gt 15}">
										<td class="padded" colspan="2">
										</c:when>
										<c:otherwise>
										<td class="padded">
										</c:otherwise>
									</c:choose>
									<c:forEach
										items="${metaDataModel.metadataMap }" var="metadataMap">
										<c:choose>

											<c:when
												test="${vendor.master_metadata_id == metadataMap.key}">
							     
							      ${metadataMap.value}
							      
							      			</c:when>
										</c:choose>
									</c:forEach>
									</td></tr>
							</c:when>

							<c:when
								test="${vendor.metadata_attr_type=='Select Box'&& vendor.master_metadata_id == metadataMap.key  }">
								<c:forEach items="${valuemodel }" var="valueMap">
									<c:choose>
										<c:when test="${vendor.master_metadata_id==valueMap.key}">
											<tr><th>${vendor.master_metadata_name}:</th>
											<c:choose>
										<c:when test="${count gt 15}">
										<td class="padded" colspan="2">
										</c:when>
										<c:otherwise>
										<td class="padded">
										</c:otherwise>
									</c:choose>
											${metadataMap.value}</td></tr>
												
										</c:when>
									</c:choose>
								</c:forEach>
							</c:when>
							<c:when
								test="${vendor.metadata_attr_type=='Check Box'&& vendor.master_metadata_id == metadataMap.key}">
								<c:forEach items="${valuemodel }" var="valueMap">
									<c:choose>
										<c:when test="${vendor.master_metadata_id==valueMap.key}">
											
												<th>${vendor.master_metadata_name}:</th>
												<c:choose>
										<c:when test="${count gt 12}">
										<td class="padded" colspan="2">
										</c:when>
										<c:otherwise>
										<td class="padded">
										</c:otherwise>
									</c:choose>
												${metadataMap.value}</td>
											
										</c:when>
									</c:choose>
								</c:forEach>
							</c:when>
						</c:choose>
						
		<c:set var="count" value="${count + 1}" scope="page"/>				
</c:forEach>
</tbody>
</table>
</div>
</div>
<br style="clear:both;"/>
</div>
</div>
<a href="showfiles.jsp">Home Page</a>
</body>
<script>

$('audio,video').mediaelementplayer();

</script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#tableOuterDiv table tr:even').addClass('even');
		/*$('.tableOuterDiv table tr').hover(function(){
    		$(this).removeClass('even');
    			$(this).addClass('hover');
		}, function() {
			$(this).removeClass('hover');
			$('.tableOuterDiv table tr:even').addClass('even');
		});*/
		$('<td rowspan="12" id="videoContainerCell"></td>').insertAfter('#tableOuterDiv table tr:first td');
		$('.videoPreviewContainer').appendTo('#videoContainerCell');
		
		//alert("Hi I am from jquery");
$('#opener').click(function() {
	//alert("I am clicking opener");
	if ($(this).hasClass('open') && $('#de-side-nav').hasClass('open')) {
		$('#de-side-nav').removeClass('open');
		$('#de-side-nav').addClass('closed');
		$('.text').hide();
		$(this).removeClass('open');
		$(this).addClass('closed');
		var dtw = $('#details-title').width();
		dtw = dtw + 108;
		$('#details .tableholder').width(dtw);
		
		$('#selectedMenu').hide();
		
	} else {
		$('#selectedMenu').show();
		$('#de-side-nav').removeClass('closed');
		$('#de-side-nav').addClass('open');
		$(this).removeClass('closed');
		$('.text').show();
		$(this).addClass('open');
		var dtw = $('#details-title').width();
		dtw = dtw - 106;
		$('#details .tableholder').width(dtw);
	}
});

	$('#assetmenu li').click(function() {
		if ($(this).attr('id') != 'opener') {
		if(!$(this).hasClass('menuHeading')){
		$('#de-side-nav li').removeClass('current');
		$(this).addClass('current');
		var headingText=$('.text',this).text();
		$('#selectedMenu').remove();
		$('<br/><p id="selectedMenu" class="text">/'+headingText+'</p>').insertAfter('.headingText');
		if(!$('#de-side-nav').hasClass('closed')){
		    $('#selectedMenu').show();
		}else{
		$('#selectedMenu').hide();
		}
		}}
	}); 

	$('#assetmenu li').hover(function(){
				if(!$(this).hasClass('menuHeading')){
    			$(this).addClass('sideMenuHover');
    			}
		}, function() {
		    if(!$(this).hasClass('menuHeading')){
			$(this).removeClass('sideMenuHover');
			}
		});
	$('#editMetadataLink').click(function(){
	$('#de-side-nav li').removeClass('current');
		$(this).parent().addClass('current');
		var headingText=$('.text',this).text();
		$('#selectedMenu').remove();
		$('<br/><p id="selectedMenu" class="text">/'+headingText+'</p>').insertAfter('.headingText');
		if(!$('#de-side-nav').hasClass('closed')){
		    $('#selectedMenu').show();
		}else{
		$('#selectedMenu').hide();
		}
		
		$.ajaxSetup ({
			cache: false
		});
		
		
		//e.preventDefault();
		$.ajax( {
			type : 'get',
			url : '${pageContext.request.contextPath}/'+'editmetadataAjax',
			async : false,			
			error :  function(data, textStatus) {
		        if (data.redirect) {
		            // data.redirect contains the string URL to redirect to
		            window.location.href = data.redirect;
		        }
		        else {
		            // data.form contains the HTML for the replacement form
		            $('body').html(data);
		        }
		    },
			success:function(response) {
		    	$('#tableOuterDiv').html(response);
		    		
		    		  
			}
		});
	
	
	});
	
});
		
	
</script>

<script type="text/javascript">
/* <![CDATA[ */
( function( $ ) {
   $( 'a[href="#"]' ).click( function(e) {
      e.preventDefault();
   } );
} )( jQuery );
/* ]]> */
</script>
</html>