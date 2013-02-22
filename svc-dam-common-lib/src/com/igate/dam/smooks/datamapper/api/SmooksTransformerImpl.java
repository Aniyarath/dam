package com.igate.dam.smooks.datamapper.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Scanner;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.milyn.Smooks;
import org.milyn.SmooksException;
import org.milyn.container.ExecutionContext;
import org.milyn.io.StreamUtils;
import org.milyn.payload.JavaResult;
import org.milyn.payload.JavaSource;
import org.milyn.payload.StringSource;
import org.xml.sax.SAXException;

import com.igate.dam.smooks.Logger.SmooksLoggerUtil;
import com.igate.dam.smooks.exception.DamSmooksException;

public class SmooksTransformerImpl implements SmooksTransformerIntf{
	
	SmooksLoggerUtil smooksloggerutil=new SmooksLoggerUtil();

/********** XML to JAVA Transformation ********/
	@Override
	public JavaResult xmlToJavaTransformation(String inputFileName,String configFileName) throws DamSmooksException {
		
		    JavaResult javaResult=new JavaResult();
			StringReader reader = null;
			Smooks smooks = null;
			String xmlContent = null;
			ExecutionContext executionContext = null;
			try {
				
				 smooks = new Smooks(configFileName);
				 executionContext = smooks.createExecutionContext();
				 xmlContent = getFileContent(inputFileName);
				 
				 reader = new StringReader(xmlContent);
				 smooks.filterSource(executionContext, new StreamSource(reader),javaResult);
			} 
			catch(IOException ie)
			{
				smooksloggerutil.Ioexception();
				throw new DamSmooksException("Unable to find smooks-config-XmlToJava file");
			}
			catch(SAXException se)
			{
				smooksloggerutil.saxexception();
				throw new DamSmooksException("exception occured while parsing the xml file");
			}
			catch(SmooksException exception)
			{
				smooksloggerutil.smooksException();
				throw new DamSmooksException("Exception occured with smooks while filtering source");
			}
			catch(Exception exception)
			{exception.printStackTrace();
				smooksloggerutil.smooksException();
				throw new DamSmooksException("Exception occured with smooks while filtering source");
			}
			return javaResult;
	}
	
/******* JAVA to XML Transformation ***************/
	@Override
	public void javaToXMLTransformation(Object inputJavaObject,	String configFileName, String outputFilePath)throws DamSmooksException {
		Smooks smooks = null;
		StringWriter writer = null;
		ExecutionContext executionContext = null;
		FileWriter fileWriter = null;
		
		try {

			smooks = new Smooks(configFileName);
			writer = new StringWriter();
			executionContext = smooks.createExecutionContext();
			smooks.filterSource(executionContext, new JavaSource(inputJavaObject),
					new StreamResult(writer));
			fileWriter = new FileWriter(outputFilePath);
			fileWriter.write(writer.toString());
			fileWriter.flush();
			fileWriter.close();
			

		}
		catch(FileNotFoundException fe){
			smooksloggerutil.fileNotFound();
			throw new DamSmooksException("smooks-config-JavaToXml doesnot exist");
		}
		catch (IOException ie) {
			smooksloggerutil.Ioexception();
			throw new DamSmooksException("Unable to find smooks-config-JavaToXml file");
		} 
		catch(SmooksException exception){
			smooksloggerutil.smooksException();
			throw new DamSmooksException("Exception occured with smooks while filtering source");
		}
		catch(SAXException saxException)
		{
			smooksloggerutil.saxexception();
			throw new DamSmooksException("Exception occured during parsing the xml");
		}
		
	}

/******* CSV to JAVA Transformation ************/
	@Override
	public JavaResult csvToJavaTransformation(String inputFileName,String configFileName) throws DamSmooksException {
		Smooks smooks = null;
		JavaResult javaresult = null;
		ExecutionContext executionContext = null;
		String messageIn = null;
		
		try {
			File inputFile=new File(inputFileName);
			messageIn = readInputMessage(inputFile);
			smooks = new Smooks(configFileName);
			executionContext = smooks.createExecutionContext();
			javaresult = new JavaResult();
			smooks.filterSource(executionContext, new StringSource(messageIn),
					javaresult);
		} 
		catch(FileNotFoundException fe){
			smooksloggerutil.fileNotFound();
			throw new DamSmooksException("smooks-config-CsvToJava doesnot exist");
		}
		catch(IOException ie){
			smooksloggerutil.Ioexception();
			throw new DamSmooksException("Unable to find smooks-config-CsvToJava file");
			
		}
		catch(SAXException se){
			smooksloggerutil.saxexception();
			throw new DamSmooksException("exception occured while parsing the xml file");
			
		}
		catch(SmooksException exception){
			smooksloggerutil.smooksException();
			throw new DamSmooksException("Exception occured with smooks while filtering source");
		}
		
		return javaresult;
	}
	
	/**
	 * @param fileName
	 * @return
	 * @throws DamSmooksException
	 */
	private  String getFileContent(String fileName) throws DamSmooksException {
		Scanner scanner = null;
		StringBuilder fileContents = null;
		File file = null;
		String lineSeparator = null;
		try {
			file = new File(fileName);
			fileContents = new StringBuilder((int) file.length());
			scanner = new Scanner(file);
			lineSeparator = System.getProperty("line.separator");
			while (scanner.hasNextLine()) {
				fileContents.append(scanner.nextLine() + lineSeparator);
			}
		} catch (FileNotFoundException e) {
			smooksloggerutil.fileNotFound();
			throw new DamSmooksException("Unable to find input file");
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
		return fileContents.toString();
	}

	/**
	 * @param file1
	 * @return
	 * @throws DamSmooksException
	 */
	private String readInputMessage(File fileObj) throws DamSmooksException {
		try {
			return StreamUtils.readStreamAsString(new FileInputStream(fileObj.getAbsolutePath()));
		} catch (IOException e) {
			smooksloggerutil.Ioexception();
			throw new DamSmooksException("No message found"+e);
		
		}
	}
	
	
	
	  
		
		 
	 }

	