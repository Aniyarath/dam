package com.igate.dam.metadata.dto;

public class PackageMetadata {
	private String master_metadata_name;
	private String metadata_attr_value;
	private int master_metadata_id;
	private String metadata_attr_type;
	private int metadata_id;
	
	public int getMetadata_id() {
		return metadata_id;
	}
	public void setMetadata_id(int metadata_id) {
		this.metadata_id = metadata_id;
	}
	public int getMaster_metadata_id() {
		return master_metadata_id;
	}
	public void setMaster_metadata_id(int master_metadata_id) {
		this.master_metadata_id = master_metadata_id;
	}
	public String getMetadata_attr_type() {
		return metadata_attr_type;
	}
	public void setMetadata_attr_type(String metadata_attr_type) {
		this.metadata_attr_type = metadata_attr_type;
	}
	public String getMaster_metadata_name() {
		return master_metadata_name;
	}
	public void setMaster_metadata_name(String master_metadata_name) {
		this.master_metadata_name = master_metadata_name;
	}
	public String getMetadata_attr_value() {
		return metadata_attr_value;
	}
	public void setMetadata_attr_value(String metadata_attr_value) {
		this.metadata_attr_value = metadata_attr_value;
	}
	
	

}
