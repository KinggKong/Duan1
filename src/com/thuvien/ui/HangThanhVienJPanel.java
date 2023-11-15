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
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
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
import com.thuvien.entity.HangThanhVien;
import com.thuvien.utils.DialogHelper;

public class HangThanhVienJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaHTV;
	private JTextField txtTenHang;
	private JTextField txtDonGia;
	private final ButtonGroup buttonGroup = new ButtonGroup();
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
	private JTextField txtPhiThueSach;
	private JTextField txtThoiGianHieuLuc;
	private JTextField txtTuoiMin;
	private JTextField txtTuoiMax;
	HangTheThanhVienDao htvd = new HangTheThanhVienDao();
	private JTextField textField;

	public HangThanhVienJPanel() {
		setLayout(null);
		JLabel lblTitle = new JLabel("Quản Lý Hạng Thành Viên");
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setBounds(457, 10, 434, 37);
		add(lblTitle);

		JPanel pnlThongTinTG = new JPanel();
		pnlThongTinTG.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlThongTinTG.setBounds(87, 91, 470, 317);
		add(pnlThongTinTG);
		pnlThongTinTG.setLayout(null);

		JLabel lblMaHangThanhVien = new JLabel("Mã HTV");
		lblMaHangThanhVien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaHangThanhVien.setBounds(10, 16, 104, 19);
		pnlThongTinTG.add(lblMaHangThanhVien);

		txtMaHTV = new JTextField();
		txtMaHTV.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtMaHTV.getText().equals("VD:TG001...")) {
					txtMaHTV.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtMaHTV.getText().isEmpty()) {
					txtMaHTV.setText("VD:TG001...");
				}
			}
		});
		txtMaHTV.setColumns(10);
		txtMaHTV.setBounds(10, 38, 235, 19);
		pnlThongTinTG.add(txtMaHTV);

		JLabel lblTenHangThanhVien = new JLabel("Tên Hạng");
		lblTenHangThanhVien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenHangThanhVien.setBounds(10, 67, 104, 19);
		pnlThongTinTG.add(lblTenHangThanhVien);

		txtTenHang = new JTextField();
		txtTenHang.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtTenHang.getText().equals("VD: Nguyễn Văn A...")) {
					txtTenHang.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTenHang.getText().isEmpty()) {
					txtTenHang.setText("VD: Nguyễn Văn A...");
				}
			}
		});
		txtTenHang.setColumns(10);
		txtTenHang.setBounds(10, 90, 235, 19);
		pnlThongTinTG.add(txtTenHang);

		JLabel lblDonGia = new JLabel("Đơn Giá");
		lblDonGia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDonGia.setBounds(10, 119, 104, 19);
		pnlThongTinTG.add(lblDonGia);

		txtDonGia = new JTextField();
		txtDonGia.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtDonGia.getText().equals("VD: Việt Nam...")) {
					txtDonGia.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtDonGia.getText().isEmpty()) {
					txtDonGia.setText("VD: Việt Nam...");
				}
			}
		});
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(10, 141, 235, 19);
		pnlThongTinTG.add(txtDonGia);

		JLabel lblPhiThueSach = new JLabel("Phí Thuê Sách");
		lblPhiThueSach.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPhiThueSach.setBounds(10, 170, 140, 19);
		pnlThongTinTG.add(lblPhiThueSach);

		txtPhiThueSach = new JTextField();
		txtPhiThueSach.setColumns(10);
		txtPhiThueSach.setBounds(10, 194, 235, 19);
		pnlThongTinTG.add(txtPhiThueSach);

		JLabel lblThoiGianHieuLuc = new JLabel("Thời Gian Hiệu Lực");
		lblThoiGianHieuLuc.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblThoiGianHieuLuc.setBounds(10, 223, 180, 19);
		pnlThongTinTG.add(lblThoiGianHieuLuc);

		txtThoiGianHieuLuc = new JTextField();
		txtThoiGianHieuLuc.setColumns(10);
		txtThoiGianHieuLuc.setBounds(10, 252, 235, 19);
		pnlThongTinTG.add(txtThoiGianHieuLuc);

		JLabel lblTuoiMin = new JLabel("Tuổi Min");
		lblTuoiMin.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTuoiMin.setBounds(281, 16, 104, 19);
		pnlThongTinTG.add(lblTuoiMin);

		txtTuoiMin = new JTextField();
		txtTuoiMin.setColumns(10);
		txtTuoiMin.setBounds(278, 38, 165, 19);
		pnlThongTinTG.add(txtTuoiMin);

		JLabel lblTuoiMax = new JLabel("Tuổi Max");
		lblTuoiMax.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTuoiMax.setBounds(281, 67, 104, 19);
		pnlThongTinTG.add(lblTuoiMax);

		txtTuoiMax = new JTextField();
		txtTuoiMax.setColumns(10);
		txtTuoiMax.setBounds(278, 90, 165, 19);
		pnlThongTinTG.add(txtTuoiMax);

		JPanel pnlDanhSach = new JPanel();
		pnlDanhSach.setLayout(null);
		pnlDanhSach.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh S\u00E1ch", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		pnlDanhSach.setBounds(658, 81, 643, 378);
		add(pnlDanhSach);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 623, 307);
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
		String[] columns = { "ID", "Mã Hạng", "Tên", "Đơn Giá", "Phí Thuê", "Tháng Hiệu Lực", "Tuổi Min", "Tuổi Max" };
		Object[][] rows = {

		};
		model = new DefaultTableModel(rows, columns);
		table.setModel(model);
		
		textField = new JTextField();
		textField.setBounds(129, 24, 379, 19);
		pnlDanhSach.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBounds(530, 23, 85, 21);
		pnlDanhSach.add(btnNewButton);
		
		JLabel lblTmKim = new JLabel("Tìm Kiếm");
		lblTmKim.setBounds(15, 22, 104, 19);
		pnlDanhSach.add(lblTmKim);
		lblTmKim.setFont(new Font("Tahoma", Font.BOLD, 15));

		JPanel pnlButton2 = new JPanel();
		pnlButton2.setBounds(141, 482, 350, 30);
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
		pnlButton1.setBounds(141, 429, 350, 30);
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
		btnPrevList.setBounds(827, 491, 85, 21);
		add(btnPrevList);

		btnNextList = new JButton("Next");
		btnNextList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indexTrang++;
				if (indexTrang > (Math.ceil(htvd.selectAll().size() * 1.0 / 5))) {
					DialogHelper.alert(null, "Đây là trang cuối cùng !");
					indexTrang--;
				} else {

					load(indexTrang);
					lblIndexTrang.setText(indexTrang + "");
				}
			}
		});
		btnNextList.setBounds(1098, 491, 85, 21);
		add(btnNextList);

		setStatus(true);

		lblIndexTrang = new JLabel("1");
		lblIndexTrang.setBounds(1002, 495, 39, 13);
		add(lblIndexTrang);
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				load(indexTrang);
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
		SwingWorker<List<HangThanhVien>, Void> worker = new SwingWorker<List<HangThanhVien>, Void>() {

			@Override
			protected List<HangThanhVien> doInBackground() throws Exception {
				return htvd.loadTrang((soTrang - 1) * 5, 5);
			}

			@Override
			protected void done() {
				try {
					List<HangThanhVien> list = get();
					model.setRowCount(0);
					for (HangThanhVien htv : list) {
						Object[] rows = { htv.getId(), htv.getMaHTV(), htv.getTenHang(), htv.getDonGia(),
								htv.getPhiThueSach(), htv.getSoThangHieuLuc(), htv.getTuoiMin(), htv.getTuoiMax() };
						model.addRow(rows);
					}

				} catch (InterruptedException e) {
					DialogHelper.alert(null, "Lỗi truy vấn dữ liệu!");
					e.printStackTrace();
				} catch (ExecutionException e) {
					DialogHelper.alert(null, "Lỗi truy vấn dữ liệu!");
					e.printStackTrace();
				}

			}

		};
		worker.execute();
	}

	void insert() {
		try {
			HangThanhVien htv = getForm();
			if (htv != null) {
				htvd.insert(htv);
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
				int id = (int) table.getValueAt(index, 0);
				htvd.delete(id);
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
				HangThanhVien htv = getForm();
				htvd.update(htv);
				load(indexTrang);
				clear();
				DialogHelper.alert(this, "Update Successful");
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Update Failed");
		}

	}

	void clear() {
		txtMaHTV.setText("");
		txtTenHang.setText("");
		txtDonGia.setText("");
		txtPhiThueSach.setText("");
		txtThoiGianHieuLuc.setText("");
		txtTuoiMin.setText("");
		txtTuoiMax.setText("");
		setStatus(true);
	}

	void setForm(HangThanhVien htv) {
		txtMaHTV.setText(htv.getMaHTV());
		txtTenHang.setText(htv.getTenHang());
		txtDonGia.setText(htv.getDonGia() + "");
		txtPhiThueSach.setText(htv.getPhiThueSach() + "");
		txtThoiGianHieuLuc.setText(htv.getSoThangHieuLuc() + "");
		txtTuoiMin.setText(htv.getTuoiMin() + "");
		txtTuoiMax.setText(htv.getTuoiMax() + "");
	}

	HangThanhVien getForm() {
		HangThanhVien htv = new HangThanhVien();
		if (txtMaHTV.getText().isEmpty()) {

		} else {
			htv.setMaHTV(txtMaHTV.getText());
		}

		if (txtTenHang.getText().isEmpty()) {

		} else {
			htv.setTenHang(txtTenHang.getText());
		}

		if (txtDonGia.getText().isEmpty()) {

		} else {
			try {
				float donGia = Float.valueOf(txtDonGia.getText());
				htv.setDonGia(donGia);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		if (txtPhiThueSach.getText().isEmpty()) {

		} else {
			float donGia2 = Float.valueOf(txtPhiThueSach.getText());
			htv.setPhiThueSach(donGia2);
		}

		if (txtThoiGianHieuLuc.getText().isEmpty()) {

		} else {
			int tg = Integer.valueOf(txtThoiGianHieuLuc.getText());
			htv.setSoThangHieuLuc(tg);
		}

		if (txtTuoiMin.getText().isEmpty()) {

		} else {
			int tuoiMin = Integer.valueOf(txtTuoiMin.getText());
			htv.setTuoiMin(tuoiMin);
		}

		if (txtTuoiMax.getText().isEmpty()) {

		} else {
			int tuoiMax = Integer.valueOf(txtTuoiMax.getText());
			htv.setTuoiMax(tuoiMax);
		}
		return htv;
	}

	void edit() {
		try {
			int id = (int) table.getValueAt(index, 0);
			HangThanhVien htv = htvd.selectById(id);
			if (htv != null) {
				setForm(htv);
				setStatus(false);
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
			e.printStackTrace();
		}

	}

	void setStatus(boolean insertable) {
		txtMaHTV.setEditable(insertable);
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
}
