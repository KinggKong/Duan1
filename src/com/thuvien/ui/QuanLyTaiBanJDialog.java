package com.thuvien.ui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class QuanLyTaiBanJDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtMaSach;
	private JTextField txtLanTaiBan;
	private JTextField txtThoiGianTB;
	DefaultTableModel model;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyTaiBanJDialog dialog = new QuanLyTaiBanJDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public QuanLyTaiBanJDialog() {
		setBounds(100, 100, 566, 696);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Quản Lý Tái Bản");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(154, 10, 223, 31);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mã Sách");
		lblNewLabel_1.setBounds(27, 69, 78, 13);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Lần Tái Bản");
		lblNewLabel_1_1.setBounds(328, 69, 98, 13);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Thời Gian Tái Bản");
		lblNewLabel_1_2.setBounds(27, 150, 163, 13);
		getContentPane().add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Trạng Thái");
		lblNewLabel_1_3.setBounds(328, 150, 111, 13);
		getContentPane().add(lblNewLabel_1_3);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 230, 532, 335);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		String[] columns = { "Mã Sách", "Lần Tái Bản", "Thời Gian", "Trạng Thái" };
		Object[][] rows = {

		};
		model = new DefaultTableModel(rows, columns);
		table.setModel(model);

		txtMaSach = new JTextField();
		txtMaSach.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtMaSach.getText().equals("Nhập Mã Sách")) {
					txtMaSach.setText("");
				}

			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtMaSach.getText().isEmpty()) {
					txtMaSach.setText("Nhập Mã Sách");
				}
			}
		});
		txtMaSach.setBounds(27, 87, 201, 19);
		getContentPane().add(txtMaSach);
		txtMaSach.setColumns(10);

		txtLanTaiBan = new JTextField();
		txtLanTaiBan.setColumns(10);
		txtLanTaiBan.setBounds(328, 87, 201, 19);
		txtLanTaiBan.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtLanTaiBan.getText().equals("Nhập Lần Tái Bản")) {
					txtLanTaiBan.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtLanTaiBan.getText().isEmpty()) {
					txtLanTaiBan.setText("Nhập Lần Tái Bản");
				}
			}
		});
		getContentPane().add(txtLanTaiBan);

		txtThoiGianTB = new JTextField();
		txtThoiGianTB.setColumns(10);
		txtThoiGianTB.setBounds(27, 173, 201, 19);
		getContentPane().add(txtThoiGianTB);

		JComboBox cboTrangThai = new JComboBox();
		cboTrangThai.setModel(new DefaultComboBoxModel(new String[] { "Còn ", "Không" }));
		cboTrangThai.setBounds(328, 172, 201, 21);
		getContentPane().add(cboTrangThai);

		JButton btnPrev = new JButton("Prev");
		btnPrev.setBounds(166, 575, 85, 21);
		getContentPane().add(btnPrev);

		JButton btnNext = new JButton("Next");
		btnNext.setBounds(312, 575, 85, 21);
		getContentPane().add(btnNext);

		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(46, 614, 85, 21);
		getContentPane().add(btnInsert);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(176, 614, 85, 21);
		getContentPane().add(btnDelete);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(312, 614, 85, 21);
		getContentPane().add(btnUpdate);

		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(444, 614, 85, 21);
		getContentPane().add(btnClear);
		setLocationRelativeTo(null);
	}
	void insert() {
		
	}
	void delete() {
		
	}
	void update() {
		
	}
	void clear() {
		
	}
	void setForm() {
		
	}
	void getForm() {
		
	}
}
