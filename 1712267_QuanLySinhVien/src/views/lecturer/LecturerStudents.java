package views.lecturer;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.DSLMDao;
import dao.DSSVMDao;
import dao.LopDao;
import dao.SinhVienDao;
import entity.DSL_MON;
import entity.DSSV_MON;
import entity.GiaoVu;
import entity.Lop;
import entity.SinhVien;
import util.FileParser;
import views.GenericStuff;
import views.Login;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class LecturerStudents extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GenericStuff genericStuff = new GenericStuff();

	// �?ang kéo thả tại t�?a độ x y
	public int draggedAtX;
	public int draggedAtY;
	private JTable tableSinhVien;
	private DefaultTableModel tableModel;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_ClassFilter;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_SubjectFilter;
	private final List<SinhVien> sinhViens = new SinhVienDao().findAll();
	private final List<DSSV_MON> dssv_MONs = new DSSVMDao().findAll();
	private JTextField txtSearch;
	private JTextField txtChoMng;
	private GiaoVu giaoVu;
	private JTable table_Subject;
	private JTextField textField;
	private JTextField textField_1;

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
					LecturerStudents frame = new LecturerStudents(new GiaoVu());
					frame.setLocationRelativeTo(null);
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void event_listener() {
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("2019 LTUD Java - 1712267 Nguyễn Hoàng Thiên Ân");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(335, 540, 330, 14);
		contentPane.add(lblNewLabel);
		JPanel SideBar = new JPanel();
		SideBar.setBounds(0, 0, 85, 560);
		SideBar.setBackground(new Color(128, 0, 0));
		contentPane.add(SideBar);

		JPanel SearchBar = new JPanel();
		SearchBar.setBounds(85, 0, 670, 60);
		SearchBar.setBackground(new Color(178, 34, 34));
		contentPane.add(SearchBar);
		SearchBar.setLayout(null);

		// Tìm kiếm
		txtSearch = new JTextField();
		txtSearch.setText("Tìm kiếm sinh viên");
		txtSearch.setBackground(Color.LIGHT_GRAY);
		txtSearch.setBounds(530, 11, 130, 20);
		SearchBar.add(txtSearch);
		txtSearch.setColumns(10);

		txtChoMng = new JTextField();
		txtChoMng.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtChoMng.setEditable(false);
		txtChoMng.setText("Chào mừng " + giaoVu.get_ten());
		txtChoMng.setColumns(10);
		txtChoMng.setBackground(Color.LIGHT_GRAY);
		txtChoMng.setBounds(10, 11, 230, 38);
		SearchBar.add(txtChoMng);

		JPanel MiddleBar = new JPanel();
		MiddleBar.setBounds(85, 60, 240, 500);
		MiddleBar.setBackground(new Color(178, 34, 34));
		contentPane.add(MiddleBar);
		MiddleBar.setLayout(null);

		// Element và sử lý sự kiện của thêm sinh viên
		JPanel panelThem = new JPanel();
		panelThem.setLayout(null);
		panelThem.setBackground(new Color(178, 34, 34));
		panelThem.setBounds(10, 121, 100, 110);
		MiddleBar.add(panelThem);
		JLabel labelThemSinhVien = new JLabel("Thêm Sinh Viên", SwingConstants.CENTER);
		labelThemSinhVien.setForeground(Color.BLACK);
		labelThemSinhVien.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelThemSinhVien.setBounds(0, 86, 100, 14);
		panelThem.add(labelThemSinhVien);
		JLabel lblThem = new JLabel();
		lblThem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Không gọi generic stuff chỗ này cho sử dụng thanh đóng mở của Jframe này
				LecturerAddStudent lecturerAddStudent = new LecturerAddStudent(giaoVu);
				lecturerAddStudent.setLocationRelativeTo(null);
				lecturerAddStudent.setVisible(true);
				dispose();
			}
		});
		lblThem.setBounds(10, 5, 80, 80);
		panelThem.add(lblThem);
		ImageIcon imgIcon_Them = new ImageIcon(Login.class.getResource("/resources/images/Add.png"));
		Image image_Them = imgIcon_Them.getImage();
		Image newImage_Them = image_Them.getScaledInstance(80, 75, java.awt.Image.SCALE_SMOOTH);
		lblThem.setIcon(new ImageIcon(newImage_Them));

		genericStuff.hover(lblThem, labelThemSinhVien, panelThem, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK,
				new Color(178, 34, 34));

		// Element và sử lý sự kiện của sửa sinh viên
		JPanel panelSua = new JPanel();
		panelSua.setLayout(null);
		panelSua.setBackground(new Color(178, 34, 34));
		panelSua.setBounds(140, 242, 100, 110);
		MiddleBar.add(panelSua);
		JLabel lblSaSinhVin = new JLabel("Sửa Sinh Viên", SwingConstants.CENTER);
		lblSaSinhVin.setForeground(Color.BLACK);
		lblSaSinhVin.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblSaSinhVin.setBounds(0, 86, 100, 14);
		panelSua.add(lblSaSinhVin);
		JLabel lblSua = new JLabel();
		lblSua.setBounds(10, 11, 80, 75);
		panelSua.add(lblSua);
		ImageIcon imgIcon_Sua = new ImageIcon(Login.class.getResource("/resources/images/Edit.png"));
		Image image_Sua = imgIcon_Sua.getImage();
		Image newImage_Sua = image_Sua.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblSua.setIcon(new ImageIcon(newImage_Sua));

		genericStuff.hover(lblSua, lblSaSinhVin, panelSua, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK,
				new Color(178, 34, 34));

		// Element và sử lý sự kiện của xóa sinh viên
		JPanel panelXoa = new JPanel();
		panelXoa.setLayout(null);
		panelXoa.setBackground(new Color(178, 34, 34));
		panelXoa.setBounds(10, 242, 100, 110);
		MiddleBar.add(panelXoa);
		JLabel lblXaSinhVin = new JLabel("Xóa Sinh Viên", SwingConstants.CENTER);
		lblXaSinhVin.setForeground(Color.BLACK);
		lblXaSinhVin.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblXaSinhVin.setBounds(0, 86, 100, 14);
		panelXoa.add(lblXaSinhVin);
		JLabel lblXoa = new JLabel();
		lblXoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tableSinhVien.getSelectedRowCount() == 0) {

					JOptionPane.showMessageDialog(null, "Chưa chọn sinh viên nào trên danh sách!");

				} else {

					int cols = tableSinhVien.getColumnCount();
					int rows = tableSinhVien.getSelectedRow();
					for (int i = 0; i < cols; i++) {
						System.out.println(tableSinhVien.getModel().getValueAt(rows, i));
					}
				}
			}
		});
		lblXoa.setBounds(10, 11, 80, 75);
		panelXoa.add(lblXoa);
		ImageIcon imgIcon_Xoa = new ImageIcon(Login.class.getResource("/resources/images/Minus.png"));
		Image image_Xoa = imgIcon_Xoa.getImage();
		Image newImage_Xoa = image_Xoa.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblXoa.setIcon(new ImageIcon(newImage_Xoa));

		genericStuff.hover(lblXoa, lblXaSinhVin, panelXoa, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK,
				new Color(178, 34, 34));

		// Element và sử lý sự kiện của import CSV
		JPanel panelImport = new JPanel();
		panelImport.setLayout(null);
		panelImport.setBackground(new Color(178, 34, 34));
		panelImport.setBounds(140, 121, 100, 110);
		MiddleBar.add(panelImport);
		JLabel lblNhpFileCsv = new JLabel("Nhập File CSV", SwingConstants.CENTER);
		lblNhpFileCsv.setForeground(Color.BLACK);
		lblNhpFileCsv.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNhpFileCsv.setBounds(0, 86, 100, 14);
		panelImport.add(lblNhpFileCsv);
		JLabel lblImport = new JLabel();
		lblImport.setBounds(10, 11, 80, 75);
		panelImport.add(lblImport);
		lblImport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma-separated Values", "csv");
				fileChooser.setFileFilter(filter);
				fileChooser.setCurrentDirectory(new File("."));
				fileChooser.setDialogTitle("Chọn File CSV Để Thêm Mới Sinh Viên");

				int results = fileChooser.showSaveDialog(null);

				if (results == JFileChooser.APPROVE_OPTION) {
					FileParser fileParser = new FileParser();
					// Uncomment khi muốn test sinh viên và quyền được lấy như thế nào
					List<SinhVien> list_sinhVien = fileParser
							.readFromCSV_SinhVien(fileChooser.getSelectedFile().getAbsolutePath());

					// Nếu muốn in thử list sinh viên và lấy sinh viên thuộc quyền...
					// printForTestPurpose(list_sinhVien);
					if (list_sinhVien.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Nhập file sinh viên thất bại!");
						LecturerStudents frame = new LecturerStudents(getGiaoVu());
						genericStuff.call_frame(frame);
					} else {
						JOptionPane.showMessageDialog(null, "Nhập file sinh viên thành công!");
						LecturerStudents frame = new LecturerStudents(getGiaoVu());
						genericStuff.call_frame(frame);
					}

				} else {
					LecturerStudents frame = new LecturerStudents(getGiaoVu());
					genericStuff.call_frame(frame);
				}
			}
		});
		ImageIcon imgIcon_Import = new ImageIcon(Login.class.getResource("/resources/images/Import.png"));
		Image image_Import = imgIcon_Import.getImage();
		Image newImage_Import = image_Import.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblImport.setIcon(new ImageIcon(newImage_Import));
		genericStuff.hover(lblImport, lblNhpFileCsv, panelImport, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK,
				new Color(178, 34, 34));

		textField = new JTextField();
		textField.setText("Tên tài khoản:");
		textField.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(10, 11, 220, 38);
		MiddleBar.add(textField);

		textField_1 = new JTextField();
		textField_1.setText(giaoVu.get_msgv());
		textField_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setBounds(10, 60, 220, 38);
		MiddleBar.add(textField_1);

		// Element và sử lý sự kiện của nút quay lại
		JPanel panelBack = new JPanel();
		panelBack.setBounds(140, 363, 100, 110);
		MiddleBar.add(panelBack);
		panelBack.setLayout(null);
		panelBack.setBackground(new Color(178, 34, 34));
		JLabel lblQuayLi = new JLabel("Quay Lại", SwingConstants.CENTER);
		lblQuayLi.setForeground(Color.BLACK);
		lblQuayLi.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblQuayLi.setBounds(0, 86, 100, 14);
		panelBack.add(lblQuayLi);
		JLabel lblBack = new JLabel();
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				LecturerDashBoard lecturerDashBoard = new LecturerDashBoard(giaoVu);
				genericStuff.call_frame(lecturerDashBoard);
			}
		});
		lblBack.setBounds(10, 11, 80, 80);
		panelBack.add(lblBack);
		ImageIcon imgIcon_Back = new ImageIcon(Login.class.getResource("/resources/images/Back.png"));
		Image image_Back = imgIcon_Back.getImage();
		Image newImage_Back = image_Back.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblBack.setIcon(new ImageIcon(newImage_Back));
		genericStuff.hover(lblBack, lblQuayLi, panelBack, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK,
				new Color(178, 34, 34));

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		// Bao bên ngoài table sinh viên để có thể scroll table, k có thì overflow sinh
		// viên sẽ k thấy được
		JScrollPane scrollPane_SinhVien = new JScrollPane();
		scrollPane_SinhVien.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_SinhVien.setBounds(324, 61, 420, 207);
		contentPane.add(scrollPane_SinhVien);

		tableSinhVien = new JTable();
		scrollPane_SinhVien.setViewportView(tableSinhVien);
		tableSinhVien.setBackground(Color.LIGHT_GRAY);
		tableSinhVien.setModel(drawTabelSinhVien(sinhViens));
		reSizeTable(tableSinhVien, centerRenderer);

		// Tìm tất cả các lớp render vào combobox
		LopDao lopDao = new LopDao();
		List<Lop> lops = lopDao.findAll();
		List<String> strings_Lop = new ArrayList<String>();
		strings_Lop.add("Tất Cả Sinh Viên");
		for (Lop item : lops) {
			strings_Lop.add(item.get_maLop());
		}

		String[] options_Lop = new String[strings_Lop.size()];
		options_Lop = strings_Lop.toArray(options_Lop);

		// Combo box để chọn lọc loại sinh viên thuộc lớp nào
		ComboBoxModel boxModels_sinhViens = new DefaultComboBoxModel(options_Lop);
		boxModels_sinhViens.setSelectedItem(null);
		comboBox_ClassFilter = new JComboBox(boxModels_sinhViens);
		comboBox_ClassFilter.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

				if (comboBox_ClassFilter.getSelectedItem().toString().equals("Tất Cả Sinh Viên")) {
					tableSinhVien.setModel(drawTabelSinhVien(sinhViens));
					reSizeTable(tableSinhVien, centerRenderer);
				} else {
					Lop lop = new Lop();
					lop.set_maLop(comboBox_ClassFilter.getSelectedItem().toString());
					lop = lop.findByML(lops, lop);
					tableSinhVien.setModel(drawTabelSinhVien(lop.getSinhViens()));
					reSizeTable(tableSinhVien, centerRenderer);
				}
			}
		});
		comboBox_ClassFilter.setBounds(324, 269, 100, 20);
		contentPane.add(comboBox_ClassFilter);

		JLabel lblSapXep = new JLabel("Lọc theo lớp");
		lblSapXep.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblSapXep.setBounds(430, 269, 85, 20);
		contentPane.add(lblSapXep);
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		JScrollPane scrollPane_Subject = new JScrollPane();
		scrollPane_Subject.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_Subject.setBounds(324, 300, 416, 150);
		contentPane.add(scrollPane_Subject);

		table_Subject = new JTable();
		scrollPane_Subject.setViewportView(table_Subject);
		table_Subject.setBackground(Color.LIGHT_GRAY);
		table_Subject.setModel(drawTabelSinhVien_Mon(dssv_MONs));
		reSizeTable(table_Subject, centerRenderer);

		JLabel lblLcTheoMn = new JLabel("Lọc theo môn");
		lblLcTheoMn.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblLcTheoMn.setBounds(454, 450, 85, 20);
		contentPane.add(lblLcTheoMn);

		List<String> strings_Lop_Mon = new ArrayList<String>();
		strings_Lop_Mon.add("Tất Cả Môn");
		List<DSL_MON> dsl_MONs = new DSLMDao().findAll();
		for (DSL_MON item : dsl_MONs) {
			strings_Lop_Mon.add(item.getMalop_mon());
		}

		String[] options_LopMon = new String[strings_Lop_Mon.size()];
		options_LopMon = strings_Lop_Mon.toArray(options_LopMon);

		ComboBoxModel boxModels_dslMon = new DefaultComboBoxModel(options_LopMon);
		boxModels_dslMon.setSelectedItem(null);
		comboBox_SubjectFilter = new JComboBox(boxModels_dslMon);
		comboBox_SubjectFilter.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBox_SubjectFilter.getSelectedItem().toString().equals("Tất Cả Môn")) {
					table_Subject.setModel(drawTabelSinhVien_Mon(dssv_MONs));
					reSizeTable(table_Subject, centerRenderer);
				} else {
					DSSV_MON dssv_MON = new DSSV_MON();
					List<DSSV_MON> mons = dssv_MON.findByMLM(comboBox_SubjectFilter.getSelectedItem().toString(),
							dssv_MONs);
					table_Subject.setModel(drawTabelSinhVien_Mon(mons));
					reSizeTable(table_Subject, centerRenderer);
				}
			}
		});
		comboBox_SubjectFilter.setBounds(324, 451, 120, 20);
		contentPane.add(comboBox_SubjectFilter);
	}

	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		event_listener();
	}

	public LecturerStudents(GiaoVu giaoVu) {
		setGiaoVu(giaoVu);

		init();

		// Lấy vị trí hiện tại của con tr�? JFrame
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
				// Set location cho JFrame dựa trên vị trí con tr�? chuột đang nằm ở JFrame và
				// vị
				// trí của JFrame trên màn hình
				setLocation(x - draggedAtX, y - draggedAtY);
			}
		});
	}

	// Trả ra DefaultTableModel để render lại bảng theo ý muốn.
	private DefaultTableModel drawTabelSinhVien(List<SinhVien> sinhViens) {
		String[] columns = { "STT", "Tên", "MSSV", "Giới Tính", "Lớp" };
		tableModel = new DefaultTableModel(columns, 0);
		// tableModel.addRow(columns);
		int i = 0;
		for (SinhVien student : sinhViens) {
			i++;
			String[] data = { String.valueOf(i), student.get_ten(), student.get_mssv(), student.get_gioiTinh(),
					student.getMa_lop() };
			tableModel.addRow(data);
		}
		return tableModel;
	}

	private DefaultTableModel drawTabelSinhVien_Mon(List<DSSV_MON> dssv_MONs) {
		String[] columns = { "STT", "Tên", "MSSV", "Giới Tính", "Lớp Môn" };
		tableModel = new DefaultTableModel(columns, 0);
		// tableModel.addRow(columns);
		int i = 0;
		for (DSSV_MON dssv : dssv_MONs) {
			i++;
			// Tìm sinh viên dựa vào class đã map ở dssv
			String tenSv = dssv.getSinhVien().findByMSSV(sinhViens, dssv.getSinhVien()).get_ten();
			String gioiTinh = dssv.getSinhVien().findByMSSV(sinhViens, dssv.getSinhVien()).get_gioiTinh();

			String[] data = { String.valueOf(i), tenSv, dssv.get_mssv(), gioiTinh, dssv.get_malopMon() };
			tableModel.addRow(data);
		}
		return tableModel;
	}

	// Gọi mỗi lần render lại bảng... :(((
	private void reSizeTable(JTable jTable, DefaultTableCellRenderer centerRenderer) {
		jTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		jTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(180);
		jTable.getColumnModel().getColumn(4).setPreferredWidth(120);
	}
}
