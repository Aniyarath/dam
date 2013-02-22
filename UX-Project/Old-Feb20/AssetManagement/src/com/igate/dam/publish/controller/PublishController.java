package com.igate.dam.publish.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.igate.dam.metadata.service.MetadataService;
import com.igate.dam.common.exception.DAMException;
import com.igate.dam.common.framework.util.CommonControllerUtil;
import com.igate.dam.metadata.dto.MediaPackage;
import com.igate.dam.metadata.dto.MetaDataModel;
import com.igate.dam.metadata.dto.PackageMetadata;
import com.igate.dam.metadata.dto.VendorMetadata;
import com.igate.dam.publish.dto.DamPackage;

import com.igate.dam.publish.dto.MetadataFields;
import com.igate.dam.publish.dto.PublishProfile;
import com.igate.dam.publish.service.PublishService;



/**
 * @author mj802966
 *
 */
@Controller
@Scope("session")
public class PublishController {
	
	@Autowired
	private CommonControllerUtil commonControllerUtil;
	@Autowired
	PublishService publishService;
	@Autowired
	private MetadataService metadataService;
	List<MediaPackage> mediaList = new ArrayList<MediaPackage>();
	
	

	@RequestMapping(value = "select", method = RequestMethod.GET)
	public String publishPage(@RequestParam(value = "id") String profileName,
			Model model, @ModelAttribute(value = "media") MediaPackage media,
			BindingResult bindingResult, HttpServletRequest request,
			HttpSession session) {

		System.out.println("id----------" + profileName);
		request.getSession().setAttribute("profileName", profileName);
		model.addAttribute("media", new MediaPackage());
		System.out.println("inside controller............");
		Map<String, String> lowresNameMap = new LinkedHashMap<String, String>();
		try
		{
	   int vendorId=(Integer)session.getAttribute("vendorId");
		mediaList = publishService.listPackages(vendorId);
		System.out.println("******************" + mediaList.size());
	
		
		lowresNameMap = publishService.previewVideo(mediaList);
		model = commonControllerUtil.getModelObject(model);
		} catch (DAMException e) {
			e.printStackTrace();
			
			//throw new DAMException(e.getMessage());
			model.addAttribute("errorMessage",e.getMessage());
			
			
		}
		if (mediaList.size() > 0) {
			System.out.println("size is.....................");
			model.addAttribute("mediaList", mediaList);
			  session.setAttribute("mediaList", mediaList);
			model.addAttribute("lowresNameMap", lowresNameMap);
			  session.setAttribute("lowresNameMap", lowresNameMap);
			return "show_media_layout";
		} else {
			model.addAttribute("errorMessage","no packages are avilable");
			return "show_media_layout";
		}
	}

/*---------------Move files to Publish Folder and Generate	XML.................................*/
	

