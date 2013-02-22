package com.igate.dam.metadata.dto;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Vendor {

	private int vendor_id;
	private String vendor_code;
	private String vendor_name;
	private List<MetadataVendorAssoc> metadataVendorAssocList;
	public List<MetadataVendorAssoc> getMetadataVendorAssocList() {
		return metadataVendorAssocList;
	}
	public void setMetadataVendorAssocList(
			List<MetadataVendorAssoc> metadataVendorAssocList) {
		this.metadataVendorAssocList = metadataVendorAssocList;
	}
	public int getVendor_id() {
		return vendor_id;
	}
	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
	}
	public String getVendor_code() {
		return vendor_code;
	}
	public void setVendor_code(String vendor_code) {
		this.vendor_code = vendor_code;
	}
	public String getVendor_name() {
		return vendor_name;
	}
	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}
	
	
}
