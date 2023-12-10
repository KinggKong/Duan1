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
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.thuvien.dao.TacGiaDao;
import com.thuvien.entity.TacGia;
import com.thuvien.utils.DialogHelper;

public class TacGiaJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaTG;
	private JTextField txtHoTen;
	private JTextField txtQuocTich;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtTimKiem;
	private JTable table;
	DefaultTableModel model;
	TacGiaDao tgd = new TacGiaDao();
	private JButton btnInsert;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnClear;
	private JButton btnPrevList;
	private JButton btnNextList;
	int indexTrang = 1;
	int index = 0;
	private JRadioButton rdoNam;
	private JRadioButton rdoNu;
	private JButton btnFirst;
	private JButton btnPrevEdit;
	private JButton btnNextEdit;
	private JButton btnLast;
	private JLabel lblIndexTrang;
	String regexMaTG = "^TG\\d{3}$";

	public TacGiaJPanel() {
		setLayout(null);
		JLabel lblTitle = new JLabel("Quản Lý Tác Giả");
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setBounds(503, 10, 327, 37);
		add(lblTitle);

		JPanel pnlThongTinTG = new JPanel();
		pnlThongTinTG.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlThongTinTG.setBounds(66, 93, 429, 317);
		add(pnlThongTinTG);
		pnlThongTinTG.setLayout(null);

		JLabel lblMaTG = new JLabel("Mã Tác Giả");
		lblMaTG.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaTG.setBounds(10, 16, 104, 19);
		pnlThongTinTG.add(lblMaTG);

		txtMaTG = new JTextField();
		txtMaTG.setText("VD:TG001...");
		txtMaTG.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtMaTG.getText().equals("VD:TG001...")) {
					txtMaTG.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtMaTG.getText().isEmpty()) {
					txtMaTG.setText("VD:TG001...");
				}
			}
		});
		txtMaTG.setColumns(10);
		txtMaTG.setBounds(10, 38, 296, 19);
		pnlThongTinTG.add(txtMaTG);

		JLabel lblTenTG = new JLabel("Họ Và Tên");
		lblTenTG.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenTG.setBounds(10, 92, 104, 19);
		pnlThongTinTG.add(lblTenTG);

		txtHoTen = new JTextField();
		txtHoTen.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtHoTen.getText().equals("VD: Nguyễn Văn A...")) {
					txtHoTen.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtHoTen.getText().isEmpty()) {
					txtHoTen.setText("VD: Nguyễn Văn A...");
				}
			}
		});
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(10, 113, 296, 19);
		txtHoTen.setText("VD: Nguyễn Văn A...");
		pnlThongTinTG.add(txtHoTen);

		JLabel lblQuocTich = new JLabel("Quốc Tịch");
		lblQuocTich.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblQuocTich.setBounds(10, 168, 104, 19);
		pnlThongTinTG.add(lblQuocTich);

		txtQuocTich = new JTextField();
		txtQuocTich.setText("VD: Việt Nam...");
		txtQuocTich.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtQuocTich.getText().equals("VD: Việt Nam...")) {
					txtQuocTich.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtQuocTich.getText().isEmpty()) {
					txtQuocTich.setText("VD: Việt Nam...");
				}
			}
		});
		txtQuocTich.setColumns(10);
		txtQuocTich.setBounds(10, 189, 296, 19);
		pnlThongTinTG.add(txtQuocTich);

		JLabel lblGioiTinh = new JLabel("Giới Tính");
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGioiTinh.setBounds(10, 243, 104, 19);
		pnlThongTinTG.add(lblGioiTinh);

		rdoNam = new JRadioButton("Nam");
		buttonGroup.add(rdoNam);
		rdoNam.setSelected(true);
		rdoNam.setBounds(10, 268, 81, 21);
		pnlThongTinTG.add(rdoNam);

		rdoNu = new JRadioButton("Nữ");
		buttonGroup.add(rdoNu);
		rdoNu.setBounds(93, 268, 103, 21);
		pnlThongTinTG.add(rdoNu);

		JPanel pnlDanhSach = new JPanel();
		pnlDanhSach.setLayout(null);
		pnlDanhSach.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh S\u00E1ch", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		pnlDanhSach.setBounds(561, 84, 716, 378);
		add(pnlDanhSach);

		JLabel lblTimKiem = new JLabel("Tìm Kiếm");
		lblTimKiem.setBounds(10, 20, 55, 13);
		pnlDanhSach.add(lblTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setText("Nhập vào mã hoặc tên của tác giả");
		txtTimKiem.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtTimKiem.getText().equals("Nhập vào mã hoặc tên của tác giả")) {
					txtTimKiem.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTimKiem.getText().isEmpty()) {
					txtTimKiem.setText("Nhập vào mã hoặc tên của tác giả");
				}
			}
		});
		txtTimKiem.setBounds(85, 17, 474, 19);
		pnlDanhSach.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		JButton btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnTimKiem.setBounds(585, 16, 97, 21);
		pnlDanhSach.add(btnTimKiem);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 696, 307);
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
		String[] columns = { "Mã Tác Giả", "Tên", "Quốc Tịch", "Giới Tính" };
		Object[][] rows = {

		};
		model = new DefaultTableModel(rows, columns);
		table.setModel(model);

		JPanel pnlButton2 = new JPanel();
		pnlButton2.setBounds(780, 499, 356, 30);
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
		pnlButton1.setBounds(104, 437, 350, 30);
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
		btnPrevList.setBounds(780, 472, 85, 21);
		add(btnPrevList);

		btnNextList = new JButton("Next");
		btnNextList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indexTrang++;

				if (indexTrang > (Math.ceil(tgd.selectAll().size() * 1.0 / 5))) {
					DialogHelper.alert(null, "Đây là trang cuối cùng !");
					indexTrang--;
				} else {

					load(indexTrang);
					lblIndexTrang.setText(indexTrang + "");
				}
			}
		});
		btnNextList.setBounds(1051, 472, 85, 21);
		add(btnNextList);

		setStatus(true);

		lblIndexTrang = new JLabel("1");
		lblIndexTrang.setBounds(954, 476, 39, 13);
		add(lblIndexTrang);

		JButton btnNewButton = new JButton("Tác giả chi tiết");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TacGiaChiTietJPanel tacGiaChiTietJPanel = new TacGiaChiTietJPanel();
				Container container = TacGiaJPanel.this.getParent();
				container.remove(TacGiaJPanel.this);
				container.add(tacGiaChiTietJPanel);
				container.revalidate();
				container.repaint();
			}
		});
		btnNewButton.setBounds(561, 472, 117, 21);
		add(btnNewButton);
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
		// Thực hiện truy vấn cơ sở dữ liệu trong một SwingWorker
		SwingWorker<List<TacGia>, Void> worker = new SwingWorker<List<TacGia>, Void>() {
			@Override
			protected List<TacGia> doInBackground() throws Exception {
				return tgd.loadTrang((soTrang - 1) * 5, 5);
			}

			@Override
			protected void done() {
				try {
					List<TacGia> list = get();
					model.setRowCount(0);
					for (TacGia tg : list) {
						Object[] row = { tg.getMaTG(), tg.getHoTen(), tg.getQuocTich(),
								tg.isGioiTinh() ? "Nam" : "Nữ" };
						model.addRow(row);
					}
				} catch (Exception e) {
					DialogHelper.alert(TacGiaJPanel.this, "Lỗi truy vấn dữ liệu!");
				}
			}
		};

		worker.execute();
	}

	void insert() {
		try {
			TacGia tg = getForm();
			if (tg != null) {
				TacGia tacGiaCheck = tgd.selectById(tg.getMaTG());
				if (tacGiaCheck == null) {
					tgd.insert(tg);
					load(indexTrang);
					clear();
					DialogHelper.alert(this, "Insert Successful");
				} else {
					DialogHelper.alert(this, "Mã tác giả đã tồn tại");
				}
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Insert Failed");
		}
	}

	void delete() {
		try {
			if (DialogHelper.confirm(this, "Bạn có chắc chắn muốn xóa không ?")) {
				TacGia tg = getForm();
				if (tg != null) {
					tgd.delete(tg.getMaTG());
					load(indexTrang);
					clear();
					DialogHelper.alert(this, "Delete Successful");
				}
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Delete Failed");
		}
	}

	void update() {
		try {
			if (DialogHelper.confirm(this, "Bạn có chắc chắn muốn Update không ?")) {
				TacGia tg = getForm();
				if (tg != null) {
					tgd.update(tg);
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
		txtHoTen.setText("VD: Nguyễn Văn A...");
		txtMaTG.setText("VD:TG001...");
		txtQuocTich.setText("VD: Việt Nam...");
		setStatus(true);
		table.clearSelection();
		txtTimKiem.setText("Nhập vào mã hoặc tên của tác giả");
		rdoNam.setSelected(true);

	}

	void setForm(TacGia tg) {
		txtMaTG.setText(tg.getMaTG());
		txtHoTen.setText(tg.getHoTen());
		txtQuocTich.setText(tg.getQuocTich());
		if (tg.isGioiTinh()) {
			rdoNam.setSelected(true);
		} else {
			rdoNu.setSelected(true);
		}
	}

	TacGia getForm() {
		TacGia tg = new TacGia();
		if (txtMaTG.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống mã tác giả");
			return null;
		} else {
			if (txtMaTG.getText().matches(regexMaTG)) {
				tg.setMaTG(txtMaTG.getText());
			} else {
				DialogHelper.alert(this, "Vui lòng nhập đúng định dạng mã tác giả");
				return null;
			}

		}

		if (txtHoTen.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống họ tên tác giả");
			return null;
		} else {
			if (txtHoTen.getText().length() > 8) {
				if (txtHoTen.getText().equals("VD: Nguyễn Văn A...")) {
					DialogHelper.alert(this, "Tên Không Hợp Lệ");
					return null;
				} else {
					tg.setHoTen(txtHoTen.getText());
				}

			} else {
				DialogHelper.alert(this, "Nhập đồ dài tên trên 8 ");
				return null;
			}

		}

		if (txtQuocTich.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống quốc tịch");
			return null;
		} else {
			if (txtQuocTich.getText().equals("VD: Việt Nam...")) {
				DialogHelper.alert(this, "Quốc Tịch Không Hợp Lệ");
				return null;
			} else {
				tg.setQuocTich(txtQuocTich.getText());
			}
		}

		tg.setGioiTinh(rdoNam.isSelected());
		return tg;
	}

	void edit() {
		try {
			String maTG = (String) table.getValueAt(this.index, 0);
			TacGia tg = tgd.selectById(maTG);
			if (tg != null) {
				setForm(tg);
				setStatus(false);
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
		}

	}

	void setStatus(boolean insertable) {
		txtMaTG.setEditable(insertable);
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
		String keyword = txtTimKiem.getText().trim();
		// Thực hiện tìm kiếm trong một SwingWorker
		SwingWorker<List<TacGia>, Void> worker = new SwingWorker<List<TacGia>, Void>() {
			@Override
			protected List<TacGia> doInBackground() throws Exception {
				return tgd.selectByKeyword(keyword);
			}

			@Override
			protected void done() {
				try {
					List<TacGia> list = get();
					if (list.size() == 0) {
						DialogHelper.alert(null, "Không tồn tại ");
					} else {
						model.setRowCount(0);
						for (TacGia tg : list) {
							Object[] row = { tg.getMaTG(), tg.getHoTen(), tg.getQuocTich(),
									tg.isGioiTinh() ? "Nam" : "Nữ" };
							model.addRow(row);
						}
					}

				} catch (Exception e) {
					DialogHelper.alert(TacGiaJPanel.this, "Lỗi truy vấn dữ liệu!");
				}
			}
		};

		worker.execute();
	}
}
