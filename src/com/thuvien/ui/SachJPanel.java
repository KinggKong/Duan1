package com.thuvien.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ButtonGroup;

public class SachJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField txtTenSach;
	private JTextField textField_2;
	private JTextField txtNgayNhap;
	private JTextField txtTimKiem;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public SachJPanel() {
		setLayout(null);

		JLabel lblTitle = new JLabel("Quản Lý Sách");
		lblTitle.setBounds(403, 5, 244, 37);
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		add(lblTitle);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(23, 108, 495, 215);
		add(panel);
		panel.setLayout(null);

		JLabel lblMSch = new JLabel("Mã Sách");
		lblMSch.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMSch.setBounds(10, 10, 104, 19);
		panel.add(lblMSch);

		textField = new JTextField();
		textField.setBounds(10, 34, 225, 19);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblTenSach = new JLabel("Tên Sách");
		lblTenSach.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenSach.setBounds(10, 63, 104, 19);
		panel.add(lblTenSach);

		txtTenSach = new JTextField();
		txtTenSach.setColumns(10);
		txtTenSach.setBounds(10, 87, 225, 19);
		panel.add(txtTenSach);

		JLabel lblNmXutBn = new JLabel("Năm Xuất Bản");
		lblNmXutBn.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNmXutBn.setBounds(283, 10, 144, 19);
		panel.add(lblNmXutBn);

		textField_2 = new JTextField();
		textField_2.setBounds(283, 34, 183, 19);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblTrangThai = new JLabel("Trạng Thái Sản Xuất");
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTrangThai.setBounds(10, 126, 168, 19);
		panel.add(lblTrangThai);

		JRadioButton rdoCon = new JRadioButton("Còn");
		buttonGroup.add(rdoCon);
		rdoCon.setSelected(true);
		rdoCon.setBounds(10, 151, 92, 21);
		panel.add(rdoCon);

		JRadioButton rdoKhong = new JRadioButton("Không");
		buttonGroup.add(rdoKhong);
		rdoKhong.setBounds(115, 151, 92, 21);
		panel.add(rdoKhong);

		JLabel lblNgayNhap = new JLabel("Ngày Nhập");
		lblNgayNhap.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgayNhap.setBounds(283, 63, 144, 19);
		panel.add(lblNgayNhap);

		txtNgayNhap = new JTextField();
		txtNgayNhap.setBounds(283, 87, 183, 19);
		panel.add(txtNgayNhap);
		txtNgayNhap.setColumns(10);

		JComboBox cbxNhaXuatBan = new JComboBox();
		cbxNhaXuatBan.setBounds(279, 155, 206, 21);
		panel.add(cbxNhaXuatBan);

		JLabel lblNhaXuatBan = new JLabel("Nhà Xuất Bản");
		lblNhaXuatBan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNhaXuatBan.setBounds(283, 126, 144, 19);
		panel.add(lblNhaXuatBan);

		JPanel pnlButton1 = new JPanel();
		pnlButton1.setBounds(97, 356, 350, 30);
		add(pnlButton1);
		pnlButton1.setLayout(new GridLayout(1, 4, 10, 0));

		JButton btnInsert = new JButton("Insert");
		btnInsert.setEnabled(true);
		pnlButton1.add(btnInsert);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		pnlButton1.add(btnDelete);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		pnlButton1.add(btnUpdate);

		JButton btnClear = new JButton("Clear");
		pnlButton1.add(btnClear);

		JPanel pnlButton2 = new JPanel();
		pnlButton2.setBounds(97, 406, 350, 30);
		add(pnlButton2);
		pnlButton2.setLayout(new GridLayout(1, 4, 10, 0));

		JButton btnFirst = new JButton("First");
		btnFirst.setEnabled(false);
		pnlButton2.add(btnFirst);

		JButton btnPrevEdit = new JButton("Prev");
		btnPrevEdit.setEnabled(false);
		pnlButton2.add(btnPrevEdit);

		JButton btnNextEdit = new JButton("Next");
		btnNextEdit.setEnabled(false);
		pnlButton2.add(btnNextEdit);

		JButton btnLast = new JButton("Last");
		btnLast.setEnabled(false);
		pnlButton2.add(btnLast);

		JPanel pnlDanhSach = new JPanel();
		pnlDanhSach.setLayout(null);
		pnlDanhSach.setBorder(new TitledBorder(

				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),

				"Danh S\u00E1ch", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		pnlDanhSach.setBounds(538, 86, 491, 378);
		add(pnlDanhSach);

		JLabel lblTimKiem = new JLabel("Tìm Kiếm");
		lblTimKiem.setBounds(10, 20, 55, 13);
		pnlDanhSach.add(lblTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(85, 17, 266, 19);
		pnlDanhSach.add(txtTimKiem);

		JButton btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setBounds(361, 16, 97, 21);
		pnlDanhSach.add(btnTimKiem);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 471, 307);
		pnlDanhSach.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		String[] columns = { "Mã Sách", "Tên Sách", "Năm XB", "Ngày Nhập", "Tình Trạng", "NXB" };
		Object[][] rows = {

		};

		JButton btnPrevList = new JButton("Prev");
		btnPrevList.setBounds(645, 489, 85, 21);
		add(btnPrevList);

		JButton btnNextList = new JButton("Next");
		btnNextList.setBounds(854, 489, 85, 21);
		add(btnNextList);

		JLabel lblIndexTrang = new JLabel("1");
		lblIndexTrang.setBounds(787, 493, 39, 13);
		add(lblIndexTrang);

	}
}
