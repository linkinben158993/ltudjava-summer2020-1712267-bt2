package util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import entity.DSSV_MON;
import entity.DCHP;
import entity.DSL_MON;
import entity.GiaoVu;
import entity.Lop;
import entity.Mon;
import entity.Quyen;
import entity.SinhVien;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			Configuration configuration = new Configuration();
			configuration.configure("/resources/config/hibernate.cfg.xml");
			
			// Configure các class.
			configuration.addAnnotatedClass(Quyen.class);
			configuration.addAnnotatedClass(SinhVien.class);
			configuration.addAnnotatedClass(Lop.class);
			configuration.addAnnotatedClass(GiaoVu.class);
			configuration.addAnnotatedClass(Mon.class);
			configuration.addAnnotatedClass(DSSV_MON.class);
			configuration.addAnnotatedClass(DSL_MON.class);
			configuration.addAnnotatedClass(DCHP.class);
			
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			return sessionFactory;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
