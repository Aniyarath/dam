/**
 * 
 */
package com.igate.dam.database.service;

import java.sql.Connection;

import com.igate.dam.common.exception.DAMException;

/**
 * @author 706440
 *
 */
public interface DatabaseServiceIntf {
	
	public Connection getConnection() throws DAMException;
	
	public int packageEntry(String inputFilePath,int vendorId) throws DAMException;
	
	public boolean mediaFileEntry(String inputFilePath,String fileType)throws DAMException;
	
	public boolean metadataFileEntry(String inputFilePath,String fileType)throws DAMException;
	
	public boolean updateMetadataIngestStatus(String inputFilePath)throws DAMException;
	
	public boolean updateMediaIngestStatus(String inputFilePath)throws DAMException;
	
	public String getFileName(String packageName,String fileType,String inputFilePath)throws DAMException;
	
	public int MediapackageEntry(String packageName,int vendorId)throws DAMException;
	
	

}
