package com.igate.dam.publish.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.common.framework.util.CommonControllerUtil;
import com.igate.dam.metadata.dto.MediaPackage;
import com.igate.dam.metadata.dto.Vendor;

import com.igate.dam.publish.dto.PublishProfile;

import com.igate.dam.publish.service.PublishProfileService;




/**
 * @author mj802966
 *
 */
@Controller
public class PublishProfileController {
	
	
	@Autowired
	private CommonControllerUtil commonControllerUtil;
	@Autowired
	PublishProfileService publishProfileService;
	
	List<MediaPackage> mediaList = new ArrayList<MediaPackage>();

	@RequestMapping(value = "publishprofilegoto", method = RequestMethod.GET)
	public String profileManagement(Model model,@ModelAttribute(value = "profile") PublishProfile profile,
			BindingResult bindingResult,HttpSession session) throws DAMException {
		try {
      	model.addAttribute("profile", new PublishProfile());
      	List vendorList = publishProfileService.getVendorNames();
      	session.setAttribute("vendorList", vendorList);
      	model.addAttribute("vendorList",vendorList);
      	List<PublishProfile> profiles = publishProfileService.listProfiles();
      	 model.addAttribute("profileList", profiles);
      
			model = commonControllerUtil.getModelObject(model);
		} catch (DAMException e) {
			
			e.printStackTrace();
			throw new DAMException(e.getMessage());
		}
		return "publish_profile_layout";
	}

/******************************view add profile page****************************************************************/
	
