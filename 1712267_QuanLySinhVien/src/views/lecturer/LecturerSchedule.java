package views.lecturer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import dao.MonDao;
import entity.DSL_MON;
import entity.DSSV_MON;
import entity.Mon;
import entity.SinhVien;
import util.FileParser;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

public class LecturerSchedule extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LecturerSchedule frame = new LecturerSchedule();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LecturerSchedule() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*
				 * MonDao monDao = new MonDao(); List<Mon> mons = monDao.findAll(); for (Mon
				 * item : mons) { System.out.println(item.get_tenMon()); for (DSSV_MON dssv_MON
				 * : item.getDssv_MONs()) { System.out.println(dssv_MON.get_mssv()); } }
				 */

				JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma-separated Values", "csv");
				fileChooser.setFileFilter(filter);
				fileChooser.setCurrentDirectory(new File("."));
				fileChooser.setDialogTitle("Chọn File CSV Để Thêm Mới Sinh Viên");

				int results = fileChooser.showSaveDialog(null);

				if (results == JFileChooser.APPROVE_OPTION) {
					FileParser fileParser = new FileParser();
					fileParser.readFromCSV_Mon(fileChooser.getSelectedFile().getAbsolutePath());
				} else {

				}
				DSL_MON dsl_MON = new DSL_MON();
				dsl_MON.set_maMon("CTT011");
				dsl_MON.set_maLop("17HCB");
				dsl_MON.set_phongHoc("C32");

				MonDao monDao = new MonDao();
				monDao.insert(dsl_MON);
			}
		});
		contentPane.add(btnNewButton, BorderLayout.EAST);
	}

}
