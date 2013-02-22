package com.igate.dam.metadata.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MetaDataModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1678393015333695365L;
	private Map<Integer,String> metadataMap = new HashMap<Integer,String>();
	
	
	
	
	public Map<Integer, String> getMetadataMap() {
		return metadataMap;
	}
	public void setMetadataMap(Map<Integer, String> metadataMap2) {
		this.metadataMap = metadataMap2;
	}

}
