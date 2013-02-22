package com.igate.dam.poller.filepolling.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


import com.igate.dam.poller.exception.DamJnotifyException;
import com.igate.dam.poller.filepolling.api.FilePollingImpl;
import com.igate.dam.poller.util.LoggerUtil;

public class JnotifyTesting {
	 public static void main(String[] args) {
		 
		LoggerUtil jNotifyLogger=new LoggerUtil();
		
		 FilePollingImpl FilePoller=new FilePollingImpl();
		 try
		 {
		 int timeInterval=FilePoller.loadPropertyFile();
		 Properties properties=new Properties();
		 properties.load(new FileInputStream(new File("resources\\log4j.properties")));
		 String watchPath=properties.getProperty("watchPath");
			
		 jNotifyLogger.loadDirectoryName(watchPath);
		 FilePoller.filePolling(timeInterval,watchPath );
		 }catch (FileNotFoundException fileNotFoundException) {
			 System.out.println("Unable to find the property File");
	    	
	     } catch (IOException e) {
	    	 System.out.println("unable to load the property file");
				
		 }
		 catch(DamJnotifyException damJnotifyException)
		 {
			System.out.println(damJnotifyException.getMessage());
		 }  
		 
}

}
