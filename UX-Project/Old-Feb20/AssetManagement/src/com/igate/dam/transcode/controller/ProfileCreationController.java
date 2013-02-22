
package com.igate.dam.transcode.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.axis.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.common.framework.util.CommonControllerUtil;
import com.igate.dam.transcode.dto.ProfileCreation;
import com.igate.dam.transcode.dto.Vendors;
import com.igate.dam.transcode.service.ProfileCreationService;

/**
 * @author mj802966
 *
 */
@Controller
@Scope("session")
public class ProfileCreationController {

	@Autowired
	private CommonControllerUtil commonControllerUtil;
	@Autowired
	ProfileCreationService profileCreationService;
	List<String> profileList=new ArrayList<String>();
	List<ProfileCreation> profiles=new ArrayList<ProfileCreation>();
	List<Vendors> vendorList = new ArrayList<Vendors>();
	List<String> profileNameList=new ArrayList<String>();
	
/**********redirecting to profile management Page***************/

	@RequestMapping(value = "/profilemgmnt", method = RequestMethod.GET)
	public String profileManagement(Model model) {
		
      	model.addAttribute("profile", new ProfileCreation());
		return "profile_layout";
	}

/******************************view add profile page****************************************************************/
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String addProfile(Model model,
			@ModelAttribute(value = "profile") ProfileCreation profile,
			BindingResult bindingResult){
try
{
		System.out.println("-----------------Create page------------------------------");
		model.addAttribute("profile", new ProfileCreation());
		profileList = profileCreationService.getVendorNames();
		Iterator<String> iterator = profileList.iterator();
		while (iterator.hasNext()) {
			String profileName = iterator.next();
			System.out.println(profileName);
		}
		model.addAttribute("profileList", profileList);
}
catch(DAMException damException)
{
	//throw new DAMException(damException.getMessage());
	model.addAttribute("errorMessage",damException.getMessage());
}
		return "create_profile_layout";

	}
 
/*********************************show available profiles*******************************************************************/
	

	
	@RequestMapping(value = "display", method = RequestMethod.GET)
	public String displayProfile(Model model,
			@ModelAttribute(value = "profile") ProfileCreation profile,
			BindingResult bindingResult){
		String viewName=null;
try
{
		profiles = profileCreationService.listProfiles();
		System.out.println("******************" + profiles.size());
		if (profiles.size() > 0) {

			model.addAttribute("profileList", profiles);
			viewName= "display_layout";
		} else {
			viewName= "profile_layout";
		}
}
catch(DAMException e)
{
	//throw new DAMException(e.getMessage());
	model.addAttribute("errorMessage",e.getMessage());
}
return viewName;
	}
	
/*****************************************************************************************************/
	

	@RequestMapping(value = "addprofile", method = RequestMethod.GET)
	public String createProfile(
			@RequestParam(value = "vendorName") String name, Model model,
			@ModelAttribute(value = "profile") ProfileCreation profile,
			BindingResult bindingResult,HttpSession session) {
		
		int count=0;
		try {
			model = commonControllerUtil.getModelObject(model);
		
		System.out.println(profile.getProfileName());
		System.out.println("name,................" + name);
		
		    profileNameList=profileCreationService.getProfileNames();
	        Iterator< String> iterator1=profileNameList.iterator();
	        
	    	while (iterator1.hasNext()) {
	    		String profileName=iterator1.next();
	    		System.out.println(" checking names....."+profileName);
	    		if(profileName.equalsIgnoreCase(profile.getProfileName())){
	    		 System.out.println("profile name already exist");
	    			// throw new DAMException("profile name already exist");
	    		 count=1;
	    		 model.addAttribute("errorMessage","Profile name already exist");
	    		}
	    	}
        System.out.println("count------->"+count);
		System.out.println("----------------" + profile.getProfileName());
		if(count==0)
		{
		vendorList = profileCreationService.getvendor();
		List<String>  vendorsList=(List<String>) session.getAttribute("profileList");
		model.addAttribute("profileList", vendorsList);
		Iterator<Vendors> iterator = vendorList.iterator();
		while (iterator.hasNext()) {
			Vendors vendor = iterator.next();
			if (vendor.getVendorName().equalsIgnoreCase(name)) {
				profile.setVendorid(vendor.getVendorId());
				System.out.println(vendor.getVendorId());
				System.out.println("**************" + profile.getVendorid());
			}
		}
		System.out.println(" before checking"+profile);
       
		profileCreationService.createProfile(profile);
		
		
		model.addAttribute("successMsg", "Profile Created Successfully");
		}
		List<ProfileCreation> displayList=profileCreationService.listProfiles();
		model.addAttribute("displayList", displayList);
		session.setAttribute("displayList", displayList);
		
} catch (DAMException e) {
			
			e.printStackTrace();
			 model.addAttribute("errorMessage",e.getMessage());
			
		}
		return "add_title";
	}

/***************************delete profile from data base*****************************************************************/

