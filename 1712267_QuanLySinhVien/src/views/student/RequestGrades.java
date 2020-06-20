package views.student;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DotDao;
import dao.PhucKhaoDao;
import entity.DSL_MON;
import entity.DSSV_MON;
import entity.Diem;
import entity.Dot;
import entity.PhucKhao;
import entity.SinhVien;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;

public class RequestGrades extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtDiemMongMuon;

	private JComboBox<String> comboBoxLopMon;
	private JTextField txtLopMon;

	private JComboBox<String> comboBoxCotDiem;
	private JTextField txtCotDiem;
	private JTextField txtMo;
	private JTextField txtDong;

	public static void main(String[] args) {
		try {
			RequestGrades dialog = new RequestGrades(new SinhVien());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RequestGrades(SinhVien sinhVien) {
		setBounds(100, 100, 500, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblTitle = new JLabel("Điền Thông Tin Phúc Khảo");
		lblTitle.setOpaque(true);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTitle.setBackground(Color.GRAY);
		lblTitle.setBounds(85, 0, 280, 40);
		contentPanel.add(lblTitle);
		{
			JLabel lblLopMon = new JLabel("Môn muốn phúc khảo:");
			lblLopMon.setHorizontalAlignment(SwingConstants.RIGHT);
			lblLopMon.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblLopMon.setBounds(10, 51, 150, 40);
			contentPanel.add(lblLopMon);
		}
		{
			// Chọn lớp đang mở hiện tại
			Map<Integer, DSL_MON> options = new HashMap<Integer, DSL_MON>();
			List<DSL_MON> dsl_MONs = new ArrayList<DSL_MON>();
			for (DSSV_MON dssv_MON : sinhVien.getDssv_MON()) {
				System.out.println(dssv_MON.getDsl_MON().get_danhsachlopNo() + ": "
						+ dssv_MON.getDsl_MON().getMon_lop().get_tenMon());
				options.put(dssv_MON.getDsl_MON().get_danhsachlopNo(), dssv_MON.getDsl_MON());
				dsl_MONs.add(dssv_MON.getDsl_MON());
			}
			comboBoxLopMon = new JComboBox<String>();
			for (Integer id : options.keySet()) {
				comboBoxLopMon.addItem(options.get(id).getMalop_mon());
			}
			comboBoxLopMon.setSelectedItem(null);
			comboBoxLopMon.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					txtLopMon = new JTextField();
					txtLopMon.setColumns(10);
					txtLopMon.setEditable(false);
					txtLopMon.setBounds(310, 62, 164, 20);
					contentPanel.add(txtLopMon);

					DSL_MON dsl_MON = new DSL_MON();
					dsl_MON.setMalop_mon(comboBoxLopMon.getSelectedItem().toString());
					DSL_MON foundDSLM = new DSL_MON().findByMLM(dsl_MONs, dsl_MON);
					if (foundDSLM == null) {
						txtLopMon.setText("Không có môn!");
					} else {
						txtLopMon.setText(foundDSLM.getMon_lop().get_tenMon().toString());
					}

				}
			});
			comboBoxLopMon.setMaximumRowCount(3);
			comboBoxLopMon.setBounds(170, 62, 130, 20);
			contentPanel.add(comboBoxLopMon);
		}

		{
			JLabel lblCotDiem = new JLabel("Cột điểm phúc khảo:");
			lblCotDiem.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCotDiem.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblCotDiem.setBounds(10, 102, 150, 40);
			contentPanel.add(lblCotDiem);
		}
		{
			comboBoxCotDiem = new JComboBox<String>();
			comboBoxCotDiem.setMaximumRowCount(3);
			comboBoxCotDiem.setBounds(170, 113, 130, 20);
			comboBoxCotDiem.addItem("Cột 1");
			comboBoxCotDiem.addItem("Cột 2");
			comboBoxCotDiem.addItem("Cột 3");
			comboBoxCotDiem.setSelectedItem(null);
			comboBoxCotDiem.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					txtCotDiem = new JTextField();
					txtCotDiem.setEditable(false);
					txtCotDiem.setColumns(10);
					txtCotDiem.setBounds(310, 113, 164, 20);
					if (comboBoxLopMon.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(null, "Phải chọn lớp!");
						txtCotDiem.setText("Chọn lớp để xem điểm!");
					} else {
						for (Diem diem : sinhVien.getDiems()) {
							if (diem.getMaLop_mon().equals(comboBoxLopMon.getSelectedItem().toString())
									&& comboBoxCotDiem.getSelectedItem().toString().split(" ")[1].equals("1")) {
								txtCotDiem.setText(String.valueOf(diem.get_gk()));
							} else if (diem.getMaLop_mon().equals(comboBoxLopMon.getSelectedItem().toString())
									&& comboBoxCotDiem.getSelectedItem().toString().split(" ")[1].equals("2")) {
								txtCotDiem.setText(String.valueOf(diem.get_ck()));
							} else if (diem.getMaLop_mon().equals(comboBoxLopMon.getSelectedItem().toString())
									&& comboBoxCotDiem.getSelectedItem().toString().split(" ")[1].equals("3")) {
								txtCotDiem.setText(String.valueOf(diem.get_khac()));
							} else {
								txtCotDiem.setText("Chưa có điểm môn này!");
							}
						}
					}
					contentPanel.add(txtCotDiem);
				}
			});
			contentPanel.add(comboBoxCotDiem);
		}

		{
			JLabel lblDiemMongMuon = new JLabel("Điểm mong muốn:");
			lblDiemMongMuon.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDiemMongMuon.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblDiemMongMuon.setBounds(10, 153, 150, 40);
			contentPanel.add(lblDiemMongMuon);
		}

		txtDiemMongMuon = new JTextField();
		txtDiemMongMuon.setBounds(170, 164, 130, 20);
		contentPanel.add(txtDiemMongMuon);
		txtDiemMongMuon.setColumns(10);
		{
			JLabel lblDot = new JLabel("Đợt phúc khảo:");
			lblDot.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDot.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblDot.setBounds(10, 204, 150, 40);
			contentPanel.add(lblDot);
		}

		JComboBox<String> comboBoxDot = new JComboBox<String>();
		DotDao dotDao = new DotDao();
		List<Dot> dots = dotDao.findAll();
		for (Dot dot : dots) {
			comboBoxDot.addItem("Đợt " + dot.getMa_dot());
		}
		comboBoxDot.setSelectedItem(null);
		comboBoxDot.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Dot dot = new Dot();
				dot.setMa_dot(Integer.parseInt(comboBoxDot.getSelectedItem().toString().split(" ")[1]));

				Dot foundDot = new Dot().findByMaDot(dots, dot);

				txtMo = new JTextField();
				txtMo.setColumns(10);
				txtMo.setEditable(false);
				txtMo.setBounds(310, 204, 164, 20);
				contentPanel.add(txtMo);

				txtDong = new JTextField();
				txtDong.setColumns(10);
				txtDong.setEditable(false);
				txtDong.setBounds(310, 235, 164, 20);
				contentPanel.add(txtDong);

				if (foundDot == null) {
					txtMo.setText("Không tồn tại!");
				} else {
					txtMo.setText("Từ:  " + foundDot.getNgay_mo().toLocalDateTime().toString());
					txtDong.setText("Đến:  " + foundDot.getNgay_dong().toLocalDateTime().toString());
				}

			}
		});
		comboBoxDot.setMaximumRowCount(3);
		comboBoxDot.setBounds(170, 215, 130, 20);
		contentPanel.add(comboBoxDot);

		{
			JLabel lblNiDung = new JLabel("Nội dung:");
			lblNiDung.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNiDung.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblNiDung.setBounds(10, 255, 150, 40);
			contentPanel.add(lblNiDung);
		}

		JTextArea txtNoiDung = new JTextArea();
		txtNoiDung.setWrapStyleWord(true);
		txtNoiDung.setLineWrap(true);
		txtNoiDung.setBounds(170, 264, 254, 100);
		contentPanel.add(txtNoiDung);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Gửi Yêu Cầu");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if (comboBoxLopMon.getSelectedItem() == null || comboBoxCotDiem.getSelectedItem() == null
								|| comboBoxDot.getSelectedItem() == null || txtNoiDung.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Phải chọn và nhập đầy đủ trường!");
						} else {
							PhucKhaoDao phucKhaoDao = new PhucKhaoDao();
							PhucKhao phucKhao = new PhucKhao();
							phucKhao.setMalop_mon(comboBoxLopMon.getSelectedItem().toString());
							phucKhao.setCot_diem(
									Integer.parseInt(comboBoxCotDiem.getSelectedItem().toString().split(" ")[1]));

							String match = "^(10|\\d)(\\.\\d{1,2})?$";
							boolean pass = txtDiemMongMuon.getText().matches(match);

							if (pass) {
								System.out.println("Đúng định dạng!");
								Timestamp now = new Timestamp(System.currentTimeMillis());
								phucKhao.setNgay_nop(now);
								phucKhao.setMa_dot(
										Integer.parseInt(comboBoxDot.getSelectedItem().toString().split(" ")[1]));
								phucKhao.setMa_sinhVien(sinhVien.get_mssv());
								phucKhao.setDiem_mongmuon(Float.parseFloat(txtDiemMongMuon.getText()));
								phucKhao.setNoidung(txtNoiDung.getText());
								int success = phucKhaoDao.insert(phucKhao);
								if (success < 0) {
									JOptionPane.showMessageDialog(null, "Gửi yêu cầu phúc khảo thất bại!");
									dispose();
								} else {
									JOptionPane.showMessageDialog(null, "Gửi yêu cầu phúc khảo thành công!");
									dispose();
								}
							} else {
								JOptionPane.showMessageDialog(null, "Vui lòng nhập điểm lại!");
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Hủy");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
