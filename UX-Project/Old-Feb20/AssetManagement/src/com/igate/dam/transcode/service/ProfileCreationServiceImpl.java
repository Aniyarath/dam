package com.igate.dam.transcode.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.transcode.dao.ProfileCreationDAO;
import com.igate.dam.transcode.dto.ProfileCreation;
import com.igate.dam.transcode.dto.Vendors;

/**
 * @author mj802966
 *
 */
@Service("profileCreationServiceImpl")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class ProfileCreationServiceImpl implements ProfileCreationService{

	
	@Autowired
	private ProfileCreationDAO profileCreationDAO;
	
	public ProfileCreationDAO getProfileCreationDAO() {
		return profileCreationDAO;
	}
	public void setProfileCreationDAO(ProfileCreationDAO profileCreationDAO) {
		this.profileCreationDAO = profileCreationDAO;
	}

	@Override
    public List<String> getVendorNames() throws DAMException{
        List<String> vendorNameList = new ArrayList<String>();
        vendorNameList = profileCreationDAO.getVendorNames();
		return vendorNameList;
	}


	public void createProfile(ProfileCreation profile) throws DAMException {
		 System.out.println("----------------service"+profile.getProfileName());
		 profileCreationDAO.createProfile(profile);
	}

	
	@Override
    public List<Vendors> getvendor() throws DAMException{
	   List<Vendors> vendorList = new ArrayList<Vendors>();
	   vendorList= profileCreationDAO.getvendor();
	   return vendorList;
    }



    public List<ProfileCreation> listProfiles() throws DAMException{
	     List<ProfileCreation> profileList = new ArrayList<ProfileCreation>();
	     profileList = profileCreationDAO.listProfiles();
	     System.out.println("ser"+ profileList.size());
	     return profileList;
    }

    public void deleteProfile(int profileid) throws DAMException{
	    profileCreationDAO.deleteProfile(profileid);
    }

    public ProfileCreation updateProfile(int profileid) throws DAMException {
	    ProfileCreation profile = profileCreationDAO.updateProfile(profileid);
	    return profile;
    }


    public void profileSave(ProfileCreation profile) throws DAMException{
	   profileCreationDAO.profileSave(profile);
   }
    
    public List<String> getProfileNames() throws DAMException{
		
		 List<String> profileList = new ArrayList<String>();
		 profileList=profileCreationDAO.getProfileNames();
		 return profileList;
	}
}
