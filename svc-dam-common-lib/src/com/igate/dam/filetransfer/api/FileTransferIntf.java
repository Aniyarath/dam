/**
 * 
 */
package com.igate.dam.filetransfer.api;

import java.io.IOException;

import com.igate.dam.filetransfer.exception.FileTransferException;

/**
 * @author 706440
 *
 */
public interface FileTransferIntf {

	/**
	 * @param fileName
	 * @param sourcePath
	 * @param destinationPath
	 * @return
	 */
	public boolean moveFile(String fileName, String sourcePath, String destinationPath) throws FileTransferException, IOException;
	
	/**
	 * @param fileName
	 * @param sourcePath
	 * @param destinationPath
	 * @return
	 */
	public boolean copyFile(String fileName, String sourcePath,String destinationPath) throws FileTransferException, IOException;
	
	/**
	 * @param fileName
	 * @param destinationPath
	 * @return
	 */
	public boolean createFile(String fileName, String destinationPath) throws FileTransferException, IOException;
	
	/**
	 * @param sourcePath
	 * @param destinationPath
	 * @return
	 * @throws IOException 
	 */
	public boolean moveAllFiles(String inputFilePath,String outputFilePath) throws FileTransferException, IOException;
}
