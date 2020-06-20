package entity;

import java.io.Serializable;
import java.sql.Timestamp;
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

@Entity
@Table(name = "dot")
public class Dot implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_dot")
	private int ma_dot;

	@Column(name = "phuckhao_ngaymo")
	private Timestamp ngay_mo;

	@Column(name = "phuckhao_ngaydong")
	private Timestamp ngay_dong;

	public int getMa_dot() {
		return ma_dot;
	}

	public void setMa_dot(int ma_dot) {
		this.ma_dot = ma_dot;
	}

	public Timestamp getNgay_mo() {
		return ngay_mo;
	}

	public void setNgay_mo(Timestamp ngay_mo) {
		this.ngay_mo = ngay_mo;
	}

	public Timestamp getNgay_dong() {
		return ngay_dong;
	}

	public void setNgay_dong(Timestamp ngay_dong) {
		this.ngay_dong = ngay_dong;
	}

	@OneToMany(mappedBy = "dot_phuckhao", fetch = FetchType.LAZY)
	private List<PhucKhao> phucKhaos;

	public List<PhucKhao> getPhucKhaos() {
		return phucKhaos;
	}

	public void setPhucKhaos(List<PhucKhao> phucKhaos) {
		this.phucKhaos = phucKhaos;
	}

	@Transient
	Comparator<Dot> sinhVienComparator = new Comparator<Dot>() {
		public int compare(Dot o1, Dot o2) {
			return Integer.compare(o1.getMa_dot(), o2.getMa_dot());
		}
	};

	
	public Dot findByMaDot(List<Dot> listDot, Dot dot) {
		Collections.sort(listDot, sinhVienComparator);
		int i = Collections.binarySearch(listDot, dot, sinhVienComparator);

		if (i < 0) {
			return null;
		} else {
			return listDot.get(i);
		}
	}
}
