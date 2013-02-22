package com.igate.dam.filenamingservice.dto;

import java.util.ArrayList;
import java.util.List;

public class Vendor {

	private String vendorName;
	private List<String> mediaFormatList=new ArrayList<String>();
	private List<String> metadataFormatList=new ArrayList<String>();
	
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public List<String> getMediaFormatList() {
		return mediaFormatList;
	}
	public void setMediaFormatList(List<String> mediaFormatList) {
		this.mediaFormatList = mediaFormatList;
	}
	public List<String> getMetadataFormatList() {
		return metadataFormatList;
	}
	public void setMetadataFormatList(List<String> metadataFormatList) {
		this.metadataFormatList = metadataFormatList;
	}
}
