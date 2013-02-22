<%@include file="/WEB-INF/jsp/include.jsp"%>
<script type="text/javascript">
jQuery(document).ready(function() {



	
		$('#adminMenuButton').css({'background-color':'#989898','font-weight': 'bold'});
		$('#metadataManagementSubMenuButton').css({'background-color':'#989898','font-weight': 'bold'});
		
		

		$("#addbtn").click(function() {

			$('#lstbox1 option:selected').each(function(){
	            $(this).remove().appendTo("#lstbox2");
	        });
			
			

		});
		 
		 $('#delbtn').click(function () {
			    $('#lstbox2 option:selected').appendTo("#lstbox1"); // this will move all selected options to left side
			  });
		
		/*('#attribute_form').on('submit', function () {
			    $('#lstbox2 option').prop('selected', true); // set all options on right side to selected
		});*/

		var listBoxOldOptionCount=0,listBoxNewOptionCount=0;
		$('#lstbox2 option').each(function(){
		  listBoxOldOptionCount++;
		});
		//alert('listBoxOldOptionCount:'+listBoxOldOptionCount);
		$('#attribute_form').on('submit', function () {
		listBoxNewOptionCount=0;
				$('#lstbox2 option').each(function(){
		  listBoxNewOptionCount++;
		});
		//alert('listBoxNewOptionCount:'+listBoxNewOptionCount);
				if(listBoxOldOptionCount>=listBoxNewOptionCount){
					//alert("Please Select atleast one attribute");
					$('#attributeSuccess').hide();
					document.getElementById('attributeError').innerHTML='Please Select atleast one attribute';
					return false;
				}else{
			    $('#lstbox2 option').prop('selected', true); // set all options on right side to selected
			    	return true;
			    }
		});

	});
</script>

<h3 align="center">Add Attribute</h3>

	<form:form action="saveAttribute.obj"
		modelAttribute="metadatavendorassoc" method="post" id="attribute_form">
		<div id="titleForm" class="leftInnerDivStyle" align="center">
			<div class="subItemDetails">
			<span style="color: green" id="attributeSuccess">${addSuccessMessage }</span>
			<span style="color: red">${errorMessage }</span>
			<span style="color:red" id="attributeError"></span>
				<table border="0" bordercolor="black">
					<tr>
						<td><form:select path="master_metadata_id" multiple="true"
								size="18" id="lstbox1">
								<c:forEach items="${lstMasterMetadata}" var="master">
									<form:option value="${master.master_metadata_id}">${master.master_metadata_name}</form:option>
								</c:forEach>
							</form:select></td>
						<td><input type="button" id="addbtn" value=">>"></td>
						<td><input type="button" id="delbtn" value="<<<"/></td>
						<td><form:select path="metadataList" multiple="true" size="18"
								id="lstbox2" name="lstbox2">
								<c:forEach items="${listVendorMasterMetadata}" var="vendor">
									<form:option value="${vendor.master_metadata_id}">${vendor.master_metadata_name}</form:option>
								</c:forEach>
							</form:select>
						</td>
					</tr>
					<tr>
					<tr>
				</table>
			</div>		
		<div  align="center">
			<table>
				<tr>
					<td><a href="#" onclick="submitForm();"><input type="image" src="resources/images/Submit.png" /></a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	</form:form>
