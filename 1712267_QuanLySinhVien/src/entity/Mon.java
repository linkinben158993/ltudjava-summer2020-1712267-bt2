package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mon")
public class Mon implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mon_no")
	private int _monNo;

	@Column(name = "ma_mon")
	private String _maMon;

	@Column(name = "ten_mon")
	private String _tenMon;

	@Column(name = "ngay_mo")
	private Date _ngayMo;

	public int get_monNo() {
		return _monNo;
	}

	public void set_monNo(int _monNo) {
		this._monNo = _monNo;
	}

	public String get_maMon() {
		return _maMon;
	}

	public void set_maMon(String _maMon) {
		this._maMon = _maMon;
	}

	public String get_tenMon() {
		return _tenMon;
	}

	public void set_tenMon(String _tenMon) {
		this._tenMon = _tenMon;
	}

	public Date get_ngayMo() {
		return _ngayMo;
	}

	public void set_ngayMo(Date _ngayMo) {
		this._ngayMo = _ngayMo;
	}

	@OneToMany(mappedBy = "mon_dssinhvien", fetch = FetchType.LAZY)
	private List<DSSV_MON> dssv_MONs;

	public List<DSSV_MON> getDssv_MONs() {
		return dssv_MONs;
	}

	public void setDssv_MONs(List<DSSV_MON> dssv_MONs) {
		this.dssv_MONs = dssv_MONs;
	}

	@OneToMany(mappedBy = "mon_lop", fetch = FetchType.LAZY)
	private List<DSL_MON> dsl_MONs;

	public List<DSL_MON> getDsl_MONs() {
		return dsl_MONs;
	}

	public void setDsl_MONs(List<DSL_MON> dsl_MONs) {
		this.dsl_MONs = dsl_MONs;
	}

}