	@RequestMapping(value="movefile", method=RequestMethod.GET)
	public String moveFiles(@RequestParam(value="id")int id,Model model,@ModelAttribute(value = "media") 
			MediaPackage media,
			BindingResult bindingResult,HttpServletRequest request,HttpSession session) {
		
		
		  String status=null;
		  System.out.println("id---->"+id);
	      String profileName= (String) session.getAttribute("profileName");
		  System.out.println("------------my profile name---------------"+profileName);
		  try
		  {
		  String vendorName=publishService.getVendorName(id);
		  System.out.println("vendorName---->"+vendorName);
		  String fileName=publishService.getMediaFileName(id);
		  System.out.println("fileName---->"+fileName);
		  
		
			  List<String> fileList =publishService.moveAllFile(vendorName, fileName,profileName);
			  int vendorid=publishService.getVendorid(id);
			  System.out.println("vendorid------->"+vendorid);
		 
			  List<MetadataFields> attList=publishService.getAttributes(id, vendorid);
			  Iterator<MetadataFields> iterator1=attList.iterator();
			  while(iterator1.hasNext())
			  	{
				  MetadataFields m1=iterator1.next();
				  System.out.println("-----------------------"+m1);
			  	}
			  DamPackage damPackage=new DamPackage();
			  damPackage.setMedia_package_name(fileName);
			  Iterator<String> iterator=fileList.iterator();
			  		while (iterator.hasNext()) {
			  			String string = (String) iterator.next();
			  			System.out.println("from list......."+string);
			  				if(string.contains(".jpg")){ 
			  					damPackage.setImage(string);
			  				}else{
			  					damPackage.setFileName(string);
			  					}
			  			}
		 damPackage.setMetadataFields(attList);
		 System.out.println("----------Size"+attList.size());
		
		System.out.println("final................."+damPackage);
        String xmlFile=publishService.runSmooksTransform(damPackage,profileName);
        System.out.println("final file....."+xmlFile);
     
        File file = new File(xmlFile);
		if (file.exists()) {
			MediaPackage mediaPackage = new MediaPackage();
			mediaPackage.setMedia_package_id(id);
			mediaPackage.setMedia_package_name(fileName);
			mediaPackage.setVendor_id(vendorid);
			mediaPackage.setStatus_id("PUB");
			mediaPackage.setMedia_ingest_status("Y");
			mediaPackage.setMetadata_ingest_status("Y");
			mediaPackage.setTranscoding_status("Y");
			publishService.updateStatus(mediaPackage);
			model.addAttribute("successMsg","Published Successfully");
			List<MediaPackage> mediaList= publishService.listPackages(vendorid);
			  Map<String, String> lowresNameMap =publishService.previewVideo(mediaList);
				model.addAttribute("mediaList", mediaList);
				model.addAttribute("lowresNameMap", lowresNameMap);
			status = "show_media_layout";
			
		} else {
			status = "tiles_home_layout";
		}
	
			model = commonControllerUtil.getModelObject(model);
		  
		} 
		  catch (DAMException e) {
			
			e.printStackTrace();
			//throw new DAMException(e.getMessage());
			model.addAttribute("errorMessage",e.getMessage());
			
			status = "show_media_layout";
			try
			{
			model = commonControllerUtil.getModelObject(model);
			} catch (DAMException ex) {
				
				ex.printStackTrace();
		}
		  }
		return status;
	}

	
/*-----------------------------------------------------------------------------------------------*/	
	
	@RequestMapping(value = "goto", method = RequestMethod.GET)
	public String searchFiles(Model model) {
		
		System.out.println(":goto controllerrrrrrrr");
      	model.addAttribute("profile", new PublishProfile());
      
      try {
		List vendorList = publishService.getVendorNames();
		@SuppressWarnings("unchecked")
		Iterator<String> iterator = vendorList.iterator();
		while (iterator.hasNext()) {
			String vendorName = iterator.next();
			System.out.println(vendorName);
		}
		model.addAttribute("vendorList",vendorList);
	
			model = commonControllerUtil.getModelObject(model);
		} catch (DAMException e) {
			
			e.printStackTrace();
			//throw new DAMException(e.getMessage());
			model.addAttribute("errorMessage",e.getMessage());
		}
		return "publish_layout";
	}
	
	
	

	@RequestMapping(value="profiles", method=RequestMethod.GET)
	public String goback(Model model,HttpSession session)
	
	{
		List profiles=(List) session.getAttribute("vendorProfiles");
		model.addAttribute("profiles",profiles);
		try {
			model = commonControllerUtil.getModelObject(model);
		} catch (DAMException e) {
			
			e.printStackTrace();
			//throw new DAMException(e.getMessage());
			model.addAttribute("errorMessage",e.getMessage());
		}
		return "profiles_layout";
	}
	

	
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String showFiles(@RequestParam(value="vendorName")String id,Model model,@ModelAttribute(value = "profile")PublishProfile profile,
			BindingResult bindingResult,HttpSession session){
		
		System.out.println("name------"+id);
		
			try {
		List vendorList = publishService.getVendorNames();
		model.addAttribute("vendorList",vendorList);
		int vendorid=publishService.getVendorid(id);
		System.out.println(vendorid);
		session.setAttribute("vendorId",vendorid);
		List<PublishProfile> profiles=publishService.listProfiles(vendorid);
		session.setAttribute("vendorProfiles", profiles);
		Iterator<PublishProfile> iterator1=profiles.iterator();
		while(iterator1.hasNext())
		{
			PublishProfile p=iterator1.next();
			System.out.println(p);
		}
		model.addAttribute("profiles",profiles);
		
		System.out.println("controller-----------size"+profiles.size());
	
			model = commonControllerUtil.getModelObject(model);
		} catch (DAMException e) {
			
			e.printStackTrace();
			model.addAttribute("errorMessage",e.getMessage());
			//throw new DAMException(e.getMessage());
		}
		return "profile_layout";
		
      
	}
	

