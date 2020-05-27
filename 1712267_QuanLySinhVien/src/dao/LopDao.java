package dao;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Lop;
import util.HibernateUtil;

@Transactional(rollbackOn = Exception.class)
public class LopDao {
	protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public void insert(Lop lop) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			session.saveOrUpdate(lop);
			trans.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		session.close();
	}
}
