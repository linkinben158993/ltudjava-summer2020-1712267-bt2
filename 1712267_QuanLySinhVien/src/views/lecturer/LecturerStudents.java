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

import dao.LopDao;
import dao.SinhVienDao;
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

public class LecturerStudents extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GenericStuff genericStuff = new GenericStuff();

	// �?ang kéo thả tại t�?a độ x y
	public int draggedAtX;
	public int draggedAtY;
	private JTable table;
	private DefaultTableModel tableModel;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private final List<SinhVien> sinhViens = new SinhVienDao().findAll();
	private JTextField txtSearch;
	private JTextField txtChoMng;
	private GiaoVu giaoVu;

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
		txtSearch.setText("Tìm kiếm");
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
		MiddleBar.setBounds(85, 60, 240, 520);
		MiddleBar.setBackground(new Color(178, 34, 34));
		contentPane.add(MiddleBar);
		MiddleBar.setLayout(null);

		// Element và sử lý sự kiện của thêm sinh viên
		JPanel panelThem = new JPanel();
		panelThem.setLayout(null);
		panelThem.setBackground(new Color(178, 34, 34));
		panelThem.setBounds(140, 0, 100, 110);
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
		panelXoa.setBounds(140, 121, 100, 110);
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
				if (table.getSelectedRowCount() == 0) {

					JOptionPane.showMessageDialog(null, "Chưa chọn sinh viên nào trên danh sách!");

				} else {

					int cols = table.getColumnCount();
					int rows = table.getSelectedRow();
					for (int i = 0; i < cols; i++) {
						System.out.println(table.getModel().getValueAt(rows, i));
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
		panelImport.setBounds(140, 363, 100, 110);
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

		// Element và sử lý sự kiện của nút quay lại
		JPanel panelBack = new JPanel();
		panelBack.setLayout(null);
		panelBack.setBackground(Color.WHITE);
		panelBack.setBounds(640, 420, 100, 110);
		contentPane.add(panelBack);
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

		table = new JTable();
		table.setBackground(Color.LIGHT_GRAY);
		table.setBounds(324, 60, 420, 360);
		table.setModel(drawTabel(new SinhVienDao().findAll()));

		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);

		contentPane.add(table);

		LopDao lopDao = new LopDao();
		List<Lop> lops = lopDao.findAll();
		List<String> strings = new ArrayList<String>();
		strings.add("Tất Cả Sinh Viên");
		for (Lop item : lops) {
			strings.add(item.get_maLop());
		}

		String[] options = new String[strings.size()];
		options = strings.toArray(options);

		ComboBoxModel boxModels = new DefaultComboBoxModel(options);
		boxModels.setSelectedItem(null);
		comboBox = new JComboBox(boxModels);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

				if (comboBox.getSelectedItem().toString().equals("Tất Cả Sinh Viên")) {
					table.setModel(drawTabel(sinhViens));
				} else {
					Lop lop = new Lop();
					lop.set_maLop(comboBox.getSelectedItem().toString());
					lop = lop.findByML(lops, lop);
					table.setModel(drawTabel(lop.getSinhViens()));
				}
			}
		});
		comboBox.setBounds(550, 420, 80, 20);
		contentPane.add(comboBox);
	}

	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 560);
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
	private DefaultTableModel drawTabel(List<SinhVien> sinhViens) {
		String[] columns = { "STT", "Tên", "MSSV", "Giới Tính", "Lớp" };
		tableModel = new DefaultTableModel(columns, 0);

		int i = 0;
		for (SinhVien student : sinhViens) {
			i++;
			String[] data = { String.valueOf(i), student.get_ten(), student.get_mssv(), student.get_gioiTinh(),
					student.getMa_lop() };
			tableModel.addRow(data);
		}
		return tableModel;
	}
}
