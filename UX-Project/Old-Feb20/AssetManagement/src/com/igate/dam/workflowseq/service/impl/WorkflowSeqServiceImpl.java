/**
 * 
 */
package com.igate.dam.workflowseq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.metadata.dto.MediaPackage;
import com.igate.dam.workflowseq.dao.WorkflowSeqDAO;
import com.igate.dam.workflowseq.dto.MediaPkgWorkflowSEQ;
import com.igate.dam.workflowseq.service.WorkflowSeqService;

/**
 * @author 706440
 * 
 */
@Service("workflowSeqServiceImpl")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class WorkflowSeqServiceImpl implements WorkflowSeqService{
	
	
	@Autowired
	WorkflowSeqDAO workflowSeqDAO;

	
	/**
	 * @return the workflowSeqDAO
	 */
	public WorkflowSeqDAO getWorkflowSeqDAO() {
		return workflowSeqDAO;
	}


	/**
	 * @param workflowSeqDAO the workflowSeqDAO to set
	 */
	public void setWorkflowSeqDAO(WorkflowSeqDAO workflowSeqDAO) {
		this.workflowSeqDAO = workflowSeqDAO;
	}


	@Override
	public boolean insertWorkflowSeq(String packageName, String strSequence,int vendorId,int workflowId)throws DAMException {
		System.out.println("Entering insertWorkflowSeq in WorkflowSeqServiceImpl");
		boolean success = false;
		success=workflowSeqDAO.insertWorkflowSeq(packageName,strSequence,vendorId,workflowId);
		
		System.out.println("Exiting insertWorkflowSeq in WorkflowSeqServiceImpl");
		return success;
	}

	
	public List<MediaPackage> loadPackgeNames(int vendorId)throws DAMException {
		
		return workflowSeqDAO.loadPackgeNames(vendorId);
	}


	@Override
	public int checkMediaPackage(int media_package_id)throws DAMException {
		int count=workflowSeqDAO.checkMediaPackage(media_package_id);
		return count;
	}


	@Override
	public int getvendorId(String mediaName) throws DAMException {
		int vendorId=workflowSeqDAO.getvendorId(mediaName);
		return vendorId;
	}
}
