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
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.thuvien.dao.HangTheThanhVienDao;
import com.thuvien.dao.ThanhVienDao;
import com.thuvien.dao.TheThanhVienDao;
import com.thuvien.entity.HangThanhVien;
import com.thuvien.entity.ThanhVien;
import com.thuvien.entity.TheThanhVien;
import com.thuvien.utils.DialogHelper;
import com.thuvien.utils.XDate;

public class TheThanhVienJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaTheThanhVien;
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
	private JButton btnFirst;
	private JButton btnPrevEdit;
	private JButton btnNextEdit;
	private JButton btnLast;
	private JLabel lblIndexTrang;
	private JTextField txtNgayHieuLuc;
	private JTextField txtNgayCap;
	private JTextField txtOldID;
	TheThanhVienDao ttvd = new TheThanhVienDao();
	private JComboBox cbxHangThanhVien;
	private JComboBox cbxTrangThai;
	DefaultComboBoxModel cbxModel = new DefaultComboBoxModel<>();
	HangTheThanhVienDao htvd = new HangTheThanhVienDao();
	ThanhVienDao tvd = new ThanhVienDao();
	private JTextField txtID;
	private JComboBox cbxThoiGianHieuLuc;
	private JComboBox cbxThanhVien;
	DefaultComboBoxModel<ThanhVien> cbxModelThanhVien = new DefaultComboBoxModel<>();

	public TheThanhVienJPanel() {
		setLayout(null);
		JLabel lblTitle = new JLabel("Quản Lý  Thẻ Thành Viên");
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setBounds(475, 10, 400, 37);
		add(lblTitle);

		JPanel pnlThongTinTG = new JPanel();
		pnlThongTinTG.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlThongTinTG.setBounds(10, 103, 459, 281);
		add(pnlThongTinTG);
		pnlThongTinTG.setLayout(null);

		JLabel lblMaThanhVien = new JLabel("Mã Thẻ");
		lblMaThanhVien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaThanhVien.setBounds(10, 67, 157, 19);
		pnlThongTinTG.add(lblMaThanhVien);

		txtMaTheThanhVien = new JTextField();
		txtMaTheThanhVien.setColumns(10);
		txtMaTheThanhVien.setBounds(10, 89, 198, 19);
		pnlThongTinTG.add(txtMaTheThanhVien);

		JLabel lblHangThanhVien = new JLabel("Hạng Thành Viên");
		lblHangThanhVien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHangThanhVien.setBounds(10, 118, 139, 19);
		pnlThongTinTG.add(lblHangThanhVien);

		JLabel lblThanhVien = new JLabel("Thành Viên");
		lblThanhVien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblThanhVien.setBounds(10, 164, 104, 19);
		pnlThongTinTG.add(lblThanhVien);

		JLabel lblThoiGianHieuLuc = new JLabel("Thời Gian Hiệu Lực");
		lblThoiGianHieuLuc.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblThoiGianHieuLuc.setBounds(252, 118, 162, 19);
		pnlThongTinTG.add(lblThoiGianHieuLuc);

		JLabel lblNgayHieuLuc = new JLabel("Ngày Hiệu Lực");
		lblNgayHieuLuc.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgayHieuLuc.setBounds(252, 67, 139, 19);
		pnlThongTinTG.add(lblNgayHieuLuc);

		txtNgayHieuLuc = new JTextField();
		txtNgayHieuLuc.setColumns(10);
		txtNgayHieuLuc.setBounds(252, 89, 193, 19);
		pnlThongTinTG.add(txtNgayHieuLuc);

		JLabel lblNgayCap = new JLabel("Ngày Cấp");
		lblNgayCap.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgayCap.setBounds(252, 16, 139, 19);
		pnlThongTinTG.add(lblNgayCap);

		txtNgayCap = new JTextField();
		txtNgayCap.setColumns(10);
		txtNgayCap.setBounds(252, 38, 193, 19);
		pnlThongTinTG.add(txtNgayCap);

		JLabel lblOldID = new JLabel("Old ID");
		lblOldID.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblOldID.setBounds(10, 223, 104, 19);
		pnlThongTinTG.add(lblOldID);

		txtOldID = new JTextField();
		txtOldID.setColumns(10);
		txtOldID.setBounds(10, 241, 198, 19);
		pnlThongTinTG.add(txtOldID);

		cbxHangThanhVien = new JComboBox();
		cbxHangThanhVien.setBounds(10, 138, 198, 21);

		pnlThongTinTG.add(cbxHangThanhVien);

		cbxThoiGianHieuLuc = new JComboBox();
		cbxThoiGianHieuLuc.setModel(new DefaultComboBoxModel(new String[] { "3", "6", "9", "12", "24" }));
		cbxThoiGianHieuLuc.setBounds(252, 138, 198, 21);
		pnlThongTinTG.add(cbxThoiGianHieuLuc);

		JLabel lblTrangThai = new JLabel("Trạng Thái");
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTrangThai.setBounds(252, 164, 104, 19);
		pnlThongTinTG.add(lblTrangThai);

		cbxTrangThai = new JComboBox();
		cbxTrangThai.setModel(new DefaultComboBoxModel(new String[] { "Hoạt Động", "Ngừng Hoạt Động" }));
		cbxTrangThai.setBounds(252, 190, 198, 21);
		pnlThongTinTG.add(cbxTrangThai);

		JLabel lblID = new JLabel("ID");
		lblID.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblID.setBounds(10, 16, 104, 19);
		pnlThongTinTG.add(lblID);

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setEnabled(false);
		txtID.setColumns(10);
		txtID.setBounds(10, 38, 198, 19);
		pnlThongTinTG.add(txtID);

		cbxThanhVien = new JComboBox();
		cbxThanhVien.setBounds(10, 190, 198, 21);
		cbxThanhVien.setModel(cbxModelThanhVien);
		pnlThongTinTG.add(cbxThanhVien);

		JPanel pnlDanhSach = new JPanel();
		pnlDanhSach.setLayout(null);
		pnlDanhSach.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh S\u00E1ch", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		pnlDanhSach.setBounds(496, 83, 819, 378);
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
		txtTimKiem.setBounds(85, 17, 567, 19);
		pnlDanhSach.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		JButton btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnTimKiem.setBounds(687, 16, 97, 21);
		pnlDanhSach.add(btnTimKiem);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 788, 307);
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
		String[] columns = { "ID", "Mã Thẻ", "Hạng Thẻ", "Ngày Cấp", "Ngày Hiệu Lực", "Thời Gian Hiệu Lực",
				"Thành Viên", "OLD ID", "Trạng Thái" };
		Object[][] rows = {

		};
		model = new DefaultTableModel(rows, columns);
		table.setModel(model);

		JPanel pnlButton2 = new JPanel();
		pnlButton2.setBounds(67, 452, 350, 30);
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
		pnlButton1.setBounds(67, 412, 350, 30);
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
		btnPrevList.setBounds(731, 487, 85, 21);
		add(btnPrevList);

		btnNextList = new JButton("Next");
		btnNextList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indexTrang++;

				if (indexTrang > (Math.ceil(ttvd.selectAll().size() * 1.0 / 5))) {
					DialogHelper.alert(null, "Đây là trang cuối cùng !");
					indexTrang--;
				} else {

					load(indexTrang);
					lblIndexTrang.setText(indexTrang + "");
				}
			}
		});
		btnNextList.setBounds(1021, 487, 85, 21);
		add(btnNextList);

		setStatus(true);
		fillCbxHang();
		lblIndexTrang = new JLabel("1");
		lblIndexTrang.setBounds(915, 491, 36, 13);
		add(lblIndexTrang);
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
			}
		};

		worker.execute();
	}

	void load(int soTrang) {
		SwingWorker<List<TheThanhVien>, Void> worker = new SwingWorker<List<TheThanhVien>, Void>() {
			@Override
			protected List<TheThanhVien> doInBackground() throws Exception {
				return ttvd.loadTrang((soTrang - 1) * 5, 5);
			}

			@Override
			protected void done() {
				try {
					List<TheThanhVien> list = get();
					model.setRowCount(0);
					for (TheThanhVien ttv : list) {
						Object[] row = { ttv.getId(), ttv.getMaTTV(), htvd.selectById(ttv.getIdHangTV()),
								ttv.getNgayCap(), ttv.getNgayHieuLuc(), ttv.getTgHieuLuc(), ttv.getIdThanhVien(),
								ttv.getOldID(), ttv.isTrangThai() ? "Còn Hiệu Lực" : "Hết Hiệu Lực" };
						model.addRow(row);
					}
				} catch (Exception e) {
					DialogHelper.alert(TheThanhVienJPanel.this, "Lỗi truy vấn dữ liệu!");
				}
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
					cbxModelThanhVien.removeAllElements();
					for (ThanhVien tv : list) {
						cbxModelThanhVien.addElement(tv);
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
		};
		worker.execute();
	}

	void insert() {
		try {
			TheThanhVien tg = getForm();
			if (tg != null) {
				ttvd.insert(tg);
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
				TheThanhVien tg = getForm();
				ttvd.delete(tg.getId());
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
				TheThanhVien tg = getForm();
				ttvd.update(tg);
				load(indexTrang);
				clear();
				DialogHelper.alert(this, "Update Successful");
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Update Failed");
			e.printStackTrace();
		}
	}

	void clear() {
		txtID.setText("");
		txtMaTheThanhVien.setText("");
		txtOldID.setText("");
		txtNgayCap.setText("");
		txtNgayHieuLuc.setText("");
		cbxHangThanhVien.setSelectedIndex(0);
		cbxThanhVien.setSelectedIndex(0);
		cbxThoiGianHieuLuc.setSelectedIndex(0);
		cbxTrangThai.setSelectedIndex(0);
		setStatus(true);

	}

	void setForm(TheThanhVien ttv) {
		txtID.setText(ttv.getId() + "");
		txtMaTheThanhVien.setText(ttv.getMaTTV());
		for (int i = 0; i < cbxModel.getSize(); i++) {
			HangThanhVien htv = (HangThanhVien) cbxModel.getElementAt(i);
			if (ttv.getIdHangTV() == htv.getId()) {
				cbxHangThanhVien.setSelectedIndex(i);
				break;
			}
		}
		txtNgayCap.setText(ttv.getNgayCap() + "");
		txtNgayHieuLuc.setText(ttv.getNgayHieuLuc() + "");
//		cbxThoiGianHieuLuc.setSelectedItem(ttv.getTgHieuLuc());
		String tgHieuLuc = String.valueOf(ttv.getTgHieuLuc());
		cbxThoiGianHieuLuc.setSelectedItem(tgHieuLuc);
		txtOldID.setText(ttv.getOldID() + "");
		if (ttv.isTrangThai()) {
			cbxTrangThai.setSelectedIndex(0);
		} else {
			cbxTrangThai.setSelectedIndex(1);
		}

	}

	TheThanhVien getForm() {
		TheThanhVien ttv = new TheThanhVien();

		if (txtID.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống id");
			return null;
		} else {
			ttv.setId(Integer.valueOf(txtID.getText()));
		}

		if (txtMaTheThanhVien.getText().isEmpty()) {
			DialogHelper.alert(this, "Khong de trong ma the");
			return null;
		} else {
			ttv.setMaTTV(txtMaTheThanhVien.getText());
		}

		if (txtNgayCap.getText().isEmpty()) {
			DialogHelper.alert(this, "Khong de trong ngay cap");
			return null;
		} else {
			Date nc = XDate.toDate(txtNgayCap.getText(), "yyyy-MM-dd");
			ttv.setNgayCap(nc);
		}

		if (txtNgayHieuLuc.getText().isEmpty()) {

		} else {
			ttv.setNgayHieuLuc(XDate.toDate(txtNgayHieuLuc.getText(), "yyyy-MM-dd"));
		}

		if (txtOldID.getText().isEmpty()) {
			ttv.setOldID(0);
		} else {
			ttv.setOldID(Integer.valueOf(txtOldID.getText()));
		}

		HangThanhVien htv = (HangThanhVien) cbxHangThanhVien.getSelectedItem();
		ttv.setIdHangTV(htv.getId());
		Integer tg = Integer.valueOf((String) cbxThoiGianHieuLuc.getSelectedItem());
		ttv.setTgHieuLuc(tg);

		ThanhVien tv = (ThanhVien) cbxThanhVien.getSelectedItem();
		ttv.setIdThanhVien(tv);
		ttv.setTrangThai(cbxTrangThai.getSelectedItem() == "Hoạt Động" ? true : false);
		return ttv;
	}

	void edit() {
		try {
			int id = (int) table.getValueAt(this.index, 0);
			TheThanhVien ttv = ttvd.selectById(id);
			if (ttv != null) {
				setForm(ttv);
				setStatus(false);
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
		}

	}

	void setStatus(boolean insertable) {
		txtMaTheThanhVien.setEditable(insertable);
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

	public void fillCbxHang() {
		cbxHangThanhVien.removeAllItems();
		List<HangThanhVien> hangList = htvd.selectAll();
		for (HangThanhVien htv : hangList) {
			cbxModel.addElement(htv);
		}
		;
		cbxHangThanhVien.setModel(cbxModel);
	}

}
