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

import com.thuvien.dao.TacGiaDao;
import com.thuvien.dao.ViTriDao;
import com.thuvien.entity.NXB;
import com.thuvien.entity.TacGia;
import com.thuvien.entity.ViTri;
import com.thuvien.utils.DialogHelper;
import java.util.HashSet;

public class ViTriJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtDay;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table;
	DefaultTableModel model;
	private JButton btnInsert;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnClear;
	private JButton btnPrevList;
	private JButton btnNextList;
	ViTriDao vtdao = new ViTriDao();
	int indexTrang = 1;
	int index = 0;
	private JButton btnFirst;
	private JButton btnPrevEdit;
	private JButton btnNextEdit;
	private JButton btnLast;
	private JLabel lblIndexTrang;

	public ViTriJPanel() {
		setLayout(null);
		JLabel lblTitle = new JLabel("Quản Lý Vị Trí");
		lblTitle.setBounds(535, 10, 327, 37);
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		add(lblTitle);

		JPanel pnlThongTinTG = new JPanel();
		pnlThongTinTG.setBounds(57, 174, 429, 105);
		pnlThongTinTG.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(pnlThongTinTG);
		pnlThongTinTG.setLayout(null);

		JLabel lblDay = new JLabel("Vị Trí Dãy");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDay.setBounds(10, 16, 104, 19);
		pnlThongTinTG.add(lblDay);

		txtDay = new JTextField();
		txtDay.setText("VD: 1");
		txtDay.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtDay.getText().equals("VD: 1")) {
					txtDay.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtDay.getText().isEmpty()) {
					txtDay.setText("VD: 1");
				}
			}
		});
		txtDay.setColumns(10);
		txtDay.setBounds(10, 45, 296, 19);
		pnlThongTinTG.add(txtDay);

		JPanel pnlDanhSach = new JPanel();
		pnlDanhSach.setBounds(605, 94, 662, 345);
		pnlDanhSach.setLayout(null);
		pnlDanhSach.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh S\u00E1ch", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		add(pnlDanhSach);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 28, 630, 307);
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
		String[] columns = { "ID", "Vị Trí Dãy" };
		Object[][] rows = {};
		model = new DefaultTableModel(rows, columns);
		table.setModel(model);

		JPanel pnlButton2 = new JPanel();
		pnlButton2.setBounds(100, 381, 350, 30);
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
		pnlButton1.setBounds(100, 321, 350, 30);
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
		btnPrevList.setBounds(777, 487, 85, 21);
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
		add(btnPrevList);

		btnNextList = new JButton("Next");
		btnNextList.setBounds(1049, 487, 85, 21);
		btnNextList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indexTrang++;

				if (indexTrang > (Math.ceil(vtdao.selectAll().size() * 1.0 / 5))) {
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
		lblIndexTrang.setBounds(950, 491, 56, 13);
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

//	void load(int soTrang) {
//		SwingUtilities.invokeLater(() -> {
//			List<TacGia> list = tgd.loadTrang((soTrang - 1) * 5, 5);
//			model.setRowCount(0);
//			for (TacGia tg : list) {
//				Object[] row = { tg.getMaTG(), tg.getHoTen(), tg.getQuocTich(), tg.isGioiTinh() ? "Nam" : "Nữ" };
//				model.addRow(row);
//			}
//		});
//	}
	void load(int soTrang) {
		SwingWorker<List<ViTri>, Void> worker = new SwingWorker<List<ViTri>, Void>() {
			@Override
			protected List<ViTri> doInBackground() throws Exception {
				return vtdao.loadTrang((soTrang - 1) * 5, 5);
			}

			@Override
			protected void done() {
				try {
					List<ViTri> list = get();
					model.setRowCount(0);
					for (ViTri nxb : list) {
						Object[] row = { nxb.getID(), nxb.getDay() };
						model.addRow(row);
					}
				} catch (Exception e) {
					DialogHelper.alert(ViTriJPanel.this, "Lỗi truy vấn dữ liệu!");
				}
			}
		};

		worker.execute();

	}

	void insert() {
		try {
			ViTri nxb = getForminsert();
			if (nxb != null) {
				vtdao.insert(nxb);
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
				ViTri vtd = getForm();
				vtdao.delete(vtd.getID());
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
				ViTri vtd = getForm();
				vtdao.update(vtd);
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
		txtDay.setText("");
		txtDay.setEditable(true);
		btnInsert.setEnabled(true);

	}

	void setForm(ViTri vtd) {
		txtDay.setText(vtd.getDay() + "");

	}

	ViTri getForm() {
		ViTri nxb = new ViTri();
		int ID = (int) table.getValueAt(this.index, 0);
		nxb.setID(ID);
		nxb.setDay(txtDay.getText());
		if (txtDay.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống dãy");
			return null;
		} else {
			nxb.setDay(txtDay.getText());
		}

		return nxb;

	}

	ViTri getForminsert() {

		ViTri nxb = new ViTri();
		nxb.setDay(txtDay.getText());
		if (txtDay.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống họ tên tác giả");
			return null;
		} else {
			nxb.setDay(txtDay.getText());
		}

		return nxb;
	}

//        catch (NumberFormatException e) {
//            // Xử lý nếu không thể chuyển đổi thành số nguyên
//            DialogHelper.alert(this, "Ngày số không hợp lệ. Vui lòng nhập số nguyên.");
//            return null;
	void edit() {
		try {
			//Kiểm tra giá trị là kiểu dữ liệu gì 
//			Object value = table.getValueAt(this.index, 0);
//			System.out.println(value.getClass());
			int maNxb = (int) table.getValueAt(this.index, 0);
			ViTri tg = vtdao.selectById(maNxb);
			if (tg != null) {
				setForm(tg);
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

	private ViTri getInsert() {
		ViTri nxb = null;
		if (txtDay.getText().isEmpty()) {
			DialogHelper.alert(this, "Không để trống họ tên tác giả");
			return null;
		} else {
			nxb.setDay(txtDay.getText());
		}

		return nxb;
	}
}
