package com.thuvien.ui;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GioiThieuJDialogThuVien extends JDialog {

	private JTextPane txtGioiThieu;

	public GioiThieuJDialogThuVien(Frame parent, boolean modal) {
		super(parent, modal);
		setUndecorated(true);
		setBounds(100, 100, 555, 562);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setTitle("Giới Thiệu Phần Mềm");

		JLabel lblLogo = new JLabel("");
		lblLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				exit();
			}
		});
		lblLogo.setBackground(new Color(255, 255, 255));
		lblLogo.setIcon(new ImageIcon(GioiThieuJDialogThuVien.class.getResource("/icon/logo.png")));
		lblLogo.setBounds(10, 0, 521, 332);
		getContentPane().add(lblLogo);

		txtGioiThieu = new JTextPane();
		txtGioiThieu.setEditable(false);
		txtGioiThieu.setBackground(new Color(240, 240, 240));
		txtGioiThieu.setText(
				"Ứng dụng quản lý thư viện FBook được thực hiện bởi nhóm 6. Do thầy nguyễn công tuấn hướng dẫn và hỗ trợ. Dự án giúp quản lý sách, phiếu mượn, phiếu trả thay thế cho các mượn truyền thống bằng giấy lạc hậu. Dễ bị mất các thông tin quan trọng.");
		txtGioiThieu.setBounds(10, 342, 521, 87);
		getContentPane().add(txtGioiThieu);

		JTextPane txtDieuKien = new JTextPane();
		txtDieuKien.setBackground(new Color(240, 240, 240));
		txtDieuKien.setText(
				"Yêu cầu về môi trường: 1. Hệ điều hành bất kỳ 2. JDK từ 1.8 trở lên      3.SQL Server từ 2008 trở lên");
		txtDieuKien.setBounds(10, 432, 120, 87);
		getContentPane().add(txtDieuKien);

	}

	void exit() {
		this.dispose();
	}

}
