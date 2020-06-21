package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "diem")
public class Diem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_diem")
	private int _diemNo;

	@Column(name = "ma_mon")
	private String _maMon;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ma_mon", insertable = false, updatable = false, nullable = true, referencedColumnName = "ma_mon")
	private Mon mon_diem;

	public Mon getMon_diem() {
		return mon_diem;
	}

	public void setMon_diem(Mon mon_diem) {
		this.mon_diem = mon_diem;
	}

	@Column(name = "ten_sinhvien")
	private String _tenSinhVien;

	@Column(name = "malop_mon")
	private String maLop_mon;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "malop_mon", insertable = false, updatable = false, nullable = true, referencedColumnName = "malop_mon")
	private DSL_MON dsl_mon;

	public String getMaLop_mon() {
		return maLop_mon;
	}

	public void setMaLop_mon(String maLop_mon) {
		this.maLop_mon = maLop_mon;
	}

	public DSL_MON getDsl_mon() {
		return dsl_mon;
	}

	public void setDsl_mon(DSL_MON dsl_mon) {
		this.dsl_mon = dsl_mon;
	}

	@Column(name = "ma_sinhvien")
	private String _mssv;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ma_sinhvien", insertable = false, updatable = false, nullable = true, referencedColumnName = "ma_sinhvien")
	private SinhVien sinhVien;

	public String get_mssv() {
		return _mssv;
	}

	public void set_mssv(String _mssv) {
		this._mssv = _mssv;
	}

	public SinhVien getSinhVien() {
		return sinhVien;
	}

	public void setSinhVien(SinhVien sinhVien) {
		this.sinhVien = sinhVien;
	}

	@Column(name = "diem_gk")
	private float _gk;

	@Column(name = "diem_ck")
	private float _ck;

	@Column(name = "diem_khac")
	private float _khac;

	@Column(name = "diem_tong")
	private float _tongDiem;

	public Diem() {

	}

	public int get_diemNo() {
		return _diemNo;
	}

	public void set_diemNo(int _diemNo) {
		this._diemNo = _diemNo;
	}

	public String get_maMon() {
		return _maMon;
	}

	public void set_maMon(String _maMon) {
		this._maMon = _maMon;
	}

	public float get_gk() {
		return _gk;
	}

	public void set_gk(float _gk) {
		this._gk = _gk;
	}

	public float get_ck() {
		return _ck;
	}

	public void set_ck(float _ck) {
		this._ck = _ck;
	}

	public float get_khac() {
		return _khac;
	}

	public void set_khac(float _khac) {
		this._khac = _khac;
	}

	public float get_tongDiem() {
		return _tongDiem;
	}

	public void set_tongDiem(float _tongDiem) {
		this._tongDiem = _tongDiem;
	}

	public String get_tenSinhVien() {
		return _tenSinhVien;
	}

	public void set_tenSinhVien(String _tenSinhVien) {
		this._tenSinhVien = _tenSinhVien;
	}

	public List<Diem> findByML_Mon(List<Diem> diems, String ml_mon) {
		List<Diem> diems2 = new ArrayList<Diem>();
		for (Diem diem : diems) {
			if (diem.getMaLop_mon().equals(ml_mon)) {
				diems2.add(diem);
			}
		}
		return diems2;
	}

	@Transient
	Comparator<Diem> diemComparator = new Comparator<Diem>() {
		public int compare(Diem o1, Diem o2) {
			int c = o1.get_mssv().compareTo(o2.get_mssv());
			if (c == 0) {
				c = o1.getMaLop_mon().compareTo(o2.getMaLop_mon());
			}
			return c;
		}
	};

	public Diem findByMSSV_LopMon(List<Diem> diems, Diem diem) {
		Collections.sort(diems, diemComparator);
		int i = Collections.binarySearch(diems, diem, diemComparator);

		if (i < 0) {
			return null;
		} else {
			return diems.get(i);
		}
	}
}
