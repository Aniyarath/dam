package com.igate.dam.pack.service;

import java.util.List;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.metadata.dto.MediaPackage;

public interface MediaPackageService {

   	public List<MediaPackage> lstrecentlyCreatedPackages(int noOfFetch) throws DAMException;

}
