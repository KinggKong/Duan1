package com.thuvien.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import com.thuvien.dao.ViTriDao;
import com.thuvien.entity.Day;
import com.thuvien.entity.Ngan;
import com.thuvien.entity.OSoChiTiet;
import com.thuvien.entity.QuyenSach;
import com.thuvien.entity.ViTri;
import com.thuvien.utils.DialogHelper;

public class ViTriJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table;
	DefaultTableModel model;
	private JButton btnUpdate;
	ViTriDao vtdao = new ViTriDao();
	int indexTrang = 1;
	int index = 0;

	DefaultComboBoxModel<Day> modelCbxDay = new DefaultComboBoxModel<>();
	DefaultComboBoxModel<Ngan> modelNgan = new DefaultComboBoxModel<>();
	DefaultComboBoxModel<QuyenSach> modelCbxQuyenSach = new DefaultComboBoxModel<>();
	private JComboBox cbxDay;
	private JComboBox cbxNgan;
	private JTextField txtO;
	private JComboBox cbxQuyenSach;

	public ViTriJPanel() {
		setLayout(null);
		JLabel lblTitle = new JLabel("Quản Lý Vị Trí");
		lblTitle.setBounds(535, 10, 327, 37);
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		add(lblTitle);

		JPanel pnlThongTinTG = new JPanel();
		pnlThongTinTG.setBounds(57, 78, 429, 315);
		pnlThongTinTG.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(pnlThongTinTG);
		pnlThongTinTG.setLayout(null);

		JLabel lblDay = new JLabel("Dãy");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDay.setBounds(10, 16, 104, 19);
		pnlThongTinTG.add(lblDay);

		cbxDay = new JComboBox();
		cbxDay.setBounds(10, 45, 279, 21);
		pnlThongTinTG.add(cbxDay);
		cbxDay.setModel(modelCbxDay);
		cbxDay.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Day day = (Day) cbxDay.getSelectedItem();
				fillNgan(day.getIdDay());

			}
		});

		JLabel lblNgn = new JLabel("Ngăn ");
		lblNgn.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgn.setBounds(10, 91, 104, 19);
		pnlThongTinTG.add(lblNgn);

		cbxNgan = new JComboBox();
		cbxNgan.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

			}
		});
		cbxNgan.setBounds(10, 120, 279, 21);
		pnlThongTinTG.add(cbxNgan);
		cbxNgan.setModel(modelNgan);

		JLabel lblQuynSch = new JLabel("Quyển Sách");
		lblQuynSch.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblQuynSch.setBounds(10, 237, 164, 19);
		pnlThongTinTG.add(lblQuynSch);

		cbxQuyenSach = new JComboBox();
		cbxQuyenSach.setBounds(10, 266, 279, 21);
		pnlThongTinTG.add(cbxQuyenSach);
		cbxQuyenSach.setModel(modelCbxQuyenSach);

		txtO = new JTextField();
		txtO.setBounds(10, 191, 279, 19);
		pnlThongTinTG.add(txtO);
		txtO.setColumns(10);

		JLabel lblId = new JLabel("ID Ô");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblId.setBounds(10, 162, 104, 19);
		pnlThongTinTG.add(lblId);

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
				String idO = (String) table.getValueAt(index, 0);
				txtO.setText(idO);
			}
		});
		scrollPane.setViewportView(table);
		String[] columns = { "ID Ô", "Tên Ô", "Parent_ID", "Mã Quyển Sách" };
		Object[][] rows = {};
		model = new DefaultTableModel(rows, columns);
		table.setModel(model);

		JButton btnNewButton = new JButton("Xem");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ngan ngan = (Ngan) cbxNgan.getSelectedItem();
				load(ngan.getIdNgan());
			}
		});
		btnNewButton.setBounds(296, 449, 98, 30);
		add(btnNewButton);

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(134, 449, 110, 30);
		add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuyenSach qs = (QuyenSach) cbxQuyenSach.getSelectedItem();
				update(qs.getMaQS());
			}
		});
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				fillDay();
				fillCbxQuyenSach();
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

	void fillDay() {
		SwingWorker<List<Day>, Void> worker = new SwingWorker<List<Day>, Void>() {

			@Override
			protected List<Day> doInBackground() throws Exception {
				List<Day> listDay = vtdao.getAllDay();
				return listDay;
			}

			@Override
			protected void done() {
				try {
					List<Day> listDay = get();
					modelCbxDay.removeAllElements();
					for (Day d : listDay) {
						modelCbxDay.addElement(d);
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

	void fillNgan(String idDay) {
		SwingWorker<List<Ngan>, Void> worker = new SwingWorker<List<Ngan>, Void>() {

			@Override
			protected List<Ngan> doInBackground() throws Exception {
				List<Ngan> listDay = vtdao.getAllNgan(idDay);
				return listDay;
			}

			@Override
			protected void done() {
				try {
					List<Ngan> listDay = get();
					modelNgan.removeAllElements();
					for (Ngan d : listDay) {
						modelNgan.addElement(d);
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

	void load(String IDngan) {
		SwingWorker<List<OSoChiTiet>, Void> worker = new SwingWorker<List<OSoChiTiet>, Void>() {

			@Override
			protected List<OSoChiTiet> doInBackground() throws Exception {
				List<OSoChiTiet> listO = vtdao.getAllO(IDngan);
				return listO;
			}

			@Override
			protected void done() {
				try {
					List<OSoChiTiet> listO = get();
					model.setRowCount(0);
					for (OSoChiTiet o : listO) {
						Object[] row = { o.getIdO(), o.getTenO(), o.getParent_ID(), o.getMaQS() };
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

	void fillCbxQuyenSach() {
		SwingWorker<List<QuyenSach>, Void> worker = new SwingWorker<List<QuyenSach>, Void>() {

			@Override
			protected List<QuyenSach> doInBackground() throws Exception {
				List<QuyenSach> listQS = vtdao.getQuyenSachChuaCoO();
				return listQS;
			}

			@Override
			protected void done() {
				try {
					List<QuyenSach> listQS = get();
					modelCbxQuyenSach.removeAllElements();
					for (QuyenSach qs : listQS) {
						modelCbxQuyenSach.addElement(qs);
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

	void update(String maQS) {
		try {
			if (DialogHelper.confirm(this, "Bạn có chắc chắn muốn Update không ?")) {

				String idO = txtO.getText();
				vtdao.update(idO, maQS);
				DialogHelper.alert(this, "Update Successful");
				fillCbxQuyenSach();
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Update Failed");
			e.printStackTrace();
		}

	}

	void clear() {

	}

	void setForm(OSoChiTiet oSo) {
		txtO.setText(oSo.getIdO());
	}

	OSoChiTiet getForm() {

		return null;
	}

	ViTri getForminsert() {

		return null;
	}

//        catch (NumberFormatException e) {
//            // Xử lý nếu không thể chuyển đổi thành số nguyên
//            DialogHelper.alert(this, "Ngày số không hợp lệ. Vui lòng nhập số nguyên.");
//            return null;
	void edit() {

	}

	private ViTri getInsert() {

		return null;
	}
}
