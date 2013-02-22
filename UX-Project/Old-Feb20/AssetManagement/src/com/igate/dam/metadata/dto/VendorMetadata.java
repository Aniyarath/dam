package com.igate.dam.metadata.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendorMetadata {
	private int metadata_attr_type_id;
	private String metadata_attr_type;
	private String metadata_attribute_value;
	private String master_metadata_name;
	private int master_metadata_id;
	private String  metadata_attr_value;
	
	
	public String getMetadata_attr_value() {
		return metadata_attr_value;
	}
	public void setMetadata_attr_value(String metadata_attr_value) {
		this.metadata_attr_value = metadata_attr_value;
	}
	private Map<Integer, StringBuffer> attributevaluesMap=new HashMap<Integer, StringBuffer>();

	
	public Map<Integer, StringBuffer> getAttributevaluesMap() {
		return attributevaluesMap;
	}
	public void setAttributevaluesMap(Map<Integer, StringBuffer> attributevaluesMap) {
		this.attributevaluesMap = attributevaluesMap;
	}
	public int getMaster_metadata_id() {
		return master_metadata_id;
	}
	public void setMaster_metadata_id(int master_metadata_id) {
		this.master_metadata_id = master_metadata_id;
	}


	
	public String getMaster_metadata_name() {
		return master_metadata_name;
	}
	public void setMaster_metadata_name(String master_metadata_name) {
		this.master_metadata_name = master_metadata_name;
	}
	public int getMetadata_attr_type_id() {
		return metadata_attr_type_id;
	}
	public void setMetadata_attr_type_id(int metadata_attr_type_id) {
		this.metadata_attr_type_id = metadata_attr_type_id;
	}
	public String getMetadata_attr_type() {
		return metadata_attr_type;
	}
	public void setMetadata_attr_type(String metadata_attr_type) {
		this.metadata_attr_type = metadata_attr_type;
	}
	public String getMetadata_attribute_value() {
		return metadata_attribute_value;
	}
	public void setMetadata_attribute_value(String metadata_attribute_value) {
		this.metadata_attribute_value = metadata_attribute_value;
	}
	@Override
	public String toString() {
		return "VendorMetadata [metadata_attr_type_id=" + metadata_attr_type_id
				+ ", metadata_attr_type=" + metadata_attr_type
				+ ", metadata_attribute_value=" + metadata_attribute_value
				+ ", master_metadata_name=" + master_metadata_name
				+ ", master_metadata_id=" + master_metadata_id
				+ ", metadata_attr_value=" + metadata_attr_value
				+ ", attributevaluesMap=" + attributevaluesMap + "]";
	}
	
	
	

}
