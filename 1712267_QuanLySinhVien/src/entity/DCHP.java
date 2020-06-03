package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dieuchinh_hocphan")
public class DCHP {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int yeucau_no;

	@Column(name = "noi_dung")
	private String _noiDung;

	@Column(name = "ma_yeucau")
	private int _mayeuCau;

	@Column(name = "malop_mon")
	private String _malopMon;

	@Column(name = "ma_sinhvien")
	private String _masinhVien;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "malop_mon", insertable = false, updatable = false, nullable = true, foreignKey = @ForeignKey(name = "FK_SINHVIEN_MALOP"), referencedColumnName = "ma_lop")
	private DSL_MON dsl_MON;

	public DCHP() {

	}

	public DCHP(int yeucau_no, String _noiDung, int _mayeuCau, String _malopMon, String _masinhVien) {
		super();
		this.yeucau_no = yeucau_no;
		this._noiDung = _noiDung;
		this._mayeuCau = _mayeuCau;
		this._malopMon = _malopMon;
		this._masinhVien = _masinhVien;
	}

	public int getYeucau_no() {
		return yeucau_no;
	}

	public void setYeucau_no(int yeucau_no) {
		this.yeucau_no = yeucau_no;
	}

	public String get_noiDung() {
		return _noiDung;
	}

	public void set_noiDung(String _noiDung) {
		this._noiDung = _noiDung;
	}

	public int get_mayeuCau() {
		return _mayeuCau;
	}

	public void set_mayeuCau(int _mayeuCau) {
		this._mayeuCau = _mayeuCau;
	}

	public String get_malopMon() {
		return _malopMon;
	}

	public void set_malopMon(String _malopMon) {
		this._malopMon = _malopMon;
	}

	public String get_masinhVien() {
		return _masinhVien;
	}

	public void set_masinhVien(String _masinhVien) {
		this._masinhVien = _masinhVien;
	}

	public DSL_MON getDsl_MON() {
		return dsl_MON;
	}

	public void setDsl_MON(DSL_MON dsl_MON) {
		this.dsl_MON = dsl_MON;
	}

}
