package com.igate.dam.app.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.AuthorityUtils;

import com.igate.dam.app.model.ApplPermission;
import com.igate.dam.app.model.Person;
import com.igate.dam.app.model.Role;
import com.igate.dam.common.exception.DAMException;
import com.igate.dam.common.framework.dao.hibernate.GenericHibernateDAO;

import com.igate.dam.metadata.dto.Vendor;
import com.igate.dam.model.UserProfile;


public class UserDaoImpl extends GenericHibernateDAO<Person>
 implements UserDao{

	@Override
	public UserProfile findUser(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getUserDetails(String username) throws DAMException{
		System.out.println("entered into dao layer");
		Person perObj=null;
		try
		{
		perObj= (Person) getSessionFactory().openSession().createQuery("select u from Person u where FIRST_NAME='"+username+"'").uniqueResult();
		if(perObj==null)
		{
			throw new DAMException("No User Exists.");
		}
		}
		catch (Exception exception) {
			throw new DAMException("Username doesnot exists");
		}
		return  perObj;
	}

	@Override
	public Map<String, String> getuserType(int userId) throws DAMException{
		
		Map accMap=null;
		List<ApplPermission> userPermissionList;
		try
		{
		System.out.println("entered into dao layer");
		List<Role> roleList=(List<Role>) getSessionFactory().openSession().createQuery(
				"select r from Role r,Person p,PersonAssoc pa,RolePermissionAssoc rpa where pa.PERSON_ID="+userId+"and pa.APP_ROLE_ID=r.APP_ROLE_ID and r.APP_ROLE_ID=rpa.APP_ROLE_ID ").list();
		
		System.out.println("size of List"+roleList.toString());
		 accMap=new HashMap<String, String>();
		for (Role roleObj:roleList)
		{
			String userRole=roleObj.getCODE();
			System.out.println("userRole"+userRole);
			int userroleId=roleObj.getAPP_ROLE_ID();
			System.out.println("userroleId"+userroleId);
			userPermissionList=(List<ApplPermission>) getSessionFactory().openSession().createQuery(
                "select a from ApplPermission a,Role r,RolePermissionAssoc rpa where"+" "
+"r.APP_ROLE_ID=rpa.APP_ROLE_ID and rpa.APP_PERMISSION_ID=a.APP_PERMISSION_ID and r.APP_ROLE_ID="+userroleId).list();
		
		System.out.println("size of userPermissionList"+userPermissionList.toString());
		for(ApplPermission up:userPermissionList)
		{
			String userPermisiions=up.getCODE();
			System.out.println("userPermisiions****"+userPermisiions);
			accMap.put(userRole, userPermisiions);
		}
		if (userPermissionList.size()==0) {
			throw new DAMException("No User Exists.");
		}
			
		}
		
		}
		
		catch (Exception exception) 
		{
			throw new DAMException("Username doesnot exists");
		}
		return accMap;
	}
	
}
