package com.igate.dam.metadata.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.igate.dam.common.framework.model.AbstractDomain;
@Entity
@Table(name="metadata_attribute_types")
public class MetadataAttributeTypes extends AbstractDomain implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private int metadata_attr_type_id;
	private String metadata_attr_type;
	private String metadata_attr_code;
	private int metadata_attr_length;
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
	public String getMetadata_attr_code() {
		return metadata_attr_code;
	}
	public void setMetadata_attr_code(String metadata_attr_code) {
		this.metadata_attr_code = metadata_attr_code;
	}
	public int getMetadata_attr_length() {
		return metadata_attr_length;
	}
	public void setMetadata_attr_length(int metadata_attr_length) {
		this.metadata_attr_length = metadata_attr_length;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}