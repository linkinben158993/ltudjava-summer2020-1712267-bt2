package entity;

import java.io.Serializable;
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
@Table(name = "quyen")
public class Quyen implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_quyen")
	private int _quyenNo;

	@Column(name = "ten_quyen", unique = true)
	private String _tenQuyen;
	@Column(name = "mieu_ta")
	private String _mieuTa;

	@OneToMany(mappedBy = "quyen_sinhvien", fetch = FetchType.LAZY)
	private List<SinhVien> sinhViens;

	@OneToMany(mappedBy = "quyen_giaovu", fetch = FetchType.LAZY)
	private List<GiaoVu> giaoVus;

	public Quyen() {

	}

	public Quyen(int _quyenNo, String _tenQuyen, String _mieuTa) {
		super();
		this._quyenNo = _quyenNo;
		this._tenQuyen = _tenQuyen;
		this._mieuTa = _mieuTa;
	}

	public int get_quyenNo() {
		return _quyenNo;
	}

	public void set_quyenNo(int _quyenNo) {
		this._quyenNo = _quyenNo;
	}

	public String get_tenQuyen() {
		return _tenQuyen;
	}

	public void set_tenQuyen(String _tenQuyen) {
		this._tenQuyen = _tenQuyen;
	}

	public String get_mieuTa() {
		return _mieuTa;
	}

	public void set_mieuTa(String _mieuTa) {
		this._mieuTa = _mieuTa;
	}

	public List<SinhVien> getSinhViens() {
		return sinhViens;
	}

	public void setSinhViens(List<SinhVien> sinhViens) {
		this.sinhViens = sinhViens;
	}

	public List<GiaoVu> getGiaoVus() {
		return giaoVus;
	}

	public void setGiaoVus(List<GiaoVu> giaoVus) {
		this.giaoVus = giaoVus;
	}

}
