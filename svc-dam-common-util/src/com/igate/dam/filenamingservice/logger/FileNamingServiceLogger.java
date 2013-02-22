package com.igate.dam.filenamingservice.logger;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;

import com.igate.dam.filenamingservice.constants.FileNamingServiceConstants;
import com.igate.dam.filenamingservice.service.impl.VendorMain;



public class FileNamingServiceLogger
{

private static final Logger logger = Logger.getLogger(VendorMain.class.getName());
	
	public FileNamingServiceLogger(){
		ResourceBundle resourceBundle=ResourceBundle.getBundle(FileNamingServiceConstants.VENDOR_PROPERTY_FILE_NAME);
		String loggerFileName=resourceBundle.getString("LOG4J_PROPERTY");//read from prop file
      	URL url=Loader.getResource(loggerFileName);//LOG4J_PROPERTY
		PropertyConfigurator.configure(url);
	}
	
	public void logSupportedFileFormats(String vendorName,List<String> supportedFormat){
		logger.info("supported file formats for "+vendorName+":"+supportedFormat);
	}

	public void logValidationResult(boolean result){
		logger.info("file validation result:"+result);
	}
	
	public void logFileNamingServiceException(String message){
	 logger.error("File Naming Service Exception:"+message);	
	}
}
