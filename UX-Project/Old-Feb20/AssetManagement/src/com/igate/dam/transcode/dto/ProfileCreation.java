
package com.igate.dam.transcode.dto;

import java.io.Serializable;

import com.igate.dam.common.framework.model.AbstractDomain;

/**
 * @author mj802966
 */

public class ProfileCreation extends AbstractDomain implements Serializable {
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProfileCreation [profileName=" + profileName + ", profileId="
				+ profileId + ", transcoder=" + transcoder + ", profilePath="
				+ profilePath + ", vendorid=" + vendorid + "]";
	}

	private static final long serialVersionUID = 1L;
	
	private String profileName;
	private int profileId;
	private String transcoder;
	
	private String profilePath;
	private int vendorid;
	

	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	

	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	
	

	public String getTranscoder() {
		return transcoder;
	}
	
	public void setTranscoder(String transcoder) {
		this.transcoder = transcoder;
	}

	
	

	public String getProfilePath() {
		return profilePath;
	}

	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}

	public int getVendorid() {
		return vendorid;
	}

	public void setVendorid(int vendorid) {
		this.vendorid = vendorid;
	}
	
    
}
