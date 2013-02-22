<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%
   response.setHeader( "Pragma", "No-cache" );
   response.setHeader( "Cache-Control", "no-cache, no-store, must-revalidate" );
   response.setDateHeader( "Expires", -1 );
%>
<html>
                <head>
                                <title>DIGITAL ASSET MANAGEMENT SYSTEM</title>
                                <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">                 
                                <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
                                <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
                                <META HTTP-EQUIV="Expires" CONTENT="-1">
                                <meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate, Post-Check=0, Pre-Check=0">
                                <script type="text/javascript">
                                                function dosubmit(){
                                                                document.getElementById("tiles_person_form").submit()
                                                }
                                                function doreset(){
                                                                document.getElementById("tiles_person_form").reset()
                                                }
                                </script>                                              
                                                                                                                                               
                                <link href="resources/css/style.css" rel="stylesheet" type="text/css" />
                                <link href="resources/css/newThemeStyle.css" rel="stylesheet" type="text/css" />                      
                                <link href="resources/css/dbstyle.css" rel="stylesheet" type="text/css" />
                                <link href="resources/flexigrid/css/flexigrid.css" rel="stylesheet" type="text/css" />     
                    <link href="resources/flexigrid/css/flexigrid.pack.css" rel="stylesheet" type="text/css" />      
                    <link href="resources/css/jquery-ui-1.9.0.custom.min.css" rel="stylesheet" type="text/css" />            
                </head>              
                <body>
                
<div id="MainContainer"><!--MainContainer Start-->
                <div class="BannerBgContainer"><!--BannerBgContainer Start-->
                                <div id="headerid">
                                                <div class="clearBoth" ></div>
                                                                <div class="BannerBgContainer"><!--BannerBgContainer Start-->                                                                                                                                                                                                                                                            
                                                                </div><!--BannerBgContainer End-->
                                </div>
    </div><!--BannerBgContainer End-->   
    <div class="InnerContentMain"><!--InnerContentMain Start-->
Application Encountered an error. Please try again later...${exception.faultMessage}


                </div><!--InnerContentMain End-->
  <div class="FooterContainer"><!--FooterContainer Start-->
    <div><img border="0" src="resources/images/FooterLine.gif" style="width: 1025px;"></div>
        <div class="FooterLinkContainer"><a href="#" class="FooterLink">Home </a> | <a href="#" class="FooterLink">TERMS &amp; CONDITIONS</a> | <a href="#" class="FooterLink">PRIVACY POLICY</a> | <a href="#" class="FooterLink">SITE MAP</a>
     | <a href="#" class="FooterLink">contact us</a> | <a href="#" class="FooterLink">help</a></div>
     </div><!--FooterContainer End-->
     <div class="CopyRight">COPYRIGHT <span class="Copy">©</span> 2012. ALL RIGHTS RESERVED. </div>
</div><!--MainContainer End-->
</body>
</html>
