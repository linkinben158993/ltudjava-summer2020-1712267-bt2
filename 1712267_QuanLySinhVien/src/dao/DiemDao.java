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

	public Diem findById(int id) {
		Session session = sessionFactory.openSession();
		try {
			return session.find(Diem.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	public void insert(Diem diem) {
		Session session = sessionFactory.openSession();
		try {
			Transaction trans = session.beginTransaction();
			session.saveOrUpdate(diem);
			trans.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public boolean update(int id, int cot, float cotDiemMoi) {
		Session session = sessionFactory.openSession();
		try {
			Transaction trans = session.beginTransaction();
			Diem newDiem = (Diem) session.get(Diem.class, id);
			System.out.println(newDiem.get_tenSinhVien());
			System.out.println(cot);
			switch (cot) {
			case 1:
				newDiem.set_gk(cotDiemMoi);
				trans.commit();
				break;
			case 2:
				newDiem.set_ck(cotDiemMoi);
				trans.commit();
				break;
			case 3:
				newDiem.set_khac(cotDiemMoi);
				trans.commit();
				break;
			default:
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
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
