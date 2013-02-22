package com.igate.dam.process.task.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.igate.dam.common.framework.model.AbstractDomain;


public class FilmEstimateWorkFlow extends AbstractDomain implements Serializable{

	private static final long serialVersionUID = 1L;

	
private Long filmEstimateId;
private String filmEstimateStatus;
private Long territoryId;
private String territoryName;
private Long titleId;
private String titleName;
private Long version;
public Long getVersion() {
	return version;
}
public void setVersion(Long string) {
	this.version = string;
}
private Long totalCost;
private Long requesterId;
private String requesterName;
private Long approverId;
private Long approverName;
private BigDecimal printEstimateCost;
private BigDecimal adpubEstimateCost;
private BigDecimal estimateGBOCost;
private BigDecimal otherDDECost;

public BigDecimal getPrintEstimateCost() {
	return printEstimateCost;
}
public void setPrintEstimateCost(BigDecimal printEstimateCost) {
	this.printEstimateCost = printEstimateCost;
}
public BigDecimal getAdpubEstimateCost() {
	return adpubEstimateCost;
}
public void setAdpubEstimateCost(BigDecimal adpubEstimateCost) {
	this.adpubEstimateCost = adpubEstimateCost;
}
public BigDecimal getEstimateGBOCost() {
	return estimateGBOCost;
}
public void setEstimateGBOCost(BigDecimal estimateGBOCost) {
	this.estimateGBOCost = estimateGBOCost;
}
public BigDecimal getOtherDDECost() {
	return otherDDECost;
}
public void setOtherDDECost(BigDecimal otherDDECost) {
	this.otherDDECost = otherDDECost;
}
public static long getSerialVersionUID() {
	return serialVersionUID;
}
public Long getRequesterId() {
	return requesterId;
}
public void setRequesterId(Long requesterId) {
	this.requesterId = requesterId;
}
public String getRequesterName() {
	return requesterName;
}
public void setRequesterName(String requesterName) {
	this.requesterName = requesterName;
}
public Long getApproverId() {
	return approverId;
}
public void setApproverId(Long approverId) {
	this.approverId = approverId;
}
public Long getApproverName() {
	return approverName;
}
public void setApproverName(Long approverName) {
	this.approverName = approverName;
}

public Long getTerritoryId() {
	return territoryId;
}
public void setTerritoryId(Long territoryId) {
	this.territoryId = territoryId;
}
public Long getTitleId() {
	return titleId;
}
public void setTitleId(Long titleId) {
	this.titleId = titleId;
}


public Long getFilmEstimateId() {
	return filmEstimateId;
}
public void setFilmEstimateId(Long filmEstimateId) {
	this.filmEstimateId = filmEstimateId;
}
public String getFilmEstimateStatus() {
	return filmEstimateStatus;
}
public void setFilmEstimateStatus(String filmEstimateStatus) {
	this.filmEstimateStatus = filmEstimateStatus;
}
public String getTerritoryName() {
	return territoryName;
}
public void setTerritoryName(String territoryName) {
	this.territoryName = territoryName;
}
public String getTitleName() {
	return titleName;
}
public void setTitleName(String titleName) {
	this.titleName = titleName;
}
public Long getTotalCost() {
	return totalCost;
}
public void setTotalCost(Long totalCost) {
	this.totalCost = totalCost;
}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return titleName + filmEstimateId + version + territoryName;
}
}
