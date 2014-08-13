package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.lab410.yy.hibernate.Hibernate;
import com.lab410.yy.hibernate.RealTimeDataModel;


public class HibernateTest {

	public HibernateTest() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args){
		Session session =  Hibernate.currentSession();
		Transaction tc = session.beginTransaction();
		String sql = "select max(id) from _machine group by machinenumber";
		List l = session.createSQLQuery(sql).list();
		for(Iterator i = l.iterator();i.hasNext();){
			System.out.println((int)i.next());
		}
		tc.commit();Hibernate.closeSession();
		
	}
	

}
