package com.igate.dam.process.task.model;

import java.io.Serializable;

import org.jbpm.task.query.TaskSummary;


public class EstimateTaskSummary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1378456440493689784L;
	
	private FilmEstimateWorkFlow filmEstimateWorkflow;
	private TaskSummary taskSummary = new TaskSummary();
	private String taskStatus;
	private InitiateTerritoryFilmEstimateWorkflow initiateTerritoryFilmEstimateWorkflow;
	
	public FilmEstimateWorkFlow getFilmEstimateWorkflow() {
		return filmEstimateWorkflow;
	}
	public void setFilmEstimateWorkflow(FilmEstimateWorkFlow filmEstimateWorkflow) {
		this.filmEstimateWorkflow = filmEstimateWorkflow;
	}
	
	public InitiateTerritoryFilmEstimateWorkflow getInitiateTerritoryFilmEstimateWorkflow() {
		return initiateTerritoryFilmEstimateWorkflow;
	}
	public void setInitiateTerritoryFilmEstimateWorkflow(
			InitiateTerritoryFilmEstimateWorkflow initiateTerritoryFilmEstimateWorkflow) {
		this.initiateTerritoryFilmEstimateWorkflow = initiateTerritoryFilmEstimateWorkflow;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public TaskSummary getTaskSummary() {
		return taskSummary;
	}
	public void setTaskSummary(TaskSummary taskSummary) {
		this.taskSummary = taskSummary;
	}
	
	

	

	
}
