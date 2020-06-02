package views.lecturer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import util.FileParser;
import views.GenericStuff;
import views.Login;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

public class LecturerSchedule extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GenericStuff genericStuff = new GenericStuff();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LecturerSchedule frame = new LecturerSchedule();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LecturerSchedule() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelImport = new JPanel();
		panelImport.setLayout(null);
		panelImport.setBackground(Color.WHITE);
		panelImport.setBounds(10, 5, 100, 110);
		contentPane.add(panelImport);
		JLabel lblNhpFile = new JLabel("Nhập File CSV", SwingConstants.CENTER);
		lblNhpFile.setForeground(Color.BLACK);
		lblNhpFile.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNhpFile.setBounds(0, 86, 100, 14);
		panelImport.add(lblNhpFile);
		JLabel lblIconImport = new JLabel();
		lblIconImport.setBounds(10, 5, 80, 80);
		ImageIcon imgIcon_Import = new ImageIcon(Login.class.getResource("/resources/images/Import.png"));
		Image image_Import = imgIcon_Import.getImage();
		Image newImage_Import = image_Import.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblIconImport.setIcon(new ImageIcon(newImage_Import));
		lblIconImport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma-separated Values", "csv");
				fileChooser.setFileFilter(filter);
				fileChooser.setCurrentDirectory(new File("."));
				fileChooser.setDialogTitle("Chọn File CSV Để Thêm Mới Sinh Viên");

				int results = fileChooser.showSaveDialog(null);

				if (results == JFileChooser.APPROVE_OPTION) {
					FileParser fileParser = new FileParser();
					fileParser.readFromCSV_Mon(fileChooser.getSelectedFile().getAbsolutePath());
				} else {
					System.out.println("Failed");
				}

			}
		});
		genericStuff.hover(lblIconImport, lblNhpFile, panelImport, new Color(230, 230, 250), Color.LIGHT_GRAY,
				Color.BLACK, Color.WHITE);
		panelImport.add(lblIconImport);
	}
}
