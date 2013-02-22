package com.igate.dam.metadata.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.common.framework.util.CommonControllerUtil;
import com.igate.dam.metadata.dto.MasterMetadata;
import com.igate.dam.metadata.dto.Media;
import com.igate.dam.metadata.dto.MediaPackage;
import com.igate.dam.metadata.dto.MediaPackageFiles;
import com.igate.dam.metadata.dto.MetaDataModel;
import com.igate.dam.metadata.dto.Metadata;
import com.igate.dam.metadata.dto.MetadataAttributeTypes;
import com.igate.dam.metadata.dto.MetadataVendorAssoc;
import com.igate.dam.metadata.dto.PackageMetadata;
import com.igate.dam.metadata.dto.Vendor;
import com.igate.dam.metadata.dto.VendorMetadata;
import com.igate.dam.metadata.service.MetadataService;
import com.igate.dam.publish.dto.MetadataFields;
import com.igate.dam.publish.service.PublishService;

@Controller
@Scope("session")
public class MetadataController {

	
	@Autowired
	private CommonControllerUtil commonControllerUtil;
    @Autowired
	private MetadataService metadataService;
	
      @Autowired
      private  PublishService publishService;
    
	public MetadataService getMetadataService() {
		return metadataService;
	}

	public void setMetadataService(MetadataService metadataService) {
		this.metadataService = metadataService;
	}

