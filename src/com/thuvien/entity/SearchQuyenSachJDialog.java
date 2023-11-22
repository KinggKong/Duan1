package com.thuvien.entity;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchQuyenSachJDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SearchQuyenSachJDialog dialog = new SearchQuyenSachJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SearchQuyenSachJDialog() {
		setBounds(100, 100, 492, 314);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thông Tin Tìm Kiếm");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(134, 10, 206, 26);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên:");
		lblNewLabel_1.setBounds(10, 59, 45, 20);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Lần Tái Bản:");
		lblNewLabel_1_1.setBounds(10, 104, 65, 20);
		contentPanel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Vị Trí:");
		lblNewLabel_1_1_1.setBounds(10, 148, 65, 20);
		contentPanel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Tác Giả:");
		lblNewLabel_1_1_2.setBounds(10, 198, 65, 20);
		contentPanel.add(lblNewLabel_1_1_2);
		
		JLabel lblTenSach = new JLabel("New label");
		lblTenSach.setBounds(85, 59, 382, 20);
		contentPanel.add(lblTenSach);
		
		JLabel lblLanTB = new JLabel("New label");
		lblLanTB.setBounds(85, 104, 382, 20);
		contentPanel.add(lblLanTB);
		
		JLabel lblViTri = new JLabel("New label");
		lblViTri.setBounds(85, 148, 382, 20);
		contentPanel.add(lblViTri);
		
		JLabel lblTacGia = new JLabel("New label");
		lblTacGia.setBounds(86, 198, 382, 20);
		contentPanel.add(lblTacGia);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(382, 246, 85, 21);
		contentPanel.add(btnNewButton);
	}
}
