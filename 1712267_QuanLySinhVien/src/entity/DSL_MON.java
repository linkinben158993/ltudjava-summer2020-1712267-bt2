package entity;

import java.io.Serializable;
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
@Table(name = "danhsachlop_mon")
public class DSL_MON implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "danhsachlop_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int _danhsachlopNo;

	@Column(name = "ma_mon")
	private String _maMon;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ma_mon", insertable = false, updatable = false, nullable = true, referencedColumnName = "ma_mon")
	private Mon mon_lop;

	public Mon getMon_lop() {
		return mon_lop;
	}

	public void setMon_lop(Mon mon_lop) {
		this.mon_lop = mon_lop;
	}

	@Column(name = "ma_lop")
	private String _maLop;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ma_lop", insertable = false, updatable = false, nullable = true, referencedColumnName = "ma_lop")
	private Lop lop_mon;

	public Lop getLop_mon() {
		return lop_mon;
	}

	public void setLop_mon(Lop lop_mon) {
		this.lop_mon = lop_mon;
	}

	public int get_danhsachlopNo() {
		return _danhsachlopNo;
	}

	public void set_danhsachlopNo(int _danhsachlopNo) {
		this._danhsachlopNo = _danhsachlopNo;
	}

	@OneToMany(mappedBy = "dsl_MON", fetch = FetchType.LAZY)
	private List<DSSV_MON> dssv_Mons;

	public List<DSSV_MON> getDssv_Mons() {
		return dssv_Mons;
	}

	public void setDssv_Mons(List<DSSV_MON> dssv_Mons) {
		this.dssv_Mons = dssv_Mons;
	}

	@OneToMany(mappedBy = "dsl_MON", fetch = FetchType.LAZY)
	private List<DCHP> dchps;

	public List<DCHP> getDchps() {
		return dchps;
	}

	public void setDchps(List<DCHP> dchps) {
		this.dchps = dchps;
	}

	@Column(name = "malop_mon")
	private String malop_mon;

	public String getMalop_mon() {
		return malop_mon;
	}

	public void setMalop_mon(String malop_mon) {
		this.malop_mon = malop_mon;
	}

	@Column(name = "phong_hoc")
	private String _phongHoc;

	public String get_maMon() {
		return _maMon;
	}

	public void set_maMon(String _maMon) {
		this._maMon = _maMon;
	}

	public String get_maLop() {
		return _maLop;
	}

	public void set_maLop(String _maLop) {
		this._maLop = _maLop;
	}

	public String get_phongHoc() {
		return _phongHoc;
	}

	public void set_phongHoc(String _phongHoc) {
		this._phongHoc = _phongHoc;
	}

	@Transient
	Comparator<DSL_MON> dslmComparator = new Comparator<DSL_MON>() {
		public int compare(DSL_MON o1, DSL_MON o2) {
			return o1.getMalop_mon().compareTo(o2.getMalop_mon());
		}
	};

	public DSL_MON findByMLM(List<DSL_MON> listSV, DSL_MON dsl_MON) {
		Collections.sort(listSV, dslmComparator);
		int i = Collections.binarySearch(listSV, dsl_MON, dslmComparator);

		if (i < 0) {
			return null;
		} else {
			return listSV.get(i);
		}
	}

}
