package com.thuvien.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamException;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.thuvien.dao.ThanhVienDao;
import com.thuvien.entity.ThanhVien;
import com.thuvien.utils.DialogHelper;
import com.thuvien.utils.XDate;

public class ThanhVienJPanel extends JPanel implements Runnable, ThreadFactory {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaTV;
	private JTextField txtHoTen;
	private JTextField txtEmail;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtTimKiem;
	private JTable table;
	DefaultTableModel model;
	private JButton btnInsert;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnClear;
	private JButton btnPrevList;
	private JButton btnNextList;
	int indexTrang = 1;
	int index = 0;
	ThanhVienDao tvd = new ThanhVienDao();
	private JButton btnFirst;
	private JButton btnPrevEdit;
	private JButton btnNextEdit;
	private JButton btnLast;
	private JLabel lblIndexTrang;
	private JTextField txtSDT;
	private JTextField txtNgaySinh;
	private JTextField txtNgayDangKy;
	private JTextField txtCCCD;
	private JTextArea txtDiaChi;
	private JPanel panel2;
	private WebcamPanel panel = null;
	private Webcam webcam = null;
	private Executor executor = Executors.newSingleThreadExecutor(this);
	private JButton btnTat;
	Date ngayHienTai = new Date();
	String memberIDRegex = "^TV\\d{3}$";

	public ThanhVienJPanel() {
		addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
			}

			public void ancestorMoved(AncestorEvent event) {
			}

