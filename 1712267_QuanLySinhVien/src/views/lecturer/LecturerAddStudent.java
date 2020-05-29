package views.lecturer;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import constants.AlertConstants;
import dao.LopDao;
import dao.SinhVienDao;
import entity.Lop;
import entity.SinhVien;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Color;
import java.awt.Font;

public class LecturerAddStudent extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textTen;
	private JTextField textMSSV;
	private JTextField textCMND;

	@SuppressWarnings("rawtypes")
	private JList list;
	private JTextField textLop;
	private List<Lop> lops;

	@SuppressWarnings("rawtypes")
	public JList getList() {
		return list;
	}

	@SuppressWarnings("rawtypes")
	public void setList(JList list) {
		this.list = list;
	}

	public JTextField getTextLop() {
		return textLop;
	}

	public void setTextLop(JTextField textLop) {
		this.textLop = textLop;
	}

	// �?ang kéo thả tại t�?a độ x y
	public int draggedAtX;
	public int draggedAtY;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LecturerAddStudent frame = new LecturerAddStudent();
					frame.setTitle("Thêm sinh viên mới.");
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LecturerAddStudent() {
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void event_listener() {
		LopDao lopDao = new LopDao();
		lops = lopDao.findAll();
		if (!lops.isEmpty()) {

			DefaultListModel<String> listModel = new DefaultListModel<String>();
			for (Lop item : lops) {
				listModel.addElement(item.get_maLop());
			}
			list = new JList(listModel);

			list.setBounds(120, 200, 150, 150);
			list.setFont(new Font("Tahoma", Font.BOLD, 11));
			list.setBackground(Color.LIGHT_GRAY);
			contentPane.add(list);
		} else {
			textLop = new JTextField();
			textLop.setColumns(10);
			textLop.setBounds(120, 220, 140, 20);
			contentPane.add(textLop);
		}

		JRadioButton rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setBackground(Color.LIGHT_GRAY);
		rdbtnNam.setBounds(120, 140, 70, 23);
		contentPane.add(rdbtnNam);

		JRadioButton rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setBackground(Color.LIGHT_GRAY);
		rdbtnNu.setBounds(192, 140, 70, 23);
		contentPane.add(rdbtnNu);

		JButton btnNewButton = new JButton("Thêm Mới");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (lops.isEmpty()) {

					String tenSv = textTen.getText();
					String mssv = textMSSV.getText();
					String cmnd = textCMND.getText();
					String lop = textLop.getText();
					boolean pass = validate_Form(tenSv, mssv, cmnd, lop, rdbtnNam, rdbtnNu);
					if (pass) {

						SinhVien newSinhVien = new SinhVien();
						newSinhVien.set_ten(tenSv);
						newSinhVien.set_mssv(mssv);
						newSinhVien.set_cmnd(cmnd);
						newSinhVien.setMa_lop(lop);

						if (rdbtnNam.isSelected()) {
							newSinhVien.set_gioiTinh(rdbtnNam.getText());
						} else {
							newSinhVien.set_gioiTinh(rdbtnNu.getText());
						}

						newSinhVien.setMa_quyen(2);

						SinhVienDao sinhVienDao = new SinhVienDao();
						LopDao lopDao = new LopDao();
						sinhVienDao.insert(newSinhVien);
						lopDao.insert(new Lop(lop, "CQ" + lop, "Chính Quy"));

						JOptionPane.showMessageDialog(null, "Thêm mới thành công!");
						dispose();

					} else {
						JOptionPane.showMessageDialog(null, "Thêm mới thất bại!");
					}

				} else {

					if (list.getSelectedValuesList().size() == 0) {

						JOptionPane.showMessageDialog(null, AlertConstants.BLANK_FIELD_WARNING_CLASS_UNCHECK);

					} else {

						String tenSv = textTen.getText();
						String mssv = textMSSV.getText();
						String cmnd = textCMND.getText();
						String lop = list.getSelectedValue().toString();
						boolean pass = validate_Form(tenSv, mssv, cmnd, lop, rdbtnNam, rdbtnNu);
						if (pass) {
							SinhVien newSinhVien = new SinhVien();
							newSinhVien.set_ten(tenSv);
							newSinhVien.set_mssv(mssv);
							newSinhVien.set_cmnd(cmnd);
							newSinhVien.setMa_lop(lop);

							if (rdbtnNam.isSelected()) {
								newSinhVien.set_gioiTinh(rdbtnNam.getText());
							} else {
								newSinhVien.set_gioiTinh(rdbtnNu.getText());
							}

							newSinhVien.setMa_quyen(2);

							SinhVienDao sinhVienDao = new SinhVienDao();
							sinhVienDao.insert(newSinhVien);

							JOptionPane.showMessageDialog(null, "Thêm mới thành công!");
							dispose();

						} else {
							JOptionPane.showMessageDialog(null, "Thêm mới thất bại!");
						}

					}
				}
			}
		});
		btnNewButton.setBounds(10, 230, 100, 30);
		contentPane.add(btnNewButton);

		JButton btnLpMi = new JButton("Lớp Mới");
		btnLpMi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Bỏ JList
				contentPane.remove(getList());
				((DefaultListModel) getList().getModel()).clear();
				contentPane.repaint();
				lops.removeAll(lops);

				textLop = new JTextField();
				textLop.setColumns(10);
				textLop.setBackground(Color.LIGHT_GRAY);
				textLop.setBounds(120, 195, 140, 20);
				contentPane.add(textLop);
			}
		});
		btnLpMi.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLpMi.setBackground(Color.LIGHT_GRAY);
		btnLpMi.setBounds(10, 313, 100, 30);
		contentPane.add(btnLpMi);

		JLabel lblLopKhac = new JLabel("Lớp khác:");
		lblLopKhac.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLopKhac.setBounds(10, 270, 100, 30);
		contentPane.add(lblLopKhac);
	}

	private void init() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 390);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tên Sinh Viên:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 10, 100, 30);
		contentPane.add(lblNewLabel);

		textTen = new JTextField();
		textTen.setBackground(Color.LIGHT_GRAY);
		textTen.setBounds(120, 15, 140, 20);
		contentPane.add(textTen);
		textTen.setColumns(10);

		JLabel lblMSSinh = new JLabel("Mã số sinh viên:");
		lblMSSinh.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMSSinh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMSSinh.setBounds(10, 50, 100, 30);
		contentPane.add(lblMSSinh);

		textMSSV = new JTextField();
		textMSSV.setBackground(Color.LIGHT_GRAY);
		textMSSV.setColumns(10);
		textMSSV.setBounds(120, 55, 140, 20);
		contentPane.add(textMSSV);

		JLabel lblCmnd = new JLabel("CMND:");
		lblCmnd.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCmnd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCmnd.setBounds(10, 95, 100, 30);
		contentPane.add(lblCmnd);

		textCMND = new JTextField();
		textCMND.setBackground(Color.LIGHT_GRAY);
		textCMND.setColumns(10);
		textCMND.setBounds(120, 100, 140, 20);
		contentPane.add(textCMND);

		JLabel lblGiiTnh = new JLabel("Giới tính:");
		lblGiiTnh.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGiiTnh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGiiTnh.setBounds(10, 135, 100, 30);
		contentPane.add(lblGiiTnh);

		JLabel lblLp = new JLabel("Lớp:");
		lblLp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLp.setBounds(10, 190, 100, 30);
		contentPane.add(lblLp);

		event_listener();
	}

	private boolean validate_Form(String tenSv, String mssv, String cmnd, String lop, JRadioButton rdbtnNam,
			JRadioButton rdbtnNu) {
		try {
			if (tenSv.isEmpty()) {
				JOptionPane.showMessageDialog(null, AlertConstants.BLANK_FIELD_WARNING_NAME);
				return false;
			} else if (mssv.isEmpty()) {
				JOptionPane.showMessageDialog(null, AlertConstants.BLANK_FIELD_WARNING_MSSV);
				return false;
			} else if (cmnd.isEmpty()) {
				JOptionPane.showMessageDialog(null, AlertConstants.BLANK_FIELD_WARNING_CMND);
				return false;
			} else if (!rdbtnNam.isSelected() && !rdbtnNu.isSelected()) {
				JOptionPane.showMessageDialog(null, AlertConstants.BLANK_FIELD_WARNING_GENDER);
				return false;
			} else if (lop.isEmpty()) {
				JOptionPane.showMessageDialog(null, AlertConstants.BLANK_FIELD_WARNING_CLASS);
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
