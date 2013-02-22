package com.igate.dam.process.task.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.task.AccessType;
import org.jbpm.task.Content;
import org.jbpm.task.Task;
import org.jbpm.task.TaskData;
import org.jbpm.task.query.TaskSummary;
import org.jbpm.task.service.ContentData;
import org.jbpm.task.service.TaskClient;
import org.jbpm.task.service.responsehandlers.BlockingGetContentResponseHandler;
import org.jbpm.task.service.responsehandlers.BlockingGetTaskResponseHandler;
import org.jbpm.task.service.responsehandlers.BlockingTaskOperationResponseHandler;
import org.jbpm.task.service.responsehandlers.BlockingTaskSummaryResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.process.task.model.EstimateTaskSummary;
import com.igate.dam.process.task.model.HumanTaskSummary;
import com.igate.dam.process.task.model.InitiateTerritoryFilmEstimateWorkflow;
import com.igate.dam.process.task.service.DAMHumanTaskService;

public class DAMHumanTaskServiceImpl implements DAMHumanTaskService{

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	private BlockingTaskSummaryResponseHandler taskSummaryResponseHandler;
	private BlockingTaskOperationResponseHandler taskOperationHandler;
	private BlockingGetTaskResponseHandler taskResponseHandler;
	private BlockingGetContentResponseHandler taskContentResponseHandler;
	
	public DAMHumanTaskServiceImpl()
	{
		super();
	}
	public BlockingGetTaskResponseHandler getTaskResponseHandler() {
		return taskResponseHandler;
	}
	public BlockingGetContentResponseHandler getTaskContentResponseHandler() {
		return taskContentResponseHandler;
	}
	public void setTaskContentResponseHandler(
			BlockingGetContentResponseHandler taskContentResponseHandler) {
		this.taskContentResponseHandler = taskContentResponseHandler;
	}
	public void setTaskResponseHandler(
			BlockingGetTaskResponseHandler taskResponseHandler) {
		this.taskResponseHandler = taskResponseHandler;
	}
	private TaskClient taskClient;
	
	public BlockingTaskSummaryResponseHandler getTaskSummaryResponseHandler() {
		return taskSummaryResponseHandler;
	}
	public void setTaskSummaryResponseHandler(
			BlockingTaskSummaryResponseHandler taskSummaryResponseHandler) {
		this.taskSummaryResponseHandler = taskSummaryResponseHandler;
	}
	public BlockingTaskOperationResponseHandler getTaskOperationHandler() {
		return taskOperationHandler;
	}
	public void setTaskOperationHandler(
			BlockingTaskOperationResponseHandler taskOperationHandler) {
		this.taskOperationHandler = taskOperationHandler;
	}
	public TaskClient getTaskClient() {
		return taskClient;
	}
	public void setTaskClient(TaskClient taskClient) {
		this.taskClient = taskClient;
	}
	
	public void claimTask(Long taskId, String userName) {
		// TODO Auto-generated method stub
		
	}
	public void complete(Long taskId, String userName,
			Map<String, Object> inputMap) throws DAMException {
		logger.debug("Entering into DAMHumanTaskServiceImpl.doIngestAgain");
		taskOperationHandler = new BlockingTaskOperationResponseHandler();
		try{
			logger.debug("Completing the Task---------->\t"+taskId);
			ContentData contentData = null;
			if(inputMap != null)
			{
				ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
				ObjectOutputStream objectOutput = null;
				objectOutput = new ObjectOutputStream(byteOutput);
				objectOutput.writeObject(inputMap);
				objectOutput.close();
				contentData = new ContentData();
				contentData.setContent(byteOutput.toByteArray());
				contentData.setAccessType(AccessType.Inline);
			}
			startTask(taskId, userName);
			taskClient.complete(taskId,userName,contentData,taskOperationHandler);
			taskOperationHandler.waitTillDone(10000l);
			
			logger.debug("Task Completed------->\t"+taskId);
			
			
		}
		catch(IOException io){
			logger.error(
					" IO Exception occured in DAMHumanTaskServiceImpl.completeTask :",
					io.getMessage());
			throw new DAMException(" IO Exception occured in DAMHumanTaskServiceImpl.completeTask :",
					io.getMessage());
			
		}
		catch(Exception ex)
		{
			logger.error(
					" Exception occured in DAMHumanTaskServiceImpl.completeTask :",
					ex.getMessage());
			throw new DAMException(" IO Exception occured in DAMHumanTaskServiceImpl.completeTask :",
					ex.getMessage());
			
		}
		logger.debug("Exiting into DAMHumanTaskServiceImpl.completeTask");
		
	}
	
