package com.lab410.yy.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class Hibernate {
	public static final SessionFactory sessionFactory;
	
	static{
		Configuration cfg = new Configuration();
		//使用hibernate.cfg.xml配置文件
		cfg.configure();
		ServiceRegistryBuilder serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties());
		sessionFactory = cfg.buildSessionFactory(serviceRegistry.buildServiceRegistry());
	}
	
	
	private static final ThreadLocal<Session> session = new ThreadLocal<Session>();
	
	public static Session currentSession() throws HibernateException{
		Session s = session.get();
		if(s == null){//if no session in ThreadLocal
			s = sessionFactory.openSession();
			session.set(s);
		}
		return s;
	}
	
	public static void closeSession()
			throws HibernateException 
		{
			Session s = session.get();
			if (s != null)
				s.close();
			session.set(null);
		}
	

}
