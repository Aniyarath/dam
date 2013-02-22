/**
 * 
 */
package com.igate.dam.filetransfer.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.igate.dam.filetransfer.exception.FileTransferException;
import com.igate.dam.filetransfer.logger.FileTransferLogger;

/**
 * @author 706440
 *
 */
public class FileTransferImpl implements FileTransferIntf{
	
	FileTransferLogger logger=new FileTransferLogger();
	
	/* (non-Javadoc)
	 * @see com.igate.dam.filetransfer.api.FileTransferIntf#moveFile(java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean moveFile(String fileName, String sourcePath, String destinationPath) throws FileTransferException, IOException{
		File sourceFile = null;
		File destinationFolder = null;
		File destinationFile = null;
		try{
		   sourceFile = new File(sourcePath+fileName);
		   if(!sourceFile.exists()){
			  logger.error("Source file doesn't exist");
			  return false;
		   }
		   
		   destinationFolder = new File(destinationPath);
		   if(!destinationFolder.exists()){
			   logger.error("Destination folder doesn't exists");
			   return false;
		   }
		   
		   destinationFile = new File(destinationPath + fileName);
		   if(sourceFile.renameTo(destinationFile)){
			   return true;
	       }else{
	    	   logger.error("Error in moving file");
	    	  return false;
	       }
	    }catch(Exception ex){
	    	logger.error("Error in moving file "+ex.getMessage());
	    	throw new FileTransferException("Error in moving files "+ex.getMessage());
	    }
	}
	
	/* (non-Javadoc)
	 * @see com.igate.dam.filetransfer.api.FileTransferIntf#copyFile(java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean copyFile(String fileName, String sourcePath,String destinationPath) throws FileTransferException, IOException{
		 int len;
		 InputStream inputStream = null;
		 OutputStream outputStream = null;
		 File sourceFile = null;
		 File destinationFile = null;
		 File destinationFolder = null;
		 try{
			 
			  sourceFile = new File(sourcePath+fileName);
			  if(!sourceFile.exists()){
				  logger.error("Source file doesn't exist");
				  return false;
			  }
			  destinationFolder = new File(destinationPath);
			  if(!destinationFolder.exists()){
				  logger.error("Destination folder doesn't exist");
				  return false;
			  }
			  
			  destinationFile = new File(destinationPath+fileName);
			  inputStream = new FileInputStream(sourceFile);
			  outputStream = new FileOutputStream(destinationFile);
			  byte[] buf = new byte[1024];
			  while ((len = inputStream.read(buf)) > 0){
				  outputStream.write(buf, 0, len);
			  }
			  inputStream.close();
			  outputStream.close();
			  return true;
		  }
		  catch(Exception ex){
			  logger.error("Error in copying files "+ex.getMessage());
			  throw new FileTransferException("Error in copying files "+ex.getMessage());
		  }
	}
	
	/* (non-Javadoc)
	 * @see com.igate.dam.filetransfer.api.FileTransferIntf#createFile(java.lang.String, java.lang.String)
	 */
	public boolean createFile(String fileName, String destinationPath) throws FileTransferException, IOException{
		File destinationFolder = null;
		File file = null;
		try{
			destinationFolder = new File(destinationPath);
			if(!destinationFolder.exists()){
				logger.error("Destination folder doesn't exist");
				return false;
			}
			file = new File(destinationPath+fileName);
			if(!file.exists()){
				file.createNewFile();
				return true;
			}else{
				logger.error("File Already Exists");
				return false;
			}
		}catch(Exception ex){
			logger.error("Error in File Creation "+ex.getMessage());
			throw new FileTransferException("Error in File Creation "+ex.getMessage());
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.igate.dam.filetransfer.api.FileTransferIntf#moveAllFiles(java.lang.String, java.lang.String)
	 */
	public boolean moveAllFiles(String inputFilePath,String outputFilePath) throws FileTransferException, IOException{
	
		int filesLength = 0;
		String sourceFile = null;
		String destinationFile = null;
		File source = null;
		File destination = null;
		File[] files = null;
		File file = null;
		boolean success = true;
		try{
			
			/*ResourceBundle resourceBundle=ResourceBundle.getBundle("damUtil");
			 String destinationPath=resourceBundle.getString("destinationPath");//Reading from prop file
			 String errorFolderPath=resourceBundle.getString("errorFolderPath");*/
			
		
			
			/*if(inputFilePath.lastIndexOf(".")!=-1){
				parent = new File(inputFilePath).getParent();
				sourcePath = parent.substring(0,parent.lastIndexOf("\\"));
				success = true;
			}
			else{
				destinationPath = errorFolderPath;
				sourcePath = inputFilePath;
			}*/
			
			source = new File(inputFilePath);
			if(!source.exists()){
				logger.error("Source folder doesn't exist");
				success = false;
			}
			destination = new File(outputFilePath);
			if(!destination.exists()){
				logger.error("Destination folder doesn't exist");
				success = false;
			}
			if(success){
			files = source.listFiles();
			if(files!=null){
				filesLength = files.length;
			}
			for (int i=0; i < filesLength; i++){
				sourceFile =(source+"\\"+files[i].getName());
				destinationFile =(destination + "\\"+ files[i].getName());
				file = new File(sourceFile);
				file.renameTo(new File(destinationFile));
			}
			}
			return success;
		}catch(Exception ex){
			logger.error("Error in moving all the files"+ex.getMessage());
			throw new FileTransferException("Error in moving all the files"+ex.getMessage());
		}
	}


}
