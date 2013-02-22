package com.igate.dam.uploadmetadata.dto;

import java.util.List;

/**
 * @author mj802966
 *
 */
public class ListMetadata {

	 /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ListMetadata [MetadataItems=" + MetadataItems + "]";
	}

	private List<Metadata> MetadataItems;

	/**
	 * @return the metadataItems
	 */
	public List<Metadata> getMetadataItems() {
		return MetadataItems;
	}

	/**
	 * @param metadataItems the metadataItems to set
	 */
	public void setMetadataItems(List<Metadata> metadataItems) {
		MetadataItems = metadataItems;
	}
}
