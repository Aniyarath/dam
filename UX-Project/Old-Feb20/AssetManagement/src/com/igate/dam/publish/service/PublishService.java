package com.igate.dam.publish.service;


import java.util.List;
import java.util.Map;


import com.igate.dam.common.exception.DAMException;
import com.igate.dam.metadata.dto.MediaPackage;
import com.igate.dam.publish.dto.DamPackage;

import com.igate.dam.publish.dto.MetadataFields;
import com.igate.dam.publish.dto.PublishProfile;



/**
 * @author mj802966
 *
 */
public interface PublishService {
	public List<MediaPackage> listPackages(int vendorId)throws DAMException;

	public String getVendorName(int mediaid)throws DAMException;

	public String getMediaFileName(int mediaid)throws DAMException;

	public List<String> moveAllFile(String vendorName, String mediaPackage,
			String profileName) throws DAMException;

	public List<MetadataFields> getAttributes(int mediaid, int vendorid)throws DAMException;

	public int getVendorid(int mediaid)throws DAMException;

	public String runSmooksTransform(DamPackage inputJavaObject,
			String profileName) throws DAMException;

	public void updateStatus(MediaPackage mediaPackage)throws DAMException;

	public List<String> getVendorNames()throws DAMException;

	public int getVendorid(String name)throws DAMException;

	public List<PublishProfile> listProfiles(int vendorid)throws DAMException;

	public Map<String, String> previewVideo(List<MediaPackage> mediaList)
			throws DAMException;

	public List<String> fetchPosterNVideo(String name) throws DAMException;
}
