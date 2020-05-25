package entity;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

public class SinhVien {
	private int _svNo;
	private String _mssv;
	private String _ten;
	private String _gioiTinh;
	private String _cmnd;
	private Quyen _quyen;

	@Column(name = "lop_no")
	@NotBlank(message = "Vui lòng chọn mã lớp!")
	private int ma_lop;
	
	
	@ManyToOne()
	@JoinColumn(name = "ma_lop", insertable = false, updatable = false, nullable = true)
	private Lop _lop;

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

	public Quyen get_quyen() {
		return _quyen;
	}

	public void set_quyen(Quyen _quyen) {
		this._quyen = _quyen;
	}
	public int getMa_lop() {
		return ma_lop;
	}

	public void setMa_lop(int ma_lop) {
		this.ma_lop = ma_lop;
	}
	public Lop get_lop() {
		return _lop;
	}

	public void set_lop(Lop _lop) {
		this._lop = _lop;
	}

}
