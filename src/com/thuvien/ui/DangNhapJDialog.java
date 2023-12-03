package com.thuvien.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.thuvien.dao.NhanVienDao;
import com.thuvien.entity.NhanVien;
import com.thuvien.utils.DialogHelper;
import com.thuvien.utils.ShareHelper;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DangNhapJDialog extends JDialog {
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	NhanVienDao nvd = new NhanVienDao();

	public DangNhapJDialog(Frame parent, boolean modal) {
		super(parent, modal);
		setBounds(100, 100, 598, 358);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setTitle("Ứng dụng Quản Lý Thư Viện");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(0, 0, 244, 321);
			getContentPane().add(panel);

			JLabel lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon(DangNhapJDialog.class.getResource("/icon/ong.jpg")));
			panel.add(lblNewLabel_1);
		}
		{
			JLabel lblUsername = new JLabel("UserName");
			lblUsername.setIcon(new ImageIcon(DangNhapJDialog.class.getResource("/icon/user2.png")));
			lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblUsername.setBounds(279, 51, 253, 29);
			getContentPane().add(lblUsername);
		}
		{
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setIcon(new ImageIcon(DangNhapJDialog.class.getResource("/icon/password.png")));
			lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblPassword.setBounds(279, 138, 253, 29);
			getContentPane().add(lblPassword);
		}

		txtUsername = new JTextField();
		txtUsername.setBounds(279, 91, 265, 19);
		txtUsername.setText("NV001");
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(279, 177, 265, 19);
		txtPassword.setText("admin");
		getContentPane().add(txtPassword);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(new Color(218, 165, 32));
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblLogin.setBounds(277, 0, 81, 41);
		getContentPane().add(lblLogin);

		JButton btnNewButton = new JButton("Đăng Nhập");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btnNewButton.setBounds(291, 253, 104, 21);
		getContentPane().add(btnNewButton);

		JButton btnKtThc = new JButton("Kết Thúc");
		btnKtThc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		btnKtThc.setBounds(428, 253, 104, 21);
		getContentPane().add(btnKtThc);
	}

	void login() {
		String maNV = txtUsername.getText();
		String matKhau = new String(txtPassword.getPassword());
		try {
			NhanVien nv = nvd.selectById(maNV);
			if (nv != null) {
				String mk2 = nv.getPassWord();
				if (matKhau.equals(mk2)) {
					ShareHelper.USER = nv;
					DialogHelper.alert(this, "Đăng nhập thành công!");
					this.dispose();
				} else {
					DialogHelper.alert(this, "Sai mật khẩu!");
				}
			} else {
				DialogHelper.alert(this, "Sai tên đăng nhập!");
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
		}
	}

	void exit() {
		if (DialogHelper.confirm(this, "Bạn có muốn thoát khỏi ứng dụng không?")) {
			System.exit(0);
		}
	}
}
