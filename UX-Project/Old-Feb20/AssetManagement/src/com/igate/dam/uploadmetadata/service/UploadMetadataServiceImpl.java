package com.igate.dam.uploadmetadata.service;

import java.io.IOException;

import org.milyn.SmooksException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.metadata.exception.DamSmooksException;
import com.igate.dam.uploadmetadata.dao.UploadMetadataDAO;
import com.igate.dam.uploadmetadata.dto.ListMetadata;


@Service("uploadMetadataServiceImpl")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class UploadMetadataServiceImpl implements UploadMetadataService{

@Autowired
UploadMetadataDAO uploadMetadataDAO;

public UploadMetadataDAO getUploadMetadataDAO() {
	return uploadMetadataDAO;
}

public void setUploadMetadataDAO(UploadMetadataDAO uploadMetadataDAO) {
	this.uploadMetadataDAO = uploadMetadataDAO;
}

	@Override
	public int metadataIngest(String mediaName) throws DAMException{
		int mediaid = uploadMetadataDAO.metadataIngest(mediaName);
		return mediaid;
	}

	@Override
	public ListMetadata xmlToJavaTransformation(String inputFileName,
			String configFileName, String xmlContent, String vendorName)
			throws DAMException {
		ListMetadata listMetadata;
	
			listMetadata = uploadMetadataDAO.xmlToJavaTransformation(
					inputFileName, configFileName, xmlContent, vendorName);
		
		return listMetadata;
	}

	@Override
	public String packageEntry(String inputFilePath, String vendorName)
			throws DAMException {
		String ext;
	
			ext = uploadMetadataDAO.packageEntry(inputFilePath, vendorName);
		
		return ext;
	}

	@Override
	public boolean saveXmlcontents(ListMetadata metadataitems,
			String inputFileName, int vendorId) throws DAMException {
		boolean success;
	
			success = uploadMetadataDAO.saveXmlcontents(metadataitems,
					inputFileName, vendorId);
		
		return false;
	}

}
