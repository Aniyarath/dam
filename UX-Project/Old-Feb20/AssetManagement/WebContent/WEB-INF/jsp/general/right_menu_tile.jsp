<%@include file="/WEB-INF/jsp/include.jsp"%>
<script>
function submitTitleSearchForm(){
	var packageName=document.getElementById("packages").value;
	if(packageName=='')
	{
		document.getElementById("packageError").innerHTML="Please Enter Package Name";
		//alert("Please Enter Package Name");
	}
	else
	{
		document.getElementById("packageError").innerHTML="";
	document.getElementById("search_title_form").submit();
	}	
}

function clearErrorMessage(){

	document.getElementById("packageError").innerHTML="";
	
}
</script>

 <style>
.ui-autocomplete {
max-height: 73px;
max-width:234px;
overflow-y: auto;
/* prevent horizontal scrollbar */
overflow-x: hidden;
}
/* IE 6 doesn't support max-height
* we use height instead, but this forces the menu to always be this tall
*/
* html .ui-autocomplete {
height: 100px;
}
</style>
<div class="rightInnerDivStyle">
	<div class="InnerBoxRgtSmlTop">
		<span class="InnerBoxTitle">RECENT PACKAGES</span>
	</div>
	<div class="InnerBoxRgtSmlMid">
		<table id="recenttitles">
			<tbody>
			
				<c:forEach items="${packageSideList}" var="package"	varStatus="itrStatus">
					<tr>
						<td><a href="loadmetadata.obj?id=${package.media_package_id}&package=${package.media_package_name}">${package.media_package_name}</a>
						</td>
						<td>${package.masterStatus.statusName}</td>
					</tr>
				</c:forEach>


			</tbody>
		</table>
	</div>
	<div>
		<img src="resources/images/InnerBoxSmlRgtBot.jpg" border="0" />
	</div>
</div>
	
	 <div class="rightInnerDivStyle" style="padding-top: 15px;">
	<form:form action="./searchpackages" id="search_title_form"	method="POST">



		<div class="InnerBoxRgtSmlTop">
		<div class="ui-widget">
			<span class="InnerBoxTitle">
			<label for="packages">PACKAGE SEARCH </label>
			</span>
			</div>
		</div>



		<%-- <form:errors path="*" cssClass="error"  element="div"/> --%>

	<div class="InnerBoxRgtSmlMid" style="padding-left: 10px;">
			<div class="clearBoth" style="padding-top: 10px;">
				<div class="floatLeft">

					<div class="txtBg">
					
						<div class="ui-widget">
							<input type="text" size="32" class="imgSearchEfx"
							name="packageName"  id="packages" onfocus="clearErrorMessage();"/>
						</div>
						
					</div>

				</div>

				<div class="floatLeft">
					<a href="#" onclick="submitTitleSearchForm();"><img border="0"
						src="resources/images/searchIcon.jpg">
					</a>
				</div>
				<span id="packageError" style="color: red"></span>
				
			</div>

		
</div>


		

		<div>
			<img src="resources/images/InnerBoxSmlRgtBot.jpg" border="0" />
		</div>

	</form:form>
