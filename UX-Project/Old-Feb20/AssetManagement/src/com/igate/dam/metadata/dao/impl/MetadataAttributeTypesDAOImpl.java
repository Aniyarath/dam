package com.igate.dam.metadata.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.common.framework.dao.hibernate.GenericHibernateDAO;
import com.igate.dam.metadata.dao.MetadataAttributeTypesDAO;

import com.igate.dam.metadata.dto.MasterMetadata;
import com.igate.dam.metadata.dto.Media;
import com.igate.dam.metadata.dto.MediaPackage;
import com.igate.dam.metadata.dto.MediaPackageFiles;
import com.igate.dam.metadata.dto.Metadata;
import com.igate.dam.metadata.dto.MetadataAttributeTypes;
import com.igate.dam.metadata.dto.MetadataAttributeValues;
import com.igate.dam.metadata.dto.MetadataVendorAssoc;
import com.igate.dam.metadata.dto.PackageMetadata;
import com.igate.dam.metadata.dto.Vendor;
import com.igate.dam.metadata.dto.VendorMetadata;

public class MetadataAttributeTypesDAOImpl extends GenericHibernateDAO<MetadataAttributeTypes> implements MetadataAttributeTypesDAO{

	
	@Override
	public List<MediaPackage> getPackgeNames(int vendorId)throws DAMException{

		List<MediaPackage> listofPackages=new ArrayList<MediaPackage>();
		try{
		   listofPackages=getSessionFactory().openSession().createQuery("select m from MediaPackage m where vendor_id="+vendorId).list();
		   System.out.println("lstMetadataAttributeType size"+listofPackages.size());

		   for(MediaPackage mediaPackage:listofPackages){
			System.out.println("lenth of "+mediaPackage.getMedia_package_name());
		   }
		}
		catch (Exception exception) {
			throw new DAMException("error while fetching package name");
		}

		return listofPackages;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<MediaPackage> getPackageNames(String packageName,int vendorId)throws DAMException{

		List<MediaPackage> listofPackages=new ArrayList<MediaPackage>();
		
		try{
		
			Query query=getSessionFactory().openSession().createQuery("select m from MediaPackage m " +
			"where upper(media_package_name) like :packageName and m.vendor_id=:vendor_id");
			query.setParameter("packageName",
					"%" + packageName.toUpperCase() + "%");
			query.setInteger("vendor_id", vendorId);
			listofPackages= query.list();
			System.out.println("lstMetadataAttributeType size"+listofPackages.size());

			for(MediaPackage mediaPackage:listofPackages){
				System.out.println("lenth of "+mediaPackage.getMedia_package_name());
			}
	         System.out.println("size of package---------------->"+listofPackages.size());
		}
		catch (Exception exception) {
			throw new DAMException("error while fetching packages");
		}
 
		return listofPackages;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<MediaPackage> getPackageNames(String packageName) throws DAMException{

		 List<MediaPackage> listofPackages=new ArrayList<MediaPackage>();
		 try{
				
		  
		    Query query=getSessionFactory().openSession().createQuery("select m from MediaPackage m " +
		                 "where upper(media_package_name) like :packageName");
		    query.setParameter("packageName",
				"%" + packageName.toUpperCase() + "%");
		
		    listofPackages= query.list();
		    System.out.println("lstMetadataAttributeType size"+listofPackages.size());

		    for(MediaPackage mediaPackage:listofPackages){
			  System.out.println("lenth of "+mediaPackage.getMedia_package_name());
		    }
		       System.out.println("size of package---------------->"+listofPackages.size());
		   }
          catch (Exception exception) {
			 throw new DAMException("error while fetching packages");
		  }
		  return listofPackages;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<MediaPackage> getRecentPackages()throws DAMException {
		
     try{
         return getSessionFactory().openSession().createQuery("select m from MediaPackage m order by media_package_id desc limit = 3 ").list();
     }
     catch (Exception exception) {
	
    	 throw new DAMException("error while fetching recent media package");
	 }
	}
	
	
	public MediaPackage getSpecifiedPackage(String packageName)throws DAMException
	{
		MediaPackage mediaPackage=new MediaPackage();
		try{
			System.out.println("inside my method---------------->");
			String hql = "SELECT m FROM MediaPackage m WHERE upper(media_package_name) like :pname";
	        Query query =getSessionFactory().openSession().createQuery(hql);
			query.setParameter("pname", packageName.toUpperCase());
			
			mediaPackage=(MediaPackage) query.uniqueResult();
			
			System.out.println("inside dao-------------->"+mediaPackage);
		}
	    catch (Exception exception) {
			throw new DAMException("error while fetching package");
		}
		return mediaPackage;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<MediaPackageFiles> getFileList(int id)throws DAMException
	{
		 List<MediaPackageFiles> listofPackages=new ArrayList<MediaPackageFiles>();
		try{
		 
		  listofPackages=getSessionFactory().openSession().createQuery("select m from MediaPackageFiles m " +
		  "where m.media_package_id = :packageId").setInteger("packageId",id).list();
		  
		  System.out.println("size in dao"+listofPackages.size());
		  System.out.println("idddd"+id);
		}
		catch (Exception exception) {
		  throw new DAMException("error while fetching package files");
		}
		return listofPackages;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VendorMetadata> getgetVendorMetadataFields(int vendorId)throws DAMException {
		System.out.println("Inside dao layer...........");
		

		int mastermetadataId = 0;
		int metadata_attr_type_id = 0;
		String master_metadata_name=null;
		String metadata_attribute_value=null;
		String metadata_attr_type = null;
		List<VendorMetadata> metadanameslist =new ArrayList<VendorMetadata>();
		try{
			
			Session session=getSessionFactory().openSession();
			
			Vendor v =(Vendor)session.get(Vendor.class,vendorId );
			System.out.println("vendor detail"+v.getVendor_id());
			List listofAvailableFields=v.getMetadataVendorAssocList();
			System.out.println("v.getMetadataVendorAssocList()"+v.getMetadataVendorAssocList());
			Iterator itr=listofAvailableFields.iterator();
			
			
			List valueList=new ArrayList();
			List<MetadataAttributeValues> valuesObj=new ArrayList<MetadataAttributeValues>();
			Map<Integer,StringBuffer> valuesMap=new HashMap<Integer, StringBuffer>();
			while(itr.hasNext()){
				MasterMetadata mm=(MasterMetadata)itr.next();
				mastermetadataId=mm.getMaster_metadata_id();
				master_metadata_name=mm.getMaster_metadata_name();
				

				System.out.println("master_metadata_id----"+mastermetadataId);
				System.out.println("getMaster_metadata_name----"+master_metadata_name);
				System.out.println("getMetadata_attr_type_id----"+mm.getMetadata_attr_type_id());
				metadata_attr_type_id=mm.getMetadata_attr_type_id();
				
				if(metadata_attr_type_id > 1)
				{
					valuesObj=getSessionFactory().openSession().createQuery("select m from MetadataAttributeValues m where m.master_metadata_id="+mastermetadataId).list();
			
				
			
					System.out.println("size of ...&&&&"+valuesObj.size());
					System.out.println("size of ...&&&&"+valuesObj.toString());
					
				
				}
				System.out.println("valueList size++++++"+valueList.size());
				System.out.println("valueList ++++++"+valueList.toString());

				MetadataAttributeTypes mat =(MetadataAttributeTypes)session.get(MetadataAttributeTypes.class,metadata_attr_type_id );
				metadata_attr_type=mat.getMetadata_attr_type();
				System.out.println("getMetadata_attr_type"+metadata_attr_type);
				int key=0;
				StringBuffer value=new StringBuffer();
				for(MetadataAttributeValues values:valuesObj)
				{
					System.out.println("mId"+values.getMaster_metadata_id());
					System.out.println("values"+values.getMetadata_attribute_value());
					key=values.getMaster_metadata_id();
					value.append(values.getMetadata_attribute_value()).append(",");
					//value.setLength(value.length()-1);
					
				}

				valuesMap.put(key, value);
				System.out.println("valueMapObj"+valuesMap.toString());
				
				
				VendorMetadata vendorMetadata=new VendorMetadata();
				vendorMetadata.setMaster_metadata_id(mastermetadataId);
				vendorMetadata.setMaster_metadata_name(master_metadata_name);
				vendorMetadata.setMetadata_attr_type(metadata_attr_type);
				vendorMetadata.setAttributevaluesMap(valuesMap);
				metadanameslist.add(vendorMetadata);
				
			}
			
			System.out.println("sisze of listofAvailableFields1"+metadanameslist.size());
			
		}
		catch (Exception exception) {
			
			throw new DAMException("error while fetching vendor master metadata");
		}
				
		return metadanameslist;
	}
/**************************************************************/

	@SuppressWarnings("unchecked")
	public List<PackageMetadata> getMediaPackageMetadataDetails(int packageId)throws DAMException{

		System.out.println("entered into getMediaPackageMetadataDetails dao ");
		System.out.println("packageId"+packageId);
		List<PackageMetadata> pakmetadataList=new ArrayList<PackageMetadata>();
		List<Metadata> metadataList=new ArrayList<Metadata>();
		
		try{
			metadataList=getSessionFactory().openSession().createQuery("select v from Metadata v where media_package_id="+packageId).list();

			System.out.println("size of metadataList"+metadataList.size());

			for(Metadata metaObj:metadataList){ 
				
				System.out.println("entered into for loop");

				int metadataId=metaObj.getMetadata_id();
				String metadataValue=metaObj.getMetadata_attr_value();
				int assId=metaObj.getMetadata_vendor_attr_assoc_id();
				System.out.println(metadataId+"--"+metadataValue+"--"+assId);
				System.out.println("assId:"+assId);
				List<MetadataVendorAssoc> assList=getSessionFactory().openSession().createQuery(
						"select ass from MetadataVendorAssoc ass where metadata_vendor_attributes_assoc_id="+assId).list();
				System.out.println("size of assList" +assList.size());

				int mmId=0;
				for(MetadataVendorAssoc assObj:assList)
				{
					mmId=assObj.getMaster_metadata_id();
					System.out.println("mmId:"+mmId);


					PackageMetadata packageMetadata=new PackageMetadata();
					packageMetadata.setMetadata_id(metadataId);
					packageMetadata.setMetadata_attr_value(metadataValue);
					packageMetadata.setMaster_metadata_id(mmId);
					pakmetadataList.add(packageMetadata);
				}
			}
			for(PackageMetadata mObj:pakmetadataList)

			{
				System.out.println("mId..."+mObj.getMetadata_id());
				System.out.println("mvalue.."+mObj.getMetadata_attr_value());
				System.out.println("mmId..."+mObj.getMaster_metadata_name());

			}
		}
	
        catch (Exception exception) {
			
        	throw new DAMException("error while fetching package metadata details");
		}

		return pakmetadataList; 
	}	



	@SuppressWarnings("unchecked")
	@Override
	public List<MetadataAttributeTypes> getAttributeTypeList()throws DAMException {
		
		List<MetadataAttributeTypes> listofPackages=new ArrayList<MetadataAttributeTypes>();
		try{
			
			listofPackages= getSessionFactory().openSession().createQuery("select t from MetadataAttributeTypes t").list();
			System.out.println("size of listofPackages"+listofPackages.size());
			for(MetadataAttributeTypes typeslist:listofPackages)
			{
				System.out.println("type name"+typeslist.getMetadata_attr_type());
			}
		}
		catch (Exception exception) {
			throw new DAMException("error while fetching metadata attribute types");
		}

		return listofPackages;
	}

	/*------------------------------------------*/
	@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
	public String updateMetadataDetails(Map<Integer, Metadata> metaMap,int packageId)throws DAMException{
		System.out.println("--------------------------eneteres into uopdate metadata-------------------");
		System.out.println("metatada object---"+metaMap.size());
		int master_metadata_id=0;
		int metadata_vendor_attributes_assoc_id=0;
		
		try{
			Session session=getSessionFactory().openSession();
			System.out.println("map size in dao:"+metaMap.size());
			
			for(Map.Entry<Integer, Metadata> mapObj:metaMap.entrySet()){
				
				master_metadata_id=mapObj.getKey();
				System.out.println("master metadata Id:::::"+master_metadata_id);
				
				
				Metadata metaData=mapObj.getValue();
				System.out.println("metadat value*************"
						+metaData.getMetadata_attr_value());
				List<MetadataVendorAssoc> metadataVendorAssocList=getSessionFactory().openSession().createQuery("select a from MetadataVendorAssoc a where a.master_metadata_id="+master_metadata_id).list();
				
				
				
				MetadataVendorAssoc metadataVendorAssoc=(MetadataVendorAssoc)metadataVendorAssocList.get(0);
				
				
				metadata_vendor_attributes_assoc_id=metadataVendorAssoc.getMetadata_vendor_attributes_assoc_id();
				System.out.println("assoc_id*********"+metadata_vendor_attributes_assoc_id);
			
				
				List<Metadata> valueList=getSessionFactory().openSession().createQuery("select m from Metadata m where m.metadata_vendor_attr_assoc_id="+metadata_vendor_attributes_assoc_id+" "+"and m.media_package_id="+packageId).list();
				if(valueList.size()>0)
				{
				
				String matadatavalue=null;
				System.out.println("valueList===="+valueList.toString());
				for(Metadata metadataObj:valueList)
				{
					matadatavalue=metadataObj.getMetadata_attr_value();
					metadataObj.setMetadata_attr_value(metaData.getMetadata_attr_value());
					getSessionFactory().getCurrentSession().saveOrUpdate(metadataObj);
				}
				}
				else{
					Metadata meta=new  Metadata();
					meta.setMetadata_vendor_attr_assoc_id(metadata_vendor_attributes_assoc_id);
					meta.setMetadata_attr_value(metaData.getMetadata_attr_value());
					meta.setMedia_package_id(packageId);
					getSessionFactory().getCurrentSession().saveOrUpdate(meta);
				}
				
			}
		}
		
		catch (NullPointerException nullPointerException) {

            throw new DAMException("enter data to save");
		}
		catch (Exception exception) {
			
			throw new DAMException("error while updating metadata details");
		}
	

		
		return "success";
	}

	
	
	@SuppressWarnings("unchecked")
	public List<MasterMetadata> getMasterMetadata(int vendorId)throws DAMException{
		List<MasterMetadata> lstMasterMetadata=new ArrayList<MasterMetadata>();
		try{
			
			Query query=getSessionFactory().openSession().createQuery("select m from MasterMetadata m where m.master_metadata_id NOT IN(select m.master_metadata_id from MasterMetadata m,MetadataVendorAssoc a where a.vendor_id=:vendorId and a.master_metadata_id=m.master_metadata_id)");
			query.setInteger("vendorId", vendorId);
			lstMasterMetadata=query.list();
			System.out.println("size in dao..."+lstMasterMetadata.size());
		}
		catch (Exception exception) {
			
			throw new DAMException("error while fetching master metadata");
		}
		return lstMasterMetadata;
	}


	@SuppressWarnings("unchecked")
	public List<MasterMetadata> getVendorSpecificMasterMetadata(int vendorId)throws DAMException{

		List<MasterMetadata> listVendorMasterMetadata=new ArrayList<MasterMetadata>();
		try{
			Query query=getSessionFactory().openSession().createQuery("select m from MasterMetadata m,MetadataVendorAssoc a where a.vendor_id=:vendorId and a.master_metadata_id=m.master_metadata_id)");
			query.setInteger("vendorId", vendorId);
			listVendorMasterMetadata=query.list();

			System.out.println("non size...in dao"+listVendorMasterMetadata.size());
		}
		catch (Exception exception) {
			throw new DAMException("error while fetching vendor metadata");
		}
		return listVendorMasterMetadata;
	}


    /*------------------------*/
	public String saveVendorAttribute(List<MetadataVendorAssoc> metadataVendorList)throws DAMException{

		String status=null;
		
		System.out.println("in dao...");
		try{
			for(MetadataVendorAssoc metadataVendorAssoc:metadataVendorList){

				int metadataAssocId=(Integer)getSessionFactory().getCurrentSession().save(metadataVendorAssoc);
				System.out.println("assoc id:"+metadataAssocId);
				
				if(metadataAssocId>0){
					status="success";
				}
			}


		}
		catch (NullPointerException nullPointerException) {

            throw new DAMException("enter data to save");
		}	
		catch (Exception exception) {
			System.out.println(exception.getMessage());
			throw new DAMException("error while saving vendor metadata attribute");
		}

		return status;

	}



	private SessionFactory sessionFactory;
	
	/*------------------------------*/
	@Override
	public void saveAttribute(MasterMetadata masterMetadata)throws DAMException {
		int id=0;
		try{
			System.out.println("Inside metadataDaoImpl");
			System.out.println("AttributeName....."+masterMetadata.getMaster_metadata_name());
			System.out.println("id of the Attribute....."+masterMetadata.getMetadata_attr_type_id());
			id=(Integer)getSessionFactory().getCurrentSession().save(masterMetadata);
			System.out.println("Inserted id .."+id);
		}

		catch (NullPointerException nullPointerException) {

            throw new DAMException("enter data to save");
		}
		
		catch (DataIntegrityViolationException dataIntegrityViolationException) {
			throw new DAMException("Attribute with the same name exist");
		}
		catch(Exception exception){
			
			System.out.println("Attribute with the same name exist");
			throw new DAMException("error while saving metadata");

		}
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MetadataAttributeTypes> getAttributeType()throws DAMException {
		
		List<MetadataAttributeTypes> lstMetadataAttributeTypes=null;
		try	{
			
			System.out.println("Coming Inside the MetadataDaoImpl");
			lstMetadataAttributeTypes=getSessionFactory().openSession().createQuery("from MetadataAttributeTypes").list();

		}
		catch(Exception exception){
			throw new DAMException("error while fetching metadata attribute types");
		}
		return lstMetadataAttributeTypes;
	}
    /*----------------------------*/
	public void saveAttributeValues(MasterMetadata masterMetadata,List<String> attributeValues)throws DAMException{

		MetadataAttributeValues metadataAttributeValues=null;
		int id=0;
		try{
			id=(Integer)getSessionFactory().getCurrentSession().save(masterMetadata);
			
			for(String value:attributeValues){
				metadataAttributeValues=new MetadataAttributeValues();
				metadataAttributeValues.setMaster_metadata_id(id);
				metadataAttributeValues.setMetadata_attribute_value(value);
				int vid=(Integer)getSessionFactory().getCurrentSession().save(metadataAttributeValues);
				System.out.println("vid...inserted:"+vid);
			}
		}
		catch (NullPointerException nullPointerException) {

            throw new DAMException("enter data to save");
		}
        catch (DataIntegrityViolationException dataIntegrityViolationException) {
			
			throw new DAMException("Attribute with the same name exist");
		}
		catch (Exception exception) {

              throw new DAMException("error while saving metadata attribute values");
		}


	}

	@Override
	public String saveMetadataDetails(Metadata metaData, int asscoId) {
		// TODO Auto-generated method stub
		return null;
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<Vendor> getVendors()throws DAMException {
		
		List<Vendor> vendorList=new ArrayList<Vendor>();
			
		try	{
			System.out.println("Coming Inside the MetadataDaoImpl");
			vendorList=getSessionFactory().openSession().createQuery("from Vendor").list();

		}
		catch(Exception exception){
				
			throw new DAMException("error while fetching vendors");
		}
		return vendorList;
	}
	



	@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
	public String updateMetadataDetails(Map<Integer, Metadata> metaMap)throws DAMException{
		
		System.out.println("--------------------------eneteres into uopdate metadata-------------------");
		System.out.println("metatada object---"+metaMap.toString());
		int master_metadata_id=0;
		int metadata_vendor_attributes_assoc_id=0;
		
		Session session=getSessionFactory().openSession();
		try{
			
			System.out.println("map size in dao:"+metaMap.size());
			
			for(Map.Entry<Integer, Metadata> mapObj:metaMap.entrySet()){
				
				master_metadata_id=mapObj.getKey();
				System.out.println("master metadata Id:::::"+master_metadata_id);
				
				
				Metadata metaData=mapObj.getValue();
				System.out.println("metadat value*************"
						+metaData.getMetadata_attr_value());
				List<MetadataVendorAssoc> metadataVendorAssocList=getSessionFactory().openSession().createQuery("select a from MetadataVendorAssoc a where a.master_metadata_id="+master_metadata_id).list();
				MetadataVendorAssoc metadataVendorAssoc=(MetadataVendorAssoc)metadataVendorAssocList.get(0);
				
				
				
				metadata_vendor_attributes_assoc_id=metadataVendorAssoc.getMetadata_vendor_attributes_assoc_id();
				System.out.println("assoc_id*********"+metadata_vendor_attributes_assoc_id);
				
				String matadatavalue=null;
				
				
				List<Metadata> valueList=getSessionFactory().openSession().createQuery("select m from Metadata m where m.metadata_vendor_attr_assoc_id="+metadata_vendor_attributes_assoc_id).list();
				
				System.out.println("valueList===="+valueList.toString());
				
				for(Metadata vl:valueList)
				{
					matadatavalue=vl.getMetadata_attr_value();
					vl.setMetadata_attr_value(metaData.getMetadata_attr_value());
				
				
				System.out.println("metada attibute value"+matadatavalue);
				
				System.out.println("metada attibute value to be update"+metaData.getMetadata_attr_value());
				
				
				getSessionFactory().getCurrentSession().update(vl);
				
				}
			    System.out.println("end loop---");
				
			}
		}
		catch (NullPointerException nullPointerException) {

            throw new DAMException("enter data to save");
		}
		
		catch (Exception exception) {

            throw new DAMException("error occurred while updating metadata details ");
          
		}
		
		return "success";
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<MediaPackageFiles> getFileType(String fileName)throws DAMException {
		
		System.out.println("inside dao");
		System.out.println("fileName"+fileName);
		
		List<MediaPackageFiles> listPackages=new ArrayList<MediaPackageFiles>();
		try{
			
			listPackages=getSessionFactory().openSession().createQuery("select m from MediaPackageFiles m where media_package_file_name='"+fileName+"'").list();
			System.out.println("lstMediapackagefiletypes size"+listPackages.size());
			
			for(MediaPackageFiles mediaPackage:listPackages){
				System.out.println("filename::"+mediaPackage.getMedia_package_file_name());
				System.out.println("filetype::"+mediaPackage.getFile_type());
			}
		}
		catch (Exception exception) {
			
			throw new DAMException("error occurred while fetching media package files");
		}


		return listPackages;
	}
		

	@SuppressWarnings("unchecked")
	public List<MediaPackage> loadPackgeNames(int vendorId)throws DAMException {

		List<MediaPackage> listofPackages=new ArrayList<MediaPackage>();
		try{
			listofPackages=getSessionFactory().openSession().createQuery("select m from MediaPackage m where vendor_id="+vendorId).list();
			
			System.out.println("lstMetadataAttributeType size"+listofPackages.size());

			for(MediaPackage mediaPackage:listofPackages){
				System.out.println("lenth of "+mediaPackage.getMedia_package_name());
			}
		}
		catch (Exception exception) {
			
			throw new DAMException("error occurred while loading media packages");
		}


		return listofPackages;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MediaPackage> displayPackgeNames()throws DAMException {
		List<MediaPackage> listofPackages=new ArrayList<MediaPackage>();
		try{
		listofPackages=getSessionFactory().openSession().createQuery("select m from MediaPackage m ").list();
		System.out.println("lstMetadataAttributeType size"+listofPackages.size());

		for(MediaPackage mediaPackage:listofPackages){
			System.out.println("lenth of "+mediaPackage.getMedia_package_name());
		}
		}
		catch (Exception exception) {
			throw new DAMException("error occurred while fetching media packages");
		}

		return listofPackages;
	}
	
	
	



	
	
	public int getvendorId(int packageId)throws DAMException
	{
		System.out.println("entered into dao layer");
		int vendorId=0;
		try
		{
	  MediaPackage mediaPackage=(MediaPackage) getSessionFactory().getCurrentSession().createQuery(
				"select m from MediaPackage m where media_package_id="+packageId).uniqueResult();
		vendorId=mediaPackage.getVendor_id();
		System.out.println("vendor_id in dao---------"+vendorId);
		}
		catch(Exception exception)
		{
			throw new DAMException("unable to get vendorid");
		}
		return vendorId;
	}
	
}

