package dao;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;

@Transactional(rollbackOn = Exception.class)
public class SinhVienDao {
	protected SessionFactory sessionFactory;
}
