package com.igate.dam.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.igate.dam.app.model.Person;
import com.igate.dam.common.exception.DAMException;



public interface UserService extends UserDetailsService {

	Person getUserDetails(String username)throws DAMException;

	Map<String, String> getuserType(int userId) throws DAMException ;

	

}
