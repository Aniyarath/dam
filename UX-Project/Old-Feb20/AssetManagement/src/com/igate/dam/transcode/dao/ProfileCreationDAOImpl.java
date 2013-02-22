package com.igate.dam.transcode.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.common.framework.dao.hibernate.GenericHibernateDAO;
import com.igate.dam.transcode.dto.ProfileCreation;
import com.igate.dam.transcode.dto.Vendors;

@Repository("profileCreationDAOImpl")

public class ProfileCreationDAOImpl extends GenericHibernateDAO<ProfileCreation>implements ProfileCreationDAO  {

	List<String> vendorNameList=new ArrayList<String>();
	@Autowired
	SessionFactory sessionFactory;	
	
	
/****************************  List the Vendors Name **************************************************/		
	@SuppressWarnings("unchecked")
	public List<String> getVendorNames() throws DAMException {

		try
		{
		String query = "Select v.vendorName from Vendors v";
		vendorNameList = getSessionFactory().openSession().createQuery(query)
				.list();
		System.out.println("dao----------" + vendorNameList.size());
		}
		catch(Exception exception)
		{
			throw new DAMException("unable to fetch vendor details");
		}
		return vendorNameList;
	}
	

/********************************   Add Profile to Data Base   *******************************************/
	
	public void createProfile(ProfileCreation profile) throws DAMException{
		// TODO Auto-generated method stub
try
{
		System.out.println("----------------Inside dao....." + profile.getProfileName());
		System.out.println("getSession---"+sessionFactory);
		sessionFactory.getCurrentSession().save(profile);
}
catch(Exception exception)
{
	throw new DAMException("unable to save profile details");
}

	}

	
/*************************** List the Vendors Available  **********************************************/
	@SuppressWarnings("unchecked")
	public List<Vendors> getvendor() throws DAMException{
		List<Vendors> vendorList = new ArrayList<Vendors>();
		
		try
		{
			String query = "from Vendors";
		
		vendorList = getSessionFactory().openSession().createQuery(query)
		.list();
		}
		catch(Exception exception)
		{
			throw new DAMException("unable to fetch vendor details");
		}
		return vendorList;
	}

/************************************* List the Profiles Available *************************************/

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<ProfileCreation> listProfiles()throws DAMException {
		// TODO Auto-generated method stub
		List<ProfileCreation> profileList = new ArrayList<ProfileCreation>();
		try {
			profileList = sessionFactory.getCurrentSession().find(
					"from ProfileCreation");
			System.out.println(profileList.size());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new DAMException("unable to fetch profile details");
		}
		return profileList;
	}
	
/*********************** Delete the Profile from data base **********************************************/
	
	
	public void deleteProfile(int profileid) throws DAMException{
		ProfileCreation profileCreation;
		System.out.println("dao..........." + profileid);
try
{
		List<ProfileCreation> profileList = listProfiles();
		Iterator<ProfileCreation> iterator = profileList.iterator();
		while (iterator.hasNext()) {
			profileCreation = iterator.next();
			if (profileCreation.getProfileId() == profileid) {
				sessionFactory.getCurrentSession().delete(profileCreation);
			}
		}
}
catch(Exception exception)
{
	throw new DAMException("unable to delete the profile");
}
	}
	
/****************Retrieve the profile choose for update *************************************************/
	
	
	public ProfileCreation updateProfile(int profileid) throws DAMException{
		// TODO Auto-generated method stub
		ProfileCreation profileCreation;
		ProfileCreation profileCreation2 = null;
		System.out.println("dao..........." + profileid);
		try
		{
		List<ProfileCreation> profileList = listProfiles();
		Iterator<ProfileCreation> iterator = profileList.iterator();
		while (iterator.hasNext()) {
			profileCreation = iterator.next();
			if (profileCreation.getProfileId() == profileid) {
				profileCreation2 = profileCreation;
			}
		}
		}
		catch(Exception exception)
		{
			throw new DAMException("unable to update the profile");
		}
		return profileCreation2;
	}

/****************************** Update the Profile ***************************************************/
	
	public void profileSave(ProfileCreation profile) throws DAMException{

	try
	{sessionFactory.getCurrentSession().saveOrUpdate(profile);
	}
	catch(Exception exception)
	{
		throw new DAMException("unable to update the profile");
	}

	}

	/*******************get Profile Names *******************************************/
	@SuppressWarnings("unchecked")
	public	List<String> getProfileNames()throws DAMException
	{
		List<String> profileList=new ArrayList<String>();
		try
		
		{
			String query="Select p.profileName from ProfileCreation p";
			Query query1=sessionFactory.getCurrentSession().createQuery(query);
			profileList=query1.list();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			throw new DAMException("unable to fetch the profile details");
		}
		return profileList;
		
	}
}