	@RequestMapping(value = "publishcreate", method = RequestMethod.GET)
	public String addProfile(Model model,
			@ModelAttribute(value = "profile") PublishProfile profile,
			BindingResult bindingResult)throws DAMException {

		try
		{System.out.println("-----------------Create page------------------------------");
		model.addAttribute("profile", new PublishProfile());
		List vendorList = publishProfileService.getVendorNames();
		Iterator<String> iterator = vendorList.iterator();
		while (iterator.hasNext()) {
			String vendorName = iterator.next();
			System.out.println(vendorName);
		}
		model.addAttribute("vendorList",vendorList);
		}
		catch(DAMException e)
		{
			throw new DAMException(e.getMessage());
		}
		return "add_Publishprofile_layout";

	}



@RequestMapping(value = "addpublishprofile", method = RequestMethod.GET)
public String createProfile(
		@RequestParam(value = "vendorName") String name, Model model,
		@ModelAttribute(value = "profile")PublishProfile profile,
		BindingResult bindingResult,HttpSession session) throws DAMException{
	
	int count=0;
	try {
		model = commonControllerUtil.getModelObject(model);
	
	
	System.out.println(profile.getPublish_profile_name());
	System.out.println("name,................" + name);
	
	List profileNameList=publishProfileService.getProfileNames();
        Iterator< String> iterator1=profileNameList.iterator();
    	while (iterator1.hasNext()) {
    		String profileName=iterator1.next();
    		System.out.println(" checking names....."+profileName);
    		if(profileName.equalsIgnoreCase(profile.getPublish_profile_name())){
    		 System.out.println("profile name already exist");
    		 count=1;
    		 model.addAttribute("errorMessage","profile name already exist");
    		}
    	}
if(count==0)
{
	System.out.println("----------------" + profile.getPublish_profile_name());
	List<Vendor> vendorsList = publishProfileService.getVendorid();
	System.out.println("size"+vendorsList.size());
	Iterator<Vendor> iterator = vendorsList.iterator();
	while (iterator.hasNext()) {
		Vendor vendor = iterator.next();
		if (vendor.getVendor_name().equalsIgnoreCase(name)) {
			profile.setVendor_id(vendor.getVendor_id());
			System.out.println(vendor.getVendor_id());
			System.out.println("**************" + profile.getVendor_id());
		}
	}
	System.out.println(" before checking"+profile);
	publishProfileService.createProfile(profile);
	model.addAttribute("successMsg","publishing profile created successfully");
	
}	
    List<Vendor> vendorList=(List<Vendor>) session.getAttribute("vendorList");
  	model.addAttribute("vendorList",vendorList);
    List<PublishProfile> profiles = publishProfileService.listProfiles();
     model.addAttribute("profileList", profiles);
	
} catch (DAMException e) {
		
		e.printStackTrace();
		throw new DAMException(e.getMessage());
	}
	return "publish_profile_layout";
}
@RequestMapping(value = "publishprofiledisplay", method = RequestMethod.GET)
public String displayProfile(Model model,
		@ModelAttribute(value = "profile")PublishProfile profile,
		BindingResult bindingResult) throws DAMException{
try
{
List<PublishProfile> profiles = publishProfileService.listProfiles();
	System.out.println("******************" + profiles.size());
	if (profiles.size() > 0) {
        model.addAttribute("profileList", profiles);
		return "publish_Profiledisplay_layout";
	} else {
		model.addAttribute("message","no profiles found");
		return "publish_Profiledisplay_layout";
	}
}
catch(DAMException e)
{
	throw new DAMException(e.getMessage());
}
}

/***************************delete profile from data base*****************************************************************/

@RequestMapping(value = "deletepublishprofile", method = RequestMethod.GET)
public String deleteProfile(@RequestParam(value = "id") int id,
		Model model,
		@ModelAttribute(value = "profile") PublishProfile profile,
		BindingResult bindingResult,HttpSession session)throws DAMException {

	try {
		model = commonControllerUtil.getModelObject(model);
	
	System.out.println("************" + id);
	publishProfileService.deleteProfile(id);
	model.addAttribute("deletesuccessMsg","publishing profile deleted successfully");
	List<PublishProfile> profiles = publishProfileService.listProfiles();
	 model.addAttribute("profileList", profiles);
	 List<Vendor> vendorList=(List<Vendor>) session.getAttribute("vendorList");
	  	model.addAttribute("vendorList",vendorList);
	  	model.addAttribute("tabId", 1);
  	
} catch (DAMException e) {
		
		e.printStackTrace();
		throw new DAMException(e.getMessage());
	}
	return "publish_profile_layout";

}

/**********************Select the  profile to update*********************************************************/	



@RequestMapping(value = "updatepublishprofile", method = RequestMethod.GET)
public String updateListProfile(@RequestParam(value = "id") int id,Model model)throws DAMException {

	System.out.println("************" + id);
	try {
		model = commonControllerUtil.getModelObject(model);
	
	PublishProfile profile1 = publishProfileService.updateProfile(id);
	System.out.println("updateing profile------------" + profile1);
	model.addAttribute("updatePublishprofile", profile1);
} catch (DAMException e) {
		
		e.printStackTrace();
		throw new DAMException(e.getMessage());
	}
	return "update_Publishprofile_layout";

}

/*************************Updating the profile*******************************************/

@RequestMapping(value = "publishprofileupdate", method = RequestMethod.POST)
public String profileSaveUpdate(
		@ModelAttribute(value = "updatePublishprofile") PublishProfile profile1,Model model,
		BindingResult bindingResult) throws DAMException{
	
	int count=0;
	try {
		model = commonControllerUtil.getModelObject(model);
	
	System.out.println("inside controller");

	
	System.out.println(">>>>>>>>>>>" + profile1);
	
	List profileNameList=publishProfileService.getProfileNames();
    Iterator< String> iterator1=profileNameList.iterator();
	while (iterator1.hasNext()) {
		String profileName=iterator1.next();
		System.out.println(" checking names....."+profileName);
		if(profileName.equalsIgnoreCase(profile1.getPublish_profile_name())){
		 System.out.println("profile name already exist");
		 count=1;
		 model.addAttribute("errorMessage","profile name already exist");
		}
	}
	if(count==0)
	{
	publishProfileService.profileSave(profile1);
	model.addAttribute("successMsg","publishing profile updated successfully");
	}
	
} catch (DAMException e) {
		
		e.printStackTrace();
		throw new DAMException(e.getMessage());
	}
	return "update_Publishprofile_layout";

}



/********************************************************************************/





}