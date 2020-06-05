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

import entity.Diem;
import util.HibernateUtil;

@Transactional(rollbackOn = Exception.class)
public class DiemDao {
	protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public void insert(Diem diem) {
		Session session = sessionFactory.openSession();
		try {
			Transaction trans = session.beginTransaction();
			session.saveOrUpdate(diem);
			trans.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		session.close();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Diem> findAll() {
		Session session = sessionFactory.openSession();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery query = (CriteriaQuery) builder.createQuery(Diem.class);

			Root<Diem> root = (Root<Diem>) query.from(Diem.class);

			query.select(root);

			Query<Diem> list = session.createQuery(query);

			return list.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
}
