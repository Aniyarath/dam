<%@include file="/WEB-INF/jsp/include.jsp"%>


<table border="1" style="border-collapse:collapse;">
<tr>
<td>
<div id="home" style="width:100%;">

		<div class="InnerLeftContainer"><!--InnerLeftContainer Start-->
        	<div class="InnerBoxMainCotainer"><!--InnerBoxMainCotainer Start-->
            	<div class="InnerBoxLeftCotainer"><!--InnerBoxLeftCotainer Start-->
					<div class="InnerBoxSmlTop"><span class="InnerBoxTitle">NO OF INGEST</span></div>
					<div class="InnerBoxSmlMid"><!--InnerBoxSmlMid Start-->
						<%-- <sec:authorize access="hasRole('TERRITORY_USER')"> --%>
							<img src="resources/images/Img01.jpg" border="0" style="padding-left:10px"/>
					<%-- 	</sec:authorize> 
						<sec:authorize access="hasRole('REGION_USER')">
							<img src="resources/images/Img01_RO.jpg" border="0" style="padding-left:10px"/>
						</sec:authorize>
						<sec:authorize access="hasRole('HEAD_OFFICE_USER_ROL')">
							<img src="resources/images/Img01_HO.jpg" border="0" style="padding-left:10px"/>
						</sec:authorize>
						--%>
					</div><!--InnerBoxSmlMid End-->
					<div><img src="resources/images/InnerBoxSmlBot.jpg" border="0" /></div>
				</div><!--InnerBoxLeftCotainer End--> 
            <div class="InnerBoxRightCotainer"><!--InnerBoxRightCotainer Start--> 
				<div class="InnerBoxSmlTop"><span class="InnerBoxTitle">NO OF PUBLISH</span></div>
                <div class="InnerBoxSmlMid"><!--InnerBoxSmlMid Start-->
                
					<%-- <sec:authorize access="hasRole('TERRITORY_USER')"> --%>
						<img src="resources/images/Img02.jpg" border="0" style="padding-left:10px"/>
					<%-- </sec:authorize>
					<sec:authorize access="hasRole('REGION_USER')">
						<img src="resources/images/Img02_RO.jpg" border="0" style="padding-left:10px"/>
					</sec:authorize>
					<sec:authorize access="hasRole('HEAD_OFFICE_USER_ROL')">
							<img src="resources/images/Img02_HO.jpg" border="0" style="padding-left:10px"/>
					</sec:authorize> --%>
				</div><!--InnerBoxSmlMid End-->
                <div><img src="resources/images/InnerBoxSmlBot.jpg" border="0" /></div>
            </div><!--InnerBoxRightCotainer End-->
            <div class="ClearFloat"></div>            
			</div><!--InnerBoxMainCotainer End-->
			<div class="InnerBoxBigTop">
				<span class="InnerBoxTitle">TRANSCODE STATUS</span>
				<!--<sec:authorize access="hasRole('TERRITORY_USER')">
					<span class="InnerBoxTitle">TOTAL COST - ICE AGE 5 - TERRITORY WISE COMPARISON</span>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('HEAD_OFFICE_USER_ROL','REGION_USER')">
					<span class="InnerBoxTitle">TITLE ESTIMATION STATUS DASHBOARD</span>
				</sec:authorize>
			-->
			
			</div>
            <div class="InnerBoxBigMid"><!--InnerBoxBigMid Start-->
				<%-- <sec:authorize access="hasRole('TERRITORY_USER')"> --%>
					<img src="resources/images/Img03.jpg" border="0" style="padding-left:10px"/>
				<%-- </sec:authorize>
				<sec:authorize access="hasRole('REGION_USER')">
					<img src="resources/images/Img03_RO.jpg" border="0" style="padding-left:10px"/>
				</sec:authorize>
				<sec:authorize access="hasRole('HEAD_OFFICE_USER_ROL')">
							<img src="resources/images/Img03_HO.jpg" border="0" style="padding-left:10px"/>
				</sec:authorize> --%>
            </div><!--InnerBoxBigMid End-->
            <!--<div><img src="resources/images/InnerBoxBigBot.jpg" border="0" /></div>

			<div class="InnerBoxBigTop"><span class="InnerBoxTitle">Recently Ingested Packages</span></div>
            <div class="InnerBoxBigMid" style="padding-left: 10px;">InnerBoxBigMid Start
				<birt:viewer id="birtViewer"  reportDesign="ffe_film_estimate_cost.rptdesign" baseURL="http://localhost:8080/birt" pattern="frameset" height="350" width="715" format="html" title="Film Estimate - ${recenttitlename}"  frameborder="yes" style="bgcolor:black" showNavigationBar="no" showToolBar="no">
				<birt:param name="film_estimate_id" value="${filmestimateid}"></birt:param>
				</birt:viewer>
            </div>InnerBoxBigMid End
            <div><img src="resources/images/InnerBoxBigBot.jpg" border="0" /></div>

			

		--></div>
</div>
</td>
</tr>
</table>
  <script type="text/javascript">
 $(document).ready(function(){
 $('#homeMenuButton').css({'background-color':'#989898','font-weight': 'bolder'});
 });
 </script>