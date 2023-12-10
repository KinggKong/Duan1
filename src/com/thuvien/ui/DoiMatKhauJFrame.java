package com.thuvien.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.thuvien.dao.NhanVienDao;
import com.thuvien.utils.DialogHelper;
import com.thuvien.utils.ShareHelper;

public class DoiMatKhauJFrame extends JFrame {
	private JTextField txtMaNV;
	private JPasswordField txtMatKhauHienTai;
	private JPasswordField txtMatKhauMoi;
	private JPasswordField txtNhapLaiMatKhauMoi;
	NhanVienDao nvd = new NhanVienDao();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoiMatKhauJFrame frame = new DoiMatKhauJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DoiMatKhauJFrame() {
		setBounds(100, 100, 495, 306);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setTitle("Đổi Mật Khẩu");

		JLabel lblTieuDeDoiMatKhau = new JLabel("Đổi Mật Khẩu");
		lblTieuDeDoiMatKhau.setForeground(Color.BLUE);
		lblTieuDeDoiMatKhau.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTieuDeDoiMatKhau.setBounds(20, 10, 282, 24);
		getContentPane().add(lblTieuDeDoiMatKhau);

		JLabel lblMaNV = new JLabel("Mã NV");
		lblMaNV.setBounds(20, 44, 198, 13);
		getContentPane().add(lblMaNV);

		txtMaNV = new JTextField();
		txtMaNV.setEditable(false);
		txtMaNV.setEnabled(false);
		txtMaNV.setBounds(20, 67, 198, 19);
		getContentPane().add(txtMaNV);
		txtMaNV.setColumns(10);
		txtMaNV.setText(ShareHelper.USER.getMaNV());

		JLabel lblMatKhauHienTai = new JLabel("Mật Khẩu Hiện Tại");
		lblMatKhauHienTai.setBounds(262, 44, 198, 13);
		getContentPane().add(lblMatKhauHienTai);

		JLabel lblMatKhauMoi = new JLabel("Mật Khẩu Mới");
		lblMatKhauMoi.setBounds(20, 127, 198, 13);
		getContentPane().add(lblMatKhauMoi);

		JLabel lblNhapLaiMatKhauMoi = new JLabel("Nhập Lại Mật Khẩu Mới");
		lblNhapLaiMatKhauMoi.setBounds(262, 127, 198, 13);
		getContentPane().add(lblNhapLaiMatKhauMoi);

		JButton btnXacNhan = new JButton("Xác Nhận");
		btnXacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xacNhan();
			}
		});
		btnXacNhan.setBounds(235, 211, 99, 21);
		getContentPane().add(btnXacNhan);

		JButton btnHuy = new JButton("Hủy");
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				huyBo();
			}
		});
		btnHuy.setBounds(355, 211, 99, 21);
		getContentPane().add(btnHuy);

		txtMatKhauHienTai = new JPasswordField();
		txtMatKhauHienTai.setBounds(262, 67, 196, 19);
		getContentPane().add(txtMatKhauHienTai);

		txtMatKhauMoi = new JPasswordField();
		txtMatKhauMoi.setBounds(20, 150, 196, 19);
		getContentPane().add(txtMatKhauMoi);

		txtNhapLaiMatKhauMoi = new JPasswordField();
		txtNhapLaiMatKhauMoi.setBounds(262, 150, 196, 19);
		getContentPane().add(txtNhapLaiMatKhauMoi);

	}

	void xacNhan() {
		String maNV = txtMaNV.getText();
		String mkHienTai = new String(txtMatKhauHienTai.getPassword());
		String matKhauMoi = new String(txtMatKhauMoi.getPassword());
		String xacNhanMatKhau = new String(txtNhapLaiMatKhauMoi.getPassword());
		if (ShareHelper.USER.getPassWord().equals(mkHienTai)) {
			if (matKhauMoi.equals(xacNhanMatKhau)) {
				ShareHelper.USER.setPassWord(xacNhanMatKhau);
				nvd.update(ShareHelper.USER);
				DialogHelper.alert(this, "Đổi mật khẩu thành công");
				clear();
			} else {
				DialogHelper.alert(this, "Xác nhận mật khẩu chưa chính xác!");
			}
		} else {
			DialogHelper.alert(this, "Mật khẩu hiện tại chưa đúng !\nVui lòng thử lại !");
		}
	}

	void clear() {
		txtMaNV.setText(ShareHelper.USER.getMaNV());
		txtMatKhauHienTai.setText("");
		txtMatKhauMoi.setText("");
		txtNhapLaiMatKhauMoi.setText("");
	}

	void huyBo() {
		this.dispose();
	}
}
