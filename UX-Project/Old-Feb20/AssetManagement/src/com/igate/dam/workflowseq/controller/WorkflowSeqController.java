/**
 * 
 */
package com.igate.dam.workflowseq.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.common.framework.util.CommonControllerUtil;
import com.igate.dam.database.service.DatabaseServiceIntf;
import com.igate.dam.database.service.impl.DatabaseServiceImpl;
import com.igate.dam.metadata.dto.MediaPackage;
import com.igate.dam.metadata.dto.Vendor;
import com.igate.dam.transcode.dto.Vendors;
import com.igate.dam.transcode.service.ProfileCreationService;
import com.igate.dam.workflowseq.dto.MediaPkgWorkflowSEQ;
import com.igate.dam.workflowseq.service.WorkflowSeqService;

/**
 * @author 706440
 * 
 */
@Controller
@Scope("session")
public class WorkflowSeqController {
	
	@Autowired
	WorkflowSeqService workflowSeqService;
	
	@Autowired
	private CommonControllerUtil commonControllerUtil;
	
	@Autowired
	 ProfileCreationService profileCreationService;
	
	
	@RequestMapping(value = "/dynamicWF")
	public String loadDynamicWF(Model model,
			HttpServletRequest httpServletRequest,HttpSession session){
		System.out.println("Entering WorkflowSeqController -- loadDynamicWF");
		String viewName = "dynamicWF_layout";
		try {
		    String userAccess=(String) session.getAttribute("userPermission");
		    if(userAccess.equals("USER_ACCESS"))
		    {
		       int vendorId=(Integer) session.getAttribute("vendorId");
		
		       List<MediaPackage> packageNames=workflowSeqService.loadPackgeNames(vendorId);
		
		       Iterator<MediaPackage> iterator=packageNames.iterator();
		       while(iterator.hasNext())
	        	{
			     MediaPackage mediaPackage=iterator.next();
			     System.out.println(" from dynamic workflow------------>"+mediaPackage);
		        }
		        System.out.println("Exiting WorkflowSeqController -- loadDynamicWF");
		
		        session.setAttribute("packageNames",packageNames);
		        model.addAttribute("packageNames", packageNames);
			
			    model = commonControllerUtil.getModelObject(model);
			//model=commonControllerUtil.getModelObject(model, vendorId);
	    		return viewName;
		    }
		    else
		    {
		    	List<Vendors> vendorList=profileCreationService.getvendor();
			    model.addAttribute("vendorList",vendorList);
			    session.setAttribute("vendorList", vendorList);
			    System.out.println("vendor list size--------->"+vendorList.size());
			
				model = commonControllerUtil.getModelObject(model);
				//model=commonControllerUtil.getModelObject(model, vendorId);
		    }
		   }catch (DAMException exception) {
				
				//exception.printStackTrace();
				//throw new DAMException(exception.getMessage());
			   List<Vendors> vendorList=(List<Vendors>) session.getAttribute("vendorList");
			   session.setAttribute("vendorList", vendorList);
			   model.addAttribute("errorMessage",exception.getMessage());
		}
		return viewName;
		
		
	}

