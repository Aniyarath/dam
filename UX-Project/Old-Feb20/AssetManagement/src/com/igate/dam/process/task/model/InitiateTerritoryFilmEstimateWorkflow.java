package com.igate.dam.process.task.model;

import java.io.Serializable;

public class InitiateTerritoryFilmEstimateWorkflow implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String titleName;
private String regionName;
private String description;

public String getTitleName() {
	return titleName;
}
public void setTitleName(String titleName) {
	this.titleName = titleName;
}
public String getRegionName() {
	return regionName;
}
public void setRegionName(String regionName) {
	this.regionName = regionName;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}

}
