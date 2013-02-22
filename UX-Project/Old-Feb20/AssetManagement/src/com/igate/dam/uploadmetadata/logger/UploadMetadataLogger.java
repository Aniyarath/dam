package com.igate.dam.uploadmetadata.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class UploadMetadataLogger {
	

		public static final Logger logger = Logger.getLogger(UploadMetadataLogger.class);
		 
		public UploadMetadataLogger()
		{
			  PropertyConfigurator.configure("/log4j.properties");
		}
		
		public void error(String message){
			logger.error(message);
		}
		
		public void info(String message){
			logger.info(message);
		}
		
		
	}


