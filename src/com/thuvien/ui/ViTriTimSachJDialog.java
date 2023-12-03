package com.thuvien.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.thuvien.entity.QuyenSachTim;

public class ViTriTimSachJDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblTenQuyenSach;
	private JLabel lblMaQS;
	private JLabel lblDay;
	private JLabel lblNgan;
	private JLabel lblViTriSo;

	public ViTriTimSachJDialog(QuyenSachJPanel quyenSachJPanel, boolean modal, QuyenSachTim qst) {
		setBounds(100, 100, 456, 324);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		setTitle("Vị Trí Cụ Thể Của Quyển Sách");

		JLabel lblNewLabel = new JLabel("Vị Trí Chi Tiết");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(155, 10, 208, 35);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mã Quyển Sách:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 54, 101, 22);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Tên Quyển Sách:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(10, 97, 123, 22);
		contentPanel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Dãy Số:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_2.setBounds(10, 143, 101, 22);
		contentPanel.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Ngăn Số:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_3.setBounds(10, 193, 101, 22);
		contentPanel.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_3_1 = new JLabel("Vị Trí Số:");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_3_1.setBounds(10, 240, 101, 22);
		contentPanel.add(lblNewLabel_1_3_1);

		lblTenQuyenSach = new JLabel("");
		lblTenQuyenSach.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTenQuyenSach.setBounds(143, 97, 289, 22);
		contentPanel.add(lblTenQuyenSach);
		lblTenQuyenSach.setText(qst.getTenSach().getTenQS());

		lblMaQS = new JLabel("");
		lblMaQS.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMaQS.setBounds(143, 54, 289, 22);
		contentPanel.add(lblMaQS);
		lblMaQS.setText(qst.getMaQuyenSacch().getMaQS());

		lblDay = new JLabel("");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDay.setBounds(143, 143, 289, 22);
		contentPanel.add(lblDay);
		lblDay.setText(qst.getDaySo());

		lblNgan = new JLabel("");
		lblNgan.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNgan.setBounds(143, 193, 289, 22);
		contentPanel.add(lblNgan);
		lblNgan.setText(qst.getNganSo());

		lblViTriSo = new JLabel("");
		lblViTriSo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblViTriSo.setBounds(143, 240, 289, 22);
		contentPanel.add(lblViTriSo);
		lblViTriSo.setText(qst.getSoViTri());
	}
}
