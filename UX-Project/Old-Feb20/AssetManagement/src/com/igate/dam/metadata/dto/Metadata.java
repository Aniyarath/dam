package com.igate.dam.metadata.dto;

import org.springframework.transaction.annotation.Transactional;

import com.igate.dam.common.framework.model.AbstractDomain;
@Transactional
public class Metadata extends AbstractDomain {
	private int metadata_id;
	private int media_package_id;
	private String metadata_attr_value;
	private int metadata_vendor_attr_assoc_id;
	
	public int getMetadata_id() {
		return metadata_id;
	}
	public void setMetadata_id(int metadata_id) {
		this.metadata_id = metadata_id;
	}
	public int getMedia_package_id() {
		return media_package_id;
	}
	public void setMedia_package_id(int media_package_id) {
		this.media_package_id = media_package_id;
	}
	public String getMetadata_attr_value() {
		return metadata_attr_value;
	}
	public void setMetadata_attr_value(String metadata_attr_value) {
		this.metadata_attr_value = metadata_attr_value;
	}
	public int getMetadata_vendor_attr_assoc_id() {
		return metadata_vendor_attr_assoc_id;
	}
	public void setMetadata_vendor_attr_assoc_id(int metadata_vendor_attr_assoc_id) {
		this.metadata_vendor_attr_assoc_id = metadata_vendor_attr_assoc_id;
	}
	
	
	

}