	@RequestMapping(value = "/searchallpackages")
	public String searchpackages(Model model,HttpSession session)
	{
		System.out.println("Coming inside the searchpackages");
		
		int vendorId=(Integer) session.getAttribute("vendorId");
		List<MediaPackage> metadatasearchPack;
		try {	
		System.out.println("vendorId"+vendorId);
	    metadatasearchPack = new ArrayList<MediaPackage>();
		
		metadatasearchPack=metadataService.getPackgeNames(vendorId);


		List<MediaPackageFiles> packageFileList=new ArrayList<MediaPackageFiles>();		
	    for(MediaPackage m:metadatasearchPack)
	    {
	    	int packageId=m.getMedia_package_id();
	    	System.out.println("pId"+packageId);
	    	packageFileList=metadataService.getFileList(packageId);
	    	m.setPackageFileList(packageFileList);
	    }
	    
	    model.addAttribute("packageFileList", packageFileList);
	    //session.setAttribute("packageFileList", packageFileList);
	    
		if(metadatasearchPack.size() == 0){
			model.addAttribute("notitle","No Package Found");
		}
	
		model.addAttribute("packagesList",metadatasearchPack);
		session.setAttribute("packagesList",metadatasearchPack);
			model = commonControllerUtil.getModelObject(model);
			//model=commonControllerUtil.getModelObject(model, vendorId);
		} catch (DAMException e) {
			
			e.printStackTrace();
			model.addAttribute("errorMessage",e.getMessage());
			//throw new DAMException(e.getMessage());
		}
		
				
		return "search_results";
	}
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/searchpackages")
	public String searchpackages(@RequestParam(value = "packageName") String packageName,
			Model model,HttpSession session,HttpServletRequest request)
	{
		
		String permission=(String) session.getAttribute("userPermission");
		
		List<MediaPackageFiles> packageFileList=new ArrayList<MediaPackageFiles>();		
		
		//String packageName = mp.getMedia_package_name();
		String viewName=null;
		if (packageName == null || packageName.trim().length()<=0){
			model.addAttribute("errorMessage","Package name is empty");
		}
		
		
		System.out.println("Coming inside the searchpackages :"+packageName);
	try
	{
		MediaPackage mediaPackage=metadataService.getSpecifiedPackage(packageName);
		

		System.out.println("-------specified package--------------->"+mediaPackage);
		
		if (mediaPackage == null) {
			
			if(permission.equals("USER_ACCESS"))
			{
				int vendorId=(Integer) session.getAttribute("vendorId");
			System.out.println("no matching found-------------->");
			List<MediaPackage> packagesList = metadataService
					.getPackgeNames(packageName,vendorId);

			if (packagesList.size() == 0) {
				model.addAttribute("notitle", "No Package Found");

			} else {
				model.addAttribute("packagesList", packagesList);
				session.setAttribute("packagesList", packagesList);
				 for(MediaPackage m:packagesList)
				    {
				    	int packageId=m.getMedia_package_id();
				    	System.out.println("pId"+packageId);
				    	packageFileList=metadataService.getFileList(packageId);
				    	m.setPackageFileList(packageFileList);
				    }
				    
				    model.addAttribute("packageFileList", packageFileList);
				
			}
			viewName = "search_results";
			
			}
			
			if(permission.equals("ALL_ACCESS"))
			{
			
			System.out.println("no matching found-------------->");
			List<MediaPackage> packagesList = metadataService.getPackgeNames(packageName);
					

			if (packagesList.size() == 0) {
				model.addAttribute("notitle", "No Package Found");

			} else {
				model.addAttribute("packagesList", packagesList);
				session.setAttribute("packagesList", packagesList);
				 for(MediaPackage m:packagesList)
				    {
				    	int packageId=m.getMedia_package_id();
				    	System.out.println("pId"+packageId);
				    	packageFileList=metadataService.getFileList(packageId);
				    	m.setPackageFileList(packageFileList);
				    }
				    
				    model.addAttribute("packageFileList", packageFileList);
				
			}
			viewName = "search_results";
			
			}
			
			
			
		} 
		else {
			
			int id=mediaPackage.getMedia_package_id();
			 List fileList=new ArrayList<String>();
			 fileList=publishService.fetchPosterNVideo(packageName);
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
				else{
					lowresFile=string;
				    }
				
			}
			System.out.println("fileName*******"+imageFile);
			System.out.println("lowresfile*******"+lowresFile);
			model.addAttribute("imagefile", imageFile);
			model.addAttribute("lowresfile", lowresFile);
			//adding for metadata display
			session.setAttribute("packageId", id);
			session.setAttribute("packageName", packageName);
			
	    	
	    	model.addAttribute("packageName",packageName);
	    	 //packageName=(String)session.getAttribute("packageName");
	    	
	    	 //int packageId=Integer.parseInt(request.getParameter("id"));
  
		    	/*int vendorId=metadataService.getvendorId(packageId);
		    	System.out.println("vendorId from controller"+vendorId);*/
		    	
	    		model.addAttribute("packageName",packageName);
		    	//editMapObj = metadataServic
		    	//System.out.println("package Id"+packageId);
		   
	    	System.out.println("package:"+packageName);
	    System.out.println("---------------->"+mediaPackage.getVendor_id());
			List<VendorMetadata>getVendorMetadataFields=new ArrayList<VendorMetadata>();
			getVendorMetadataFields = metadataService.getgetVendorMetadataFields(mediaPackage.getVendor_id());
			@SuppressWarnings("rawtypes")
			List<PackageMetadata>mediapakList=new ArrayList<PackageMetadata>();
			mediapakList=metadataService.getMediaPackageMetadataDetails(id);
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
			model.addAttribute("packageName", packageName);
			System.out.println("---------------------Shekhar---------------------------------------");
			System.out.println("valuesMap:"+valuesMap);
			System.out.println("mediaPkLIst:"+mediapakList);
			System.out.println("getVendorMetadataFields:"+getVendorMetadataFields);
			System.out.println("metadataMap"+metadataMap);

		
			/*try {
				model = commonControllerUtil.getModelObject(model);
			} catch (DAMException e) {
				
				e.printStackTrace();
			}*/
			
			viewName = "videopreview_layout";
		}
		//try {
			model = commonControllerUtil.getModelObject(model);
			
		} 
		catch (DAMException e) {
			
			e.printStackTrace();
			viewName = "videopreview_layout";
			model.addAttribute("errorMessage",e.getMessage());
			//throw new DAMException(e.getMessage());
		}
		
	 
	  return viewName;
	}
	
	@RequestMapping(value="/getFileList")
	public String getFileList(@RequestParam(value = "packageId") int packageId,Model model,HttpSession session)
	{
		try {
			model = commonControllerUtil.getModelObject(model);
		
		List<Media> packagesList=(List<Media>) session.getAttribute("packagesList");
		model.addAttribute("packagesList",packagesList);
		System.out.println(packageId);
		List<MediaPackageFiles> packageFileList=metadataService.getFileList(packageId);
		model.addAttribute("FileList", packageFileList);
		for(MediaPackageFiles mediaPackageFiles:packageFileList)
		{
			System.out.println("fhdddddddddddd"+mediaPackageFiles);
		}
		
	} catch (DAMException e) {
		
		e.printStackTrace();
		model.addAttribute("errorMessage",e.getMessage());
	}
		return "search_results";
	}

	
	
	
	 @RequestMapping(value = "/loadmetadata")
		public String loadMetaData(Model model,HttpSession session,HttpServletRequest request)
	    {
		 
			
		    	System.out.println("Coming inside the loadmetadata");
		    	//int packageId=1;
		    	  try {	
		    	String packageName=request.getParameter("package");
		    	session.setAttribute("packageName", packageName);
		    	model.addAttribute("packageName",packageName);
		    	 packageName=(String)session.getAttribute("packageName");
		    	
		    	 int packageId=Integer.parseInt(request.getParameter("id"));

			    	int vendorId=metadataService.getvendorId(packageId);
			    	System.out.println("vendorId from controller"+vendorId);
			    	
			    	
			    	//editMapObj = metadataServic
			    	System.out.println("package Id"+packageId);
			   
		    	System.out.println("package:"+packageName);
		    
		    	
		    	//editMapObj = metadataServic
		    	System.out.println("package Id"+packageId);
		        List<VendorMetadata>getVendorMetadataFields=new ArrayList<VendorMetadata>();
		    	getVendorMetadataFields = metadataService.getgetVendorMetadataFields(vendorId);
		    	System.out.println("getVendorMetadataFields.getMetadata_attr_type()**************"+getVendorMetadataFields.toString());
		    	System.out.println("values in controller"+getVendorMetadataFields.get(1).getAttributevaluesMap().toString());
		    	VendorMetadata vm=new VendorMetadata();
		    
		        for(VendorMetadata vendorValues:getVendorMetadataFields)
		        {
			    System.out.println("vendorValues.getMaster_metadata_id()$$$"+vendorValues.getMaster_metadata_id());
			    System.out.println("vendorValues.getMaster_metadata_name()$$$"+vendorValues.getMaster_metadata_name());
			    System.out.println("vendorValues.getMetadata_attr_type()()$$$"+vendorValues.getMetadata_attr_type());
			    System.out.println("getMetadata_attribute_value()$$$"+vendorValues.getMetadata_attribute_value());
		       
			   
			  
		        
		        }
		    
		        
		    	@SuppressWarnings("rawtypes")
		    	 List<PackageMetadata>mediapakList=new ArrayList<PackageMetadata>();
				 mediapakList=metadataService.getMediaPackageMetadataDetails(packageId);
		    	 session.setAttribute("packageId", packageId);
		    	 String mValueObj=null;
		    	 for(PackageMetadata ml:mediapakList)
					{
					mValueObj=ml.getMetadata_attr_value();
					System.out.println("++++++mValueObj"+mValueObj);	
					getVendorMetadataFields.get(0).setMetadata_attr_value(mValueObj);
					}
					
		    	System.out.println("mediapakmap#######################"+mediapakList.size());
		    	System.out.println("mediapakList values"+mediapakList.toString());
		        Map<Integer,String> metadataMap=new HashMap<Integer,String>();
		        Map<Integer,String> editMapObj=new HashMap<Integer, String>();
		        Map<Integer,StringBuffer> valuesMap=new HashMap<Integer,StringBuffer>();
		        System.out.println("getVendorMetadataFields.size() size of:"+getVendorMetadataFields.size());
		    	
		    	for(int count=0 ; count<getVendorMetadataFields.size();count++)
		    		
		    	{
		    		System.out.println("entered into for loop....");
		    		System.out.println("getVendorMetadataFields.get().getMaster_metadata_id()"+getVendorMetadataFields.get(count).getMaster_metadata_id());
		    		int mastermetadaId=0;
		    		String value=null;
			    	for(int count1=0; count1<mediapakList.size();count1++)
			    	{
			    		if(getVendorMetadataFields.get(count).getMaster_metadata_id() == mediapakList.get(count1).getMaster_metadata_id())
			    		{
			    			System.out.println("entered into for loop1.... "+count);
			    			mastermetadaId=getVendorMetadataFields.get(count).getMaster_metadata_id();
			    			String metadataValue=mediapakList.get(count1).getMetadata_attr_value();
			    			
			    			valuesMap=getVendorMetadataFields.get(count).getAttributevaluesMap();
			    			metadataMap.put(mastermetadaId, metadataValue);
			    			
			    		}
			    		
		    			
		    		System.out.println("metadataMap  777777"+metadataMap.toString());
		    		for (Entry<Integer, StringBuffer> entry : valuesMap.entrySet())
		        	{
		        	    System.out.println(entry.getKey() + "/" + entry.getValue());
		        	}
		    	}
		    	}
			    	
		    		for(VendorMetadata v:getVendorMetadataFields)
		    		{
		    		
		    			System.out.println("value of v.getMaster_metadata_id()***"+v.getMaster_metadata_id());
		    			System.out.println("value of metadataMap.get(v.getMaster_metadata_id()***"+metadataMap.get(v.getMaster_metadata_id()));
		    			System.out.println("value of metadataMap.get(Integer v.getMaster_metadata_id()***"+metadataMap.get(new Integer(v.getMaster_metadata_id())));
			    		if (metadataMap.get(v.getMaster_metadata_id())==null){
			    			
			    			metadataMap.put(v.getMaster_metadata_id(), null);	
			    		}
			    	}
		    		
		    		
		    		
		    		System.out.println("size of metadataMap***"+metadataMap.size());
		    	System.out.println("++++++++++++++++++");
		    	System.out.println(metadataMap.entrySet());
		    	MetaDataModel metaDataModel = new MetaDataModel();
		    	
		    	System.out.println("metadataMap values*********"+metadataMap.toString());
		    	
		    	metaDataModel.setMetadataMap(metadataMap);
		    	System.out.println("size of metadataMap !!!!!!!!"+metadataMap.size());
		    	
		    	
		    	
		    	
		    	model.addAttribute("metaDataModel", metaDataModel);
				model.addAttribute("vendorList", getVendorMetadataFields);
		        model.addAttribute("packageList", mediapakList);
		        model.addAttribute("valuemodel",valuesMap);
		        model.addAttribute("packageName", packageName);
		        

		        
		        //model.addAttribute("", );
		      
		      
					model = commonControllerUtil.getModelObject(model);
					
				} catch (DAMException e) {
					
					e.printStackTrace();
					model.addAttribute("errorMessage",e.getMessage());
					try
					{
					model = commonControllerUtil.getModelObject(model);
					}
					catch (DAMException damException) {
						e.printStackTrace();
					}
					
				}
		return "load_metadata_details";
	}
	 
	 
	 
	 
	 
	 @RequestMapping(value = "/editmetadata")
		public String editMetaData(Model model,HttpSession session,HttpServletRequest request)
	    {
		 
	    	System.out.println("Coming inside the editmetadata");
	    	//int packageId=1;
	    	
	    	
	try
	{
		//editMapObj = metadataServic
		int packageId=(Integer)session.getAttribute("packageId");
    	String packageName=(String)session.getAttribute("packageName");
	    	System.out.println("package Id"+packageId);
	    	int vendorId=metadataService.getvendorId(packageId);
	        List<VendorMetadata>getVendorMetadataFields=new ArrayList<VendorMetadata>();
	    	getVendorMetadataFields = metadataService.getgetVendorMetadataFields(vendorId);
	    	System.out.println("getVendorMetadataFields.getMetadata_attr_type()**************"+getVendorMetadataFields.toString());
	    	System.out.println("values in controller"+getVendorMetadataFields.get(1).getAttributevaluesMap().toString());
	    	VendorMetadata vm=new VendorMetadata();
	    
	        for(VendorMetadata vendorValues:getVendorMetadataFields)
	        {
		    System.out.println("vendorValues.getMaster_metadata_id()$$$"+vendorValues.getMaster_metadata_id());
		    System.out.println("vendorValues.getMaster_metadata_name()$$$"+vendorValues.getMaster_metadata_name());
		    System.out.println("vendorValues.getMetadata_attr_type()()$$$"+vendorValues.getMetadata_attr_type());
		    System.out.println("getMetadata_attribute_value()$$$"+vendorValues.getMetadata_attribute_value());		  
	        
	        }
	    
	        
	    	@SuppressWarnings("rawtypes")
	    	 List<PackageMetadata>mediapakList=new ArrayList<PackageMetadata>();
			 mediapakList=metadataService.getMediaPackageMetadataDetails(packageId);
	    	 session.setAttribute("packageId", packageId);
	    	 String mValueObj=null;
	    	 for(PackageMetadata ml:mediapakList)
				{
				mValueObj=ml.getMetadata_attr_value();
				System.out.println("++++++mValueObj"+mValueObj);	
				getVendorMetadataFields.get(0).setMetadata_attr_value(mValueObj);
				}
				
	    	System.out.println("mediapakmap#######################"+mediapakList.size());
	    	System.out.println("mediapakList values"+mediapakList.toString());
	        Map<Integer,String> metadataMap=new HashMap<Integer,String>();
	        Map<Integer,String> editMapObj=new HashMap<Integer, String>();
	        Map<Integer,StringBuffer> valuesMap=new HashMap<Integer,StringBuffer>();
	        System.out.println("getVendorMetadataFields.size() size of:"+getVendorMetadataFields.size());
	    	
	    	for(int count=0 ; count<getVendorMetadataFields.size();count++)
	    		
	    	{
	    		System.out.println("entered into for loop....");
	    		System.out.println("getVendorMetadataFields.get().getMaster_metadata_id()"+getVendorMetadataFields.get(count).getMaster_metadata_id());
	    		int mastermetadaId=0;
	    		String value=null;
		    	for(int count1=0; count1<mediapakList.size();count1++)
		    	{
		    		if(getVendorMetadataFields.get(count).getMaster_metadata_id() == mediapakList.get(count1).getMaster_metadata_id())
		    		{
		    			System.out.println("entered into for loop1.... "+count);
		    			mastermetadaId=getVendorMetadataFields.get(count).getMaster_metadata_id();
		    			String metadataValue=mediapakList.get(count1).getMetadata_attr_value();
		    			
		    			valuesMap=getVendorMetadataFields.get(count).getAttributevaluesMap();
		    			metadataMap.put(mastermetadaId, metadataValue);
		    			
		    		}
		    		
	    			
	    		System.out.println("metadataMap  777777"+metadataMap.toString());
	    		for (Entry<Integer, StringBuffer> entry : valuesMap.entrySet())
	        	{
	        	    System.out.println(entry.getKey() + "/" + entry.getValue());
	        	}
	    	}
	    	}
		    	
	    		for(VendorMetadata v:getVendorMetadataFields)
	    		{
	    		
	    			System.out.println("value of v.getMaster_metadata_id()***"+v.getMaster_metadata_id());
	    			System.out.println("value of metadataMap.get(v.getMaster_metadata_id()***"+metadataMap.get(v.getMaster_metadata_id()));
	    			System.out.println("value of metadataMap.get(Integer v.getMaster_metadata_id()***"+metadataMap.get(new Integer(v.getMaster_metadata_id())));
		    		if (metadataMap.get(v.getMaster_metadata_id())==null){
		    			
		    			metadataMap.put(v.getMaster_metadata_id(), null);	
		    		}
		    	}
	    		
	    		
	    		
	    		System.out.println("size of metadataMap***"+metadataMap.size());
	    	System.out.println("++++++++++++++++++");
	    	System.out.println(metadataMap.entrySet());
	    	MetaDataModel metaDataModel = new MetaDataModel();
	    	
	    	System.out.println("metadataMap values*********"+metadataMap.toString());
	    	
	    	metaDataModel.setMetadataMap(metadataMap);
	    	System.out.println("size of metadataMap !!!!!!!!"+metadataMap.size());
	    	
	    	
	    	
	    	
	    	model.addAttribute("metaDataModel", metaDataModel);
	    	System.out.println("getVendorMetadataFields--->"+getVendorMetadataFields.toString());
			model.addAttribute("vendorList", getVendorMetadataFields);
	        model.addAttribute("packageList", mediapakList);
	        model.addAttribute("valuemodel",valuesMap);
	        model.addAttribute("packageName", packageName);
	        
	        
	       // session.setAttribute("metaDataModel", metaDataModel);
			session.setAttribute("vendorList", getVendorMetadataFields);
			session.setAttribute("packageList", mediapakList);
			session.setAttribute("valuemodel",valuesMap);
			session.setAttribute("packageName", packageName);
	        

	        
	        //model.addAttribute("", );
	      
	    
				model = commonControllerUtil.getModelObject(model);
				
			}
	catch (NullPointerException nullPointerException) {
		nullPointerException.printStackTrace();
		model.addAttribute("errorMessage","Error while loading metadata");
		try
		{
		model = commonControllerUtil.getModelObject(model);
		}
		catch (DAMException damException) {
			damException.printStackTrace();
		}
	}
	
	
	catch (DAMException e) {
				
				e.printStackTrace();
				model.addAttribute("errorMessage", e.getMessage());
				try
				{
				model = commonControllerUtil.getModelObject(model);
				}
				catch (DAMException damException) {
					e.printStackTrace();
				}
				
			}
		return "edit_metadata_details";
	}
	 
	     
	 
	 
	 
	 
	 
		/*@RequestMapping(value="/loadAttributs")
	    public String loadAttributesList(Model model)
	     {
			System.out.println("Coming inside the loadAttributesList");
	   
	    	List<MetadataAttributeTypes> attributesList = metadataService.getAttributeTypeList();
		
	     	model.addAttribute("metadatamodel", attributesList);
	    	   	
			return "addnewattribute";
		}*/
		@RequestMapping(value="/updateMetadata")
		public String saveMetadataDetails1(@ModelAttribute(value="metaDataModel")MetaDataModel metaDataModel,BindingResult result,Model model,HttpSession session){
			System.out.println("entered into save metadata details....");
			Map<Integer, String> metaDataMap=metaDataModel.getMetadataMap();
			System.out.println("metaDataMap size:"+metaDataMap.size());
			System.out.println("metaDataMap values:"+metaDataMap.toString());
			String status=null;
			
			int packageId=(Integer)session.getAttribute("packageId");
			
			Map<Integer, Metadata> metaMap=new HashMap<Integer, Metadata>();
			try {	
			for(Map.Entry<Integer, String> mapObj:metaDataMap.entrySet()){
				
				int masterMetadataId=mapObj.getKey();
				String value=mapObj.getValue();
				//System.out.println("value :"+value.length()+ " : "+value);
				System.out.println("masterMetadataId:"+masterMetadataId);
			if(value!=null){
				value = value.trim();
				if(!"".equals(value))
				{ 
					Metadata metadata=new Metadata();
					metadata.setMedia_package_id(packageId);
					metadata.setMetadata_attr_value(value);
					metaMap.put(masterMetadataId,metadata);
					
				}/*else{
					Metadata metadata=new Metadata();
					metadata.setMedia_package_id(packageId);
					metadata.setMetadata_attr_value(value);
					metaMap.put(masterMetadataId,metadata);
				}*/
			}
			/*else
			{
				Metadata metadata=new Metadata();
				metadata.setMedia_package_id(packageId);
				metadata.setMetadata_attr_value(value);
				metaMap.put(masterMetadataId,metadata);
			}
			*/
			}
			System.out.println("metaMap size"+metaMap.size());
			status=metadataService.updateMetadataDetails(metaMap,packageId);
			
				model = commonControllerUtil.getModelObject(model);
			} catch (DAMException e) {
				
				e.printStackTrace();
				model.addAttribute("errorMessage", e.getMessage());
				
			}
			model.addAttribute("SuccessMessage", "Updated Successfully");
			
			
			List<VendorMetadata> getVendorMetadataFields=(List<VendorMetadata>) session.getAttribute("vendorList");
			List<PackageMetadata> mediapakList=(List<PackageMetadata>) session.getAttribute("packageList");
			Map<Integer,StringBuffer> valuesMap=(Map<Integer, StringBuffer>) session.getAttribute("valuemodel");
			String packageName=(String) session.getAttribute("packageName");
			
			model.addAttribute("vendorList", getVendorMetadataFields);
			model.addAttribute("packageList", mediapakList);
			model.addAttribute("valueMap", valuesMap);
			model.addAttribute("packageName", packageName);
			model.addAttribute("metaDataModel", metaDataModel);
			return "edit_metadata_details";
		}
	
	
	
	
	
	
	
	
	 
	 
	 
		@RequestMapping(value="/loadAttributs")
	    public String loadAttributesList(Model model)
	     {
			System.out.println("Coming inside the loadAttributesList");
			try { 
	    	List<MetadataAttributeTypes> attributesList = metadataService.getAttributeTypeList();
		
	     	model.addAttribute("metadatamodel", attributesList);
	    	   	
	     	
				model = commonControllerUtil.getModelObject(model);
			} catch (DAMException e) {
				
				e.printStackTrace();
				//throw new DAMException(e.getMessage());
				model.addAttribute("errorMessage",e.getMessage());
				
			}
			return "addnewattribute";
		}

		
		@RequestMapping(value="save")
		public String saveMetadataDetails(@ModelAttribute(value="metaDataModel")MetaDataModel metaDataModel,BindingResult result,Model model,HttpSession session){
			
			Map<Integer, String> metaDataMap=metaDataModel.getMetadataMap();
			System.out.println(metaDataMap.size());
			System.out.println(metaDataMap.toString());
			try {		
			
			String status=null;
		
			int packageId=(Integer)session.getAttribute("packageId");
			
			Map<Integer, Metadata> metaMap=new HashMap<Integer, Metadata>();
		
			for(Map.Entry<Integer, String> mapObj:metaDataMap.entrySet()){
				
				int masterMetadataId=mapObj.getKey();
				String value=mapObj.getValue();
				System.out.println("masterMetadataId:"+masterMetadataId);
				Metadata metadata=new Metadata();
				metadata.setMedia_package_id(packageId);
				metadata.setMetadata_attr_value(value);
				metaMap.put(masterMetadataId,metadata);
				
			}
			
			status=metadataService.updateMetadataDetails(metaMap,packageId);
			metaDataModel.setMetadataMap(metaDataMap);
			model.addAttribute("metadatamodel", metaDataModel);
		
				model = commonControllerUtil.getModelObject(model);
			} catch (DAMException e) {
				
				e.printStackTrace();
				model.addAttribute("errorMessage",e.getMessage());
				
			}
			return "user_updateattribute_success_layout";
		}
		
		@RequestMapping(value="useraddattribute")
		public String addAttribute(Model model,HttpSession session) {
			//int vendorId=1;
			int vendorId=(Integer)session.getAttribute("vendorId");
			System.out.println("vendorID:"+vendorId);
			try
			{
			List<MasterMetadata> lstMasterMetadata=metadataService.getMasterMetadata(vendorId);
			System.out.println("size..."+lstMasterMetadata.size());
			List<MasterMetadata> listVendorMasterMetadata=metadataService.getVendorSpecificMasterMetadata(vendorId);
			
			MetaDataModel metaDataModel=new MetaDataModel();
			session.setAttribute("listVendorMasterMetadata", listVendorMasterMetadata);
			session.setAttribute("lstMasterMetadata",lstMasterMetadata);
			model.addAttribute("lstMasterMetadata",lstMasterMetadata);
			model.addAttribute("listVendorMasterMetadata",listVendorMasterMetadata);
			//model.addAttribute("mastermetadata",new MasterMetadata());
			model.addAttribute("metadatavendorassoc",new MetadataVendorAssoc());
			
		
				model = commonControllerUtil.getModelObject(model);
			} catch (DAMException e) {
				
				e.printStackTrace();
				model.addAttribute("errorMessage",e.getMessage());
			}
			return "user_attribute_layout";
		}
		
		@RequestMapping(value="saveAttribute")
		public String saveVendorAttribute(@ModelAttribute(value="metadatavendorassoc")MetadataVendorAssoc metadataVendorAssoc,
				BindingResult result,Model model,HttpSession session,HttpServletRequest request){
			int vendorId=(Integer)session.getAttribute("vendorId");;
			//int packageId=1;
			String status=null;
			
			/*String[] h=request.getParameterValues("lstbox2");
			System.out.println("values ..in new list:"+h);*/
		try
		{
			List<Integer> vendorMetadataIdList=new ArrayList<Integer>();
			List<MasterMetadata> vendorMetadataList=(List<MasterMetadata>) session.getAttribute("listVendorMasterMetadata");
			System.out.println("vendorList:"+vendorMetadataList);
			for(MasterMetadata masterMetaData:vendorMetadataList){
				
				int masterMetadataId=masterMetaData.getMaster_metadata_id();
				vendorMetadataIdList.add(masterMetadataId);
			}
			System.out.println("old list..."+vendorMetadataIdList);
			List<Integer> metadataList=metadataVendorAssoc.getMetadataList();
			System.out.println("new list:"+metadataList);
			
			metadataList.removeAll(vendorMetadataIdList);
			System.out.println("updated list..."+metadataList);
			
			List<MetadataVendorAssoc> metadataVendorList=new ArrayList<MetadataVendorAssoc>() ;
			  
			for(int count=0;count<metadataList.size();count++){
				
				MetadataVendorAssoc mAssoc=new MetadataVendorAssoc();
				mAssoc.setMaster_metadata_id(metadataList.get(count));
				mAssoc.setVendor_id(vendorId);
				metadataVendorList.add(mAssoc);
			}
			System.out.println("m list..."+metadataVendorList);
			
			/*int id=metadataVendorAssoc.getMaster_metadata_id();
			System.out.println("id of added thing"+id);*/
			/*metadataVendorAssoc.setVendor_id(vendorId);*/
			status=metadataService.saveVendorAttribute(metadataVendorList);
			System.out.println("back in controller...");
		
				model = commonControllerUtil.getModelObject(model);
				
				List<MasterMetadata> lstMasterMetadata=metadataService.getMasterMetadata(vendorId);
				System.out.println("size..."+lstMasterMetadata.size());
				List<MasterMetadata> listVendorMasterMetadata=metadataService.getVendorSpecificMasterMetadata(vendorId);
				model.addAttribute("lstMasterMetadata",lstMasterMetadata);
				model.addAttribute("listVendorMasterMetadata",listVendorMasterMetadata);
				model.addAttribute("addSuccessMessage", "Attribute Added Successfully");
			} catch (DAMException e) {
				
				e.printStackTrace();
			//	throw new DAMException(e.getMessage());
				model.addAttribute("errorMessage",e.getMessage());
				
			}
			return "user_attribute_layout";
		}
		
		/**************************************Admin********************************************/
		
		
		@RequestMapping(value="/addAttribute",method=RequestMethod.POST)	
		public String saveAttribute(@ModelAttribute(value="masterMetadata")MasterMetadata masterMetadata,
				BindingResult result,Model model,HttpSession session){
			
			List<String> attributeValues=new ArrayList<String>();
			
			try {	
			System.out.println("Coming Inside the Controller");
			System.out.println("AttributeName:"+masterMetadata.getMaster_metadata_name());
			int metadataAttrTypeId=masterMetadata.getMetadata_attr_type_id();
			System.out.println("metadataAttrTypeId:"+metadataAttrTypeId);
			System.out.println("values..."+masterMetadata.getMetadataValues());
			
			if( metadataAttrTypeId==1 && masterMetadata.getMetadataValues()==null ){
				
				metadataService.saveAttribute(masterMetadata);
				model.addAttribute("createSuccessMessage", "Attribute Added Successfully");
			}
			if(metadataAttrTypeId>1){
					
					String values[]=masterMetadata.getMetadataValues().split(",");
					System.out.println("values array.."+values); 
					for(String value:values){
						System.out.println("values..:"+value);
						attributeValues.add(value);
					}
					//int metadataAttrTypeId=masterMetadata.getMetadata_attr_type_id();
					System.out.println("id:"+metadataAttrTypeId +"\t" + "AttributeName:"+masterMetadata.getMaster_metadata_name());
					metadataService.saveAttributeValues(masterMetadata, attributeValues);
					model.addAttribute("createSuccessMessage", "Attribute Added Successfully");
				
			}
			/*String values[]=masterMetadata.getMetadataValues().split(",");
			System.out.println("values array.."+values); 
			
			for(String value:values){
				System.out.println("values..:"+value);
				attributeValues.add(value);
			}
			int metadataAttrTypeId=masterMetadata.getMetadata_attr_type_id();
			System.out.println("id:"+metadataAttrTypeId +"\t" + "AttributeName:"+masterMetadata.getMaster_metadata_name());
			if(metadataAttrTypeId==1){
				metadataService.saveAttribute(masterMetadata);
			}
			else{
				metadataService.saveAttributeValues(masterMetadata, attributeValues);
			}*/
		
				model = commonControllerUtil.getModelObject(model);
				//List<MetadataAttributeTypes> attributesList=(List<MetadataAttributeTypes>) session.getAttribute("attributeList");
				List<Vendor> vendorList=(List<Vendor>) session.getAttribute("vendorList");
				model.addAttribute("vendorList",vendorList );
		     	//model.addAttribute("metadatamodel", attributesList);
		     	model.addAttribute("vendor",new Vendor());
				
				List<MetadataAttributeTypes> metadataList=(List<MetadataAttributeTypes>) session.getAttribute("metadataList");
		    	model.addAttribute("metadataList",metadataList);
				
			} catch (DAMException e) {
				
				e.printStackTrace();
				//throw new DAMException(e.getMessage());
				model.addAttribute("errorMessage",e.getMessage());
			}
			
			return "add_attribute_layout1";
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
			//private MetadataService metadataService;
		  //  List<MetadataAttributeTypes> metadataList=new ArrayList<MetadataAttributeTypes>();
		    
			
		   /* @RequestMapping(value = "/titlehome", method = RequestMethod.GET)
			public String metadataList(Model model) {

		    	System.out.println("list size"+metadataList.size());
		    	metadataList= metadataService.getAttributeType();
		    	model.addAttribute("metadataList",metadataList);
				model.addAttribute("masterMetadata",new MasterMetadata());
				
				
				List<MetadataAttributeTypes> attributesList = metadataService.getAttributeTypeList();
				
		     	model.addAttribute("metadatamodel", attributesList);
		     	try {
					model = commonControllerUtil.getModelObject(model);
				} catch (DAMException e) {
					
					e.printStackTrace();
				}
				
				return "add_attribute_layout";
		    	
			}*/
	/*			
	    @RequestMapping(value = "/addAttribute", method = RequestMethod.GET)
		public String metadataList(Model model) throws DAMException {


			List<MetadataAttributeTypes> metadataList=new ArrayList<MetadataAttributeTypes>();
	    	//System.out.println("list size"+metadataList.size());
	    	metadataList= metadataService.getAttributeType();
	    	model.addAttribute("metadataList",metadataList);
			model.addAttribute("masterMetadata",new MasterMetadata());
			 model.addAttribute("vendor",new Vendor());
			
			List<MetadataAttributeTypes> attributesList = metadataService.getAttributeTypeList();
			
	     	model.addAttribute("metadatamodel", attributesList);
	     	try {
				model = commonControllerUtil.getModelObject(model);
			} catch (DAMException e) {
				
				e.printStackTrace();
			}
			
			return "add_attribute_layout1";
	    	
		}
		    */
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/********************************************************/
		
			/*@RequestMapping(value="/addAttribute",method=RequestMethod.POST)	
			public String saveAttribute(@ModelAttribute(value="masterMetadata")MasterMetadata masterMetadata,
					BindingResult result,Model model,HttpSession session)throws DAMException{
				try {	
				System.out.println("Coming Inside the Controller");
				System.out.println("AttributeName: controller::"+masterMetadata.getMaster_metadata_name());
				System.out.println("AttributeType:"+masterMetadata.getMetadata_attr_type_id());
				System.out.println("values..."+masterMetadata.getMetadataValues());
				String values[]=masterMetadata.getMetadataValues().split(",");
				System.out.println("values array.."+values); 
				List<String> attributeValues=new ArrayList<String>();
				for(String value:values){
					System.out.println("values..:"+value);
					attributeValues.add(value);
				}
				int metadataAttrTypeId=masterMetadata.getMetadata_attr_type_id();
				System.out.println("id:"+metadataAttrTypeId +"\t" + "AttributeName:"+masterMetadata.getMaster_metadata_name());
				if(metadataAttrTypeId==1){
					metadataService.saveAttribute(masterMetadata);
				}
				else{
					metadataService.saveAttributeValues(masterMetadata, attributeValues);
				}
			
					model = commonControllerUtil.getModelObject(model);
					//List<MetadataAttributeTypes> attributesList=(List<MetadataAttributeTypes>) session.getAttribute("attributeList");
					List<Vendor> vendorList=(List<Vendor>) session.getAttribute("vendorList");
					model.addAttribute("vendorList",vendorList );
			     	//model.addAttribute("metadatamodel", attributesList);
			     	model.addAttribute("vendor",new Vendor());
					model.addAttribute("createSuccessMessage", "Attribute Added Successfully");
					List<MetadataAttributeTypes> metadataList=(List<MetadataAttributeTypes>) session.getAttribute("metadataList");
			    	model.addAttribute("metadataList",metadataList);
					
				} catch (DAMException e) {
					
					e.printStackTrace();
					throw new DAMException(e.getMessage());
				}
				
				return "add_attribute_layout1";
			}*/
	
	
			/* @RequestMapping(value="/getmastermetadata",method=RequestMethod.POST)
			    public String getMasterMetadata(@ModelAttribute(value="vendor")Vendor vendor,Model model,
			    		BindingResult result,HttpSession session)throws DAMException{
				 try
				 {
			    	int vendorId=vendor.getVendor_id();
			    	List<MasterMetadata> listVendorMasterMetadata=metadataService.getVendorSpecificMasterMetadata(vendorId);
			    	session.setAttribute("vendorId", vendorId);
			    	List<Vendor> vendorList=(List<Vendor>) session.getAttribute("vendorList");
			    	model.addAttribute("masterMetadataList", listVendorMasterMetadata);
			    	model.addAttribute("vendorList",vendorList);
			    	model.addAttribute("vendor", new Vendor());
			    	
			   
						model = commonControllerUtil.getModelObject(model);
					} catch (DAMException e) {
						
						e.printStackTrace();
						throw new DAMException(e.getMessage());
					}
			    	return "add_attribute_layout";
			    }*/


	/* @RequestMapping(value = "/titlehome", method = RequestMethod.GET)
				public String metadataList(Model model,HttpSession session)throws DAMException {

			  try
			  {
				  List<Vendor> vendorList=new ArrayList<Vendor>();
			  
			    	vendorList=metadataService.getVendors();
			    	if(vendorList.size()==0){
			    	
			    		model.addAttribute("notitle", "No records");
			    	}
			    	System.out.println("vendorlist size"+vendorList.size());
			    	
			    	model.addAttribute("vendorList",vendorList);
				    model.addAttribute("vendor",new Vendor());
					session.setAttribute("vendorList", vendorList);
					
					
			  
						model = commonControllerUtil.getModelObject(model);
					} catch (DAMException e) {
						
						e.printStackTrace();
						throw new DAMException(e.getMessage());
					}
					
					return "add_attribute_layout";
			    	
				}
	 */
	 
	 
	 @RequestMapping(value = "/recorddescription", method=RequestMethod.GET)
		public String record(@ModelAttribute(value="fileName")String fileName, MediaPackageFiles mediaPackageFiles,
				BindingResult result,Model model,HttpSession session,HttpServletRequest request)
	 {
		
		 
		 try
		 {
			 System.out.println("In Record Controller");
	
			String FileName=request.getParameter("fileName");
			//int packageId=Integer.parseInt(request.getParameter("id"));
			System.out.println("FileName*****"+FileName);
		  List<MediaPackageFiles> recordList=new ArrayList<MediaPackageFiles>();
		  recordList=metadataService.getFileType(FileName);
		  System.out.println("recordList size"+recordList.size());
		  
	
		 model.addAttribute("recordList",recordList);
		

				model = commonControllerUtil.getModelObject(model);
			} catch (DAMException e) {
				
				e.printStackTrace();
				//throw new DAMException(e.getMessage());
				model.addAttribute("errorMessage",e.getMessage());
			}
		 return"recorddescription";

}
	 
	 
	 
	 @RequestMapping(value = "/displayallpackages")
		public String displayPackages(Model model,HttpSession session)
		{
			System.out.println("Coming inside the searchpackages");
			
			
		try
		{
			
			List<MediaPackage> metadatasearchPack = new ArrayList<MediaPackage>();
			
			metadatasearchPack=metadataService.displayPackgeNames();


			List<MediaPackageFiles> packageFileList=new ArrayList<MediaPackageFiles>();		
		    for(MediaPackage m:metadatasearchPack)
		    {
		    	int packageId=m.getMedia_package_id();
		    	System.out.println("pId"+packageId);
		    	packageFileList=metadataService.getFileList(packageId);
		    	m.setPackageFileList(packageFileList);
		    }
		    
		    model.addAttribute("packageFileList", packageFileList);
		    //session.setAttribute("packageFileList", packageFileList);
		    
			if(metadatasearchPack.size() == 0){
				model.addAttribute("notitle","No Package Found");
			}
		
				model = commonControllerUtil.getModelObject(model);
				model.addAttribute("packagesList",metadatasearchPack);
				session.setAttribute("packagesList",metadatasearchPack);
				//model=commonControllerUtil.getModelObject(model, vendorId);
			} catch (DAMException e) {
				
				e.printStackTrace();
				//throw new DAMException(e.getMessage());
				model.addAttribute("errorMessage",e.getMessage());
			}
			
				return "search_results";
		}
	 @RequestMapping(value = "/editmetadataAjax")
		public String editMetaDataAjax(Model model,HttpSession session,HttpServletRequest request)
	    {
		 	
	    try
	    {System.out.println("Coming inside the editmetadata");
	    	//int packageId=1;
	    	int packageId=(Integer)session.getAttribute("packageId");
	    	System.out.println("package Id"+packageId);
	    	String packageName=(String)session.getAttribute("packageName");
	    	
	    	//editMapObj = metadataServic
	    	System.out.println("package name"+packageName);
	        List<VendorMetadata>getVendorMetadataFields=new ArrayList<VendorMetadata>();
	        int vendorId=metadataService.getvendorId(packageId);
	    	getVendorMetadataFields = metadataService.getgetVendorMetadataFields(vendorId);
	    	System.out.println("getVendorMetadataFields.getMetadata_attr_type()**************"+getVendorMetadataFields.toString());
	    	System.out.println("values in controller"+getVendorMetadataFields.get(1).getAttributevaluesMap().toString());
	    	VendorMetadata vm=new VendorMetadata();
	    
	        for(VendorMetadata vendorValues:getVendorMetadataFields)
	        {
		    System.out.println("vendorValues.getMaster_metadata_id()$$$"+vendorValues.getMaster_metadata_id());
		    System.out.println("vendorValues.getMaster_metadata_name()$$$"+vendorValues.getMaster_metadata_name());
		    System.out.println("vendorValues.getMetadata_attr_type()()$$$"+vendorValues.getMetadata_attr_type());
		    System.out.println("getMetadata_attribute_value()$$$"+vendorValues.getMetadata_attribute_value());
	       
		   
		  
	        
	        }
	    
	        
	    	@SuppressWarnings("rawtypes")
	    	 List<PackageMetadata>mediapakList=new ArrayList<PackageMetadata>();
			 mediapakList=metadataService.getMediaPackageMetadataDetails(packageId);
	    	 session.setAttribute("packageId", packageId);
	    	 String mValueObj=null;
	    	 for(PackageMetadata ml:mediapakList)
				{
				mValueObj=ml.getMetadata_attr_value();
				System.out.println("++++++mValueObj"+mValueObj);	
				getVendorMetadataFields.get(0).setMetadata_attr_value(mValueObj);
				}
				
	    	System.out.println("mediapakmap#######################"+mediapakList.size());
	    	System.out.println("mediapakList values"+mediapakList.toString());
	        Map<Integer,String> metadataMap=new HashMap<Integer,String>();
	        Map<Integer,String> editMapObj=new HashMap<Integer, String>();
	        Map<Integer,StringBuffer> valuesMap=new HashMap<Integer,StringBuffer>();
	        System.out.println("getVendorMetadataFields.size() size of:"+getVendorMetadataFields.size());
	    	
	    	for(int count=0 ; count<getVendorMetadataFields.size();count++)
	    		
	    	{
	    		System.out.println("entered into for loop....");
	    		System.out.println("getVendorMetadataFields.get().getMaster_metadata_id()"+getVendorMetadataFields.get(count).getMaster_metadata_id());
	    		int mastermetadaId=0;
	    		String value=null;
		    	for(int count1=0; count1<mediapakList.size();count1++)
		    	{
		    		if(getVendorMetadataFields.get(count).getMaster_metadata_id() == mediapakList.get(count1).getMaster_metadata_id())
		    		{
		    			System.out.println("entered into for loop1.... "+count);
		    			mastermetadaId=getVendorMetadataFields.get(count).getMaster_metadata_id();
		    			String metadataValue=mediapakList.get(count1).getMetadata_attr_value();
		    			
		    			valuesMap=getVendorMetadataFields.get(count).getAttributevaluesMap();
		    			metadataMap.put(mastermetadaId, metadataValue);
		    			
		    		}
		    		
	    			
	    		System.out.println("metadataMap  777777"+metadataMap.toString());
	    		for (Entry<Integer, StringBuffer> entry : valuesMap.entrySet())
	        	{
	        	    System.out.println(entry.getKey() + "/" + entry.getValue());
	        	}
	    	}
	    	}
		    	
	    		for(VendorMetadata v:getVendorMetadataFields)
	    		{
	    		
	    			System.out.println("value of v.getMaster_metadata_id()***"+v.getMaster_metadata_id());
	    			System.out.println("value of metadataMap.get(v.getMaster_metadata_id()***"+metadataMap.get(v.getMaster_metadata_id()));
	    			System.out.println("value of metadataMap.get(Integer v.getMaster_metadata_id()***"+metadataMap.get(new Integer(v.getMaster_metadata_id())));
		    		if (metadataMap.get(v.getMaster_metadata_id())==null){
		    			
		    			metadataMap.put(v.getMaster_metadata_id(), null);	
		    		}
		    	}
	    		
	    		
	    		
	    		System.out.println("size of metadataMap***"+metadataMap.size());
	    	System.out.println("++++++++++++++++++");
	    	System.out.println(metadataMap.entrySet());
	    	MetaDataModel metaDataModel = new MetaDataModel();
	    	
	    	System.out.println("metadataMap values*********"+metadataMap.toString());
	    	
	    	metaDataModel.setMetadataMap(metadataMap);
	    	System.out.println("size of metadataMap !!!!!!!!"+metadataMap.size());
	    	
	    	
	    	
	    	
	    	model.addAttribute("metaDataModel", metaDataModel);
	    	model.addAttribute("vendorList", getVendorMetadataFields);
	        model.addAttribute("packageList", mediapakList);
	        model.addAttribute("valuemodel",valuesMap);
	        model.addAttribute("packageName", packageName);
	        

	        
	        //model.addAttribute("", );
	        
	    
				model = commonControllerUtil.getModelObject(model);
				
			} catch (DAMException e) {
				
				e.printStackTrace();
				//throw new DAMException(e.getMessage());
				model.addAttribute("errorMessage",e.getMessage());
			}
		return "editmetadatadetails";
	}
	 
	 
	 @RequestMapping(value="/metadata",method=RequestMethod.POST)
	    public String getMasterMetadata(@ModelAttribute(value="vendor")Vendor vendor,Model model,
	    		BindingResult result,HttpSession session){
		 try
		 {
	    	int vendorId=vendor.getVendor_id();
	    	List<MasterMetadata> listVendorMasterMetadata=metadataService.getVendorSpecificMasterMetadata(vendorId);
	    	session.setAttribute("vendorId", vendorId);
	    	List<Vendor> vendorList=(List<Vendor>) session.getAttribute("vendorList");
	    	model.addAttribute("masterMetadataList", listVendorMasterMetadata);
	    	model.addAttribute("vendorList",vendorList);
	    	model.addAttribute("vendor", new Vendor());
	    	
	   
				model = commonControllerUtil.getModelObject(model);
			} catch (DAMException e) {
				
				e.printStackTrace();
			//	throw new DAMException(e.getMessage());
				model.addAttribute("errorMessage",e.getMessage());
			}
	    	return "view_attribute_layout";
	    }
	 
	 
	 @RequestMapping(value="/getAllMasterMetadata",method=RequestMethod.GET)
	 public @ResponseBody String getAllMasterMetadata(@RequestParam(value="vendorName")int vendorId,HttpSession session,Model model) throws DAMException
		{
			String returnString = "";		
			try
			{
			System.out.println("vendorId------------>"+vendorId);
			List<MasterMetadata> listVendorMasterMetadata=metadataService.getVendorSpecificMasterMetadata(vendorId);
	    	session.setAttribute("vendorId", vendorId);
	    	System.out.println("master metadata list size::::"+listVendorMasterMetadata.size());
		
			Iterator iterator=listVendorMasterMetadata.iterator();
			while(iterator.hasNext())
			{
				
				MasterMetadata masterMetadata=(MasterMetadata) iterator.next();
				System.out.println("MasterMetadata Names..................from  Controller"+masterMetadata.getMaster_metadata_name());
				returnString +="<tr><td>"+masterMetadata.getMaster_metadata_name()+"</td></tr>";
				
			}
			
			returnString+="<tr><td><a href='useraddattribute.obj'><img src='resources/images/Btn_AddReceiver1.JPG' width='70' height='25'/></a></td></tr>";
			System.out.println("master list:"+returnString);
			}
			catch(DAMException damException)
			{
				//throw new DAMException(damException.getMessage());
				model.addAttribute("errorMessage",damException.getMessage());
			}
	      return returnString;
		}
}


