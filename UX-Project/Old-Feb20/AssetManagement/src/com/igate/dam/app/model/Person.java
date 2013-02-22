package com.igate.dam.app.model;

import java.io.Serializable;
import java.util.Date;

import com.igate.dam.common.framework.model.AbstractDomain;

public class Person extends AbstractDomain implements Serializable{
	private int PERSON_ID;
	private String FIRST_NAME;
	private String LAST_NAME;
	private String EMAIL;
	private Date LAST_UPDT_DT_TM;
	private Date CRT_DT_TM;
	private String DELETED_FLAG;
	private String PASSWORD;
	private int vendor_id;
	
	@Override
	public String toString() {
		return "Person [PERSON_ID=" + PERSON_ID + ", FIRST_NAME=" + FIRST_NAME
				+ ", LAST_NAME=" + LAST_NAME + ", EMAIL=" + EMAIL
				+ ", LAST_UPDT_DT_TM=" + LAST_UPDT_DT_TM + ", CRT_DT_TM="
				+ CRT_DT_TM + ", DELETED_FLAG=" + DELETED_FLAG + ", PASSWORD="
				+ PASSWORD + ", vendor_id=" + vendor_id + "]";
	}
	public int getVendor_id() {
		return vendor_id;
	}
	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public int getPERSON_ID() {
		return PERSON_ID;
	}
	public void setPERSON_ID(int pERSON_ID) {
		PERSON_ID = pERSON_ID;
	}
	public String getFIRST_NAME() {
		return FIRST_NAME;
	}
	public void setFIRST_NAME(String fIRST_NAME) {
		FIRST_NAME = fIRST_NAME;
	}
	public String getLAST_NAME() {
		return LAST_NAME;
	}
	public void setLAST_NAME(String lAST_NAME) {
		LAST_NAME = lAST_NAME;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public Date getLAST_UPDT_DT_TM() {
		return LAST_UPDT_DT_TM;
	}
	public void setLAST_UPDT_DT_TM(Date lAST_UPDT_DT_TM) {
		LAST_UPDT_DT_TM = lAST_UPDT_DT_TM;
	}
	public Date getCRT_DT_TM() {
		return CRT_DT_TM;
	}
	public void setCRT_DT_TM(Date cRT_DT_TM) {
		CRT_DT_TM = cRT_DT_TM;
	}
	public String getDELETED_FLAG() {
		return DELETED_FLAG;
	}
	public void setDELETED_FLAG(String dELETED_FLAG) {
		DELETED_FLAG = dELETED_FLAG;
	}
	
	

}
