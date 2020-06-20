package views;

import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import dao.QuyenDao;
import entity.Quyen;
import entity.SinhVien;

public class GenericStuff extends JFrame {
	private static final long serialVersionUID = 1L;

	public int draggedAtX;
	public int draggedAtY;

	public GenericStuff() {

	}

	// Gọi show JFrame
	public void call_frame(JFrame frame) {
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setVisible(true);
	}

	// Gọi show JDialog
	public void call_dialog(JDialog jDialog) {
		jDialog.setModalityType(ModalityType.APPLICATION_MODAL);
		jDialog.setLocationRelativeTo(null);
		jDialog.setVisible(true);
		jDialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				dispose();
			}
		});
	}

	// Gọi chung nếu muốn sử dụng hover cho nút custom bằng panel
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

	// Gọi chung nếu muốn tạo hộp thoại confirm.
	@SuppressWarnings("static-access")
	public int confirmDialog(String message, String title, String yes_opt, String no_opt, String default_opt) {
		JOptionPane confirm = new JOptionPane();
		int res = confirm.showOptionDialog(null, message, title, JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, new String[] { yes_opt, no_opt }, default_opt);
		dispose();
		return res;
	}

	public JFileChooser chooseFile(String message) {
		JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma-separated Values", "csv");
		fileChooser.setFileFilter(filter);
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setDialogTitle("Chọn File CSV Để Thêm Mới Sinh Viên");
		return fileChooser;
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
