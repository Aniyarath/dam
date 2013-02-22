package com.igate.dam.publish.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.igate.dam.common.exception.DAMException;
import com.igate.dam.common.framework.dao.hibernate.GenericHibernateDAO;
import com.igate.dam.metadata.dto.Vendor;
import com.igate.dam.publish.dto.PublishProfile;




/**
 * @author mj802966
 *
 */
@Repository("publishProfileDAOImpl")
public class PublishProfileDAOImpl extends GenericHibernateDAO<PublishProfile>implements PublishProfileDAO {

	@Autowired
	SessionFactory sessionFactory;	
	
	List<String> vendorNameList=new ArrayList<String>();
	@Override
	public List<String> getVendorNames() throws DAMException {
		try
		{String query = "Select v.vendor_name from Vendor v";
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
	
	public List<Vendor> getVendorid()throws DAMException
	
	{
		List<Vendor> vendorList;
		try
		{
		String query = "from Vendor";
	    vendorList = getSessionFactory().openSession().createQuery(query)
				.list();
		System.out.println("dao----------" + vendorList.size());
		}
		catch(Exception exception)
		{
			throw new DAMException("unable to fetch vendor details");
		}
		return vendorList;
	}
	@SuppressWarnings("unchecked")
	public	List<String> getProfileNames()throws DAMException
	{
		List<String> profileList=new ArrayList<String>();
		try
		
		{
			String query="Select p.publish_profile_name from PublishProfile p";
			Query query1=sessionFactory.getCurrentSession().createQuery(query);
			profileList=query1.list();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			throw new DAMException("unable to get the profile name");
		}
		return profileList;
		
	}

	
	public void createProfile(PublishProfile profile) throws DAMException {
		// TODO Auto-generated method stub
try
{
		System.out.println("----------------Inside dao....." + profile.getPublish_profile_name());
		System.out.println("getSession---"+sessionFactory);
		sessionFactory.getCurrentSession().save(profile);
}
catch(Exception exception)
{
	throw new DAMException("unable to save profile details");
}

	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<PublishProfile> listProfiles() throws DAMException{
		// TODO Auto-generated method stub
		List<PublishProfile> profileList = new ArrayList<PublishProfile>();
		try {
			profileList = sessionFactory.getCurrentSession().find(
					"from PublishProfile");
			System.out.println(profileList.size());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new DAMException("unable to  fetch profile details");
		}
		return profileList;
	}
/*********************** Delete the Profile from data base **********************************************/
	
	
	public void deleteProfile(int profileid) throws DAMException{
		PublishProfile profileCreation;
		try
		{
		System.out.println("dao..........." + profileid);

		List<PublishProfile> profileList = listProfiles();
		Iterator<PublishProfile> iterator = profileList.iterator();
		while (iterator.hasNext()) {
			profileCreation = iterator.next();
			if (profileCreation.getPublish_profile_id() == profileid) {
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
	
	
	public PublishProfile updateProfile(int profileid) throws DAMException{
		// TODO Auto-generated method stub
		PublishProfile profileCreation;
		PublishProfile profileCreation2 = null;
		try
		{
		System.out.println("dao..........." + profileid);
		List<PublishProfile> profileList = listProfiles();
		Iterator<PublishProfile> iterator = profileList.iterator();
		while (iterator.hasNext()) {
			profileCreation = iterator.next();
			if (profileCreation.getPublish_profile_id() == profileid) {
				profileCreation2 = profileCreation;
			}
		}
		}
		catch(Exception exception)
		{
			throw new DAMException("unable to fetch profile details");
		}
		return profileCreation2;
	}

/****************************** Update the Profile ***************************************************/
	
	public void profileSave(PublishProfile profile) throws DAMException {
try
{
		sessionFactory.getCurrentSession().saveOrUpdate(profile);
}
catch(Exception exception)
{
	throw new DAMException("unable to update the profile details");
}

	}
}
