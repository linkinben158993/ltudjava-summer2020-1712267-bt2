package views.lecturer;

import java.awt.EventQueue;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.DSLMDao;
import dao.DiemDao;
import entity.DSL_MON;
import entity.Diem;
import entity.GiaoVu;
import util.FileParser;
import views.GenericStuff;
import views.Login;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class LecturerGrades extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GenericStuff genericStuff = new GenericStuff();

	private GiaoVu giaoVu;

	public int draggedAtX;
	public int draggedAtY;

	private List<Diem> diems = new DiemDao().findAll();
	private JTable table_Diem;
	private DefaultTableModel tableModel_Diem;
	private JTable table_ThongKe;
	private DefaultTableModel tableModel_ThongKe;
	private JTable table_PhanTram;
	private DefaultTableModel tableModel_PhanTram;

	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_ClassFilter;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_Stats;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_Percentage;

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
					LecturerGrades frame = new LecturerGrades(new GiaoVu());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LecturerGrades(GiaoVu giaoVu) {
		this.giaoVu = giaoVu;

		init();

		// Lấy vị trí hiện tại của con trỏ JFrame
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				draggedAtX = e.getX();
				draggedAtY = e.getY();
			}
		});
		// Kéo thả Jpanel khi đã có vị trí hiện tại của con JFrame e.getXOnScreen()
		// e.getYOnScreen() trên màn hình
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				// Set location cho JFrame dựa trên vị trí con trỏ chuột đang nằm ở JFrame và vị
				// trí của JFrame trên màn hình
				setLocation(x - draggedAtX, y - draggedAtY);
			}
		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void event_listener() {
		JPanel panelImport = new JPanel();
		panelImport.setLayout(null);
		panelImport.setBackground(Color.WHITE);
		panelImport.setBounds(10, 151, 100, 110);
		contentPane.add(panelImport);
		JLabel lblNhpFile = new JLabel("Nhập File CSV", SwingConstants.CENTER);
		lblNhpFile.setForeground(Color.BLACK);
		lblNhpFile.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNhpFile.setBounds(0, 86, 100, 14);
		panelImport.add(lblNhpFile);
		JLabel lblIconImport = new JLabel();
		lblIconImport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();

				FileParser fileParser = new FileParser();

				JFileChooser jFileChooser = genericStuff.chooseFile("Chọn File CSV Để Thêm Điểm!");
				int result = jFileChooser.showSaveDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {
					List<Diem> diems = fileParser.readFromCSV_Lop(jFileChooser.getSelectedFile().getAbsolutePath());

					if (diems.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Nhập điểm thất bại!");
						LecturerGrades lecturerGrades = new LecturerGrades(giaoVu);
						genericStuff.call_frame(lecturerGrades);
					} else {
						JOptionPane.showMessageDialog(null, "Nhập điểm thành công!");
						LecturerGrades lecturerGrades = new LecturerGrades(giaoVu);
						genericStuff.call_frame(lecturerGrades);
					}
				} else {
					LecturerGrades lecturerGrades = new LecturerGrades(giaoVu);
					genericStuff.call_frame(lecturerGrades);
				}
			}
		});
		lblIconImport.setBounds(10, 5, 80, 80);
		ImageIcon imgIcon_Import = new ImageIcon(Login.class.getResource("/resources/images/Import.png"));
		Image image_Import = imgIcon_Import.getImage();
		Image newImage_Import = image_Import.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblIconImport.setIcon(new ImageIcon(newImage_Import));
		genericStuff.hover(lblIconImport, lblNhpFile, panelImport, new Color(230, 230, 250), Color.LIGHT_GRAY,
				Color.BLACK, Color.WHITE);
		panelImport.add(lblIconImport);

		JPanel panel_QuayLai = new JPanel();
		panel_QuayLai.setLayout(null);
		panel_QuayLai.setBackground(Color.WHITE);
		panel_QuayLai.setBounds(800, 490, 100, 110);
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

		List<DSL_MON> dsl_MONs = new DSLMDao().findAll();
		String[] option_lopMon = new String[dsl_MONs.size() + 1];
		option_lopMon[0] = "Tất Cả";
		for (int i = 1; i < option_lopMon.length; ++i) {
			option_lopMon[i] = dsl_MONs.get(i - 1).getMalop_mon();
		}
		ComboBoxModel boxModel_Class = new DefaultComboBoxModel(option_lopMon);
		ComboBoxModel boxModel_Stats = new DefaultComboBoxModel(option_lopMon);
		ComboBoxModel boxModel_Percent = new DefaultComboBoxModel(option_lopMon);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		JLabel lblBangDiem = new JLabel("Bảng Điểm:");
		lblBangDiem.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblBangDiem.setBounds(10, 12, 100, 30);
		contentPane.add(lblBangDiem);
		JLabel lblClassFilter = new JLabel("Lọc theo lớp:");
		lblClassFilter.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblClassFilter.setBounds(10, 53, 100, 14);
		contentPane.add(lblClassFilter);
		comboBox_ClassFilter = new JComboBox(boxModel_Class);
		comboBox_ClassFilter.setSelectedItem(null);
		comboBox_ClassFilter.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (comboBox_ClassFilter.getSelectedItem().toString().equals("Tất Cả")) {
					table_Diem.setModel(drawTable_Diem(diems));
					resizeTableDiem(table_Diem, centerRenderer);
				} else {
					table_Diem.setModel(drawTable_Diem(
							new Diem().findByML_Mon(diems, comboBox_ClassFilter.getSelectedItem().toString())));
					resizeTableDiem(table_Diem, centerRenderer);
				}
			}
		});
		comboBox_ClassFilter.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBox_ClassFilter.setMaximumRowCount(3);
		comboBox_ClassFilter.setBounds(10, 78, 100, 20);
		contentPane.add(comboBox_ClassFilter);
		JScrollPane scrollPane_Diem = new JScrollPane();
		scrollPane_Diem.setBounds(120, 11, 760, 250);
		contentPane.add(scrollPane_Diem);
		table_Diem = new JTable();
		table_Diem.setModel(drawTable_Diem(diems));
		scrollPane_Diem.setViewportView(table_Diem);
		resizeTableDiem(table_Diem, centerRenderer);
		JSeparator separator_Grades = new JSeparator();
		separator_Grades.setBounds(10, 11, 100, 2);
		contentPane.add(separator_Grades);

		JLabel lblThongKe = new JLabel("Thống Kê:");
		lblThongKe.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblThongKe.setBounds(10, 272, 100, 30);
		contentPane.add(lblThongKe);
		JLabel lblStatsFilter = new JLabel("Lọc theo lớp:");
		lblStatsFilter.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblStatsFilter.setBounds(10, 313, 100, 14);
		contentPane.add(lblStatsFilter);
		comboBox_Stats = new JComboBox(boxModel_Stats);
		comboBox_Stats.setSelectedItem(null);
		comboBox_Stats.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBox_Stats.getSelectedItem().toString().equals("Tất Cả")) {
					table_ThongKe.setModel(drawTable_ThongKe(diems));
					resizeTableThongKe(table_ThongKe, centerRenderer);
				} else {
					table_ThongKe.setModel(drawTable_ThongKe(
							new Diem().findByML_Mon(diems, comboBox_Stats.getSelectedItem().toString())));
					resizeTableThongKe(table_ThongKe, centerRenderer);
				}
			}
		});
		comboBox_Stats.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBox_Stats.setMaximumRowCount(3);
		comboBox_Stats.setBounds(10, 338, 100, 20);
		contentPane.add(comboBox_Stats);
		JScrollPane scrollPane_Thongke = new JScrollPane();
		scrollPane_Thongke.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_Thongke.setBounds(120, 272, 450, 250);
		contentPane.add(scrollPane_Thongke);
		table_ThongKe = new JTable();
		table_ThongKe.setModel(drawTable_ThongKe(diems));
		resizeTableThongKe(table_ThongKe, centerRenderer);
		scrollPane_Thongke.setViewportView(table_ThongKe);

		JSeparator separator_Stats = new JSeparator();
		separator_Stats.setBounds(10, 272, 100, 2);
		contentPane.add(separator_Stats);

		JLabel lblPercentage = new JLabel("Lọc theo lớp:");
		lblPercentage.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblPercentage.setBounds(580, 458, 100, 14);
		contentPane.add(lblPercentage);
		comboBox_Percentage = new JComboBox(boxModel_Percent);
		comboBox_Percentage.setSelectedItem(null);
		comboBox_Percentage.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (comboBox_Percentage.getSelectedItem().toString().equals("Tất Cả")) {
					table_PhanTram.setModel(drawTable_ThongKePhanTram(diems));
					resizeTablePhanTram(table_PhanTram, centerRenderer);

				} else {
					table_PhanTram.setModel(drawTable_ThongKePhanTram(
							new Diem().findByML_Mon(diems, comboBox_Percentage.getSelectedItem().toString())));
					resizeTablePhanTram(table_PhanTram, centerRenderer);
				}
			}
		});
		comboBox_Percentage.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBox_Percentage.setMaximumRowCount(3);
		comboBox_Percentage.setBounds(580, 483, 100, 20);
		contentPane.add(comboBox_Percentage);
		JScrollPane scrollPane_PhanTram = new JScrollPane();
		scrollPane_PhanTram.setBounds(580, 272, 300, 175);
		contentPane.add(scrollPane_PhanTram);
		table_PhanTram = new JTable();
		table_PhanTram.setModel(drawTable_ThongKePhanTram(diems));
		scrollPane_PhanTram.setViewportView(table_PhanTram);
		resizeTablePhanTram(table_PhanTram, centerRenderer);
		JSeparator separator_Cred = new JSeparator();
		separator_Cred.setBounds(291, 580, 330, 2);
		contentPane.add(separator_Cred);

		JLabel lblLtudJava = new JLabel("2020 LTUD Java - 1712267 Nguyễn Hoàng Thiên Ân");
		lblLtudJava.setHorizontalAlignment(SwingConstants.CENTER);
		lblLtudJava.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLtudJava.setBounds(291, 580, 330, 14);
		contentPane.add(lblLtudJava);

		JPanel panelPhucKhao = new JPanel();
		panelPhucKhao.setLayout(null);
		panelPhucKhao.setBackground(Color.WHITE);
		panelPhucKhao.setBounds(10, 412, 100, 110);
		contentPane.add(panelPhucKhao);
		JLabel lblPhcKho = new JLabel("Phúc Khảo", SwingConstants.CENTER);
		lblPhcKho.setForeground(Color.BLACK);
		lblPhcKho.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPhcKho.setBounds(0, 92, 100, 14);
		panelPhucKhao.add(lblPhcKho);
		JLabel lblIconPhucKhao = new JLabel();
		lblIconPhucKhao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		lblIconPhucKhao.setBounds(10, 11, 80, 80);
		ImageIcon imgIcon_PhucKhao = new ImageIcon(
				LecturerSchedule.class.getResource("/resources/images/Messages.png"));
		Image image_PhucKhao = imgIcon_PhucKhao.getImage();
		Image newImage_PhucKhao = image_PhucKhao.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblIconPhucKhao.setIcon(new ImageIcon(newImage_PhucKhao));
		genericStuff.hover(lblIconPhucKhao, lblPhcKho, panelPhucKhao, new Color(230, 230, 250), Color.LIGHT_GRAY,
				Color.BLACK, Color.WHITE);
		panelPhucKhao.add(lblIconPhucKhao);

	}

	private void init() {
		setGiaoVu(giaoVu);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		event_listener();
	}

	private DefaultTableModel drawTable_Diem(List<Diem> diems) {
		if (diems.isEmpty()) {
			String[] columns = { "STT", "Mã Sinh Viên", "Họ Tên", "Mã Lớp", "Lớp Môn", "Giữa Kì", "Cuối Kì", "Khác",
					"Tổng" };
			tableModel_Diem = new DefaultTableModel(columns, 0);
			tableModel_Diem.addRow(
					new String[] { "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A" });
		} else {
			String[] columns = { "STT", "Mã Sinh Viên", "Họ Tên", "Mã Lớp", "Lớp Môn", "Giữa Kì", "Cuối Kì", "Khác",
					"Tổng" };
			tableModel_Diem = new DefaultTableModel(columns, 0);
			int i = 0;

			for (Diem diem : diems) {
				i++;
				String[] data = { String.valueOf(i), diem.get_mssv(), diem.get_tenSinhVien(),
						diem.getSinhVien().getMa_lop(), diem.getMaLop_mon(), String.valueOf(diem.get_gk()),
						String.valueOf(diem.get_ck()), String.valueOf(diem.get_khac()),
						String.valueOf(diem.get_tongDiem()) };
				tableModel_Diem.addRow(data);
			}
		}
		return tableModel_Diem;
	}

	private DefaultTableModel drawTable_ThongKe(List<Diem> diems) {
		if (diems.isEmpty()) {
			String[] columns = { "STT", "Mã Sinh Viên", "Họ Tên", "Mã Lớp", "Lớp Môn", "Tổng", "Tình Trạng" };
			tableModel_ThongKe = new DefaultTableModel(columns, 0);
			tableModel_ThongKe.addRow(new String[] { "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A" });
		} else {
			String[] columns = { "STT", "Mã Sinh Viên", "Họ Tên", "Mã Lớp", "Lớp Môn", "Tổng", "Tình Trạng" };
			tableModel_ThongKe = new DefaultTableModel(columns, 0);
			int i = 0;

			for (Diem diem : diems) {
				i++;
				System.out.println(diem.getMaLop_mon());
				if (diem.get_tongDiem() < 4.75) {
					String[] data = { String.valueOf(i), diem.get_mssv(), diem.get_tenSinhVien(),
							diem.getSinhVien().getMa_lop(), diem.getMaLop_mon(), String.valueOf(diem.get_tongDiem()),
							"Rớt Môn" };
					tableModel_ThongKe.addRow(data);
				} else {
					String[] data = { String.valueOf(i), diem.get_mssv(), diem.get_tenSinhVien(),
							diem.getSinhVien().getMa_lop(), diem.getMaLop_mon(), String.valueOf(diem.get_tongDiem()),
							"Qua Môn" };
					tableModel_ThongKe.addRow(data);
				}
			}
		}

		return tableModel_ThongKe;
	}

	@SuppressWarnings("static-access")
	private DefaultTableModel drawTable_ThongKePhanTram(List<Diem> diems) {
		if (diems.isEmpty()) {
			String[] columns = { "STT", "Lớp", "Đậu", "Rớt" };
			tableModel_PhanTram = new DefaultTableModel(columns, 0);
			tableModel_PhanTram.addRow(new String[] { "N/A", "N/A", "N/A", "N/A" });
		} else {
			int _rotCount = 0;
			int _dauCount = 0;
			for (Diem diem : diems) {
				if (diem.get_ck() < 4.75) {
					_rotCount++;
				} else {
					_dauCount++;
				}
			}
			float _rot = 100 * (float) _rotCount / diems.size();
			float _dau = 100 * (float) _dauCount / diems.size();

			String[] columns = { "STT", "Lớp", "Đậu", "Rớt" };
			tableModel_PhanTram = new DefaultTableModel(columns, 0);
			int i = 1;
			tableModel_PhanTram.addRow(
					new String[] { String.valueOf(i), "Tất Cả", String.valueOf(_dau * 100).format("%.1f", _dau) + "%",
							String.valueOf(_rot * 100).format("%.1f", _rot) + "%" });

			_rotCount = 0;
			_dauCount = 0;
			List<DSL_MON> dsl_MONs = new DSLMDao().findAll();
			for (DSL_MON dsl_MON : dsl_MONs) {
				int _lopCount = 0;
				for (Diem diem : diems) {
					if (diem.getMaLop_mon().equals(dsl_MON.getMalop_mon())) {
						_lopCount++;
						if (diem.get_ck() < 4.75) {
							_rotCount++;
						} else {
							_dauCount++;
						}
					}
				}
				if (_lopCount == 0) {
					continue;
				} else {
					_rot = 100 * (float) _rotCount / _lopCount;
					_dau = 100 * (float) _dauCount / _lopCount;
					tableModel_PhanTram.addRow(new String[] { String.valueOf(++i), dsl_MON.getMalop_mon(),
							String.valueOf(_dau).format("%.1f", _dau) + "%",
							String.valueOf(_rot).format("%.1f", _rot) + "%" });
				}
			}
		}

		return tableModel_PhanTram;
	}

	private void resizeTableDiem(JTable jTable, DefaultTableCellRenderer centerRenderer) {
		jTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		jTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(80);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		jTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		jTable.getColumnModel().getColumn(4).setPreferredWidth(150);
		jTable.getColumnModel().getColumn(5).setPreferredWidth(60);
		jTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		jTable.getColumnModel().getColumn(6).setPreferredWidth(60);
		jTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		jTable.getColumnModel().getColumn(7).setPreferredWidth(60);
		jTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
		jTable.getColumnModel().getColumn(8).setPreferredWidth(60);
		jTable.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
	}

	private void resizeTableThongKe(JTable jTable, DefaultTableCellRenderer centerRenderer) {
		jTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		jTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(120);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		jTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		jTable.getColumnModel().getColumn(4).setPreferredWidth(150);
		jTable.getColumnModel().getColumn(5).setPreferredWidth(40);
		jTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		jTable.getColumnModel().getColumn(6).setPreferredWidth(100);
	}

	private void resizeTablePhanTram(JTable jTable, DefaultTableCellRenderer centerRenderer) {
		jTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		jTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(100);
	}
}
