package com.thuvien.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import com.thuvien.utils.ShareHelper;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TrangChuJFrame extends JFrame {

	private JPanel contentPane;
	private JPanel pnlContain;
	JPanel jpanel;

// Lưu ý !!!
// các panel con có kích thước chiều dài: 1050; chiều rộng: 540;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrangChuJFrame frame = new TrangChuJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TrangChuJFrame() {
		init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1085, 665);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1071, 34);
		contentPane.add(menuBar);

		JMenu mnuHeThong = new JMenu("Hệ Thống");
		menuBar.add(mnuHeThong);

		JMenuItem mniDangNhap = new JMenuItem("Đăng Nhập");
		mniDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openLogin();
			}
		});
		mniDangNhap.setIcon(new ImageIcon(TrangChuJFrame.class.getResource("/icon/login.24.png")));
		mnuHeThong.add(mniDangNhap);

		JMenuItem mniDangXuat = new JMenuItem("Đăng Xuất");
		mniDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logoff();
			}
		});
		mniDangXuat.setIcon(new ImageIcon(TrangChuJFrame.class.getResource("/icon/Log out.png")));
		mnuHeThong.add(mniDangXuat);

		JSeparator separator = new JSeparator();
		mnuHeThong.add(separator);

		JMenuItem mniDoiMatKhau = new JMenuItem("Đổi Mật Khẩu");
		mniDoiMatKhau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DoiMatKhauJFrame().setVisible(true);
			}
		});
		mniDoiMatKhau.setIcon(new ImageIcon(TrangChuJFrame.class.getResource("/icon/Refresh.png")));
		mnuHeThong.add(mniDoiMatKhau);

		JSeparator separator_1 = new JSeparator();
		mnuHeThong.add(separator_1);

		JMenuItem mniKetThuc = new JMenuItem("Kết Thúc");
		mniKetThuc.setIcon(new ImageIcon(TrangChuJFrame.class.getResource("/icon/-Exit.24.png")));
		mnuHeThong.add(mniKetThuc);

		JMenu mnuQuanLy = new JMenu("Quản Lý");
		menuBar.add(mnuQuanLy);

		JMenuItem mniTacGia = new JMenuItem("Tác Giả");
		mniTacGia.setIcon(new ImageIcon(TrangChuJFrame.class.getResource("/icon/user2.png")));
		mniTacGia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpanel = new TacGiaJPanel();
				changePanel(jpanel);
			}
		});

		mnuQuanLy.add(mniTacGia);

		JMenuItem mniHangThanhVien = new JMenuItem("Hạng Thành Viên");
		mniHangThanhVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpanel = new HangThanhVienJPanel();
				changePanel(jpanel);
			}
		});
		mniHangThanhVien.setIcon(new ImageIcon(TrangChuJFrame.class.getResource("/icon/Certificate.png")));
		mnuQuanLy.add(mniHangThanhVien);

		JMenuItem mniNhanVien = new JMenuItem("Nhân Viên");
		mniNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpanel = new NhanVienJPanel();
				changePanel(jpanel);
			}
		});
		mniNhanVien.setIcon(new ImageIcon(TrangChuJFrame.class.getResource("/icon/person.24.png")));
		mnuQuanLy.add(mniNhanVien);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Vị Trí");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpanel = new ViTriJPanel();
				changePanel(jpanel);
			}
		});
		mntmNewMenuItem_3.setIcon(new ImageIcon(TrangChuJFrame.class.getResource("/icon/Open door.png")));
		mnuQuanLy.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Thể Loại");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpanel = new TheLoaiJPanel();
				changePanel(jpanel);
			}
		});
		mntmNewMenuItem_4.setIcon(new ImageIcon(TrangChuJFrame.class.getResource("/icon/Clipboard.png")));
		mnuQuanLy.add(mntmNewMenuItem_4);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Nhà Xuất Bản");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpanel = new NhaXuatBanJPanel();
				changePanel(jpanel);
			}
		});
		mntmNewMenuItem_5.setIcon(new ImageIcon(TrangChuJFrame.class.getResource("/icon/Brick house.png")));
		mnuQuanLy.add(mntmNewMenuItem_5);

		JMenu mnuThongKe = new JMenu("Thống Kê");
		menuBar.add(mnuThongKe);

		JMenuItem mntmNewMenuItem = new JMenuItem("Số Lượng Sách");
		mntmNewMenuItem.setIcon(new ImageIcon(TrangChuJFrame.class.getResource("/icon/Bar chart.png")));
		mnuThongKe.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Số Lượng Mượn Của Từng Sách");
		mntmNewMenuItem_2.setIcon(new ImageIcon(TrangChuJFrame.class.getResource("/icon/Best.png")));
		mnuThongKe.add(mntmNewMenuItem_2);

		JMenu mnuTroGiup = new JMenu("Trợ Giúp");
		menuBar.add(mnuTroGiup);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Giới Thiệu");
		mnuTroGiup.add(mntmNewMenuItem_1);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 33, 1071, 34);
		contentPane.add(toolBar);

		JButton btnSach = new JButton("Sách");
		btnSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpanel = new SachJPanel();
				changePanel(jpanel);
			}
		});
		btnSach.setIcon(new ImageIcon(TrangChuJFrame.class.getResource("/icon/Book.png")));
		toolBar.add(btnSach);

		JButton btnThanhVien = new JButton("Thành Viên");
		btnThanhVien.setIcon(new ImageIcon(TrangChuJFrame.class.getResource("/icon/User group.png")));
		btnThanhVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpanel = new ThanhVienJPanel();
				changePanel(jpanel);
			}
		});

		JButton btnNewButton = new JButton("Quyển Sách");
		btnNewButton.setIcon(
				new ImageIcon(TrangChuJFrame.class.getResource("/icon/Google-Noto-Emoji-Objects-62863-books.24.png")));
		toolBar.add(btnNewButton);
		toolBar.add(btnThanhVien);

		JButton btnPhieuMuon = new JButton("Phiếu Mượn");
		btnPhieuMuon.setIcon(
				new ImageIcon(TrangChuJFrame.class.getResource("/icon/Fatcow-Farm-Fresh-Application-form-add.24.png")));
		toolBar.add(btnPhieuMuon);

		JButton btnPhieuTra = new JButton("Phiếu Trả");
		btnPhieuTra.setIcon(new ImageIcon(
				TrangChuJFrame.class.getResource("/icon/Fatcow-Farm-Fresh-Application-form-delete.24.png")));
		toolBar.add(btnPhieuTra);

		pnlContain = new JPanel();
		pnlContain.setBounds(10, 78, 1050, 540);
		contentPane.add(pnlContain);
		pnlContain.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TrangChuJFrame.class.getResource("/icon/logo.png")));
		pnlContain.add(lblNewLabel, BorderLayout.CENTER);

	}

//	private void changePanel(JPanel p) {
//		pnlContain.removeAll();
//		pnlContain.add(p);
//		pnlContain.updateUI();
//	}

	private void changePanel(JPanel p) {
		pnlContain.removeAll();
		pnlContain.add(p);
		pnlContain.revalidate();
		pnlContain.repaint();
	}

	public void init() {
		openWelcome();
		openLogin();
	}

	void openWelcome() {
		new ChaoJDialog(this, true).setVisible(true);
	}

	void openLogin() {
		new DangNhapJDialog(this, true).setVisible(true);
	}

	public void logoff() {
		ShareHelper.logoff();
		this.openLogin();
	}
}
