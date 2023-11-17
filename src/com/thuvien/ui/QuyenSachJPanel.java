package com.thuvien.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
import com.thuvien.dao.QuyenSachRepoDao;
import com.thuvien.entity.QuyenSach;
import com.thuvien.entity.TacGia;
import com.thuvien.repo.QuyenSachRepo;
import com.thuvien.utils.DialogHelper;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuyenSachJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtTenSach_1;
	private JTextField txtMaQS;
	private JTextField txtIDTaiBan;
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
	QuyenSachRepoDao sachRepoDao = new QuyenSachRepoDao();
	QuyenSachDao quyenSachDao = new QuyenSachDao();
	
	private JLabel lblIndexTrang;
	private JButton btnTimKiem;

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

		txtTenSach_1 = new JTextField();
		txtTenSach_1.setEnabled(false);
		txtTenSach_1.setEditable(false);
		txtTenSach_1.setBounds(10, 34, 225, 19);
		panel.add(txtTenSach_1);
		txtTenSach_1.setColumns(10);

		JLabel lblTenSach = new JLabel("Tên Sách");
		lblTenSach.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenSach.setBounds(10, 10, 104, 19);
		panel.add(lblTenSach);

		txtMaQS = new JTextField();
		txtMaQS.setColumns(10);
		txtMaQS.setBounds(10, 87, 225, 19);
		panel.add(txtMaQS);

		JLabel lblNmXutBn = new JLabel("ID Tái Bản");
		lblNmXutBn.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNmXutBn.setBounds(283, 10, 144, 19);
		panel.add(lblNmXutBn);

		txtIDTaiBan = new JTextField();
		txtIDTaiBan.setBounds(283, 34, 183, 19);
		panel.add(txtIDTaiBan);
		txtIDTaiBan.setColumns(10);

		cbxTinhTrang = new JComboBox();
		cbxTinhTrang.setModel(new DefaultComboBoxModel(new String[] { "Mới", "Hỏng Nhẹ", "Không Dùng Được" }));
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

		JLabel lblNhaXuatBan = new JLabel("Dãy Số");
		lblNhaXuatBan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNhaXuatBan.setBounds(283, 63, 144, 19);
		panel.add(lblNhaXuatBan);

		cbxViTri = new JComboBox();
		cbxViTri.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		cbxViTri.setBounds(283, 87, 183, 21);
		panel.add(cbxViTri);

		JPanel pnlButton1 = new JPanel();
		pnlButton1.setBounds(97, 436, 350, 30);
		add(pnlButton1);
		pnlButton1.setLayout(new GridLayout(1, 4, 10, 0));

		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnInsert.setEnabled(true);
		pnlButton1.add(btnInsert);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDelete.setEnabled(false);
		pnlButton1.add(btnDelete);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdate.setEnabled(false);
		pnlButton1.add(btnUpdate);

		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		txtTimKiem.setBounds(85, 17, 266, 19);
		pnlDanhSach.add(txtTimKiem);

		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setBounds(361, 16, 97, 21);
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
		String[] columns = { "Mã Sách", "Tên Sách", "Dãy Số", "Lần TB", "Ngày TB", "NXB", "Tên NXB", "Ghi Chú",
				"Trạng Thái" };
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
				if (indexTrang > (Math.ceil(sachRepoDao.selectAll().size() * 1.0 / 5))) {
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
		SwingWorker<List<QuyenSachRepo>, Void> worker = new SwingWorker<List<QuyenSachRepo>, Void>() {
			@Override
			protected List<QuyenSachRepo> doInBackground() throws Exception {
				return sachRepoDao.loadTrang((soTrang - 1) * 5, 5);
			}

			@Override
			protected void done() {
				try {
					List<QuyenSachRepo> list = get();
					model.setRowCount(0);
					for (QuyenSachRepo qs : list) {
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
						Object[] row = { qs.getMaQS(), qs.getTenSach(), qs.getDaySo(), qs.getLanTB(), qs.getNgayTB(),
								qs.getNamXB(), qs.getTenNXB(), qs.getGhiChu(), trangThai };
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

	void insert() {

	}

	void delete() {

	}

	void update() {

	}

	void clear() {

	}

	void setForm(QuyenSach qs) {
		txtMaQS.setText(qs.getMaQS());
		txtGhiChu.setText(qs.getGhiChu());
		if (qs.getTinhTrang() == 0) {
			cbxTinhTrang.setSelectedIndex(2);
		} else if (qs.getTinhTrang() == 1) {
			cbxTinhTrang.setSelectedIndex(1);
		} else {
			cbxTinhTrang.setSelectedIndex(0);
		}
		cbxViTri.setSelectedItem(qs.getIdViTri());
		txtIDTaiBan.setText(qs.getIdTaiBan() + "");
	}

	QuyenSach getForm() {

		return null;
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

//	void search() {
//		String keyword = txtTimKiem.getText().trim();
//		// Thực hiện tìm kiếm trong một SwingWorker
//		SwingWorker<List<TacGia>, Void> worker = new SwingWorker<List<TacGia>, Void>() {
//			@Override
//			protected List<TacGia> doInBackground() throws Exception {
//				return tgd.selectByKeyword(keyword);
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
//					DialogHelper.alert(QuyenSachJPanel.this, "Lỗi truy vấn dữ liệu!");
//				}
//			}
//		};
//
//		worker.execute();
//	}
}
