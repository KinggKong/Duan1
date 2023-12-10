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
import com.thuvien.entity.Sach;
import com.thuvien.entity.TacGia;
import com.thuvien.entity.TacGiaChiTiet;
import com.thuvien.utils.DialogHelper;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class TacGiaChiTietJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	DefaultTableModel model;
	private int indexTrang = 1;
	private int index = 0;

	DefaultComboBoxModel<TacGia> cbxModelTacGia = new DefaultComboBoxModel<>();
	DefaultComboBoxModel<Sach> cbxModelSach = new DefaultComboBoxModel<>();

	TacGiaChiTietDao tacGiaChiTietDao = new TacGiaChiTietDao();
	SachDao sachDao = new SachDao();
	TacGiaDao tacGiaDao = new TacGiaDao();
	private JComboBox cbxSach;
	private JComboBox cbxTacGia;
	private JComboBox cbxVaiTro;
	private JTextField textField;
	private JLabel lblSoTrang;

	public TacGiaChiTietJPanel() {
		setLayout(null);

		JLabel lblTcGiChi = new JLabel("Tác Giả Chi Tiết");
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
		String[] column = { "ID", "Tên Sách", "Tên Tác Giả", "Vai Trò" };
		Object[][] rows = {

		};
		model = new DefaultTableModel(rows, column);
		table.setModel(model);

		JPanel panel = new JPanel();
		panel.setBounds(127, 109, 418, 342);
		add(panel);
		panel.setLayout(null);

		cbxSach = new JComboBox();
		cbxSach.setBounds(10, 66, 325, 29);
		panel.add(cbxSach);
		cbxSach.setModel(cbxModelSach);

		cbxTacGia = new JComboBox();
		cbxTacGia.setBounds(10, 146, 325, 29);
		panel.add(cbxTacGia);
		cbxTacGia.setModel(cbxModelTacGia);

		JLabel lblNewLabel = new JLabel("Sách");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 33, 146, 23);
		panel.add(lblNewLabel);

		JLabel lblTcGi = new JLabel("Tác Giả");
		lblTcGi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTcGi.setBounds(10, 113, 146, 23);
		panel.add(lblTcGi);

		JLabel lblTcGi_1 = new JLabel("Vai Trò");
		lblTcGi_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTcGi_1.setBounds(10, 202, 146, 23);
		panel.add(lblTcGi_1);

		cbxVaiTro = new JComboBox();
		cbxVaiTro.setModel(new DefaultComboBoxModel(new String[] { "Tác giả chính", "Tác giả phụ" }));
		cbxVaiTro.setBounds(10, 235, 325, 29);
		panel.add(cbxVaiTro);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setEnabled(false);
		textField.setBounds(10, 309, 325, 23);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblTcGi_1_1 = new JLabel("ID");
		lblTcGi_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTcGi_1_1.setBounds(10, 280, 146, 23);
		panel.add(lblTcGi_1_1);

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

				if (indexTrang > (Math.ceil(tacGiaChiTietDao.selectAll().size() * 1.0 / 20))) {
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
				fillCbxTacGia();
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
		TacGiaChiTiet tacGiaChiTiet = tacGiaChiTietDao.selectById(id);
		if (tacGiaChiTiet != null) {
			setForm(tacGiaChiTiet);
		}
	}

	void insert() {
		TacGiaChiTiet tacGiaChiTiet = getForm();
		if (tacGiaChiTiet != null) {
			tacGiaChiTietDao.insert(tacGiaChiTiet);
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
				TacGiaChiTiet tacGiaChiTiet = tacGiaChiTietDao.selectById(id);
				if (tacGiaChiTiet != null) {
					Sach sach = (Sach) cbxSach.getSelectedItem();
					tacGiaChiTiet.setIdSach(sach.getId());
					TacGia tacGia = (TacGia) cbxTacGia.getSelectedItem();
					tacGiaChiTiet.setIdTacGia(tacGia.getId());
					String vaiTro = cbxVaiTro.getSelectedItem().toString();
					tacGiaChiTiet.setVaiTro(vaiTro);
					tacGiaChiTietDao.update(tacGiaChiTiet);
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
				tacGiaChiTietDao.delete(id);
				loadDataToTable(indexTrang);
				clear();
				DialogHelper.alert(this, "Delete thành công ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void setForm(TacGiaChiTiet tacGiaChiTiet) {
		cbxSach.setSelectedItem(sachDao.selectByIdSach(tacGiaChiTiet.getIdSach()));
		cbxTacGia.setSelectedItem(tacGiaDao.selectById2(tacGiaChiTiet.getIdTacGia()));
		if (tacGiaChiTiet.getVaiTro().equals("Tác giả chính")) {
			cbxVaiTro.setSelectedIndex(0);
		} else {
			cbxVaiTro.setSelectedIndex(1);
		}
	}

	void loadDataToTable(int indexTrang) {
		SwingWorker<List<TacGiaChiTiet>, Void> worker = new SwingWorker<List<TacGiaChiTiet>, Void>() {

			@Override
			protected List<TacGiaChiTiet> doInBackground() throws Exception {
				return tacGiaChiTietDao.loadTrang((indexTrang - 1) * 20, 20);
			}

			@Override
			protected void done() {
				try {
					List<TacGiaChiTiet> listTacGiaChiTiet = get();
					model.setRowCount(0);
					for (TacGiaChiTiet tacGiaChiTiet : listTacGiaChiTiet) {
						Object[] row = { tacGiaChiTiet.getId(),
								sachDao.selectByIdSach(tacGiaChiTiet.getIdSach()).getTenSach(),
								tacGiaDao.selectById2(tacGiaChiTiet.getIdTacGia()).getHoTen(),
								tacGiaChiTiet.getVaiTro() };
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

	void fillCbxTacGia() {
		SwingWorker<List<TacGia>, Void> worker = new SwingWorker<List<TacGia>, Void>() {

			@Override
			protected List<TacGia> doInBackground() throws Exception {
				return tacGiaDao.selectAllID();
			}

			@Override
			protected void done() {
				try {
					List<TacGia> listTacGia = get();
					cbxModelTacGia.removeAllElements();
					for (TacGia tacGia : listTacGia) {
						cbxModelTacGia.addElement(tacGia);
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

	TacGiaChiTiet getForm() {
		TacGiaChiTiet tacGiaChiTiet = new TacGiaChiTiet();
		Sach sach = (Sach) cbxSach.getSelectedItem();
		tacGiaChiTiet.setIdSach(sach.getId());
		TacGia tacGia = (TacGia) cbxTacGia.getSelectedItem();
		tacGiaChiTiet.setIdTacGia(tacGia.getId());
		String vaiTro = (String) cbxVaiTro.getSelectedItem();
		tacGiaChiTiet.setVaiTro(vaiTro);
		return tacGiaChiTiet;
	}

	void clear() {
		cbxSach.setSelectedIndex(0);
		cbxTacGia.setSelectedIndex(0);
		cbxVaiTro.setSelectedIndex(0);
		table.clearSelection();
	}
}
