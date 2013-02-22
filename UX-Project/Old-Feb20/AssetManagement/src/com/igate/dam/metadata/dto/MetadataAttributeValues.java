package com.igate.dam.metadata.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.igate.dam.common.framework.model.AbstractDomain;

@Entity
@Table(name="metadata_attribute_values")
public class MetadataAttributeValues extends AbstractDomain implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private int metadata_attribute_value_id;
	private String metadata_attribute_value;
	private int master_metadata_id;
	public int getMetadata_attribute_value_id() {
		return metadata_attribute_value_id;
	}
	public void setMetadata_attribute_value_id(int metadata_attribute_value_id) {
		this.metadata_attribute_value_id = metadata_attribute_value_id;
	}
	public String getMetadata_attribute_value() {
		return metadata_attribute_value;
	}
	public void setMetadata_attribute_value(String metadata_attribute_value) {
		this.metadata_attribute_value = metadata_attribute_value;
	}
	public int getMaster_metadata_id() {
		return master_metadata_id;
	}
	public void setMaster_metadata_id(int master_metadata_id) {
		this.master_metadata_id = master_metadata_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
