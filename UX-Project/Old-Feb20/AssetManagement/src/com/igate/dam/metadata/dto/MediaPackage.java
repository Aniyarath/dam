package com.igate.dam.metadata.dto;

import java.util.List;

import com.igate.dam.common.framework.model.AbstractDomain;

public class MediaPackage extends AbstractDomain{
	private int media_package_id;
	private String media_package_name;
	private String media_ingest_status;
	private String metadata_ingest_status;
	private List<Metadata> metadatalist;
	private String status_id;
	private String transcoding_status;
	private List<MediaPackageFiles> packageFileList;
	
	public String getTranscoding_status() {
		return transcoding_status;
	}
	public void setTranscoding_status(String transcoding_status) {
		this.transcoding_status = transcoding_status;
	}
	public String getStatus_id() {
		return status_id;
	}
	public void setStatus_id(String status_id) {
		this.status_id = status_id;
	}
	private MasterStatus masterStatus;
	
	public MasterStatus getMasterStatus() {
		return masterStatus;
	}
	public void setMasterStatus(MasterStatus masterStatus) {
		this.masterStatus = masterStatus;
	}
	public List<Metadata> getMetadatalist() {
		return metadatalist;
	}
	public void setMetadatalist(List<Metadata> metadatlist) {
		this.metadatalist = metadatlist;
	}
	private int vendor_id;
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
	public String getMedia_ingest_status() {
		return media_ingest_status;
	}
	public void setMedia_ingest_status(String media_ingest_status) {
		this.media_ingest_status = media_ingest_status;
	}
	public String getMetadata_ingest_status() {
		return metadata_ingest_status;
	}
	public void setMetadata_ingest_status(String metadata_ingest_status) {
		this.metadata_ingest_status = metadata_ingest_status;
	}
	public int getVendor_id() {
		return vendor_id;
	}
	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
	}
	
	/**
	 * @return the packageFileList
	 */
	public List<MediaPackageFiles> getPackageFileList() {
		return packageFileList;
	}
	/**
	 * @param packageFileList the packageFileList to set
	 */
	public void setPackageFileList(List<MediaPackageFiles> packageFileList) {
		this.packageFileList = packageFileList;
	}
	public String toString() {
		return "MediaPackage [media_package_id=" + media_package_id
				+ ", media_package_name=" + media_package_name
				+ ", media_ingest_status=" + media_ingest_status
				+ ", metadata_ingest_status=" + metadata_ingest_status
				+ ", metadatalist=" + metadatalist + ", masterStatus="
				+ masterStatus + ", vendor_id=" + vendor_id + ", status_id=" + status_id + "]";
	}
}
