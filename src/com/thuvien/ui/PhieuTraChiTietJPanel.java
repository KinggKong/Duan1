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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.thuvien.dao.PhieuMuonChiTietDao;
import com.thuvien.dao.PhieuMuonDao;
import com.thuvien.dao.PhieuTraChiTietDao;
import com.thuvien.entity.PhieuMuon;
import com.thuvien.entity.PhieuMuonCT;
import com.thuvien.entity.PhieuTra;
import com.thuvien.entity.PhieuTraCT;
import com.thuvien.entity.TacGia;
import com.thuvien.entity.ViTri;
import com.thuvien.utils.DialogHelper;
import com.thuvien.utils.ShareHelper;
import com.thuvien.utils.XDate;

import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class PhieuTraChiTietJPanel extends JPanel {

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
	private JComboBox cbxMaPhieuMuon;
	private JComboBox cbxPhieuMuonChiTiet;
	private JRadioButton rdoDaTra;
	private JTextArea txtGhiChu;
	Date ngayHienTai = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	DefaultComboBoxModel<PhieuMuon> modelPhieuMuon = new DefaultComboBoxModel<>();
	DefaultComboBoxModel<PhieuMuonCT> modelPhieuMuonCT = new DefaultComboBoxModel<>();

	PhieuTraChiTietDao ptctd = new PhieuTraChiTietDao();
	PhieuMuonDao pmd = new PhieuMuonDao();
	PhieuMuonChiTietDao pmctd = new PhieuMuonChiTietDao();
	private JLabel lblNewLabel_2;
	private JLabel lblThanhVien;

	public PhieuTraChiTietJPanel(PhieuTra phieuTra) {
		setLayout(null);
		JLabel lblTitle = new JLabel("Phiếu Trả Chi Tiết");
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setBounds(530, 10, 327, 37);
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
		String[] columns = { "ID", "Thành Viên", "Quyển Sách", "Tình Trạng ", "Ghi Chú" };
		Object[][] rows = {

		};
		model = new DefaultTableModel(rows, columns);
		table.setModel(model);

		JPanel pnlButton2 = new JPanel();
		pnlButton2.setBounds(98, 515, 350, 30);
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
		pnlButton1.setBounds(98, 475, 350, 30);
		add(pnlButton1);
		pnlButton1.setLayout(new GridLayout(1, 4, 10, 0));

		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert(phieuTra);
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

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				quayLaiPhieuTra();
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(PhieuTraChiTietJPanel.class.getResource("/icon/quayLai.png")));
		lblNewLabel_1.setBounds(32, 29, 66, 30);
		add(lblNewLabel_1);

		JPanel panel = new JPanel();
		panel.setBounds(21, 122, 499, 343);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mã Phiếu Mượn");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 10, 144, 29);
		panel.add(lblNewLabel);

		cbxMaPhieuMuon = new JComboBox();
		cbxMaPhieuMuon.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				PhieuMuon pm = (PhieuMuon) cbxMaPhieuMuon.getSelectedItem();
				int idPhieuMuon = pm.getId();
				fillCbxPhieuMuonCT(idPhieuMuon);
				loadTable(idPhieuMuon);
			}
		});
		cbxMaPhieuMuon.setBounds(10, 41, 203, 29);
		panel.add(cbxMaPhieuMuon);
		cbxMaPhieuMuon.setModel(modelPhieuMuon);

		cbxPhieuMuonChiTiet = new JComboBox();
		cbxPhieuMuonChiTiet.setBounds(10, 111, 479, 29);
		panel.add(cbxPhieuMuonChiTiet);
		cbxPhieuMuonChiTiet.setModel(modelPhieuMuonCT);

		JLabel lblPhiuMnChi = new JLabel("Phiếu Mượn Chi Tiết");
		lblPhiuMnChi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPhiuMnChi.setBounds(10, 72, 224, 29);
		panel.add(lblPhiuMnChi);

		JLabel lblNewLabel_2_1 = new JLabel("Tình Trạng");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(299, 10, 144, 29);
		panel.add(lblNewLabel_2_1);

		rdoDaTra = new JRadioButton("Đã Trả");
		rdoDaTra.setSelected(true);
		buttonGroup.add(rdoDaTra);
		rdoDaTra.setBounds(299, 49, 103, 21);
		panel.add(rdoDaTra);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(10, 200, 479, 125);
		panel.add(scrollPane_1);

		txtGhiChu = new JTextArea();
		scrollPane_1.setViewportView(txtGhiChu);

		JLabel lblGhiChu = new JLabel("Ghi Chú");
		lblGhiChu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGhiChu.setBounds(10, 165, 144, 29);
		panel.add(lblGhiChu);

		lblNewLabel_2 = new JLabel("Phiếu Trả Chi Tiết Của :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(31, 82, 145, 21);
		add(lblNewLabel_2);

		lblThanhVien = new JLabel("");
		lblThanhVien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblThanhVien.setBounds(186, 82, 283, 21);
		add(lblThanhVien);
		lblThanhVien.setText(phieuTra.getIdThanhVien().getTenTV());
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				fillCbxPhieuMuon(phieuTra);
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

	private void quayLaiPhieuTra() {
		// Tạo một đối tượng của PhieuMuonJPanel
		PhieuTraJPanel phieuMuonJPanel = new PhieuTraJPanel();

		// Lấy đối tượng Container chứa PhieuMuonChiTietJPanel
		Container container = this.getParent();

		// Thay thế PhieuMuonChiTietJPanel bằng PhieuMuonJPanel trong Container
		container.remove(this);
		container.add(phieuMuonJPanel);

		// Yêu cầu Container cập nhật lại giao diện
		container.revalidate();
		container.repaint();
	}

	void fillCbxPhieuMuon(PhieuTra phieuTra) {
		SwingWorker<List<PhieuMuon>, Void> worker = new SwingWorker<List<PhieuMuon>, Void>() {

			@Override
			protected List<PhieuMuon> doInBackground() throws Exception {
				return pmd.selectTheoThanhVien(phieuTra.getIdThanhVien().getId());
			}

			@Override
			protected void done() {
				try {
					List<PhieuMuon> listPhieuMuon = get();
					modelPhieuMuon.removeAllElements();
					for (PhieuMuon pm : listPhieuMuon) {
						modelPhieuMuon.addElement(pm);
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

	void fillCbxPhieuMuonCT(int idPhieuMuon) {
		SwingWorker<List<PhieuMuonCT>, Void> worker = new SwingWorker<List<PhieuMuonCT>, Void>() {

			@Override
			protected List<PhieuMuonCT> doInBackground() throws Exception {
				return pmctd.selectPhieuMuonCTChuaTra(idPhieuMuon);
			}

			@Override
			protected void done() {
				try {
					List<PhieuMuonCT> list = get();
					modelPhieuMuonCT.removeAllElements();
					for (PhieuMuonCT pmct : list) {
						modelPhieuMuonCT.addElement(pmct);
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

	void loadTable(int idPhieuMuon) {
		SwingWorker<List<PhieuTraCT>, Void> worker = new SwingWorker<List<PhieuTraCT>, Void>() {

			@Override
			protected List<PhieuTraCT> doInBackground() throws Exception {
				return ptctd.selectAllTheoIDPhieuMuon(idPhieuMuon);
			}

			@Override
			protected void done() {
				try {
					List<PhieuTraCT> listPMCT = get();
					model.setRowCount(0);
					for (PhieuTraCT ptct : listPMCT) {
						Object[] row = { ptct.getId(), ptct.getIdPhieuTra().getIdThanhVien().getTenTV(),
								ptct.getIdPhieuMuonCT().getIdQuyenSach().getTenQS(),
								ptct.isTinhTrangSach() ? "Đã Trả" : "Chưa Trả", ptct.getGhiChu() };
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
		fillCbxPhieuMuonCT(idPhieuMuon);
	}

	void insert(PhieuTra phieuTra) {
		try {
			PhieuTraCT ptct = getForm(phieuTra);
			if (ptct != null) {
				ptctd.insert(ptct);
				PhieuMuon phieuMuon = (PhieuMuon) cbxMaPhieuMuon.getSelectedItem();
				loadTable(phieuMuon.getId());
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
				int idPhieuMuonCT = (int) table.getValueAt(this.index, 0);
				pmctd.delete(idPhieuMuonCT);
				PhieuMuon phieuMuon = (PhieuMuon) cbxMaPhieuMuon.getSelectedItem();
				loadTable(phieuMuon.getId());
				clear();
				DialogHelper.alert(this, "Delete Successful");
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Delete Failed");
		}
	}

	void update() {

	}

	void clear() {

		setStatus(true);
	}

	void setForm(PhieuTra pt) {

	}

	PhieuTraCT getForm(PhieuTra phieutra) {
		PhieuTraCT ptct = new PhieuTraCT();
		ptct.setIdPhieuTra(phieutra);
		PhieuMuonCT pmct = (PhieuMuonCT) cbxPhieuMuonChiTiet.getSelectedItem();
		ptct.setIdPhieuMuonCT(pmct);
		ptct.setTinhTrangSach(rdoDaTra.isSelected() == true ? true : false);
		ptct.setGhiChu(txtGhiChu.getText());
		return ptct;
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
