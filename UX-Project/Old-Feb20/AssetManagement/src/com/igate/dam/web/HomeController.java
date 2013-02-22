package com.igate.dam.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

import com.igate.dam.app.model.Person;
import com.igate.dam.app.service.impl.UserService;
import com.igate.dam.common.exception.DAMException;
import com.igate.dam.common.framework.util.CommonControllerUtil;
import com.igate.dam.metadata.dto.MasterMetadata;
import com.igate.dam.metadata.dto.MediaPackage;
import com.igate.dam.metadata.dto.MetadataAttributeTypes;
import com.igate.dam.metadata.dto.Vendor;
import com.igate.dam.metadata.service.MetadataService;
import com.igate.dam.model.UserProfile;
import com.igate.dam.transcode.dto.ProfileCreation;
import com.igate.dam.transcode.service.ProfileCreationService;


@Controller
@Scope("session")
public class HomeController 
{

	List<String> profileList=new ArrayList<String>();
	List<ProfileCreation> profiles=new ArrayList<ProfileCreation>();
	@Autowired

	ProfileCreationService profileCreationService;
	
	@Autowired
	private CommonControllerUtil commonControllerUtil;
	
	@Autowired
	private MetadataService metadataService;
	
	@Autowired
	private UserService userService;
	
	public MetadataService getMetadataService() {
		return metadataService;
	}

	public void setMetadataService(MetadataService metadataService) {
		this.metadataService = metadataService;
	}

	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String loadHome(@RequestParam(value="username")String name,Model model,HttpServletRequest httpServletRequest,@ModelAttribute(value="userProfile") 
			UserProfile userProfile,BindingResult bindingResult,HttpSession session)
	{
		String viewName=null;
	try
	{
		
		System.out.println("In Controller");
		System.out.println("In  login Controller"+userProfile.getVendorId()+userProfile.getVendorName());
		model.addAttribute("userProfile",userProfile);
		
		//String name=userProfile.getVendorName();
		//System.out.println("name:::::"+name);
		httpServletRequest.getSession().setAttribute("username",name);
		
		
		System.out.println("entered into login controller");
		
		String username=httpServletRequest.getParameter("username");
		
		String password=httpServletRequest.getParameter("password");
		System.out.println(username+password);
		
		Person personObj=userService.getUserDetails(username);
		String dbuserName=personObj.getFIRST_NAME();
		String dbpassword=personObj.getPASSWORD();
		int userId=personObj.getPERSON_ID();
		
		int vendorId=personObj.getVendor_id();
		System.out.println("vendor_id------------>"+vendorId);
		
		session.setAttribute("vendorId", vendorId);
		
		System.out.println("username"+dbuserName+"password:"+dbpassword+userId+userId+"");
		System.out.println("userId:"+userId);
		List typeList=new ArrayList();
		Map<String, String> accMap=new HashMap<String, String>();
		
		if(username.equals(dbuserName) && dbpassword.equals(password))
		{
			
			accMap=userService.getuserType(userId);
			model.addAttribute("accMap",accMap);
			System.out.println("accMap"+accMap.toString());
			System.out.println("login success");
			
			for(Map.Entry<String, String> entry:accMap.entrySet())
			{
				String userPermission=entry.getValue();
				System.out.println("userPermission*"+userPermission);
				session.setAttribute("userPermission", userPermission);
				
			}
			
			System.out.println("********************"+name);
			
				
				String names=userProfile.getVendorName();
				System.out.println("name:::::"+names);
				
				model = commonControllerUtil.getModelObject(model);
		
				
				
			 viewName = "tiles_home_layout";
			
			
			
		}
		else
		{
			System.out.println("credentials are wrong");
			String loginFails="Invalid Username or Password";
			session.setAttribute("invalid_user", loginFails);
	 viewName = "login";
		}
		
		
		
		}
		catch (DAMException damException) {
			String exceptionMessage=damException.getMessage();
			if(exceptionMessage.equalsIgnoreCase("No User Exists.")||exceptionMessage.equalsIgnoreCase("Username doesnot exists")){
				model.addAttribute("errorMessage","Incorrect loginId or Password");
				 viewName = "login";
			}
		}
		
		return viewName;
		
	}
	
	
	
	
	@RequestMapping(value="/gohome",method=RequestMethod.GET)
	public String goHome(Model model,HttpServletRequest httpServletRequest)
	{
		String viewName = "tiles_home_layout";
		try {
			model = commonControllerUtil.getModelObject(model);
		} catch (DAMException e) {
			
			e.printStackTrace();
		}
		return viewName;
	}
	
