package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entity.GiaoVu;
import util.HibernateUtil;

public class GiaoVuDao {
	protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public void insert(GiaoVu giaoVu) {
		Session session = sessionFactory.openSession();
		try {
			Transaction trans = session.beginTransaction();
			session.saveOrUpdate(giaoVu);
			trans.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		session.close();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<GiaoVu> findAll() {
		Session session = sessionFactory.openSession();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery query = (CriteriaQuery) builder.createQuery(GiaoVu.class);

			Root<GiaoVu> root = (Root<GiaoVu>) query.from(GiaoVu.class);

			query.select(root);

			Query<GiaoVu> list = session.createQuery(query);

			return list.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
}
