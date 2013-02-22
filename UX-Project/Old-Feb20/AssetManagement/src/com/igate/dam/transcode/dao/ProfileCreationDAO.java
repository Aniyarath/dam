package com.igate.dam.transcode.dao;

import java.util.List;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.transcode.dto.ProfileCreation;
import com.igate.dam.transcode.dto.Vendors;

/**
 * @author mj802966
 *
 */
public interface ProfileCreationDAO {
	
	public List<String> getVendorNames()throws DAMException;
	public void createProfile(ProfileCreation profile)throws DAMException;
	public List<Vendors> getvendor()throws DAMException;
	public List<ProfileCreation> listProfiles()throws DAMException;
	public void deleteProfile(int profileid)throws DAMException;
	public ProfileCreation updateProfile(int profileid) throws DAMException;
	public void profileSave(ProfileCreation profile)throws DAMException;
	public	List<String> getProfileNames()throws DAMException;
}
