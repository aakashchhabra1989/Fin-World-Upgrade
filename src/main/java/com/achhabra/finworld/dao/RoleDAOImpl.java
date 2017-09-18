package com.achhabra.finworld.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.achhabra.finworld.model.Role;
import com.achhabra.finworld.util.ConnectionUtil;

@Repository
public class RoleDAOImpl implements RoleDAO {
	
/*	@Autowired
*/	private SessionFactory sessionFactory;
	
/*	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
*/
	private Session getCurrentSession() {
		Configuration configuration = new AnnotationConfiguration();
		Configuration configure = configuration.configure();
		SessionFactory factory= configure.buildSessionFactory();			
		Session session = factory.openSession();
		return session;
		//return ConnectionUtil.getFactory().getCurrentSession();
	}

	public Role getRole(int id) {
		Role role = (Role) getCurrentSession().load(Role.class, id);
		return role;
	}

}
