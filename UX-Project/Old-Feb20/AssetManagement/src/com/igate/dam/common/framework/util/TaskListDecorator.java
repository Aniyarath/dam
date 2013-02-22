package com.igate.dam.common.framework.util;

import org.displaytag.decorator.TableDecorator;
import org.jbpm.task.query.TaskSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.ffe.process.task.model.EstimateTaskSummary;

public class TaskListDecorator extends TableDecorator {
	private static final Logger log = LoggerFactory.getLogger(TaskListDecorator.class);	
	public TaskSummary getTaskSummary() {
		log.debug("Coming inside TaskListDecorator.getTaskSummary()");
		/*EstimateTaskSummary estimateTaskSum = (EstimateTaskSummary)getCurrentRowObject();
		TaskSummary taskSum = estimateTaskSum.getTaskSummary();
		if (estimateTaskSum.getTaskStatus().equalsIgnoreCase("Claimed")){
			String taskName = taskSum.getName();
			log.debug("Coming inside the Decorator---------------------->Condition Satisfied");
			taskSum.setName("<font color=\"red\">"+taskName+"</font>");
		}
		return taskSum;*/
		return null;
	}
}
