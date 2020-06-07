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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "danhsachsinhvien_mon")
public class DSSV_MON implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "danhsachsinhvien_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int _dssvNo;

	@Column(name = "ma_sinhvien")
	private String _mssv;

	@Column(name = "malop_mon")
	private String _malopMon;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "malop_mon", insertable = false, updatable = false, nullable = true, referencedColumnName = "malop_mon")
	private DSL_MON dsl_MON;

	public DSL_MON getDsl_MON() {
		return dsl_MON;
	}

	public void setDsl_MON(DSL_MON dsl_MON) {
		this.dsl_MON = dsl_MON;
	}

	@Column(name = "ma_mon")
	private String _maMon;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ma_mon", insertable = false, updatable = false, nullable = true, referencedColumnName = "ma_mon")
	private Mon mon_dssinhvien;

	public Mon getMon_dssinhvien() {
		return mon_dssinhvien;
	}

	public void setMon_dssinhvien(Mon mon_dssinhvien) {
		this.mon_dssinhvien = mon_dssinhvien;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ma_sinhvien", insertable = false, updatable = false, nullable = true, referencedColumnName = "ma_sinhvien")
	private SinhVien sinhVien;

	public SinhVien getSinhVien() {
		return sinhVien;
	}

	public void setSinhVien(SinhVien sinhVien) {
		this.sinhVien = sinhVien;
	}

	@OneToMany(mappedBy = "dssv_MON")
	private List<PhucKhao> phucKhaos;

	public List<PhucKhao> getPhucKhaos() {
		return phucKhaos;
	}

	public void setPhucKhaos(List<PhucKhao> phucKhaos) {
		this.phucKhaos = phucKhaos;
	}

	public int get_dssvNo() {
		return _dssvNo;
	}

	public void set_dssvNo(int _dssvNo) {
		this._dssvNo = _dssvNo;
	}

	public String get_mssv() {
		return _mssv;
	}

	public void set_mssv(String _mssv) {
		this._mssv = _mssv;
	}

	public String get_malopMon() {
		return _malopMon;
	}

	public void set_malopMon(String _malopMon) {
		this._malopMon = _malopMon;
	}

	public String get_maMon() {
		return _maMon;
	}

	public void set_maMon(String _maMon) {
		this._maMon = _maMon;
	}

	@Transient
	Comparator<DSSV_MON> dssvMonComparator = new Comparator<DSSV_MON>() {
		public int compare(DSSV_MON o1, DSSV_MON o2) {
			return o1.get_mssv().compareTo(o2.get_mssv());
		}
	};

	public DSSV_MON findByMSSV(List<DSSV_MON> dssv_MONs, DSSV_MON dssv_MON) {
		Collections.sort(dssv_MONs, dssvMonComparator);
		int i = Collections.binarySearch(dssv_MONs, dssv_MON, dssvMonComparator);

		if (i < 0) {
			return null;
		} else {
			return dssv_MONs.get(i);
		}
	}

	public List<DSSV_MON> findByMLM(String mlm, List<DSSV_MON> dssv_MONs) {
		List<DSSV_MON> dssv_MONs2 = new ArrayList<DSSV_MON>();
		for (DSSV_MON item : dssv_MONs) {
			if (item.get_malopMon().equals(mlm)) {
				dssv_MONs2.add(item);
			}
		}
		if (dssv_MONs2.isEmpty()) {
			return null;
		} else {
			return dssv_MONs2;
		}
	}

}
