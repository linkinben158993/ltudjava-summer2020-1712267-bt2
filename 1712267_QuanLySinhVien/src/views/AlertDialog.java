package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
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
					AlertDialog frame = new AlertDialog();
					frame.setVisible(true);
					frame.setUndecorated(true);
					System.out.println(frame.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void event_listener() {
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
		button_relogin.setBounds(100, 250, 150, 20);
		contentPane.add(button_relogin);
		
		Button button_exit = new Button("Thoát");
		button_exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		button_exit.setForeground(Color.WHITE);
		button_exit.setBackground(Color.GRAY);
		button_exit.setBounds(100, 276, 150, 20);
		contentPane.add(button_exit);

	}

	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 351);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Alert");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(AlertDialog.class.getResource("/resources/Alert.gif")));

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
	public AlertDialog(String message) {
		this.message = message;
		init();
	}

	public AlertDialog() {
		init();
	}
}
