package com.igate.dam.publish.dto;

import java.io.Serializable;

import com.igate.dam.common.framework.model.AbstractDomain;

/**
 * @author mj802966
 *
 */
public class PublishProfile extends AbstractDomain implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int publish_profile_id;
	private String publish_profile_name;
	private int vendor_id;
	private String publish_profile_path;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PublishProfile [publish_profile_id=" + publish_profile_id
				+ ", publish_profile_name=" + publish_profile_name
				+ ", vendor_id=" + vendor_id + ", publish_profile_path="
				+ publish_profile_path + "]";
	}

	public int getPublish_profile_id() {
		return publish_profile_id;
	}
	
	public void setPublish_profile_id(int publish_profile_id) {
		this.publish_profile_id = publish_profile_id;
	}

	public String getPublish_profile_name() {
		return publish_profile_name;
	}
	
	public void setPublish_profile_name(String publish_profile_name) {
		this.publish_profile_name = publish_profile_name;
	}
	
	public int getVendor_id() {
		return vendor_id;
	}
	
	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
	}
	
	public String getPublish_profile_path() {
		return publish_profile_path;
	}

	public void setPublish_profile_path(String publish_profile_path) {
		this.publish_profile_path = publish_profile_path;
	}
	
	

}
