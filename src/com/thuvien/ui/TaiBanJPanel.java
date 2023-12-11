package com.thuvien.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.thuvien.dao.SachDao;
import com.thuvien.dao.TaiBanDao;
import com.thuvien.entity.Sach;
import com.thuvien.entity.TaiBan;
import com.thuvien.utils.DialogHelper;
import com.thuvien.utils.XDate;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TaiBanJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txLanTB;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtTimKiem;
	private JTable table;
	DefaultTableModel model;
	TaiBanDao tbd = new TaiBanDao();
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
	private JTextField txtThoiGianTB;
	private JComboBox cbxSach;

	SachDao sd = new SachDao();

	DefaultComboBoxModel<Sach> modelSach = new DefaultComboBoxModel<>();

	public TaiBanJPanel() {
		setLayout(null);
		JLabel lblTitle = new JLabel("Quản Lý Tái Bản");
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setBounds(525, 10, 327, 37);
		add(lblTitle);

		JPanel pnlThongTinTG = new JPanel();
		pnlThongTinTG.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlThongTinTG.setBounds(99, 139, 412, 247);
		add(pnlThongTinTG);
		pnlThongTinTG.setLayout(null);

		JLabel lblIDSach = new JLabel("Sách");
		lblIDSach.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIDSach.setBounds(22, 17, 104, 19);
		pnlThongTinTG.add(lblIDSach);

		JLabel lblLanTB = new JLabel("Lần Tái Bản");
		lblLanTB.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLanTB.setBounds(22, 90, 104, 19);
		pnlThongTinTG.add(lblLanTB);

		txLanTB = new JTextField();

		txLanTB.setColumns(10);
		txLanTB.setBounds(22, 113, 296, 19);
		pnlThongTinTG.add(txLanTB);

		JLabel lblThiGianTi = new JLabel("Thời Gian Tái Bản");
		lblThiGianTi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblThiGianTi.setBounds(22, 155, 150, 19);
		pnlThongTinTG.add(lblThiGianTi);

		txtThoiGianTB = new JTextField();
		txtThoiGianTB.setColumns(10);
		txtThoiGianTB.setBounds(22, 178, 296, 19);
		pnlThongTinTG.add(txtThoiGianTB);

		cbxSach = new JComboBox();
		cbxSach.setBounds(22, 41, 296, 21);
		pnlThongTinTG.add(cbxSach);
		cbxSach.setModel(modelSach);

		JPanel pnlDanhSach = new JPanel();
		pnlDanhSach.setLayout(null);
		pnlDanhSach.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh S\u00E1ch", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		pnlDanhSach.setBounds(655, 103, 649, 378);
		add(pnlDanhSach);

		JLabel lblTimKiem = new JLabel("Tìm Kiếm");
		lblTimKiem.setBounds(10, 20, 55, 13);
		pnlDanhSach.add(lblTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtTimKiem.getText().equals("Nhập vào tên quyển sách")) {
					txtTimKiem.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTimKiem.getText().isEmpty()) {
					txtTimKiem.setText("Nhập vào tên quyển sách");
				}
			}
		});
		txtTimKiem.setText("Nhập vào tên quyển sách");
		txtTimKiem.setBounds(85, 17, 409, 19);
		pnlDanhSach.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		JButton btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtTimKiem.getText().equals(" ")) {
					load(indexTrang);
				} else {
					search(txtTimKiem.getText().trim());
				}

			}
		});
		btnTimKiem.setBounds(529, 16, 97, 21);
		pnlDanhSach.add(btnTimKiem);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 629, 307);
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
		String[] columns = { "ID", "Tên Sách", "Lần TB", "Ngày TB" };
		Object[][] rows = {};
		model = new DefaultTableModel(rows, columns);
		table.setModel(model);

		JPanel pnlButton2 = new JPanel();
		pnlButton2.setBounds(135, 509, 350, 30);
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
		pnlButton1.setBounds(135, 451, 350, 30);
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
		btnPrevList.setBounds(826, 491, 85, 21);
		add(btnPrevList);

		btnNextList = new JButton("Next");
		btnNextList.setBounds(1063, 491, 85, 21);
		btnNextList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indexTrang++;

				if (indexTrang > (Math.ceil(tbd.selectAll().size() * 1.0 / 15))) {
					DialogHelper.alert(null, "Đây là trang cuối cùng !");
					indexTrang--;
				} else {

					load(indexTrang);
					lblIndexTrang.setText(indexTrang + "");
				}
			}
		});
		add(btnNextList);

		setStatus(true);

		lblIndexTrang = new JLabel("1");
		lblIndexTrang.setBounds(984, 495, 39, 13);
		add(lblIndexTrang);
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				load(indexTrang);
				fillSach();
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

	protected void search(String key) {
		SwingWorker<List<TaiBan>, Void> worker = new SwingWorker<List<TaiBan>, Void>() {

			@Override
			protected List<TaiBan> doInBackground() throws Exception {
				return tbd.selectByKeyword(key);
			}

			@Override
			protected void done() {
				try {
					List<TaiBan> listTB = get();
					if (listTB.isEmpty()) {
						DialogHelper.alert(TaiBanJPanel.this, "Không tồn tại");
					} else {
						model.setRowCount(0);
						for (TaiBan tb : listTB) {
							Object[] row = { tb.getId(), sd.selectByIdSach(tb.getIdSach()).getTenSach(),
									tb.getLanTaiBan(), tb.getThoiGianTB() };
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

	void fillSach() {
		SwingWorker<List<Sach>, Void> worker = new SwingWorker<List<Sach>, Void>() {

			@Override
			protected List<Sach> doInBackground() throws Exception {
				return sd.selectAll();
			}

			@Override
			protected void done() {
				try {
					List<Sach> list = get();
					modelSach.removeAllElements();
					for (Sach s : list) {
						modelSach.addElement(s);
					}
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};
		worker.execute();
	}

	void load(int soTrang) {
		SwingWorker<List<TaiBan>, Void> worker = new SwingWorker<List<TaiBan>, Void>() {
			@Override
			protected List<TaiBan> doInBackground() throws Exception {
				return tbd.loadTrang((soTrang - 1) * 15, 15);
			}

			@Override
			protected void done() {
				try {
					List<TaiBan> list = get();
					model.setRowCount(0);
					for (TaiBan tb : list) {
						Object[] row = { tb.getId(), sd.selectByIdSach(tb.getIdSach()).getTenSach(), tb.getLanTaiBan(),
								tb.getThoiGianTB() };
						model.addRow(row);
					}
				} catch (Exception e) {
					DialogHelper.alert(TaiBanJPanel.this, "Lỗi truy vấn dữ liệu!");
					e.printStackTrace();
				}
			}
		};

		worker.execute();
	}

	void insert() {
		try {
			TaiBan tl = getFormInsert();
			if (tl != null) {
				tbd.insert(tl);
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
				int id = (int) table.getValueAt(this.index, 0);
				tbd.delete(id);
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
				TaiBan tl = getForm();
				if (tl != null) {
					tbd.update(tl);
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
		cbxSach.setSelectedIndex(0);
		txLanTB.setText("");
		txtThoiGianTB.setText("");
		setStatus(true);
		txtTimKiem.setText("Nhập vào tên quyển sách");
		table.clearSelection();
	}

	void setForm(TaiBan tl) {
		cbxSach.setSelectedItem(sd.selectByIdSach(tl.getId()));
		txtThoiGianTB.setText(tl.getThoiGianTB() + "");
		txLanTB.setText(tl.getLanTaiBan() + "");
	}

	TaiBan getForm() {
		TaiBan tb = new TaiBan();
		int id = (int) table.getValueAt(this.index, 0);
		Sach s = (Sach) cbxSach.getSelectedItem();
		tb.setId(id);
		tb.setIdSach(s.getId());
		if (txLanTB.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống lần tái bản");
			return null;
		} else {
			try {
				int lanTV = Integer.parseInt(txLanTB.getText());
				if (lanTV <= 0) {
					DialogHelper.alert(this, "Lần tái bản không được nhỏ hơn 0");
					return null;
				} else {
					tb.setLanTaiBan(lanTV);
				}
			} catch (Exception e) {
				DialogHelper.alert(this, "Chỉ nhập số cho lần tái bản");
				return null;
			}
		}
		if (txtThoiGianTB.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống thời gian tái bản");
			return null;
		} else {
			if (isValidDateFormat(txtThoiGianTB.getText())) {
				tb.setThoiGianTB(XDate.toDate(txtThoiGianTB.getText(), "yyyy-MM-dd"));
			} else {
				DialogHelper.alert(this, "Nhập đúng định dạng thời gian tái bản (yyyy-MM-dd)");
				return null;
			}
		}
		return tb;
	}

	TaiBan getFormInsert() {
		TaiBan tb = new TaiBan();
		Sach s = (Sach) cbxSach.getSelectedItem();
		tb.setIdSach(s.getId());
		if (txLanTB.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống lần tái bản");
			return null;
		} else {
			try {
				int lanTV = Integer.parseInt(txLanTB.getText());
				if (lanTV <= 0) {
					DialogHelper.alert(this, "Lần tái bản không được nhỏ hơn 0");
					return null;
				} else {
					tb.setLanTaiBan(lanTV);
				}
			} catch (Exception e) {
				DialogHelper.alert(this, "Chỉ nhập số cho lần tái bản");
				return null;
			}
		}
		if (txtThoiGianTB.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống thời gian tái bản");
			return null;
		} else {
			if (isValidDateFormat(txtThoiGianTB.getText())) {
				tb.setThoiGianTB(XDate.toDate(txtThoiGianTB.getText(), "yyyy-MM-dd"));
			} else {
				DialogHelper.alert(this, "Nhập đúng định dạng thời gian tái bản (yyyy-MM-dd)");
				return null;
			}
		}

		return tb;
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
			TaiBan tl = tbd.selectById(id);
			if (tl != null) {
				setForm(tl);
				setStatus(false);
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
			e.printStackTrace();
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

//	void search() {
//		String keyword = txtTimKiem.getText().trim();
//		// Thực hiện tìm kiếm trong một SwingWorker
//		SwingWorker<List<TaiBan>, Void> worker = new SwingWorker<List<TaiBan>, Void>() {
//			@Override
//			protected List<TaiBan> doInBackground() throws Exception {
//				return tbd.selectByKeyword(keyword);
//			}
//
//			@Override
//			protected void done() {
//				try {
//					List<TaiBan> list = get();
//					if (list.size() == 0) {
//						DialogHelper.alert(null, "Không tồn tại ");
//					} else {
//						model.setRowCount(0);
//						for (TaiBan tl : list) {
//							Object[] row = {};
//							model.addRow(row);
//						}
//					}
//
//				} catch (Exception e) {
//					DialogHelper.alert(TaiBanJPanel.this, "Lỗi truy vấn dữ liệu!");
//				}
//			}
//		};
//
//		worker.execute();
//	}
}