</div>

	<div class="rightInnerDivStyle" style="padding-top: 15px;" >
		<div class="InnerBoxRgtSmlTop">
		<span class="InnerBoxTitle">MY TASKS</span>
		<span style="float:right; padding:10px; line-height:30px"><img src="resources/images/statusCircles.jpg" border="0" /></span>
		</div>	
		 <div class="InnerBoxRgtSmlMid">
		<table id="mytasks">
		<tbody>
					<c:forEach items="${homeTasks}" var="task" varStatus="itrStatus">
						
						
						<tr>
						<c:choose>
                           <c:when test="${task.taskSummary.status == 'Created' || task.taskSummary.status == 'Ready'}">
                                 <td><h4 style="background-color:#6A6869;">&nbsp;</h4></td>
                           </c:when>
                           <c:when test="${task.taskSummary.status == 'Reserved'}">
                                 <td><h4 style="background-color:#4B8F54;">&nbsp;</h4></td>
                           </c:when>
                           <c:when test="${task.taskSummary.status == 'InProgress' }">
                                 <td><h4 style="background-color:#ff7F27;">&nbsp;</h4></td>
                           </c:when>
                           <c:when test="${task.taskSummary.status == 'Suspended' }">
                                 <td><h4 style="background-color:#ff1717;">&nbsp;</h4></td>
                           </c:when>
                        </c:choose>
						<td>
						${task.packageName}
						</td>
						<td>
						${task.taskSummary.actualOwner.id}
						</td>
						<td>
						<fmt:formatDate value="${task.taskSummary.createdOn}" type="date" dateStyle="SHORT" />
						</td>
						</tr>
						
					</c:forEach> 
		</tbody>
		</table>
		</div> 
		<div><img src="resources/images/InnerBoxSmlRgtBot.jpg" border="0" /></div>
	</div>

	
	
	
	<div class="rightInnerDivStyle" style="padding-top: 15px;">
			<div class="InnerBoxRgtSmlTop"><span class="InnerBoxTitle">Quick Links</span></div>
			<div class="InnerBoxRgtSmlMid"><!--
				<table id="recenttitles">
			<tbody>
			
					<tr>
						<td><a href="loadmetadata.obj?id=${package.media_package_id}&package=${package.media_package_name}">${package.media_package_name}</a>
						</td>
						<td>${package.masterStatus.statusName}</td>
					</tr>
				
				</tbody>
				</table>
				
				--><div class="quickLinksAction"><a href="#" class="linkText">Request Title Addition</a></div>
						<div class="quickLinksAction"><a href="#" class="linkText">Reports</a></div>
						<div class="quickLinksAction"><a href="#" class="linkText">Review Plan-Estimate-Forecast</a></div>
						<div class="quickLinksAction"><a href="#" class="linkText">Vendor Management</a></div>
						<div class="quickLinksAction"><a href="#" class="linkText">Exchange Rate</a></div>
						<div class="quickLinksAction"><a href="#" class="linkText">Holiday Plan</a></div>
					<sec:authorize access="hasAnyRole('HEAD_OFFICE_USER_ROL','REGION_USER')">
						<div class="quickLinksAction"><a href="#" class="linkText">Request Film Estimates</a></div>
						<div class="quickLinksAction"><a href="#" class="linkText">Reports</a></div>
						<div class="quickLinksAction"><a href="#" class="linkText">Exchange Rate</a></div>
						<div class="quickLinksAction"><a href="#" class="linkText">Add Title</a></div>
						<div class="quickLinksAction"><a href="#" class="linkText">Holiday Plan</a></div>						
					</sec:authorize>
					<sec:authorize access="hasRole('TERRITORY_USER')">
						
					</sec:authorize>
					
				</div>
				
			
			<div><img src="resources/images/InnerBoxSmlRgtBot.jpg" border="0" /></div>

</div>

<!--<script>
$(function() {
var progressbar = $( "#progressbar" ),
progressLabel = $( ".progress-label" );
progressbar.progressbar({
value: false,
change: function() {
progressLabel.text( progressbar.progressbar( "value" ) + "%" );
},
complete: function() {
progressLabel.text( "Complete!" );
}
});
function progress() {
var val = progressbar.progressbar( "value" ) || 0;
progressbar.progressbar( "value", val + 1 );
if ( val < 99 ) {
setTimeout( progress, 100 );
}
}
setTimeout( progress, 3000 );
});
</script>

--><script type="text/javascript"><!--
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

		
		$("#recenttitles").flexigrid({
			dataType: 'json',
			colModel : [
				{display: 'Package Name', name : 'media_package_name', width : 111, sortable : true, align: 'left'},
				{display: 'Status', name : 'media_package_id', width : 120, sortable : true, align: 'left'}
			],
			//sortname: "title_id",
			sortorder: "asc",
			showTableToggleBtn: true,
			width: 265,
			height: 'auto',
			searchitems : [
				{display: 'Package Name', name : 'media_package_name'},
				{display: 'Status', name : 'media_package_id', isdefault: true}
			],	
			resizable: false							
		});


		 $('#packages').focus(function(){
			 
			 $.ajax( {

				 
					type : 'get',
					url : '/DAMUX/getPackages',
					async : false,
					dataType : 'json',
					error :  function(data, textStatus) {
				 alert('Handler for .focus() error.');
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
				    	
					   var availableTags=response;
					$( "#packages" ).autocomplete({
					 source: availableTags
					 });

				    	
				    		
				    		  
					}
				
			 });
		
	});


		 $("#mytasks").flexigrid({
				dataType: 'json',
				colModel : [
					{display: 'Status', name : 'property', width : 30, sortable : true, align: 'left'},
					{display: 'Package', name : 'packageName', width : 90, sortable : true, align: 'left'},
					{display: 'Owner', name : 'Owner', width : 55, sortable : true, align: 'left'},
					{display: 'Date', name : 'Created Date', width : 40, sortable : true, align: 'left'}
				],
				showTableToggleBtn: true,
				width: 265,
				height: 130,
				searchitems : [
					{display: 'Status', name : 'property'}
				]							
			});
	}); 



	
</script>


