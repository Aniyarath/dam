/**
 * 
 */
package com.igate.dam.filenamingservice.service;

import org.drools.runtime.StatefulKnowledgeSession;

import com.igate.dam.filenamingservice.exception.FileNamingServiceException;

/**
 * @author pp802965
 *
 */
public interface FileNamingServiceIntf {
	
	/**
	 * @param vendorName
	 * @param fileName
	 * @return
	 * @throws FileNamingServiceException
	 */
	public boolean validateFileFormat(String vendorName,String fileName,StatefulKnowledgeSession statefulSession) throws FileNamingServiceException;
	public boolean validateFile(String vendorName,StatefulKnowledgeSession statefulSession) throws FileNamingServiceException;

}