	public HashMap<String, Object> getTaskData(Long taskId) throws DAMException {
		// TODO Auto-generated method stub
		logger.debug("Entering into DAMHumanTaskServiceImpl.getTask");
		Task task = null;
		HashMap<String,Object> contentMap = null;
		taskResponseHandler = new BlockingGetTaskResponseHandler();
		taskContentResponseHandler = new BlockingGetContentResponseHandler();
		try{
			logger.debug("Entering into DAMHumanTaskServiceImpl.getTask");
			taskClient.getTask(taskId, taskResponseHandler);
			taskResponseHandler.waitTillDone(10000l);
			task = taskResponseHandler.getTask();
			TaskData taskData = task.getTaskData();
			taskClient.getContent(taskData.getDocumentContentId(), taskContentResponseHandler);
			taskContentResponseHandler.waitTillDone(10000l);
			Content content = taskContentResponseHandler.getContent();
			ByteArrayInputStream byteArrayInput = new ByteArrayInputStream(content.getContent());
			ObjectInputStream objectInput = new ObjectInputStream(byteArrayInput);
			Object object = null;
			object = objectInput.readObject();
			contentMap = (HashMap<String,Object>)object;
		}
		catch(IOException io){
			logger.error(
					"IO Exception occured in DAMHumanTaskServiceImpl.getTask :",
					io.getMessage());
			throw new DAMException("IO Exception occured in DAMHumanTaskServiceImpl.getTask :",io.getMessage());
			
			
		}
		catch(Exception ex){
			logger.error(
					" Exception occured in DAMHumanTaskServiceImpl.getTask :",
					ex.getMessage());
			throw new DAMException(" Exception occured in DAMHumanTaskServiceImpl.getTask :",
					ex.getMessage());
		}
		logger.debug("Exiting into DAMHumanTaskServiceImpl.getTask");
		return contentMap;
	}

	public List<HumanTaskSummary> lstTaskAssginedtoGroup(String groupName,
			String language) throws DAMException {
		
		logger.debug("Entering into DAMHumanTaskServiceImpl.lstTaskAssginedtoUser");
		List<TaskSummary> lstTaskSummary = null;
		List<HumanTaskSummary> lstHumanTaskSummary = null;
		HumanTaskSummary humanTaskSummary = null;
		taskSummaryResponseHandler = new BlockingTaskSummaryResponseHandler();
		long taskId =0;
		String packageName = null;
		String vendorName = null;
		try{
			taskClient.getTasksAssignedAsPotentialOwner(groupName,language, taskSummaryResponseHandler);
			taskSummaryResponseHandler.waitTillDone(100000l);
			lstTaskSummary = taskSummaryResponseHandler.getResults();
			if (null != lstTaskSummary){
                Collections.reverse(lstTaskSummary);
                lstHumanTaskSummary = new ArrayList<HumanTaskSummary>();
                for(TaskSummary taskSummary:lstTaskSummary){
                      taskId = taskSummary.getId();
                      HashMap<String,Object> contentMap = getTaskDetails(taskId);
                      if (null != contentMap){
                    	  	humanTaskSummary = new HumanTaskSummary();
                    	  	humanTaskSummary.setTaskSummary(taskSummary);
                            packageName = (String)contentMap.get("packageName");
                            System.out.println("packageName--->"+packageName);
                            humanTaskSummary.setPackageName(packageName);
                            vendorName = (String)contentMap.get("vendorName");
                            System.out.println("vendorName-->"+vendorName);
                            humanTaskSummary.setVendorName(vendorName);
                      }
                      lstHumanTaskSummary.add(humanTaskSummary);
                }
          }
		}
		catch(Exception ex)
		{
			logger.error(
					" Exception occured in DAMHumanTaskServiceImpl.lstTaskAssginedtoUser :",
					ex.getMessage());
			throw new DAMException(" Exception occured in DAMHumanTaskServiceImpl.lstTaskAssginedtoUser :",
					ex.getMessage());
			
		}
		logger.debug("Exiting into DAMHumanTaskServiceImpl.lstTaskAssginedtoUser");
		return lstHumanTaskSummary;
	}

