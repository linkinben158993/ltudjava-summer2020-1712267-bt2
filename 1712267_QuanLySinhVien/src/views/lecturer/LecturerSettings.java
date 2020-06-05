package views.lecturer;

import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import entity.GiaoVu;
import views.GenericStuff;
import javax.swing.border.EtchedBorder;

public class LecturerSettings extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GiaoVu giaoVu;

	public GiaoVu getGiaoVu() {
		return giaoVu;
	}

	public void setGiaoVu(GiaoVu giaoVu) {
		this.giaoVu = giaoVu;
	}

	private GenericStuff genericStuff = new GenericStuff();

	public int draggedAtX;
	public int draggedAtY;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LecturerSettings frame = new LecturerSettings(new GiaoVu());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LecturerSettings(GiaoVu giaoVu) {
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

	private void event_listener() {
		contentPane.setLayout(null);

		JPanel panel_Profile = new JPanel();
		panel_Profile.setLayout(null);
		panel_Profile.setBackground(Color.WHITE);
		panel_Profile.setBounds(0, 0, 120, 154);
		contentPane.add(panel_Profile);
		JLabel lblProfile = new JLabel(getGiaoVu().get_ten(), SwingConstants.CENTER);
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

		JPanel panel_QuayLai = new JPanel();
		panel_QuayLai.setLayout(null);
		panel_QuayLai.setBackground(Color.WHITE);
		panel_QuayLai.setBounds(334, 165, 100, 110);
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

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(130, 0, 304, 154);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblFullName = new JLabel("Họ Tên:");
		lblFullName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblFullName.setBounds(10, 11, 100, 14);
		panel.add(lblFullName);

		JLabel lblGVNo = new JLabel("Mã Giáo Vụ:");
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

		JLabel lblName = new JLabel(getGiaoVu().get_ten());
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblName.setBounds(120, 12, 100, 14);
		panel.add(lblName);

		JLabel lblLecCode = new JLabel(String.valueOf(getGiaoVu().get_gvNo()));
		lblLecCode.setHorizontalAlignment(SwingConstants.LEFT);
		lblLecCode.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblLecCode.setBounds(120, 37, 100, 14);
		panel.add(lblLecCode);

		JLabel lblLecUN = new JLabel(getGiaoVu().get_msgv());
		lblLecUN.setHorizontalAlignment(SwingConstants.LEFT);
		lblLecUN.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblLecUN.setBounds(120, 62, 100, 14);
		panel.add(lblLecUN);

		JLabel lblLecGen = new JLabel(getGiaoVu().get_gioiTinh());
		lblLecGen.setHorizontalAlignment(SwingConstants.LEFT);
		lblLecGen.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblLecGen.setBounds(120, 87, 100, 14);
		panel.add(lblLecGen);

		JLabel lblLecId = new JLabel(getGiaoVu().get_cmnd());
		lblLecId.setHorizontalAlignment(SwingConstants.LEFT);
		lblLecId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblLecId.setBounds(120, 112, 100, 14);
		panel.add(lblLecId);

		JPanel panelChangePassword = new JPanel();
		panelChangePassword.setLayout(null);
		panelChangePassword.setBackground(Color.WHITE);
		panelChangePassword.setBounds(130, 165, 100, 110);
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
				LecturerChangePassword changePassword = new LecturerChangePassword(giaoVu);
				changePassword.setModalityType(ModalityType.APPLICATION_MODAL);
				changePassword.setLocationRelativeTo(null);
				changePassword.setVisible(true);

				changePassword.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						dispose();
					}
				});
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
	}

	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblLtudJava = new JLabel("2020 LTUD Java - 1712267 Nguyễn Hoàng Thiên Ân");
		lblLtudJava.setHorizontalAlignment(SwingConstants.CENTER);
		lblLtudJava.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLtudJava.setBounds(48, 287, 330, 14);
		contentPane.add(lblLtudJava);

		event_listener();
	}
}
