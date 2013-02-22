package com.igate.dam.uploadmetadata.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.milyn.Smooks;
import org.milyn.SmooksException;
import org.milyn.container.ExecutionContext;
import org.milyn.payload.JavaResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xml.sax.SAXException;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.common.framework.dao.hibernate.GenericHibernateDAO;
import com.igate.dam.constants.util.ApplicationConstants;
import com.igate.dam.database.service.DatabaseServiceIntf;
import com.igate.dam.database.service.impl.DatabaseServiceImpl;
import com.igate.dam.metadata.dto.MasterStatus;
import com.igate.dam.metadata.dto.MediaPackage;
import com.igate.dam.metadata.dto.Metadata;
import com.igate.dam.metadata.exception.DamSmooksException;
import com.igate.dam.uploadmetadata.dto.ListMetadata;


@Repository("uploadMetadataDAOImpl")
public class UploadMetadataDAOImpl extends GenericHibernateDAO<Metadata> implements UploadMetadataDAO {
	
@Autowired
SessionFactory sessionFactory;

public static final Logger logger = Logger.getLogger(UploadMetadataDAOImpl.class);

	public ListMetadata xmlToJavaTransformation(String inputFileName,
			String configFileName,String xmlContent,String vendorName) throws DAMException {

		System.out.println("inside xmlToJavaTransformation" + inputFileName
				+ configFileName);
		
		logger.info("input file---->"+inputFileName);
		JavaResult javaResult = new JavaResult();
		StringReader reader = null;
		Smooks smooks = null;
		ListMetadata metadataItems=null;
		//String xmlContent = null;
		ExecutionContext executionContext = null;
		
		boolean success = false;
		try {
			System.out.println("smooks-->" + smooks);
			smooks = new Smooks(configFileName);
			System.out.println("smooks-->" + smooks);
			System.out.println("before executionContext");
			executionContext = smooks.createExecutionContext();
			//xmlContent = getFileContent(inputFileName);
			System.out.println("after  xmlContent" + xmlContent);
			reader = new StringReader(xmlContent);
			smooks.filterSource(executionContext, new StreamSource(reader),
					javaResult);

		 metadataItems = (ListMetadata) javaResult
					.getBean("listmetadata");
			logger.info("Metadata : " + metadataItems);
			
			System.out.println("Metadata : " + metadataItems);
           
			if(metadataItems.equals(null))
			{
				throw new DAMException("Please check the input file");
			}

		} catch (IOException ie) {
			
			logger.info("Unable to find smooks-config-XmlToJava file");
           	throw new DAMException("Please check the input file");
           	
		} catch (SAXException se) {

			logger.info("exception occured while parsing the xml file");
			throw new DAMException("Please check the input file");
			
		} catch (SmooksException exception) {
			
			logger.info("Exception occured with smooks while filtering source");
			throw new DAMException("Please check the input file");
			
		} catch (Exception exception) {
		
			logger.info("Exception occured with smooks while filtering source");
			throw new DAMException("Please check the input file");
		}
		return metadataItems;
	}

public boolean saveXmlcontents(ListMetadata metadataitems,String inputFileName,int  vendorId) throws DAMException
{
	boolean success=false;
	String fileName = inputFileName;
	fileName = fileName.substring(0, fileName.lastIndexOf("."));
	logger.info("file name---->"+fileName);
	System.out.println(fileName);
	try
	{
	DatabaseServiceIntf databaseServiceIntf=new DatabaseServiceImpl();
    databaseServiceIntf.packageEntry(inputFileName,vendorId);
    
	List<Metadata> metadataList = saveXmlContent(metadataitems,fileName);
	Iterator<Metadata> iterator = metadataList.iterator();
	while (iterator.hasNext()) {
		Metadata metadataHBM = iterator.next();
		System.out.println(metadataHBM);
	}
	success = saveMetadata(metadataList);
	}
	catch(Exception sqlException)
	{
		System.out.println("exception occured while saving data");
		throw new DAMException("error occured while saving data");
	}
	return success;
}

