package entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity(name = "lop")
public class Lop {
	@Id
	private int _lopNo;
	@NotBlank(message = "Chưa có tên lớp!")
	private String _maLop;
	private String _tenLop;
	private String _mieuTa;

	@OneToMany(mappedBy = "lop", fetch = FetchType.LAZY)
	private List<SinhVien> sinhViens;
	
	public Lop() {

	}

	public Lop(String _maLop, String _tenLop, String _mieuTa) {
		super();
		this._maLop = _maLop;
		this._tenLop = _tenLop;
		this._mieuTa = _mieuTa;
	}

	public int get_lopNo() {
		return _lopNo;
	}

	public void set_lopNo(int _lopNo) {
		this._lopNo = _lopNo;
	}

	public String get_maLop() {
		return _maLop;
	}

	public void set_maLop(String _maLop) {
		this._maLop = _maLop;
	}

	public String get_tenLop() {
		return _tenLop;
	}

	public void set_tenLop(String _tenLop) {
		this._tenLop = _tenLop;
	}

	public String get_mieuTa() {
		return _mieuTa;
	}

	public void set_mieuTa(String _mieuTa) {
		this._mieuTa = _mieuTa;
	}

}
