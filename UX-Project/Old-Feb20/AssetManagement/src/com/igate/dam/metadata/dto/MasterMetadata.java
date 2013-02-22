package com.igate.dam.metadata.dto;

import java.util.List;

public class MasterMetadata {
	
	private int master_metadata_id;
	private String master_metadata_name;
	private int metadata_attr_type_id;
	private List<MetadataAttributeTypes> MetadatTpyesList;
    private String metadataValues;
	
	public String getMetadataValues() {
		return metadataValues;
	}
	public void setMetadataValues(String metadataValues) {
		this.metadataValues = metadataValues;
	}
	public List<MetadataAttributeTypes> getMetadatTpyesList() {
		return MetadatTpyesList;
	}
	public void setMetadatTpyesList(List<MetadataAttributeTypes> metadatTpyesList) {
		MetadatTpyesList = metadatTpyesList;
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

}
