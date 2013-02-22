package com.igate.dam.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.igate.dam.app.dao.impl.UserDao;
import com.igate.dam.app.model.Person;
import com.igate.dam.common.exception.DAMException;



public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	
	
	


	public UserDao getUserDao() {
		return userDao;
	}


	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Person getUserDetails(String username) throws DAMException {
		return userDao.getUserDetails(username);
	
	}


	@Override
	public Map<String, String> getuserType(int userId)throws DAMException {
		
		return userDao.getuserType(userId);
	}


	
	

	/*@Override
	public UserDetails saveUser(UserProfile userProfile)
			throws GTSException {
		System.out.println("entering with UserServiceImpl.LoadUserByUserName-->"+ userProfile);
		UserDetails userDetails= null;
		try {
			userDao.update(userProfile,"");
			
		} catch (Exception gtsException) {
			gtsException.printStackTrace();
		
		}
		System.out.println("exiting  with UserServiceImpl.LoadUserByUserName");
		return userDetails;
	}


	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		System.out.println("entering with UserServiceImpl.LoadUserByUserName-->"+ userName);
		UserDetails userDetails= null;
		try {
			userDetails=userDao.findUser(userName);
			System.out.println("userObject ?"+ userDetails);
			if (null==userDetails){
				throw new UsernameNotFoundException("NO USER EXISTS");
			}
		} catch (Exception gtsException) {
			gtsException.printStackTrace();
			throw new UsernameNotFoundException("Some ERROR OCCURRED");
		}
		System.out.println("exiting  with UserServiceImpl.LoadUserByUserName");
		return userDetails;
	}
*/

	

}
