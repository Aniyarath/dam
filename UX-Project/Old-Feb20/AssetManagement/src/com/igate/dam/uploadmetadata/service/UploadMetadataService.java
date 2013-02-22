package com.igate.dam.uploadmetadata.service;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.metadata.exception.DamSmooksException;
import com.igate.dam.uploadmetadata.dto.ListMetadata;

public interface UploadMetadataService {

	public int metadataIngest(String mediaName)throws DAMException;
	public ListMetadata xmlToJavaTransformation(String inputFileName,String configFileName,String xmlContent,String vendorName) throws DAMException;
	public String packageEntry(String inputFilePath,String vendorName) throws DAMException;
	public boolean saveXmlcontents(ListMetadata metadataitems,String inputFileName,int vendorId) throws DAMException;

}
