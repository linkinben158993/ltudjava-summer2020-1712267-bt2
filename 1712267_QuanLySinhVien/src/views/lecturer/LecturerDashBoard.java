package views.lecturer;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import constants.AlertConstants;
import entity.GiaoVu;
import views.AlertDialog;
import views.GenericStuff;
import views.Login;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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
	private GiaoVu giaoVu;

	public GiaoVu getGiaoVu() {
		return giaoVu;
	}

	public void setGiaoVu(GiaoVu giaoVu) {
		this.giaoVu = giaoVu;
	}

	// �?ang kéo thả tại t�?a độ x y
	public int draggedAtX;
	public int draggedAtY;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LecturerDashBoard frame = new LecturerDashBoard(new GiaoVu());
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

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 760, 400);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(0, 0, 760, 180);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 100, 100);
		panel_2.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel DashIcon = new JLabel("");
		DashIcon.setBackground(Color.GRAY);
		DashIcon.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon imgIcon_Dash = new ImageIcon(Login.class.getResource("/resources/images/DashBoardIcon.png"));
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

		JPanel panel_SinhVien = new JPanel();
		panel_SinhVien.setBackground(Color.LIGHT_GRAY);
		panel_SinhVien.setBounds(95, 210, 100, 110);
		panel.add(panel_SinhVien);
		panel_SinhVien.setLayout(null);

		JLabel lblSinhVien = new JLabel("Sinh Viên", SwingConstants.CENTER);
		lblSinhVien.setForeground(Color.BLACK);
		lblSinhVien.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblSinhVien.setBounds(0, 86, 100, 14);
		panel_SinhVien.add(lblSinhVien);

		JLabel lblIconsinhvien = new JLabel("");
		lblIconsinhvien.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconsinhvien.setBounds(10, 11, 80, 75);
		ImageIcon imgIcon_Students = new ImageIcon(Login.class.getResource("/resources/images/Students.png"));
		Image image_Students = imgIcon_Students.getImage();
		Image newImage_Students = image_Students.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblIconsinhvien.setIcon(new ImageIcon(newImage_Students));
		panel_SinhVien.add(lblIconsinhvien);

		JPanel panel_Lich = new JPanel();
		panel_Lich.setBackground(Color.LIGHT_GRAY);
		panel_Lich.setBounds(235, 210, 100, 110);
		panel.add(panel_Lich);
		panel_Lich.setLayout(null);

		JLabel lblIconlichhoc = new JLabel("");
		lblIconlichhoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconlichhoc.setBounds(10, 11, 80, 75);
		ImageIcon imgIcon_Schedules = new ImageIcon(Login.class.getResource("/resources/images/Schedule.png"));
		Image image_Schedules = imgIcon_Schedules.getImage();
		Image newImage_Schedules = image_Schedules.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblIconlichhoc.setIcon(new ImageIcon(newImage_Schedules));
		panel_Lich.add(lblIconlichhoc);

		JLabel lblLchHc = new JLabel("Lịch Giảng Dạy", SwingConstants.CENTER);
		lblLchHc.setForeground(Color.BLACK);
		lblLchHc.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblLchHc.setBounds(0, 86, 100, 14);
		panel_Lich.add(lblLchHc);

		JPanel panel_Diem = new JPanel();
		panel_Diem.setBackground(Color.LIGHT_GRAY);
		panel_Diem.setBounds(375, 210, 100, 110);
		panel.add(panel_Diem);
		panel_Diem.setLayout(null);

		JLabel lblIcondiem = new JLabel("");
		lblIcondiem.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcondiem.setBounds(10, 11, 80, 75);
		ImageIcon imgIcon_Scores = new ImageIcon(Login.class.getResource("/resources/images/Scores.png"));
		Image image_Scores = imgIcon_Scores.getImage();
		Image newImage_Scores = image_Scores.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblIcondiem.setIcon(new ImageIcon(newImage_Scores));
		panel_Diem.add(lblIcondiem);

		JLabel lblimS = new JLabel("Điểm Số", SwingConstants.CENTER);
		lblimS.setForeground(Color.BLACK);
		lblimS.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblimS.setBounds(0, 86, 100, 14);
		panel_Diem.add(lblimS);

		JPanel panel_CaiDat = new JPanel();
		panel_CaiDat.setBackground(Color.LIGHT_GRAY);
		panel_CaiDat.setBounds(515, 210, 100, 110);
		panel.add(panel_CaiDat);
		panel_CaiDat.setLayout(null);

		JLabel lblIconcaidat = new JLabel("");
		lblIconcaidat.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconcaidat.setBounds(10, 11, 80, 75);
		ImageIcon imgIcon_Settings = new ImageIcon(Login.class.getResource("/resources/images/Settings.png"));
		Image image_Settings = imgIcon_Settings.getImage();
		Image newImage_Settings = image_Settings.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblIconcaidat.setIcon(new ImageIcon(newImage_Settings));
		panel_CaiDat.add(lblIconcaidat);

		JLabel lblCit = new JLabel("Cài Đặt", SwingConstants.CENTER);
		lblCit.setForeground(Color.BLACK);
		lblCit.setIcon(null);
		lblCit.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblCit.setBounds(0, 86, 100, 14);
		panel_CaiDat.add(lblCit);

		lblIconsinhvien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				LecturerStudents lecturerStudents = new LecturerStudents(giaoVu);
				genericStuff.call_frame(lecturerStudents);
			}
		});
		// Lấy frame hiện tại truy�?n vào Generic
		genericStuff.hover(lblIconsinhvien, lblSinhVien, panel_SinhVien, new Color(230, 230, 250), Color.DARK_GRAY,
				Color.BLACK, Color.LIGHT_GRAY);

		lblIconlichhoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				LecturerSchedule lecturerSchedule = new LecturerSchedule(giaoVu);
				genericStuff.call_frame(lecturerSchedule);

			}
		});
		// Lấy frame hiện tại truy�?n vào Generic
		genericStuff.hover(lblIconlichhoc, lblLchHc, panel_Lich, new Color(230, 230, 250), Color.DARK_GRAY, Color.BLACK,
				Color.LIGHT_GRAY);

		lblIcondiem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		// Lấy frame hiện tại truy�?n vào Generic
		genericStuff.hover(lblIcondiem, lblimS, panel_Diem, new Color(230, 230, 250), Color.DARK_GRAY, Color.BLACK,
				Color.LIGHT_GRAY);

		lblIconcaidat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		// Lấy frame hiện tại truy�?n vào Generic
		genericStuff.hover(lblIconcaidat, lblCit, panel_CaiDat, new Color(230, 230, 250), Color.DARK_GRAY, Color.BLACK,
				Color.LIGHT_GRAY);

		JPanel panel_QuayLai = new JPanel();
		panel_QuayLai.setLayout(null);
		panel_QuayLai.setBackground(Color.LIGHT_GRAY);
		panel_QuayLai.setBounds(660, 290, 100, 110);
		panel.add(panel_QuayLai);
		JLabel lblBack = new JLabel("Quay Lại", SwingConstants.CENTER);
		lblBack.setForeground(Color.BLACK);
		lblBack.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblBack.setBounds(0, 86, 100, 14);
		panel_QuayLai.add(lblBack);
		JLabel lblIconBack = new JLabel();
		ImageIcon imgIcon_Back = new ImageIcon(Login.class.getResource("/resources/images/Back.png"));
		Image image_Back = imgIcon_Back.getImage();
		Image newImage_Back = image_Back.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblIconBack.setIcon(new ImageIcon(newImage_Back));
		lblIconBack.setBounds(10, 11, 80, 80);
		panel_QuayLai.add(lblIconBack);
		lblIconBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				AlertDialog alert = new AlertDialog(AlertConstants.LOG_OUT_WARNINGS, AlertConstants.LOG_OUT, giaoVu);
				genericStuff.call_frame(alert);
			}
		});
		genericStuff.hover(lblIconBack, lblBack, panel_QuayLai, new Color(230, 230, 250), Color.DARK_GRAY, Color.BLACK,
				Color.LIGHT_GRAY);

		JLabel lblCredit = new JLabel("2019 LTUD Java - 1712267 Nguyễn Hoàng Thiên Ân");
		lblCredit.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredit.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCredit.setBounds(206, 386, 330, 14);
		panel.add(lblCredit);
	}

	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		event_listener();
	}

	public LecturerDashBoard(GiaoVu giaovu) {

		setGiaoVu(giaovu);
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

		init();
	}
}