	@RequestMapping(value = "/dynamicWFSuccess")
	public String loadDynamicWFSuccess(
			@RequestParam(value = "strSequence") String strSequence,
			Model model, HttpServletRequest httpServletRequest,HttpSession session)throws DAMException {
		String viewName = null;
		String packageName = httpServletRequest.getParameter("mediaName");
		//int vendorId=(Integer) session.getAttribute("vendorId");
		  String userAccess=(String) session.getAttribute("userPermission");
		
		System.out.println("pac::::::::::::::::::::::"+packageName);
		List<MediaPackage> packageList=(List<MediaPackage>) session.getAttribute("packageNames");
	
		try{		
			System.out.println("Entering WorkflowSeqController -- dynamicWFSuccess");
			System.out.println("strSequence---->" + strSequence);
			
			if(strSequence.endsWith(",")){
				System.out.println("sTRING sEQUENCE IF-----------------------");
				strSequence = strSequence.substring(0,strSequence.length()-1);
				
			}
			System.out.println("sTRING sEQUENCE ENDIF-----------------------");
		
			if(packageName==null)
			{
				System.out.println("-------------------------Value from Select Box----------------------------------");
				packageName=httpServletRequest.getParameter("packageNames");
				System.out.println("select name------------>"+packageName);
				session.setAttribute("packageName", packageName);
				session.setAttribute("strSequence", strSequence);
				int packageId=0;
				Iterator iterator1=packageList.iterator();
				while(iterator1.hasNext())
				{
					MediaPackage mediaPackage1=(MediaPackage) iterator1.next();
					if(mediaPackage1.getMedia_package_name().equals(packageName))
					{
					packageId=mediaPackage1.getMedia_package_id();
						System.out.println("id------------>"+packageId);
					}
				}
				
				int workflowId=workflowSeqService.checkMediaPackage(packageId);
				System.out.println("count-------------->"+workflowId);
				session.setAttribute("workflowId", workflowId);
				if(workflowId>0)
				{
					//model.addAttribute("UpdateMessage", "Work flow already exists");
					
					return "update_workflow_layout";
				}
				else
				{
					if(userAccess.equalsIgnoreCase("USER_ACCESS"))
					{
						
					int vendorId=(Integer) session.getAttribute("vendorId");
					workflowSeqService.insertWorkflowSeq(packageName,strSequence,vendorId,0);
					model.addAttribute("insertSuccessMessage", "Workflow created Successfully");
					  model.addAttribute("packageNames", packageList);
					}
					else
					{
						int vendorId=workflowSeqService.getvendorId(packageName);
						workflowSeqService.insertWorkflowSeq(packageName,strSequence,vendorId,0);
						model.addAttribute("insertSuccessMessage", "Workflow created Successfully");
						 model.addAttribute("packageNames", packageList);
						 List<Vendors>  vendorList=(List<Vendors>) session.getAttribute("vendorList" );
						  model.addAttribute("vendorList",vendorList);
					}
					return "dynamicWF_layout";
				}
							
				
			}
			else if(packageName!=null)
			{
				if(userAccess.equalsIgnoreCase("USER_ACCESS"))
				{
					int vendorId=(Integer) session.getAttribute("vendorId");
				
				System.out.println("entered into else part");
				DatabaseServiceIntf databaseServiceIntf=new DatabaseServiceImpl();
				int insertcount=databaseServiceIntf.MediapackageEntry(packageName,vendorId);
				System.out.println("insercount"+insertcount);
				if(insertcount>0)
				{
				workflowSeqService.insertWorkflowSeq(packageName,strSequence,vendorId,0);
				System.out.println("inserted-----------------");
				 model.addAttribute("insertSuccessMessage", "Workflow created Successfully");
				 model.addAttribute("packageNames", packageList);
				viewName = "dynamicWF_layout";
				}
				else
				{
					
					viewName = "workflow_error_layout";
				}
				}
				else
				{
					System.out.println("entered text boxxxx");
					
					DatabaseServiceIntf databaseServiceIntf=new DatabaseServiceImpl();
					String vendorName = httpServletRequest.getParameter("vendorName");
					System.out.println("vendor name...........>."+vendorName);
					int vendorId=0;
					List<Vendors>  vendorList=(List<Vendors>) session.getAttribute("vendorList" );
					Iterator iterator=vendorList.iterator();
					while(iterator.hasNext())
							{
						Vendors vendor=(Vendors) iterator.next();
						if(vendorName.contains(vendor.getVendorName()))
						{
							vendorId=vendor.getVendorId();
							}
							}
					int insertcount=databaseServiceIntf.MediapackageEntry(packageName,vendorId);
					//int vendorId=workflowSeqService.getvendorId(packageName);
					System.out.println("insercount"+insertcount);
					if(insertcount>0)
					{
					workflowSeqService.insertWorkflowSeq(packageName,strSequence,vendorId,0);
					System.out.println("inserted-----------------");
					 model.addAttribute("insertSuccessMessage", "Workflow created Successfully");
					model.addAttribute("vendorList",vendorList);
					viewName = "dynamicWF_layout";
					}
					else
					{
						
						viewName = "workflow_error_layout";
					}
				}
				
			}
						
			model = commonControllerUtil.getModelObject(model);
				
		}
		catch(Exception exception){
			System.out.println("Error in loadDynamicWFSuccess-->"+exception.getMessage());
			//throw new DAMException(exception.getMessage());
			//List<Vendors>  vendorList=(List<Vendors>) session.getAttribute("vendorList" );
			//model.addAttribute("vendorList",vendorList);
			model.addAttribute("errorMessage",exception.getMessage());
		}
		System.out.println("Exiting WorkflowSeqController -- dynamicWFSuccess");
		
		return viewName;
	}
	
	
	@RequestMapping(value="updatedynamicWF")
	public String updatedynamicWorkflow(HttpSession session,Model model)
	{
		System.out.println("update------------------------->");
		String packageName=(String) session.getAttribute("packageName");
		int workflowId=(Integer) session.getAttribute("workflowId");
		String strSequence=(String) session.getAttribute("strSequence");
		try{
			  String userAccess=(String) session.getAttribute("userPermission");
			  if(userAccess.equalsIgnoreCase("USER_ACCESS"))
			  {
			
		
			int vendorId=(Integer) session.getAttribute("vendorId");
			
		    workflowSeqService.insertWorkflowSeq(packageName,strSequence,vendorId,workflowId);	
		    /*List<Vendors>  vendorList=(List<Vendors>) session.getAttribute("vendorList" );
		    model.addAttribute("vendorList",vendorList);*/
			model.addAttribute("updateSuccessMessage", "Workflow Updated Successfully");
			
			System.out.println("Values updated............................................");	
			  }
			  else
			  {
				
				  int vendorId=workflowSeqService.getvendorId(packageName);
				  workflowSeqService.insertWorkflowSeq(packageName,strSequence,vendorId,workflowId);	
				    List<Vendors>  vendorList=(List<Vendors>) session.getAttribute("vendorList" );
				    model.addAttribute("vendorList",vendorList);
					model.addAttribute("updateSuccessMessage", "Workflow Updated Successfully");
					System.out.println("Values updated............................................");	
				  
			  }
			  List<MediaPackage> packageList=(List<MediaPackage>) session.getAttribute("packageNames");
			  model.addAttribute("packageNames", packageList);
		}
		
		catch (Exception exception) {
			
			//throw new DAMException(exception.getMessage());
			model.addAttribute("errorMessage",exception.getMessage());
		}
		return "dynamicWF_layout";
	}
}
