package com.igate.dam.metadata.service;

import java.util.List;
import java.util.Map;

import com.igate.dam.common.exception.DAMException;
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

public interface MetadataService {

    public List<MediaPackage> getPackgeNames(String packageName,int vendorId)throws DAMException;
    
    public List<MediaPackage> getPackgeNames(String packageName)throws DAMException;

	public List<MediaPackage> getRecentPackages()throws DAMException;
	
	//public List<Media> getPackages(String packageName);
	
	public List<MediaPackageFiles> getFileList(int packageId)throws DAMException;
	
	public List<VendorMetadata> getgetVendorMetadataFields(int vendorId)throws DAMException;
	public List<PackageMetadata> getMediaPackageMetadataDetails(int packageId)throws DAMException;
	public List<MetadataAttributeTypes> getAttributeTypeList()throws DAMException;
	public Map<MetadataAttributeTypes, List<MetadataAttributeValues>> getMasterAttributeTypes()throws DAMException;
	public Map<String, String> getVendorMetadataAttributeTypes()throws DAMException;
	public Map<String, String> getMasterAttributeValues()throws DAMException;
	public void saveMasterAttributeValues(Map<String, String> valueMap)throws DAMException;

	public String saveMetadataDetails(Metadata metaData,int asscoId)throws DAMException;
	public String updateMetadataDetails(Map<Integer, Metadata> metaMap, int packageId)throws DAMException;

	public List<MasterMetadata> getMasterMetadata(int vendorId)throws DAMException;


	public List<MasterMetadata> getVendorSpecificMasterMetadata(int vendorId)throws DAMException;
	public String saveVendorAttribute(List<MetadataVendorAssoc> metadataVendorList)throws DAMException;

	public void saveAttribute(MasterMetadata masterMetadata)throws DAMException;
	public Map<String, String> getMasterAttributes()throws DAMException;
	public List<MetadataAttributeTypes> getAttributeType()throws DAMException;
	public void saveAttributeValues(MasterMetadata masterMetadata,List<String> attributeValues)throws DAMException;

	public List<MediaPackage> getPackgeNames(int vendorId)throws DAMException;
	public List<Vendor> getVendors()throws DAMException;
	public List<MediaPackageFiles> getFileType(String fileName)throws DAMException;
	
	public MediaPackage getSpecifiedPackage(String packageName)throws DAMException;
	
	public List<MediaPackage> loadPackgeNames(int vendorId)throws DAMException;
	public List<MediaPackage> displayPackgeNames()throws DAMException;
	
	public int getvendorId(int packageId)throws DAMException;
}
