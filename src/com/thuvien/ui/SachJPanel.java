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
import java.util.List;

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
import com.thuvien.entity.HangThanhVien;
import com.thuvien.entity.NXB;
import com.thuvien.entity.Sach;
import com.thuvien.utils.DialogHelper;
import com.thuvien.utils.XDate;

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
		panel.setBounds(23, 108, 495, 215);
		add(panel);
		panel.setLayout(null);

		JLabel lblMaSach = new JLabel("Mã Sách");
		lblMaSach.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaSach.setBounds(10, 10, 104, 19);
		panel.add(lblMaSach);

		txtMaSach = new JTextField();
		txtMaSach.setBounds(10, 34, 225, 19);
		panel.add(txtMaSach);
		txtMaSach.setColumns(10);

		JLabel lblTenSach = new JLabel("Tên Sách");
		lblTenSach.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenSach.setBounds(10, 63, 104, 19);
		panel.add(lblTenSach);

		txtTenSach = new JTextField();
		txtTenSach.setColumns(10);
		txtTenSach.setBounds(10, 87, 225, 19);
		panel.add(txtTenSach);

		JLabel lblNamXuatBan = new JLabel("Năm Xuất Bản");
		lblNamXuatBan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNamXuatBan.setBounds(283, 10, 144, 19);
		panel.add(lblNamXuatBan);

		txtNamXuatBan = new JTextField();
		txtNamXuatBan.setBounds(283, 34, 183, 19);
		panel.add(txtNamXuatBan);
		txtNamXuatBan.setColumns(10);

		JLabel lblTrangThai = new JLabel("Trạng Thái Sản Xuất");
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTrangThai.setBounds(10, 126, 168, 19);
		panel.add(lblTrangThai);

		rdoCon = new JRadioButton("Còn");
		buttonGroup.add(rdoCon);
		rdoCon.setSelected(true);
		rdoCon.setBounds(10, 151, 92, 21);
		panel.add(rdoCon);

		rdoKhong = new JRadioButton("Không");
		buttonGroup.add(rdoKhong);
		rdoKhong.setBounds(115, 151, 92, 21);
		panel.add(rdoKhong);

		JLabel lblNgayNhap = new JLabel("Ngày Nhập");
		lblNgayNhap.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgayNhap.setBounds(283, 63, 144, 19);
		panel.add(lblNgayNhap);

		txtNgayNhap = new JTextField();
		txtNgayNhap.setBounds(283, 87, 183, 19);
		panel.add(txtNgayNhap);
		txtNgayNhap.setColumns(10);

		cbxNhaXuatBan = new JComboBox();
		cbxNhaXuatBan.setBounds(279, 155, 206, 21);
		panel.add(cbxNhaXuatBan);
		cbxNhaXuatBan.setModel(modelCombobox);

		JLabel lblNhaXuatBan = new JLabel("Nhà Xuất Bản");
		lblNhaXuatBan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNhaXuatBan.setBounds(283, 126, 144, 19);
		panel.add(lblNhaXuatBan);

		JPanel pnlButton1 = new JPanel();
		pnlButton1.setBounds(97, 356, 350, 30);
		add(pnlButton1);
		pnlButton1.setLayout(new GridLayout(1, 4, 10, 0));

		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});
		btnInsert.setEnabled(true);
		pnlButton1.add(btnInsert);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDelete.setEnabled(false);
		pnlButton1.add(btnDelete);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnUpdate.setEnabled(false);
		pnlButton1.add(btnUpdate);

		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		pnlButton1.add(btnClear);

		JPanel pnlButton2 = new JPanel();
		pnlButton2.setBounds(97, 406, 350, 30);
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
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(85, 17, 525, 19);
		pnlDanhSach.add(txtTimKiem);

		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setBounds(637, 16, 97, 21);
		pnlDanhSach.add(btnTimKiem);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 724, 307);
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
		String[] columns = { "Mã Sách", "Tên Sách", "Năm XB", "Ngày Nhập", "Tình Trạng", "NXB" };
		Object[][] rows = {

		};
		model = new DefaultTableModel(rows, columns);
		table.setModel(model);
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
		btnPrevList.setBounds(764, 489, 85, 21);
		add(btnPrevList);

		btnNextList = new JButton("Next");
		btnNextList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indexTrang++;

				if (indexTrang > (Math.ceil(sd.selectAll().size() * 1.0 / 5))) {
					DialogHelper.alert(null, "Đây là trang cuối cùng !");
					indexTrang--;
				} else {

					load(indexTrang);
					lblIndexTrang.setText(indexTrang + "");
				}
			}
		});
		btnNextList.setBounds(1056, 489, 85, 21);
		add(btnNextList);

		lblIndexTrang = new JLabel("1");
		lblIndexTrang.setBounds(954, 493, 39, 13);
		add(lblIndexTrang);
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

	void load(int soTrang) {
		// Thực hiện truy vấn cơ sở dữ liệu trong một SwingWorker
		SwingWorker<List<Sach>, Void> worker = new SwingWorker<List<Sach>, Void>() {
			@Override
			protected List<Sach> doInBackground() throws Exception {
				return sd.loadTrang((soTrang - 1) * 5, 5);
			}

			@Override
			protected void done() {
				try {
					List<Sach> list = get();
					model.setRowCount(0);
					for (Sach s : list) {
						Object[] row = { s.getMaSach(), s.getTenSach(), s.getNamXB(), s.getNgayNhap(),
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
				sd.insert(tg);
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
				Sach tg = getForm();
				sd.delete(tg.getMaSach());
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
				sd.update(tg);
				load(indexTrang);
				clear();
				DialogHelper.alert(this, "Update Successful");
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Update Failed");
		}

	}

	void clear() {
		txtMaSach.setText("");
		txtNgayNhap.setText("");
		txtNamXuatBan.setText("");
		txtTenSach.setText("");
		cbxNhaXuatBan.setSelectedIndex(0);
		setStatus(true);
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
			return null;
		} else {
			model.setMaSach(txtMaSach.getText());
		}

		if (txtNamXuatBan.getText().isEmpty()) {
			return null;
		} else {
			model.setNamXB(Integer.valueOf(txtNamXuatBan.getText()));
		}

		if (txtNgayNhap.getText().isEmpty()) {
			return null;
		} else {
			model.setNgayNhap(XDate.toDate(txtNgayNhap.getText(), "yyyy-MM-dd"));
		}

		if (rdoCon.isSelected()) {
			model.setTinhTrang(true);
		} else {
			model.setTinhTrang(false);
		}

		if (txtTenSach.getText().isEmpty()) {
			return null;
		} else {
			model.setTenSach(txtTenSach.getText());
		}

		model.setIdNXB((NXB) cbxNhaXuatBan.getSelectedItem());
		return model;
	}

	void edit() {
		try {
			String maSach = (String) table.getValueAt(this.index, 0);
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
//		SwingWorker<List<Sach>, Void> worker = new SwingWorker<List<Sach>, Void>() {
//			@Override
//			protected List<Sach> doInBackground() throws Exception {
//				return sachDao.selectById(keyword);
//			}
//
//			@Override
//			protected void done() {
//				try {
//					List<TacGia> list = get();
//					if (list.size() == 0) {
//						DialogHelper.alert(null, "Không tồn tại ");
//					} else {
//						model.setRowCount(0);
//						for (TacGia tg : list) {
//							Object[] row = { tg.getMaTG(), tg.getHoTen(), tg.getQuocTich(),
//									tg.isGioiTinh() ? "Nam" : "Nữ" };
//							model.addRow(row);
//						}
//					}
//
//				} catch (Exception e) {
//					DialogHelper.alert(SachJPanel.this, "Lỗi truy vấn dữ liệu!");
//				}
//			}
//		};
//
//		worker.execute();
//	}
}
