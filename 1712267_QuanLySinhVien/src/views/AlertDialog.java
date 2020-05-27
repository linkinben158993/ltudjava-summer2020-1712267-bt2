package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import constants.AlertConstants;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlertDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GenericStuff genericStuff = new GenericStuff();
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlertDialog frame = new AlertDialog("/resources/Alert.gif");
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

		Button button_exit = new Button("Thoát");
		button_exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		button_exit.setForeground(Color.WHITE);
		button_exit.setBackground(Color.GRAY);
		button_exit.setBounds(100, 278, 150, 20);
		contentPane.add(button_exit);
	}

	private void init(String path) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 347);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Alert");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		// "/resources/Alert.gif"
		lblNewLabel.setIcon(new ImageIcon(AlertDialog.class.getResource(path)));

		lblNewLabel.setBounds(20, 0, 335, 200);
		contentPane.add(lblNewLabel);

		String alert = this.getMessage();
		JLabel lblNewLabel_1 = new JLabel(alert, SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setBounds(0, 200, 355, 35);
		lblNewLabel_1.setOpaque(true);
		contentPane.add(lblNewLabel_1);

		event_listener();
	}

	// Init với biến cho trường message
	/**
	 * @wbp.parser.constructor
	 */
	public AlertDialog(String message, String path) {
		this.message = message;
		init(path);

		if (message.equals(AlertConstants.LEAVING_SO_SOON)) {
			Button button_stay = new Button("Ở Lại");
			button_stay.setForeground(Color.WHITE);
			button_stay.setBackground(Color.GRAY);
			button_stay.setBounds(100, 252, 150, 20);
			contentPane.add(button_stay);
			button_stay.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dispose();
				}
			});
		}
		if (message.equals(AlertConstants.BLANK_FIELD_WARNING)) {
			Button button_relogin = new Button("Đăng Nhập Lại");
			button_relogin.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dispose();
					Home home = new Home();
					genericStuff.call_frame(home);
				}
			});
			button_relogin.setForeground(Color.WHITE);
			button_relogin.setBackground(Color.GRAY);
			button_relogin.setBounds(100, 252, 150, 20);
			contentPane.add(button_relogin);
		}
	}

	public AlertDialog(String path) {
		init(path);
	}
}
