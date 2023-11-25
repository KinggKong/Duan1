package com.thuvien.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.thuvien.dao.QuyenSachDao;

import com.thuvien.dao.SachDao;
import com.thuvien.dao.TaiBanDao;
import com.thuvien.dao.ViTriDao;
import com.thuvien.entity.QuyenSach;
import com.thuvien.entity.Sach;
import com.thuvien.entity.TacGia;
import com.thuvien.entity.TaiBan;
import com.thuvien.entity.ViTri;
import com.thuvien.utils.DialogHelper;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class QuyenSachJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaQS;
	private JTextField txtTimKiem;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	DefaultTableModel model;
	private JComboBox cbxTinhTrang;
	private JTextArea txtGhiChu;
	private JComboBox cbxViTri;
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
	SachDao sd = new SachDao();
	QuyenSachDao quyenSachDao = new QuyenSachDao();
	TaiBanDao tbd = new TaiBanDao();
	ViTriDao vtd = new ViTriDao();

	private JLabel lblIndexTrang;
	private JButton btnTimKiem;
	private JComboBox cbxSach;
	private JComboBox cbxTaiBan;

	DefaultComboBoxModel<TaiBan> modelTaiBan = new DefaultComboBoxModel<>();
	DefaultComboBoxModel<Sach> modelSach = new DefaultComboBoxModel<>();
	DefaultComboBoxModel<ViTri> modelViTri = new DefaultComboBoxModel<>();

	public QuyenSachJPanel() {
		setLayout(null);

		JLabel lblTitle = new JLabel("Quản Lý Quyển Sách");
		lblTitle.setBounds(494, 10, 350, 37);
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		add(lblTitle);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(23, 108, 495, 318);
		add(panel);
		panel.setLayout(null);

		JLabel lblMSch = new JLabel("Mã QS");
		lblMSch.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMSch.setBounds(10, 58, 104, 19);
		panel.add(lblMSch);

		JLabel lblTenSach = new JLabel("Sách");
		lblTenSach.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenSach.setBounds(10, 10, 104, 19);
		panel.add(lblTenSach);

		txtMaQS = new JTextField();
		txtMaQS.setColumns(10);
		txtMaQS.setBounds(10, 87, 225, 19);
		panel.add(txtMaQS);

		JLabel lblNmXutBn = new JLabel("Lần Tái Bản\r\n");
		lblNmXutBn.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNmXutBn.setBounds(283, 10, 144, 19);
		panel.add(lblNmXutBn);

		cbxTinhTrang = new JComboBox();
		cbxTinhTrang.setModel(new DefaultComboBoxModel(new String[] { "Hỏng", "Mới", "Hỏng Nhẹ" }));
		cbxTinhTrang.setBounds(10, 156, 225, 24);
		panel.add(cbxTinhTrang);

		JLabel lblTinhTrang = new JLabel("Tình Trạng");
		lblTinhTrang.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTinhTrang.setBounds(10, 126, 128, 19);
		panel.add(lblTinhTrang);

		JLabel lblGhiChu = new JLabel("Ghi Chú");
		lblGhiChu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGhiChu.setBounds(10, 190, 128, 19);
		panel.add(lblGhiChu);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(10, 219, 475, 89);
		panel.add(scrollPane_1);

		txtGhiChu = new JTextArea();
		scrollPane_1.setViewportView(txtGhiChu);

		JLabel lblNhaXuatBan = new JLabel("Dãy ");
		lblNhaXuatBan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNhaXuatBan.setBounds(283, 63, 144, 19);
		panel.add(lblNhaXuatBan);

		cbxViTri = new JComboBox();
		cbxViTri.setModel(new DefaultComboBoxModel(new String[] { "A", "B", "C", "D", "E" }));
		cbxViTri.setBounds(283, 87, 183, 21);
		panel.add(cbxViTri);
		cbxViTri.setModel(modelViTri);

		cbxSach = new JComboBox();
		cbxSach.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Sach s = (Sach) cbxSach.getSelectedItem();
				int idSach = s.getId();
				fillTaiBan(idSach);
			}
		});
		cbxSach.setBounds(10, 33, 225, 24);
		panel.add(cbxSach);
		cbxSach.setModel(modelSach);

		cbxTaiBan = new JComboBox();
		cbxTaiBan.setBounds(283, 35, 183, 21);
		panel.add(cbxTaiBan);
		cbxTaiBan.setModel(modelTaiBan);

		JPanel pnlButton1 = new JPanel();
		pnlButton1.setBounds(97, 436, 350, 30);
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
		pnlButton2.setBounds(97, 476, 350, 30);
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
		pnlDanhSach.setBounds(548, 82, 755, 378);
		add(pnlDanhSach);

		JLabel lblTimKiem = new JLabel("Tìm Kiếm");
		lblTimKiem.setBounds(10, 20, 55, 13);
		pnlDanhSach.add(lblTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(85, 17, 441, 19);
		pnlDanhSach.add(txtTimKiem);

		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTimKiem.setBounds(550, 16, 97, 21);
		pnlDanhSach.add(btnTimKiem);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 735, 307);
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
		String[] columns = { "Mã Sách", "Tên Sách", "Dãy", "Lần TB", "Ghi Chú", "Trạng Thái" };
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
		btnPrevList.setBounds(775, 489, 85, 21);
		add(btnPrevList);

		btnNextList = new JButton("Next");
		btnNextList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indexTrang++;
				if (indexTrang > (Math.ceil(quyenSachDao.selectAll().size() * 1.0 / 5))) {
					DialogHelper.alert(null, "Đây là trang cuối cùng !");
					indexTrang--;
				} else {
					load(indexTrang);
					lblIndexTrang.setText(indexTrang + "");
				}
			}
		});
		btnNextList.setBounds(1032, 489, 85, 21);
		add(btnNextList);

		lblIndexTrang = new JLabel("1");
		lblIndexTrang.setBounds(941, 493, 39, 13);
		add(lblIndexTrang);
		SwingWorker<Void, Void> wk = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				load(indexTrang);
				fillSach();
				fillViTri();
				return null;
			}

			@Override
			protected void done() {
				super.done();
			}

		};
		wk.execute();
	}

	void load(int soTrang) {
		// Thực hiện truy vấn cơ sở dữ liệu trong một SwingWorker
		SwingWorker<List<QuyenSach>, Void> worker = new SwingWorker<List<QuyenSach>, Void>() {
			@Override
			protected List<QuyenSach> doInBackground() throws Exception {
				return quyenSachDao.loadTrang((soTrang - 1) * 5, 5);
			}

			@Override
			protected void done() {
				try {
					List<QuyenSach> list = get();
					model.setRowCount(0);
					for (QuyenSach qs : list) {
						String trangThai = null;
						switch (qs.getTinhTrang()) {
						case 0:
							trangThai = "Hỏng";
							break;
						case 1:
							trangThai = "Mới";
							break;
						case 2:
							trangThai = "Hỏng Nhẹ";
							break;
						default:
							trangThai = "Tình Trạng Không Đúng !!!";
						}
						Object[] row = { qs.getMaQS(), qs.getTenQS(), qs.getIdViTri().getDay(),
								qs.getIdTaiBan().getLanTaiBan(), qs.getGhiChu(), trangThai };
						model.addRow(row);
					}
				} catch (Exception e) {
					DialogHelper.alert(QuyenSachJPanel.this, "Lỗi truy vấn dữ liệu!");
					e.printStackTrace();
				}
			}
		};

		worker.execute();
	}

	void fillTaiBan(int idSach) {
		SwingWorker<List<TaiBan>, Void> worker = new SwingWorker<List<TaiBan>, Void>() {

			@Override
			protected List<TaiBan> doInBackground() throws Exception {
				return tbd.selectAllTheoIDSach(idSach);
			}

			@Override
			protected void done() {
				try {
					List<TaiBan> list = get();
					modelTaiBan.removeAllElements();
					for (TaiBan tb : list) {
						modelTaiBan.addElement(tb);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		};
		worker.execute();
	}

	void fillViTri() {
		SwingWorker<List<ViTri>, Void> worker = new SwingWorker<List<ViTri>, Void>() {

			@Override
			protected List<ViTri> doInBackground() throws Exception {
				return vtd.selectAll();
			}

			@Override
			protected void done() {
				try {
					List<ViTri> list = get();
					modelViTri.removeAllElements();
					for (ViTri tb : list) {
						modelViTri.addElement(tb);
					}
				} catch (Exception e) {
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
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		};
		worker.execute();
	}

	void insert() {
		try {
			QuyenSach qs = getForm();
			if (qs != null) {
				quyenSachDao.insert(qs);
				load(indexTrang);
				clear();
				DialogHelper.alert(this, "Insert Successful");
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Insert Fail !");
			e.printStackTrace();
		}
	}

	void delete() {
		try {
			if (DialogHelper.confirm(this, "Bạn có chắc chắn muốn xóa không ?")) {
				String maQS = (String) table.getValueAt(this.index, 0);
				quyenSachDao.delete(maQS);
				load(indexTrang);
				clear();
				DialogHelper.alert(this, "Delete Successful");
			}

		} catch (Exception e) {
			DialogHelper.alert(this, "Delete Fail !");
			e.printStackTrace();
		}
	}

	void update() {
		try {
			if (DialogHelper.confirm(this, "Bạn có chắc chắn muốn xóa không ?")) {
				QuyenSach qs = getForm();
				if (qs != null) {
					quyenSachDao.update(qs);
					load(indexTrang);
					clear();
					DialogHelper.alert(this, "Update Successful");
				}
			}

		} catch (Exception e) {
			DialogHelper.alert(this, "Update Fail !");
			e.printStackTrace();
		}

	}

	void clear() {
		cbxSach.setSelectedIndex(0);
		cbxTaiBan.setSelectedIndex(0);
		txtGhiChu.setText("");
		txtMaQS.setText("");
		cbxViTri.setSelectedIndex(0);
		cbxTinhTrang.setSelectedIndex(0);
		setStatus(true);
	}

	void setForm(QuyenSach qs) {
		txtMaQS.setText(qs.getMaQS());
		cbxSach.setSelectedItem(qs.getIdTaiBan().getIdSach());
		txtGhiChu.setText(qs.getGhiChu());
		cbxTinhTrang.setSelectedIndex(qs.getTinhTrang());
		cbxViTri.setSelectedItem(qs.getIdViTri());
		cbxTaiBan.setSelectedItem(qs.getIdTaiBan());
	}

	QuyenSach getForm() {
		QuyenSach qs = new QuyenSach();

		if (txtMaQS.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống mã quyển sách ");
			return null;
		} else {
			qs.setMaQS(txtMaQS.getText());
		}

		qs.setTenQS(cbxSach.getSelectedItem().toString());
		TaiBan tb = (TaiBan) cbxTaiBan.getSelectedItem();
		if (tb != null) {
			qs.setIdTaiBan(tb);
		} else {
			DialogHelper.alert(this, "Dang loi tai ban");
		}
		ViTri vt = (ViTri) cbxViTri.getSelectedItem();
		qs.setIdViTri(vt);
		qs.setGhiChu(txtGhiChu.getText());
		if (cbxTinhTrang.getSelectedIndex() == 0) {
			qs.setTinhTrang(0);
		} else if (cbxTinhTrang.getSelectedIndex() == 1) {
			qs.setTinhTrang(1);
		} else {
			qs.setTinhTrang(2);
		}

		return qs;
	}

	void edit() {
		try {
			String maQS = (String) table.getValueAt(this.index, 0);
			QuyenSach tg = quyenSachDao.selectById(maQS);
			if (tg != null) {
				setForm(tg);
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
}
