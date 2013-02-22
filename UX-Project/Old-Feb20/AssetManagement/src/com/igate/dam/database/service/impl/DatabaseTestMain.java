/**
 * 
 */
package com.igate.dam.database.service.impl;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import java.util.GregorianCalendar;

//import java.sql.Connection;



/**
 * @author 706440
 *
 */
public class DatabaseTestMain {
	public static void main(String[] args){
		/*DatabaseServiceImpl objdataDatabaseServiceImpl = new DatabaseServiceImpl();
		Connection con = (Connection)objdataDatabaseServiceImpl.getConnection();
		System.out.println(con.toString());*/
		
		/*String fileName = null;
		String path = "D:\\DAM-PROJ\\WIP\\Metadata\\metadata.xls";
		File inputFile = new File(path);
		fileName = inputFile.getName();
		System.out.println("fileName --->"+ fileName);
		fileName = fileName.substring(0,fileName.lastIndexOf("."));
		System.out.println("fileName --->"+ fileName);*/
		
		
		/*int filesLength = 0;
		String sourceFile = null;
		String destinationFile = null;
		File source = null;
		File destination = null;
		File[] files = null;
		File file = null;
		boolean success = true;
		File inputFile = null;
		String fileName = null;
		String packageName = null;
		String parent = null;
		String tempFileName= null;
		String tempName = null;
		
		
		String inputFilePath = "D:\\DAM-PROJ\\Test\\AXN\\media\\JurassicPark.txt";
		
		String outputFilePath = "D:\\DAM-PROJ\\Test\\WIP\\media\\";
		
		if(inputFilePath!=null){
			 inputFile = new File(inputFilePath);
			 fileName = inputFile.getName();
			 packageName = fileName.substring(0,fileName.lastIndexOf("."));
			 System.out.println("packageName--->"+packageName);
			 parent = inputFile.getParent();
			 System.out.println("parent--->"+parent);
		}
		
		
		source = new File(parent);
		if(!source.exists()){
			
			success = false;
		}
		destination = new File(outputFilePath);
		if(!destination.exists()){
			
			success = false;
		}
		if(success){
		files = source.listFiles();
		if(files!=null){
			filesLength = files.length;
		}
		for (int i=0; i < filesLength; i++){
			System.out.println("files[i].getName()--->"+files[i].getName());
			tempFileName  = files[i].getName();
			tempName = tempFileName.substring(0,tempFileName.lastIndexOf("."));
			if(tempName.equalsIgnoreCase(packageName)){
				System.out.println("inside if condition");
				sourceFile =(source+"\\"+files[i].getName());
				destinationFile =(destination + "\\"+ files[i].getName());
				file = new File(sourceFile);
				file.renameTo(new File(destinationFile));
			}
		}
		}*/
		
		/*String inputFilePath = "D:\\DAM-PROJ\\WatchFolder\\NDTV\\media\\JurassicPark.wav";
		String vendorName = new File (new File(inputFilePath).getParent()).getParent();
		System.out.println(vendorName);
		vendorName = vendorName.substring(vendorName.lastIndexOf("\\")+1, vendorName.length());
		System.out.println(vendorName);*/
		
		/*Calendar currentDate = Calendar.getInstance(); //Get the current date
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss"); //format it as per your requirement
		Date dateNow = (Date)formatter.parse(currentDate);
		System.out.println("Now the date is :=>  " + dateNow);*/
		
		
		/*Calendar currentDate = Calendar.getInstance();//Get the current date
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss"); //format it as per your requirement
		String dateNow = formatter.format(currentDate.getTime());
		System.out.println("dateNow-->"+dateNow);
		Date xx = Date.valueOf(dateNow);
		System.out.println(xx);*/
		
		/*java.util.Date date = new java.util.Date();
		java.sql.Date dd = new java.sql.Date(date.getTime());
		System.out.println(dd);*/
		 
		String inputFilePath = "D:\\DAM-PROJ\\WatchFolder\\NDTV\\media\\JurassicPark.wav";
		String packageName = new File(inputFilePath).getName();
		packageName = packageName.substring(0,packageName.lastIndexOf("."));
		System.out.println("packageName-->"+packageName);
		


	}

}
