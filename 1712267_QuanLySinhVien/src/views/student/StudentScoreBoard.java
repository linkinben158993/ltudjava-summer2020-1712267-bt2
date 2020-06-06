package views.student;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entity.Diem;
import entity.SinhVien;
import views.lecturer.LecturerSchedule;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class StudentScoreBoard extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private SinhVien sinhVien;
	private List<Diem> diems;
	private JTable table_Diem;
	private DefaultTableModel tableModel_Diem;

	public SinhVien getSinhVien() {
		return sinhVien;
	}

	public void setSinhVien(SinhVien sinhVien) {
		this.sinhVien = sinhVien;
	}

	public List<Diem> getDiems() {
		return diems;
	}

	public void setDiems(List<Diem> diems) {
		this.diems = diems;
	}

	@SuppressWarnings("serial")
	public static void main(String[] args) {
		try {
			StudentScoreBoard dialog = new StudentScoreBoard(new SinhVien(), new ArrayList<Diem>() {
			});
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StudentScoreBoard(SinhVien sinhVien, List<Diem> diems) {
		this.sinhVien = sinhVien;
		this.diems = diems;

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		setBounds(100, 100, 650, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBackground(Color.WHITE);
			panel.setBounds(0, 0, 120, 154);
			contentPanel.add(panel);
			{
				JLabel lblProfile = new JLabel(sinhVien.get_ten(), SwingConstants.CENTER);
				lblProfile.setForeground(Color.BLACK);
				lblProfile.setFont(new Font("Times New Roman", Font.BOLD, 12));
				lblProfile.setBounds(0, 103, 120, 40);
				panel.add(lblProfile);
			}
			{
				JLabel lblIconProfile = new JLabel();
				lblIconProfile.setHorizontalAlignment(SwingConstants.CENTER);
				lblIconProfile.setBounds(0, 11, 120, 81);
				ImageIcon imgIcon_Profile = new ImageIcon(
						LecturerSchedule.class.getResource("/resources/images/Profile.png"));
				Image image_Profile = imgIcon_Profile.getImage();
				Image newImage_Profile = image_Profile.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
				lblIconProfile.setIcon(new ImageIcon(newImage_Profile));
				panel.add(lblIconProfile);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel.setBounds(130, 0, 344, 154);
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("Họ Tên:");
				label.setFont(new Font("Times New Roman", Font.BOLD, 14));
				label.setBounds(10, 11, 100, 14);
				panel.add(label);
			}
			{
				JLabel lblMSinhVin = new JLabel("Mã Sinh Viên:");
				lblMSinhVin.setFont(new Font("Times New Roman", Font.BOLD, 14));
				lblMSinhVin.setBounds(10, 36, 100, 14);
				panel.add(lblMSinhVin);
			}
			{
				JLabel lblLp = new JLabel("Lớp:");
				lblLp.setFont(new Font("Times New Roman", Font.BOLD, 14));
				lblLp.setBounds(10, 61, 100, 14);
				panel.add(lblLp);
			}
			{
				JLabel label = new JLabel("Giới Tính: ");
				label.setFont(new Font("Times New Roman", Font.BOLD, 14));
				label.setBounds(10, 86, 100, 14);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("CMND:");
				label.setFont(new Font("Times New Roman", Font.BOLD, 14));
				label.setBounds(10, 111, 100, 14);
				panel.add(label);
			}
			{
				JLabel lblTen = new JLabel(sinhVien.get_ten());
				lblTen.setHorizontalAlignment(SwingConstants.LEFT);
				lblTen.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				lblTen.setBounds(120, 12, 174, 14);
				panel.add(lblTen);
			}
			{
				JLabel lblMSSV = new JLabel(sinhVien.get_mssv());
				lblMSSV.setHorizontalAlignment(SwingConstants.LEFT);
				lblMSSV.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				lblMSSV.setBounds(120, 37, 174, 14);
				panel.add(lblMSSV);
			}
			{
				JLabel lblLop = new JLabel(sinhVien.getMa_lop());
				lblLop.setHorizontalAlignment(SwingConstants.LEFT);
				lblLop.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				lblLop.setBounds(120, 62, 174, 14);
				panel.add(lblLop);
			}
			{
				JLabel lblGender = new JLabel(sinhVien.get_gioiTinh());
				lblGender.setHorizontalAlignment(SwingConstants.LEFT);
				lblGender.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				lblGender.setBounds(120, 87, 174, 14);
				panel.add(lblGender);
			}
			{
				JLabel lblCMND = new JLabel(sinhVien.get_cmnd());
				lblCMND.setHorizontalAlignment(SwingConstants.LEFT);
				lblCMND.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				lblCMND.setBounds(120, 112, 174, 14);
				panel.add(lblCMND);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 165, 614, 152);
			contentPanel.add(scrollPane);
			{
				table_Diem = new JTable();
				table_Diem.setModel(drawTable_Diem(this.diems));
				resizeTableDiem(table_Diem, centerRenderer);
				scrollPane.setViewportView(table_Diem);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	private DefaultTableModel drawTable_Diem(List<Diem> diems) {
		if (diems == null) {
			String[] columns = { "STT", "Tên Môn", "Lớp", "Giữa Kì", "Cuối Kì", "Khác", "Tổng", "Tình Trạng" };
			tableModel_Diem = new DefaultTableModel(columns, 0);
			tableModel_Diem.addRow(new String[] { "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A" });
		} else {
			String[] columns = { "STT", "Tên Môn", "Lớp", "Giữa Kì", "Cuối Kì", "Khác", "Tổng", "Tình Trạng" };
			tableModel_Diem = new DefaultTableModel(columns, 0);
			int i = 0;

			for (Diem diem : diems) {
				i++;
				System.out.println(diem.getMaLop_mon());

				if (diem.get_tongDiem() < 4.75) {
					String[] data = { String.valueOf(i), diem.getDsl_mon().getMon_lop().get_tenMon(),
							diem.getMaLop_mon(), String.valueOf(diem.get_gk()), String.valueOf(diem.get_ck()),
							String.valueOf(diem.get_khac()), String.valueOf(diem.get_tongDiem()), "Rớt Môn" };
					tableModel_Diem.addRow(data);
				} else {
					String[] data = { String.valueOf(i), diem.getDsl_mon().getMon_lop().get_tenMon(),
							diem.getMaLop_mon(), String.valueOf(diem.get_gk()), String.valueOf(diem.get_ck()),
							String.valueOf(diem.get_khac()), String.valueOf(diem.get_tongDiem()), "Qua Môn" };
					tableModel_Diem.addRow(data);
				}
			}
		}
		return tableModel_Diem;
	}

	private void resizeTableDiem(JTable jTable, DefaultTableCellRenderer centerRenderer) {
		jTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		jTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		jTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		jTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		jTable.getColumnModel().getColumn(5).setPreferredWidth(50);
		jTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		jTable.getColumnModel().getColumn(6).setPreferredWidth(50);
		jTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		jTable.getColumnModel().getColumn(7).setPreferredWidth(120);
	}
}
