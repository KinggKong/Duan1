package com.thuvien.ui;

import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.thuvien.dao.SachDao;
import com.thuvien.dao.TacGiaChiTietDao;
import com.thuvien.dao.TacGiaDao;
import com.thuvien.dao.TheLoaiChiTietDao;
import com.thuvien.dao.TheLoaiDao;
import com.thuvien.entity.Sach;
import com.thuvien.entity.TacGia;
import com.thuvien.entity.TacGiaChiTiet;
import com.thuvien.entity.TheLoai;
import com.thuvien.entity.TheLoaiCT;
import com.thuvien.utils.DialogHelper;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class TheLoaiChiTietJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	DefaultTableModel model;
	private int indexTrang = 1;
	private int index = 0;
	private JComboBox cbxSach;
	private JComboBox cbxTTheLoai;
	private JLabel lblSoTrang;

	DefaultComboBoxModel<TheLoai> cbxModelThrLoai = new DefaultComboBoxModel<>();
	DefaultComboBoxModel<Sach> cbxModelSach = new DefaultComboBoxModel<>();

	SachDao sachDao = new SachDao();
	TheLoaiDao theLoaiDao = new TheLoaiDao();
	TheLoaiChiTietDao theLoaiChiTietDao = new TheLoaiChiTietDao();

	public TheLoaiChiTietJPanel() {
		setLayout(null);

		JLabel lblTcGiChi = new JLabel("Thể Loại Chi Tiết");
		lblTcGiChi.setBounds(540, 5, 290, 37);
		lblTcGiChi.setForeground(Color.BLUE);
		lblTcGiChi.setFont(new Font("Tahoma", Font.BOLD, 30));
		add(lblTcGiChi);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(646, 70, 648, 423);
		add(scrollPane);

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
		String[] column = { "ID", "Tên Sách", "Tên Thể Loại" };
		Object[][] rows = {

		};
		model = new DefaultTableModel(rows, column);
		table.setModel(model);

		JPanel panel = new JPanel();
		panel.setBounds(127, 163, 418, 226);
		add(panel);
		panel.setLayout(null);

		cbxSach = new JComboBox();
		cbxSach.setBounds(10, 66, 325, 29);
		panel.add(cbxSach);
		cbxSach.setModel(cbxModelSach);

		cbxTTheLoai = new JComboBox();
		cbxTTheLoai.setBounds(10, 146, 325, 29);
		panel.add(cbxTTheLoai);
		cbxTTheLoai.setModel(cbxModelThrLoai);

		JLabel lblNewLabel = new JLabel("Sách");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 33, 146, 23);
		panel.add(lblNewLabel);

		JLabel lblTcGi = new JLabel("Thể Loại");
		lblTcGi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTcGi.setBounds(10, 113, 146, 23);
		panel.add(lblTcGi);

		JButton btnNewButton = new JButton("Insert");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});
		btnNewButton.setBounds(99, 490, 85, 21);
		add(btnNewButton);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnUpdate.setBounds(227, 490, 85, 21);
		add(btnUpdate);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDelete.setBounds(362, 490, 85, 21);
		add(btnDelete);

		JButton btnDelete_1 = new JButton("Clear");
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnDelete_1.setBounds(487, 490, 85, 21);
		add(btnDelete_1);

		JButton btnNewButton_1 = new JButton("Prev");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indexTrang--;
				if (indexTrang == 0) {
					DialogHelper.alert(null, "Đây là trang đầu tiên !");
					indexTrang++;
				} else {
					loadDataToTable(indexTrang);
					lblSoTrang.setText(indexTrang + "");
				}
			}
		});
		btnNewButton_1.setBounds(819, 519, 85, 21);
		add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Next");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indexTrang++;

				if (indexTrang > (Math.ceil(theLoaiChiTietDao.selectAll().size() * 1.0 / 20))) {
					DialogHelper.alert(null, "Đây là trang cuối cùng !");
					indexTrang--;
				} else {

					loadDataToTable(indexTrang);
					lblSoTrang.setText(indexTrang + "");
				}
			}
		});
		btnNewButton_2.setBounds(1068, 519, 85, 21);
		add(btnNewButton_2);

		lblSoTrang = new JLabel("");
		lblSoTrang.setBounds(969, 527, 45, 13);
		lblSoTrang.setText(indexTrang + "");
		add(lblSoTrang);
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				loadDataToTable(indexTrang);
				fillCbxSach();
				fillCbxTheLoai();
				return null;
			}

			@Override
			protected void done() {
				// TODO Auto-generated method stub
				super.done();
			}
		};
		worker.execute();
	}

	protected void edit() {
		int id = (int) table.getValueAt(this.index, 0);
		TheLoaiCT tacGiaChiTiet = theLoaiChiTietDao.selectById(id);
		if (tacGiaChiTiet != null) {
			setForm(tacGiaChiTiet);
		}
	}

	void insert() {
		TheLoaiCT tacGiaChiTiet = getForm();
		if (tacGiaChiTiet != null) {
			theLoaiChiTietDao.insert(tacGiaChiTiet);
			loadDataToTable(indexTrang);
			clear();
			DialogHelper.alert(this, "Insert successful");
		}
	}

	void update() {
		int choose = JOptionPane.showConfirmDialog(this, "Bạn có muốn update không !");
		if (choose == JOptionPane.YES_OPTION) {
			try {
				int id = (int) table.getValueAt(this.index, 0);
				TheLoaiCT tacGiaChiTiet = theLoaiChiTietDao.selectById(id);
				if (tacGiaChiTiet != null) {
					Sach sach = (Sach) cbxSach.getSelectedItem();
					tacGiaChiTiet.setIdSach(sach.getId());
					TheLoai theLoai = (TheLoai) cbxTTheLoai.getSelectedItem();
					tacGiaChiTiet.setIdTheLoai(theLoai.getId());
					theLoaiChiTietDao.update(tacGiaChiTiet);
					loadDataToTable(indexTrang);
					clear();
					DialogHelper.alert(this, "Update thành công ");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	void delete() {
		try {
			int choose = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không !");
			if (choose == JOptionPane.YES_OPTION) {
				int id = (int) table.getValueAt(this.index, 0);
				theLoaiChiTietDao.delete(id);
				loadDataToTable(indexTrang);
				clear();
				DialogHelper.alert(this, "Delete thành công ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void setForm(TheLoaiCT tacGiaChiTiet) {
		cbxSach.setSelectedItem(sachDao.selectByIdSach(tacGiaChiTiet.getIdSach()));
		cbxTTheLoai.setSelectedItem(theLoaiDao.selectById2(tacGiaChiTiet.getIdTheLoai()));
	}

	void loadDataToTable(int indexTrang) {
		SwingWorker<List<TheLoaiCT>, Void> worker = new SwingWorker<List<TheLoaiCT>, Void>() {

			@Override
			protected List<TheLoaiCT> doInBackground() throws Exception {
				return theLoaiChiTietDao.selectAll();
			}

			@Override
			protected void done() {
				try {
					List<TheLoaiCT> listTheLoaiChiTiet = get();
					model.setRowCount(0);
					for (TheLoaiCT theLoaiCT : listTheLoaiChiTiet) {
						Object[] row = { theLoaiCT.getId(), sachDao.selectByIdSach(theLoaiCT.getIdSach()).getTenSach(),
								theLoaiDao.selectById2(theLoaiCT.getIdTheLoai()).getTenTL() };
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

	void fillCbxSach() {
		SwingWorker<List<Sach>, Void> worker = new SwingWorker<List<Sach>, Void>() {

			@Override
			protected List<Sach> doInBackground() throws Exception {
				return sachDao.selectAll();
			}

			@Override
			protected void done() {
				try {
					List<Sach> listSach = get();
					cbxModelSach.removeAllElements();
					for (Sach sach : listSach) {
						cbxModelSach.addElement(sach);
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

	void fillCbxTheLoai() {
		SwingWorker<List<TheLoai>, Void> worker = new SwingWorker<List<TheLoai>, Void>() {

			@Override
			protected List<TheLoai> doInBackground() throws Exception {
				return theLoaiDao.selectAllID();
			}

			@Override
			protected void done() {
				try {
					List<TheLoai> listSach = get();
					cbxModelSach.removeAllElements();
					for (TheLoai sach : listSach) {
						cbxModelThrLoai.addElement(sach);
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

	TheLoaiCT getForm() {
		TheLoaiCT tacGiaChiTiet = new TheLoaiCT();
		Sach sach = (Sach) cbxSach.getSelectedItem();
		tacGiaChiTiet.setIdSach(sach.getId());
		TheLoai theLoai = (TheLoai) cbxTTheLoai.getSelectedItem();
		tacGiaChiTiet.setIdTheLoai(theLoai.getId());
		return tacGiaChiTiet;
	}

	void clear() {
		cbxSach.setSelectedIndex(0);
		cbxTTheLoai.setSelectedIndex(0);
		table.clearSelection();
	}
}
