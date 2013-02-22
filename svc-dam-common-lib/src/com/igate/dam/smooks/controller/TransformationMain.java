package com.igate.dam.smooks.controller;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.milyn.payload.JavaResult;

import com.igate.dam.smooks.Logger.SmooksLoggerUtil;
import com.igate.dam.smooks.bo.SmooksBO;
import com.igate.dam.smooks.dto.Person;
import com.igate.dam.smooks.exception.DamSmooksException;

public class TransformationMain {
	
	SmooksLoggerUtil smooksloggerutil=new SmooksLoggerUtil();
	

	
	public void fileMapperOperationInput(String inputFileName) throws DamSmooksException
	{
		
		String fileName = null;
		int mid = 0;
		String ext = null;
		String configFileName = null;
		JavaResult javaResult = null;
		Person person = new Person();
		String outputFilePath=null;
				
		try
		{
			smooksloggerutil.logFileName(inputFileName);
			mid= inputFileName.lastIndexOf(".");
			ext=inputFileName.substring(mid+1,inputFileName.length());  			
			SmooksBO smooksBO = new SmooksBO();
		    fileName=inputFileName;
		 	if(ext.equalsIgnoreCase("xml")){

				configFileName = "smooks-config-XmlToJava.xml";
				javaResult  = smooksBO.xmlToJavaTransformation(fileName, configFileName);
				person = (Person) javaResult.getBean("person");
				smooksloggerutil.display("XML to Java Transformation results in Java bean output --->"+person.getFirstName());
			}
			else if(ext.equalsIgnoreCase("java")){
				configFileName = "smooks-config-JavaToXml.xml";
				 ResourceBundle resourceBundle=ResourceBundle.getBundle("damUtil");
				 outputFilePath=resourceBundle.getString("outputDirectory");//Reading from prop file
			    smooksBO.javaToXMLTransformation(person, configFileName,outputFilePath);
				smooksloggerutil.display("output xml is stored in :"+outputFilePath);
			}
			else if(ext.equalsIgnoreCase("csv")){
				configFileName= "smooks-config-CsvToJava.xml";
				javaResult = smooksBO.csvToJavaTransformation(fileName, configFileName);
				Object list=(List)javaResult.getBean("person");
				smooksloggerutil.display("CSV to Java Transformation results in Java bean output --->"+list);
			}
			else{
				smooksloggerutil.display("Invaild input file");
			}
		}

       catch (DamSmooksException damSmooksException) {
			System.out.println(damSmooksException.getMessage());
			
		}
		
	}
		
		public void loadPropertyFile() throws FileNotFoundException, IOException
		{
			 ResourceBundle resourceBundle=ResourceBundle.getBundle("damUtil");
			 String directoryName=resourceBundle.getString("inputDirectory");//Reading from prop file

			Iterator it = FileUtils.iterateFiles(new File(directoryName), null,false);
	        while(it.hasNext()){
	                  String fileName=((File) it.next()).getName();
	                  String inputFileName=directoryName+fileName;
	                  System.out.println("inputFileName from smooks"+inputFileName);
	                  fileMapperOperationInput(inputFileName);
	                  
		}
			
			
	

		}
}
