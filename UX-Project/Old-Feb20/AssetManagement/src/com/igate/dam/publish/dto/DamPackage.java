package com.igate.dam.publish.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author mj802966
 *
 */
public class DamPackage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String media_package_name;
	private String fileName;
	private String image;
	private List<MetadataFields> metadataFields;

	public String getMedia_package_name() {
		return media_package_name;
	}
	
	public void setMedia_package_name(String media_package_name) {
		this.media_package_name = media_package_name;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DamPackage [media_package_name=" + media_package_name
				+ ", fileName=" + fileName + ", image=" + image
				+ ", metadataFields=" + metadataFields + "]";
	}

	public List<MetadataFields> getMetadataFields() {
		return metadataFields;
	}

	
	public void setMetadataFields(List<MetadataFields> metadataFields) {
		this.metadataFields = metadataFields;
	}

	
	

}
