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
import java.text.SimpleDateFormat;
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

import com.thuvien.dao.NhanVienDao;
import com.thuvien.dao.PhieuMuonDao;
import com.thuvien.dao.ThanhVienDao;
import com.thuvien.entity.NhanVien;
import com.thuvien.entity.PhieuMuon;
import com.thuvien.entity.ThanhVien;
import com.thuvien.utils.DialogHelper;
import com.thuvien.utils.ShareHelper;
import com.thuvien.utils.XDate;

public class PhieuMuonJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNgayMuon;
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
	private JTextField txtTienCoc;
	private JTextField txtNgayTra;
	private JButton btnXemChiTiet;

	ThanhVienDao tvd = new ThanhVienDao();
	NhanVienDao nvd = new NhanVienDao();

	DefaultComboBoxModel<ThanhVien> modelThanhVien = new DefaultComboBoxModel<>();
	private JComboBox cbxThanhVien;
	private JTextField txtIDNhanVien;
	private JTextField txtMaPhieu;

	Date ngayHienTai = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	PhieuMuonDao pmd = new PhieuMuonDao();

	public PhieuMuonJPanel() {
		setLayout(null);
		JLabel lblTitle = new JLabel("Quản Lý Phiếu Mượn");
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setBounds(495, 10, 327, 37);
		add(lblTitle);

		btnXemChiTiet = new JButton("Xem Chi Tiết");

		JPanel pnlThongTinTG = new JPanel();
		pnlThongTinTG.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlThongTinTG.setBounds(31, 131, 536, 252);
		add(pnlThongTinTG);
		pnlThongTinTG.setLayout(null);

		JLabel lblMaThanhVien = new JLabel("ID Thành Viên");
		lblMaThanhVien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaThanhVien.setBounds(10, 16, 157, 19);
		pnlThongTinTG.add(lblMaThanhVien);

		JLabel lblHoTen = new JLabel("ID Nhân Viên");
		lblHoTen.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHoTen.setBounds(307, 81, 104, 19);
		pnlThongTinTG.add(lblHoTen);

		JLabel lblEmail = new JLabel("Ngày Mượn");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(10, 81, 104, 19);
		pnlThongTinTG.add(lblEmail);

		txtNgayMuon = new JTextField();
		txtNgayMuon.setEditable(false);
		txtNgayMuon.setColumns(10);
		txtNgayMuon.setBounds(10, 110, 227, 19);
		pnlThongTinTG.add(txtNgayMuon);
		txtNgayMuon.setText(dateFormat.format(ngayHienTai));

		JLabel lblNgayDangKy = new JLabel("Tiền Cọc");
		lblNgayDangKy.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgayDangKy.setBounds(10, 158, 139, 19);
		pnlThongTinTG.add(lblNgayDangKy);

		txtTienCoc = new JTextField();
		txtTienCoc.setColumns(10);
		txtTienCoc.setBounds(10, 187, 227, 19);
		pnlThongTinTG.add(txtTienCoc);

		JLabel lblCCCD = new JLabel("Ngày Phải Trả");
		lblCCCD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCCCD.setBounds(308, 158, 148, 19);
		pnlThongTinTG.add(lblCCCD);

		txtNgayTra = new JTextField();
		txtNgayTra.setColumns(10);
		txtNgayTra.setBounds(305, 187, 200, 19);
		pnlThongTinTG.add(txtNgayTra);

		cbxThanhVien = new JComboBox();
		cbxThanhVien.setBounds(10, 45, 227, 26);
		pnlThongTinTG.add(cbxThanhVien);
		cbxThanhVien.setModel(modelThanhVien);

		txtIDNhanVien = new JTextField();
		txtIDNhanVien.setEditable(false);
		txtIDNhanVien.setBounds(305, 110, 200, 19);
		pnlThongTinTG.add(txtIDNhanVien);
		txtIDNhanVien.setColumns(10);

		JLabel lblMPhiu = new JLabel("Mã Phiếu");
		lblMPhiu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMPhiu.setBounds(307, 16, 104, 19);
		pnlThongTinTG.add(lblMPhiu);

		txtMaPhieu = new JTextField();
		txtMaPhieu.setColumns(10);
		txtMaPhieu.setBounds(305, 45, 200, 19);
		pnlThongTinTG.add(txtMaPhieu);

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
		txtTimKiem.setText("Nhập vào tên hoặc mã phiếu mượn");
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
		String[] columns = { "ID", "Mã Phiếu", "Tên Thành Viên", "Tên Nhân Viên", "Ngày Mượn", "Ngày Phải Trả",
				"Tiền Cọc" };
		Object[][] rows = {

		};
		model = new DefaultTableModel(rows, columns);
		table.setModel(model);

		JPanel pnlButton2 = new JPanel();
		pnlButton2.setBounds(105, 487, 350, 30);
		add(pnlButton2);
		pnlButton2.setLayout(new GridLayout(1, 4, 10, 0));

		btnFirst = new JButton("First");
		btnFirst.setEnabled(false);
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
		pnlButton1.setBounds(105, 425, 350, 30);
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

				if (indexTrang > (Math.ceil(pmd.selectAll().size() * 1.0 / 5))) {
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
		lblIndexTrang.setBounds(958, 491, 39, 13);
		add(lblIndexTrang);

		btnXemChiTiet.setEnabled(false);
		btnXemChiTiet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PhieuMuon pm = new PhieuMuon();
				// Lấy dữ liệu từ PhieuMuonJPanel và truyền vào PhieuMuonChiTietJPanel
				int selectedRowIndex = table.getSelectedRow();
				if (selectedRowIndex >= 0) {
					int id = (int) table.getValueAt(selectedRowIndex, 0);
					pm = pmd.selectById(id);
					if (pm != null) {
						// Tạo một instance mới của PhieuMuonChiTietJPanel
						PhieuMuonChiTietJPanel chiTietJPanel = new PhieuMuonChiTietJPanel(pm);
						Container container = PhieuMuonJPanel.this.getParent();
						container.remove(PhieuMuonJPanel.this);
						container.add(chiTietJPanel);
						container.revalidate();
						container.repaint();
					}
				}

			}
		});
		btnXemChiTiet.setBounds(597, 487, 145, 21);
		add(btnXemChiTiet);
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				load(indexTrang);
//				fillNhanVien();
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
		SwingWorker<List<PhieuMuon>, Void> worker = new SwingWorker<List<PhieuMuon>, Void>() {

			@Override
			protected List<PhieuMuon> doInBackground() throws Exception {
				return pmd.loadTrang((soTrang - 1) * 5, 5);
			}

			@Override
			protected void done() {
				try {
					List<PhieuMuon> list = get();
					model.setRowCount(0);
					for (PhieuMuon pm : list) {
						Object[] row = { pm.getId(), pm.getMaPhieuMuon(), pm.getIdThanhVien().getTenTV(),
								pm.getIdNhanVien().getTenNV(), pm.getNgayMuon(), pm.getNgayPhaiTra(), pm.getTienCoc() };
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
			PhieuMuon pm = getForm();
			if (pm != null) {
				pmd.insert(pm);
				load(indexTrang);
				clear();
				DialogHelper.alert(this, "Insert Successful");
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Insert Failed");
			e.printStackTrace();
		}
	}

	void delete() {
		try {
			if (DialogHelper.confirm(this, "Bạn có chắc chắn muốn xóa không ?")) {
				int id = (int) table.getValueAt(this.index, 0);
				pmd.delete(id);
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
				PhieuMuon pm = getForm();
				pmd.update(pm);
				load(indexTrang);
				clear();
				DialogHelper.alert(this, "Update Successful");
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Update Failed");
		}

	}

	void clear() {
		txtIDNhanVien.setText("");
		txtMaPhieu.setText("");
		txtNgayMuon.setText("");
		txtNgayTra.setText("");
		txtTienCoc.setText("");
		cbxThanhVien.setSelectedIndex(0);
		txtNgayMuon.setText(dateFormat.format(ngayHienTai));
		setStatus(true);
	}

	void setForm(PhieuMuon pm) {
		txtIDNhanVien.setText(pm.getIdNhanVien().toString());
		txtMaPhieu.setText(pm.getMaPhieuMuon());
		txtNgayMuon.setText(pm.getNgayMuon() + "");
		txtNgayTra.setText(pm.getNgayPhaiTra() + "");
		txtTienCoc.setText(pm.getTienCoc() + "");
		cbxThanhVien.setSelectedItem(pm.getIdThanhVien());
	}

	PhieuMuon getForm() {
		PhieuMuon pm = new PhieuMuon();
		ThanhVien tv = (ThanhVien) cbxThanhVien.getSelectedItem();
		if (tv != null) {
			pm.setIdThanhVien(tv);
		} else {
			// Xử lý trường hợp cbxThanhVien.getSelectedItem() là null
			DialogHelper.alert(null, "Chọn một thành viên!");
			return null;
		}
		NhanVien nv = nvd.selectById2(ShareHelper.idNhanVien());
		pm.setIdNhanVien(nv);
		if (txtMaPhieu.getText().isEmpty()) {
			DialogHelper.alert(null, "Không để trống mã phiếu");
			return null;
		} else {
			pm.setMaPhieuMuon(txtMaPhieu.getText());
		}

		pm.setNgayMuon(XDate.toDate(txtNgayMuon.getText(), "yyyy-MM-dd"));
		pm.setNgayPhaiTra(XDate.toDate(txtNgayTra.getText(), "yyyy-MM-dd"));
		if (txtTienCoc.getText().isEmpty()) {
			DialogHelper.alert(null, "Không để trống tiền cọc");
			return null;
		} else {
			try {
				float tienCoc = Float.valueOf(txtTienCoc.getText());
				pm.setTienCoc(tienCoc);
			} catch (Exception e) {
				DialogHelper.alert(null, "Chỉ nhập số");
				e.printStackTrace();
			}
		}
		return pm;
	}

	void edit() {
		try {
			int id = (int) table.getValueAt(this.index, 0);
			PhieuMuon pm = pmd.selectById(id);
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
		btnDelete.setEnabled(!insertable);
		boolean first = this.index > 0;
		boolean last = this.index < model.getRowCount() - 1;
		btnFirst.setEnabled(!insertable && first);
		btnPrevEdit.setEnabled(!insertable && first);
		btnNextEdit.setEnabled(!insertable && last);
		btnLast.setEnabled(!insertable && last);
		btnXemChiTiet.setEnabled(!insertable);
	}

	void search() {
	}
}
