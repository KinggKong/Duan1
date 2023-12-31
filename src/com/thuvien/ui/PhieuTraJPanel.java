package com.thuvien.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.thuvien.dao.NhanVienDao;
import com.thuvien.dao.PhieuTraChiTietDao;
import com.thuvien.dao.PhieuTraDao;
import com.thuvien.dao.ThanhVienDao;
import com.thuvien.entity.NhanVien;
import com.thuvien.entity.PhieuTra;
import com.thuvien.entity.ThanhVien;
import com.thuvien.utils.DialogHelper;
import com.thuvien.utils.ShareHelper;
import com.thuvien.utils.XDate;
import javax.swing.ImageIcon;

public class PhieuTraJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNhanVien;
	private JTextField txtNgayTraThucTe;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtTimKiem;
	private JTable table;
	DefaultTableModel model;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnClear;
	private JButton btnPrevList;
	private JButton btnNextList;
	int indexTrang = 1;
	int index = 0;
	private JButton btnFirst;
	private JButton btnPrevEdit;
	private JButton btnNextEdit;
	private JButton btnLast;
	private JLabel lblIndexTrang;
	private JTextField txtTienPhat;
	private JTextField txtMaPhieuTra;
	private JComboBox cbxThanhVien;
	String regexMaPhieuTra = "^PT\\d{3}$";

	PhieuTraDao ptd = new PhieuTraDao();
	PhieuTraChiTietDao ptctd = new PhieuTraChiTietDao();

	DefaultComboBoxModel<ThanhVien> modelThanhVien = new DefaultComboBoxModel<>();

	ThanhVienDao tvd = new ThanhVienDao();
	NhanVienDao nvd = new NhanVienDao();
	PhieuTraChiTietDao phieuTraChiTietDao = new PhieuTraChiTietDao();
	private JTextField txtTienTra;
	private JTextArea txtLyDoPhat;
	private JRadioButton rdoHoanThanh;
	private JRadioButton rdoChuaHoanThanh;
	private JButton btnXemChiTiet;

	public PhieuTraJPanel() {
		setLayout(null);
		JLabel lblTitle = new JLabel("Quản Lý Phiếu Trả");
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setBounds(495, 10, 327, 37);
		add(lblTitle);

		JPanel pnlThongTinTG = new JPanel();
		pnlThongTinTG.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlThongTinTG.setBounds(32, 81, 530, 378);
		add(pnlThongTinTG);
		pnlThongTinTG.setLayout(null);

		JLabel lblMaThanhVien = new JLabel("ID Thành Viên");
		lblMaThanhVien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaThanhVien.setBounds(10, 16, 157, 19);
		pnlThongTinTG.add(lblMaThanhVien);

		JLabel lblHoTen = new JLabel("ID Nhân Viên");
		lblHoTen.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHoTen.setBounds(273, 16, 104, 19);
		pnlThongTinTG.add(lblHoTen);

		txtNhanVien = new JTextField();
		txtNhanVien.setEditable(false);
		txtNhanVien.setColumns(10);
		txtNhanVien.setBounds(273, 38, 218, 21);
		pnlThongTinTG.add(txtNhanVien);

		JLabel lblEmail = new JLabel("Ngày Trả Thực Tế");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(10, 133, 157, 19);
		pnlThongTinTG.add(lblEmail);

		txtNgayTraThucTe = new JTextField();
		txtNgayTraThucTe.setColumns(10);
		txtNgayTraThucTe.setBounds(10, 162, 218, 19);
		pnlThongTinTG.add(txtNgayTraThucTe);

		JLabel lblNgayDangKy = new JLabel("Lý Do Phạt");
		lblNgayDangKy.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgayDangKy.setBounds(10, 241, 139, 19);
		pnlThongTinTG.add(lblNgayDangKy);

		JLabel lblCCCD = new JLabel("Tiền Phạt");
		lblCCCD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCCCD.setBounds(273, 77, 148, 19);
		pnlThongTinTG.add(lblCCCD);

		txtTienPhat = new JTextField();
		txtTienPhat.setColumns(10);
		txtTienPhat.setBounds(273, 106, 218, 19);
		pnlThongTinTG.add(txtTienPhat);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 270, 492, 92);
		pnlThongTinTG.add(scrollPane_1);

		txtLyDoPhat = new JTextArea();
		scrollPane_1.setViewportView(txtLyDoPhat);

		JLabel lblTrngThi = new JLabel("Trạng Thái");
		lblTrngThi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTrngThi.setBounds(273, 147, 148, 19);
		pnlThongTinTG.add(lblTrngThi);

		txtMaPhieuTra = new JTextField();
		txtMaPhieuTra.setColumns(10);
		txtMaPhieuTra.setBounds(10, 104, 218, 19);
		pnlThongTinTG.add(txtMaPhieuTra);

		JLabel lblTinTr = new JLabel("Mã Phiếu Trả");
		lblTinTr.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTinTr.setBounds(10, 77, 157, 19);
		pnlThongTinTG.add(lblTinTr);

		rdoHoanThanh = new JRadioButton("Hoàn Thành");
		buttonGroup.add(rdoHoanThanh);
		rdoHoanThanh.setBounds(273, 175, 103, 21);
		pnlThongTinTG.add(rdoHoanThanh);

		rdoChuaHoanThanh = new JRadioButton("Chưa Hoàn Thành");
		rdoChuaHoanThanh.setSelected(true);
		buttonGroup.add(rdoChuaHoanThanh);
		rdoChuaHoanThanh.setBounds(378, 175, 128, 21);
		pnlThongTinTG.add(rdoChuaHoanThanh);

		cbxThanhVien = new JComboBox();
		cbxThanhVien.setBounds(10, 38, 218, 29);
		pnlThongTinTG.add(cbxThanhVien);
		cbxThanhVien.setModel(modelThanhVien);

		JLabel lblTinTr_1 = new JLabel("Tiền Trả");
		lblTinTr_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTinTr_1.setBounds(10, 191, 148, 19);
		pnlThongTinTG.add(lblTinTr_1);

		txtTienTra = new JTextField();
		txtTienTra.setColumns(10);
		txtTienTra.setBounds(10, 212, 218, 19);
		pnlThongTinTG.add(txtTienTra);

		JPanel pnlDanhSach = new JPanel();
		pnlDanhSach.setLayout(null);
		pnlDanhSach.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh S\u00E1ch", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		pnlDanhSach.setBounds(590, 81, 711, 378);
		add(pnlDanhSach);

		JLabel lblTimKiem = new JLabel("Tìm Kiếm");
		lblTimKiem.setBounds(10, 20, 55, 13);
		pnlDanhSach.add(lblTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setText("Nhập vào mã hoặc tên của thành viên");
		txtTimKiem.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtTimKiem.getText().equals("Nhập vào mã hoặc tên của thành viên")) {
					txtTimKiem.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTimKiem.getText().isEmpty()) {
					txtTimKiem.setText("Nhập vào mã hoặc tên của thành viên");
				}
			}
		});
		txtTimKiem.setBounds(85, 17, 482, 25);
		pnlDanhSach.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		JButton btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setIcon(new ImageIcon(PhieuTraJPanel.class.getResource("/icon/Search.png")));
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtTimKiem.getText().equals(" ")) {
					load(indexTrang);
				} else {
					search(txtTimKiem.getText().trim());
				}
			}
		});
		btnTimKiem.setBounds(577, 16, 124, 25);
		pnlDanhSach.add(btnTimKiem);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 691, 307);
		pnlDanhSach.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				index = table.rowAtPoint(e.getPoint());
				if (index >= 0) {
					edit();
				}

			}
		});
		scrollPane.setViewportView(table);
		String[] columns = { "ID", "Mã Phiếu Trả", "Thành Viên", "Nhân Viên", "Ngày Trả Thực Tế", "Tiền Phạt",
				"Tiền Trả", "Lý Do Phạt", "Tình Trạng" };
		Object[][] rows = {

		};
		model = new DefaultTableModel(rows, columns);
		table.setModel(model);

		JPanel pnlButton2 = new JPanel();
		pnlButton2.setBounds(783, 523, 369, 30);
		add(pnlButton2);
		pnlButton2.setLayout(new GridLayout(1, 4, 10, 0));

		btnFirst = new JButton("First");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index = 0;
				table.setRowSelectionInterval(index, index);
				edit();
			}
		});
		pnlButton2.add(btnFirst);

		btnPrevEdit = new JButton("Prev");
		btnPrevEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index--;
				table.setRowSelectionInterval(index, index);
				edit();
			}
		});
		pnlButton2.add(btnPrevEdit);

		btnNextEdit = new JButton("Next");
		btnNextEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index++;
				table.setRowSelectionInterval(index, index);
				edit();
			}
		});
		pnlButton2.add(btnNextEdit);

		btnLast = new JButton("Last");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index = model.getRowCount() - 1;
				table.setRowSelectionInterval(index, index);
				edit();
			}
		});
		pnlButton2.add(btnLast);

		btnPrevList = new JButton("Prev");
		btnPrevList.setIcon(new ImageIcon(PhieuTraJPanel.class.getResource("/icon/Left.png")));
		btnPrevList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indexTrang--;
				if (indexTrang == 0) {
					DialogHelper.alert(null, "Đây là trang đầu tiên !");
					indexTrang++;
				} else {
					load(indexTrang);
					lblIndexTrang.setText(indexTrang + "");
				}
			}
		});
		btnPrevList.setBounds(783, 478, 105, 30);
		add(btnPrevList);

		btnNextList = new JButton("Next");
		btnNextList.setIcon(new ImageIcon(PhieuTraJPanel.class.getResource("/icon/Right.png")));
		btnNextList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indexTrang++;

				if (indexTrang > (Math.ceil(ptd.selectAll().size() * 1.0 / 15))) {
					DialogHelper.alert(null, "Đây là trang cuối cùng !");
					indexTrang--;
				} else {
					load(indexTrang);
					lblIndexTrang.setText(indexTrang + "");
				}
			}
		});
		btnNextList.setBounds(1047, 478, 105, 30);
		add(btnNextList);

		lblIndexTrang = new JLabel("1");
		lblIndexTrang.setBounds(968, 487, 39, 13);
		add(lblIndexTrang);

		btnXemChiTiet = new JButton("Xem Chi Tiết");
		btnXemChiTiet.setEnabled(false);
		btnXemChiTiet.setIcon(new ImageIcon(PhieuTraJPanel.class.getResource("/icon/ShowDetail.png")));
		btnXemChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PhieuTra pt = new PhieuTra();
				// Lấy dữ liệu từ PhieuMuonJPanel và truyền vào PhieuMuonChiTietJPanel
				int selectedRowIndex = table.getSelectedRow();
				if (selectedRowIndex >= 0) {
					int id = (int) table.getValueAt(selectedRowIndex, 0);
					pt = ptd.selectById(id);
					if (pt != null) {
						// Tạo một instance mới của PhieuMuonChiTietJPanel
						PhieuTraChiTietJPanel chiTietJPanel = new PhieuTraChiTietJPanel(pt);
						Container container = PhieuTraJPanel.this.getParent();
						container.remove(PhieuTraJPanel.this);
						container.add(chiTietJPanel);
						container.revalidate();
						container.repaint();
					}
				}
			}
		});
		btnXemChiTiet.setBounds(590, 496, 145, 30);
		add(btnXemChiTiet);

		btnInsert = new JButton("Insert");
		btnInsert.setIcon(new ImageIcon(PhieuTraJPanel.class.getResource("/icon/Create.png")));
		btnInsert.setBounds(70, 478, 134, 30);
		add(btnInsert);

		btnClear = new JButton("Clear");
		btnClear.setIcon(new ImageIcon(PhieuTraJPanel.class.getResource("/icon/Trash.png")));
		btnClear.setBounds(404, 478, 134, 30);
		add(btnClear);

		btnUpdate = new JButton("Update");
		btnUpdate.setIcon(new ImageIcon(PhieuTraJPanel.class.getResource("/icon/Upload.png")));
		btnUpdate.setBounds(236, 478, 134, 30);
		add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});
		setStatus(true);
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				load(indexTrang);
				fillThanhVien();
				return null;
			}

			@Override
			protected void done() {
				super.done();
				// Tự động thực hiện khi load xong
			}
		};

		worker.execute();
	}

	void fillThanhVien() {
		SwingWorker<List<ThanhVien>, Void> worker = new SwingWorker<List<ThanhVien>, Void>() {

			@Override
			protected List<ThanhVien> doInBackground() throws Exception {
				return tvd.selectAll();
			}

			@Override
			protected void done() {
				try {
					List<ThanhVien> list = get();
					modelThanhVien.removeAllElements();
					for (ThanhVien tv : list) {
						modelThanhVien.addElement(tv);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		worker.execute();
	}

	void load(int soTrang) {
		SwingWorker<List<PhieuTra>, Void> worker = new SwingWorker<List<PhieuTra>, Void>() {

			@Override
			protected List<PhieuTra> doInBackground() throws Exception {
				return ptd.loadTrang((soTrang - 1) * 15, 15);
			}

			@Override
			protected void done() {
				try {
					NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
					List<PhieuTra> list = get();
					model.setRowCount(0);
					for (PhieuTra pt : list) {
						Object[] row = { pt.getId(), pt.getMaPT(), pt.getIdThanhVien().getTenTV(),
								pt.getIdNhanVien().getTenNV(), pt.getNgayTraThucTe(), format.format(pt.getTienPhat()),
								format.format(pt.getTienTra()), pt.getLiDoPhat(),
								pt.isTrangThai() ? "Hoàn Thành" : "Chưa Hoàn Thành" };
						model.addRow(row);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};
		worker.execute();
	}

	void insert() {
		try {
			PhieuTra pm = getForm();
			if (pm != null) {
				if (ptd.selectById2(pm.getMaPT()) == null) {
					ptd.insert(pm);
					load(indexTrang);
					clear();
					DialogHelper.alert(this, "Insert Successful");
				} else {
					DialogHelper.alert(this, "Mã phiếu trả đã tồn tại");
				}
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Insert Failed");
			e.printStackTrace();
		}
	}

	void update() {
		try {
			if (DialogHelper.confirm(this, "Bạn có chắc chắn muốn Update không ?")) {
				PhieuTra pm = getForm();
				if (pm != null) {
					ptd.update(pm);
					load(indexTrang);
					clear();
					DialogHelper.alert(this, "Update Successful");
				}
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Update Failed");
		}
	}

	void clear() {
		txtTimKiem.setText("Nhập vào mã hoặc tên của thành viên");
		txtLyDoPhat.setText("");
		txtMaPhieuTra.setText("");
		txtNgayTraThucTe.setText("");
		txtNhanVien.setText("");
		txtTienPhat.setText("");
		txtTienTra.setText("");
		cbxThanhVien.setSelectedIndex(0);
		rdoHoanThanh.setSelected(true);
		setStatus(true);
		table.clearSelection();
	}

	void setForm(PhieuTra pt) {
		cbxThanhVien.setSelectedItem(pt.getIdThanhVien());
		txtLyDoPhat.setText(pt.getLiDoPhat());
		txtMaPhieuTra.setText(pt.getMaPT());
		txtNgayTraThucTe.setText(pt.getNgayTraThucTe() + "");
		txtNhanVien.setText(pt.getIdNhanVien().getTenNV());
		txtTienPhat.setText(pt.getTienPhat() + "");
		txtTienTra.setText(pt.getTienTra() + "");
		if (pt.isTrangThai() == true) {
			rdoHoanThanh.setSelected(true);
		} else {
			rdoChuaHoanThanh.setSelected(true);
		}
	}

	PhieuTra getForm() {
		PhieuTra pt = new PhieuTra();
		if (txtMaPhieuTra.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống mã phiếu trả");
			return null;
		} else {
			if (txtMaPhieuTra.getText().matches(regexMaPhieuTra)) {
				pt.setMaPT(txtMaPhieuTra.getText());
			} else {
				DialogHelper.alert(this, "Nhập đúng định dạng mã phiếu trả");
				return null;
			}

		}

		if (txtNgayTraThucTe.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống ngày trả thực tế");
			return null;
		} else {
			if (isValidDateFormat(txtNgayTraThucTe.getText())) {
				pt.setNgayTraThucTe(XDate.toDate(txtNgayTraThucTe.getText(), "yyyy-MM-dd"));
			} else {
				DialogHelper.alert(this, "Vui lòng nhập đúng định dạng (yyyy-MM-dd)");
				return null;
			}

		}

		if (txtTienTra.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống tiền trả");
			return null;
		} else {
			try {
				float tienTra = Float.parseFloat(txtTienTra.getText());
				if (tienTra < 0) {
					DialogHelper.alert(this, "Tiền trả không được nhỏ hơn 0");
					return null;
				} else {
					pt.setTienTra(tienTra);
				}
			} catch (Exception e) {
				DialogHelper.alert(this, "Tiền trả chỉ nhập số");
				return null;
			}
		}

		ThanhVien tv = (ThanhVien) cbxThanhVien.getSelectedItem();
		pt.setIdThanhVien(tv);
		NhanVien nv = nvd.selectById2(ShareHelper.idNhanVien());
		pt.setIdNhanVien(nv);
		if (rdoHoanThanh.isSelected()) {
			pt.setTrangThai(true);
		} else {
			pt.setTrangThai(false);
		}
		if (txtTienPhat.getText().isEmpty()) {
			pt.setTienPhat(0);
		} else {
			try {
				float tienPhat = Float.parseFloat(txtTienPhat.getText());
				if (tienPhat < 0) {
					DialogHelper.alert(this, "Tiền phạt không được bé hơn 0");
					return null;
				} else {
					pt.setTienPhat(tienPhat);
				}
			} catch (Exception e) {
				DialogHelper.alert(this, "Tiền phạt không hợp lệ ");
				return null;
			}
		}

		pt.setLiDoPhat(txtLyDoPhat.getText());

		return pt;
	}

	private boolean isValidDateFormat(String inputDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false); // Disable lenient mode
		try {
			Date parsedDate = sdf.parse(inputDate);
			if (parsedDate != null) {
				// Check the components of the parsed date
				Calendar cal = Calendar.getInstance();
				cal.setTime(parsedDate);

				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH) + 1; // Month is 0-based
				int day = cal.get(Calendar.DAY_OF_MONTH);

				// Additional checks for valid year, month, and day
				if (year >= 1000 && year <= 9999 && month >= 1 && month <= 12 && day >= 1
						&& day <= cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
					return true;
				}
			}
			return false;
		} catch (ParseException e) {
			return false; // Parsing failed, date is not in the correct format
		}
	}

	void edit() {
		try {
			int id = (int) table.getValueAt(this.index, 0);
			PhieuTra pm = ptd.selectById(id);
			if (pm != null) {
				setForm(pm);
				setStatus(false);
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
		}
	}

	void setStatus(boolean insertable) {
		btnInsert.setEnabled(insertable);
		btnUpdate.setEnabled(!insertable);
		boolean first = this.index > 0;
		boolean last = this.index < model.getRowCount() - 1;
		btnFirst.setEnabled(!insertable && first);
		btnPrevEdit.setEnabled(!insertable && first);
		btnNextEdit.setEnabled(!insertable && last);
		btnLast.setEnabled(!insertable && last);
		txtTienPhat.setEditable(!insertable);
		txtMaPhieuTra.setEditable(insertable);
		btnXemChiTiet.setEnabled(!insertable);
	}

	void search(String keyString) {
		SwingWorker<List<PhieuTra>, Void> worker = new SwingWorker<List<PhieuTra>, Void>() {

			@Override
			protected List<PhieuTra> doInBackground() throws Exception {
				return ptd.selectByKeyword(keyString);
			}

			@Override
			protected void done() {
				NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
				try {
					List<PhieuTra> listPT = get();
					if (listPT.isEmpty()) {
						DialogHelper.alert(PhieuTraJPanel.this, "Không tồn tại");
					} else {
						model.setRowCount(0);
						for (PhieuTra pt : listPT) {
							Object[] row = { pt.getId(), pt.getMaPT(), pt.getIdThanhVien().getTenTV(),
									pt.getIdNhanVien().getTenNV(), pt.getNgayTraThucTe(),
									format.format(pt.getTienPhat()), format.format(pt.getTienTra()), pt.getLiDoPhat(),
									pt.isTrangThai() ? "Hoàn Thành" : "Chưa Hoàn Thành" };
							model.addRow(row);
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		worker.execute();
	}
}
