package views.lecturer;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.mindrot.jbcrypt.BCrypt;

import constants.AlertConstants;
import dao.LopDao;
import dao.SinhVienDao;
import entity.GiaoVu;
import entity.Lop;
import entity.SinhVien;
import views.GenericStuff;

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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LecturerAddStudent extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textTen;
	private JTextField textMSSV;
	private JTextField textCMND;
	private GenericStuff genericStuff = new GenericStuff();

	@SuppressWarnings("rawtypes")
	private JList list;
	private JTextField textLop;
	private List<Lop> lops;
	private GiaoVu giaoVu;

	private JRadioButton rdbtnNam = new JRadioButton("Nam");
	private JRadioButton rdbtnNu = new JRadioButton("Nữ");

	public GiaoVu getGiaoVu() {
		return giaoVu;
	}

	public void setGiaoVu(GiaoVu giaoVu) {
		this.giaoVu = giaoVu;
	}

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
					LecturerAddStudent frame = new LecturerAddStudent(new GiaoVu());
					frame.setTitle("Thêm sinh viên mới.");
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LecturerAddStudent(GiaoVu giaoVu) {
		setGiaoVu(giaoVu);

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

		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				int jOptionPane = JOptionPane.showConfirmDialog(null, "Thoát thêm mới sinh viên?", "Đóng cửa sổ!",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (jOptionPane == JOptionPane.YES_OPTION) {
					genericStuff.call_frame(new LecturerStudents(giaoVu));
				} else {
					LecturerAddStudent frame = new LecturerAddStudent(new GiaoVu());
					frame.setTitle("Thêm sinh viên mới.");
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
			}

			@Override
			public void windowClosed(WindowEvent e) {

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});

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

		rdbtnNam.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdbtnNu.isSelected()) {
					rdbtnNu.setSelected(false);
				} else {
					rdbtnNam.setSelected(true);
				}
			}
		});
		rdbtnNam.setBackground(Color.LIGHT_GRAY);
		rdbtnNam.setBounds(120, 140, 70, 23);
		contentPane.add(rdbtnNam);

		rdbtnNu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdbtnNam.isSelected()) {
					rdbtnNam.setSelected(false);
				} else {
					rdbtnNu.setSelected(true);
				}
			}
		});
		rdbtnNu.setBackground(Color.LIGHT_GRAY);
		rdbtnNu.setBounds(192, 140, 70, 23);
		contentPane.add(rdbtnNu);

		JButton btnNewButton = new JButton("Thêm Mới");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent e) {
				// Clear list bên dưới nhưng vẫn giữ lại danh sách lớp để check nếu người dùng
				// nhập trùng
				if (((DefaultListModel) getList().getModel()).isEmpty()) {

					String tenSv = textTen.getText();
					String mssv = textMSSV.getText();
					String cmnd = textCMND.getText();
					String lop = textLop.getText();
					String hashed = BCrypt.hashpw(mssv, BCrypt.gensalt(12));

					// Kiểm tra người dùng có nhập lớp bị trùng với danh sách lớp nào không
					String lopFinal = lop.toUpperCase();
					Lop lopExisted = new Lop();
					lopExisted.set_maLop(lopFinal);
					lopExisted = lopExisted.findByML(lops, lopExisted);

					// Người dùng nhập lớp đã tồn tại confirm thêm mới sinh viên
					if (lopExisted != null) {
						JOptionPane confirm = new JOptionPane();
						int res = confirm.showOptionDialog(null, "Lớp đã tồn tại!",
								"Thêm mới sinh viên vào lớp " + lopExisted.get_maLop(), JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.INFORMATION_MESSAGE, null, new String[] { "Thêm", "Hủy" }, "Mặc Định");

						if (res == 0) {
							add_Student(tenSv, mssv, cmnd, hashed, lopFinal, rdbtnNam, rdbtnNu);
						} else if (res == 1 || res == -1) {
							// Chưa cần làm gì chỗ này
						}

					}

					// Lớp chưa tồn tại và người dùng đồng ý thêm lớp mới
					else {

						LopDao lopDao = new LopDao();
						lopDao.insert(new Lop(lop, "CQ" + lop, "Chính Quy"));
						add_Student(tenSv, mssv, cmnd, hashed, lopFinal, rdbtnNam, rdbtnNu);
					}

				}
				// Người dùng không check thêm lớp mới
				else {

					if (list.getSelectedValuesList().size() == 0) {

						JOptionPane.showMessageDialog(null, AlertConstants.BLANK_FIELD_WARNING_CLASS_UNCHECK);

					} else {

						String tenSv = textTen.getText();
						String mssv = textMSSV.getText();
						String cmnd = textCMND.getText();
						String lop = list.getSelectedValue().toString();
						String hashed = BCrypt.hashpw(cmnd, BCrypt.gensalt(12));
						add_Student(tenSv, mssv, cmnd, hashed, lop, rdbtnNam, rdbtnNu);

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

				if (textLop != null) {
					// Tránh render lại mất dữ liệu người dùng nhập hay jtext bị đè. Chưa cần làm gì
					// thêm ở đây
				} else {

					textLop = new JTextField();
					textLop.setColumns(10);
					textLop.setBackground(Color.LIGHT_GRAY);
					textLop.setBounds(120, 195, 140, 20);
					contentPane.add(textLop);

				}
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

	// Hàm validate các trường trước khi kiểm thêm mới sinh viên
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

	// Hàm thêm mới sinh viên sau khi đã validate xong các trường.
	private void add_Student(String tenSv, String mssv, String cmnd, String hashed, String lop, JRadioButton rdbtnNam,
			JRadioButton rdbtnNu) {
		boolean pass = validate_Form(tenSv, mssv, cmnd, lop, rdbtnNam, rdbtnNu);
		if (pass) {

			SinhVien newSinhVien = new SinhVien();
			newSinhVien.set_ten(tenSv);
			newSinhVien.set_mssv(mssv);
			newSinhVien.set_cmnd(cmnd);
			newSinhVien.setMa_lop(lop);
			newSinhVien.set_password(hashed);

			if (rdbtnNam.isSelected()) {
				newSinhVien.set_gioiTinh(rdbtnNam.getText());
			} else {
				newSinhVien.set_gioiTinh(rdbtnNu.getText());
			}

			newSinhVien.setMa_quyen(2);

			SinhVienDao sinhVienDao = new SinhVienDao();

			SinhVien foundSinhVien = newSinhVien.existed(sinhVienDao.findAll(), newSinhVien);
			if (foundSinhVien == null) {
				sinhVienDao.insert(newSinhVien);
				JOptionPane.showMessageDialog(null, "Thêm mới thành công!");
				dispose();
				genericStuff.call_frame(new LecturerStudents(giaoVu));

			} else {
				JOptionPane.showMessageDialog(null, "Sinh viên tồn tại!");
			}

		} else {
			JOptionPane.showMessageDialog(null, "Thêm mới thất bại!");
			dispose();
			genericStuff.call_frame(new LecturerStudents(giaoVu));

		}
	}
}
