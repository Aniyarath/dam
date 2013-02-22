package com.igate.dam.filetransfer.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class FileTransferLogger {

	public static final Logger logger = Logger.getLogger(FileTransferLogger.class);
	 
	public FileTransferLogger()
	{
		  PropertyConfigurator.configure("D:\\DAMTOOLS\\svc-dam-common-lib\\resources\\log4j.properties");
	}
	
	public void error(String message){
		logger.error(message);
	}
	
	public void info(String message){
		logger.info(message);
	}
	
	
}