			public void ancestorRemoved(AncestorEvent event) {
				closeWebcam();
			}
		});
		setLayout(null);
		JLabel lblTitle = new JLabel("Quản Lý Thành Viên");
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setBounds(763, 10, 327, 37);
		add(lblTitle);

		JPanel pnlThongTinTG = new JPanel();
		pnlThongTinTG.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlThongTinTG.setBounds(32, 191, 474, 317);
		add(pnlThongTinTG);
		pnlThongTinTG.setLayout(null);

		JLabel lblMaThanhVien = new JLabel("Mã Thành Viên");
		lblMaThanhVien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaThanhVien.setBounds(10, 16, 157, 19);
		pnlThongTinTG.add(lblMaThanhVien);

		txtMaTV = new JTextField();
		txtMaTV.setColumns(10);
		txtMaTV.setBounds(10, 38, 198, 19);
		pnlThongTinTG.add(txtMaTV);

		JLabel lblHoTen = new JLabel("Họ Và Tên");
		lblHoTen.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHoTen.setBounds(10, 67, 104, 19);
		pnlThongTinTG.add(lblHoTen);

		txtHoTen = new JTextField();
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(10, 89, 198, 19);
		pnlThongTinTG.add(txtHoTen);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(10, 118, 104, 19);
		pnlThongTinTG.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(10, 139, 198, 19);
		pnlThongTinTG.add(txtEmail);

		JLabel lblSDT = new JLabel("SDT");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSDT.setBounds(271, 118, 104, 19);
		pnlThongTinTG.add(lblSDT);

		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(271, 139, 193, 19);
		pnlThongTinTG.add(txtSDT);

		JLabel lblNgaySinh = new JLabel("Ngày Sinh");
		lblNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgaySinh.setBounds(271, 67, 104, 19);
		pnlThongTinTG.add(lblNgaySinh);

		txtNgaySinh = new JTextField();
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBounds(271, 89, 193, 19);
		pnlThongTinTG.add(txtNgaySinh);

		JLabel lblNgayDangKy = new JLabel("Ngày Đăng Ký");
		lblNgayDangKy.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgayDangKy.setBounds(271, 16, 139, 19);
		pnlThongTinTG.add(lblNgayDangKy);

		txtNgayDangKy = new JTextField();
		txtNgayDangKy.setEnabled(false);
		txtNgayDangKy.setColumns(10);
		txtNgayDangKy.setBounds(271, 38, 193, 19);
		pnlThongTinTG.add(txtNgayDangKy);
		txtNgayDangKy.setText(XDate.toString(ngayHienTai, "yyyy-MM-dd"));

		JLabel lblCCCD = new JLabel("CCCD");
		lblCCCD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCCCD.setBounds(13, 164, 104, 19);
		pnlThongTinTG.add(lblCCCD);

		txtCCCD = new JTextField();
		txtCCCD.setColumns(10);
		txtCCCD.setBounds(10, 191, 198, 19);
		pnlThongTinTG.add(txtCCCD);

		JLabel lblDiaChi = new JLabel("Địa Chỉ");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDiaChi.setBounds(13, 216, 104, 19);
		pnlThongTinTG.add(lblDiaChi);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(10, 245, 454, 62);
		pnlThongTinTG.add(scrollPane_1);

		txtDiaChi = new JTextArea();
		txtDiaChi.setLineWrap(true);
		scrollPane_1.setViewportView(txtDiaChi);

		JPanel pnlDanhSach = new JPanel();
		pnlDanhSach.setLayout(null);
		pnlDanhSach.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh S\u00E1ch", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		pnlDanhSach.setBounds(554, 81, 747, 382);
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
		txtTimKiem.setBounds(85, 17, 514, 19);
		pnlDanhSach.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		JButton btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnTimKiem.setBounds(609, 16, 97, 21);
		pnlDanhSach.add(btnTimKiem);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 727, 311);
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
		String[] columns = { "Mã TV", "Tên", "SDT", "Địa Chỉ", "Email", "CCCD", "Ngày Sinh", "Ngày ĐK" };
		Object[][] rows = {};
		model = new DefaultTableModel(rows, columns);
		table.setModel(model);

		JPanel pnlButton2 = new JPanel();
		pnlButton2.setBounds(783, 521, 350, 30);
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

		JPanel pnlButton1 = new JPanel();
		pnlButton1.setBounds(95, 521, 350, 30);
		add(pnlButton1);
		pnlButton1.setLayout(new GridLayout(1, 4, 10, 0));

		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});
		pnlButton1.add(btnInsert);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		pnlButton1.add(btnDelete);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		pnlButton1.add(btnUpdate);

		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		pnlButton1.add(btnClear);

		btnPrevList = new JButton("Prev");
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
		btnPrevList.setBounds(785, 487, 85, 21);
		add(btnPrevList);

		btnNextList = new JButton("Next");
		btnNextList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indexTrang++;

				if (indexTrang > (Math.ceil(tvd.selectAll().size() * 1.0 / 5))) {
					DialogHelper.alert(null, "Đây là trang cuối cùng !");
					indexTrang--;
				} else {

					load(indexTrang);
					lblIndexTrang.setText(indexTrang + "");
				}
			}
		});
		btnNextList.setBounds(1048, 487, 85, 21);
		add(btnNextList);

		setStatus(true);

		lblIndexTrang = new JLabel("1");
		lblIndexTrang.setBounds(951, 492, 39, 13);
		add(lblIndexTrang);

		panel2 = new JPanel();
		panel2.setBounds(72, 10, 400, 171);
		add(panel2);
		panel2.setLayout(null);

		JButton btnQuetQR = new JButton("Quét");
		btnQuetQR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initWebCam();
			}
		});
		btnQuetQR.setBounds(565, 530, 85, 21);
		add(btnQuetQR);

		btnTat = new JButton("Tắt");
		btnTat.setEnabled(false);
		btnTat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (webcam != null) {
					closeWebcam();
				}

			}
		});
		btnTat.setBounds(660, 530, 85, 21);
		add(btnTat);
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				load(indexTrang);
				initWebCam();
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

	void load(int soTrang) {
		SwingWorker<List<ThanhVien>, Void> worker = new SwingWorker<List<ThanhVien>, Void>() {
			@Override
			protected List<ThanhVien> doInBackground() throws Exception {
				return tvd.loadTrang((soTrang - 1) * 5, 5);
			}

			@Override
			protected void done() {
				try {
					List<ThanhVien> list = get();
					model.setRowCount(0);
					for (ThanhVien tv : list) {
						Object[] row = { tv.getMaTV(), tv.getTenTV(), tv.getSDT(), tv.getDiaChi(), tv.getEmail(),
								tv.getCCCD(), tv.getNgayDK(), tv.getNgaySinh() };
						model.addRow(row);
					}
				} catch (Exception e) {
					DialogHelper.alert(ThanhVienJPanel.this, "Lỗi truy vấn dữ liệu!");
					e.printStackTrace();
				}
			}
		};
		worker.execute();
	}

	void insert() {
		try {
			ThanhVien tv = getForm();
			if (tv != null) {
				tvd.insert(tv);
				load(indexTrang);
				clear();
				DialogHelper.alert(this, "Insert Successful");
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Insert Failed");
		}

	}

	void delete() {
		try {
			if (DialogHelper.confirm(this, "Bạn có chắc chắn muốn xóa không ?")) {
				ThanhVien tv = getForm();
				tvd.delete(tv.getMaTV());
				load(indexTrang);
				clear();
				DialogHelper.alert(this, "Delete Successful");
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Delete Failed");
			e.printStackTrace();
		}
	}

	void update() {
		try {
			if (DialogHelper.confirm(this, "Bạn có chắc chắn muốn Update không ?")) {
				ThanhVien tv = getForm();
				if (tv != null) {
					tvd.update(tv);
					load(indexTrang);
					clear();
					DialogHelper.alert(this, "Update Successful");
				}
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Update Failed");
			e.printStackTrace();
		}
	}

	void clear() {
		txtMaTV.setText("");
		txtHoTen.setText("");
		txtSDT.setText("");
		txtDiaChi.setText("");
		txtEmail.setText("");
		txtCCCD.setText("");
		txtNgayDangKy.setText(XDate.toString(ngayHienTai, "yyyy-MM-dd"));
		txtNgaySinh.setText("");
		setStatus(true);
		table.clearSelection();
	}

	void setForm(ThanhVien tv) {

		txtMaTV.setText(tv.getMaTV());
		txtHoTen.setText(tv.getTenTV());
		txtSDT.setText(tv.getSDT());
		txtDiaChi.setText(tv.getDiaChi());
		txtEmail.setText(tv.getEmail());
		txtCCCD.setText(tv.getCCCD());
		txtNgayDangKy.setText(tv.getNgayDK() + "");
		txtNgaySinh.setText(tv.getNgaySinh() + "");
	}

	ThanhVien getForm() {
		ThanhVien tv = new ThanhVien();
		if (txtMaTV.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống mã thành viên");
			return tv = null;
		} else {
			if (txtMaTV.getText().matches(memberIDRegex)) {
				tv.setMaTV(txtMaTV.getText());
			} else {
				DialogHelper.alert(this, "Nhập đúng định dạng mã thành viên");
				return null;
			}

		}

		if (txtHoTen.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống họ tên thành viên");
			return null;
		} else {
			if (txtHoTen.getText().length() > 3) {
				tv.setTenTV(txtHoTen.getText());
			} else {
				DialogHelper.alert(this, "Độ dài tên không hợp lệ !");
				return null;
			}

		}

		if (txtSDT.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống số điện thoại");
			return null;
		} else {
			if (isValidPhoneNumber(txtSDT.getText())) {
				tv.setSDT(txtSDT.getText());
			} else {
				DialogHelper.alert(this, "Số điện thoại không hợp lệ ");
				return null;
			}

		}

		if (txtDiaChi.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống địa chỉ");
			return null;
		} else {
			tv.setDiaChi(txtDiaChi.getText());
		}

		if (txtCCCD.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống CCCD");
			return null;
		} else {
			if (isValidCCCD(txtCCCD.getText())) {
				tv.setCCCD(txtCCCD.getText());
			} else {
				DialogHelper.alert(this, "CCCD không hợp lệ !");
				return null;
			}

		}

		if (txtEmail.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống Email");
			return null;
		} else {
			if (isValidEmail(txtEmail.getText())) {
				tv.setEmail(txtEmail.getText());
			} else {
				DialogHelper.alert(this, "Định dạng email sai !");
				return null;
			}

		}

		if (txtNgayDangKy.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống ngày đăng ký");
			return null;
		} else {
			Date nc = XDate.toDate(txtNgayDangKy.getText(), "yyyy-MM-dd");
			tv.setNgayDK(nc);
		}

		if (txtNgaySinh.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống ngày sinh");
			return null;
		} else {
			if (!isValidDateFormat(txtNgaySinh.getText())) {
				DialogHelper.alert(this, "Vui lòng nhập đúng định dạng ngày hiệu lực (yyyy-MM-dd)");
				return null;
			}
			Date ngaySinh = XDate.toDate(txtNgaySinh.getText(), "yyyy-MM-dd");
			if (ngaySinh != null) {
				tv.setNgaySinh(ngaySinh);
			}

		}
		return tv;
	}

	// Validate phone number
	private boolean isValidPhoneNumber(String phoneNumber) {
		// Simple validation using regular expression
		// Allow only numeric characters, require a specific length (e.g., 10 digits),
		// and ensure it starts with the digit "0"
		String phoneRegex = "^0[0-9]{9}$";
		return phoneNumber.matches(phoneRegex);
	}

	// Validate email
	private boolean isValidEmail(String email) {
		// Simple validation using regular expression
		// This pattern checks for a basic email format, but it's not foolproof
		String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
		return email.matches(emailRegex);
	}

	private boolean isValidCCCD(String CCCD) {
		// Simple validation using regular expression
		// This pattern checks for a basic email format, but it's not foolproof
		String CCCDRegex = "^0[0-9]{11}$";
		return CCCD.matches(CCCDRegex);
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
			String maTV = (String) table.getValueAt(this.index, 0);
			ThanhVien tv = tvd.selectById(maTV);
			if (tv != null) {
				setForm(tv);
				setStatus(false);
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
		}
	}

	void setStatus(boolean insertable) {
		btnInsert.setEnabled(insertable);
		btnUpdate.setEnabled(!insertable);
		btnDelete.setEnabled(!insertable);
		boolean first = this.index > 0;
		boolean last = this.index < model.getRowCount() - 1;
		btnFirst.setEnabled(!insertable && first);
		btnPrevEdit.setEnabled(!insertable && first);
		btnNextEdit.setEnabled(!insertable && last);
		btnLast.setEnabled(!insertable && last);
	}

	void search() {

	}

	public void initWebCam() {
		try {
			java.awt.Dimension size = WebcamResolution.QVGA.getSize();
			webcam = Webcam.getWebcams().get(0);
			webcam.setViewSize(size);

			panel = new WebcamPanel(webcam);
			panel.setPreferredSize(size);
			panel.setFPSDisplayed(true);
			panel.setBounds(0, 0, 400, 171);
			panel2.add(panel);
			executor.execute(this);
			btnTat.setEnabled(true);
		} catch (WebcamException e) {
			e.printStackTrace();
			closeWebcam();
		}
	}

	@Override
	public void run() {
		do {
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				Logger.getLogger(ThanhVienJPanel.class.getName()).log(Level.SEVERE, null, ex);
			}
			Result result = null;
			BufferedImage image = null;
			if (webcam.isOpen()) {
				if ((image = webcam.getImage()) == null) {
					continue;
				}
			}
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			try {
				result = new MultiFormatReader().decode(bitmap);
			} catch (NotFoundException ex) {
				Logger.getLogger(ThanhVienJPanel.class.getName()).log(Level.SEVERE, null, ex);
			}
			if (result != null) {
				System.out.println(result);
				String[] cccd = result.getText().split("\\|");
				txtHoTen.setText(cccd[2]);
				txtCCCD.setText(cccd[0]);
				txtDiaChi.setText(cccd[5]);
				closeWebcam();
				break; //

			}
		} while (true);
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, "My Thread");
		t.setDaemon(true);
		return t;
	}

	public void closeWebcam() {
		if (webcam.isOpen()) {
			btnTat.setEnabled(false);
			webcam.close();
		}
	}
}
