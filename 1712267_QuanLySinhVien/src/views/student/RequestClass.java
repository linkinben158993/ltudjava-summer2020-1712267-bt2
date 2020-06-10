package views.student;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DCHPDao;
import dao.DSLMDao;
import entity.DCHP;
import entity.DSL_MON;
import entity.SinhVien;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RequestClass extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblTenMon;
	private SinhVien sinhVien;

	public static void main(String[] args) {
		try {
			RequestClass dialog = new RequestClass(new SinhVien());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public RequestClass(SinhVien sinhVien) {
		this.sinhVien = sinhVien;
		setBounds(100, 100, 450, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblLop = new JLabel("Lớp Đang Mở:");
		lblLop.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblLop.setBounds(10, 51, 100, 40);
		contentPanel.add(lblLop);

		JLabel lblMsg = new JLabel("Điền Thông Tin Gửi Yêu Cầu Vào Lớp");
		lblMsg.setBackground(Color.GRAY);
		lblMsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblMsg.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMsg.setBounds(80, 0, 280, 40);
		lblMsg.setOpaque(true);
		contentPanel.add(lblMsg);
		{
			JLabel lblReqType = new JLabel("Loại Yêu Cầu:");
			lblReqType.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblReqType.setBounds(10, 102, 100, 40);
			contentPanel.add(lblReqType);
		}

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 89, 100, 2);
		contentPanel.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 141, 100, 2);
		contentPanel.add(separator_1);

		// Chọn lớp đang mở hiện tại
		Map<Integer, DSL_MON> options = new HashMap<Integer, DSL_MON>();
		DSLMDao dslmDao = new DSLMDao();
		List<DSL_MON> dsl_MONs = dslmDao.findAll();
		for (DSL_MON dsl_MON : dsl_MONs) {
			options.put(dsl_MON.get_danhsachlopNo(), dsl_MON);
		}
		JComboBox<String> comboBoxClass = new JComboBox<String>();
		for (Integer id : options.keySet()) {
			comboBoxClass.addItem(options.get(id).getMalop_mon());
		}
		comboBoxClass.setSelectedItem(null);
		comboBoxClass.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				DSL_MON dsl_MON = new DSL_MON();
				dsl_MON.setMalop_mon(comboBoxClass.getSelectedItem().toString());
				DSL_MON foundDSLM = new DSL_MON().findByMLM(dsl_MONs, dsl_MON);
				if (foundDSLM == null) {
					lblTenMon.setText("Không có môn!");
				} else {
					lblTenMon.setText(foundDSLM.getMon_lop().get_tenMon().toString());
				}
			}
		});
		lblTenMon = new JLabel();
		lblTenMon.setBounds(255, 51, 194, 40);
		contentPanel.add(lblTenMon);
		comboBoxClass.setMaximumRowCount(3);
		comboBoxClass.setBounds(120, 62, 130, 20);
		contentPanel.add(comboBoxClass);

		DCHP dchp = new DCHP();
		JLabel lblYeuCau = new JLabel();
		lblYeuCau.setBounds(255, 102, 194, 40);
		contentPanel.add(lblYeuCau);
		JComboBox<String> comboBoxType = new JComboBox<String>();
		comboBoxType.addItem("Xin Vào Lớp");
		comboBoxType.addItem("Xin Rời Lớp");
		comboBoxType.setSelectedItem(null);
		comboBoxType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (comboBoxType.getSelectedItem().toString().equals("Xin Vào Lớp")) {
					dchp.set_mayeuCau(1);
				} else if (comboBoxType.getSelectedItem().toString().equals("Xin Rời Lớp")) {
					dchp.set_mayeuCau(2);
				}
			}
		});
		comboBoxType.setMaximumRowCount(3);
		comboBoxType.setBounds(120, 113, 130, 20);
		contentPanel.add(comboBoxType);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Gửi Yêu Cầu");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(comboBoxClass.getSelectedItem() == null || comboBoxType.getSelectedItem() == null) {
							JOptionPane.showMessageDialog(null,
									"Phải chọn lớp đang mở và loại yêu cầu!");
						}
						else {
							dchp.set_masinhVien(sinhVien.get_mssv());
							dchp.set_malopMon(comboBoxClass.getSelectedItem().toString());
							DCHPDao dchpDao = new DCHPDao();
							dchpDao.insert(dchp);
						}
					}
				});
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
