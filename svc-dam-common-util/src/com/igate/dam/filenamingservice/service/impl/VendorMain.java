package com.igate.dam.filenamingservice.service.impl;


import java.io.File;
import java.util.Iterator;
import java.util.ResourceBundle;
import org.apache.commons.io.FileUtils;

import com.igate.dam.filenamingservice.constants.FileNamingServiceConstants;
import com.igate.dam.filenamingservice.exception.FileNamingServiceException;
import com.igate.dam.filenamingservice.logger.FileNamingServiceLogger;
import com.igate.dam.filenamingservice.service.FileNamingServiceIntf;

public class VendorMain {

	
	
	 
	public static void main(String[] args){
		boolean result=false; 
		Iterator iterator=null;
		FileNamingServiceLogger fileNamingServiceLogger=new FileNamingServiceLogger();
		
		try{
			
			FileNamingServiceIntf fileNamingServiceIntf = new FileNamingServiceImpl();
			ResourceBundle resourceBundle=ResourceBundle.getBundle(FileNamingServiceConstants.VENDOR_PROPERTY_FILE_NAME);//Name of the prop file
			String vendors=resourceBundle.getString(FileNamingServiceConstants.VENDOR_NAMES);
			String baseFolder=resourceBundle.getString(FileNamingServiceConstants.BASE_FOLDER_LOCATION);
			String[] vendorNames=vendors.split(",");
			
			String vendorInfo = "";
			 String fileName="";
			 String vendorName[]=null;
			 int count1=0;
			 for(int count=0;count<vendorNames.length;count=count+2){
				 System.out.println(vendorNames[count]);
			 for( count1=count;count1<vendorNames.length;count1++){
				
				iterator = FileUtils.iterateFiles(new File(baseFolder+"/"+vendorNames[count1]), null,false);
	            while(iterator.hasNext()){

	        	   fileName=((File) iterator.next()).getName();
	        	  
	        	   
	            }
	            if(fileNamingServiceIntf.validateFileFormat(vendorNames[count1], fileName)){
	        		   vendorInfo =  vendorNames[count1];
	        		   vendorName=vendorInfo.split("/");
	        		   result=true;
	        		   break;
	        		   
	        	   }
	            else
	            {
	            	result=false;
	            	break;
	            }
			}
			 if(result)
	         {
			 String metadatafolder=vendorName[0]+"/"+FileNamingServiceConstants.METADATA_FOLDER_NAME;
			 
			 iterator = FileUtils.iterateFiles(new File(baseFolder+"/"+metadatafolder), null,false);
             while(iterator.hasNext()){

        	    fileName=((File) iterator.next()).getName();
        	    
        	   
             }
            
            	
            	result=fileNamingServiceIntf.validateFileFormat(metadatafolder, fileName);
	        }
            else
            {
            	result=false;
            }
     	    System.out.println("File Validation Result:"+result);
			
     	 					
          }
		}
		catch (FileNamingServiceException exception) 
         {
        	  fileNamingServiceLogger.logFileNamingServiceException(exception.getMessage());
        	
         }

	
	}
	
	
}