	/*------------------------------preview of video-------------------------------------*/
	
	@RequestMapping(value="previewvideo", method=RequestMethod.GET)
	public String preview(@RequestParam(value="name")String name,@RequestParam(value="id")String id,Model model,@ModelAttribute(value = "media") 
			MediaPackage media,
			BindingResult bindingResult,HttpServletRequest request,HttpSession session) {
		
				  System.out.println("name"+name);
				  System.out.println("id"+id);
				  List fileList=new ArrayList<String>();
		try
           {
			fileList=publishService.fetchPosterNVideo(name);
			Iterator<String> it=fileList.iterator();
			String imageFile=null;
			String lowresFile=null;
			  while(it.hasNext())
			  	{
					//System.out.println(it.next());
					String string = (String) it.next();
					System.out.println("from list......."+string);
					if(string.contains(".jpg")){ 
						 imageFile=string;
						} 
					else{lowresFile=string; }
				}
			System.out.println("fileName*******"+imageFile);
		
			model.addAttribute("imagefile", imageFile);
			model.addAttribute("lowresfile", lowresFile);
			//adding for metadata display
			int packageId=0;
			if(id!=null ||id !="")
			{
				packageId=Integer.parseInt(id);
				session.setAttribute("packageId", packageId);
			}else{
				packageId=(Integer)session.getAttribute("packageId");
			}

       session.setAttribute("packageName", name);


                 System.out.println("packageId"+packageId);
				int vendorId=metadataService.getvendorId(packageId);
				System.out.println("vendorId*******"+vendorId);
				List<VendorMetadata>getVendorMetadataFields=new ArrayList<VendorMetadata>();
				getVendorMetadataFields = metadataService.getgetVendorMetadataFields(vendorId);
				@SuppressWarnings("rawtypes")
				List<PackageMetadata>mediapakList=new ArrayList<PackageMetadata>();
				mediapakList=metadataService.getMediaPackageMetadataDetails(Integer.parseInt(id));
				Map<Integer,String> metadataMap=new HashMap<Integer,String>();
				Map<Integer,StringBuffer> valuesMap=new HashMap<Integer,StringBuffer>();
				for(int count=0 ; count<getVendorMetadataFields.size();count++)
				{
				for(int count1=0; count1<mediapakList.size();count1++)
				{
					if(getVendorMetadataFields.get(count).getMaster_metadata_id() == mediapakList.get(count1).getMaster_metadata_id())
					{
						
						int mastermetadaId=getVendorMetadataFields.get(count).getMaster_metadata_id();
						String metadataValue=mediapakList.get(count1).getMetadata_attr_value();
						
						valuesMap=getVendorMetadataFields.get(count).getAttributevaluesMap();
						metadataMap.put(mastermetadaId, metadataValue);
						   
					}
				}
					
				}
				
				MetaDataModel metaDataModel = new MetaDataModel();
				
				metaDataModel.setMetadataMap(metadataMap);
				model.addAttribute("metaDataModel", metaDataModel);
				model.addAttribute("vendorList", getVendorMetadataFields);
				model.addAttribute("packageList", mediapakList);
				model.addAttribute("valuemodel",valuesMap);
				model.addAttribute("packageName", name);
			
				System.out.println("valuesMap:"+valuesMap);
				System.out.println("mediaPkLIst:"+mediapakList);
				System.out.println("getVendorMetadataFields:"+getVendorMetadataFields);
				System.out.println("metadataMap"+metadataMap);
				
			
					model = commonControllerUtil.getModelObject(model);
				} catch (DAMException e) {
					
					e.printStackTrace();
					//throw new DAMException(e.getMessage());
					model.addAttribute("errorMessage",e.getMessage());
				}
return "videopreview_layout";


		 
}
	