	public int metadataIngest(String mediaName) throws DAMException{
		int mediaId ;
		try
		{
		String hql = "SELECT m. media_package_id FROM MediaPackage m WHERE m.media_package_name =:pname";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("pname", mediaName);
		mediaId = (Integer) query.uniqueResult();
		System.out.println("media id--------------" + mediaId);
		logger.info("mediaId--------->"+mediaId);
		}
		catch(Exception exception)
		{
			throw new DAMException("unable to fetch media package details");
		}
		return mediaId;

	}

	public int getmetadataid(String attributename) {
		int metadataId = 0;
		try {
			String hql = "SELECT m.master_metadata_id FROM MasterMetadata m WHERE m.master_metadata_name =:pname";

			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("pname", attributename);
			metadataId = (Integer) query.uniqueResult();
			System.out.println("---------------------------" + metadataId);
			logger.info("-------metadata id--------------------" + metadataId);
			

		} catch (NullPointerException ne) {
			
			logger.info("metadata attribute  is not existing--->null pointer exception");
			System.out.println("metadata attribute is not existing---");
		}
		return metadataId;
	}

	
    public int getassociationid(int metadataId, int vendorid) {

		int assocId = 0;
		try {
			String hql = "select mv.metadata_vendor_attributes_assoc_id from MetadataVendorAssoc mv where mv.vendor_id=:vid and mv.master_metadata_id=:mid";

			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setInteger("vid", vendorid);
			query.setInteger("mid", metadataId);

			if (query.uniqueResult().equals(null)) {
				assocId = 0;
			}

			else {
				assocId = (Integer) query.uniqueResult();
				System.out.println("-------------Assoc ID-------------------"
						+ assocId);
				logger.info("----Association ID--------"+assocId);
			}
		} catch (NullPointerException ne) {
			logger.info("association id is not existing------null pointer exception");
			System.out.println("null pointer exce");
		}
		return assocId;
	}

	public List<Metadata> saveXmlContent(ListMetadata metadataitems,
			String mediaName)throws DAMException{
		List<Metadata> valueList = new ArrayList<Metadata>();
		int mediaId = metadataIngest(mediaName);
		System.out.println(mediaId);
		int vendorId = getVendorid(mediaId);
		List<com.igate.dam.uploadmetadata.dto.Metadata> metadataList = metadataitems
				.getMetadataItems();

		System.out.println("--------------" + metadataList.size());

		Iterator<com.igate.dam.uploadmetadata.dto.Metadata> iterator3 = metadataList
				.iterator();
		while (iterator3.hasNext()) {
			com.igate.dam.uploadmetadata.dto.Metadata metadatas = iterator3
					.next();
			System.out.println("*****************" + metadatas);
			int metadataid = getmetadataid(metadatas.getName());
			if (metadataid > 0) {
				int assId = getassociationid(metadataid, vendorId);
				System.out.println("ass id-----" + assId);
				if (assId > 0) {
					Metadata metdataHBM = new Metadata();
					metdataHBM.setMedia_package_id(mediaId);
					metdataHBM.setMetadata_attr_value(metadatas.getValue());
					metdataHBM.setMetadata_vendor_attr_assoc_id(assId);
					valueList.add(metdataHBM);
				}
			}
		}
		System.out.println("size-----------" + valueList.size());
		return valueList;
	}

	public boolean saveMetadata(List<Metadata> valueList) {
		boolean success=false;
		Iterator iterator1 = valueList.iterator();
		while (iterator1.hasNext()) {
			Metadata metadataHBM = (Metadata) iterator1.next();
			System.out.println(metadataHBM);
			int metadataId = getmetadataId(metadataHBM);
			if (metadataId > 0) {
				Metadata metadataUpdate = new Metadata();
				metadataUpdate.setMedia_package_id(metadataHBM
						.getMedia_package_id());
				metadataUpdate.setMetadata_attr_value(metadataHBM
						.getMetadata_attr_value());
				metadataUpdate.setMetadata_vendor_attr_assoc_id(metadataHBM
						.getMetadata_vendor_attr_assoc_id());
				metadataUpdate.setMetadata_id(metadataId);
				System.out.println("update-------------" + metadataUpdate);
				sessionFactory.getCurrentSession().saveOrUpdate(metadataUpdate);
				success=true;
			} else {
				Metadata metadataUpdate = new Metadata();
				metadataUpdate.setMedia_package_id(metadataHBM
						.getMedia_package_id());
				metadataUpdate.setMetadata_attr_value(metadataHBM
						.getMetadata_attr_value());
				metadataUpdate.setMetadata_vendor_attr_assoc_id(metadataHBM
						.getMetadata_vendor_attr_assoc_id());
				sessionFactory.getCurrentSession().save(metadataUpdate);
				success=true;
			}

		}

		return success;
	}

