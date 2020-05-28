package entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "sinhvien")
public class SinhVien implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sinhvien_no")
	private int _svNo;

	@Column(name = "ma_sinhvien")
	private String _mssv;
	@ColumnDefault(value = "cmnd_sinhvien")
	@Column(name = "password_sinhvien")
	private String _password;

	@Column(name = "ten_sinhvien")
	private String _ten;
	@Column(name = "gioitinh_sinhvien")
	private String _gioiTinh;
	@Column(name = "cmnd_sinhvien")
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
	@JoinColumn(name = "ma_quyen", insertable = false, updatable = false, nullable = true, foreignKey = @ForeignKey(name = "FK_SINHVIEN_QUYEN"))
	private Quyen quyen_sinhvien;

	public Quyen getQuyen_sinhvien() {
		return quyen_sinhvien;
	}

	public void setQuyen_sinhvien(Quyen quyen_sinhvien) {
		this.quyen_sinhvien = quyen_sinhvien;
	}

	@Column(name = "malop_sinhvien", length = 8)
	@NotBlank(message = "Vui lòng chọn mã lớp!")
	private String ma_lop;

	public String getMa_lop() {
		return ma_lop;
	}

	public void setMa_lop(String ma_lop) {
		this.ma_lop = ma_lop;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "malop_sinhvien", insertable = false, updatable = false, nullable = true, foreignKey = @ForeignKey(name = "FK_SINHVIEN_MALOP"), referencedColumnName = "ma_lop")
	private Lop lop_sinhvien;

	public Lop getLop_sinhvien() {
		return lop_sinhvien;
	}

	public void setLop_sinhvien(Lop lop_sinhvien) {
		this.lop_sinhvien = lop_sinhvien;
	}

	public SinhVien() {

	}

	public SinhVien(int _svNo, String _mssv, String _password, String _ten, String _gioiTinh, String _cmnd,
			@NotBlank(message = "Vui lòng chọn quyền!") int ma_quyen, Quyen quyen_sinhvien,
			@NotBlank(message = "Vui lòng chọn mã lớp!") String ma_lop, Lop lop_sinhvien) {
		super();
		this._svNo = _svNo;
		this._mssv = _mssv;
		this._password = _password;
		this._ten = _ten;
		this._gioiTinh = _gioiTinh;
		this._cmnd = _cmnd;
		this.ma_quyen = ma_quyen;
		this.quyen_sinhvien = quyen_sinhvien;
		this.ma_lop = ma_lop;
		this.lop_sinhvien = lop_sinhvien;
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

	public String get_password() {
		return _password;
	}

	public void set_password(String _password) {
		this._password = _password;
	}

	@Transient
	Comparator<SinhVien> sinhVienComparator = new Comparator<SinhVien>() {
		public int compare(SinhVien o1, SinhVien o2) {
			return o1.get_mssv().compareTo(o2.get_mssv());
		}
	};

	public SinhVien findByMSSV(List<SinhVien> listSV, SinhVien sv) {
		Collections.sort(listSV, sinhVienComparator);
		int i = Collections.binarySearch(listSV, sv, sinhVienComparator);

		if (i < 0) {
			return null;
		} else {
			return listSV.get(i);
		}
	}
}
