/**
 * 
 */
package com.igate.dam.common.framework.model;

import java.util.Date;

import org.springframework.security.core.context.SecurityContextHolder;



/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author 501299108
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public abstract class AbstractDomain {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String createdBy;

	/** 
	 * @return the createdBy
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public String getCreatedBy() {
		// begin-user-code
		return "";
		// end-user-code
	}

	/** 
	 * @param createdBy the createdBy to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setCreatedBy(String createdBy) {
		// begin-user-code
		this.createdBy = createdBy;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String lastUpdatedBy;

	/** 
	 * @return the lastUpdatedBy
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public String getLastUpdatedBy() {
		// begin-user-code
		return "";
	}

	/** 
	 * @param lastUpdatedBy the lastUpdatedBy to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setLastUpdatedBy(String lastUpdatedBy) {
		// begin-user-code
		this.lastUpdatedBy = lastUpdatedBy;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Date lastUpdatedDateTime;

	/** 
	 * @return the lastUpdatedDateTime
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Date getLastUpdatedDateTime() {
		// begin-user-code
		if(null!=lastUpdatedDateTime){
			return lastUpdatedDateTime;
		}else{
			return new Date();
		}
		// end-user-code
	}

	/** 
	 * @param lastUpdatedDateTime the lastUpdatedDateTime to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setLastUpdatedDateTime(Date lastUpdatedDateTime) {
		// begin-user-code
		this.lastUpdatedDateTime = lastUpdatedDateTime;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Date createdDateTime;

	/** 
	 * @return the createdDateTime
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Date getCreatedDateTime() {
		// begin-user-code
				if(null!=createdDateTime){
					return createdDateTime;
				}else{
					return new Date();
				}
		// end-user-code
	}

	/** 
	 * @param createdDateTime the createdDateTime to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setCreatedDateTime(Date createdDateTime) {
		// begin-user-code
		this.createdDateTime = createdDateTime;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Boolean deletedFlag;

	/** 
	 * @return the deletedFlag
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Boolean getDeletedFlag() {
		// begin-user-code
			if (null!=deletedFlag){
		return deletedFlag;
			}else {
				return false;
			}
		// end-user-code
	}

	/** 
	 * @param deletedFlag the deletedFlag to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setDeletedFlag(Boolean deletedFlag) {
		// begin-user-code
		this.deletedFlag = deletedFlag;
		// end-user-code
	}
}