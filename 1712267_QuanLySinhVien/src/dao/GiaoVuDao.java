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

import entity.GiaoVu;
import util.HibernateUtil;

@Transactional(rollbackOn = Exception.class)
public class GiaoVuDao {
	private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public GiaoVu findById(int id) {
		Session session = sessionFactory.openSession();
		try {
			return session.find(GiaoVu.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int updatePassword(String password, int id) {
		Session session = sessionFactory.openSession();
		try {
			Transaction trans = session.beginTransaction();
			GiaoVu newGiaoVu = (GiaoVu) session.get(GiaoVu.class, id);
			newGiaoVu.set_password(password);
			trans.commit();
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

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
