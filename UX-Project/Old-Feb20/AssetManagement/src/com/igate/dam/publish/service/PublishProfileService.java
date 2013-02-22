package com.igate.dam.publish.service;

import java.util.List;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.metadata.dto.Vendor;
import com.igate.dam.publish.dto.PublishProfile;


/**
 * @author mj802966
 *
 */
public interface PublishProfileService {
	public List<String> getVendorNames()throws DAMException;
	public	List<String> getProfileNames()throws DAMException;
	public void createProfile(PublishProfile profile)throws DAMException;
	public List<Vendor> getVendorid()throws DAMException;
	public List<PublishProfile> listProfiles()throws DAMException;
	public void deleteProfile(int profileid)throws DAMException;
	public PublishProfile updateProfile(int profileid) throws DAMException;
	public void profileSave(PublishProfile profile)throws DAMException;

}
