/**
 * 
 */
package com.igate.dam.workflowseq.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.common.framework.dao.hibernate.GenericHibernateDAO;
import com.igate.dam.metadata.dto.MediaPackage;
import com.igate.dam.workflowseq.dao.WorkflowSeqDAO;
import com.igate.dam.workflowseq.dto.MediaPkgWorkflowSEQ;



/**
 * @author 706440
 * 
 */

@Repository("workflowSeqDAOImpl")

public class WorkflowSeqDAOImpl extends GenericHibernateDAO<MediaPkgWorkflowSEQ> implements WorkflowSeqDAO{
	@Autowired
	SessionFactory sessionFactory;
	
	
	public boolean insertWorkflowSeq(String packageName, String strSequence,int vendorId,int workflowId)throws DAMException {
		
		boolean result=true;
		try{
		System.out.println("Entering insertWorkflowSeq in WorkflowSeqDAOImpl");
		Query query = sessionFactory.getCurrentSession().createQuery("select m.media_package_id from MediaPackage m where m.media_package_name=:packageName");
		query.setString("packageName", packageName);
		int packageId = (Integer) query.uniqueResult();
		if(workflowId>0)
		{
			MediaPkgWorkflowSEQ mediaPkgWorkflowSEQ = new MediaPkgWorkflowSEQ();
			mediaPkgWorkflowSEQ.setMedia_package_id(packageId);
			mediaPkgWorkflowSEQ.setPkg_workflow_sequence(strSequence);
			mediaPkgWorkflowSEQ.setVendor_ID(vendorId);
			mediaPkgWorkflowSEQ.setMedia_pkg_workflow_seq_id(workflowId);
			sessionFactory.getCurrentSession().update(mediaPkgWorkflowSEQ);
		}

		else
		{
		MediaPkgWorkflowSEQ mediaPkgWorkflowSEQ = new MediaPkgWorkflowSEQ();
		mediaPkgWorkflowSEQ.setMedia_package_id(packageId);
		mediaPkgWorkflowSEQ.setPkg_workflow_sequence(strSequence);
		mediaPkgWorkflowSEQ.setVendor_ID(vendorId);
		sessionFactory.getCurrentSession().save(mediaPkgWorkflowSEQ);
		}
		}
		catch (NullPointerException nullPointerException) {
			throw new DAMException("media package doesnot exist");
		}
		catch(Exception exception){
			
			/*System.out.println("Error in insertWorkflowSeq in WorkflowSeqDAOImpl--->"+exception.getMessage());
			return false;*/
			result=false;
			throw new DAMException("Error in insertWorkflowSeq in WorkflowSeqDAOImpl");
          			
			
		}
		System.out.println("Exiting insertWorkflowSeq in WorkflowSeqDAOImpl");
		//return true;
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MediaPackage> loadPackgeNames(int vendorId)throws DAMException {

		List<MediaPackage> listofPackages=new ArrayList<MediaPackage>();
		try{
			
			listofPackages=getSessionFactory().openSession().createQuery("select m from MediaPackage m where vendor_id="+vendorId+"and status_id='START'").list();
			
			System.out.println("lstMetadataAttributeType size"+listofPackages.size());

			for(MediaPackage mediaPackage:listofPackages){
				System.out.println("lenth of "+mediaPackage.getMedia_package_name());
			}
		}
		catch (Exception exception) {
			throw new DAMException("error while fetching package names");
		}


		return listofPackages;
	}
	
	public int checkMediaPackage(int media_package_id)
	{
		 int workflowId=0;
		String hql = "select m.media_pkg_workflow_seq_id FROM MediaPkgWorkflowSEQ m where m.media_package_id=:media_package_id ";
    try
    {
     Query query = sessionFactory.getCurrentSession().createQuery(hql);
     query.setInteger("media_package_id", media_package_id);
     workflowId=( Integer)query.uniqueResult();
     System.out.println("dao---------------->"+workflowId);
     
     if (query.uniqueResult().equals(null)) {
    	 workflowId = 0;
		}

		else {
			workflowId = (Integer) query.uniqueResult();
			System.out.println("-------------packageid ID-------------------"
					+ workflowId);
			
		}
    
	} catch (NullPointerException nullPointerException) {
		
		System.out.println("null pointer exce");
		
	}
	
    return workflowId;
	
}
	
	public int getvendorId(String mediaName)throws DAMException
	{
		System.out.println("entered into dao layer");
		int vendorId=0;
		try
		{
	  MediaPackage mediaPackage=(MediaPackage) getSessionFactory().getCurrentSession().createQuery(
				"select m from MediaPackage m where media_package_name=:mediaName").setParameter("mediaName", mediaName).uniqueResult();
		vendorId=mediaPackage.getVendor_id();
		System.out.println("vendor_id in dao---------"+vendorId);
		}
		catch(Exception exception)
		{
			throw new DAMException("unable to get vendorid");
		}
		return vendorId;
	}
}
