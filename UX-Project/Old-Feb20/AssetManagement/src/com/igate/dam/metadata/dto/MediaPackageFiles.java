package com.igate.dam.metadata.dto;

public class MediaPackageFiles {

	private int media_package_files_id;
	private int media_package_id;
	private String media_package_file_name;
	private String media_package_file_path;
	private String file_type;
	private String isarrived;
	public int getMedia_package_files_id() {
		return media_package_files_id;
	}
	public void setMedia_package_files_id(int media_package_files_id) {
		this.media_package_files_id = media_package_files_id;
	}
	public int getMedia_package_id() {
		return media_package_id;
	}
	public void setMedia_package_id(int media_package_id) {
		this.media_package_id = media_package_id;
	}
	
	public String getMedia_package_file_name() {
		return media_package_file_name;
	}
	public void setMedia_package_file_name(String media_package_file_name) {
		this.media_package_file_name = media_package_file_name;
	}
	public String getMedia_package_file_path() {
		return media_package_file_path;
	}
	public void setMedia_package_file_path(String media_package_file_path) {
		this.media_package_file_path = media_package_file_path;
	}
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	public String getIsarrived() {
		return isarrived;
	}
	public void setIsarrived(String isarrived) {
		this.isarrived = isarrived;
	}
	@Override
	public String toString() {
		return "MediaPackageFiles [media_package_files_id="
				+ media_package_files_id + ", media_package_id="
				+ media_package_id + ", media_package_file_name="
				+ media_package_file_name + ", media_package_file_path="
				+ media_package_file_path + ", file_type=" + file_type
				+ ", isarrived=" + isarrived + "]";
	}
	
}