	public List<HumanTaskSummary> lstTaskAssginedtoUser(String userName,
			String language) throws DAMException {
		
		logger.debug("Entering into DAMHumanTaskServiceImpl.lstTaskAssginedtoUser");
		List<TaskSummary> lstTaskSummary = null;
		List<HumanTaskSummary> lstHumanTaskSummary = null;
		HumanTaskSummary humanTaskSummary = null;
		taskSummaryResponseHandler = new BlockingTaskSummaryResponseHandler();
		long taskId =0;
		String packageName = null;
		String vendorName = null;
		
		try{
			taskClient.getTasksAssignedAsPotentialOwner(userName,language, taskSummaryResponseHandler);
			taskSummaryResponseHandler.waitTillDone(10000l);
			lstTaskSummary = taskSummaryResponseHandler.getResults();
			System.out.println("list task summary------------>"+lstTaskSummary.size());
			if (null != lstTaskSummary){
                Collections.reverse(lstTaskSummary);
                lstHumanTaskSummary = new ArrayList<HumanTaskSummary>();
                for(TaskSummary taskSummary:lstTaskSummary){
                      taskId = taskSummary.getId();
                      HashMap<String,Object> contentMap = getTaskDetails(taskId);
                      if (null != contentMap){
                    	  	humanTaskSummary = new HumanTaskSummary();
                    	  	humanTaskSummary.setTaskSummary(taskSummary);
                            packageName = (String)contentMap.get("packageName");
                            System.out.println("packageName--->"+packageName);
                            humanTaskSummary.setPackageName(packageName);
                            vendorName = (String)contentMap.get("vendorName");
                            System.out.println("vendorName-->"+vendorName);
                            humanTaskSummary.setVendorName(vendorName);
                      }
                      lstHumanTaskSummary.add(humanTaskSummary);
                }
          }
		}
		catch(Exception ex)
		{
			logger.error(
					" Exception occured in DAMHumanTaskServiceImpl.lstTaskAssginedtoUser :",
					ex.getMessage());
			throw new DAMException(" Exception occured in DAMHumanTaskServiceImpl.lstTaskAssginedtoUser :"+ex.getMessage());
			
		}
		logger.debug("Exiting into DAMHumanTaskServiceImpl.lstTaskAssginedtoUser");
		System.out.println("list human task summary size------------->"+lstHumanTaskSummary.size());
		return lstHumanTaskSummary;
	}
	
