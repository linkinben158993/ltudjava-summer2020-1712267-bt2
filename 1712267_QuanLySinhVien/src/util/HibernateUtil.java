package util;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import entity.DSSV_MON;
import entity.Diem;
import entity.Dot;
import entity.DCHP;
import entity.DSL_MON;
import entity.GiaoVu;
import entity.Lop;
import entity.Mon;
import entity.PhucKhao;
import entity.Quyen;
import entity.SinhVien;

public class HibernateUtil {
	private static Logger logger = Logger.getLogger(HibernateUtil.class);

	private static final SessionFactory sessionFactory = buildSessionFactory();

	@SuppressWarnings("static-access")
	private static SessionFactory buildSessionFactory() {
		try {
			// Tạo SessionFactory from hibernate.cfg.xml
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
			configuration.addAnnotatedClass(Diem.class);
			configuration.addAnnotatedClass(PhucKhao.class);
			configuration.addAnnotatedClass(Dot.class);

			// Chỉ show lỗi không show debug log của thư viện log4j
			logger.getRootLogger().setLevel(Level.ERROR);

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			BasicConfigurator.configure();
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
