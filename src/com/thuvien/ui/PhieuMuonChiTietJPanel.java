package com.thuvien.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.thuvien.dao.PhieuMuonChiTietDao;
import com.thuvien.dao.PhieuMuonDao;
import com.thuvien.dao.QuyenSachDao;
import com.thuvien.entity.PhieuMuon;
import com.thuvien.entity.PhieuMuonCT;
import com.thuvien.entity.QuyenSach;
import com.thuvien.utils.DialogHelper;

public class PhieuMuonChiTietJPanel extends JPanel {

	private final ButtonGroup buttonGroup = new ButtonGroup();
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
	private JTextField txtTimSach;
	private JList jListSach;
	Date ngayHienTai = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	QuyenSachDao qsd = new QuyenSachDao();
	PhieuMuonDao pmd = new PhieuMuonDao();
	PhieuMuonChiTietDao pmctd = new PhieuMuonChiTietDao();

	DefaultListModel<QuyenSach> listModel = new DefaultListModel<>();

	List<QuyenSach> listQuyenSach = new ArrayList<>();
	private JLabel lblNewLabel_2;
	private JLabel lblThanhVien;

	public PhieuMuonChiTietJPanel(PhieuMuon phieuMuon) {
		setLayout(null);
		JLabel lblTitle = new JLabel("Phiếu Mượn Chi Tiết");
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setBounds(495, 10, 327, 37);
		add(lblTitle);

		JPanel pnlDanhSach = new JPanel();
		pnlDanhSach.setLayout(null);
		pnlDanhSach.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh S\u00E1ch", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		pnlDanhSach.setBounds(530, 87, 765, 378);
		add(pnlDanhSach);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 30, 739, 338);
		pnlDanhSach.add(scrollPane);

		loadJList();

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
		String[] columns = { "ID", "Mã Phiếu Mượn", "Tên Sách" };
		Object[][] rows = {

		};
		model = new DefaultTableModel(rows, columns);
		table.setModel(model);

