/**
 * 
 */
package com.igate.dam.workflowseq.dto;

import java.io.Serializable;

import com.igate.dam.common.framework.model.AbstractDomain;

/**
 * @author 706440
 * 
 */
public class MediaPkgWorkflowSEQ extends AbstractDomain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1117123810449563487L;
	
	private int media_pkg_workflow_seq_id;
	private int media_package_id;
	private String pkg_workflow_sequence;
	private int vendor_ID;
	

	public int getVendor_ID() {
		return vendor_ID;
	}

	public void setVendor_ID(int vendor_ID) {
		this.vendor_ID = vendor_ID;
	}

	public int getMedia_pkg_workflow_seq_id() {
		return media_pkg_workflow_seq_id;
	}

	public void setMedia_pkg_workflow_seq_id(int media_pkg_workflow_seq_id) {
		this.media_pkg_workflow_seq_id = media_pkg_workflow_seq_id;
	}

	public String getPkg_workflow_sequence() {
		return pkg_workflow_sequence;
	}

	public void setPkg_workflow_sequence(String pkg_workflow_sequence) {
		this.pkg_workflow_sequence = pkg_workflow_sequence;
	}

	public int getMedia_package_id() {
		return media_package_id;
	}

	public void setMedia_package_id(int media_package_id) {
		this.media_package_id = media_package_id;
	}

	

	@Override
	public String toString() {
		return "MediaPackageFiles [media_pkg_workflow_seq_id="
				+ media_pkg_workflow_seq_id + ", media_package_id="
				+ media_package_id + ", pkg_workflow_sequence="
				+ pkg_workflow_sequence + "]";
	}

}
