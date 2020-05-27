package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "sinhvien")
public class SinhVien {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sinhvien_no")
	private int _svNo;
	@Column(name = "ma_sinhvien")
	private String _mssv;
	@Column(name = "ten_sinhvien")
	private String _ten;
	@Column(name = "gioitinh_sinhvien")
	private String _gioiTinh;
	@Column(name = "cmnd_sinhvien")
	private String _cmnd;

	@Column(name = "ma_quyen")
	@NotBlank(message = "Vui lòng chọn quyền!")
	private int ma_quyen;

	@ManyToOne()
	@JoinColumn(name = "quyen_no", insertable = false, updatable = false, nullable = true)
	private Quyen quyen_sinhvien;

	@Column(name = "malop_sinhvien")
	@NotBlank(message = "Vui lòng chọn mã lớp!")
	private String ma_lop;

	@ManyToOne()
	@JoinColumn(name = "ma_lop", insertable = false, updatable = false, nullable = true)
	private Lop lop_sinhvien;

	public int getMa_quyen() {
		return ma_quyen;
	}

	public void setMa_quyen(int ma_quyen) {
		this.ma_quyen = ma_quyen;
	}

	public SinhVien() {

	}

	public SinhVien(String _mssv, String _ten, String _gioiTinh, String _cmnd) {
		super();
		this._mssv = _mssv;
		this._ten = _ten;
		this._gioiTinh = _gioiTinh;
		this._cmnd = _cmnd;
	}

	public int get_svNo() {
		return _svNo;
	}

	public void set_svNo(int _svNo) {
		this._svNo = _svNo;
	}

	public String get_mssv() {
		return _mssv;
	}

	public void set_mssv(String _mssv) {
		this._mssv = _mssv;
	}

	public String get_ten() {
		return _ten;
	}

	public void set_ten(String _ten) {
		this._ten = _ten;
	}

	public String get_gioiTinh() {
		return _gioiTinh;
	}

	public void set_gioiTinh(String _gioiTinh) {
		this._gioiTinh = _gioiTinh;
	}

	public String get_cmnd() {
		return _cmnd;
	}

	public void set_cmnd(String _cmnd) {
		this._cmnd = _cmnd;
	}

	public Quyen get_quyen_sinhvien() {
		return quyen_sinhvien;
	}

	public void set_quyen_sinhvien(Quyen quyen_sinhvien) {
		this.quyen_sinhvien = quyen_sinhvien;
	}

	public String getMa_lop() {
		return ma_lop;
	}

	public void setMa_lop(String ma_lop) {
		this.ma_lop = ma_lop;
	}

	public Lop get_lop_sinhvien() {
		return lop_sinhvien;
	}

	public void set_lop_sinhvien(Lop lop_sinhvien) {
		this.lop_sinhvien = lop_sinhvien;
	}

}
