package views.lecturer;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import util.FileParser;
import views.GenericStuff;
import views.Login;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import javax.swing.SwingConstants;
import java.awt.Font;

public class LecturerStudents extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GenericStuff genericStuff = new GenericStuff();

	// �?ang kéo thả tại t�?a độ x y
	public int draggedAtX;
	public int draggedAtY;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LecturerStudents frame = new LecturerStudents();
					frame.setLocationRelativeTo(null);
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void event_listener() {
		contentPane.setLayout(null);
		JPanel SideBar = new JPanel();
		SideBar.setBounds(0, 0, 85, 600);
		SideBar.setBackground(new Color(128, 0, 0));
		contentPane.add(SideBar);

		JPanel SearchBar = new JPanel();
		SearchBar.setBounds(85, 0, 670, 60);
		SearchBar.setBackground(new Color(178, 34, 34));
		contentPane.add(SearchBar);

		JPanel MiddleBar = new JPanel();
		MiddleBar.setBounds(85, 60, 240, 540);
		MiddleBar.setBackground(new Color(178, 34, 34));
		contentPane.add(MiddleBar);
		MiddleBar.setLayout(null);

		// Element và sử lý sự kiện của thêm sinh viên
		JPanel panelThem = new JPanel();
		panelThem.setLayout(null);
		panelThem.setBackground(new Color(178, 34, 34));
		panelThem.setBounds(130, 11, 100, 110);
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
				LecturerAddStudent lecturerAddStudent = new LecturerAddStudent();
				lecturerAddStudent.setLocationRelativeTo(null);
				lecturerAddStudent.setVisible(true);
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
		panelSua.setBounds(130, 251, 100, 110);
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
		panelXoa.setBounds(130, 130, 100, 110);
		MiddleBar.add(panelXoa);
		JLabel lblXaSinhVin = new JLabel("Xóa Sinh Viên", SwingConstants.CENTER);
		lblXaSinhVin.setForeground(Color.BLACK);
		lblXaSinhVin.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblXaSinhVin.setBounds(0, 86, 100, 14);
		panelXoa.add(lblXaSinhVin);
		JLabel lblXoa = new JLabel();
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
		panelImport.setBounds(130, 373, 100, 110);
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
					// List<SinhVien> list_sinhVien =
					fileParser.readFromCSV(fileChooser.getSelectedFile().getAbsolutePath());

					// Nếu muốn in thử list sinh viên và lấy sinh viên thuộc quyền...
					// printForTestPurpose(list_sinhVien);

				} else {
					LecturerStudents frame = new LecturerStudents();
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

		JLabel label_1 = new JLabel("");
		label_1.setBounds(510, 163, 80, 75);
		contentPane.add(label_1);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
	}

	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		event_listener();
	}

	public LecturerStudents() {
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
}
