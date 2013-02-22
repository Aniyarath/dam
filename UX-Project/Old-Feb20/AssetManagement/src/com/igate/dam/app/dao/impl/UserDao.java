package com.igate.dam.app.dao.impl;

import java.util.List;
import java.util.Map;

import com.igate.dam.app.model.Person;
import com.igate.dam.common.exception.DAMException;
import com.igate.dam.common.framework.dao.GenericDao;
import com.igate.dam.model.UserProfile;



public interface UserDao  {

	public UserProfile findUser(String userName);

	public Person getUserDetails(String username)throws DAMException;

	

	public Map<String, String> getuserType(int userId) throws DAMException;

}
