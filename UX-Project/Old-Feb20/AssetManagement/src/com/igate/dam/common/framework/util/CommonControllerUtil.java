/*package com.igate.dam.common.framework.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.jbpm.task.query.TaskSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import com.ffe.estimate.model.FilmEstimate;
import com.ffe.estimate.model.RecentlyCreatedEstimate;
import com.ffe.estimate.service.EstimateService;
import com.ffe.process.task.model.EstimateTaskSummary;
import com.igate.dam.common.exception.DAMException;
import com.igate.dam.common.framework.dao.hibernate.GenericHibernateDAO;
import com.igate.dam.metadata.dao.MetadataAttributeTypesDAO;
import com.igate.dam.metadata.dto.MediaPackage;
import com.igate.dam.metadata.service.MetadataService;
import com.igate.dam.metadata.service.impl.MetadataServiceImpl;
import com.igate.dam.pack.service.MediaPackageService;
import com.igate.dam.process.task.service.DAMHumanTaskService;

public class CommonControllerUtil {
	private static Logger logger = LoggerFactory.getLogger(CommonControllerUtil.class);
	private String langCode = "en-UK";
	//private EstimateService estimateService;
	private MediaPackageService mediaPackageService;
	private MetadataService metadataService;
	private DAMHumanTaskService damHumanTaskService;
	
	public DAMHumanTaskService getDamHumanTaskService() {
		return damHumanTaskService;
	}

	public void setDamHumanTaskService(DAMHumanTaskService damHumanTaskService) {
		this.damHumanTaskService = damHumanTaskService;
	}
	public MediaPackageService getMediaPackageService() {
		return mediaPackageService;
	}

	public void setMediaPackageService(MediaPackageService mediaPackageService) {
		this.mediaPackageService = mediaPackageService;
	}

	private MetadataAttributeTypesDAO filmEstimateDAO;
	private MetadataService titleService = new MetadataServiceImpl();
	private static final Logger log = LoggerFactory.getLogger(CommonControllerUtil.class);	
	
public Model getModelObject(Model model) throws DAMException{
	
	logger.debug("Inside CommonControllerUtil.getModelObject");
	
	
	System.out.println("inside commoncontrolutil");
		logger.debug("before calling service");
		String name = "abu";
		try
		{
		List<TaskSummary> lstTaskSummary = damHumanTaskService.lstTaskAssginedtoUser(name,langCode);

		System.out.println("---------->"+lstTaskSummary.size());
		model.addAttribute("homeTasks",lstTaskSummary);
		System.out.println("***********************************");
		for (TaskSummary ts:lstTaskSummary)
		{
			System.out.println(ts.getName());
		}
		
		
		List<MediaPackage> lstTitle = mediaPackageService.lstrecentlyCreatedPackages(5);
			
		model.addAttribute("packageSideList",lstTitle);
		}
		catch(DAMException dException)
		{
			throw new DAMException(dException.getMessage());
		}
		
		
	return model;
}

public Model getModelObject(Model model,int vendorId) throws DAMException
{
	List<MediaPackage> metadatasearchPack = new ArrayList<MediaPackage>();
	
	metadatasearchPack=metadataService.getPackgeNames(vendorId);
	model.addAttribute("packagesList",metadatasearchPack);
	return model;
}




public MetadataAttributeTypesDAO getFilmEstimateDAO() {
	return filmEstimateDAO;
}

public void setFilmEstimateDAO(MetadataAttributeTypesDAO filmEstimateDAO) {
	this.filmEstimateDAO = filmEstimateDAO;
}

public MetadataService getTitleService() {
	return titleService;
}

public void setTitleService(MetadataService titleService) {
	this.titleService = titleService;
}

public EstimateService getEstimateService() {
	return estimateService;
}

public void setEstimateService(EstimateService estimateService) {
	this.estimateService = estimateService;
}



}*/


/**************************************************************************/
package com.igate.dam.common.framework.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.jbpm.task.query.TaskSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

/*import com.ffe.estimate.model.FilmEstimate;
import com.ffe.estimate.model.RecentlyCreatedEstimate;
import com.ffe.estimate.service.EstimateService;
import com.ffe.process.task.model.EstimateTaskSummary;*/
import com.igate.dam.common.exception.DAMException;
import com.igate.dam.common.framework.dao.hibernate.GenericHibernateDAO;
import com.igate.dam.metadata.dao.MetadataAttributeTypesDAO;
import com.igate.dam.metadata.dto.MediaPackage;
import com.igate.dam.metadata.service.MetadataService;
import com.igate.dam.metadata.service.impl.MetadataServiceImpl;
import com.igate.dam.pack.service.MediaPackageService;
import com.igate.dam.process.task.model.HumanTaskSummary;
import com.igate.dam.process.task.service.DAMHumanTaskService;

public class CommonControllerUtil {
                private static Logger logger = LoggerFactory.getLogger(CommonControllerUtil.class);
                private String langCode = "en-UK";
                //private EstimateService estimateService;
                private MediaPackageService mediaPackageService;
                private MetadataService metadataService;
                private DAMHumanTaskService damHumanTaskService;
                
                public DAMHumanTaskService getDamHumanTaskService() {
                                return damHumanTaskService;
                }

                public void setDamHumanTaskService(DAMHumanTaskService damHumanTaskService) {
                                this.damHumanTaskService = damHumanTaskService;
                }
                public MediaPackageService getMediaPackageService() {
                                return mediaPackageService;
                }

                public void setMediaPackageService(MediaPackageService mediaPackageService) {
                                this.mediaPackageService = mediaPackageService;
                }

                private MetadataAttributeTypesDAO filmEstimateDAO;
                private MetadataService titleService = new MetadataServiceImpl();
                private static final Logger log = LoggerFactory.getLogger(CommonControllerUtil.class);  
                
                public Model getModelObject(Model model) throws DAMException {

            		logger.debug("Inside CommonControllerUtil.getModelObject");

            		System.out.println("inside commoncontrolutil");
            		logger.debug("before calling service");
            		
            		String name = "abu";
            		
            		  List<HumanTaskSummary> lstHumanTaskSummary =damHumanTaskService.lstTaskAssginedtoUser(name,langCode);
            		  
            		  model.addAttribute("homeTasks",lstHumanTaskSummary);
            		  System.out.println("***********************************");
            		  
            		  for(HumanTaskSummary ts:lstHumanTaskSummary) {
            		  System.out.println(ts.getTaskSummary().getName()); }
            		

            		List<MediaPackage> lstTitle = mediaPackageService .lstrecentlyCreatedPackages(5);

            		model.addAttribute("packageSideList", lstTitle);

            		return model;
            	}

/*public Model getModelObject(Model model,int vendorId) throws DAMException
{
                List<MediaPackage> metadatasearchPack = new ArrayList<MediaPackage>();
                
                metadatasearchPack=metadataService.getPackgeNames(vendorId);
                model.addAttribute("packagesList",metadatasearchPack);
                return model;
}
*/



public MetadataAttributeTypesDAO getFilmEstimateDAO() {
                return filmEstimateDAO;
}

public void setFilmEstimateDAO(MetadataAttributeTypesDAO filmEstimateDAO) {
                this.filmEstimateDAO = filmEstimateDAO;
}

public MetadataService getTitleService() {
                return titleService;
}

public void setTitleService(MetadataService titleService) {
                this.titleService = titleService;
}

/*public EstimateService getEstimateService() {
                return estimateService;
}

public void setEstimateService(EstimateService estimateService) {
                this.estimateService = estimateService;
}

*/

}