	/*@RequestMapping(value="/titlehome")
	public String loadMetadata(Model model,HttpServletRequest httpServletRequest)
	{
		String viewName = "add_title";
		System.out.println("In Controller");
		return viewName;
	}*/
	
	@RequestMapping(value="/login")
	public String loadLogin(Model model,HttpServletRequest httpServletRequest)
	{
		String viewName = "login";
		return viewName;
	}
	
	
	@RequestMapping(value="/logout")
	public String loadLoginPage(Model model,HttpServletRequest httpServletRequest)
	{
		String viewName = "login";
		return viewName;
	}
	
	@RequestMapping(value = "/adminhome")
	public String loadAdmin(Model model,
			@ModelAttribute(value = "profile") ProfileCreation profile,
			BindingResult bindingResult,HttpSession session) {
		String viewName = "admin_home";
		try {
			model = commonControllerUtil.getModelObject(model);
		
		System.out.println("In Controller");
		System.out
				.println("-----------------Create page------------------------------");
		model.addAttribute("profile", new ProfileCreation());
		profileList = profileCreationService.getVendorNames();
		Iterator<String> iterator = profileList.iterator();
		while (iterator.hasNext()) {
			String profileName = iterator.next();
			System.out.println(profileName);
		}
		model.addAttribute("profileList", profileList);
		session.setAttribute("profileList",profileList );
		profiles = profileCreationService.listProfiles();
		session.setAttribute("displayList",profiles);
		System.out.println("******************" + profiles.size());
	

		model.addAttribute("displayList", profiles);
			
} catch (DAMException e) {
			
			e.printStackTrace();
			//throw new DAMException(e.getMessage());
			model.addAttribute("errorMessage",e.getMessage());
		}
		return viewName;
	}
	
	@RequestMapping(value="/metadatahome")
	public String loadMetaData(Model model,HttpServletRequest httpServletRequest)
	{
		String viewName="addattribute";
		return viewName;
	}
	
	
	@RequestMapping(value="/titlehome")
	public String loadMetadata(Model model,HttpServletRequest httpServletRequest,HttpSession session) 
	{
		List<MetadataAttributeTypes> metadataList=new ArrayList<MetadataAttributeTypes>();
		String viewName = "add_attribute_layout";
     	try {
		metadataList= metadataService.getAttributeType();
		System.out.println("list size"+metadataList.size());
		session.setAttribute("metadataList", metadataList);
    	model.addAttribute("metadataList",metadataList);
		model.addAttribute("masterMetadata",new MasterMetadata());
		
		List<Vendor> vendorList=new ArrayList<Vendor>();
    	vendorList=metadataService.getVendors();
    	model.addAttribute("vendorList",vendorList);
	    model.addAttribute("vendor",new Vendor());
		session.setAttribute("vendorList", vendorList);
		
		List<MetadataAttributeTypes> attributesList = metadataService.getAttributeTypeList();
		session.setAttribute("attributeList", attributesList);
     	model.addAttribute("metadatamodel", attributesList);

			model = commonControllerUtil.getModelObject(model);
		} catch (DAMException e) {
			
			e.printStackTrace();
			model.addAttribute("errorMessage",e.getMessage());
		}
		
		return "add_attribute_layout1";
		
	}
	
}
