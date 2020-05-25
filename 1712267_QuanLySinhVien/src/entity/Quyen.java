package entity;

public class Quyen {
	private int _quyenNo;
	private String _tenQuyen;
	private String _mieuTa;

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
	
	
}
