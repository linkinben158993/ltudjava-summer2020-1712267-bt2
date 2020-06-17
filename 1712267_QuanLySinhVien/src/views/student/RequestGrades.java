package views.student;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.DSL_MON;
import entity.DSSV_MON;
import entity.SinhVien;
import javax.swing.JLabel;
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

public class RequestGrades extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtDiemMongMuon;
	private JTextField txtLopMon;
	private JTextField txtCotDiem;
	private JTextField textField_2;

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
			JComboBox<String> comboBoxLopMon = new JComboBox<String>();
			for (Integer id : options.keySet()) {
				comboBoxLopMon.addItem(options.get(id).getMalop_mon());
			}
			comboBoxLopMon.setSelectedItem(null);
			comboBoxLopMon.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					txtLopMon = new JTextField();
					txtLopMon.setColumns(10);
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
			JComboBox<String> comboBoxCotDiem = new JComboBox<String>();
			comboBoxCotDiem.setMaximumRowCount(3);
			comboBoxCotDiem.setBounds(170, 113, 130, 20);
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

		txtCotDiem = new JTextField();
		txtCotDiem.setColumns(10);
		txtCotDiem.setBounds(310, 113, 164, 20);
		contentPanel.add(txtCotDiem);
		{
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(310, 215, 164, 20);
			contentPanel.add(textField_2);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Gửi Yêu Cầu");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Hủy");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
