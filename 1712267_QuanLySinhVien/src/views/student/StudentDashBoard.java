package views.student;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import constants.AlertConstants;
import dao.SinhVienDao;
import entity.Diem;
import entity.SinhVien;
import views.AlertDialog;
import views.GenericStuff;
import views.Login;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.Button;

public class StudentDashBoard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private SinhVien sinhVien;
	private GenericStuff genericStuff = new GenericStuff();

	public SinhVien getSinhVien() {
		return sinhVien;
	}

	public void setSinhVien(SinhVien sinhVien) {
		this.sinhVien = sinhVien;
	}

	private int draggedAtX;
	private int draggedAtY;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentDashBoard frame = new StudentDashBoard(new SinhVien());
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
		panel.setLayout(null);
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 760, 180);
		contentPane.add(panel);

		JPanel panelDashIcon = new JPanel();
		panelDashIcon.setBounds(10, 11, 100, 100);
		panel.add(panelDashIcon);
		panelDashIcon.setLayout(new BorderLayout(0, 0));
		JLabel DashIcon = new JLabel("");
		DashIcon.setBackground(Color.GRAY);
		DashIcon.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon imgIcon_Dash = new ImageIcon(Login.class.getResource("/resources/images/DashBoardIcon.png"));
		Image image_Dash = imgIcon_Dash.getImage();
		Image newImage_Dash = image_Dash.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		DashIcon.setIcon(new ImageIcon(newImage_Dash));
		DashIcon.setOpaque(true);
		panelDashIcon.add(DashIcon, BorderLayout.CENTER);
		JLabel label = new JLabel("\"Whiskey Is Good Proofing Water, It Tells Who's Real And Who Isn't\"");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label.setBounds(120, 11, 564, 40);
		panel.add(label);
		JLabel label_1 = new JLabel("\"You Can Change What You Do, But You Cant't Change What You Want\"");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_1.setBounds(130, 47, 554, 40);
		panel.add(label_1);
		JLabel label_2 = new JLabel(
				"\"The Only Way To Guarantee Peace Is By Making The Prospect Of War Seem Hopeless\"");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_2.setBounds(120, 81, 564, 40);
		panel.add(label_2);

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
		panel.add(minimize);

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
		panel.add(exit);

		JPanel panel_Diem = new JPanel();
		panel_Diem.setLayout(null);
		panel_Diem.setBackground(Color.LIGHT_GRAY);
		panel_Diem.setBounds(95, 207, 100, 110);
		contentPane.add(panel_Diem);
		JLabel lblimS = new JLabel("Điểm Số", SwingConstants.CENTER);
		lblimS.setForeground(Color.BLACK);
		lblimS.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblimS.setBounds(0, 86, 100, 14);
		panel_Diem.add(lblimS);
		JLabel lblIconDiem = new JLabel("");
		lblIconDiem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SinhVienDao sinhVienDao = new SinhVienDao();
				List<SinhVien> sinhViens = sinhVienDao.findAll();
				SinhVien foundSinhVien = new SinhVien().findByMSSV(sinhViens, getSinhVien());
				List<Diem> diems = foundSinhVien.getDiems();
				for (Diem item : diems) {
					System.out.println(item.get_mssv() + " " + item.get_tongDiem() + " ");
					System.out.println(item.getDsl_mon().getMon_lop().get_tenMon());
				}
				StudentScoreBoard studentScoreBoard = new StudentScoreBoard(foundSinhVien, diems);
				genericStuff.call_dialog(studentScoreBoard);
			}
		});
		lblIconDiem.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconDiem.setBounds(10, 11, 80, 75);
		ImageIcon imgIcon_Scores = new ImageIcon(Login.class.getResource("/resources/images/Scores.png"));
		Image image_Scores = imgIcon_Scores.getImage();
		Image newImage_Scores = image_Scores.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblIconDiem.setIcon(new ImageIcon(newImage_Scores));
		genericStuff.hover(lblIconDiem, lblimS, panel_Diem, new Color(230, 230, 250), Color.DARK_GRAY, Color.BLACK,
				Color.LIGHT_GRAY);
		panel_Diem.add(lblIconDiem);

		JPanel panel_Lich = new JPanel();
		panel_Lich.setLayout(null);
		panel_Lich.setBackground(Color.LIGHT_GRAY);
		panel_Lich.setBounds(235, 207, 100, 110);
		contentPane.add(panel_Lich);
		JLabel lblLchHc = new JLabel("Lịch Học", SwingConstants.CENTER);
		lblLchHc.setForeground(Color.BLACK);
		lblLchHc.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblLchHc.setBounds(0, 86, 100, 14);
		panel_Lich.add(lblLchHc);
		JLabel lblIconLichHoc = new JLabel("");
		lblIconLichHoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblIconLichHoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconLichHoc.setBounds(10, 11, 80, 75);
		ImageIcon imgIcon_Schedules = new ImageIcon(Login.class.getResource("/resources/images/Schedule.png"));
		Image image_Schedules = imgIcon_Schedules.getImage();
		Image newImage_Schedules = image_Schedules.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblIconLichHoc.setIcon(new ImageIcon(newImage_Schedules));
		genericStuff.hover(lblIconLichHoc, lblLchHc, panel_Lich, new Color(230, 230, 250), Color.DARK_GRAY, Color.BLACK,
				Color.LIGHT_GRAY);
		panel_Lich.add(lblIconLichHoc);

		JPanel panel_YeuCau = new JPanel();
		panel_YeuCau.setLayout(null);
		panel_YeuCau.setBackground(Color.LIGHT_GRAY);
		panel_YeuCau.setBounds(375, 207, 100, 110);
		contentPane.add(panel_YeuCau);
		JLabel lblGiYuCu = new JLabel("Gửi Yêu Cầu", SwingConstants.CENTER);
		lblGiYuCu.setForeground(Color.BLACK);
		lblGiYuCu.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblGiYuCu.setBounds(0, 86, 100, 14);
		panel_YeuCau.add(lblGiYuCu);
		JLabel lblIconYeuCau = new JLabel("");
		lblIconYeuCau.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblIconYeuCau.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconYeuCau.setBounds(10, 11, 80, 75);
		ImageIcon imgIcon_Request = new ImageIcon(Login.class.getResource("/resources/images/Request.png"));
		Image image_Request = imgIcon_Request.getImage();
		Image newImage_Request = image_Request.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblIconYeuCau.setIcon(new ImageIcon(newImage_Request));
		genericStuff.hover(lblIconYeuCau, lblGiYuCu, panel_YeuCau, new Color(230, 230, 250), Color.DARK_GRAY,
				Color.BLACK, Color.LIGHT_GRAY);
		panel_YeuCau.add(lblIconYeuCau);

		JPanel panel_CaiDat = new JPanel();
		panel_CaiDat.setLayout(null);
		panel_CaiDat.setBackground(Color.LIGHT_GRAY);
		panel_CaiDat.setBounds(515, 207, 100, 110);
		contentPane.add(panel_CaiDat);
		JLabel lblCit = new JLabel("Cài Đặt", SwingConstants.CENTER);
		lblCit.setForeground(Color.BLACK);
		lblCit.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblCit.setBounds(0, 86, 100, 14);
		panel_CaiDat.add(lblCit);
		JLabel lblIconCaiDat = new JLabel("");
		lblIconCaiDat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblIconCaiDat.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconCaiDat.setBounds(10, 11, 80, 75);
		ImageIcon imgIcon_Settings = new ImageIcon(Login.class.getResource("/resources/images/Settings.png"));
		Image image_Settings = imgIcon_Settings.getImage();
		Image newImage_Settings = image_Settings.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblIconCaiDat.setIcon(new ImageIcon(newImage_Settings));
		genericStuff.hover(lblIconCaiDat, lblCit, panel_CaiDat, new Color(230, 230, 250), Color.DARK_GRAY, Color.BLACK,
				Color.LIGHT_GRAY);
		panel_CaiDat.add(lblIconCaiDat);

		JPanel panel_QuayLai = new JPanel();
		panel_QuayLai.setLayout(null);
		panel_QuayLai.setBackground(Color.LIGHT_GRAY);
		panel_QuayLai.setBounds(660, 290, 100, 110);
		contentPane.add(panel_QuayLai);
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
				AlertDialog alert = new AlertDialog(AlertConstants.LOG_OUT_WARNINGS, AlertConstants.LOG_OUT, sinhVien);
				genericStuff.call_frame(alert);
			}
		});
		genericStuff.hover(lblIconBack, lblBack, panel_QuayLai, new Color(230, 230, 250), Color.DARK_GRAY, Color.BLACK,
				Color.LIGHT_GRAY);
	}

	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 746, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		event_listener();
	}

	public StudentDashBoard(SinhVien sinhVien) {
		this.sinhVien = sinhVien;

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
