package views;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import constants.AlertConstants;
import dao.QuyenDao;
import entity.Quyen;
import entity.SinhVien;
import util.FileParser;
import util.HibernateUtil;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Button;

public class LecturerDashBoard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GenericStuff genericStuff = new GenericStuff();

	// Đang kéo thả tại tọa độ x y
	public int draggedAtX;
	public int draggedAtY;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LecturerDashBoard frame = new LecturerDashBoard();
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
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
//				Home home = new Home();
//				genericStuff.call_frame(home);
				JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma-separated Values", "csv");
				fileChooser.setFileFilter(filter);
				fileChooser.setCurrentDirectory(new File("."));
				fileChooser.setDialogTitle("Chọn File CSV");

				int results = fileChooser.showSaveDialog(null);

				if (results == JFileChooser.APPROVE_OPTION) {
					FileParser fileParser = new FileParser();
					List<SinhVien> list_sinhVien = fileParser
							.readFromCSV(fileChooser.getSelectedFile().getAbsolutePath());
					for (SinhVien sv : list_sinhVien) {
						System.out.println("MSSV: " + sv.get_mssv());
						System.out.println("CMND: " + sv.get_cmnd());
						System.out.println("Tên: " + sv.get_ten());
						System.out.println("Giới tính: " + sv.get_gioiTinh());
					}

					try {
						SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
						Session session = sessionFactory.openSession();
						
						QuyenDao quyenDao = new QuyenDao();
						List<Quyen> list_quyen = quyenDao.findAll();
						
						for (Quyen item : list_quyen) {
							System.out.println(item.get_tenQuyen());
						}
						
						session.close();

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					LecturerDashBoard frame = new LecturerDashBoard();
					genericStuff.call_frame(frame);
				}
			}
		});
		btnNewButton.setBounds(633, 335, 97, 63);
		contentPane.add(btnNewButton);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 760, 410);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(0, 0, 760, 200);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 100, 100);
		panel_2.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel DashIcon = new JLabel("");
		DashIcon.setBackground(Color.GRAY);
		DashIcon.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon imgIcon_Dash = new ImageIcon(Home.class.getResource("/resources/DashBoardIcon.png"));
		Image image_Dash = imgIcon_Dash.getImage();
		Image newImage_Dash = image_Dash.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		DashIcon.setIcon(new ImageIcon(newImage_Dash));
		DashIcon.setOpaque(true);
		panel_1.add(DashIcon, BorderLayout.CENTER);

		JLabel lblNewLabel_1 = new JLabel("\"Never Give Power To The Big Man\"");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(120, 11, 240, 40);
		panel_2.add(lblNewLabel_1);

		JLabel lblintelligenceIsA = new JLabel("\"Intelligence Is A Very Valuable Thing\"");
		lblintelligenceIsA.setHorizontalAlignment(SwingConstants.CENTER);
		lblintelligenceIsA.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblintelligenceIsA.setBounds(175, 47, 280, 40);
		panel_2.add(lblintelligenceIsA);

		JLabel lblheWhoFights = new JLabel("\"He Who Fights By The Sword Dies By It\"");
		lblheWhoFights.setHorizontalAlignment(SwingConstants.CENTER);
		lblheWhoFights.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblheWhoFights.setBounds(272, 81, 270, 40);
		panel_2.add(lblheWhoFights);

		Button minimize = new Button("-");
		minimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setState(JFrame.ICONIFIED);
			}
		});
		minimize.setForeground(Color.WHITE);
		minimize.setFont(new Font("Times New Roman", Font.BOLD, 18));
		minimize.setBackground(Color.GRAY);
		minimize.setBounds(690, 0, 30, 25);
		panel_2.add(minimize);

		Button exit = new Button("X");
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// "/resources/GoodBye.gif"
				AlertDialog alert = new AlertDialog(AlertConstants.LEAVING_SO_SOON, AlertConstants.GOODBYE);
				genericStuff.call_frame(alert);
			}
		});
		exit.setForeground(Color.WHITE);
		exit.setFont(new Font("Times New Roman", Font.BOLD, 15));
		exit.setBackground(Color.GRAY);
		exit.setBounds(720, 0, 30, 25);
		panel_2.add(exit);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_3.setBounds(120, 221, 100, 110);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel = new JLabel("Sinh Viên", SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(0, 86, 100, 14);
		panel_3.add(lblNewLabel);

		JLabel lblIconsinhvien = new JLabel("");
		lblIconsinhvien.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconsinhvien.setBounds(10, 11, 80, 75);
		ImageIcon imgIcon_Students = new ImageIcon(Home.class.getResource("/resources/Students.png"));
		Image image_Students = imgIcon_Students.getImage();
		Image newImage_Students = image_Students.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblIconsinhvien.setIcon(new ImageIcon(newImage_Students));
		panel_3.add(lblIconsinhvien);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.LIGHT_GRAY);
		panel_4.setBounds(260, 221, 100, 110);
		panel.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblIconlichhoc = new JLabel("");
		lblIconlichhoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconlichhoc.setBounds(10, 11, 80, 75);
		ImageIcon imgIcon_Schedules = new ImageIcon(Home.class.getResource("/resources/Schedule.png"));
		Image image_Schedules = imgIcon_Schedules.getImage();
		Image newImage_Schedules = image_Schedules.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblIconlichhoc.setIcon(new ImageIcon(newImage_Schedules));
		panel_4.add(lblIconlichhoc);

		JLabel lblLchHc = new JLabel("Lịch Giảng Dạy", SwingConstants.CENTER);
		lblLchHc.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblLchHc.setBounds(0, 86, 100, 14);
		panel_4.add(lblLchHc);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.LIGHT_GRAY);
		panel_5.setBounds(400, 221, 100, 110);
		panel.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblIcondiem = new JLabel("");
		lblIcondiem.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcondiem.setBounds(10, 11, 80, 75);
		ImageIcon imgIcon_Scores = new ImageIcon(Home.class.getResource("/resources/Scores.png"));
		Image image_Scores = imgIcon_Scores.getImage();
		Image newImage_Scores = image_Scores.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblIcondiem.setIcon(new ImageIcon(newImage_Scores));
		panel_5.add(lblIcondiem);

		JLabel lblimS = new JLabel("Điểm Số", SwingConstants.CENTER);
		lblimS.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblimS.setBounds(0, 86, 100, 14);
		panel_5.add(lblimS);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.LIGHT_GRAY);
		panel_6.setBounds(540, 221, 100, 110);
		panel.add(panel_6);
		panel_6.setLayout(null);

		JLabel lblIconcaidat = new JLabel("");
		lblIconcaidat.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconcaidat.setBounds(10, 11, 80, 75);
		ImageIcon imgIcon_Settings = new ImageIcon(Home.class.getResource("/resources/Settings.png"));
		Image image_Settings = imgIcon_Settings.getImage();
		Image newImage_Settings = image_Settings.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblIconcaidat.setIcon(new ImageIcon(newImage_Settings));
		panel_6.add(lblIconcaidat);

		JLabel lblCit = new JLabel("Cài Đặt", SwingConstants.CENTER);
		lblCit.setIcon(null);
		lblCit.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblCit.setBounds(0, 86, 100, 14);
		panel_6.add(lblCit);

		lblIconsinhvien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		lblIconlichhoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		lblIcondiem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		lblIconcaidat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}

	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		event_listener();
	}

	public LecturerDashBoard() {

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

		init();
	}
}
