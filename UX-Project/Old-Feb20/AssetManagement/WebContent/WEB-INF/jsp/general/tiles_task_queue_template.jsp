<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%
   response.setHeader( "Pragma", "No-cache" );
   response.setHeader( "Cache-Control", "no-cache, no-store, must-revalidate" );
   response.setDateHeader( "Expires", -1 );
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>GLOBAL THEATRICAL SYSTEM</title>
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
						
		<link rel="stylesheet" href="<c:url value="/resources/js/autosuggest/jqueryAutoSuggest.css"/>" type="text/css" media="print, projection, screen" />
		<link href="resources/css/style_film_estimate.css" rel="stylesheet" type="text/css" />
		<link href="resources/css/style.css" rel="stylesheet" type="text/css" />
		<link href="resources/css/newThemeStyle.css" rel="stylesheet" type="text/css" />	
		<link href="resources/css/taskq.css" rel="stylesheet" type="text/css" />			
		<link href="resources/css/dbstyle.css" rel="stylesheet" type="text/css" />
		<link href="resources/flexigrid/css/flexigrid.css" rel="stylesheet" type="text/css" />	
	    <link href="resources/flexigrid/css/flexigrid.pack.css" rel="stylesheet" type="text/css" />	
	    <link href="resources/css/jquery-ui-1.9.0.custom.min.css" rel="stylesheet" type="text/css" />	
	    					
		<style type="text/css">		
		.InnerFormBoxContainer {			
			padding: 10px;
			width: 234px;
		}		
		.AddInnerFormBoxContainer {			
			padding: 10px;
			width: 851px;
		}		
		</style>
</head>
<body>
	<table width="1050" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <th align="left" valign="top" background="resources/images/dbpage_03.png" style="background-repeat:repeat-x"  height="88">
		<div id="headerid">
			<tiles:insertAttribute name="header_home" />
		</div>
	</th>

  </tr>
   <tr>
    <td align="left" valign="middle" background="resources/images/dbpage_12.png" style="background-repeat:repeat-x" height="38">
		<div id='menuid'>
			<tiles:insertAttribute name="menu_home" />
		</div>
	</td>
  </tr>
  <tr>
    <td align="left" valign="top" bgcolor="#EDEDED">

	</td>
  </tr>
  
  <tr>
    <td align="left" valign="top" bgcolor="#FFFFFF">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
<!--         <th width="250" align="left" valign="top" bgcolor="#d2d2d2" scope="col"> -->
<!-- 			<div id='quicklinksid'> -->
<%-- 				<tiles:insertAttribute name="innerLeftContainer_home" /> --%>
<!-- 			</div> -->
<!-- 		</th> -->
<!--         <th width="10" align="left" valign="top" scope="col"><img src="resources/images/dbpage_20.png" width="11" height="357" /></th> -->
        <th width="100%" align="left" valign="top" scope="col">
			<div id="productinfoid">
				<tiles:insertAttribute name="innerRightContainer_home" />
			</div>
		</th>
      </tr>
    </table>
	</td>
  </tr>
  <tr>
		 <div id="footerid">
			<tiles:insertAttribute name="footer_home" />
		</div>
  </tr>
</table>
</body>
</html>