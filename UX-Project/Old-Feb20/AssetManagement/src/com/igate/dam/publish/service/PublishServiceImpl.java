package com.igate.dam.publish.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.metadata.dto.MediaPackage;
import com.igate.dam.publish.dao.PublishDAO;
import com.igate.dam.publish.dto.DamPackage;
import com.igate.dam.publish.dto.MetadataFields;
import com.igate.dam.publish.dto.PublishProfile;




/**
 * @author mj802966
 *
 */
@Service("publishServiceImpl")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)

public class PublishServiceImpl implements PublishService{
	
	@Autowired
	private PublishDAO publishDAO;
	

	public PublishDAO getPublishDAO() {
		return publishDAO;
	}

	public void setPublishDAO(PublishDAO publishDAO) {
		this.publishDAO = publishDAO;
	}

	public List<MediaPackage> listPackages(int vendorId) throws DAMException{

		System.out.println("inside service......");
		List<MediaPackage> mediaList = new ArrayList<MediaPackage>();
		mediaList = publishDAO.listMediaFiles( vendorId);
		System.out.println("ser" + mediaList.size());
		return mediaList;
	}
	
	@Override
	public String getVendorName(int mediaid)throws DAMException {

		System.out.println("inside service");
		String id = publishDAO.getVendorName(mediaid);
		return id;
	}

	@Override
	public String getMediaFileName(int mediaid) throws DAMException{
		String fileName = publishDAO.getMediaFileName(mediaid);
		return fileName;
	}

	
	@Override
	public List<String> moveAllFile(String vendorName, String mediaPackage,
			String profileName) throws DAMException {

		List<String> fileList;

			fileList = publishDAO.moveAllFile(vendorName, mediaPackage,
					profileName);
		return fileList;
	}


	

	@Override
	public int getVendorid(int mediaid) throws DAMException{
		int vendorid=publishDAO.getVendorid(mediaid);
		return vendorid;
	}




	@Override
	public String runSmooksTransform(DamPackage inputJavaObject,
			String profileName) throws DAMException {

		String fileName = null;
	
			fileName = publishDAO.runSmooksTransform(inputJavaObject,
					profileName);
		
		return fileName;
	}
	
	
	@Override
	public void updateStatus(MediaPackage mediaPackage) throws DAMException{
		publishDAO.updateStatus(mediaPackage);
		
	}
	
	@Override
	public List<String> getVendorNames() throws DAMException{
		List<String> vendorName=publishDAO.getVendorNames();
		return vendorName;
	}


	@Override
	public int getVendorid(String name) throws DAMException{
		int vendorid=publishDAO.getVendorid(name);
		return vendorid;
	}


	@Override
	public List<PublishProfile> listProfiles(int vendorid)throws DAMException {
		List<PublishProfile> profiles=publishDAO.listProfiles(vendorid);
		System.out.println("service----------"+profiles.size());
		return profiles;
	}

	
	@Override
	public List<MetadataFields> getAttributes(int mediaid, int vendorid) throws DAMException{
		List<MetadataFields> list=publishDAO.getAttributes(mediaid, vendorid);
		return list;
	}
	
	
	public Map<String, String> previewVideo(List<MediaPackage> mediaList)
			throws DAMException {

		Map<String, String> fileList = null;
	
			fileList = publishDAO.previewVideo(mediaList);
		
		return fileList;
	}

	@Override
	public List<String> fetchPosterNVideo(String name) throws DAMException {
		List<String> fileList;
		
		 fileList=publishDAO.fetchPosterNVideo(name);
		
		return fileList;
	}
}
