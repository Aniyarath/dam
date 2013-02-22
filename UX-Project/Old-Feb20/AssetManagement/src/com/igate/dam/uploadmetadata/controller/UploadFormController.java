package com.igate.dam.uploadmetadata.controller;



import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.igate.dam.common.exception.DAMException;
import com.igate.dam.common.framework.util.CommonControllerUtil;
import com.igate.dam.database.service.DatabaseServiceIntf;
import com.igate.dam.database.service.impl.DatabaseServiceImpl;
import com.igate.dam.metadata.dto.Vendor;
import com.igate.dam.publish.service.PublishService;
import com.igate.dam.transcode.dto.Vendors;
import com.igate.dam.transcode.service.ProfileCreationService;
import com.igate.dam.uploadmetadata.dto.ListMetadata;
import com.igate.dam.uploadmetadata.dto.UploadForm;
import com.igate.dam.uploadmetadata.service.UploadMetadataService;


@Scope("session")
@Controller
public class UploadFormController
	{

	@Autowired
	private CommonControllerUtil commonControllerUtil;
	@Autowired
	UploadMetadataService uploadMetadataService;
	
	@Autowired
	ProfileCreationService profileCreationService;
	
	@Autowired
	PublishService publishService;
	
	@RequestMapping(value="uploadindex",method=RequestMethod.GET)
	public String directUpload(Model model,HttpSession session)
	{
		System.out.println("file uploading controller");
		model.addAttribute("fileUpload",new UploadForm());
		String userAccess=(String) session.getAttribute("userPermission");
		try {	
		if(userAccess.equals("ALL_ACCESS"))
		{
		List<Vendors> vendorList=profileCreationService.getvendor();
		model.addAttribute("vendorList",vendorList);
		session.setAttribute("vendorList", vendorList);
		System.out.println("vendor list size--------->"+vendorList.size());
		}
		
	
			model = commonControllerUtil.getModelObject(model);
			//model=commonControllerUtil.getModelObject(model, vendorId);
		} catch (Exception e) {
			
			
			//throw new DAMException(e.getMessage());
			model.addAttribute("errorMessage",e.getMessage());
		}
		return "fileupload_layout";
	}
	
	
	@RequestMapping(value="fileupload",method=RequestMethod.POST)
	public String processUpload(
			         @ModelAttribute(value="fileUpload") UploadForm uploadForm,Model model,HttpSession session,
			          @RequestParam(value = "vendorName")String name)  {
  
		int vendorId=0;
        String inputFileName=uploadForm.getFile().getOriginalFilename();
        String configFileName="smooks-config-XmlToJava.xml";
       String viewName=null;
       
		System.err.println("Test upload: "
				+ uploadForm.getFile().getOriginalFilename());
		System.err.println("-------------------------------------------");

try {
			model = commonControllerUtil.getModelObject(model);
         String xmlContent=uploadForm.getFile().getFileItem().getString();
        System.out.println(xmlContent);
        String userAccess=(String) session.getAttribute("userPermission");
        
        System.out.println("user acess----------->"+userAccess);
     
    	String vendorname = null;
		if (userAccess.equals("USER_ACCESS")) {
			
			vendorId = (Integer) session.getAttribute("vendorId");
			System.out.println("vendorid----------->" + vendorId);
			List<Vendors> vendorsList=profileCreationService.getvendor();
			Iterator iterator = vendorsList.iterator();
			while (iterator.hasNext()) {
				Vendors vendors = (Vendors) iterator.next();
				if (vendors.getVendorId() == vendorId) {
					vendorname = vendors.getVendorName();
				}
			}
			System.out.println("check file");
			ListMetadata metadataitems = uploadMetadataService
			.xmlToJavaTransformation(inputFileName, configFileName,
					xmlContent, vendorname);
			System.out.println("check file");
			DatabaseServiceIntf databaseServiceIntf = new DatabaseServiceImpl();
			int insertcount = databaseServiceIntf.packageEntry(inputFileName,
					vendorId);
			System.out.println("insercount----------->" + insertcount);
			
			boolean filexists = databaseServiceIntf.metadataFileEntry(
					inputFileName, "METADATA");
			System.out.println("metadataIngestStatus------------>" + filexists);

			if (!filexists) {
				boolean success = uploadMetadataService.saveXmlcontents(
						metadataitems, inputFileName, vendorId);
				databaseServiceIntf.updateMetadataIngestStatus(inputFileName);
				model.addAttribute("successMsg", "file uploaded successfully");
				viewName= "fileupload_layout";

			}

			else {
				System.out.println("alreday exist");
				viewName= "uploaderror";
			}
		}
        else
        {
        	System.out.println("entered  admin flow-------------");
        	 List vendorsList = (List) session.getAttribute("vendorList");
        	 model.addAttribute("vendorList",vendorsList);
        	Iterator iterator = vendorsList.iterator();
			while (iterator.hasNext()) {
				Vendors vendors = (Vendors) iterator.next();
				if (name.contains(vendors.getVendorName())) {
					
					vendorname=vendors.getVendorName();
					System.out.println("name*************"+vendorname);
				}
			}
        	System.out.println("name----------->"+name);
            vendorId=publishService.getVendorid(vendorname);
            System.out.println("vendorid----------->"+vendorId);
           DatabaseServiceIntf databaseServiceIntf=new DatabaseServiceImpl();
            int insertcount=databaseServiceIntf.packageEntry(inputFileName,vendorId);
            System.out.println("insercount----------->"+insertcount);
            ListMetadata metadataitems=uploadMetadataService.xmlToJavaTransformation(inputFileName, configFileName,xmlContent,name);
            boolean filexists=databaseServiceIntf.metadataFileEntry(inputFileName,"METADATA");
            System.out.println("metadataIngestStatus------------>"+filexists);
           
             if(!filexists)
               {
               boolean success=uploadMetadataService.saveXmlcontents(metadataitems, inputFileName, vendorId);
               databaseServiceIntf.updateMetadataIngestStatus(inputFileName);
               model.addAttribute("successMsg", "file uploaded successfully");
               viewName= "fileupload_layout";
            
               }
            
            else
            {
            	System.out.println("alreday exist");
            	viewName= "uploaderror";
            }
        }
      
    
	} catch (DAMException e) {
         
		e.printStackTrace();
		//throw new DAMException(e.getMessage());
		model.addAttribute("errorMessage",e.getMessage());
		viewName= "fileupload_layout";
		
	}
	return viewName;
	}
	}

