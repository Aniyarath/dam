<link rel="stylesheet" href="resources/css/css/dynamicWF.css" />
<%@include file="/WEB-INF/jsp/include.jsp"%>
<style>
#resizable {
	width: 350px;
	height: 70px;
	padding: 0.5em;
	margin-left: 30px;
}

#resizable h3 {
	text-align: center;
	margin: 0;
}
</style>

<!--  

<script src="http://code.jquery.com/jquery-1.8.3.js"></script>

<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.21/jquery-ui.min.js"></script>
-->

<script>

function disableTextBox()
{
	var value=document.getElementById("packageNames").value;
	alert(value);
	if(value!=0)
	{
		
				
				document.getElementById("mediaName").disabled=true;
	}
	else
	{
		document.getElementById("mediaName").disabled=false;
	}

	
}

$(function() {
	
	$('#listB').sortable({axis:'x',cancel: "li.fixed",cursor: 'pointer',stop:function(event, ui) {
        //nothing to do for #f0 as you can't sort anything above it
       
        var f5 = $("#INGEST");
       
        var indf5 = $("#listB li").index(f5);
       
        //if f5 not in right position -> swap position
        if(indf5 !== 0) {
            if(indf5 > 0) {
                f5.prev().insertAfter(f5); //move it up by one position
            } else {
                f5.next().insertBefore(f5); //move it down by one position
            }
        }
    }});
  
 });  
  
$().ready(function(m) {
  $("#listA li").click(function() {
	 
	  temp = $(this).attr('id');
	  alert(temp);
		index = $(this).index();
		if (index == 0) {
			imgsrc = "resources/images/QC2.JPG";
		} else if (index == 1) {
			imgsrc = "resources/images/DRM2.JPG";
		}
		$('#listB').append('<li id='+temp+' class='+temp+'>'+ '<img src='+imgsrc+'/>' + ''+ '<a class="delete" href="#">'+ '' + '</a>' + '</li>');
	  });
  });
   
  
  
/*   $(function() {
	  $( "button" )
	  .button()
	  .click(function( event ) {
		  
		  $.each($("#listB").children('li'), function() {
			 alert($(this).index()+'   '+ $(this).text() ) ;
			 var strSequence = "123";
			 $('#dynamicWF').load("/dynamicWFSuccess?strSequence"+strSequence);
			 alert("strSequence"+strSequence);
			 $('#dynamicWF').submit();
		  });
	  event.preventDefault();
	  });
	  }); */
  
$(function() {

	var listA = $("#listA li").map(function() { return $(this).attr('id') }).get();
	for(var i=0; i<listA.length;i++){
	  		$('.'+listA[i]+'').remove();
	  	}
	  var strSequence='';
	  $( ":button" ).click(function( event ) {
  	  $.each($("#listB").children('li'), function() {
	 		var text = $(this).attr('id');
	 		alert("workflow"+text);
			strSequence = strSequence + text + ",";
		  });
  	  	document.getElementById('strSequence').value = strSequence;
  	    alert("after sequence-"+strSequence);
  		event.preventDefault();
  	 	document.getElementById('dynamicWF').submit();
  	 	
	  });
	  
	  $(".fpd-product-selection").hide();
	  $('#defaultWorkFlow').attr( "checked", "checked" );
	  $('#defaultWorkFlow').click(function (){
	  	$(".fpd-product-selection").hide();
	  	for(var i=0; i<listA.length;i++){
	  		$('.'+listA[i]+'').remove();
	  	}
	  });
	  $('#customWorkFlow').click(function (){
	 
	  	$(".fpd-product-selection").show();	  
	  });
	  
});


  
   $('a.delete').live('click', function(){ $(this).closest('li').remove(); });
  



   jQuery(document).ready(function($) {


	   $('#vendorName').change(function(){
		   
			 $.ajax( {

				 
					type : 'get',
					url : '/DAMUX/getAllPackages',
					async : false,
					//dataType : 'json',
					data:{vendorName:${'vendorName'}.value},
					
					success:function(response) {

				    	
					   var availableTags=response;
					$( "#packageNames" ).html( availableTags);

				    	
				    		
				    		  
					}
				
			 });
		
	});





	   



	   


   });



   
 	
</script>


<span style="color:green">${updateSuccessMessage }</span>
<span style="color:green">${insertSuccessMessage }</span>
<span style="color:red">${errorMessage }</span>
<form id="dynamicWF" name="dynamicWF" action="dynamicWFSuccess">

<input type="hidden" name="strSequence" id="strSequence" />
<div id="main-container" class="container">
<div id="content">
<div id="examples">

<div>
<div id="clothing-designer" class="fpd-container clearfix">

<!--<div class="fpd-product-selection">
<ul id="listA" class="connectMe ui-sortable">
	<li>QA</li>
	<li>DRM</li>
</ul>
</div>



--><div class="fpd-product-container">


<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td align="left" valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">


			<tr>
				<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">

					<c:if test="${not empty vendorList}">
						<tr>
							<td width="25%" height="22" class="titleFormLabelStyle">Select
							the Vendor:</td>
							<td width="72%" height="22" class="titleFormFieldStyle"><select
								name="vendorName" id="vendorName">
								<option value="0">SELECT</option>
								<c:forEach items="${vendorList}" var="name">
									<option value="${name}"><c:out
										value="${name.vendorName}" /></option>
								</c:forEach>
							</select></td>

						</tr>
					</c:if>
					<tr>
						<td width="25%" height="32" class="titleFormLabelStyle">Package
						Names</td>

						<td width="72%" height="32" class="titleFormFieldStyle"><select
							id="packageNames" name="packageNames" onchange="disableTextBox()"
							style="max-width: 210px; height: 18px;">
							<option value="0">SELECT</option>
							<c:forEach items="${packageNames}" var="packageName">

								<option><c:out
									value="${packageName.media_package_name}" /></option>
							</c:forEach>
						</select></td>
					</tr>









					<tr>
						<td width="25%" height="32" class="titleFormLabelStyle">Add
						Media Package:</td>

						<td width="72%" height="32" class="titleFormFieldStyle"><input
							type="text" name="mediaName" id="mediaName" /></td>
					</tr>

					<tr>


						<td width="25%" height="32" class="titleFormLabelStyle"><input
							type="radio" value="defaultWorkFlow" name="workFlow"
							id="defaultWorkFlow" />&nbsp;&nbsp;Default Work Flow</td>

						<td><input type="radio" value="customWorkFlow"
							name="workFlow" id="customWorkFlow" />Custom Work Flow</td>

					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

</div>

<div id="resizable" class="fpd-element fpd-editable"
	style="left: 70px; top: 60px; z-index: 1; 
							transform: rotate(0deg); width: 580px; height: 140px; padding-top: 20px"
	title="Remove">
<ol id="listB">
	<li id="INGEST" class="fixed"><img src="resources/images/Ingest.JPG" /></li>
	<li class="fixed" id="ENCODE"><img src="resources/images/Transcode.JPG" /></li>
	<li class="fixed" id="PUBLISH"><img src="resources/images/Publish.JPG" /></li>

</ol>
</div>

</div>

<div class="fpd-product-selection">
						<ol id="listA" >
							<li id="QA"><img src="resources/images/QC.JPG" /></li>
							<li id="DRM"><img src="resources/images/DRM.JPG" /></li>
						</ol>
					</div>
<div style="padding-left: 300px; padding-top: 10px">
<button>Save Changes</button>
</div>
</div>
</div>
</div>
</div>

</form>