	public int getmetadataId(Metadata metadataHBM) {
		int metadataId = 0;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select m.metadata_id from Metadata m where m.media_package_id=:mediaId"
									+ ""
									+ " and m.metadata_vendor_attr_assoc_id=:mvid");
			query.setInteger("mediaId", metadataHBM.getMedia_package_id());
			query.setInteger("mvid",
					metadataHBM.getMetadata_vendor_attr_assoc_id());
			if (query.uniqueResult().equals(null)) {
				metadataId = 0;
			} else {
				metadataId = (Integer) query.uniqueResult();
			}
		} catch (NullPointerException nullPointerException) {
			System.out.println("metadata id is not exist------");
		}
		return metadataId;
	}

	public int getVendorid(int mediaId) {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"select m.vendor_id from MediaPackage m where m.media_package_id=:mediaId");
		query.setInteger("mediaId", mediaId);
		int vendorid = (Integer) query.uniqueResult();
		return vendorid;
	}

/*-------------------------------------------------------------------------------------------------------------------*/
	
	public String packageEntry(String inputFilePath,String vendorName) throws DAMException{
		System.out.println("Entering packageEntry() in DatabaseServiceImpl");
		String fileName = null;
		String fileExtension = null;
		File inputFile = null;
    	long count = 0;
		String vendorQuery = null;
		int vendorId = 0;
		

			
			java.util.Date date = new java.util.Date();
	try
	{
			if(inputFilePath!=null){
			 inputFile = new File(inputFilePath);
			 fileName = inputFile.getName();
			 fileName = fileName.substring(0,fileName.lastIndexOf("."));
			 fileExtension=inputFilePath.substring(inputFilePath.lastIndexOf(".")+1,inputFilePath.length());
			
			 String hql = "select count(*) from MediaPackage m where m.media_package_name =:mediaName";
			
			 Query query=sessionFactory.getCurrentSession().createQuery(hql);
			 query.setString("mediaName", fileName);
			 count= (Long) query.uniqueResult();
			 System.out.println(count+"-----count----------");
			    if(count==0){
				 System.out.println("vendorName--->"+vendorName);
				 vendorQuery = "select v.vendor_id from Vendor v where vendor_name =:vname";
				 Query query1=sessionFactory.getCurrentSession().createQuery(vendorQuery);
				 query1.setString("vname", vendorName);
				 vendorId=(Integer) query1.uniqueResult();
				 System.out.println("vendorId--->"+vendorId);
				 
				 MediaPackage mediaPackage=new MediaPackage();
				 MasterStatus masterStatus=new MasterStatus();
				 masterStatus.setStatusId("START");
				 mediaPackage.setMedia_package_name(fileName);
				 mediaPackage.setStatus_id("START");
				 mediaPackage.setVendor_id(vendorId);
				 mediaPackage.setMasterStatus(masterStatus);
				 System.out.println("***********"+mediaPackage);
				 sessionFactory.getCurrentSession().save(mediaPackage);
			
			 }else{
				System.out.println("Package already exists in the table");
			 }
			}
			}catch(Exception exception)
			{
				throw new DAMException("Error in packageEntry ");	
			}
			
		
		return fileExtension;
	}
	
	