	@RequestMapping(value = "deleteprofile", method = RequestMethod.GET)
	public String deleteProfile(@RequestParam(value = "id") int id,
			Model model,
			@ModelAttribute(value = "profile") ProfileCreation profile,
			BindingResult bindingResult,HttpSession session) {
		try {
			model = commonControllerUtil.getModelObject(model);
		
		System.out.println("************" + id);
		
		profileCreationService.deleteProfile(id);
	    profiles=profileCreationService.listProfiles();
	    List<String>  vendorsList=(List<String>) session.getAttribute("profileList");
		model.addAttribute("profileList", vendorsList);
		model.addAttribute("displayList", profiles);
		model.addAttribute("tabId", 1);
		model.addAttribute("deleteSuccessMsg", "Profile Deleted Successfully");
} catch (DAMException e) {
			
			e.printStackTrace();
			model.addAttribute("errorMessage",e.getMessage());
			
		}

		return "add_title";

	}

/**********************Select the  profile to update*********************************************************/	



	@RequestMapping(value = "updateprofile", method = RequestMethod.GET)
	public String updateListProfile(@RequestParam(value = "id") int id,Model model) {
		try {
			model = commonControllerUtil.getModelObject(model);
		
		System.out.println("************" + id);
		
		ProfileCreation profile1 = profileCreationService.updateProfile(id);
		System.out.println("updateing profile------------" + profile1);
		model.addAttribute("updateprofile", profile1);
} catch (DAMException e) {
			
			e.printStackTrace();
			model.addAttribute("errorMessage",e.getMessage());
			//throw new DAMException(e.getMessage());
		}
		return "update_profile_layout";

	}

/*************************Updating the profile*******************************************/

	@RequestMapping(value = "profileupdate", method = RequestMethod.POST)
	public String profileSaveUpdate(
			@ModelAttribute(value = "updateprofile") ProfileCreation profile1,Model model,
			BindingResult bindingResult){
		int count=0;
		try {
			model = commonControllerUtil.getModelObject(model);
		
		  System.out.println("inside controller");
		  profileNameList=profileCreationService.getProfileNames();
	        Iterator< String> iterator1=profileNameList.iterator();
	        
	    	while (iterator1.hasNext()) {
	    		String profileName=iterator1.next();
	    		System.out.println(" checking names....."+profileName);
	    		if(profileName.equalsIgnoreCase(profile1.getProfileName())){
	    		 System.out.println("profile name already exist");
	    			// throw new DAMException("profile name already exist");
	    		 count=1;
	    		 model.addAttribute("errorMessage","Profile name already exist");
	    		}
	    	}
		System.out.println(">>>>>>>>>>>" + profile1);
		if(count==0)
		{
		profileCreationService.profileSave(profile1);
		model.addAttribute("successMsg", "Profile Updated Successfully");
		}
        } catch (DAMException e) {
			
			e.printStackTrace();
			model.addAttribute("errorMessage",e.getMessage());
		}
		return "update_profile_layout";

	}



}