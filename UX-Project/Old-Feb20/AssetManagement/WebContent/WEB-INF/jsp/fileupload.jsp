
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<link rel="stylesheet" type="text/css"
	href="./resources/css/createservicetemplate.css"/>
	<script type="text/javascript">

	$(document).ready(function(){

		$(':button').click(function(){
				var fileName=document.getElementById('fileName').value;
				var regex=new RegExp("^.+\.xml$");
				if(fileName!=""){
					if(fileName.match(regex))
					{
						document.getElementById('fileUpload').submit();
					}
					else{

						//alert('Please select an XML File');
						document.getElementById('fileError').innerHTML="Please select an XML File";
					}
				}
				else{
				//	alert('Please select an XML File');
					document.getElementById('fileError').innerHTML="Please select an XML File";
				}
		

			});


	});
	

	</script>
	
	<div id="titleResults" class="resultsLeftInnerDiv">

    <form:form id="fileUpload" modelAttribute="fileUpload" method="post" enctype="multipart/form-data" action="fileupload.obj?id=${vendorName}">
          <span style="color: green">${successMsg }</span> 
             <span style="color: red">${errorMessage }</span> 
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
       <c:if test="${not empty vendorList}">
       <tr>
       <td width="16%" valign="middle" align="left" class="overlayRow"><h4> Select the Vendor:</h4>
       </td>
       <td>
       <select name="vendorName">  
       <c:forEach items="${vendorList}" var="name">  
           <option value="${name}"><c:out value="${name.vendorName}"/></option>  
       </c:forEach>  
    </select>  
       </td>
       
       </tr>
       </c:if>
		<tr>

                <td width="16%" valign="middle" align="left" class="overlayRow"><h4>upload metadata file here</h4></td>
                    <form:label for="file" path="file"></form:label>
                 <td align="center">   <form:input path="file" type="file" id="fileName"/>
                </td>
		
                     <td align="right">    <input type="button" value="Upload" /></td>
                        <c:if test="${empty vendorList}">
                     <td><input type="hidden" name="vendorName" id ="vendorName" value="user"></td>
                     </c:if>
           </tr>   
           <tr>
           <td><span id ="fileError" style="color:red" ></span></td>
           </tr>  
</table>
        
          
        </form:form>
        </div>
   


