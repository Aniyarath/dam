<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String vendorName=(String)session.getAttribute("vendorName"); %>
	<div class="clearBoth" ></div>
	<div class="BannerBgContainer"><!--BannerBgContainer Start-->
    	<div class="WelcomeUserContainer">Welcome:&nbsp;<%=session.getAttribute("username")%></div>
        <div class="clearBoth"></div>
        <div class="TopLinkContainer">
			<!--<sec:authorize access="hasRole('TERRITORY_USER')">
				<span class="userRole">Territory Manager (&nbsp;<sec:authentication property="principal.territory.name"/>&nbsp;)<span> <img src="resources/images/TopLinkDivLine.gif" border="0" />
			</sec:authorize>
			<sec:authorize access="hasRole('REGION_USER')">
				<span class="userRole">Regional User (&nbsp;<sec:authentication property="principal.region.name"/>&nbsp;)<span> <img src="resources/images/TopLinkDivLine.gif" border="0" />				
			</sec:authorize>			
			<sec:authorize access="hasRole('HEAD_OFFICE_USER_ROL')">				
				<span class="userRole">HO User (&nbsp;<sec:authentication property="principal.headOffice.name"/>&nbsp;)<span> <img src="resources/images/TopLinkDivLine.gif" border="0" />
			</sec:authorize>
			-->
			
			<!-- <a href="#" class="TopLink">My Account</a><img src="resources/images/TopLinkDivLine.gif" border="0" /> -->
			<a class="TopLink" href="<c:url value="/logout"/>">&nbsp;&nbsp;&nbsp;&nbsp;Logout</a></div>
			
        <div class="clearBoth"></div>
    </div><!--BannerBgContainer End-->