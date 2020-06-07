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

import entity.PhucKhao;
import util.HibernateUtil;

@Transactional(rollbackOn = Exception.class)
public class PhucKhaoDao {
	protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public PhucKhao findById(int id) {
		Session session = sessionFactory.openSession();
		try {
			return session.find(PhucKhao.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void insert(PhucKhao phucKhao) {
		Session session = sessionFactory.openSession();
		try {
			Transaction trans = session.beginTransaction();
			session.saveOrUpdate(phucKhao);
			trans.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		session.close();
	}

	public int updatePassword(String password, int id) {
		Session session = sessionFactory.openSession();
		try {
			Transaction trans = session.beginTransaction();
			// PhucKhao newPhucKhao = (PhucKhao) session.get(PhucKhao.class, id);
			// Update tình trạng ở đây

			trans.commit();
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<PhucKhao> findAll() {
		Session session = sessionFactory.openSession();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery query = (CriteriaQuery) builder.createQuery(PhucKhao.class);

			Root<PhucKhao> root = (Root<PhucKhao>) query.from(PhucKhao.class);

			query.select(root);

			Query<PhucKhao> list = session.createQuery(query);

			return list.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
}
