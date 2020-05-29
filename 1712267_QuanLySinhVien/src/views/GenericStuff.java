package views;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.QuyenDao;
import entity.Quyen;
import entity.SinhVien;

public class GenericStuff extends JFrame {
	private static final long serialVersionUID = 1L;

	public GenericStuff() {

	}

	public void call_frame(JFrame frame) {
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setVisible(true);
	}

	public void hover(JLabel label, JLabel innerlabel, JPanel jPanel, Color colorHoverInner, Color colorHoverPanel,
			Color colorAfterInner, Color colorAfterPanel) {
		label.addMouseListener(new MouseListener() {

			@Override
			public void mouseExited(MouseEvent e) {
				innerlabel.setForeground(colorAfterInner);
				jPanel.setBackground(colorAfterPanel);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				innerlabel.setForeground(colorHoverInner);
				jPanel.setBackground(colorHoverPanel);
			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}
		});
	}

	// Hàm dùng để in để debug thử
	public void printForTestPurpose(List<SinhVien> list_sinhVien) {
		for (SinhVien sv : list_sinhVien) {
			System.out.println("MSSV: " + sv.get_mssv());
			System.out.println("CMND: " + sv.get_cmnd());
			System.out.println("Tên: " + sv.get_ten());
			System.out.println("Giới tính: " + sv.get_gioiTinh());
		}
		try {
			QuyenDao quyenDao = new QuyenDao();
			List<Quyen> list_quyen = quyenDao.findAll();
			for (Quyen item : list_quyen) {
				System.out.println(item.get_tenQuyen());
				for (SinhVien student : item.getSinhViens()) {
					System.out.println(student.get_mssv());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
