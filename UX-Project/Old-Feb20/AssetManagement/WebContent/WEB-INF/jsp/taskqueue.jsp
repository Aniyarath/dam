<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/struts-display.tld" %>

<%
   response.setHeader( "Pragma", "No-cache" );
   response.setHeader( "Cache-Control", "no-cache, no-store, must-revalidate" );
   response.setDateHeader( "Expires", -1 );
%>
<head>
<meta charset="utf-8" />
    <title>jQuery UI Dialog - Default functionality</title>
    <!-- <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
    <script src="/resources/demos/external/jquery.bgiframe-2.1.2.js"></script>
    <script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script> --><!--    
    <script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-1.8.2.js" />"></script>
    <link href="resources/css/jquery-ui-1.9.0.custom.min.css" rel="stylesheet" type="text/css" />	     
	-->
	<script type="text/javascript">
	function doTaskOperation(obj)
	{
		var lstTaskEstIds = ''
		taskOpnName = obj.value;
		taskid = getCheckedValue(document.getElementsByName("taskId")).split(":")[0];
		/* alert(taskid) */
		
		if(taskid != '' && taskid != null)
		{
			var radioObj = document.getElementsByName("taskId");
			/* alert('Radio Obj'+radioObj) */
			var radioLength = radioObj.length;
			/* alert("Length"+radioLength) */
			for(var i = 0; i < radioLength; i++) {
				if(radioObj[i].checked) {
					var checkedValue = radioObj[i].value;
					lstTaskEstIds = lstTaskEstIds + checkedValue + "-"
				}
				else
				{
					alert('Not Checked')
				}
				
			}
		
			if(taskOpnName == 'DOINGEST')
			{
					url = 'doIngestWorkFlowAgain?taskIds='+lstTaskEstIds;
					
			}
			else if(taskOpnName == 'PROCEED NEXT')
			{
				url = 'proceedToNextStep?taskIds='+lstTaskEstIds;
	
			}
			else if(taskOpnName == 'TERMINATE WORKFLOW')
			{
				url = 'terminateDynamicWorkFlow?taskIds='+lstTaskEstIds;
			}
			/* alert(lstTaskEstIds) */
			document.forms[0].action = url;
			document.forms[0].submit();
		}
		else
		{
			alert('Please select a task item')
		}
	}

	function getCheckedValue(radioObj) {
		if(!radioObj)
			return "";
		var radioLength = radioObj.length;
		//alert("Len"+radioLength)
		if(radioLength == undefined)
			if(radioObj.checked)
				//alert("Before Returning");
				{
				//alert("Before Returning");	
				return radioObj.value;
				}
			else
				//alert("Empty Returning")
				return "";

		for(var i = 0; i < radioLength; i++) {
			if(radioObj[i].checked) {
				//alert("It is Checked")
				return radioObj[i].value;
			}
		}
		return "";
	}
</script>
<link  rel="stylesheet" type="text/css" href="./resources/css/taskq.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Task Queue</title>
</head>
<body>
		<form method="post">
		
		<div id="taskOp" height="357">
		Task Operations:
		
		<display:table id="tasktb" name="taskList" pagesize="10" sort="list" requestURI="lstTaskAssinedtoGroup" class="tablesorter" cellpadding="2" cellspacing="2">
		<display:setProperty name="paging.banner.placement" value="bottom" />
		<display:setProperty name="paging.banner.no_items_found" value="No Tasks Found" />
		<display:column title="">
		<input type="checkbox" name="taskId" value="<c:out value="${tasktb.taskSummary.id}"/>"/> 
		</display:column>
		<display:column title="">
		<img src="./resources/images/pdf.png" title="Export as PDF" style="cursor: pointer;"/>
		</display:column>
		<display:column title="">
		<img src="./resources/images/edit.png" title="Detail" style="cursor: pointer;"/> 
		</display:column>
		<display:column title="">
		<img src="./resources/images/historylat.png" title="History" style="cursor: pointer;"/> 
		</display:column>
		<display:column title="">
			<a id="opener" href="#" >
			<img src="./resources/images/jbpm_logo.png" onclick="openDialogueForInitiateFilmEstimate()" title="Current Status"/>
			</a>
		</display:column>
		<display:column property="taskSummary.name" 	sortable="true" title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Task Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"/>
		<display:column property="taskSummary.description" 	sortable="true" title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Description&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"/>
		<display:column property="taskSummary.actualOwner.id" 	sortable="true" title="Actual Owner"/>
		<display:column property="taskSummary.createdOn" 	sortable="true" title="Created Date" format="{0,date,dd-MM-yyyy}"/>
		<display:column property="taskSummary.activationTime" 	sortable="true" title="Activation Date" format="{0,date,dd-MM-yyyy}"/>
		</display:table>
		</div>
		
		
		<div id="taskopnbut" class="taskopnbut"  style="padding-top: 8px;">
		<input type="image" src="./resources/images/claim.png" value="DOINGEST" onclick="doTaskOperation(this)"  title="doIngestWorkFlowAgain">
		<input type="image" src="./resources/images/Complete.png" value="PROCEED NEXT" onclick="doTaskOperation(this)"  title="proceedToNextStep">
		<input type="image" src="./resources/images/reject.png" value="TERMINATE WORKFLOW" onclick="doTaskOperation(this)" title="terminateDynamicWorkFlow">
		
		<!-- <input type="button" value="DOINGEST" onclick="doTaskOperation(this)">
		<input type="button"  value="PROCEED NEXT" onclick="doTaskOperation(this)">
		<input type="button"  value="TERMINATE WORKFLOW" onclick="doTaskOperation(this)">
		 -->
		
		</div>
		
		
		
		<div id="dialog" title="Task Status">
			<div id="taskStatusOverlay" class="jquery-dialog"></div>	
		</div>
		
		<div id="dialog_detailed_estimate" title="Detailed Estimate">
			<div id="filmServicePricingTaskQueueOverlay" class="jquery-dialog"></div>	
		</div>	
		
		</form>
		
	
</body>
<script type="text/javascript">
 $(document).ready(function(){
 $('#taskQueueMenuButton').css({'background-color':'#989898','font-weight': 'bold'});
 });
 </script>