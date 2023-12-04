package com.thuvien.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.Random;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.thuvien.dao.NhanVienDao;
import com.thuvien.entity.NhanVien;
import com.thuvien.utils.DialogHelper;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuenMatKhauJFrame extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtOTP;
	private JButton btnXacNhanOTP;
	private JButton btnGuiOTP;
	private JButton btnXacNhanDoi;
	private JPasswordField txtMatKhauMoi;
	private JPasswordField txtNhapLaiMatKhauMoi;
	NhanVienDao nvd = new NhanVienDao();
	String otp = generateOTP();
	private JLabel lblNewLabel_2;

	public QuenMatKhauJFrame() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 493, 429);
		contentPane = new JPanel();
		setTitle("Khôi phục mật khẩu");
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Khôi Phục Mật Khẩu");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(147, 10, 246, 26);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 54, 95, 19);
		contentPane.add(lblNewLabel_1);

		txtEmail = new JTextField();
		txtEmail.setBounds(10, 83, 301, 19);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		txtOTP = new JTextField();
		txtOTP.setEnabled(false);
		txtOTP.setBounds(10, 153, 301, 19);
		contentPane.add(txtOTP);
		txtOTP.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("OTP");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(10, 124, 95, 19);
		contentPane.add(lblNewLabel_1_1);

		btnGuiOTP = new JButton("Gửi OTP");
		btnGuiOTP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText();
				NhanVien nhanVien = nvd.checkTonTai(email);
				if (nhanVien != null) {
					guiEmail(email, otp);
				} else {
					DialogHelper.alert(QuenMatKhauJFrame.this, "Email không tồn tại !\nVui lòng thử lại !");
				}

			}
		});
		btnGuiOTP.setBounds(346, 82, 123, 21);
		contentPane.add(btnGuiOTP);

		btnXacNhanOTP = new JButton("Xác Nhận");
		btnXacNhanOTP.setEnabled(false);
		btnXacNhanOTP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkOTP()) {
					btnXacNhanDoi.setEnabled(true);
					txtMatKhauMoi.setEnabled(true);
					txtNhapLaiMatKhauMoi.setEnabled(true);
					btnXacNhanOTP.setEnabled(false);
					txtOTP.setEnabled(false);
				} else {
					DialogHelper.alert(null, "Mã OTP không chính xác ! \nVui lòng thử lại !");
				}
			}
		});
		btnXacNhanOTP.setBounds(346, 152, 123, 21);
		contentPane.add(btnXacNhanOTP);

		JLabel lblNewLabel_1_1_1 = new JLabel("Mật Khẩu Mới");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(10, 195, 224, 19);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Nhập Lại Mật Khẩu Mới");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1_1.setBounds(10, 279, 224, 19);
		contentPane.add(lblNewLabel_1_1_1_1);

		btnXacNhanDoi = new JButton("Xác Nhận Đổi");
		btnXacNhanDoi.setEnabled(false);
		btnXacNhanDoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (xacNhanDoi()) {
					DialogHelper.alert(QuenMatKhauJFrame.this, "Đổi mật khẩu thành công ");
					QuenMatKhauJFrame.this.setVisible(false);

				} else {
					DialogHelper.alert(QuenMatKhauJFrame.this, "Mật khẩu xác nhận chưa đúng !");
				}
			}
		});
		btnXacNhanDoi.setBounds(346, 223, 123, 21);
		contentPane.add(btnXacNhanDoi);

		txtMatKhauMoi = new JPasswordField();
		txtMatKhauMoi.setEnabled(false);
		txtMatKhauMoi.setBounds(10, 224, 301, 19);
		contentPane.add(txtMatKhauMoi);

		txtNhapLaiMatKhauMoi = new JPasswordField();
		txtNhapLaiMatKhauMoi.setEnabled(false);
		txtNhapLaiMatKhauMoi.setBounds(10, 308, 301, 19);
		contentPane.add(txtNhapLaiMatKhauMoi);

		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon(QuenMatKhauJFrame.class.getResource("/icon/Left.png")));
		lblNewLabel_2.setBounds(10, 10, 45, 24);
		contentPane.add(lblNewLabel_2);
	}

	public static String generateOTP() {
		// Tạo mã OTP ngẫu nhiên 6 chữ số
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return String.valueOf(otp);
	}

	void guiEmail(String email, String otp) {
		// Configure email information
		final String fromEmail = "anhdeptrai7749@gmail.com";
		final String password = "dujgtekrzjlkxwhu";
		String toEmail = email;
		String subject = "Xác nhận đổi mật khẩu";
		String messageText = "Mã OTP của bạn là: " + otp;

		// Set connection properties
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");

		try {
			// Create a session
			Session session = Session.getDefaultInstance(props, null);

			// Create a MimeMessage
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));

			// Create an array of Address objects
			Address[] recipients = new Address[1];
			recipients[0] = new InternetAddress(toEmail);

			message.addRecipients(Message.RecipientType.TO, recipients);
			message.setSubject(subject);
			message.setText(messageText);

			// Create a transport
			Transport transport = session.getTransport("smtp");

			// Connect to the SMTP server
			transport.connect("smtp.gmail.com", fromEmail, password);

			// Send the email
			transport.sendMessage(message, recipients);
			transport.close();

			DialogHelper.alert(this, "Đã gửi mã OTP đến địa chỉ email của bạn.");
			btnGuiOTP.setEnabled(false);
			txtEmail.setEnabled(false);
			txtOTP.setEnabled(true);
			btnXacNhanOTP.setEnabled(true);
		} catch (MessagingException e) {
			DialogHelper.alert(this, "Có lỗi khi gửi email.");
			e.printStackTrace();
		}
	}

	public boolean checkOTP() {
		String txtOtp = txtOTP.getText();
		if (txtOtp.equals(otp)) {
			return true;
		} else {
			return false;
		}
	}

	boolean xacNhanDoi() {
		String matKhauMoi = new String(txtMatKhauMoi.getPassword());
		String xacNhanMatKhauMoi = new String(txtNhapLaiMatKhauMoi.getPassword());
		if (matKhauMoi.equals(xacNhanMatKhauMoi)) {
			NhanVien nhanVien = nvd.checkTonTai(txtEmail.getText());
			nhanVien.setPassWord(xacNhanMatKhauMoi);
			nvd.update(nhanVien);
			return true;
		} else {
			return false;
		}
	}
}
