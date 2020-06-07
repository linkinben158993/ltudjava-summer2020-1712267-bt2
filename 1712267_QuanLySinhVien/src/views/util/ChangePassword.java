package views.util;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.mindrot.jbcrypt.BCrypt;

import entity.GiaoVu;
import entity.SinhVien;
import views.GenericStuff;
import views.Login;
import views.lecturer.LecturerSettings;
import views.student.StudentSettings;
import dao.GiaoVuDao;
import dao.SinhVienDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChangePassword extends JDialog {

	private static final long serialVersionUID = 1L;

	private GenericStuff genericStuff = new GenericStuff();
	private SinhVien sinhVien;

	public SinhVien getSinhVien() {
		return sinhVien;
	}

	public void setSinhVien(SinhVien sinhVien) {
		this.sinhVien = sinhVien;
	}

	private GiaoVu giaoVu;

	public GiaoVu getGiaoVu() {
		return giaoVu;
	}

	public void setGiaoVu(GiaoVu giaoVu) {
		this.giaoVu = giaoVu;
	}

	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordOld;
	private JPasswordField passwordNew;
	private JPasswordField passwordNewConfirm;

	public static void main(String[] args) {
		try {
			ChangePassword dialog = new ChangePassword(new GiaoVu());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ChangePassword(GiaoVu giaoVu) {
		this.giaoVu = giaoVu;
		setBounds(100, 100, 350, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblOldPassword = new JLabel("Nhập mật khẩu cũ:");
			lblOldPassword.setBounds(10, 11, 120, 30);
			contentPanel.add(lblOldPassword);
		}

		passwordOld = new JPasswordField();
		passwordOld.setBounds(140, 16, 184, 20);
		contentPanel.add(passwordOld);

		JLabel lblNewPassword = new JLabel("Nhập mật khẩu mới:");
		lblNewPassword.setBounds(10, 52, 120, 30);
		contentPanel.add(lblNewPassword);

		passwordNew = new JPasswordField();
		passwordNew.setBounds(140, 57, 184, 20);
		contentPanel.add(passwordNew);

		JLabel lblConfirmNewPw = new JLabel("Xác nhận mật khẩu mới:");
		lblConfirmNewPw.setBounds(10, 93, 120, 30);
		contentPanel.add(lblConfirmNewPw);

		passwordNewConfirm = new JPasswordField();
		passwordNewConfirm.setBounds(140, 98, 184, 20);
		contentPanel.add(passwordNewConfirm);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u0110\u1ED5i M\u1EADt Kh\u1EA9u");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						GiaoVuDao giaoVuDao = new GiaoVuDao();
						GiaoVu foundGV = giaoVuDao.findById(ChangePassword.this.giaoVu.get_gvNo());
						if (foundGV == null) {
							System.out.println("Lỗi!");
						} else {
							if (!BCrypt.checkpw(new String(passwordOld.getPassword()), foundGV.get_password())) {
								JOptionPane.showMessageDialog(null, "Mật khẩu cũ không đúng!");
							} else {
								if (!new String(passwordNew.getPassword())
										.equals(new String(passwordNewConfirm.getPassword()))) {
									JOptionPane.showMessageDialog(null, "Mật khẩu mới không khớp!");
								} else {

									int res = giaoVuDao.updatePassword(BCrypt
											.hashpw(new String(passwordNewConfirm.getPassword()), BCrypt.gensalt(12)),
											foundGV.get_gvNo());
									if (res < 0) {
										JOptionPane.showMessageDialog(null, "Cập nhật mật thất bại!");
									} else {
										JOptionPane.showMessageDialog(null,
												"Cập nhật mật khẩu thành công! Vui lòng đăng nhập lại!");
										dispose();
										Login login = new Login();
										genericStuff.call_frame(login);
									}
								}
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("H\u1EE7y");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
						LecturerSettings lecturerSettings = new LecturerSettings(giaoVu);
						genericStuff.call_frame(lecturerSettings);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	/**
	 * @wbp.parser.constructor
	 */
	public ChangePassword(SinhVien sinhVien) {
		this.sinhVien = sinhVien;
		setBounds(100, 100, 350, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblOldPassword = new JLabel("Nhập mật khẩu cũ:");
			lblOldPassword.setBounds(10, 11, 120, 30);
			contentPanel.add(lblOldPassword);
		}

		passwordOld = new JPasswordField();
		passwordOld.setBounds(140, 16, 184, 20);
		contentPanel.add(passwordOld);

		JLabel lblNewPassword = new JLabel("Nhập mật khẩu mới:");
		lblNewPassword.setBounds(10, 52, 120, 30);
		contentPanel.add(lblNewPassword);

		passwordNew = new JPasswordField();
		passwordNew.setBounds(140, 57, 184, 20);
		contentPanel.add(passwordNew);

		JLabel lblConfirmNewPw = new JLabel("Xác nhận mật khẩu mới:");
		lblConfirmNewPw.setBounds(10, 93, 120, 30);
		contentPanel.add(lblConfirmNewPw);

		passwordNewConfirm = new JPasswordField();
		passwordNewConfirm.setBounds(140, 98, 184, 20);
		contentPanel.add(passwordNewConfirm);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u0110\u1ED5i M\u1EADt Kh\u1EA9u");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						SinhVienDao sinhVienDao = new SinhVienDao();
						SinhVien foundSV = sinhVienDao.findById(ChangePassword.this.sinhVien.get_svNo());
						if (foundSV == null) {
							System.out.println("Lỗi!");
						} else {
							if (!BCrypt.checkpw(new String(passwordOld.getPassword()), sinhVien.get_password())) {
								JOptionPane.showMessageDialog(null, "Mật khẩu cũ không đúng!");
							} else {
								if (!new String(passwordNew.getPassword())
										.equals(new String(passwordNewConfirm.getPassword()))) {
									JOptionPane.showMessageDialog(null, "Mật khẩu mới không khớp!");
								} else {

									int res = sinhVienDao.updatePassword(BCrypt
											.hashpw(new String(passwordNewConfirm.getPassword()), BCrypt.gensalt(12)),
											foundSV.get_svNo());
									if (res < 0) {
										JOptionPane.showMessageDialog(null, "Cập nhật mật thất bại!");
									} else {
										JOptionPane.showMessageDialog(null,
												"Cập nhật mật khẩu thành công! Vui lòng đăng nhập lại!");
										dispose();
										Login login = new Login();
										genericStuff.call_frame(login);
									}
								}
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("H\u1EE7y");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
						StudentSettings studentSettings = new StudentSettings(sinhVien);
						genericStuff.call_frame(studentSettings);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