		JPanel pnlButton2 = new JPanel();
		pnlButton2.setBounds(98, 521, 350, 30);
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
		pnlButton1.setBounds(98, 481, 350, 30);
		add(pnlButton1);
		pnlButton1.setLayout(new GridLayout(1, 4, 10, 0));

		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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

			}
		});
		btnPrevList.setBounds(737, 487, 85, 21);
		add(btnPrevList);

		btnNextList = new JButton("Next");
		btnNextList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnNextList.setBounds(1007, 487, 85, 21);
		add(btnNextList);

		setStatus(true);

		lblIndexTrang = new JLabel("1");
		lblIndexTrang.setBounds(916, 491, 39, 13);
		add(lblIndexTrang);

		JPanel panel = new JPanel();
		panel.setBounds(32, 113, 477, 352);
		add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(10, 66, 457, 265);
		panel.add(scrollPane_1);

		jListSach = new JList();
		scrollPane_1.setViewportView(jListSach);
		jListSach.setModel(listModel);

		txtTimSach = new JTextField();
		txtTimSach.setBounds(10, 37, 457, 19);
		panel.add(txtTimSach);
		txtTimSach.setColumns(10);
		txtTimSach.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateList();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateList();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateList();
			}

			private void updateList() {
				String prefix = txtTimSach.getText();
				List<QuyenSach> filteredData = new ArrayList<>();

				for (QuyenSach item : listQuyenSach) {
					if (item.getTenQS().toLowerCase().startsWith(prefix.toLowerCase())) {
						filteredData.add(item);
					}
				}

				listModel.clear();
				for (QuyenSach item : filteredData) {
					listModel.addElement(item);
				}
			}
		});

		jListSach.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!jListSach.isSelectionEmpty()) {
					QuyenSach selectedValue = (QuyenSach) jListSach.getSelectedValue();

					int kq = insert(phieuMuon, selectedValue);

					if (kq == 1) {
						// Loại bỏ đối tượng đã chọn khỏi danh sách
						// Cập nhật lại JList
						listQuyenSach.remove(selectedValue);
						listModel.removeElement(selectedValue);
					}
				}
			}
		});

		JLabel lblNewLabel = new JLabel("Nhập Tên Sách");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 10, 120, 19);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				quayLaiPhieuMuon();
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(PhieuMuonChiTietJPanel.class.getResource("/icon/quayLai.png")));
		lblNewLabel_1.setBounds(32, 29, 66, 30);
		add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Phiếu Mượn Chi Tiết Của :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(45, 82, 162, 21);
		add(lblNewLabel_2);

		lblThanhVien = new JLabel("");
		lblThanhVien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblThanhVien.setBounds(206, 82, 269, 21);
		lblThanhVien.setText(phieuMuon.getIdThanhVien().getTenTV());
		add(lblThanhVien);
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				loadTable(phieuMuon);
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

	private void quayLaiPhieuMuon() {
		// Tạo một đối tượng của PhieuMuonJPanel
		PhieuMuonJPanel phieuMuonJPanel = new PhieuMuonJPanel();

		// Lấy đối tượng Container chứa PhieuMuonChiTietJPanel
		Container container = this.getParent();

		// Thay thế PhieuMuonChiTietJPanel bằng PhieuMuonJPanel trong Container
		container.remove(this);
		container.add(phieuMuonJPanel);

		// Yêu cầu Container cập nhật lại giao diện
		container.revalidate();
		container.repaint();
	}

	void loadJList() {
		SwingWorker<List<QuyenSach>, Void> worker = new SwingWorker<List<QuyenSach>, Void>() {

			@Override
			protected List<QuyenSach> doInBackground() throws Exception {
				return qsd.sachMuonDuoc();
			}

			@Override
			protected void done() {
				try {
					List<QuyenSach> initialData = get();
					// Xóa dữ liệu hiện tại
					listModel.clear();
					listQuyenSach.clear();

					// Thêm dữ liệu ban đầu vào danh sách và mô hình danh sách
					for (QuyenSach qs : initialData) {
						listQuyenSach.add(qs);
						listModel.addElement(qs);
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

	void loadTable(PhieuMuon phieuMuon) {
		SwingWorker<List<PhieuMuonCT>, Void> worker = new SwingWorker<List<PhieuMuonCT>, Void>() {

			@Override
			protected List<PhieuMuonCT> doInBackground() throws Exception {
				return pmctd.selectAllTheoPhieuMuon(phieuMuon.getId());
			}

			@Override
			protected void done() {
				try {
					List<PhieuMuonCT> listPMCT = get();
					model.setRowCount(0);
					for (PhieuMuonCT pmct : listPMCT) {
						Object[] row = { pmct.getId(), pmct.getIdPhieuMuon().getMaPhieuMuon(),
								pmct.getIdQuyenSach().getTenQS() };
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

	int insert(PhieuMuon pm, QuyenSach qs) {
		int kq = 0;
		try {
			int choose = JOptionPane.showConfirmDialog(this,
					"Bạn có chắc chắn muốn thêm " + qs.getTenQS() + " vào không ? ", "Thêm Sách Mượn",
					JOptionPane.YES_NO_OPTION);
			if (choose == JOptionPane.YES_OPTION) {
				PhieuMuonCT pmct = new PhieuMuonCT(pm, qs);
				if (pmct != null) {
					pmctd.insert(pmct);
					loadTable(pm);
					clear();
					DialogHelper.alert(this, "Insert Successful");
					kq = 1;
				}
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Insert Failed");
		}
		return kq;
	}

	void delete() {

	}

	void update() {

	}

	void clear() {

		setStatus(true);
	}

	void setForm(PhieuMuon pm) {

	}

	PhieuMuon getForm() {

		return null;
	}

	void edit() {

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
