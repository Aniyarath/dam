/**
 * 
 */
package com.igate.dam.workflowseq.service;

import java.util.List;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.metadata.dto.MediaPackage;
import com.igate.dam.workflowseq.dto.MediaPkgWorkflowSEQ;

/**
 * @author 706440
 *
 */
public interface WorkflowSeqService {

	public boolean insertWorkflowSeq(String packageName, String strSequence,int vendorId,int workflowId)throws DAMException;
	public List<MediaPackage> loadPackgeNames(int vendorId)throws DAMException;
	public int checkMediaPackage(int media_package_id)throws DAMException;
	public int getvendorId(String mediaName)throws DAMException;
}
