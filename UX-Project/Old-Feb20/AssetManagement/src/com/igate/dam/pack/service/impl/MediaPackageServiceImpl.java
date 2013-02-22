package com.igate.dam.pack.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.common.framework.util.CommonControllerUtil;
import com.igate.dam.metadata.dto.MediaPackage;
import com.igate.dam.pack.dao.MediaPackageDAO;
import com.igate.dam.pack.service.MediaPackageService;

public class MediaPackageServiceImpl implements MediaPackageService{

	private static Logger logger = LoggerFactory.getLogger(MediaPackageServiceImpl.class);
	private MediaPackageDAO mediaPackageDAO;
	
	public MediaPackageDAO getMediaPackageDAO() {
		return mediaPackageDAO;
	}

	public void setMediaPackageDAO(MediaPackageDAO mediaPackageDAO) {
		this.mediaPackageDAO = mediaPackageDAO;
	}

	public List<MediaPackage> lstrecentlyCreatedPackages(int noOfFetch)
	throws DAMException {
		System.out.println("inside service");
		logger.debug("Inside MediaPackageServiceImpl.lstrecentlyCreatedPackages");
		List<MediaPackage> lstpackage = null;
		try {
			Map<String,String> queryCondition = new HashMap<String, String>();
			queryCondition.put("updated_date","desc");
			lstpackage = mediaPackageDAO.getRecentlyModifiedRecords(queryCondition,noOfFetch);
		} catch (Exception ex) {
			ex.printStackTrace();
			//throw new DAMException(ex.getMessage(), ex.getCause());
		}
		return lstpackage;
	}
}
