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

                                                	var userName=document.getElementById("userName").value;
                                                	var password=document.getElementById("password").value;
                                                	clearIdErrorMessage();
                                                	clearPasswordErrorMessage();
                                                	if(userName=='')
                                                	{
                                                    	
                                                    	document.getElementById('loginIdError').innerHTML='Please enter login id';
                                                	}
                                                	if(password=='')
                                                	{
                                                		document.getElementById('loginError').innerHTML='Please enter password';
                                                	}
                                                	
                                                	if((userName!='')&&(password!=''))
                                                	{
                                                                document.getElementById("tiles_person_form").submit()
                                                	}
                                                }
                                                function doreset(){
                                                                document.getElementById("tiles_person_form").reset()
                                                }
                                               function clearIdErrorMessage(){

                                            	   document.getElementById("errorSpan").innerHTML="";
                                            	   document.getElementById('loginIdError').innerHTML="";
                                            	   
                                                }
                                               function clearPasswordErrorMessage(){

                                            	   document.getElementById('loginError').innerHTML="";
                                               }
                                </script>                                              
                                <link href="./resources/css/login_page_style.css" rel="stylesheet" type="text/css" />                                                                                                                 
                                <link href="resources/css/style.css" rel="stylesheet" type="text/css" />
                                <link href="resources/css/newThemeStyle.css" rel="stylesheet" type="text/css" />                      
                                <link href="resources/css/dbstyle.css" rel="stylesheet" type="text/css" />
                                <link href="resources/flexigrid/css/flexigrid.css" rel="stylesheet" type="text/css" />     
                    <link href="resources/flexigrid/css/flexigrid.pack.css" rel="stylesheet" type="text/css" />      
                    <link href="resources/css/jquery-ui-1.9.0.custom.min.css" rel="stylesheet" type="text/css" />            
                </head>              
                <body>
                
<div id="MainContainer"><!--MainContainer Start-->
                <div class="BannerBgContainer1"><!--BannerBgContainer Start-->
                                <div id="headerid">
                                                <div class="clearBoth" ></div>
                                                                <div class="BannerBgContainer1"><!--BannerBgContainer Start-->                                                                                                                                                                                                                                                            
                                                                </div><!--BannerBgContainer End-->
                                </div>
    </div><!--BannerBgContainer End-->   
    <div class="InnerContentMain"><!--InnerContentMain Start-->
	
     <div id="InnerContentMainContainer" style="border: 1px solid #EDEDED; margin-top:55px;"><!--InnerContentMainContainer start-->
          
		   <div class="loginWelcomeMessage">Welcome to DIGITAL ASSET MANAGEMENT SYSTEM</div>
		  
		
			<!--<div class="leftLoginContainer" style="float:left; ">
				<img name="featureImage" id="featureImage" alt="fox" src="resources/images/Fox Login_02.jpg" complete="complete"/>
			</div>
			-->
			
			<div class="rightLoginContainer" style="float:right; ">
				
			
				<div class="rightUpContainer" >					
					<div class=""><h3 >Login</h3></div>
					
					<form:form  action="home.obj?id=username" method="get" modelAttribute="userProfile"  id="tiles_person_form">
									<div class="PreLoginLbl">Login Id</div>
				
						<div class="PreLoginFld"><input name="username" type="text" class="PreLoginTextBox" id="userName" onfocus="clearIdErrorMessage()"/></div>
						
						<span id="loginIdError" style="color:red"></span>
						<div class="ClearFloat"></div>
						<div class="PreLoginLblHeight"></div>
						<div class="PreLoginLbl">Password</div>
						<div class="PreLoginFld">
							<input name="password" type="password"   class="PreLoginTextBox" id="password" onfocus="clearPasswordErrorMessage()"/> 
						</div>
	
						<span id="loginError" style="color:red"></span>
						<span style="color: red" id="errorSpan">${errorMessage }</span>
						<div class="ClearFloat"></div>
						<!-- <input type="submit" value="Login" align="middle"/> -->
						<div style="float: right;"><button class="buttonCssStyle" type="button" onclick="dosubmit()">Login</button></div>
						
						<div class="PreLoginLblEmpty" style="visibility:hidden"><img src="./resources/images/Spacer.png" border="0" width="46px" height="1px"/></div>

													<div class="LoginCheckBox"><input name="input2" type="checkbox" value="" /></div>
									<div class="LoginCheckFld">Remember me</div>
																<div style="visibility: hidden;">
						<input type="submit"/>
						
				
			</form:form>
				</div>
				
				
			
			
			

		  <hr style="border: 1px solid rgb(188, 188, 188);">
				
				<div class="rightDownContainer" >
						<div class="help">
							<h3 style="color: rgb(60, 60, 60);">NEED HELP?</h3>
							<a href='#'  >Sign Up Now!</a> <br />
							<a href='#'   >DAM&nbsp;&nbsp;FAQs </a> <br />
							<a href='#'  >Forgot your User ID?</a> <br />
							<a href='#'  >Modify Your Account</a> <br />


						</div>
				</div>
			</div>
		  
		  
		  
		  
		  
		  
		  
		  
		  
           
        </div><!--InnerContentMainContainer end-->                          
                </div><!--InnerContentMain End-->
  <div class="FooterContainer"><!--FooterContainer Start-->
    <div></div>
        <div class="FooterLinkContainer"></div>
     </div><!--FooterContainer End-->
     <div class="CopyRight"></div>
</div><!--MainContainer End-->
</body>
</html>
