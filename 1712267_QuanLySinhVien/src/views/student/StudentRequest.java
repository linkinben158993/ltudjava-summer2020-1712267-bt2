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
import javax.swing.border.EmptyBorder;

import dao.SinhVienDao;
import entity.DSSV_MON;
import entity.PhucKhao;
import entity.SinhVien;
import views.GenericStuff;
import views.Login;
import javax.swing.JButton;

public class StudentRequest extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public int draggedAtX;
	public int draggedAtY;

	private SinhVien sinhVien;
	private GenericStuff genericStuff = new GenericStuff();

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
					StudentRequest frame = new StudentRequest(new SinhVien());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StudentRequest(SinhVien sinhVien) {
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
		JPanel panel_QuayLai = new JPanel();
		panel_QuayLai.setLayout(null);
		panel_QuayLai.setBackground(Color.WHITE);
		panel_QuayLai.setBounds(345, 162, 100, 110);
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
		genericStuff.hover(lblIconBack, lblBack, panel_QuayLai, new Color(230, 230, 250), Color.DARK_GRAY, Color.BLACK,
				Color.LIGHT_GRAY);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SinhVienDao sinhVienDao = new SinhVienDao();
				for (SinhVien sinhVien : sinhVienDao.findAll()) {
					for (DSSV_MON dssv_MON : sinhVien.getDssv_MON()) {
						if (dssv_MON.getPhucKhaos().size() == 0) {
							continue;
						} else {
							System.out.println(dssv_MON.getSinhVien().get_mssv());
							for (PhucKhao phucKhao : dssv_MON.getPhucKhaos()) {
								System.out.println(phucKhao.getNoidung());
							}
						}
					}
				}
			}
		});
		btnNewButton.setBounds(335, 11, 89, 23);
		contentPane.add(btnNewButton);

		JPanel panel_Lich = new JPanel();
		panel_Lich.setLayout(null);
		panel_Lich.setBackground(Color.WHITE);
		panel_Lich.setBounds(10, 11, 100, 125);
		contentPane.add(panel_Lich);
		JLabel lblLchHc = new JLabel("<html>Điều Chỉnh Học Phần</html>", SwingConstants.CENTER);
		lblLchHc.setForeground(Color.BLACK);
		lblLchHc.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblLchHc.setBounds(0, 88, 100, 33);
		panel_Lich.add(lblLchHc);
		JLabel lblIconlichhoc = new JLabel();
		lblIconlichhoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RequestClass requestClass = new RequestClass(sinhVien);
				genericStuff.call_dialog(requestClass);
			}
		});
		lblIconlichhoc.setBounds(10, 11, 80, 80);
		ImageIcon imgIcon_Schedules = new ImageIcon(Login.class.getResource("/resources/images/Schedule.png"));
		Image image_Schedules = imgIcon_Schedules.getImage();
		Image newImage_Schedules = image_Schedules.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblIconlichhoc.setIcon(new ImageIcon(newImage_Schedules));
		panel_Lich.add(lblIconlichhoc);
		genericStuff.hover(lblIconlichhoc, lblLchHc, panel_Lich, new Color(230, 230, 250), Color.LIGHT_GRAY,
				Color.BLACK, Color.WHITE);

		JPanel panel_Diem = new JPanel();
		panel_Diem.setLayout(null);
		panel_Diem.setBackground(Color.WHITE);
		panel_Diem.setBounds(160, 11, 100, 125);
		contentPane.add(panel_Diem);
		JLabel lblimS = new JLabel("<html>Phúc Khảo Điểm Số</html>", SwingConstants.CENTER);
		lblimS.setForeground(Color.BLACK);
		lblimS.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblimS.setBounds(0, 86, 100, 39);
		panel_Diem.add(lblimS);
		JLabel lblIcondiem = new JLabel();
		lblIcondiem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		lblIcondiem.setBounds(10, 11, 80, 80);
		ImageIcon imgIcon_Scores = new ImageIcon(Login.class.getResource("/resources/images/Scores.png"));
		Image image_Scores = imgIcon_Scores.getImage();
		Image newImage_Scores = image_Scores.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblIcondiem.setIcon(new ImageIcon(newImage_Scores));
		panel_Diem.add(lblIcondiem);
		genericStuff.hover(lblIcondiem, lblimS, panel_Diem, new Color(230, 230, 250), Color.DARK_GRAY, Color.BLACK,
				Color.LIGHT_GRAY);

	}

	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		event_listener();
	}
}
