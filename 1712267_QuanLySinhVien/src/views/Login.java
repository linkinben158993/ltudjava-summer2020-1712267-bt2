package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.mindrot.jbcrypt.BCrypt;

import constants.AlertConstants;
import dao.GiaoVuDao;
import dao.SinhVienDao;
import entity.GiaoVu;
import entity.SinhVien;
import views.lecturer.LecturerDashBoard;

import java.awt.Color;
import java.awt.Button;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private AlertDialog alertDialog;
	private GenericStuff genericStuff = new GenericStuff();
	private JTextField textLoginName;
	private JPasswordField passwordLoginPassword;

	// Đang kéo thả tại tọa độ x y
	public int draggedAtX;
	public int draggedAtY;

	// Main ở đây
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setLocationRelativeTo(null);
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Hàm lắng nghe sự kiện component nào có sự kiện thì ở đây
	private void event_listener() {
		textLoginName = new JTextField();
		textLoginName.setBackground(Color.LIGHT_GRAY);
		textLoginName.setBounds(445, 203, 182, 40);
		contentPane.add(textLoginName);
		textLoginName.setColumns(10);

		passwordLoginPassword = new JPasswordField();
		passwordLoginPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login(textLoginName.getText(), new String(passwordLoginPassword.getPassword()));
			}
		});
		passwordLoginPassword.setBackground(Color.LIGHT_GRAY);
		passwordLoginPassword.setBounds(445, 320, 182, 40);
		contentPane.add(passwordLoginPassword);

		Button exit = new Button("X");
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AlertDialog alert = new AlertDialog(AlertConstants.LEAVING_SO_SOON, AlertConstants.GOODBYE);
				genericStuff.call_frame(alert);
			}
		});
		exit.setForeground(Color.WHITE);
		exit.setFont(new Font("Times New Roman", Font.BOLD, 15));
		exit.setBackground(Color.GRAY);
		exit.setBounds(610, 0, 30, 25);
		contentPane.add(exit);

		Button minimize = new Button("-");
		minimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		minimize.setForeground(Color.WHITE);
		minimize.setFont(new Font("Times New Roman", Font.BOLD, 18));
		minimize.setBackground(Color.GRAY);
		minimize.setBounds(580, 0, 30, 25);
		contentPane.add(minimize);

		Button button_login = new Button("Đăng Nhập");
		// Bắt sự kiện click chuột vào đăng nhập
		button_login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String userName = textLoginName.getText();
				// Password đã được trả ra String (nếu không có new String() sẽ trả ra chuỗi
				// hashed)
				String password = new String(passwordLoginPassword.getPassword());

				login(userName, password);
			}
		});
		button_login.setFont(new Font("Times New Roman", Font.BOLD, 15));
		button_login.setBackground(new Color(178, 34, 34));
		button_login.setBounds(485, 377, 102, 40);
		contentPane.add(button_login);
	}

	// Hàm khởi tạo các thành phần ở Home
	private void init() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 5, 435, 470);
		contentPane.add(panel);

		JLabel label = new JLabel("");
		label.setBounds(0, 11, 434, 360);
		label.setVerticalAlignment(SwingConstants.TOP);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel(" LIFE IS SO MUCH EASIER TO DEAL WITH");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(103, 384, 246, 19);
		panel.add(lblNewLabel);

		// Resize
		ImageIcon imgIcon = new ImageIcon(Login.class.getResource("/resources/images/Sholom.jpg"));
		Image image = imgIcon.getImage();
		Image newImage = image.getScaledInstance(435, 360, java.awt.Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(newImage));
		panel.add(label);

		JLabel lblWhenYouAre = new JLabel("WHEN YOU ARE DEAD.");
		lblWhenYouAre.setVerticalAlignment(SwingConstants.TOP);
		lblWhenYouAre.setForeground(Color.WHITE);
		lblWhenYouAre.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblWhenYouAre.setBounds(151, 408, 141, 19);
		panel.add(lblWhenYouAre);

		JLabel lblalfieSolomons = new JLabel("'...ALFIE SOLOMONS...'");
		lblalfieSolomons.setVerticalAlignment(SwingConstants.TOP);
		lblalfieSolomons.setForeground(Color.WHITE);
		lblalfieSolomons.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblalfieSolomons.setBounds(151, 438, 141, 19);
		panel.add(lblalfieSolomons);

		JLabel lblLoginName = new JLabel("Tên Đăng Nhập");
		lblLoginName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblLoginName.setBackground(new Color(178, 34, 34));
		// Để set background cho lbl
		lblLoginName.setOpaque(true);
		lblLoginName.setBounds(445, 152, 115, 40);
		contentPane.add(lblLoginName);

		JLabel lblLoginPassword = new JLabel("Mật Khẩu");
		lblLoginPassword.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblLoginPassword.setBackground(new Color(178, 34, 34));
		// Để set background cho lbl
		lblLoginPassword.setOpaque(true);
		lblLoginPassword.setBounds(445, 269, 115, 40);
		contentPane.add(lblLoginPassword);

		JSeparator separator = new JSeparator();
		separator.setBounds(445, 241, 182, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(445, 358, 182, 2);
		contentPane.add(separator_1);

		event_listener();
	}

	public Login() {
		SinhVienDao sinhVienDao = new SinhVienDao();
		GiaoVuDao giaoVuDao = new GiaoVuDao();

		// Insert người dùng mặc định
		SinhVien foundSinhVien = new SinhVien();
		GiaoVu foundGiaoVu = new GiaoVu();
		foundSinhVien.set_mssv("1712267");
		foundGiaoVu.set_msgv("giaovu");
		foundSinhVien = foundSinhVien.findByMSSV(sinhVienDao.findAll(), foundSinhVien);
		foundGiaoVu = foundGiaoVu.findByMSGV(giaoVuDao.findAll(), foundGiaoVu);

		if (foundSinhVien == null && foundGiaoVu == null) {
			sinhVienDao.insert(new SinhVien("1712267", BCrypt.hashpw("1712267", BCrypt.gensalt(12)),
					"Nguyễn Hoàng Thiên Ân", "Nam", "025852371", 2, "17CTT2"));
			sinhVienDao.insert(new SinhVien("1712286", BCrypt.hashpw("1712286", BCrypt.gensalt(12)), "Lê Hoài Bảo",
					"Nam", "09812342", 2, "17CTT2"));
			sinhVienDao.insert(new SinhVien("1712301", BCrypt.hashpw("1712301", BCrypt.gensalt(12)), "Nguyễn Hoàng Chiến",
					"Nam", "12349852", 2, "17CTT2"));

			giaoVuDao.insert(new GiaoVu("giaovu", BCrypt.hashpw("giaovu", BCrypt.gensalt(12)), "Trần Văn ABCXYZ", "Nam",
					"1357902468", 1));
		} else {
			System.out.println("TK Mặc Định Đã Tồn Tại.");
		}

		// Khởi tạo các thành phần cần thiết
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

	public void login(String userName, String password) {
		// Người dùng để trống
		if (userName.isEmpty() || password.isEmpty()) {
			dispose();
			alertDialog = new AlertDialog(AlertConstants.BLANK_FIELD_WARNING,
					AlertConstants.NOTFOUND_BLANK_WRONG_FIELD_PATH);
			genericStuff = new GenericStuff();
			genericStuff.call_frame(alertDialog);
		}

		else {
			// Lấy người dùng (sinh viên) dưới DB lên từ đây và so sánh các kiểu
			SinhVienDao sinhVienDao = new SinhVienDao();
			SinhVien sinhVien = new SinhVien();
			sinhVien.set_mssv(userName);
			sinhVien.set_password(password);
			List<SinhVien> sinhViens = sinhVienDao.findAll();

			SinhVien foundSinhVien = new SinhVien();
			foundSinhVien = foundSinhVien.findByMSSV(sinhViens, sinhVien);

			GiaoVuDao giaoVuDao = new GiaoVuDao();
			GiaoVu giaoVu = new GiaoVu();
			giaoVu.set_msgv(userName);
			giaoVu.set_msgv(password);
			List<GiaoVu> giaoVus = giaoVuDao.findAll();

			GiaoVu foundGiaoVu = new GiaoVu();
			foundGiaoVu = foundGiaoVu.findByMSGV(giaoVus, giaoVu);

			if (foundSinhVien == null && foundGiaoVu == null) {
				dispose();
				System.out.println("Không tìm thấy tài khoản!");
				alertDialog = new AlertDialog(AlertConstants.NOTFOUND_WARNINGS,
						AlertConstants.NOTFOUND_BLANK_WRONG_FIELD_PATH);
				genericStuff = new GenericStuff();
				genericStuff.call_frame(alertDialog);
			} else {

				if (foundGiaoVu != null) {
					System.out.println("Tìm thấy tài khoản giáo vụ!");
					System.out.println(foundGiaoVu.getQuyen_giaovu().get_tenQuyen());
					boolean pass = BCrypt.checkpw(password, foundGiaoVu.get_password());
					if (pass) {
						System.out.println("Mật khẩu đúng!");
						dispose();
						LecturerDashBoard lecturerDashBoard = new LecturerDashBoard(foundGiaoVu);
						genericStuff = new GenericStuff();
						genericStuff.call_frame(lecturerDashBoard);
					} else {
						System.out.println("Mật khẩu sai!");
					}
				} else {
					System.out.println("Tìm thấy tài khoản sinh viên!");
					System.out.println(foundSinhVien.getQuyen_sinhvien().get_tenQuyen());
					boolean pass = BCrypt.checkpw(password, foundSinhVien.get_password());
					System.out.println(foundSinhVien.get_password());
					if (pass) {
						System.out.println("Mật khẩu đúng!");

					} else {
						System.out.println("Mật khẩu sai!");
					}
				}
			}
		}
	}
}
