/**
 * 
 */
package com.igate.dam.filenamingservice.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.drools.KnowledgeBase;
import org.drools.agent.KnowledgeAgent;
import org.drools.agent.KnowledgeAgentConfiguration;
import org.drools.agent.KnowledgeAgentFactory;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import com.igate.dam.filenamingservice.constants.FileNamingServiceConstants;
import com.igate.dam.filenamingservice.dto.Vendor;
import com.igate.dam.filenamingservice.exception.FileNamingServiceException;
import com.igate.dam.filenamingservice.logger.FileNamingServiceLogger;
import com.igate.dam.filenamingservice.service.FileNamingServiceIntf;

/**
 * @author pp802965
 *
 */
public class FileNamingServiceImpl implements FileNamingServiceIntf {
	
	FileNamingServiceLogger fileNamingServiceLogger =  new FileNamingServiceLogger();
	
	//function to validate media file formats for a particular vendor
	/* (non-Javadoc)
	 * @see com.igate.dam.filenamingservice.main.FileNamingServiceIntf#validateFileFormat(java.lang.String, java.lang.String)
	 */
	public boolean validateFileFormat(String vendorName,String fileName) throws FileNamingServiceException
	{
		boolean result=false;
		int position=0;
		List<String> supportedFileList;
		String vendorFolder[]=vendorName.split("/");
		try 
		{
		supportedFileList = getSupportedFileFormats(vendorFolder[0],vendorFolder[1]);
		position=fileName.lastIndexOf(".");
		String extension=fileName.substring(position+1);
		fileNamingServiceLogger.logSupportedFileFormats(vendorName,supportedFileList);
		for(String format:supportedFileList)
		{
			if(format.equalsIgnoreCase(extension))
			{
				result=true;
				break;
			}
		}

		fileNamingServiceLogger.logValidationResult(result);
		}
		
		catch (Exception exception)
		 {
				throw new FileNamingServiceException("Unable to connect to drools guvnor");
		 }
		return result;
	}
	
	//function to retrieve supported media file formats for a particular vendor
	/**
	 * @param vendorName
	 * @return List
	 * @throws FileNamingServiceException
	 */
	private List<String> getSupportedFileFormats(String  vendorName,String folderType) throws FileNamingServiceException	{
		
		 List<String> supportedFileFormats=null;
		 KnowledgeBase knowledgeBase;
		 StatefulKnowledgeSession session = null;
		 Vendor vendor=null;
		 try {
			 knowledgeBase = createKnowledgeBase();
			 session = knowledgeBase.newStatefulKnowledgeSession();
			 vendor=new Vendor();
			 vendor.setVendorName(vendorName);
			 session.insert(vendor);
			 session.fireAllRules();
			 if(folderType.equalsIgnoreCase((FileNamingServiceConstants.MEDIA_FOLDER_NAME))){
				 supportedFileFormats=vendor.getMediaFormatList(); 
			 }
			 if(folderType.equalsIgnoreCase((FileNamingServiceConstants.METADATA_FOLDER_NAME))){
				 supportedFileFormats=vendor.getMetadataFormatList();
			 } 
			
		 }
		 catch(Exception exception){
			 throw new FileNamingServiceException("unable to create knowledge base");
		 }
	
		 
		 return supportedFileFormats;
	}
	
	/**
	 * @return
	 */
	private static KnowledgeBase createKnowledgeBase()throws FileNamingServiceException	{
		KnowledgeAgent kagent=null;
		try
		{
			 ResourceBundle resourceBundle=ResourceBundle.getBundle(FileNamingServiceConstants.VENDOR_PROPERTY_FILE_NAME);
			 String changesetFile=resourceBundle.getString("CHANGE_SET_XML");//Reading from prop file
			 KnowledgeAgentConfiguration kaconf = KnowledgeAgentFactory.newKnowledgeAgentConfiguration();
			 kaconf.setProperty(FileNamingServiceConstants.DROOLS_AGENT_SCANDIRECTORY, FileNamingServiceConstants.FALSE_VAL);
			 kagent = KnowledgeAgentFactory.newKnowledgeAgent(FileNamingServiceConstants.KNOWLEDGE_AGENT, kaconf );
			 kagent.applyChangeSet(ResourceFactory.newClassPathResource(changesetFile)); 
		}
		
		catch (Exception exception) {
			 throw new FileNamingServiceException("unable to parse changeset");
		}
		 return kagent.getKnowledgeBase();
	}


}
