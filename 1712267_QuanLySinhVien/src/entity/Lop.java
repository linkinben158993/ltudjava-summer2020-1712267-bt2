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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "lop")
public class Lop implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lop_no")
	private int _lopNo;

	@Column(name = "ma_lop", length = 8, unique = true)
	@NotBlank(message = "Chưa có mã lớp!")
	private String _maLop;

	@Column(name = "ten_lop")
	private String _tenLop;
	@Column(name = "mieu_ta")
	private String _mieuTa;

	@OneToMany(mappedBy = "lop_sinhvien", fetch = FetchType.LAZY)
	private List<SinhVien> sinhViens;

	@OneToMany(mappedBy = "lop_mon", fetch = FetchType.LAZY)
	private List<DSL_MON> sachLop_Mons;

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

	public List<SinhVien> getSinhViens() {
		return sinhViens;
	}

	public void setSinhViens(List<SinhVien> sinhViens) {
		this.sinhViens = sinhViens;
	}

	@Transient
	Comparator<Lop> lopComparator = new Comparator<Lop>() {
		@Override
		public int compare(Lop o1, Lop o2) {
			return o1.get_maLop().compareTo(o2.get_maLop());
		}
	};

	public Lop findByML(List<Lop> listLop, Lop lop) {
		Collections.sort(listLop, lopComparator);
		int i = Collections.binarySearch(listLop, lop, lopComparator);

		if (i < 0) {
			return null;
		} else {
			return listLop.get(i);
		}
	}

}
