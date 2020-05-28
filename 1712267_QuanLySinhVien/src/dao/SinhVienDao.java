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

import entity.SinhVien;
import util.HibernateUtil;

@Transactional(rollbackOn = Exception.class)
public class SinhVienDao {
	protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public void insert(SinhVien sinhVien) {
		Session session = sessionFactory.openSession();
		try {
			Transaction trans = session.beginTransaction();
			session.saveOrUpdate(sinhVien);
			trans.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		session.close();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SinhVien> findAll() {
		Session session = sessionFactory.openSession();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery query = (CriteriaQuery) builder.createQuery(SinhVien.class);

			Root<SinhVien> root = (Root<SinhVien>) query.from(SinhVien.class);

			query.select(root);

			Query<SinhVien> list = session.createQuery(query);

			return list.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
}
