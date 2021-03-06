package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import dao.DSLMDao;
import dao.DiemDao;
import dao.LopDao;
import dao.MonDao;
import dao.SinhVienDao;
import entity.DSL_MON;
import entity.Diem;
import entity.Lop;
import entity.Mon;
import entity.SinhVien;

public class FileParser {

	public FileParser() {

	}

	public List<SinhVien> readFromCSV_SinhVien(String fileName) {
		List<String[]> parsedStuff = new ArrayList<String[]>();
		List<SinhVien> sinhVien = new ArrayList<SinhVien>();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF8"))) {
			String line = br.readLine();
			while (line != null) {
				String[] stuff = line.split(",");
				parsedStuff.add(stuff);
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Lop lop = new Lop();

		for (int i = 0; i < parsedStuff.size(); ++i) {
			if (i == 0) {
				lop.set_maLop(parsedStuff.get(i)[0]);
			} else if (i == 1) {
				continue;
			} else {

				// Do file input có đầu vào là lớp nên phải set lớp nếu như lớp chưa tồn tại. Do
				// dùng hàm saveorupdate ở insert nên nếu lớp đã tồn tại thì sẽ giữ nguyên
				LopDao lopDao = new LopDao();
				List<Lop> lops = lopDao.findAll();
				Lop foundLop = new Lop().findByML(lops, lop);

				if (foundLop == null) {
					System.out.println("Thêm mới lớp!");
					lopDao.insert(lop);

					SinhVien newsinhVien = new SinhVien();
					newsinhVien.set_mssv(parsedStuff.get(i)[1]);
					newsinhVien.set_ten(parsedStuff.get(i)[2]);
					newsinhVien.set_gioiTinh(parsedStuff.get(i)[3]);
					newsinhVien.set_cmnd(parsedStuff.get(i)[4]);

					newsinhVien.setMa_quyen(2);
					newsinhVien.setMa_lop(lop.get_maLop().toString());

					newsinhVien.set_password(BCrypt.hashpw(newsinhVien.get_cmnd(), BCrypt.gensalt(12)));

					sinhVien.add(newsinhVien);

					// Thêm sinh viên
					SinhVienDao sinhVienDao = new SinhVienDao();
					sinhVienDao.insert(newsinhVien);
				} else {
					System.out.println("Lớp đã tồn tại thêm mới sinh viên!");

					SinhVien newsinhVien = new SinhVien();
					newsinhVien.set_mssv(parsedStuff.get(i)[1]);
					newsinhVien.set_ten(parsedStuff.get(i)[2]);
					newsinhVien.set_gioiTinh(parsedStuff.get(i)[3]);
					newsinhVien.set_cmnd(parsedStuff.get(i)[4]);

					newsinhVien.setMa_quyen(2);
					newsinhVien.setMa_lop(lop.get_maLop().toString());

					newsinhVien.set_password(BCrypt.hashpw(newsinhVien.get_mssv(), BCrypt.gensalt(12)));

					sinhVien.add(newsinhVien);

					// Thêm sinh viên
					SinhVienDao sinhVienDao = new SinhVienDao();
					sinhVienDao.insert(newsinhVien);
				}
			}
		}

		return sinhVien;
	}

	public List<Mon> readFromCSV_Mon(String fileName) {
		List<String[]> parsedStuff = new ArrayList<String[]>();
		List<Mon> mons = new ArrayList<Mon>();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF8"))) {
			String line = br.readLine();
			while (line != null) {
				String[] stuff = line.split(",");
				parsedStuff.add(stuff);
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Lớp không được để trong vòng lập do dùng lại nhiều lần
		Lop lop = new Lop();

		for (int i = 0; i < parsedStuff.size(); ++i) {

			if (i == 0) {
				lop.set_maLop(parsedStuff.get(i)[0]);
			} else if (i == 1) {
				continue;
			} else {
				System.out.println(lop.get_maLop());
				MonDao monDao = new MonDao();

				LopDao lopDao = new LopDao();
				List<Lop> lops = lopDao.findAll();
				Lop foundLop = lop.findByML(lops, lop);
				if (foundLop == null) {
					lopDao.insert(lop);
					Mon mon = new Mon();
					mon.set_maMon(parsedStuff.get(i)[1]);
					mon.set_tenMon(parsedStuff.get(i)[2]);
					mons.add(mon);
					monDao.insert(mon);

					DSLMDao dslmDao = new DSLMDao();
					DSL_MON dsl_MON = new DSL_MON();
					dsl_MON.set_maMon(mon.get_maMon());
					dsl_MON.set_maLop(lop.get_maLop());
					dsl_MON.set_phongHoc(parsedStuff.get(i)[3]);
					dslmDao.insert(dsl_MON);
				} else {
					Mon mon = new Mon();
					mon.set_maMon(parsedStuff.get(i)[1]);
					mon.set_tenMon(parsedStuff.get(i)[2]);
					mons.add(mon);
					monDao.insert(mon);

					DSLMDao dslmDao = new DSLMDao();
					DSL_MON dsl_MON = new DSL_MON();
					dsl_MON.set_maMon(mon.get_maMon());
					dsl_MON.set_maLop(lop.get_maLop());
					dsl_MON.set_phongHoc(parsedStuff.get(i)[3]);
					dslmDao.insert(dsl_MON);
				}

				// Có môn trước mới có danh sách lớp môn danh sách lớp môn sau khi insert sẽ có
				// trigger tạo danh sách lớp môn dựa vào mssv
				// Check trước do môn chỉ cần insert 2 lần mà file parse thì insert nhiều lần
				// vào môn

			}
		}

		return mons;
	}

	public List<Diem> readFromCSV_Lop(String fileName) {
		List<String[]> parsedStuff = new ArrayList<String[]>();
		List<Diem> diems = new ArrayList<Diem>();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF8"))) {
			String line = br.readLine();
			while (line != null) {
				String[] stuff = line.split(",");
				parsedStuff.add(stuff);
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DSL_MON dsl_MON = new DSL_MON();
		DiemDao diemDao = new DiemDao();

		for (int i = 0; i < parsedStuff.size(); ++i) {
			if (i == 0) {
				dsl_MON.setMalop_mon(parsedStuff.get(i)[0]);
			} else if (i == 1) {
				continue;
			} else {
				Diem diem = new Diem();
				diem.setMaLop_mon(dsl_MON.getMalop_mon());
				diem.set_maMon(dsl_MON.getMalop_mon().split("-")[1]);
				diem.set_mssv(parsedStuff.get(i)[1]);
				diem.set_tenSinhVien(parsedStuff.get(i)[2]);
				diem.set_gk(Float.parseFloat(parsedStuff.get(i)[3]));
				diem.set_ck(Float.parseFloat(parsedStuff.get(i)[4]));
				diem.set_khac(Float.parseFloat(parsedStuff.get(i)[5]));
				diem.set_tongDiem(Float.parseFloat(parsedStuff.get(i)[6]));

				
				diems.add(diem);
				diemDao.insert(diem);
			}
		}

		return diems;
	}
}
