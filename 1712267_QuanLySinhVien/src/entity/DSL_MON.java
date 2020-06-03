package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	@JoinColumn(name = "ma_mon", insertable = false, updatable = false, nullable = true, foreignKey = @ForeignKey(name = "FK_DANHSACHLOP_MON"), referencedColumnName = "ma_mon")
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
	@JoinColumn(name = "ma_lop", insertable = false, updatable = false, nullable = true, foreignKey = @ForeignKey(name = "FK_DANHSACHLOP_MON_LOP"), referencedColumnName = "ma_lop")
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

}
