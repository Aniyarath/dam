package com.igate.dam.transcode.dto;

import java.io.Serializable;

import com.igate.dam.common.framework.model.AbstractDomain;

/**
 * @author mj802966
 *
 */

public class Vendors extends AbstractDomain implements Serializable{
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", vendorName=" + vendorName
				+ ", vendorCode=" + vendorCode + "]";
	}

	private static final long serialVersionUID = 1L;
	
private int vendorId;
private String vendorName;
private String vendorCode;


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


public String getVendorCode() {
	return vendorCode;
}

public void setVendorCode(String vendorCode) {
	this.vendorCode = vendorCode;
}


}
