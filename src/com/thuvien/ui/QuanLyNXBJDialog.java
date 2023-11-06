package com.thuvien.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QuanLyNXBJDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			QuanLyNXBJDialog dialog = new QuanLyNXBJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public QuanLyNXBJDialog() {
		setBounds(100, 100, 496, 508);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Quản Lý Nhà Xuất Bản");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(116, 10, 235, 25);
		contentPanel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(152, 76, 242, 19);
		contentPanel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Tên NXB");
		lblNewLabel_1.setBounds(36, 79, 74, 13);
		contentPanel.add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 177, 462, 229);
		contentPanel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		String[] columns = { "ID", "Tên Nhà Xuất Bản" };
		Object[][] row = {

		};
		model = new DefaultTableModel(row, columns);
		table.setModel(model);
		JButton btnNewButton = new JButton("Insert");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(25, 138, 85, 21);
		contentPanel.add(btnNewButton);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(142, 138, 85, 21);
		contentPanel.add(btnDelete);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(259, 138, 85, 21);
		contentPanel.add(btnUpdate);

		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(369, 138, 85, 21);
		contentPanel.add(btnClear);

		JButton btnPrev = new JButton("Prev");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPrev.setBounds(142, 418, 85, 21);
		contentPanel.add(btnPrev);

		JButton btnNext = new JButton("Next");
		btnNext.setBounds(259, 418, 85, 21);
		contentPanel.add(btnNext);
	}
}
