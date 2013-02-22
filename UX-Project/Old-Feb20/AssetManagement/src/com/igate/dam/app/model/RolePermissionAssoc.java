package com.igate.dam.app.model;

import java.util.Date;

public class RolePermissionAssoc {
	private int APP_ROLE_PERMISSION_ASSOC_ID;
	private int APP_ROLE_ID;
	private int APP_PERMISSION_ID;
	private Date LAST_UPDT_DT_TM;
	private Date CRT_DT_TM;
	private String DELETED_FLAG;
	public int getAPP_ROLE_PERMISSION_ASSOC_ID() {
		return APP_ROLE_PERMISSION_ASSOC_ID;
	}
	public void setAPP_ROLE_PERMISSION_ASSOC_ID(int aPP_ROLE_PERMISSION_ASSOC_ID) {
		APP_ROLE_PERMISSION_ASSOC_ID = aPP_ROLE_PERMISSION_ASSOC_ID;
	}
	public int getAPP_ROLE_ID() {
		return APP_ROLE_ID;
	}
	public void setAPP_ROLE_ID(int aPP_ROLE_ID) {
		APP_ROLE_ID = aPP_ROLE_ID;
	}
	public int getAPP_PERMISSION_ID() {
		return APP_PERMISSION_ID;
	}
	public void setAPP_PERMISSION_ID(int aPP_PERMISSION_ID) {
		APP_PERMISSION_ID = aPP_PERMISSION_ID;
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
