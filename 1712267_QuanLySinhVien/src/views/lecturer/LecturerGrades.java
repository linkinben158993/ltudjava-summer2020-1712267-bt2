package views.lecturer;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Diem;
import entity.GiaoVu;
import util.FileParser;
import views.GenericStuff;
import views.Login;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class LecturerGrades extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GenericStuff genericStuff = new GenericStuff();

	private GiaoVu giaoVu;

	public int draggedAtX;
	public int draggedAtY;
	private JTable table;

	public GiaoVu getGiaoVu() {
		return giaoVu;
	}

	public void setGiaoVu(GiaoVu giaoVu) {
		this.giaoVu = giaoVu;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LecturerGrades frame = new LecturerGrades(new GiaoVu());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LecturerGrades(GiaoVu giaoVu) {
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
		JPanel panelImport = new JPanel();
		panelImport.setLayout(null);
		panelImport.setBackground(Color.WHITE);
		panelImport.setBounds(10, 11, 100, 110);
		contentPane.add(panelImport);
		JLabel lblNhpFile = new JLabel("Nhập File CSV", SwingConstants.CENTER);
		lblNhpFile.setForeground(Color.BLACK);
		lblNhpFile.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNhpFile.setBounds(0, 86, 100, 14);
		panelImport.add(lblNhpFile);
		JLabel lblIconImport = new JLabel();
		lblIconImport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();

				FileParser fileParser = new FileParser();

				JFileChooser jFileChooser = genericStuff.chooseFile("Chọn File CSV Để Thêm Điểm!");
				int result = jFileChooser.showSaveDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {
					List<Diem> diems = fileParser.readFromCSV_Lop(jFileChooser.getSelectedFile().getAbsolutePath());

					if (diems.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Nhập điểm thất bại!");
						LecturerGrades lecturerGrades = new LecturerGrades(giaoVu);
						genericStuff.call_frame(lecturerGrades);
					} else {
						JOptionPane.showMessageDialog(null, "Nhập điểm thành công!");
						LecturerGrades lecturerGrades = new LecturerGrades(giaoVu);
						genericStuff.call_frame(lecturerGrades);
					}
				} else {
					LecturerGrades lecturerGrades = new LecturerGrades(giaoVu);
					genericStuff.call_frame(lecturerGrades);
				}
			}
		});
		lblIconImport.setBounds(10, 5, 80, 80);
		ImageIcon imgIcon_Import = new ImageIcon(Login.class.getResource("/resources/images/Import.png"));
		Image image_Import = imgIcon_Import.getImage();
		Image newImage_Import = image_Import.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		lblIconImport.setIcon(new ImageIcon(newImage_Import));
		genericStuff.hover(lblIconImport, lblNhpFile, panelImport, new Color(230, 230, 250), Color.LIGHT_GRAY,
				Color.BLACK, Color.WHITE);
		panelImport.add(lblIconImport);

		JPanel panel_QuayLai = new JPanel();
		panel_QuayLai.setLayout(null);
		panel_QuayLai.setBackground(Color.WHITE);
		panel_QuayLai.setBounds(10, 240, 100, 110);
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(120, 11, 270, 231);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(407, 11, 270, 231);
		contentPane.add(scrollPane_1);

	}

	private void init() {
		setGiaoVu(giaoVu);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLtudJava = new JLabel("2020 LTUD Java - 1712267 Nguyễn Hoàng Thiên Ân");
		lblLtudJava.setHorizontalAlignment(SwingConstants.CENTER);
		lblLtudJava.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLtudJava.setBounds(133, 347, 330, 14);
		contentPane.add(lblLtudJava);
		event_listener();
	}
}
