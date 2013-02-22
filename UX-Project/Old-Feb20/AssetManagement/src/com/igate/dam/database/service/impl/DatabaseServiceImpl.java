/**
 * 
 */
package com.igate.dam.database.service.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.constants.util.ApplicationConstants;
import com.igate.dam.database.service.DatabaseServiceIntf;


/**
 * @author 706440
 *
 */
public class DatabaseServiceImpl implements DatabaseServiceIntf{
	
	public static final Logger logger = Logger.getLogger(DatabaseServiceImpl.class);
	
	public Connection getConnection() throws DAMException{
		logger.info("Entering getConnection() in DatabaseServiceImpl");
		Connection con = null;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(
				ApplicationConstants.DAM_DATABASE_URL,
				ApplicationConstants.DAM_DATABASE_USERNAME,
				ApplicationConstants.DAM_DATABASE_PASSWORD);
		logger.info("Connection is created : "+con.toString());
		}catch (Exception ex) {
			logger.error("Error in getConnection() in DatabaseServiceImpl"+ex.getMessage());
			throw new DAMException("unable to connect");
		
		}
		logger.info("Exiting getConnection() in DatabaseServiceImpl");
		return con;
	}
	
	public int packageEntry(String inputFilePath,int vendorId)throws DAMException{
		logger.info("Entering packageEntry() in DatabaseServiceImpl");
		System.out.println("--------->"+inputFilePath);
		String fileName = null;
		String fileExtension = null;
		File inputFile = null;
		Connection connection = null;
		int count = 0;
		String insertQuery = null;
		PreparedStatement pstmt = null;
		int insertCount = 0;
		ResultSet rs = null;
		String vendorQuery = null;
	
		
		try{
			
			java.util.Date date = new java.util.Date();
			
			if(inputFilePath!=null){
			// inputFile = new File(inputFilePath);
			 fileName = inputFilePath;
			 fileName = fileName.substring(0,fileName.lastIndexOf("."));
			 System.out.println("----file name------->"+fileName);
			 fileExtension=inputFilePath.substring(inputFilePath.lastIndexOf(".")+1,inputFilePath.length());
			 connection = getConnection();
			 String Query = "select count(*) count from media_package where media_package_name = ?";
			 pstmt = connection.prepareStatement(Query);
			 pstmt.setString(1, fileName);
			 rs = pstmt.executeQuery();
			 if(rs.next()){
				count = rs.getInt("count"); 
			 }
			 if(count==0){
				/* System.out.println("vendorName--->"+vendorName);
				 vendorQuery = "select vendor_id from vendor where vendor_name = ?";
				 pstmt = connection.prepareStatement(vendorQuery);
				 pstmt.setString(1, vendorName);
				 rs = pstmt.executeQuery();
				 if(rs.next()){
					vendorId = rs.getInt("vendor_id"); 
				 }
				 System.out.println("vendorId--->"+vendorId);*/
				 insertQuery = " insert into media_package(media_package_name, status_id, vendor_id, created_date, updated_date) " +
				 			   " values(?,?,?,?,?)";
				 pstmt = connection.prepareStatement(insertQuery);
				 pstmt.setString(1, fileName);
				 pstmt.setString(2, ApplicationConstants.PACKAGE_STATUSID_START);
				 pstmt.setInt(3, vendorId);
				 pstmt.setDate(4,new java.sql.Date(date.getTime()));
				 pstmt.setDate(5,new java.sql.Date(date.getTime()));
				 insertCount = pstmt.executeUpdate();
				 System.out.println("insert count----------->"+insertCount);
			 }else{
				 logger.info("Package already exists in the table");
			 }
			}
		}catch(Exception ex){
			logger.error("Error in packageEntry() in DatabaseServiceImpl"+ex.getMessage());
			throw new DAMException("Error in packageEntry");
		}finally{
			try{
				rs.close();
				pstmt.close();
				connection.close();
			}catch(SQLException ex){
				logger.error("Error in packageEntry() in DatabaseServiceImpl"+ex.getMessage());
				throw new DAMException("Error in packageEntry");
			}
		}
		logger.info("Exiting packageEntry() in DatabaseServiceImpl");
		System.out.println("return-------->"+insertCount);
		return insertCount;
	}
	

	public boolean mediaFileEntry(String inputFilePath,String fileType)throws DAMException{
		logger.info("Entering mediaFileEntry() in DatabaseServiceImpl");
		boolean mediaIngest = false;
		String packageNameQuery = null;
		String fileName = null;
		String packageName = null;
		File inputFile = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int mediaPackageId=0;
		String insertQuery = null;
		String checkQuery = null;
		boolean fileExists = false;
		String ingestQuery = null;
		int count = 0;
		int mediaCount = 0;
		
		try{
			
			if(inputFilePath!=null){
				// inputFile = new File(inputFilePath);
				 fileName =inputFilePath;
				 packageName = fileName.substring(0,fileName.lastIndexOf("."));
		
				 packageNameQuery = "select media_package_id from media_package where media_package_name = ?";
				 connection = getConnection();
				 pstmt = connection.prepareStatement(packageNameQuery);
				 pstmt.setString(1, packageName);
				 rs = pstmt.executeQuery();
				 if(rs.next()){
					 mediaPackageId = rs.getInt("media_package_id");
				 }
				 logger.info("mediaPackageId--->"+mediaPackageId);
				 
				 checkQuery = " select count(*) count from media_package_files " +
				 			  " where media_package_id = ? "+
				 			  " and file_type =? and isarrived = ? "; 
				 pstmt = connection.prepareStatement(checkQuery);
				 pstmt.setInt(1, mediaPackageId);
				 pstmt.setString(2, fileType);
				 pstmt.setString(3, "Y");
				 rs = pstmt.executeQuery();
				 if(rs.next()){
					mediaCount =  rs.getInt("count");
					if(mediaCount>0){
					 fileExists = true;
					}
				 }
				 logger.info("fileExists--->"+fileExists);
				 if(!fileExists){
					 insertQuery = " insert into media_package_files(media_package_id, media_package_file_name, media_package_file_path," +
					 			   " file_type, isarrived) values(?,?,?,?,?)";
					 pstmt = connection.prepareStatement(insertQuery);
					 pstmt.setInt(1, mediaPackageId);
					 pstmt.setString(2, fileName);
					 pstmt.setString(3, inputFilePath);
					 pstmt.setString(4, fileType);
					 pstmt.setString(5, "Y");
					 pstmt.executeUpdate();
					 logger.info("file inserted");
				 }
				 
				 ingestQuery = 	" select count(*) count from media_package_files " +
	 			  				" where media_package_id = ? "+
	 			  				" and file_type <> ? and isarrived = ? "; 
				 pstmt = connection.prepareStatement(ingestQuery);
				 pstmt.setInt(1, mediaPackageId);
				 pstmt.setString(2, ApplicationConstants.FILE_TYPE_METADATA);
				 pstmt.setString(3, "Y");
				 rs = pstmt.executeQuery();
				 if(rs.next()){
					 count = rs.getInt("count");
					 if(count==2){
						 mediaIngest = true;
					 }
				 }
			}
		}catch(Exception ex){
			logger.error("Error in mediaFileEntry() in DatabaseServiceImpl"+ex.getMessage());
			throw new DAMException("Error in mediaFileEntry");
		}
		finally{
			try{
				rs.close();
				pstmt.close();
				connection.close();
			}catch(SQLException ex){
				logger.error("Error in mediaFileEntry() in DatabaseServiceImpl"+ex.getMessage());
				throw new DAMException("Error in mediaFileEntry");
			}
		}
		logger.info("Exiting mediaFileEntry() in DatabaseServiceImpl-mediaIngest--->"+mediaIngest);
		return mediaIngest;
	}
	
	public boolean metadataFileEntry(String inputFilePath,String fileType) throws DAMException{
		logger.info("Entering metadataFileEntry() in DatabaseServiceImpl");
		boolean metadataIngest = false;
		String packageNameQuery = null;
		String fileName = null;
		String packageName = null;
		File inputFile = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int mediaPackageId=0;
		String insertQuery = null;
		String checkQuery = null;
		boolean fileExists = false;
		int count = 0;
		
		try{
			if(inputFilePath!=null){
				// inputFile = new File(inputFilePath);
				 fileName =inputFilePath;
				 packageName = fileName.substring(0,fileName.lastIndexOf("."));
				 System.out.println("packageName--->"+packageName);
				 packageNameQuery = "select media_package_id from media_package where media_package_name = ?";
				 connection = getConnection();
				 pstmt = connection.prepareStatement(packageNameQuery);
				 pstmt.setString(1, packageName);
				 rs = pstmt.executeQuery();
				 if(rs.next()){
					 mediaPackageId = rs.getInt("media_package_id");
				 }
				 System.out.println("mediaPackageId--->"+mediaPackageId);
				 
				 checkQuery = " select count(*) count from media_package_files " +
				 			  " where media_package_id = ? "+
				 			  " and file_type =? and isarrived = ? "; 
				 pstmt = connection.prepareStatement(checkQuery);
				 pstmt.setInt(1, mediaPackageId);
				 pstmt.setString(2, fileType);
				 pstmt.setString(3, "Y");
				 rs = pstmt.executeQuery();
				 if(rs.next()){
					 count = rs.getInt("count");
					 if(count>0){
					 fileExists = true;
					 }
				 }
				 System.out.println("fileExists--->"+fileExists);
				 if(!fileExists){
					 insertQuery = " insert into media_package_files(media_package_id, media_package_file_name, media_package_file_path," +
					 			   " file_type, isarrived) values(?,?,?,?,?)";
					 pstmt = connection.prepareStatement(insertQuery);
					 pstmt.setInt(1, mediaPackageId);
					 pstmt.setString(2, fileName);
					 pstmt.setString(3, inputFilePath);
					 pstmt.setString(4, fileType);
					 pstmt.setString(5, "Y");
					 pstmt.executeUpdate();
					 
					 metadataIngest = true;
				 }
			}
		}catch(Exception ex){
			logger.error("Error in metadataFileEntry() in DatabaseServiceImpl"+ex.getMessage());
			throw new DAMException("Error in metadataFileEntry");
		}
		finally{
			try{
				rs.close();
				pstmt.close();
				connection.close();
			}catch(SQLException ex){
				logger.error("Error in metadataFileEntry() in DatabaseServiceImpl"+ex.getMessage());
				throw new DAMException("Error in metadataFileEntry");
			}
		}
		logger.info("Exiting metadataFileEntry() in DatabaseServiceImpl-metadataIngest--->"+metadataIngest);
		return  fileExists;
	}
	
	
	
	
	public boolean updateMediaIngestStatus(String inputFilePath) throws DAMException{
		logger.info("Entering updateMediaIngestStatus() in DatabaseServiceImpl");
		boolean mediaIngestUpdate = false;
		File inputFile = null;
		String fileName = null;
		String packageName = null;
		String updateQuery = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String status = null;
		String statusQuery = null;
		ResultSet rsStatus = null;
		boolean metadataIngestDone = false;
		String statusIdQuery = null;
		String statusId = null;
		try{
			
			java.util.Date date = new java.util.Date();
			
			if(inputFilePath!=null){
				 inputFile = new File(inputFilePath);
				 fileName = inputFile.getName();
				 packageName = fileName.substring(0,fileName.lastIndexOf("."));
		
				 connection = getConnection();
				 
				 
				 statusQuery = "select status_id from media_package where media_package_name = ?";
				 pstmt = connection.prepareStatement(statusQuery);
				 pstmt.setString(1, packageName);
				 rsStatus = pstmt.executeQuery();
				 if(rsStatus.next()){
					status  = rsStatus.getString("status_id");
				 }
				 
				 if(status!=null && status.equalsIgnoreCase(ApplicationConstants.PACKAGE_STATUSID_METADATA_INGEST)){
					 metadataIngestDone = true;
				 }
				 
				 
				 
				 statusIdQuery = "select status_id from master_status where status_name = ?";
				 pstmt = connection.prepareStatement(statusIdQuery);
				 if(metadataIngestDone){
					 pstmt.setString(1, ApplicationConstants.PACKAGE_STATUSNAME_INGESTED);
				 }else{
					 pstmt.setString(1, ApplicationConstants.PACKAGE_STATUSNAME_MEDIA_INGEST);
				 }
				 rsStatus = pstmt.executeQuery();
				 if(rsStatus.next()){
					statusId  = rsStatus.getString("status_id");
				 }	 
					 
				 			 
				 updateQuery = "update media_package set status_id = ?, updated_date = ? where media_package_name = ?";
				 pstmt = connection.prepareStatement(updateQuery);
				 pstmt.setString(1, statusId);
				 pstmt.setDate(2, new java.sql.Date(date.getTime())); 
				 pstmt.setString(3, packageName);
				 updateCount = pstmt.executeUpdate();
				 if(updateCount>0){
					 logger.info("Successfully updated Media Ingest status in media_package table");
					 mediaIngestUpdate = true;
				 }
			}
		}catch(Exception ex){
			logger.error("Error in updateMediaIngestStatus() in DatabaseServiceImpl"+ex.getMessage());
			throw new DAMException("Error in updateMediaIngestStatus");
		}
		logger.info("Exiting updateMediaIngestStatus() in DatabaseServiceImpl");
		return metadataIngestDone;
	}
	
	public boolean updateMetadataIngestStatus(String inputFilePath)throws DAMException{
		logger.info("Entering updateMetadataIngestStatus() in DatabaseServiceImpl");
		boolean metadataIngestUpdate = false;
		File inputFile = null;
		String fileName = null;
		String packageName = null;
		String updateQuery = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String statusQuery = null;
		ResultSet rsStatus = null;
		String status = null;
		boolean mediaIngestDone = false;
		String statusIdQuery  = null;
		String statusId = null;
		try{

			java.util.Date date = new java.util.Date();
			
			if(inputFilePath!=null){
				 inputFile = new File(inputFilePath);
				 fileName = inputFile.getName();
				 packageName = fileName.substring(0,fileName.lastIndexOf("."));
				 
				 
				 connection = getConnection();
				 
				 statusQuery = "select status_id from media_package where media_package_name = ?";
				 pstmt = connection.prepareStatement(statusQuery);
				 pstmt.setString(1, packageName);
				 rsStatus = pstmt.executeQuery();
				 if(rsStatus.next()){
					status  = rsStatus.getString("status_id");
				 }
				 
				 if(status!=null && status.equalsIgnoreCase(ApplicationConstants.PACKAGE_STATUSID_MEDIA_INGEST)){
					 mediaIngestDone = true;
				 }
				 
		
				 
				 statusIdQuery = "select status_id from master_status where status_name = ?";
				 pstmt = connection.prepareStatement(statusIdQuery);
				 if(mediaIngestDone){
					 pstmt.setString(1, ApplicationConstants.PACKAGE_STATUSNAME_INGESTED);
				 }else{
					 pstmt.setString(1, ApplicationConstants.PACKAGE_STATUSNAME_METADATA_INGEST);
				 }
				 rsStatus = pstmt.executeQuery();
				 if(rsStatus.next()){
					statusId  = rsStatus.getString("status_id");
				 }	
				 
				 updateQuery = "update media_package set status_id=?, updated_date=?  where media_package_name = ?";
				 pstmt = connection.prepareStatement(updateQuery);
				 pstmt.setString(1, statusId);
				 pstmt.setDate(2,new java.sql.Date(date.getTime()));
				 pstmt.setString(3, packageName);
				 updateCount = pstmt.executeUpdate();
				 if(updateCount>0){
					 logger.info("Successfully updated Metadata Ingest status in media_package table");
					 metadataIngestUpdate = true;
				 }
			}
		}catch(Exception ex){
			logger.error("Error in updateMetadataIngestStatus() in DatabaseServiceImpl"+ex.getMessage());
			throw new DAMException("Error in updateMetadataIngestStatus");
		}
		logger.info("Exiting updateMetadataIngestStatus() in DatabaseServiceImpl");
		return mediaIngestDone;
	}
	
	public String getFileName(String packageName, String fileType,String inputFilePath)throws DAMException
	{
		logger.info("Entering in getFilePath() in DatabaseServiceImpl");
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String filename = null;
		String fetchQuery = null;
		//String filePath = null;
		try{
			
			fetchQuery = " select mpf.media_package_file_name filename from media_package_files mpf, "
				+" media_package mp	where mp.media_package_id = mpf.media_package_id and " 
				+" mpf.file_type = ? and mp.media_package_name = ? ";
			connection = getConnection();
			pstmt = connection.prepareStatement(fetchQuery);
			pstmt.setString(1,fileType);
			pstmt.setString(2, packageName);
			rs = pstmt.executeQuery();
			if(rs.next()){
				filename = rs.getString("filename");
			}
		}catch(Exception ex){
			logger.error("Error in getFilePath() in DatabaseServiceImpl--->"+ex.getMessage());
			throw new DAMException("unable to fetch the file path");
		}
		logger.info("Exiting getFilePath() in DatabaseServiceImpl-filename-->"+filename);
		return filename;
	}
	
	

	public int MediapackageEntry(String packageName,int vendorId)throws DAMException{
		logger.info("Entering packageEntry() in DatabaseServiceImpl");
		System.out.println("--------->"+packageName);
		Connection connection = null;
		int count = 0;
		String insertQuery = null;
		PreparedStatement pstmt = null;
		int insertCount = 0;
		ResultSet rs = null;
		
		
		try{
			
			java.util.Date date = new java.util.Date();	
			 connection = getConnection();
			 String Query = "select count(*) count from media_package where media_package_name = ?";
			 pstmt = connection.prepareStatement(Query);
			 pstmt.setString(1, packageName);
			 rs = pstmt.executeQuery();
			 if(rs.next()){
				count = rs.getInt("count"); 
			 }
			 System.out.println("count---->"+count);
			 if(count==0){
				/* System.out.println("vendorName--->"+vendorName);
				 vendorQuery = "select vendor_id from vendor where vendor_name = ?";
				 pstmt = connection.prepareStatement(vendorQuery);
				 pstmt.setString(1, vendorName);
				 rs = pstmt.executeQuery();
				 if(rs.next()){
					vendorId = rs.getInt("vendor_id"); 
				 }*/
				 System.out.println("vendorId--->"+vendorId);
				 insertQuery = " insert into media_package(media_package_name, status_id, vendor_id, created_date, updated_date) " +
				 			   " values(?,?,?,?,?)";
				 pstmt = connection.prepareStatement(insertQuery);
				 pstmt.setString(1, packageName);
				 pstmt.setString(2, ApplicationConstants.PACKAGE_STATUSID_START);
				 pstmt.setInt(3, vendorId);
				 pstmt.setDate(4,new java.sql.Date(date.getTime()));
				 pstmt.setDate(5,new java.sql.Date(date.getTime()));
				 insertCount = pstmt.executeUpdate();
				 System.out.println(insertCount+"--------------------------->");
			 }else{
				 logger.info("Package already exists in the table");
			 }
			
		}catch(Exception ex){
			logger.error("Error in packageEntry() in DatabaseServiceImpl"+ex.getMessage());
			throw new DAMException("Error in packageEntry");
		}finally{
			try{
				rs.close();
				pstmt.close();
				connection.close();
			}catch(SQLException ex){
				logger.error("Error in packageEntry() in DatabaseServiceImpl"+ex.getMessage());
				throw new DAMException("Error in packageEntry");
			}
		}
		logger.info("Exiting packageEntry() in DatabaseServiceImpl");
		return insertCount;
	}
	
}
