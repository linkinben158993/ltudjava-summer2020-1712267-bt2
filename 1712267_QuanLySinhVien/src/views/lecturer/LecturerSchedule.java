package views.lecturer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.DCHPDao;
import dao.DSLMDao;
import dao.MonDao;
import entity.DCHP;
import entity.DSL_MON;
import entity.GiaoVu;
import entity.Mon;
import util.FileParser;
import views.GenericStuff;
import views.Login;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class LecturerSchedule extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GenericStuff genericStuff = new GenericStuff();
	private GiaoVu giaoVu;
	private List<Mon> mons = new MonDao().findAll();

	private DefaultTableModel tableModel;
	private JTable tbl_TKB;
	private JTable tbl_DCHP;

	public GiaoVu getGiaoVu() {
		return giaoVu;
	}

	public void setGiaoVu(GiaoVu giaoVu) {
		this.giaoVu = giaoVu;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LecturerSchedule frame = new LecturerSchedule(new GiaoVu());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LecturerSchedule(GiaoVu giaoVu) {
		this.giaoVu = giaoVu;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelImport = new JPanel();
		panelImport.setLayout(null);
		panelImport.setBackground(Color.WHITE);
		panelImport.setBounds(10, 5, 100, 110);
		contentPane.add(panelImport);
		JLabel lblNhpFile = new JLabel("Nhập File CSV", SwingConstants.CENTER);
		lblNhpFile.setForeground(Color.BLACK);
		lblNhpFile.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNhpFile.setBounds(0, 86, 100, 14);
		panelImport.add(lblNhpFile);
		JLabel lblIconImport = new JLabel();
		lblIconImport.setBounds(10, 5, 80, 80);
		ImageIcon imgIcon_Import = new ImageIcon(Login.class.getResource("/resources/images/Import.png"));
		Image image_Import = imgIcon_Import.getImage();
		Image newImage_Import = image_Import.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblIconImport.setIcon(new ImageIcon(newImage_Import));
		lblIconImport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();

				JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma-separated Values", "csv");
				fileChooser.setFileFilter(filter);
				fileChooser.setCurrentDirectory(new File("."));
				fileChooser.setDialogTitle("Chọn File CSV Để Thêm Mới Sinh Viên");

				int results = fileChooser.showSaveDialog(null);

				if (results == JFileChooser.APPROVE_OPTION) {
					FileParser fileParser = new FileParser();
					List<Mon> mons = fileParser.readFromCSV_Mon(fileChooser.getSelectedFile().getAbsolutePath());
					if (mons.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Nhập file môn học thất bại!");
						LecturerSchedule lecturerSchedule = new LecturerSchedule(giaoVu);
						genericStuff.call_frame(lecturerSchedule);
					} else {
						JOptionPane.showMessageDialog(null, "Nhập file môn học thành công!");
						LecturerSchedule lecturerSchedule = new LecturerSchedule(giaoVu);
						genericStuff.call_frame(lecturerSchedule);
					}
				} else {
					LecturerSchedule lecturerSchedule = new LecturerSchedule(giaoVu);
					genericStuff.call_frame(lecturerSchedule);
				}

			}
		});
		genericStuff.hover(lblIconImport, lblNhpFile, panelImport, new Color(230, 230, 250), Color.LIGHT_GRAY,
				Color.BLACK, Color.WHITE);
		panelImport.add(lblIconImport);

		JPanel panel_QuayLai = new JPanel();
		panel_QuayLai.setLayout(null);
		panel_QuayLai.setBackground(Color.WHITE);
		panel_QuayLai.setBounds(10, 126, 100, 110);
		contentPane.add(panel_QuayLai);

		JLabel lblQuayLi = new JLabel("Quay Lại", SwingConstants.CENTER);
		lblQuayLi.setForeground(Color.BLACK);
		lblQuayLi.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblQuayLi.setBounds(0, 92, 100, 14);
		panel_QuayLai.add(lblQuayLi);

		JLabel lblIconBack = new JLabel();
		lblIconBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				LecturerDashBoard lecturerDashBoard = new LecturerDashBoard(getGiaoVu());
				genericStuff.call_frame(lecturerDashBoard);
			}
		});
		lblIconBack.setBounds(10, 11, 80, 80);
		ImageIcon imgIcon_Back = new ImageIcon(LecturerSchedule.class.getResource("/resources/images/Back.png"));
		Image image_Back = imgIcon_Back.getImage();
		Image newImage_Back = image_Back.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblIconBack.setIcon(new ImageIcon(newImage_Back));
		genericStuff.hover(lblIconBack, lblQuayLi, panel_QuayLai, new Color(230, 230, 250), Color.LIGHT_GRAY,
				Color.BLACK, Color.WHITE);
		panel_QuayLai.add(lblIconBack);

		JScrollPane scrollPane_TKB = new JScrollPane();
		scrollPane_TKB.setBounds(120, 5, 300, 230);
		contentPane.add(scrollPane_TKB);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		tbl_TKB = new JTable();
		tbl_TKB.setModel(draw_TKB(new DSLMDao().findAll()));
		reSizeTable(tbl_TKB, centerRenderer);

		scrollPane_TKB.setViewportView(tbl_TKB);

		JScrollPane scrollPane_DCHP = new JScrollPane();
		scrollPane_DCHP.setBounds(430, 6, 300, 230);
		contentPane.add(scrollPane_DCHP);

		tbl_DCHP = new JTable();
		tbl_DCHP.setModel(draw_DCHP(new DCHPDao().findAll()));
		reSizeTable(tbl_DCHP, centerRenderer);
		scrollPane_DCHP.setViewportView(tbl_DCHP);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DCHPDao dchpDao = new DCHPDao();
				List<DCHP> dchps = dchpDao.findAll();
				for (DCHP dchp : dchps) {
					System.out.println(dchp.get_noiDung());
				}
				int[] selected = tbl_DCHP.getSelectedRows();
				if (selected.length == 0) {
					JOptionPane.showMessageDialog(null, "Chưa chọn yêu cầu!");
				} else {
					for (int i : selected) {
						System.out.println(tbl_DCHP.getModel().getValueAt(i, 0));
						System.out.println(tbl_DCHP.getModel().getValueAt(i, 1));
						System.out.println(tbl_DCHP.getModel().getValueAt(i, 2));
						System.out.println(tbl_DCHP.getModel().getValueAt(i, 3));
						System.out.println(tbl_DCHP.getModel().getValueAt(i, 4));
					}
				}
			}
		});
		btnNewButton.setBounds(119, 246, 89, 23);
		contentPane.add(btnNewButton);

	}

	private DefaultTableModel draw_TKB(List<DSL_MON> dsl_MONs) {
		String[] columns = { "STT", "Mã Môn", "Mã Lớp", "Phòng Học", "Lớp Môn" };
		tableModel = new DefaultTableModel(columns, 0);
		int i = 0;
		for (DSL_MON item : dsl_MONs) {
			i++;
			String[] data = { String.valueOf(i), item.get_maMon(), item.get_maLop(), item.get_phongHoc(),
					item.getMalop_mon() };
			tableModel.addRow(data);
		}
		return tableModel;
	}

	private DefaultTableModel draw_DCHP(List<DCHP> dchps) {
		String[] columns = { "STT", "Mã Sinh Viên", "Mã Lớp", "Lớp Môn", "Yêu Cầu" };
		tableModel = new DefaultTableModel(columns, 0);
		int i = 0;
		for (DCHP item : dchps) {
			i++;
			if (item.get_mayeuCau() == 1) {
				String[] data = { String.valueOf(i), item.get_masinhVien(), item.get_malopMon(), "Thêm Mới",
						item.get_noiDung() };
				tableModel.addRow(data);
			} else if (item.get_mayeuCau() == 2) {
				String[] data = { String.valueOf(i), item.get_masinhVien(), item.get_malopMon(), "Xóa",
						item.get_noiDung() };
				tableModel.addRow(data);
			}
		}
		return tableModel;
	}

	private void reSizeTable(JTable jTable, DefaultTableCellRenderer centerRenderer) {
		jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		jTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(120);
		jTable.getColumnModel().getColumn(3).setPreferredWidth(120);
		jTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		jTable.getColumnModel().getColumn(4).setPreferredWidth(180);
	}
}
