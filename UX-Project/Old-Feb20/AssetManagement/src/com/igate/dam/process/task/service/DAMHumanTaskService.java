package com.igate.dam.process.task.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.task.query.TaskSummary;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.process.task.model.HumanTaskSummary;


public interface DAMHumanTaskService {
	
		public List<HumanTaskSummary> lstTaskAssginedtoUser(String userName,String language) throws DAMException ;
		public List<HumanTaskSummary> lstTaskAssginedtoGroup(String groupNam,String language) throws DAMException;
		public void claimTask(Long taskId,String userName);
		public void releaseTask(Long taskId);
		public void startTask(Long taskId,String userName) throws DAMException;
		public void stopTask(Long taskId,String userName) throws DAMException;
		public void complete(Long taskId,String userName,Map<String, Object> inputMap) throws DAMException;
		public HashMap<String,Object> getTaskData(Long taskId) throws DAMException;
	

}
