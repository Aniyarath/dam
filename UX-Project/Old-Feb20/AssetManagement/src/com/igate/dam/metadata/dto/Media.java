package com.igate.dam.metadata.dto;

import java.io.Serializable;

import com.igate.dam.common.framework.model.AbstractDomain;

public class Media extends AbstractDomain implements Serializable{

	
	
	private static final long serialVersionUID = 1L;
	private String media_package_name;
	private int  media_package_id;
	private String statusName;
	
	public int getMedia_package_id() {
		return media_package_id;
	}
	public void setMedia_package_id(int media_package_id) {
		this.media_package_id = media_package_id;
	}
	public String getMedia_package_name() {
		return media_package_name;
	}
	public void setMedia_package_name(String media_package_name) {
		this.media_package_name = media_package_name;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	@Override
	public String toString() {
		return "Media [media_package_name=" + media_package_name
				+ ", media_package_id=" + media_package_id + ", statusName="
				+ statusName + "]";
	}
	
}
