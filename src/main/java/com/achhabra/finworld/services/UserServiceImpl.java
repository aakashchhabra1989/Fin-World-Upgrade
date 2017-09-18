package com.achhabra.finworld.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.achhabra.finworld.beans.EntityBean;
import com.achhabra.finworld.dao.RoleDAOImpl;
import com.achhabra.finworld.dao.UserDAO;
import com.achhabra.finworld.model.Role;
import com.achhabra.finworld.model.ServiceConsumer;
import com.achhabra.finworld.model.User;
import com.achhabra.finworld.model.WealthProfile;
import com.achhabra.finworld.util.ConnectionUtil;
import com.achhabra.finworld.util.KeyValuePair;
import com.achhabra.finworld.util.StaticConstants;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	public User getUser(String userId) {
		return userDAO.getUser(userId);
	}
	public long registerUser(EntityBean entityBean) {
		long responseCode = StaticConstants.RESPONSE_EXCEPTION_OCCURED;
		if(!isEmailRegistered(entityBean.getPrimaryEmailId())){
			//Configuration configuration = new Configuration();
			Configuration configuration = new AnnotationConfiguration();
			Configuration configure = configuration.configure();
			SessionFactory factory= configure.buildSessionFactory();			
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			ServiceConsumer user = new ServiceConsumer();
			if(entityBean.getPrimaryPhoneNo()!=null){
				user.setUserId(entityBean.getPrimaryPhoneNo());
			}else if(entityBean.getPrimaryEmailId()!=null){
				user.setUserId(entityBean.getPrimaryEmailId());
			}			
			user.setFirstName(entityBean.getFirstName());
			user.setLastName(entityBean.getLastName());
			user.setPrimaryEmailId(entityBean.getPrimaryEmailId());
			user.setPrimaryPhoneNo(entityBean.getPrimaryPhoneNo());
			user.setPassword(entityBean.getConfirmedPassword());
			Role role = new RoleDAOImpl().getRole(StaticConstants.USER_ROLE_USER);			
			user.setRole(role);
			session.save(user);
			transaction.commit();
			session.close();
			System.out.println("Object Persisted Succcessfully.");			
			responseCode = user.getId();
		}else{
			responseCode = StaticConstants.RESPONSE_ALREADY_EXIST;			
		}
		return responseCode;
	}

	public boolean isEmailRegistered(String emailId) {
		EntityBean userDetail= getUserDetailByRegisteredEmail(emailId);
		if(userDetail==null){
			return false;
		}else{
			return true;
		}
			
	}

	public EntityBean getUserDetailByRegisteredEmail(String emailId) {
		
		return null;
	}
	
	public EntityBean getLoadUser(String name) {
		User user = getUser(name);
		ServiceConsumer serviceConsumer=null;
		EntityBean bean = new EntityBean();
		bean.setId(String.valueOf(user.getId()));
		bean.setUserId(String.valueOf(user.getUserId()));
		
		if(user instanceof ServiceConsumer){
			serviceConsumer=(ServiceConsumer)user;
			bean.setFirstName(serviceConsumer.getFirstName());
			bean.setLastName(serviceConsumer.getLastName());
			bean.setMiddleName(serviceConsumer.getMiddleName());
			bean.setPrimaryEmailId(serviceConsumer.getPrimaryEmailId());
			bean.setPrimaryPhoneNo(serviceConsumer.getPrimaryPhoneNo());
			bean.setSecondaryEmailId(serviceConsumer.getSecondaryEmailId());
			bean.setSecondaryPhoneNo(serviceConsumer.getSecondaryPhoneNo());
		}
		
		return bean;
	}

	public List<KeyValuePair> getUserPortfolio(String name) {
		User user = getUser(name);
		List<KeyValuePair> list= new ArrayList<KeyValuePair>();
		if(user!=null && user.getWealthProfiles() !=null){
			for(WealthProfile portfolio: user.getWealthProfiles()){
				KeyValuePair obj = new KeyValuePair();
				obj.setId(portfolio.getId() + "");
				obj.setValue(portfolio.getProfileId());
				list.add(obj);			
			}			
		}
		return list;
	}
	
	public void updateUserDetails(EntityBean entityBean) {
		Session session = null;
    	Transaction tx = null;
		try{
			if(ConnectionUtil.getSessionFactory().getCurrentSession()!=null )
				session = ConnectionUtil.getSessionFactory().getCurrentSession();
			else
				session = ConnectionUtil.getSessionFactory().openSession();
    		tx = session.beginTransaction();

    		User user= getUser(entityBean.getUserId());
			if(user instanceof ServiceConsumer){
				ServiceConsumer serviceConsumer= (ServiceConsumer)user;
				serviceConsumer.setUpdatedDate(new Date());
				serviceConsumer.setPrimaryEmailId(entityBean.getPrimaryEmailId());
				serviceConsumer.setPrimaryPhoneNo(entityBean.getPrimaryPhoneNo());
				serviceConsumer.setFirstName(entityBean.getFirstName());
				serviceConsumer.setLastName(entityBean.getLastName());
				session.update(serviceConsumer);
			}
			
			tx.commit();
		}catch(RuntimeException e){
    		try{
    			tx.rollback();
    		}catch(RuntimeException rbe){
    			//log.error("Couldn’t roll back transaction", rbe);
    		}
    		throw e;
    	}finally{
    		if(session!=null){
    			try{
    				session.close();
    			}catch(Exception exception){
    				System.out.println(exception.getMessage());
    			}
    		}
    	}
		
		
	}
	
}
