package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phuc_khao")
public class PhucKhao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "phuckhao_no")
	private int phuckhao_no;

	public int getPhuckhao_no() {
		return phuckhao_no;
	}

	public void setPhuckhao_no(int phuckhao_no) {
		this.phuckhao_no = phuckhao_no;
	}

	@Column(name = "ma_sinhvien")
	private String ma_sinhVien;

	public String getMa_sinhVien() {
		return ma_sinhVien;
	}

	public void setMa_sinhVien(String ma_sinhVien) {
		this.ma_sinhVien = ma_sinhVien;
	}

	@Column(name = "malop_mon")
	private String malop_mon;

	public String getMalop_mon() {
		return malop_mon;
	}

	public void setMalop_mon(String malop_mon) {
		this.malop_mon = malop_mon;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "ma_sinhvien", insertable = false, updatable = false, nullable = true, referencedColumnName = "ma_sinhvien"),
			@JoinColumn(name = "malop_mon", insertable = false, updatable = false, nullable = true, referencedColumnName = "malop_mon") })
	private DSSV_MON dssv_MON;

	public DSSV_MON getDssv_MON() {
		return dssv_MON;
	}

	public void setDssv_MON(DSSV_MON dssv_MON) {
		this.dssv_MON = dssv_MON;
	}

	@Column(name = "noidung_phuckhao")
	private String noidung;

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	@Column(name = "trang_thai")
	private int trangthai;

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}

	public PhucKhao() {

	}

	public PhucKhao(int phuckhao_no, String ma_sinhVien, String malop_mon, DSSV_MON dssv_MON, String noidung,
			int trangthai) {
		super();
		this.phuckhao_no = phuckhao_no;
		this.ma_sinhVien = ma_sinhVien;
		this.malop_mon = malop_mon;
		this.dssv_MON = dssv_MON;
		this.noidung = noidung;
		this.trangthai = trangthai;
	}

}
