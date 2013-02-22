package com.igate.dam.publish.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.metadata.dto.Vendor;
import com.igate.dam.publish.dao.PublishProfileDAO;
import com.igate.dam.publish.dto.PublishProfile;



/**
 * @author mj802966
 *
 */
@Service("publishProfileServiceImpl")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class PublishProfileServiceImpl implements PublishProfileService{

	@Autowired
	PublishProfileDAO publishProfileDAO;

	public PublishProfileDAO getPublishProfileDAO() {
		return publishProfileDAO;
	}

	
	public void setPublishProfileDAO(PublishProfileDAO publishProfileDAO) {
		this.publishProfileDAO = publishProfileDAO;
	}

	@Override
	public List<String> getVendorNames() throws DAMException{
		List vendorList=publishProfileDAO.getVendorNames();
		return vendorList;
	}


	@Override
	public List<String> getProfileNames() throws DAMException{
		List profileName=publishProfileDAO.getProfileNames();
		return profileName;
	}

	public void createProfile(PublishProfile profile)throws DAMException
	{
		publishProfileDAO.createProfile(profile);
	}


	@Override
	public List<Vendor> getVendorid() throws DAMException{
		List<Vendor> vendorlist=publishProfileDAO.getVendorid();
		return vendorlist;
	}


	@Override
	public List<PublishProfile> listProfiles()throws DAMException {
		List<PublishProfile> profiles=publishProfileDAO.listProfiles();
		return profiles;
	}
	  public void deleteProfile(int profileid) throws DAMException{
		    publishProfileDAO.deleteProfile(profileid);
	    }

	    public PublishProfile updateProfile(int profileid)throws DAMException  {
	    	PublishProfile  profile = publishProfileDAO.updateProfile(profileid);
		    return profile;
	    }


	    public void profileSave(PublishProfile  profile) throws DAMException{
		  publishProfileDAO.profileSave(profile);
	   }
}
