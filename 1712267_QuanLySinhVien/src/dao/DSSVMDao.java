package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entity.DSSV_MON;
import util.HibernateUtil;

@Transactional(rollbackOn = Exception.class)
public class DSSVMDao {
	protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public DSSV_MON findById(int id) {
		Session session = sessionFactory.openSession();
		try {
			return session.find(DSSV_MON.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	public void insert(DSSV_MON dssv_MON) {
		Session session = sessionFactory.openSession();
		try {
			Transaction trans = session.beginTransaction();
			session.saveOrUpdate(dssv_MON);
			trans.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		session.close();
	}

	public void remove(int id) {
		DSSV_MON foundDSSV = findById(id);
		Session session = sessionFactory.openSession();
		try {
			Transaction trans = session.beginTransaction();
			session.remove(foundDSSV);
			trans.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<DSSV_MON> findAll() {
		Session session = sessionFactory.openSession();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery query = (CriteriaQuery) builder.createQuery(DSSV_MON.class);

			Root<DSSV_MON> root = (Root<DSSV_MON>) query.from(DSSV_MON.class);

			query.select(root);

			Query<DSSV_MON> list = session.createQuery(query);

			return list.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
}
