package views.lecturer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.PhucKhaoDao;
import dao.SinhVienDao;
import entity.GiaoVu;
import entity.PhucKhao;
import entity.SinhVien;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class LecturerGradesReview extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tblRequestGrades;
	private DefaultTableModel tableModel_PhucKhao;
	private List<PhucKhao> phucKhaos = new PhucKhaoDao().findAll();
	private List<SinhVien> sinhViens = new SinhVienDao().findAll();
	private HashMap<Integer, Integer> updateList;

	public HashMap<Integer, Integer> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(HashMap<Integer, Integer> updateList) {
		this.updateList = updateList;
	}

	public static void main(String[] args) {
		try {
			LecturerGradesReview dialog = new LecturerGradesReview(new GiaoVu());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LecturerGradesReview(GiaoVu giaoVu) {
		updateList = new HashMap<Integer, Integer>();

		setBounds(100, 100, 800, 320);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 11, 764, 226);
		contentPanel.add(scrollPane);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		tblRequestGrades = new JTable();
		tblRequestGrades.setModel(drawTableReview(phucKhaos, updateList));
		resizeTableReview(tblRequestGrades, centerRenderer);

		scrollPane.setViewportView(tblRequestGrades);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Duyệt");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

					}
				});
				okButton.setActionCommand("Duyệt");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton seenButton = new JButton("Đã Xem");
				seenButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int[] selected = tblRequestGrades.getSelectedRows();
						if (selected.length == 0) {
							JOptionPane.showMessageDialog(null, "Chưa chọn yêu cầu!");
						} else {
							for (int i : selected) {
								PhucKhaoDao phucKhaoDao = new PhucKhaoDao();
								phucKhaoDao.updateStatusToSeen(updateList.get(
										Integer.parseInt(tblRequestGrades.getModel().getValueAt(i, 0).toString())));
							}
							repaint();
						}
					}
				});
				seenButton.setActionCommand("Đã Xem");
				buttonPane.add(seenButton);
			}
			{
				JButton cancelButton = new JButton("Hủy");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Hủy");
				buttonPane.add(cancelButton);
			}
		}
	}

	private DefaultTableModel drawTableReview(List<PhucKhao> phucKhaos, HashMap<Integer, Integer> thingsToUseLater) {
		tableModel_PhucKhao = new DefaultTableModel();
		if (phucKhaos.isEmpty()) {
			String[] columns = { "STT", "Mã Sinh Viên", "Họ Tên", "Mã Lớp", "Cột Điểm", "Mong Muốn", "Lí Do" };
			tableModel_PhucKhao = new DefaultTableModel(columns, 0);
			tableModel_PhucKhao.addRow(new String[] { "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A" });
		} else {
			System.out.println("Có phúc khảo!");
			String[] columns = { "STT", "Mã Sinh Viên", "Họ Tên", "Mã Lớp", "Cột Điểm", "Mong Muốn", "Lí Do" };
			tableModel_PhucKhao = new DefaultTableModel(columns, 0);
			int i = 1;
			for (PhucKhao phucKhao : phucKhaos) {
				SinhVien sinhVien = new SinhVien();
				sinhVien.set_mssv(phucKhao.getMa_sinhVien());
				thingsToUseLater.put(i, phucKhao.getPhuckhao_no());
				if (phucKhao.getTrangthai() == 0) {
					tableModel_PhucKhao.addRow(new String[] { String.valueOf(i++), phucKhao.getMa_sinhVien(),
							new SinhVien().findByMSSV(sinhViens, sinhVien).get_ten(), phucKhao.getMalop_mon(),
							String.valueOf(phucKhao.getCot_diem()), String.valueOf(phucKhao.getDiem_mongmuon()),
							phucKhao.getNoidung() });
				} else if (phucKhao.getTrangthai() == 1) {
					tableModel_PhucKhao.addRow(new String[] { String.valueOf(i++), phucKhao.getMa_sinhVien(),
							new SinhVien().findByMSSV(sinhViens, sinhVien).get_ten(), phucKhao.getMalop_mon(),
							String.valueOf(phucKhao.getCot_diem()), String.valueOf(phucKhao.getDiem_mongmuon()),
							"Đã xem: " + phucKhao.getNoidung() });
				} else {
					continue;
				}
			}
		}
		return tableModel_PhucKhao;
	}

	private void resizeTableReview(JTable jTable, DefaultTableCellRenderer centerRenderer) {
		jTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		jTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(25);
		jTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(25);
		jTable.getColumnModel().getColumn(3).setPreferredWidth(40);
		jTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		jTable.getColumnModel().getColumn(4).setPreferredWidth(10);
		jTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		jTable.getColumnModel().getColumn(5).setPreferredWidth(20);
		jTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
	}
}
