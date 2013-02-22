package com.igate.dam.model;

import java.io.Serializable;

public class UserProfile implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4699600013917466347L;
	@Override
	public String toString() {
		return "UserProfile [vendorId=" + vendorId + ", vendorName="
				+ vendorName + "]";
	}
	private int vendorId;
	private String vendorName;
	
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
}
