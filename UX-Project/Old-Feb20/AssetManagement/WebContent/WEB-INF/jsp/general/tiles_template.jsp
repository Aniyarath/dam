<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%
   response.setHeader( "Pragma", "No-cache" );
   response.setHeader( "Cache-Control", "no-cache, no-store, must-revalidate" );
   response.setDateHeader( "Expires", -1 );
%>
<html>
	<head>
		<title>Digital Asset Management System</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">		
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="-1">
		<meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate, Post-Check=0, Pre-Check=0">
		
		<script type="text/javascript" src="<c:url value="/resources/js/dojo/dojo.js" />"></script>		
		<script type="text/javascript" src="<c:url value="/resources/js/spring/Spring.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/spring/Spring-Dojo.js" />"></script>
		<%-- <script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-1.7.1.js" />"></script> --%>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-1.8.2.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-ui-1.9.0.custom.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/autosuggest/jquery.autosuggest.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/flexigrid/js/flexigrid.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/flexigrid/js/flexigrid.pack.js" />"></script>
		<%-- <script type="text/javascript" src="<c:url value="/resources/js/multizoom.js"/>"></script>  --%>

		<script type="text/javascript" src="<c:url value="/resources/js/jquery/mediaelement-and-player.min.js" />"></script>
						
		<link rel="stylesheet" href="<c:url value="/resources/js/autosuggest/jqueryAutoSuggest.css"/>" type="text/css" media="print, projection, screen" />
		<link href="resources/css/style_film_estimate.css" rel="stylesheet" type="text/css" />
		<link href="resources/css/style.css" rel="stylesheet" type="text/css" />
		<link href="resources/css/newThemeStyle.css" rel="stylesheet" type="text/css" />		
		<link href="resources/css/dbstyle.css" rel="stylesheet" type="text/css" />
		<link href="resources/flexigrid/css/flexigrid.css" rel="stylesheet" type="text/css" />	
	    <link href="resources/flexigrid/css/flexigrid.pack.css" rel="stylesheet" type="text/css" />	
	    <link href="resources/css/jquery-ui-1.9.0.custom.min.css" rel="stylesheet" type="text/css" />	
	    <link href="resources/css/metadataTablePreviewVideo.css" rel="stylesheet" type="text/css" />
	    <link href="resources/css/mediaelementplayer.css" rel="stylesheet" type="text/css" />
		 <link href="resources/css/mejs-skins.css" rel="stylesheet" type="text/css" />
	</head>	
	<body>
	
<div id="MainContainer"><!--MainContainer Start-->
	<div class="BannerBgContainer"><!--BannerBgContainer Start-->
		<div id="headerid"><tiles:insertAttribute name="header" /></div>
    </div><!--BannerBgContainer End-->
    <div class="TopNavBgContainer"><!--TopNavBgContainer Start-->
		<div id='menuid'><tiles:insertAttribute name="menu" /> </div>
	</div><!--TopNavBgContainer End-->
    <div class="InnerContentMain"><!--InnerContentMain Start-->
		<div id="InnerLeftContainer" >
			<tiles:insertAttribute name="innerLeftContainer" />
		</div>
		
		<div id="InnerRightContainer" style="float: right; width: 267px;">
			<tiles:insertAttribute name="innerRightContainer" />
		</div> 
		<br style="clear:both;"/>
	</div><!--InnerContentMain End-->
  <div class="FooterContainer"><!--FooterContainer Start-->
    <div><img border="0" src="resources/images/FooterLine.gif" style="width: 1039px;"></div>
        <div class="FooterLinkContainer"><a href="#" class="FooterLink">Home </a> | <a href="#" class="FooterLink">TERMS &amp; CONDITIONS</a> | <a href="#" class="FooterLink">PRIVACY POLICY</a> | <a href="#" class="FooterLink">SITE MAP</a>
     | <a href="#" class="FooterLink">contact us</a> | <a href="#" class="FooterLink">help</a></div>
     </div><!--FooterContainer End-->
     <div class="CopyRight">COPYRIGHT <span class="Copy">©</span> 2012. ALL RIGHTS RESERVED. </div>
</div><!--MainContainer End-->
</body>
	
	</html>