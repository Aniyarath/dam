package com.igate.dam.web;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;


import com.igate.dam.common.exception.DAMException;
import com.igate.dam.common.framework.util.CommonControllerUtil;
import com.igate.dam.metadata.dto.MediaPackage;
import com.igate.dam.metadata.service.MetadataService;
import com.igate.dam.transcode.dto.Vendors;
import com.igate.dam.workflowseq.service.WorkflowSeqService;


@Controller
public class PackageController {

	
	@Autowired
	private MetadataService metadataService;
	
	@Autowired
	WorkflowSeqService workflowSeqService;
	
	@Autowired
	private CommonControllerUtil commonControllerUtil;
	
	
	@RequestMapping(value="/getPackages",method=RequestMethod.GET)
	public @ResponseBody String[] loadHome(HttpSession session,Model model)
	{
	//String packagesList="";
	System.out.println("the beginning of the controller..........................");
	int vendorId=(Integer) session.getAttribute("vendorId");
	String packagesList[]=null;
	List<MediaPackage> mediaPackageList=null;
	try
	{
	String permission=(String)session.getAttribute("userPermission");
	//List<MediaPackage> mediaPackageList=null;
	if(permission.equals("USER_ACCESS"))
	{
    mediaPackageList=metadataService.loadPackgeNames(vendorId);
	Iterator<MediaPackage> iterator=mediaPackageList.iterator();
    packagesList=new String[mediaPackageList.size()];
	int arrayCounter=0;
	System.out.println("ajax call checking................................");
	while(iterator.hasNext())
	{
		MediaPackage mediaPackage=iterator.next();
		System.out.println("Media Package Names..................from Package Controller"+mediaPackage.getMedia_package_name());
		packagesList[arrayCounter]=mediaPackage.getMedia_package_name();
		arrayCounter++;
		
	}
	return packagesList;
	}
	else
	{
		 mediaPackageList=metadataService.displayPackgeNames();
			Iterator<MediaPackage> iterator=mediaPackageList.iterator();
		     packagesList=new String[mediaPackageList.size()];
			int arrayCounter=0;
			System.out.println("ajax call checking................................");
			while(iterator.hasNext())
			{
				MediaPackage mediaPackage=iterator.next();
				System.out.println("Media Package Names..................from Package Controller"+mediaPackage.getMedia_package_name());
				packagesList[arrayCounter]=mediaPackage.getMedia_package_name();
				arrayCounter++;
				
			}
	}
	}
	catch(DAMException e)
	{
		//throw new DAMException(e.getMessage());
		model.addAttribute("errorMessage",e.getMessage());
	}
			return packagesList;
	
}
	
	@RequestMapping(value="/getAllPackages",method=RequestMethod.GET)
	public @ResponseBody String getAllPackages(@RequestParam(value="vendorName")String name,HttpSession session,Model model) 
	{
		String returnString = "<option value='0'>SELECT</option>";		
		try
		{
		System.out.println("name------------>"+name);
		  List vendorsList = (List) session.getAttribute("vendorList");
		  int vendorId=0;
		  Iterator iterator = vendorsList.iterator();
			while (iterator.hasNext()) {
				Vendors vendors = (Vendors) iterator.next();
				if (name.contains(vendors.getVendorName())) {
					
					vendorId=vendors.getVendorId();
					System.out.println("name*************"+vendorId);
				}
			}
		List<MediaPackage> packageNames=workflowSeqService.loadPackgeNames(vendorId);
		session.setAttribute("packageNames",packageNames);
		System.out.println("packageName list--------->"+packageNames.size());
		//String returnString = "<option value='0'>SELECT</option>";		
		iterator=packageNames.iterator();
		while(iterator.hasNext())
		{
			MediaPackage mediaPackage=(MediaPackage)iterator.next();
			System.out.println("Media Package Names..................from Package Controller"+mediaPackage.getMedia_package_name());
			returnString += "<option value="+mediaPackage.getMedia_package_name()+">"+mediaPackage.getMedia_package_name()+"</option>";
			
		}
		
	/*	try {
			model = commonControllerUtil.getModelObject(model);
			//model=commonControllerUtil.getModelObject(model, vendorId);
		} catch (DAMException e) {
			
			e.printStackTrace();
		}*/
		System.out.println(returnString);
		}
		catch(DAMException damException)
		{
			//throw new DAMException(damException.getMessage());
			model.addAttribute("errorMessage",damException.getMessage());
			
		}
      return returnString;
	}
}
