package views.lecturer;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import views.Login;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class LecturerStudents extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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

	public LecturerStudents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel SideBar = new JPanel();
		SideBar.setBounds(0, 0, 85, 480);
		SideBar.setBackground(new Color(128, 0, 0));
		contentPane.add(SideBar);

		JPanel SearchBar = new JPanel();
		SearchBar.setBounds(85, 0, 670, 60);
		SearchBar.setBackground(new Color(178, 34, 34));
		contentPane.add(SearchBar);

		JPanel MiddleBar = new JPanel();
		MiddleBar.setBounds(85, 60, 250, 420);
		MiddleBar.setBackground(new Color(178, 34, 34));
		contentPane.add(MiddleBar);
		MiddleBar.setLayout(null);

		JLabel lblThem = new JLabel();
		lblThem.setBounds(10, 11, 70, 70);
		MiddleBar.add(lblThem);
		ImageIcon imgIcon_Them = new ImageIcon(Login.class.getResource("/resources/images/Add.png"));
		Image image_Them = imgIcon_Them.getImage();
		Image newImage_Them = image_Them.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblThem.setIcon(new ImageIcon(newImage_Them));

		JLabel lblSua = new JLabel();
		lblSua.setBounds(90, 11, 70, 70);
		MiddleBar.add(lblSua);
		ImageIcon imgIcon_Sua = new ImageIcon(Login.class.getResource("/resources/images/Edit.png"));
		Image image_Sua = imgIcon_Sua.getImage();
		Image newImage_Sua = image_Sua.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblSua.setIcon(new ImageIcon(newImage_Sua));
		
		JLabel lblXoa = new JLabel();
		lblXoa.setBounds(170, 11, 70, 70);
		MiddleBar.add(lblXoa);
		ImageIcon imgIcon_Xoa = new ImageIcon(Login.class.getResource("/resources/images/Minus.png"));
		Image image_Xoa = imgIcon_Xoa.getImage();
		Image newImage_Xoa = image_Xoa.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblXoa.setIcon(new ImageIcon(newImage_Xoa));
	}
}
