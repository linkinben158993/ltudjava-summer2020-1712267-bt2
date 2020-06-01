package entity;

import java.io.Serializable;

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

	@Column(name = "ma_mon")
	private String _maMon;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ma_mon", insertable = false, updatable = false, nullable = true, foreignKey = @ForeignKey(name = "FK_DANHSACHSINHVIEN_MON"), referencedColumnName = "ma_mon")
	private Mon mon_dssinhvien;
	
	public Mon getMon() {
		return mon_dssinhvien;
	}

	public void setMon(Mon mon_dssinhvien) {
		this.mon_dssinhvien = mon_dssinhvien;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ma_sinhvien", insertable = false, updatable = false, nullable = true, foreignKey = @ForeignKey(name = "FK_DANHSACHSINHVIEN_SINHVIEN"), referencedColumnName = "ma_sinhvien")
	private SinhVien sinhVien;

	public SinhVien getSinhVien() {
		return sinhVien;
	}

	public void setSinhVien(SinhVien sinhVien) {
		this.sinhVien = sinhVien;
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

}
