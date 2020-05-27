package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import entity.Quyen;
import util.HibernateUtil;

@Transactional(rollbackOn = Exception.class)
public class QuyenDao {
	
	private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Quyen> findAll() {
		Session session = sessionFactory.openSession();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery query = (CriteriaQuery) builder.createQuery(Quyen.class);

			Root<Quyen> root = (Root<Quyen>) query.from(Quyen.class);

			query.select(root);

			Query<Quyen> list = session.createQuery(query);

			return list.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
}
