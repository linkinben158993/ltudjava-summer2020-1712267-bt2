package views.student;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import entity.SinhVien;
import views.GenericStuff;
import views.Login;
import views.lecturer.LecturerSchedule;
import views.util.ChangePassword;

public class StudentSettings extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame curFrame;
	

	public int draggedAtX;
	public int draggedAtY;

	private SinhVien sinhVien;
	private GenericStuff genericStuff = new GenericStuff();

	public JFrame getCurFrame() {
		return curFrame;
	}

	public void setCurFrame(JFrame curFrame) {
		this.curFrame = curFrame;
	}

	public SinhVien getSinhVien() {
		return sinhVien;
	}

	public void setSinhVien(SinhVien sinhVien) {
		this.sinhVien = sinhVien;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentSettings frame = new StudentSettings(new SinhVien());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StudentSettings(SinhVien sinhVien) {
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

	private void event_listener() {
		this.curFrame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
		
		JPanel panel_Profile = new JPanel();
		panel_Profile.setLayout(null);
		panel_Profile.setBackground(Color.WHITE);
		panel_Profile.setBounds(0, 0, 120, 154);
		contentPane.add(panel_Profile);
		JLabel lblProfile = new JLabel(getSinhVien().get_ten(), SwingConstants.CENTER);
		lblProfile.setForeground(Color.BLACK);
		lblProfile.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblProfile.setBounds(0, 103, 120, 40);
		panel_Profile.add(lblProfile);
		JLabel lblIconProfile = new JLabel();
		lblIconProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

			}
		});
		lblIconProfile.setBounds(0, 11, 120, 81);
		ImageIcon imgIcon_Profile = new ImageIcon(LecturerSchedule.class.getResource("/resources/images/Profile.png"));
		Image image_Profile = imgIcon_Profile.getImage();
		Image newImage_Profile = image_Profile.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblIconProfile.setIcon(new ImageIcon(newImage_Profile));
		genericStuff.hover(lblIconProfile, lblProfile, panel_Profile, new Color(230, 230, 250), Color.LIGHT_GRAY,
				Color.BLACK, Color.WHITE);
		panel_Profile.add(lblIconProfile);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(130, 0, 304, 154);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblFullName = new JLabel("Họ Tên:");
		lblFullName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblFullName.setBounds(10, 11, 100, 14);
		panel.add(lblFullName);

		JLabel lblGVNo = new JLabel("Mã Sinh Viên:");
		lblGVNo.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblGVNo.setBounds(10, 36, 100, 14);
		panel.add(lblGVNo);

		JLabel lblUsername = new JLabel("Tài khoản:");
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblUsername.setBounds(10, 61, 100, 14);
		panel.add(lblUsername);

		JLabel lblGender = new JLabel("Giới Tính: ");
		lblGender.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblGender.setBounds(10, 86, 100, 14);
		panel.add(lblGender);

		JLabel lblCMND = new JLabel("CMND:");
		lblCMND.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblCMND.setBounds(10, 111, 100, 14);
		panel.add(lblCMND);

		JLabel lblName = new JLabel(getSinhVien().get_ten());
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblName.setBounds(120, 12, 174, 14);
		panel.add(lblName);

		JLabel lblLecCode = new JLabel(String.valueOf(getSinhVien().get_mssv()));
		lblLecCode.setHorizontalAlignment(SwingConstants.LEFT);
		lblLecCode.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblLecCode.setBounds(120, 37, 174, 14);
		panel.add(lblLecCode);

		JLabel lblLecUN = new JLabel(getSinhVien().get_mssv());
		lblLecUN.setHorizontalAlignment(SwingConstants.LEFT);
		lblLecUN.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblLecUN.setBounds(120, 62, 174, 14);
		panel.add(lblLecUN);

		JLabel lblLecGen = new JLabel(getSinhVien().get_gioiTinh());
		lblLecGen.setHorizontalAlignment(SwingConstants.LEFT);
		lblLecGen.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblLecGen.setBounds(120, 87, 174, 14);
		panel.add(lblLecGen);

		JLabel lblLecId = new JLabel(getSinhVien().get_cmnd());
		lblLecId.setHorizontalAlignment(SwingConstants.LEFT);
		lblLecId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblLecId.setBounds(120, 112, 174, 14);
		panel.add(lblLecId);

		JPanel panelChangePassword = new JPanel();
		panelChangePassword.setLayout(null);
		panelChangePassword.setBackground(Color.WHITE);
		panelChangePassword.setBounds(130, 200, 100, 110);
		contentPane.add(panelChangePassword);
		JLabel lbliMtKhu = new JLabel("Đổi Mật Khẩu", SwingConstants.CENTER);
		lbliMtKhu.setForeground(Color.BLACK);
		lbliMtKhu.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lbliMtKhu.setBounds(0, 92, 100, 14);
		panelChangePassword.add(lbliMtKhu);
		JLabel lblIconChangePw = new JLabel();
		lblIconChangePw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ChangePassword changePassword = new ChangePassword(sinhVien, curFrame);
				genericStuff.call_dialog(changePassword);
			}
		});
		lblIconChangePw.setBounds(10, 11, 80, 80);
		ImageIcon imgIcon_ChangePw = new ImageIcon(LecturerSchedule.class.getResource("/resources/images/Edit.png"));
		Image image_ChangePw = imgIcon_ChangePw.getImage();
		Image newImage_ChangePw = image_ChangePw.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblIconChangePw.setIcon(new ImageIcon(newImage_ChangePw));
		genericStuff.hover(lblIconChangePw, lbliMtKhu, panelChangePassword, new Color(230, 230, 250), Color.LIGHT_GRAY,
				Color.BLACK, Color.WHITE);
		panelChangePassword.add(lblIconChangePw);

		JPanel panel_QuayLai = new JPanel();
		panel_QuayLai.setLayout(null);
		panel_QuayLai.setBackground(Color.WHITE);
		panel_QuayLai.setBounds(357, 200, 100, 110);
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
				StudentDashBoard studentDashBoard = new StudentDashBoard(sinhVien);
				genericStuff.call_frame(studentDashBoard);
			}
		});
		genericStuff.hover(lblIconBack, lblBack, panel_QuayLai, new Color(230, 230, 250), Color.LIGHT_GRAY, Color.BLACK,
				Color.WHITE);
	}

	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		event_listener();
	}

}
