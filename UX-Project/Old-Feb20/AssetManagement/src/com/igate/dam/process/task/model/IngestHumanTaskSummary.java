package com.igate.dam.process.task.model;

import java.io.Serializable;

import org.jbpm.task.query.TaskSummary;

public class IngestHumanTaskSummary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3621953287280282618L;
	
	private TaskSummary taskSummary;

	public TaskSummary getTaskSummary() {
		return taskSummary;
	}
	
	public void setTaskSummary(TaskSummary taskSummary) {
		this.taskSummary = taskSummary;
	}
}

