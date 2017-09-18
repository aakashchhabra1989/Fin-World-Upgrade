package com.achhabra.finworld.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.achhabra.finworld.model.User;
import com.achhabra.finworld.util.ConnectionUtil;

@Repository
public class UserDAOImpl implements UserDAO {
	
/*	@Autowired
	*/private SessionFactory sessionFactory;
	
/*	private Session openSession() {
		return sessionFactory.getCurrentSession();
	}
*/

	public User getUser(String userId) {
		List<User> userList = new ArrayList<User>();
		Session session= ConnectionUtil.getSessionFactory().openSession();
		try{
		Query query = session.createQuery("from User u where u.userId = :userId");
		query.setParameter("userId", userId);
		userList = query.list();
		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;
		}finally{
		session.close();
		}
	}

}
