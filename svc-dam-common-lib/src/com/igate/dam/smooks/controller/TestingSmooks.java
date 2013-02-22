/*package com.igate.dam.smooks.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import org.apache.commons.io.FileUtils;

public class TestingSmooks
{
	*//**
	 * @param args
	 * @throws FileNotFoundException
	 * @throws IOException
	 *//*
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		Properties properties=new Properties();
		properties.load(new FileInputStream(new File("resources\\damUtil.properties")));
		String directoryName=properties.getProperty("inputDirectory");
		Iterator it = FileUtils.iterateFiles(new File(directoryName), null,false);
        while(it.hasNext()){
                  String fileName=((File) it.next()).getName();
                  String inputFileName=directoryName+fileName;
               
		TransformationMain transformationMain=new TransformationMain();
		transformationMain.fileMapperOperationInput(inputFileName);
	}

}
}*/