    public HashMap<String,Object> getTaskDetails(Long taskId)throws Exception {
      
        logger.debug("Entering into DAMHumanTaskServiceImpl.getTask");
        Task task = null;
        HashMap<String,Object> contentMap;
        taskResponseHandler = new BlockingGetTaskResponseHandler();
        taskContentResponseHandler = new BlockingGetContentResponseHandler();
        try{
              logger.debug("Entering into DAMHumanTaskServiceImpl.getTask");
              taskClient.getTask(taskId, taskResponseHandler);
              taskResponseHandler.waitTillDone(10000l);
              task = taskResponseHandler.getTask();
              TaskData taskData = task.getTaskData();
              taskClient.getContent(taskData.getDocumentContentId(), taskContentResponseHandler);
              taskContentResponseHandler.waitTillDone(10000l);
              Content content = taskContentResponseHandler.getContent();
              ByteArrayInputStream byteArrayInput = new ByteArrayInputStream(content.getContent());
              ObjectInputStream objectInput = new ObjectInputStream(byteArrayInput);
              Object object = null;
              object = objectInput.readObject();
              contentMap = (HashMap<String,Object>)object;
        }
        catch(IOException io){
              logger.error(
                          "IO Exception occured in DAMHumanTaskServiceImpl.getTask :",
                          io.getMessage());
            
              throw new DAMException("IO Exception occured in DAMHumanTaskServiceImpl.getTask :",
                      io.getMessage());
        }
        catch(Exception ex){
              logger.error(
                          " Exception occured in DAMHumanTaskServiceImpl.getTask :",
                          ex.getMessage());
             throw new DAMException( " Exception occured in DAMHumanTaskServiceImpl.getTask :",
                     ex.getMessage());
        }
        logger.debug("Exiting into DAMHumanTaskServiceImpl.getTask");
        return contentMap;
        
  }

	
	/*@Override
    public List<EstimateTaskSummary> lstEstimateTaskAssginedtoTerritory(
                String territoryName, String language) throws GTSException {
          // TODO Auto-generated method stub
          logger.debug("Entering into EstimateServiceImpl.lstEstimateTaskAssginedtoTerritory");
          List<TaskSummary> lstTaskSummary = null;
          List<EstimateTaskSummary> lstEstimateTaskSummary = null;
          EstimateTaskSummary estimateTaskSummary = null;
          try{
                lstTaskSummary = ffeHumanTaskService.lstTaskAssginedtoUser(territoryName,language);
                if (null != lstTaskSummary){
                      Collections.reverse(lstTaskSummary);
                      lstEstimateTaskSummary = new ArrayList<EstimateTaskSummary>();
                      for(TaskSummary taskSummary:lstTaskSummary){
                            estimateTaskSummary = new EstimateTaskSummary();
                            estimateTaskSummary.setTaskSummary(taskSummary);
                            HashMap<String,Object> contentMap = ffeHumanTaskService.getTaskData(taskSummary.getId());
                            if (null != contentMap){
                                  InitiateTerritoryFilmEstimateWorkflow initiateTerritoryFilmEstimateWorkflow = (InitiateTerritoryFilmEstimateWorkflow)contentMap.get("initiateterritoryworkflow");
                                  if (null != initiateTerritoryFilmEstimateWorkflow){
                                        estimateTaskSummary.setInitiateTerritoryFilmEstimateWorkflow(initiateTerritoryFilmEstimateWorkflow);
                                        if (taskSummary.getActualOwner() != null && taskSummary.getActualOwner().getId() != null){
                                              estimateTaskSummary.setTaskStatus("Claimed");
                                        }
                                        else
                                        {
                                              estimateTaskSummary.setTaskStatus(taskSummary.getStatus().toString());
                                        }
                                  }
                            }
                            else{
                                  estimateTaskSummary.setInitiateTerritoryFilmEstimateWorkflow(null);
                            }
                            lstEstimateTaskSummary.add(estimateTaskSummary);
                      }
                }
          }
          catch(Exception ex)
          {
                logger.error(
                            " Exception occured in EstimateServiceImpl.lstEstimateTaskAssginedtoTerritory :",
                            ex.getMessage());
                throw new GTSException(ex.getMessage(), ex.getCause());
          }
          logger.debug("Exiting into EstimateServiceImpl.lstEstimateTaskAssginedtoTerritory");
          return lstEstimateTaskSummary;
    }*/

	
	
	
	
	
	
	

	public void releaseTask(Long taskId) {
		// TODO Auto-generated method stub
		
	}

	public void startTask(Long taskId, String userName) throws DAMException {
		logger.debug("Entering into DAMHumanTaskServiceImpl.startTask");
		taskOperationHandler = new BlockingTaskOperationResponseHandler();
		try{
			logger.debug("Starting the Task---------->\t"+taskId);
			taskClient.start(taskId,userName,taskOperationHandler);
			taskOperationHandler.waitTillDone(10000l);
			logger.debug("Task started------->\t"+taskId);
			
		}
		catch(Exception ex)
		{
			logger.error(
					" Exception occured in DAMHumanTaskServiceImpl.startTask :",
					ex.getMessage());
			throw new DAMException(" Exception occured in DAMHumanTaskServiceImpl.startTask :",
					ex.getMessage());
		}
		logger.debug("Exiting into DAMHumanTaskServiceImpl.startTask");
		
	}

	public void stopTask(Long taskId, String userName) throws DAMException {
		
		logger.debug("Entering into DAMHumanTaskServiceImpl.stopTask");
		taskOperationHandler = new BlockingTaskOperationResponseHandler();
		try{
			logger.debug("Stopping the Task---------->\t"+taskId);
			taskClient.stop(taskId,userName,taskOperationHandler);
			taskOperationHandler.waitTillDone(10000l);
			logger.debug("Task Stopped------->\t"+taskId);
			
		}
		catch(Exception ex)
		{
			logger.error(
					" Exception occured in DAMHumanTaskServiceImpl.stopTask :",
					ex.getMessage());
			throw new DAMException(" Exception occured in DAMHumanTaskServiceImpl.stopTask :",
					ex.getMessage());
		}
		logger.debug("Exiting into DAMHumanTaskServiceImpl.stopTask");
	}

}
