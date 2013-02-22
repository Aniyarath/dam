package com.igate.dam.publish.dto;

import java.io.Serializable;

/**
 * @author mj802966
 *
 */
public class MetadataFields implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	

	private String attributeType;
	private String attributeValue;

	public String getAttributeType() {
		return attributeType;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MetadataFields [attributeType=" + attributeType
				+ ", attributeValue=" + attributeValue + "]";
	}
	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}
	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
}
