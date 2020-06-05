package entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "giaovu")
public class GiaoVu implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "giaovu_no")
	private int _gvNo;

	@Column(name = "ma_giaovu")
	private String _msgv;
	@ColumnDefault(value = "cmnd_giaovu")
	@Column(name = "password_giaovu")
	private String _password;

	@Column(name = "ten_giaovu")
	private String _ten;
	@Column(name = "gioitinh_giaovu")
	private String _gioiTinh;
	@Column(name = "cmnd_giaovu")
	private String _cmnd;

	@Column(name = "ma_quyen")
	@NotBlank(message = "Vui lòng chọn quyền!")
	private int ma_quyen;

	public int getMa_quyen() {
		return ma_quyen;
	}

	public void setMa_quyen(int ma_quyen) {
		this.ma_quyen = ma_quyen;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ma_quyen", insertable = false, updatable = false, nullable = true)
	private Quyen quyen_giaovu;

	public void setQuyen_giaovu(Quyen quyen_giaovu) {
		this.quyen_giaovu = quyen_giaovu;
	}

	public Quyen getQuyen_giaovu() {
		return quyen_giaovu;
	}

	public GiaoVu() {

	}

	public GiaoVu(int _gvNo, String _msgv, String _password, String _ten, String _gioiTinh, String _cmnd,
			@NotBlank(message = "Vui lòng chọn quyền!") int ma_quyen, Quyen quyen_sinhvien) {
		super();
		this._gvNo = _gvNo;
		this._msgv = _msgv;
		this._password = _password;
		this._ten = _ten;
		this._gioiTinh = _gioiTinh;
		this._cmnd = _cmnd;
		this.ma_quyen = ma_quyen;
		this.quyen_giaovu = quyen_sinhvien;
	}

	public GiaoVu(String _msgv, String _password, String _ten, String _gioiTinh, String _cmnd,
			@NotBlank(message = "Vui lòng chọn quyền!") int ma_quyen) {
		super();
		this._msgv = _msgv;
		this._password = _password;
		this._ten = _ten;
		this._gioiTinh = _gioiTinh;
		this._cmnd = _cmnd;
		this.ma_quyen = ma_quyen;
	}

	public int get_gvNo() {
		return _gvNo;
	}

	public void set_gvNo(int _gvNo) {
		this._gvNo = _gvNo;
	}

	public String get_msgv() {
		return _msgv;
	}

	public void set_msgv(String _msgv) {
		this._msgv = _msgv;
	}

	public String get_password() {
		return _password;
	}

	public void set_password(String _password) {
		this._password = _password;
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

	@Transient
	Comparator<GiaoVu> giaoVuComparator = new Comparator<GiaoVu>() {
		@Override
		public int compare(GiaoVu o1, GiaoVu o2) {
			return o1.get_msgv().compareTo(o2.get_msgv());
		}
	};

	public GiaoVu findByMSGV(List<GiaoVu> listGV, GiaoVu gv) {
		Collections.sort(listGV, giaoVuComparator);
		int i = Collections.binarySearch(listGV, gv, giaoVuComparator);

		if (i < 0) {
			return null;
		} else {
			return listGV.get(i);
		}
	}
}