/*--------------------------------------------------------------------------------------------*/
	
	public String packageEntrys(String inputFilePath,String vendorName) throws DAMException{
		System.out.println("Entering packageEntry() in DatabaseServiceImpl");
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
		int vendorId = 0;
		
		try{
			
			java.util.Date date = new java.util.Date();
			
			if(inputFilePath!=null){
			 inputFile = new File(inputFilePath);
			 fileName = inputFile.getName();
			 fileName = fileName.substring(0,fileName.lastIndexOf("."));
			 fileExtension=inputFilePath.substring(inputFilePath.lastIndexOf(".")+1,inputFilePath.length());
			 //connection = getConnection();
			 String Query = "select count(*) count from media_package where media_package_name = ?";
			 pstmt = connection.prepareStatement(Query);
			 pstmt.setString(1, fileName);
			 rs = pstmt.executeQuery();
			 if(rs.next()){
				count = rs.getInt("count"); 
			 }
			 if(count==0){
				 System.out.println("vendorName--->"+vendorName);
				 vendorQuery = "select vendor_id from vendor where vendor_name = ?";
				 pstmt = connection.prepareStatement(vendorQuery);
				 pstmt.setString(1, vendorName);
				 rs = pstmt.executeQuery();
				 if(rs.next()){
					vendorId = rs.getInt("vendor_id"); 
				 }
				 System.out.println("vendorId--->"+vendorId);
				 insertQuery = " insert into media_package(media_package_name, status_id, vendor_id, created_date, updated_date) " +
				 			   " values(?,?,?,?,?)";
				 pstmt = connection.prepareStatement(insertQuery);
				 pstmt.setString(1, fileName);
				 pstmt.setString(2, ApplicationConstants.PACKAGE_STATUSID_START);
				 pstmt.setInt(3, vendorId);
				 pstmt.setDate(4,new java.sql.Date(date.getTime()));
				 pstmt.setDate(5,new java.sql.Date(date.getTime()));
				 insertCount = pstmt.executeUpdate();
			 }else{
				System.out.println("Package already exists in the table");
			 }
			}
		}catch(Exception ex){
			System.out.println("Error in packageEntry() in DatabaseServiceImpl"+ex.getMessage());
			throw new DAMException("Error in packageEntry");
		}finally{
			try{
				rs.close();
				pstmt.close();
				connection.close();
			}catch(SQLException ex){
				System.out.println("Error in packageEntry() in DatabaseServiceImpl"+ex.getMessage());
				throw new DAMException("Error in packageEntry ");
			}
		}
		System.out.println("Exiting packageEntry() in DatabaseServiceImpl");
		return fileExtension;
	}
	
	
	public boolean metadataFileEntry(String inputFilePath,String fileType) throws DAMException{
	System.out.println("Entering metadataFileEntry() in DatabaseServiceImpl");
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
				 inputFile = new File(inputFilePath);
				 fileName = inputFile.getName();
				 packageName = fileName.substring(0,fileName.lastIndexOf("."));
				 System.out.println("packageName--->"+packageName);
				 packageNameQuery = "select media_package_id from media_package where media_package_name = ?";
				// connection = getConnection();
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
			System.out.println("Error in metadataFileEntry() in DatabaseServiceImpl"+ex.getMessage());
			throw new DAMException("Error in metadataFileEntry");
		}
		finally{
			try{
				rs.close();
				pstmt.close();
				connection.close();
			}catch(SQLException ex){
				System.out.println("Error in metadataFileEntry() in DatabaseServiceImpl"+ex.getMessage());
				throw new DAMException("Error in metadataFileEntry");
			}
		}
		System.out.println("Exiting metadataFileEntry() in DatabaseServiceImpl-metadataIngest--->"+metadataIngest);
		return metadataIngest;
	}
	
	public boolean updateMetadataIngestStatus(String inputFilePath) throws DAMException{
		System.out.println("Entering updateMetadataIngestStatus() in DatabaseServiceImpl");
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
				 
				 
				// connection = getConnection();
				 
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
					 System.out.println("Successfully updated Metadata Ingest status in media_package table");
					 metadataIngestUpdate = true;
				 }
			}
		}catch(Exception ex){
			System.out.println("Error in updateMetadataIngestStatus() in DatabaseServiceImpl"+ex.getMessage());
			throw new DAMException("Error in updating MetadataIngestStatus");
		}
		System.out.println("Exiting updateMetadataIngestStatus() in DatabaseServiceImpl");
		return mediaIngestDone;
	}

	}

