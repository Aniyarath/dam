package com.igate.dam.metadata.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.igate.dam.common.exception.DAMException;
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
import com.igate.dam.metadata.service.MetadataService;


@Transactional(readOnly=false)
public class MetadataServiceImpl implements MetadataService{

	private MetadataAttributeTypesDAO metadataAttributeTypesDAO;
	
	public MetadataAttributeTypesDAO getMetadataAttributeTypesDAO() {
		return metadataAttributeTypesDAO;
	}

	public void setMetadataAttributeTypesDAO(MetadataAttributeTypesDAO metadataAttributeTypesDAO) {
		this.metadataAttributeTypesDAO = metadataAttributeTypesDAO;
	}
	

	public List<MediaPackage> getPackgeNames(String packageName,int vendorId)throws DAMException {
		List<MediaPackage>packagesNamesValueList =  new ArrayList<MediaPackage>();
		System.out.println("Inside service layer....");
		packagesNamesValueList=metadataAttributeTypesDAO.getPackageNames(packageName,vendorId);
		return packagesNamesValueList;
	}

	public List<MediaPackage> getPackgeNames(String packageName)throws DAMException {
		List<MediaPackage>packagesNamesValueList =  new ArrayList<MediaPackage>();
		System.out.println("Inside service layer....");
		packagesNamesValueList=metadataAttributeTypesDAO.getPackageNames(packageName);
		return packagesNamesValueList;
	}
	
	
	
	
	
	public List<MediaPackage> getRecentPackages()throws DAMException {
		
			return metadataAttributeTypesDAO.getRecentPackages();
		
		
	}
	
	
	
	public List<MediaPackageFiles> getFileList(int packageId)throws DAMException
	{
		List<MediaPackageFiles> packagesFileList =  new ArrayList<MediaPackageFiles>();
		packagesFileList=metadataAttributeTypesDAO.getFileList(packageId);
		System.out.println("in service classsssssss"+packagesFileList.size());
		return  packagesFileList;
	}
	
	@Override
	public List<VendorMetadata> getgetVendorMetadataFields(int vendorId)throws DAMException {
		List<VendorMetadata> packagesNamesValueList =new ArrayList<VendorMetadata>();
		packagesNamesValueList =metadataAttributeTypesDAO.getgetVendorMetadataFields(vendorId);
		return packagesNamesValueList;
	}

	@Override
	public List<PackageMetadata> getMediaPackageMetadataDetails(int packageId)throws DAMException {
	
		return metadataAttributeTypesDAO.getMediaPackageMetadataDetails(packageId);
	}

	@Override
	public List<MetadataAttributeTypes> getAttributeTypeList()throws DAMException{
	
		return metadataAttributeTypesDAO.getAttributeTypeList();
	}

public String saveMetadataDetails(Metadata metaData,int asscoId)throws DAMException{
		
		String status="success";
		metadataAttributeTypesDAO.saveMetadataDetails(metaData, asscoId);
		return status;
	}
	
	public String updateMetadataDetails(Map<Integer, Metadata> metaMap)throws DAMException{
		
		String status="success";
		metadataAttributeTypesDAO.updateMetadataDetails(metaMap);
		return status;
	}
	
	    public List<MasterMetadata> getMasterMetadata(int vendorId)throws DAMException{
			List<MasterMetadata> lstMasterMetadata=new ArrayList<MasterMetadata>();
			lstMasterMetadata=metadataAttributeTypesDAO.getMasterMetadata(vendorId);
			return lstMasterMetadata;
		}
		
	
	
	    public List<MasterMetadata> getVendorSpecificMasterMetadata(int vendorId)throws DAMException{
	
		List<MasterMetadata> listVendorMasterMetadata=new ArrayList<MasterMetadata>();
		listVendorMasterMetadata=metadataAttributeTypesDAO.getVendorSpecificMasterMetadata(vendorId);
		return listVendorMasterMetadata;
	}
	    
	    public String saveVendorAttribute(List<MetadataVendorAssoc> metadataVendorList)throws DAMException{
		 
	    	System.out.println("in service...");
	    	String status=null;
	    	status=metadataAttributeTypesDAO.saveVendorAttribute(metadataVendorList);
	    	System.out.println("back in service..");
	    	return status;
	    }

		@Override
		public Map<MetadataAttributeTypes, List<MetadataAttributeValues>> getMasterAttributeTypes()throws DAMException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Map<String, String> getVendorMetadataAttributeTypes() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Map<String, String> getMasterAttributeValues() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void saveMasterAttributeValues(Map<String, String> valueMap) {
			// TODO Auto-generated method stub
			
		}
		


		public void saveAttribute(MasterMetadata masterMetadata) throws DAMException{
			
	       System.out.println("service....."+masterMetadata.getMaster_metadata_name());
			
			metadataAttributeTypesDAO.saveAttribute(masterMetadata);
			
		}

		@Override
		public List<MetadataAttributeTypes> getAttributeType()throws DAMException{
			// TODO Auto-generated method stub
	        List<MetadataAttributeTypes>  metadataList= new ArrayList<MetadataAttributeTypes>();
			
			metadataList = metadataAttributeTypesDAO.getAttributeType();
			return metadataList;
		}
		
		public void saveAttributeValues(MasterMetadata masterMetadata,List<String> attributeValues)throws DAMException{
			metadataAttributeTypesDAO.saveAttributeValues(masterMetadata, attributeValues);
		}

		@Override
		public Map<String, String> getMasterAttributes() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<MediaPackage> getPackgeNames(int vendorId)throws DAMException {
			
			return metadataAttributeTypesDAO.getPackgeNames(vendorId);
		}
		
        public List<Vendor> getVendors()throws DAMException{
			
			List<Vendor> vendorList=new ArrayList<Vendor>();
			vendorList=metadataAttributeTypesDAO.getVendors();
			return vendorList;
		}

        public String updateMetadataDetails(Map<Integer, Metadata> metaMap,int packageId)throws DAMException{
	
	     String status="success";
	     metadataAttributeTypesDAO.updateMetadataDetails(metaMap,packageId);
	     return status;
        }
        @Override
        public List<MediaPackageFiles> getFileType(String fileName)throws DAMException {

	     return metadataAttributeTypesDAO.getFileType(fileName);
        }

        @Override
        public MediaPackage getSpecifiedPackage(String packageName)throws DAMException {
	     MediaPackage mediaPackage=metadataAttributeTypesDAO.getSpecifiedPackage(packageName);
	     return mediaPackage;
        }

        @Override
        public List<MediaPackage> loadPackgeNames(int vendorId)throws DAMException {
         	List<MediaPackage> packagelist=metadataAttributeTypesDAO.loadPackgeNames(vendorId);
	        return packagelist;
        }



        @Override
        public List<MediaPackage> displayPackgeNames()throws DAMException {
	
	        return metadataAttributeTypesDAO.displayPackgeNames();
       }

       @Override
       public int getvendorId(int packageId)throws DAMException {
	        int vendorId=metadataAttributeTypesDAO.getvendorId(packageId);
	     return vendorId;
       }
}
