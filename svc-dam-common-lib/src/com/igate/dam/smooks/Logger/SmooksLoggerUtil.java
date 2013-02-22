package com.igate.dam.smooks.Logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class SmooksLoggerUtil {

	public static final Logger logger = Logger.getLogger(SmooksLoggerUtil.class);
	 
	public SmooksLoggerUtil()
	{
		  PropertyConfigurator.configure("D:\\DAMTOOLS\\svc-dam-common-lib\\resources\\log4j.properties");
	}
	public void display(Object message)
	{
		logger.info("Message:"+message);
	}
	public void logFileName(String name)
	{
		logger.info("Input File for Transformation:"+name);
	}
	public void fileNotFound()
	{
		logger.info("Unable to find the Input File");
	}
	public void Ioexception()
	{
		logger.info("Unable to find the Smooks confiugration File");
    }
	
	public void smooksException()
	{
		logger.info(" Exception occured in smooks while filtering the source");
    }
	public void saxexception()
	{
		logger.info("exception occured while parsing the xml file");
    }
	public void damsmooksexception()
	{
		logger.info("Exception occured while transformation");
	}
	public void loadConfigFile()
	{
		logger.info("smooks configuration file is loaded");
	}
}
