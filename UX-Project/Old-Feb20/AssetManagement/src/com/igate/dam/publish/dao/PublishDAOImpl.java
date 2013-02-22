package com.igate.dam.publish.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.xml.transform.stream.StreamResult;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.milyn.Smooks;
import org.milyn.container.ExecutionContext;
import org.milyn.payload.JavaSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.common.framework.dao.hibernate.GenericHibernateDAO;
import com.igate.dam.metadata.dto.MasterStatus;
import com.igate.dam.metadata.dto.MediaPackage;
import com.igate.dam.publish.dto.DamPackage;

import com.igate.dam.publish.dto.MetadataFields;
import com.igate.dam.publish.dto.PublishProfile;

/**
 * @author mj802966
 * 
 */
@Repository("publishDAOImpl")

public class PublishDAOImpl extends GenericHibernateDAO<MediaPackage> implements
		PublishDAO {

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("deprecation")
	public List<MediaPackage> listMediaFiles(int vendorId) throws DAMException{

		System.out.println("inside dao...entered.....");

		List<MediaPackage> MediaList = new ArrayList<MediaPackage>();
		List<MediaPackage> media = new ArrayList<MediaPackage>();
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("select m from MediaPackage m where m.vendor_id=:vendorId and m.status_id=:status");
			query.setInteger("vendorId", vendorId)	;
			query.setString("status","TRA");
			media=query.list();
			/*Iterator<MediaPackage> iterator = MediaList.iterator();
			while (iterator.hasNext()) {
				MediaPackage media1 = iterator.next();
				if (media1.getStatus_id().equalsIgnoreCase("TRA")) {
					media.add(media1);
				}
			}*/
			System.out.println(MediaList.size());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new DAMException("unable to get the media packages");
		}
		return media;
	}

	
	
	public String getVendorName(int mediaid) throws DAMException {
		String vendorName ;
try
{
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"select m.vendor_id from MediaPackage m where m.media_package_id=:mediaid");
		query.setInteger("mediaid", mediaid);
		int vendorid = (Integer) query.uniqueResult();
		System.out.println("vendor id...dao......" + vendorid);
		Query query2 = sessionFactory.getCurrentSession().createQuery(
				"select v.vendor_name from Vendor v where v.vendor_id=:id");
		query2.setInteger("id", vendorid);
        vendorName = (String) query2.uniqueResult();
		System.out.println("name....dao..........." + vendorName);
}
catch(Exception exception)
{
	throw new DAMException("vendor details are not avialable");
}
		return vendorName;
	}

	
	
	
	public int getVendorid(int mediaid) throws DAMException {
		int vendorid;
		try
		{Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"select m.vendor_id from MediaPackage m where m.media_package_id=:mediaid");
		query.setInteger("mediaid", mediaid);
		 vendorid = (Integer) query.uniqueResult();
		}
		catch(Exception exception)
		{
			throw new DAMException("vendor details are not avialable");
		}
		return vendorid;
	}

	
	
	public String getMediaFileName(int mediaid) throws DAMException{
		System.out.println("id..........." + mediaid);
		String fileName = null;
		try
		{
			List<MediaPackage> medias = sessionFactory.getCurrentSession().find("from MediaPackage");
		Iterator<MediaPackage> iterator = medias.iterator();
		while (iterator.hasNext()) {
			MediaPackage media = iterator.next();
			if (media.getMedia_package_id() == mediaid) {
				fileName = media.getMedia_package_name();
			}

		}
		}
		catch(Exception exception)
		{
			throw new DAMException("unable to fetch media details");
		}
		return fileName;
	}

	
	
	public List<String> moveAllFile(String vendorName, String mediaPackage,
			String profileName) throws DAMException {

		String profilePath = getProfilePath(profileName);
		String inputDirectory = null;
		String destinationPath = null;
		List<String> filesList = new ArrayList<String>();
		File file1 = new File(profilePath);
		try {
			FileReader fileReader = new FileReader(file1);
			BufferedReader br = new BufferedReader(fileReader);
			String s;
			while ((s = br.readLine()) != null) {
				if (s.contains("inputFile")) {
					String[] input = s.split("=");
					inputDirectory = input[1].trim();
					System.out.println("inputDirectory------------>"
							+ inputDirectory);
				} else if (s.contains("outputFile")) {
					String[] output = s.split("=");
					destinationPath = output[1].trim();
					System.out.println("out-------------------"
							+ destinationPath);
				}
			}
			fileReader.close();
			int filesLength = 0;
			String sourcePath = inputDirectory;
			System.out.println(sourcePath);
			File source = null;
			File destination = null;
			File[] files = null;

			source = new File(sourcePath);
			if (!source.exists()) {
				System.out.println("Source folder doesn't exist");

			}
			destination = new File(destinationPath);
			if (!destination.exists()) {
				System.out.println("Destination folder doesn't exist");

			}
			files = source.listFiles();
			if (files != null) {
				filesLength = files.length;
			}
			for (int i = 0; i < filesLength; i++) {
				if (((files[i].getName().contains(".jpg")) && (files[i]
						.getName().contains(mediaPackage)))
						|| ((files[i].getName().contains("Output")) && (files[i]
								.getName().contains(mediaPackage)))) {
					System.out.println(files[i].getName());
					filesList.add(files[i].getName());
					copyFile(files[i].getName(), sourcePath, destinationPath);
				}
			}
			Iterator<String> iterator = filesList.iterator();
			while (iterator.hasNext()) {
				String string = (String) iterator.next();
				System.out.println("from list......." + string);

			}
		} catch (IOException ioException) {
			System.out.println("profiles not exist");
			throw new DAMException("publishing profile is not existing..");
		} catch (Exception ex) {
			System.out.println("Error in moving all the files"
					+ ex.getMessage());
			throw new DAMException("Error in moving all the files..");

		}

		return filesList;

	}

	
	
	
	public String runSmooksTransform(DamPackage inputJavaObject,
			String profileName) throws DAMException {

		Smooks smooks;
		String destinationPath = null;
		StringWriter writer = new StringWriter();
		String fileName = null;
		try {

			String profilePath = getProfilePath(profileName);
			File file1 = new File(profilePath);
			FileReader fileReader = new FileReader(file1);
			BufferedReader br = new BufferedReader(fileReader);
			String s;
			while ((s = br.readLine()) != null) {

				if (s.contains("outputFile")) {
					String[] output = s.split("=");
					destinationPath = output[1].trim();
					System.out.println("out-------------------"
							+ destinationPath);
				}
			}
			fileReader.close();
			smooks = new Smooks("smooks-config.xml");
			System.out.println("config loaded");
			ExecutionContext executionContext = smooks.createExecutionContext();
			smooks.filterSource(executionContext, new JavaSource(
					inputJavaObject), new StreamResult(writer));

			fileName = destinationPath + "\\"
					+ inputJavaObject.getMedia_package_name() + ".xml";
			FileWriter fileWriter = new FileWriter(fileName);
			fileWriter.write(writer.toString());
			fileWriter.flush();
			fileWriter.close();
			System.out.println(writer.toString());
		} catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("profile not exist.....");
			throw new DAMException("profile not exist.....");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error occured during xml generation");
			throw new DAMException("error occured during xml generation");
		} finally {
			System.out.println("in transformation final");
		}
		return fileName;

	}

	
	
	public void updateStatus(MediaPackage mediaPackage) throws DAMException{
		System.out.println("dao" + mediaPackage);
		try
		{MasterStatus masterStatus = new MasterStatus();
		masterStatus.setStatusId(mediaPackage.getStatus_id());
		mediaPackage.setMasterStatus(masterStatus);
		System.out.println(mediaPackage);
		sessionFactory.getCurrentSession().saveOrUpdate(mediaPackage);
		}
		catch(Exception exception)
		{
			throw new DAMException("unable to save details");
		}
	}

	/*------------------------------------------------------------------------------------------------*/

	
	
	@SuppressWarnings("unchecked")
	public List<String> getVendorNames() throws DAMException{
		List<String> vendorNameList;
		try
		{String query = "Select v.vendor_name from Vendor v";

         vendorNameList = getSessionFactory().openSession()
				.createQuery(query).list();
		System.out.println("dao----------" + vendorNameList.size());
		}
		catch(Exception exception)
		{
			throw new DAMException("unable to fetch vendor details");
		}
		return vendorNameList;
	}

	
	
	public int getVendorid(String name) throws DAMException{
		int vendorid;
		
		try
		{
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select m.vendor_id from Vendor m where m.vendor_name=:name");
		query.setString("name", name);
	    vendorid = (Integer) query.uniqueResult();
		}
		catch(Exception exception)
		{
			throw new DAMException("unable to fetch vendor details");
		}
		return vendorid;
	}

	
	
	@SuppressWarnings("unchecked")
	public List<PublishProfile> listProfiles(int vendorid)throws DAMException {

		List<PublishProfile> profiles ;
		try
		{
		profiles 	= sessionFactory
				.getCurrentSession()
				.createQuery(
						"from PublishProfile p where p.vendor_id=:vendorid ")
				.setInteger("vendorid", vendorid).list();
		System.out.println("dao      ---------size" + profiles.size());
		}
		catch(Exception exception)
		{
			throw new DAMException("unable to get the profiles");
		}
		return profiles;
	}

	
	
	public List<MetadataFields> getAttributes(int mediaid, int vendorid)throws DAMException {
		List<MetadataFields> attributeMedia = new ArrayList<MetadataFields>();
		try
		{Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"select mm.master_metadata_name, m.metadata_attr_value from MasterMetadata mm, Metadata m, MetadataVendorAssoc mv where mm.master_metadata_id = mv.master_metadata_id and mv.metadata_vendor_attributes_assoc_id = m.metadata_vendor_attr_assoc_id and m.media_package_id =:mediaid and mv.vendor_id =:vendorid");
		query.setInteger("mediaid", mediaid);
		query.setInteger("vendorid", vendorid);
		
		@SuppressWarnings("rawtypes")
		List<List> attributeMap = query.list();
		System.out.println("map size--------------map" + attributeMap.size());
		@SuppressWarnings("rawtypes")
		Iterator iterator = attributeMap.iterator();
		while (iterator.hasNext()) {
			MetadataFields m2 = new MetadataFields();
			Object[] obj = (Object[]) iterator.next();
			String name = (String) obj[0];
			String value = (String) obj[1];
			m2.setAttributeType(name);
			m2.setAttributeValue(value);
			attributeMedia.add(m2);

		}
		}
		catch(Exception exception)
		{
			throw new DAMException("unable to fetch metadata details");
		}
		return attributeMedia;

	}


	
	public String getProfilePath(String profileName) throws DAMException {
		String profilePath;
		try
		{Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"select p.publish_profile_path from PublishProfile p where p.publish_profile_name=:profileName");
		query.setString("profileName", profileName);
	     profilePath = (String) query.uniqueResult();
		}
		catch(Exception exception)
		{
			throw new DAMException("unable to get the profile path");
		}
		return profilePath;
	}


	
	
	public static void copyFile(String fileName, String sourcePath,
			String destinationPath) throws DAMException {
		int len;
		System.out.println("source===========" + sourcePath);
		System.out.println("destination===========" + destinationPath);
		System.out.println("filename" + fileName);
		InputStream inputStream = null;
		OutputStream outputStream = null;
		File sourceFile = null;
		File destinationFile = null;
		File destinationFolder = null;
		String src = sourcePath + "\\" + fileName;
		String des = destinationPath + "\\" + fileName;
		System.out.println("src=========" + src);
		try {

			sourceFile = new File(src);
			System.out.println(sourceFile.getName());
			if (!sourceFile.exists()) {
				System.out.println("Source file doesn't exist");

			}
			destinationFolder = new File(destinationPath);
			if (!destinationFolder.exists()) {
				System.out.println("Destination folder doesn't exist");

			}

			destinationFile = new File(des);
			inputStream = new FileInputStream(sourceFile);
			outputStream = new FileOutputStream(destinationFile);
			byte[] buf = new byte[1024];
			while ((len = inputStream.read(buf)) > 0) {
				outputStream.write(buf, 0, len);
			}
			inputStream.close();
			outputStream.close();

		} catch (Exception ex) {
			System.out.println("Error in copying files " + ex.getMessage());
			throw new DAMException("Error in copying files ");

		}
	}

	/*--------------------------------------------------------------------------------------------------*/

	
	
	public Map<String, String> previewVideo(List<MediaPackage> mediaList)
			throws DAMException {

		Map<String, String> lowresNameMap = new LinkedHashMap<String, String>();

		for (MediaPackage mediaPackage : mediaList) {
			try {
				lowresNameMap.put(mediaPackage.getMedia_package_name(),
						getLowresName(mediaPackage.getMedia_package_name()));
			} catch (Exception exception) {
				throw new DAMException("media files are not existing...");
			}

		}

		return lowresNameMap;
	}

	
	
	private String getLowresName(String name) throws DAMException {
		System.out.println(name
				+ "------------loweres file function-------------------------");

		String fileName = null;
		int filesLength = 0;
		ResourceBundle rb = ResourceBundle.getBundle("damUtil");
		String sourcePath = rb.getString("inputDirectory");
		String filePath = null;

		System.out.println("sourcePath" + sourcePath);
		File source = null;

		File[] files = null;
		File file = null;
		try {
			source = new File(sourcePath);
			if (!source.exists()) {
				System.out.println("Source folder doesn't exist");

			}

			files = source.listFiles();
			if (files != null) {
				filesLength = files.length;
			}
			for (int i = 0; i < filesLength; i++) {
				if (((files[i].getName().contains("_lowres")) && (files[i]
						.getName().contains(name)))) {
					System.out.println("file name...." + files[i].getName());
					fileName = files[i].getName();

					break;

				}
			}
			if (fileName == null) {
				fileName = "";
				file = new File(sourcePath,
						URLDecoder.decode(fileName, "UTF-8"));
			} else {
				file = new File(sourcePath,
						URLDecoder.decode(fileName, "UTF-8"));
				filePath = (sourcePath + "/" + fileName);
			}
			System.out.println(name + "-------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAMException("media files are not existing...");
		}
		return file.getPath();

	}

	
	
	@Override
	public List<String> fetchPosterNVideo(String name) throws DAMException

	{
		System.out.println("entered into dao");

		String fileName = null;
		int filesLength = 0;
		List<String> filesList = new ArrayList<String>();

		String filePath = null;
		ResourceBundle rb = ResourceBundle.getBundle("damUtil");
		String sourcePath = rb.getString("inputDirectory");

		System.out.println("sourcePath" + sourcePath);
		File source = null;

		File[] files = null;

		try {
			source = new File(sourcePath);
			if (!source.exists()) {
				System.out.println("Source folder doesn't exist");

			}

			files = source.listFiles();
			if (files != null) {
				filesLength = files.length;
				System.out.println("number of files" + filesLength);
			}

			boolean previewIsAvailable = false;
			for (int i = 0; i < filesLength; i++) {
				if (((files[i].getName().contains("_lowres.ogv")) && (files[i]
						.getName().contains(name)))) {
					System.out.println("file name...." + files[i].getName());
					fileName = files[i].getName();
					previewIsAvailable = true;

					filePath = (fileName);
					filesList.add(filePath);
					System.out.println("filePath******" + filesList.toString());
					break;
				}
			}

			if (previewIsAvailable) {
				for (int i = 0; i < filesLength; i++) {
					if (((files[i].getName().contains(".jpg")) && (files[i]
							.getName().contains(name)))) {
						System.out
								.println("file name...." + files[i].getName());
						fileName = files[i].getName();
						previewIsAvailable = true;

						filePath = (fileName);
						filesList.add(filePath);
						System.out.println("filePath******"
								+ filesList.toString());
						break;
					}
				}
			} else {
				fileName = "previewNotAvailable.jpg";
				filePath = (fileName);
				filesList.add(filePath);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			throw new DAMException("media files are not available...");
		}
		return filesList;

	}

}
