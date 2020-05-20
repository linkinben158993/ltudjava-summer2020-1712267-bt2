package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class Home extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textLoginName;
	private JTextField textLoginPassword;
	private JPasswordField passwordLoginPassword;

	// Đang kéo thả tại tọa độ x y
	public int draggedAtX;
	public int draggedAtY;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		// Lấy vị trí hiện tại của con trỏ JFrame
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				draggedAtX = e.getX();
				draggedAtY = e.getY();
				System.out.println(draggedAtX);
				System.out.println(draggedAtY);
			}
		});
		// Kéo thả Jpanel khi đã có vị trí hiện tại của con JFrame e.getXOnScreen()
		// e.getYOnScreen() trên màn hình
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				System.out.println(x);
				System.out.println(y);
				// Set location cho JFrame dựa trên vị trí con trỏ chuột đang nằm ở JFrame và vị trí của JFrame trên màn hình
				setLocation(x - draggedAtX, y - draggedAtY);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 647, 512);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 5, 435, 468);
		contentPane.add(panel);

		JLabel label = new JLabel("");
		label.setBounds(0, 11, 434, 360);
		label.setVerticalAlignment(SwingConstants.TOP);
		ImageIcon imgIcon = new ImageIcon(Home.class.getResource("/resources/Sholom.jpg"));
		Image image = imgIcon.getImage();
		Image newImage = image.getScaledInstance(435, 360, java.awt.Image.SCALE_SMOOTH);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel(" LIFE IS SO MUCH EASIER TO DEAL WITH");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(103, 384, 246, 19);
		panel.add(lblNewLabel);

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

		Button button = new Button("Đăng Nhập");
		button.setFont(new Font("Times New Roman", Font.BOLD, 15));
		button.setBackground(new Color(178, 34, 34));
		button.setBounds(485, 377, 102, 40);
		contentPane.add(button);

		JLabel lblLoginName = new JLabel("Tên Đăng Nhập");
		lblLoginName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblLoginName.setBackground(new Color(178, 34, 34));
		lblLoginName.setOpaque(true);
		lblLoginName.setBounds(445, 152, 115, 40);
		contentPane.add(lblLoginName);

		JLabel lblLoginPassword = new JLabel("Mật Khẩu");
		lblLoginPassword.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblLoginPassword.setBackground(new Color(178, 34, 34));
		lblLoginPassword.setOpaque(true);
		lblLoginPassword.setBounds(445, 269, 115, 40);
		contentPane.add(lblLoginPassword);

		textLoginName = new JTextField();
		textLoginName.setBackground(Color.LIGHT_GRAY);
		textLoginName.setBounds(445, 203, 182, 40);
		contentPane.add(textLoginName);
		textLoginName.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(445, 241, 182, 2);
		contentPane.add(separator);

		textLoginPassword = new JTextField();
		textLoginPassword.setColumns(10);
		textLoginPassword.setBackground(Color.LIGHT_GRAY);
		textLoginPassword.setBounds(445, 320, 182, 40);
		contentPane.add(textLoginPassword);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(445, 358, 182, 2);
		contentPane.add(separator_1);

		passwordLoginPassword = new JPasswordField();
		passwordLoginPassword.setBounds(445, 320, 182, 40);
		contentPane.add(passwordLoginPassword);

		Button button_1 = new Button("X");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		button_1.setBackground(Color.GRAY);
		button_1.setBounds(596, 5, 31, 26);
		contentPane.add(button_1);

		Button button_2 = new Button("-");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		button_2.setBackground(Color.GRAY);
		button_2.setBounds(559, 5, 31, 26);
		contentPane.add(button_2);
	}
}
