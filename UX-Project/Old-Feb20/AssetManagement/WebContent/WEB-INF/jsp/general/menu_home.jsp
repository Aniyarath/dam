

<link rel="stylesheet" href="/resources/css/style.css">

<%String userPermission=(String)session.getAttribute("userPermission");%>
 <div class="TopNavBgContainer"><!--TopNavBgContainer Start-->
    <div class="TopNavLinkContainer">
     <ul class="dropdown">
			<li id="homeMenuButton" style="margin-left: 1px"><a href="./gohome">HOME</a>
			</li>
			<%if(userPermission.equals("ALL_ACCESS")){ %>
			<li id="packageSearchMenuButton"><a href="./displayallpackages" >PACKAGE SEARCH</a>
			</li>
			<%} %>
			<% if(userPermission.equals("USER_ACCESS")){ %>
			<li id="packageSearchMenuButton"><a href="./searchallpackages" >PACKAGE SEARCH</a>
			</li>
			<%} %>
			<li id="taskQueueMenuButton"><a href="./lstTaskAssinedtoGroup" >TASK QUEUE </a>
			</li>
			
			
			
						<li id="ingestMenuButton"><a href="uploadindex" >INGEST </a>
			</li>
			
						<li id="transcodeMenuButton"><a href="#" >TRANSCODE</a>
			</li>
			
			
			
			
			
			<%if(userPermission.equals("ALL_ACCESS")){ %>
						<li id="publishMenuButton"><a href="goto" >PUBLISH </a>
			</li>
			<%} %>
			
			<%if(userPermission.equals("USER_ACCESS")){ %>
						<li id="publishMenuButton"><a href="searchprofiles" >PUBLISH </a>
			</li>
			<%} %>
			
			
			
			
			<%if(userPermission.equals("ALL_ACCESS")){ %>
			<li id="adminMenuButton"><a href="#" >ADMIN</a> 
				<ul>
				
					<li id="transcodeManagementSubMenuButton"><a href="./adminhome" >Transcode Management
							</a>
					</li>
					<li id="metadataManagementSubMenuButton"><a href="./titlehome" >MetaData Management</a>
					</li>
					
					<li id="publishManagementSubMenuButton"><a href="./publishprofilegoto" >Publish Management</a>
					</li>
					
					
				</ul>
			</li>
			<%} %>
			<li id="ingestMenuButton"><a href="./dynamicWF" >DYNAMIC WF </a>
			</li>
			
		</ul>
		</div>
    <div class="ClearFloat"></div>
    </div><!--TopNavBgContainer End-->