package com.achhabra.finworld.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * @author achhabra
 *
 */
public final class ConnectionUtil {
	
	private static final SessionFactory sessionFactory;
	static {
		try {
			Configuration configuration = new AnnotationConfiguration(); 
			Configuration configure = configuration.configure(); 
			//SessionFactory factory= configure.buildSessionFactory();
			sessionFactory = configure.buildSessionFactory();
			//sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/*
	 * private static Configuration configuration = null; private static
	 * SessionFactory factory= null; private static Configuration getConfiguration()
	 * { if(configuration==null){ configuration = new
	 * AnnotationConfiguration().configure(); } return configuration; }
	 * 
	 * public static SessionFactory getSessionFactory() { if(factory==null){ factory
	 * = getConfiguration().buildSessionFactory(); } return factory; }
	 */
	/*
	 * private static ConnectionUtil instance = new ConnectionUtil();
	 * 
	 * private SessionFactory sessionFactory;
	 * 
	 * private ConnectionUtil(){ this.sessionFactory = buildSessionFactory(); }
	 * 
	 * private synchronized static SessionFactory buildSessionFactory() { return new
	 * AnnotationConfiguration().configure().buildSessionFactory(); }
	 * 
	 * public static ConnectionUtil getInstance() { if(instance == null){ return new
	 * ConnectionUtil(); } return instance; }
	 * 
	 * public SessionFactory getSessionFactory() { return sessionFactory; }
	 * 
	 * public static Session openSession() { Configuration configuration = new
	 * AnnotationConfiguration(); Configuration configure =
	 * configuration.configure(); SessionFactory factory=
	 * configure.buildSessionFactory(); Session session = factory.openSession();
	 * return session;
	 * 
	 * //return ConnectionUtil.getFactory().getCurrentSession(); }
	 */
}
