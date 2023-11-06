package com.thuvien.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class SachJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

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
		panel.setBounds(24, 85, 495, 325);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblMSch = new JLabel("Mã Sách");
		lblMSch.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMSch.setBounds(10, 10, 104, 19);
		panel.add(lblMSch);
		
		textField = new JTextField();
		textField.setBounds(10, 34, 232, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblTnSch = new JLabel("Tên Sách");
		lblTnSch.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTnSch.setBounds(10, 63, 104, 19);
		panel.add(lblTnSch);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 87, 232, 19);
		panel.add(textField_1);
		
		JLabel lblNmXutBn = new JLabel("Năm Xuất Bản");
		lblNmXutBn.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNmXutBn.setBounds(302, 10, 144, 19);
		panel.add(lblNmXutBn);
		
		textField_2 = new JTextField();
		textField_2.setBounds(302, 34, 168, 19);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNgyNhp = new JLabel("Ngày Nhập");
		lblNgyNhp.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgyNhp.setBounds(302, 63, 144, 19);
		panel.add(lblNgyNhp);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(302, 87, 168, 19);
		panel.add(textField_3);
		
		JLabel lblTrngThi = new JLabel("Trạng Thái Sản Xuất");
		lblTrngThi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTrngThi.setBounds(10, 116, 168, 19);
		panel.add(lblTrngThi);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Còn");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setBounds(11, 141, 92, 21);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnKhng = new JRadioButton("Không");
		rdbtnKhng.setBounds(131, 141, 92, 21);
		panel.add(rdbtnKhng);
		
		JLabel lblNgyNhp_1 = new JLabel("Ngày Nhập");
		lblNgyNhp_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgyNhp_1.setBounds(10, 188, 144, 19);
		panel.add(lblNgyNhp_1);
		
	}
}