	/*********************************************************************************/
	@RequestMapping(value = "gotovendorsearch", method = RequestMethod.GET)
	public String goBackVendorFiles(Model model) {
		
		System.out.println(":goto controllerrrrrrrr");
      	model.addAttribute("profile", new PublishProfile());
      	
      		try {
		List vendorList = publishService.getVendorNames();
		@SuppressWarnings("unchecked")
		Iterator<String> iterator = vendorList.iterator();
		while (iterator.hasNext()) {
			String vendorName = iterator.next();
			System.out.println(vendorName);
		}
		model.addAttribute("vendorList",vendorList);
	
			model = commonControllerUtil.getModelObject(model);
		} catch (DAMException e) {
			
			e.printStackTrace();
			model.addAttribute("errorMessage",e.getMessage());
		}
		return "publish_layout";
	}
	

	
	@RequestMapping(value="profile", method=RequestMethod.GET)
	public String gobackSearch(Model model,HttpSession session)
	{
		List profiles=(List) session.getAttribute("vendorProfiles");
		model.addAttribute("profiles",profiles);
		try {
			model = commonControllerUtil.getModelObject(model);
		} catch (DAMException e) {
			
			e.printStackTrace();
			model.addAttribute("errorMessage",e.getMessage());
		}
		return "profile_layout";
	}
	

	@RequestMapping(value = "selectprofile", method = RequestMethod.GET)
	public String publishProfilePage(@RequestParam(value="id")String profileName,Model model,
			@ModelAttribute(value = "media") MediaPackage media,
			BindingResult bindingResult,HttpServletRequest request,HttpSession session){
		
		System.out.println("id----------"+profileName);
	    request.getSession().setAttribute("profileName",profileName);
	    int vendorId=(Integer)session.getAttribute("vendorId");
      	model.addAttribute("media", new MediaPackage());
      	System.out.println("inside controller............");
      	Map<String,String> lowresNameMap = new LinkedHashMap<String,String>();
    	try {
		mediaList= publishService.listPackages(vendorId);
		System.out.println("******************" + mediaList.size());
		
	
			lowresNameMap =publishService.previewVideo(mediaList);
	
			model = commonControllerUtil.getModelObject(model);
		} catch (DAMException e) {
			
			e.printStackTrace();
			//throw new DAMException(e.getMessage());
			model.addAttribute("errorMessage",e.getMessage());
			
		}
		if (mediaList.size() > 0) {
            System.out.println("size is.....................");
            model.addAttribute("mediaList", mediaList);
            session.setAttribute("mediaList", mediaList);
            model.addAttribute("lowresNameMap",lowresNameMap);
            session.setAttribute("lowresNameMap", lowresNameMap);
			return "show_media_layouts";
		} else {
			model.addAttribute("errorMessage","no packages are avilable");
			return "show_media_layouts";
		}
	}
	
	@RequestMapping(value = "searchprofiles", method = RequestMethod.GET)
	public String showProfiles(Model model,@ModelAttribute(value = "profile")PublishProfile profile,
			BindingResult bindingResult,HttpSession session,HttpServletRequest request){
		System.out.println("inside search profiles method......................");
		
		String userAccess=(String) session.getAttribute("userPermission");
		if(userAccess.equals("USER_ACCESS"))
		{
		int vendorId=(Integer) session.getAttribute("vendorId");
		System.out.println("name------"+vendorId);
		try {
		List<PublishProfile> profiles=publishService.listProfiles(vendorId);
		session.setAttribute("vendorProfiles", profiles);
		Iterator<PublishProfile> iterator1=profiles.iterator();
		while(iterator1.hasNext())
		{
			PublishProfile p=iterator1.next();
			System.out.println(p);
		}
		model.addAttribute("profiles",profiles);
		
		System.out.println("controller-----------size"+profiles.size());
		
			model = commonControllerUtil.getModelObject(model);
		} catch (DAMException e) {
			
			e.printStackTrace();
		//	throw new DAMException(e.getMessage());
			model.addAttribute("errorMessage",e.getMessage());
		}
		return "profiles_layout";
		}
		
		
		
		else
		{
			model.addAttribute("profile", new PublishProfile());
	      
	      		try {
			List vendorList = publishService.getVendorNames();
			@SuppressWarnings("unchecked")
			Iterator<String> iterator = vendorList.iterator();
			while (iterator.hasNext()) {
				String vendorName = iterator.next();
				System.out.println(vendorName);
			}
			model.addAttribute("vendorList",vendorList);
		
				model = commonControllerUtil.getModelObject(model);
			} catch (DAMException e) {
				
				e.printStackTrace();
				model.addAttribute("errorMessage",e.getMessage());
				//throw new DAMException(e.getMessage());
			}
			return "publish_layout";
		}
		
      
	}
}