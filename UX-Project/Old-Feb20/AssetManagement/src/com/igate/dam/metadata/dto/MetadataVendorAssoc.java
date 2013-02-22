package com.igate.dam.metadata.dto;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class MetadataVendorAssoc {

	private int metadata_vendor_attributes_assoc_id;
	private int vendor_id;
	private int master_metadata_id;
	private List<Integer> metadataList;
	public List<Integer> getMetadataList() {
		return metadataList;
	}
	public void setMetadataList(List<Integer> metadataList) {
		this.metadataList = metadataList;
	}
	public int getMetadata_vendor_attributes_assoc_id() {
		return metadata_vendor_attributes_assoc_id;
	}
	public void setMetadata_vendor_attributes_assoc_id(
			int metadata_vendor_attributes_assoc_id) {
		this.metadata_vendor_attributes_assoc_id = metadata_vendor_attributes_assoc_id;
	}
	public int getVendor_id() {
		return vendor_id;
	}
	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
	}
	public int getMaster_metadata_id() {
		return master_metadata_id;
	}
	public void setMaster_metadata_id(int master_metadata_id) {
		this.master_metadata_id = master_metadata_id;
	}
	
}
