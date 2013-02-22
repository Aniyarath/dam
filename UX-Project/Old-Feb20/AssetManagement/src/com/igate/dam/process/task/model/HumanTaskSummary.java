package com.igate.dam.process.task.model;

import java.io.Serializable;

import org.jbpm.task.query.TaskSummary;

public class HumanTaskSummary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3621953287280282618L;
	
	private TaskSummary taskSummary;
	private String packageName = null;
	private String vendorName = null;

	public TaskSummary getTaskSummary() {
		return taskSummary;
	}
	
	public void setTaskSummary(TaskSummary taskSummary) {
		this.taskSummary = taskSummary;
	}

	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * @return the vendorName
	 */
	public String getVendorName() {
		return vendorName;
	}

	/**
	 * @param vendorName the vendorName to set
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
}

