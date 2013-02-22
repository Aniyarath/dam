package com.igate.dam.task.web;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jbpm.task.query.TaskSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.common.framework.util.CommonControllerUtil;
import com.igate.dam.process.task.model.HumanTaskSummary;
import com.igate.dam.process.task.service.DAMHumanTaskService;



@Controller
public class TaskOperationController{
	private Long taskId = 0l;
	private String userName;
	private String langCode = "en-UK";
	@Autowired
	private CommonControllerUtil commonControllerUtil;
	@Autowired
	private DAMHumanTaskService damHumanTaskService;
	
	public DAMHumanTaskService getDamHumanTaskService() {
		return damHumanTaskService;
	}

	public void setDamHumanTaskService(DAMHumanTaskService damHumanTaskService) {
		this.damHumanTaskService = damHumanTaskService;
	}

	private static final Logger log = LoggerFactory.getLogger(TaskOperationController.class);
	
		public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	@RequestMapping(value = "/lstTaskAssinedtoGroup")
	public String listTasksAssignedtoGroup(Model model, HttpServletRequest request)throws DAMException{
		
		log.debug("Control coming inside the lstTaskAssinedtoGroup");
		System.out.println("in task assigned group method");
		try {
		List<HumanTaskSummary> lstHumanTaskSummary = damHumanTaskService.lstTaskAssginedtoUser("abu",langCode);
		if(lstHumanTaskSummary!=null){
			System.out.println(lstHumanTaskSummary.size());
			for(HumanTaskSummary taskSum:lstHumanTaskSummary){
				System.out.println(taskSum.getTaskSummary().getDescription());
				
			}
		}
		model.addAttribute("taskList",lstHumanTaskSummary);
	
			model = commonControllerUtil.getModelObject(model);
			//model=commonControllerUtil.getModelObject(model, vendorId);
		} catch (DAMException e) {
			
			e.printStackTrace();
			throw new DAMException(e.getMessage());
		}
		return "taskqueue_layout";
	}
	
	@RequestMapping(value = "/doIngestWorkFlowAgain")
	public String doIngestWorkFlowAgain(Model model, HttpServletRequest request) throws DAMException{
		log.debug("Control coming inside the doIngestWorkFlowAgain");
		if( null != request.getParameter("taskIds") && !request.getParameter("taskIds").equals("") ){
			log.debug("222222222222222222");
			String[] eachtaskIds = request.getParameter("taskIds").split("-");
			Map<String,Object> inputMap = null;
			for(String taskId:eachtaskIds){
				//System.out.println("doIngestWorkFlowAgain"+taskId);
				inputMap = new HashMap<String, Object>();
				inputMap.put("uxflg","1");
				damHumanTaskService.complete(Long.parseLong(taskId), "abu", inputMap);
			}
			
		}
		else{
			log.debug("Invalid Request Params");
		}
		return "taskqueue";
	}
	
	
	@RequestMapping(value = "/proceedToNextStep")
	public String proceedToNextStep(Model model, HttpServletRequest request) throws DAMException{
		log.debug("Control coming inside the proceedToNextStep");
		if( null != request.getParameter("taskIds") && !request.getParameter("taskIds").equals("") ){
			log.debug("222222222222222222");
			String[] eachtaskIds = request.getParameter("taskIds").split("-");
			Map<String,Object> inputMap = null;
			for(String taskId:eachtaskIds){
				//System.out.println("doIngestWorkFlowAgain"+taskId);
				inputMap = new HashMap<String, Object>();
				inputMap.put("uxflg","2");
				damHumanTaskService.complete(Long.parseLong(taskId), "abu", inputMap);
			}
			
		}
		else{
			log.debug("Invalid Request Params");
		}
		return "taskqueue";
	}
	
	@RequestMapping(value = "/terminateDynamicWorkFlow")
	public String terminateDynamicWorkFlow(Model model, HttpServletRequest request) throws DAMException{
		log.debug("Control coming inside the terminateDynamicWorkFlow");
		if( null != request.getParameter("taskIds") && !request.getParameter("taskIds").equals("") ){
			log.debug("222222222222222222");
			String[] eachtaskIds = request.getParameter("taskIds").split("-");
			Map<String,Object> inputMap = null;
			for(String taskId:eachtaskIds){
				//System.out.println("doIngestWorkFlowAgain"+taskId);
				inputMap = new HashMap<String, Object>();
				inputMap.put("uxflg","3");
				damHumanTaskService.complete(Long.parseLong(taskId), "abu", inputMap);
			}
			
		}
		else{
			log.debug("Invalid Request Params");
		}
		return "taskqueue";
	}
	
}
