package com.thuvien.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.thuvien.dao.NXBDao;
import com.thuvien.dao.SachDao;
import com.thuvien.entity.NXB;
import com.thuvien.entity.Sach;
import com.thuvien.utils.DialogHelper;
import com.thuvien.utils.ShareHelper;
import com.thuvien.utils.XDate;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;

public class SachJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaSach;
	private JTextField txtTenSach;
	private JTextField txtNamXuatBan;
	private JTextField txtNgayNhap;
	private JTextField txtTimKiem;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	DefaultTableModel model;
	int indexTrang = 1;
	int index = 0;
	SachDao sd = new SachDao();
	private JButton btnPrevList;
	private JButton btnNextList;
	private JButton btnTimKiem;
	private JButton btnFirst;
	private JButton btnPrevEdit;
	private JButton btnNextEdit;
	private JButton btnLast;
	private JButton btnInsert;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnClear;
	NXBDao nxbd = new NXBDao();
	private JRadioButton rdoCon;
	private JRadioButton rdoKhong;
	private JComboBox cbxNhaXuatBan;
	DefaultComboBoxModel<NXB> modelCombobox = new DefaultComboBoxModel<>();
	private JLabel lblIndexTrang;
	String regexMaSach = "^S\\d{4}$";

	public SachJPanel() {
		try {
			System.setOut(new PrintStream(System.out, true, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setLayout(null);

		JLabel lblTitle = new JLabel("Quản Lý Sách");
		lblTitle.setBounds(538, 10, 244, 37);
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		add(lblTitle);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(23, 108, 495, 231);
		add(panel);
		panel.setLayout(null);

		JLabel lblMaSach = new JLabel("Mã Sách");
		lblMaSach.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaSach.setBounds(10, 10, 104, 19);
		panel.add(lblMaSach);

		txtMaSach = new JTextField();
		txtMaSach.setText("VD: S0001...");
		txtMaSach.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtMaSach.getText().equals("VD: S0001...")) {
					txtMaSach.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtMaSach.getText().isEmpty()) {
					txtMaSach.setText("VD: S0001...");
				}
			}
		});
		txtMaSach.setBounds(10, 34, 225, 19);
		panel.add(txtMaSach);
		txtMaSach.setColumns(10);

		JLabel lblTenSach = new JLabel("Tên Sách");
		lblTenSach.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenSach.setBounds(10, 63, 104, 19);
		panel.add(lblTenSach);

		txtTenSach = new JTextField();
		txtTenSach.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtTenSach.getText().equals("VD: Bố Già...")) {
					txtTenSach.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTenSach.getText().isEmpty()) {
					txtTenSach.setText("VD: Bố Già...");
				}
			}
		});
		txtTenSach.setText("VD: Bố Già...");
		txtTenSach.setColumns(10);
		txtTenSach.setBounds(10, 87, 225, 23);
		panel.add(txtTenSach);

		JLabel lblNamXuatBan = new JLabel("Năm Xuất Bản");
		lblNamXuatBan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNamXuatBan.setBounds(283, 10, 144, 19);
		panel.add(lblNamXuatBan);

		txtNamXuatBan = new JTextField();
		txtNamXuatBan.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtNamXuatBan.getText().equals("VD: 2000...")) {
					txtNamXuatBan.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtNamXuatBan.getText().isEmpty()) {
					txtNamXuatBan.setText("VD: 2000...");
				}
			}
		});
		txtNamXuatBan.setText("VD: 2000...");
		txtNamXuatBan.setBounds(283, 34, 202, 19);
		panel.add(txtNamXuatBan);
		txtNamXuatBan.setColumns(10);

		JLabel lblTrangThai = new JLabel("Trạng Thái Sản Xuất");
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTrangThai.setBounds(10, 116, 168, 19);
		panel.add(lblTrangThai);

		rdoCon = new JRadioButton("Còn");
		buttonGroup.add(rdoCon);
		rdoCon.setSelected(true);
		rdoCon.setBounds(10, 141, 92, 21);
		panel.add(rdoCon);

		rdoKhong = new JRadioButton("Không");
		buttonGroup.add(rdoKhong);
		rdoKhong.setBounds(117, 141, 92, 21);
		panel.add(rdoKhong);

		JLabel lblNgayNhap = new JLabel("Ngày Nhập");
		lblNgayNhap.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgayNhap.setBounds(283, 63, 144, 19);
		panel.add(lblNgayNhap);

		txtNgayNhap = new JTextField();
		txtNgayNhap.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtNgayNhap.getText().equals("VD: 2023-12-12...")) {
					txtNgayNhap.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtNgayNhap.getText().isEmpty()) {
					txtNgayNhap.setText("VD: 2023-12-12...");
				}
			}
		});
		txtNgayNhap.setText("VD: 2023-12-12...");
		txtNgayNhap.setBounds(283, 87, 202, 19);
		panel.add(txtNgayNhap);
		txtNgayNhap.setColumns(10);

		cbxNhaXuatBan = new JComboBox();
		cbxNhaXuatBan.setBounds(10, 191, 475, 30);
		panel.add(cbxNhaXuatBan);
		cbxNhaXuatBan.setModel(modelCombobox);

		JLabel lblNhaXuatBan = new JLabel("Nhà Xuất Bản");
		lblNhaXuatBan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNhaXuatBan.setBounds(10, 168, 144, 19);
		panel.add(lblNhaXuatBan);

		JPanel pnlButton2 = new JPanel();
		pnlButton2.setBounds(764, 520, 377, 30);
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
		btnFirst.setEnabled(false);
		pnlButton2.add(btnFirst);

		btnPrevEdit = new JButton("Prev");
		btnPrevEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index--;
				table.setRowSelectionInterval(index, index);
				edit();
			}
		});
		btnPrevEdit.setEnabled(false);
		pnlButton2.add(btnPrevEdit);

		btnNextEdit = new JButton("Next");
		btnNextEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index++;
				table.setRowSelectionInterval(index, index);
				edit();
			}
		});
		btnNextEdit.setEnabled(false);
		pnlButton2.add(btnNextEdit);

		btnLast = new JButton("Last");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index = model.getRowCount() - 1;
				table.setRowSelectionInterval(index, index);
				edit();
			}
		});
		btnLast.setEnabled(false);
		pnlButton2.add(btnLast);

		JPanel pnlDanhSach = new JPanel();
		pnlDanhSach.setLayout(null);
		pnlDanhSach.setBorder(new TitledBorder(

				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),

				"Danh S\u00E1ch", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		pnlDanhSach.setBounds(538, 86, 758, 378);
		add(pnlDanhSach);

		JLabel lblTimKiem = new JLabel("Tìm Kiếm");
		lblTimKiem.setBounds(10, 20, 55, 13);
		pnlDanhSach.add(lblTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtTimKiem.getText().equals("Nhập vào mã sách hoặc tên sách để tìm kiếm")) {
					txtTimKiem.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTimKiem.getText().isEmpty()) {
					txtTimKiem.setText("Nhập vào mã sách hoặc tên sách để tìm kiếm");
				}
			}
		});
		txtTimKiem.setText("Nhập vào mã sách hoặc tên sách để tìm kiếm");
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(85, 17, 525, 29);
		pnlDanhSach.add(txtTimKiem);

		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setIcon(new ImageIcon(SachJPanel.class.getResource("/icon/Search.png")));
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtTimKiem.getText().equals(" ")) {
					load(indexTrang);
				} else {
					search(txtTimKiem.getText().trim());
				}

			}
		});
		btnTimKiem.setBounds(620, 16, 128, 29);
		pnlDanhSach.add(btnTimKiem);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 738, 307);
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
		String[] columns = { "ID", "Mã Sách", "Tên Sách", "Năm XB", "Ngày Nhập", "Tình Trạng", "NXB" };
		Object[][] rows = {

		};
		model = new DefaultTableModel(rows, columns);
		table.setModel(model);
		btnPrevList = new JButton("Prev");
		btnPrevList.setIcon(new ImageIcon(SachJPanel.class.getResource("/icon/Left.png")));
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
		btnPrevList.setBounds(764, 480, 107, 30);
		add(btnPrevList);

		btnNextList = new JButton("Next");
		btnNextList.setIcon(new ImageIcon(SachJPanel.class.getResource("/icon/Right.png")));
		btnNextList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indexTrang++;

				if (indexTrang > (Math.ceil(sd.selectAll().size() * 1.0 / 15))) {
					DialogHelper.alert(null, "Đây là trang cuối cùng !");
					indexTrang--;
				} else {

					load(indexTrang);
					lblIndexTrang.setText(indexTrang + "");
				}
			}
		});
		btnNextList.setBounds(1034, 480, 107, 30);
		add(btnNextList);

		lblIndexTrang = new JLabel("1");
		lblIndexTrang.setBounds(955, 489, 39, 13);
		add(lblIndexTrang);

		btnInsert = new JButton("Insert");
		btnInsert.setIcon(new ImageIcon(SachJPanel.class.getResource("/icon/Create.png")));
		btnInsert.setBounds(71, 401, 131, 30);
		add(btnInsert);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});
		btnInsert.setEnabled(true);

		btnClear = new JButton("Clear");
		btnClear.setIcon(new ImageIcon(SachJPanel.class.getResource("/icon/Trash.png")));
		btnClear.setBounds(314, 401, 131, 30);
		add(btnClear);

		btnDelete = new JButton("Delete");
		btnDelete.setIcon(new ImageIcon(SachJPanel.class.getResource("/icon/delete2.png")));
		btnDelete.setBounds(71, 484, 131, 30);
		add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDelete.setEnabled(false);

		btnUpdate = new JButton("Update");
		btnUpdate.setIcon(new ImageIcon(SachJPanel.class.getResource("/icon/Upload.png")));
		btnUpdate.setBounds(314, 484, 131, 30);
		add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnUpdate.setEnabled(false);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				load(indexTrang);
				fillCombobox();
				return null;
			}

			@Override
			protected void done() {
				super.done();
			}

		};
		worker.execute();

	}

	void search(String key) {
		SwingWorker<List<Sach>, Void> worker = new SwingWorker<List<Sach>, Void>() {

			@Override
			protected List<Sach> doInBackground() throws Exception {
				return sd.selectByKeyword(key);
			}

			@Override
			protected void done() {
				try {
					List<Sach> listSach = get();
					if (listSach.isEmpty()) {
						DialogHelper.alert(SachJPanel.this, "Không tồn tại");
					} else {
						model.setRowCount(0);
						for (Sach s : listSach) {
							Object[] row = { s.getId(), s.getMaSach(), s.getTenSach(), s.getNamXB(), s.getNgayNhap(),
									s.isTinhTrang() ? "Còn Sản Xuất" : "Ngừng Sản Xuất", s.getIdNXB().getTenNXB() };
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

	void load(int soTrang) {
		// Thực hiện truy vấn cơ sở dữ liệu trong một SwingWorker
		SwingWorker<List<Sach>, Void> worker = new SwingWorker<List<Sach>, Void>() {
			@Override
			protected List<Sach> doInBackground() throws Exception {
				return sd.loadTrang((soTrang - 1) * 15, 15);
			}

			@Override
			protected void done() {
				try {
					List<Sach> list = get();
					model.setRowCount(0);
					for (Sach s : list) {
						Object[] row = { s.getId(), s.getMaSach(), s.getTenSach(), s.getNamXB(), s.getNgayNhap(),
								s.isTinhTrang() ? "Còn Sản Xuất" : "Ngừng Sản Xuất", s.getIdNXB().getTenNXB() };
						model.addRow(row);
					}
				} catch (Exception e) {
					DialogHelper.alert(SachJPanel.this, "Lỗi truy vấn dữ liệu!");
					e.printStackTrace();
				}
			}
		};

		worker.execute();
	}

	void fillCombobox() {
		SwingWorker<List<NXB>, Void> worker = new SwingWorker<List<NXB>, Void>() {

			@Override
			protected List<NXB> doInBackground() throws Exception {
				return nxbd.selectAll();
			}

			@Override
			protected void done() {
				try {
					List<NXB> list = get();
					modelCombobox.removeAllElements();
					for (NXB nxb : list) {
						modelCombobox.addElement(nxb);
					}
				} catch (Exception e) {
					DialogHelper.alert(SachJPanel.this, "Lỗi truy vấn dữ liệu!");
					e.printStackTrace();
				}
			}

		};
		worker.execute();
	}

	void insert() {
		try {
			Sach tg = getForm();
			if (tg != null) {
				Sach s = sd.selectById(tg.getMaSach());
				if (s == null) {
					sd.insert(tg);
					load(indexTrang);
					clear();
					DialogHelper.alert(this, "Insert Successful");
				} else {
					DialogHelper.alert(this, "Mã sách đã tồn tại");
				}

			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Insert Failed");
		}

	}

	void delete() {
		try {
			if (DialogHelper.confirm(this, "Bạn có chắc chắn muốn xóa không ?")) {
				String maSach = (String) table.getValueAt(this.index, 1);
				Sach s = sd.selectById(maSach);
				sd.delete(s.getMaSach());
				load(indexTrang);
				clear();
				DialogHelper.alert(this, "Delete Successful");
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Delete Failed");
		}

	}

	void update() {
		try {
			if (DialogHelper.confirm(this, "Bạn có chắc chắn muốn Update không ?")) {
				Sach tg = getForm();
				if (tg != null) {
					sd.update(tg);
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
		txtMaSach.setText("VD: S0001...");
		txtNgayNhap.setText("VD: 2023-12-12...");
		txtNamXuatBan.setText("VD: 2000...");
		txtTenSach.setText("VD: Bố Già...");
		txtTimKiem.setText("Nhập vào mã sách hoặc tên sách để tìm kiếm");
		cbxNhaXuatBan.setSelectedIndex(0);
		setStatus(true);
		table.clearSelection();
	}

	void setForm(Sach s) {
		txtMaSach.setText(s.getMaSach());
		txtNamXuatBan.setText(s.getNamXB() + "");
		txtNgayNhap.setText(s.getNgayNhap() + "");
		txtTenSach.setText(s.getTenSach());
		if (s.isTinhTrang() == true) {
			rdoCon.setSelected(true);
		} else {
			rdoKhong.setSelected(true);
		}
		cbxNhaXuatBan.setSelectedItem(s.getIdNXB());
//		for (int i = 0; i < modelCombobox.getSize(); i++) {
//			NXB nxb = modelCombobox.getElementAt(i);
//			if (s.getIdNXB().getTenNXB().equalsIgnoreCase(nxb.getTenNXB())) {
//				cbxNhaXuatBan.setSelectedIndex(i);
//				break;
//			}
//		}

	}

	Sach getForm() {
		Sach model = new Sach();
		if (txtMaSach.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống mã quyển sách");
			return null;
		} else {
			if (txtMaSach.getText().matches(regexMaSach)) {
				model.setMaSach(txtMaSach.getText());
			} else {
				DialogHelper.alert(this, "Nhập đúng định dạng mã sách");
				return null;
			}

		}

		if (txtNamXuatBan.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống năm xuất bản");
			return null;
		} else {
			try {
				int namXB = Integer.parseInt(txtNamXuatBan.getText());
				if (namXB <= 0 || namXB > 2023) {
					DialogHelper.alert(this, "Năm xuất bản không hợp lệ");
					return null;
				} else {
					model.setNamXB(Integer.valueOf(txtNamXuatBan.getText()));
				}
			} catch (Exception e) {
				DialogHelper.alert(this, "Năm xuất bản không hợp lệ ");
				return null;
			}

		}

		if (txtNgayNhap.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống ngày nhập");
			return null;
		} else {
			if (isValidDateFormat(txtNgayNhap.getText())) {
				model.setNgayNhap(XDate.toDate(txtNgayNhap.getText(), "yyyy-MM-dd"));
			} else {
				DialogHelper.alert(this, "Ngày nhập không hợp lệ");
				return null;
			}
		}

		if (rdoCon.isSelected()) {
			model.setTinhTrang(true);
		} else {
			model.setTinhTrang(false);
		}

		if (txtTenSach.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống tên sách");
			return null;
		} else {
			if (txtTenSach.getText().length() < 0) {
				DialogHelper.alert(this, "Độ dài tên không hợp lệ");
				return null;
			} else {
				if (txtTenSach.getText().equals("VD: Bố Già...")) {
					DialogHelper.alert(this, "Tên không hợp lệ, Nhập lại !!!");
					return null;
				} else {
					model.setTenSach(txtTenSach.getText());
				}

			}

		}

		model.setIdNXB((NXB) cbxNhaXuatBan.getSelectedItem());
		return model;
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
			String maSach = (String) table.getValueAt(this.index, 1);
			Sach s = sd.selectById(maSach);
			if (s != null) {
				setForm(s);
				setStatus(false);
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
		}

	}

	void setStatus(boolean insertable) {
		txtMaSach.setEditable(insertable);
		btnInsert.setEnabled(insertable);
		btnUpdate.setEnabled(!insertable);
		if (ShareHelper.USER.isVaiTro()) {
			btnDelete.setEnabled(!insertable);
		}
		boolean first = this.index > 0;
		boolean last = this.index < model.getRowCount() - 1;
		btnFirst.setEnabled(!insertable && first);
		btnPrevEdit.setEnabled(!insertable && first);
		btnNextEdit.setEnabled(!insertable && last);
		btnLast.setEnabled(!insertable && last);
	}
}
