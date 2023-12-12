package com.thuvien.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.thuvien.dao.QuyenSachDao;
import com.thuvien.dao.QuyenSachTimDao;
import com.thuvien.dao.SachDao;
import com.thuvien.dao.TaiBanDao;
import com.thuvien.entity.QuyenSach;
import com.thuvien.entity.QuyenSachTim;
import com.thuvien.entity.Sach;
import com.thuvien.entity.TaiBan;
import com.thuvien.entity.ViTri;
import com.thuvien.utils.DialogHelper;
import javax.swing.JRadioButton;

public class QuyenSachJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaQS;
	private JTextField txtTimKiem;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	DefaultTableModel model;
	private JComboBox cbxTinhTrang;
	private JTextArea txtGhiChu;
	private JButton btnInsert;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnClear;
	private JButton btnPrevList;
	private JButton btnNextList;
	private int indexTrang = 1;
	private int index = 0;
	private JButton btnFirst;
	private JButton btnPrevEdit;
	private JButton btnNextEdit;
	private JButton btnLast;

	SachDao sd = new SachDao();
	QuyenSachDao quyenSachDao = new QuyenSachDao();
	TaiBanDao tbd = new TaiBanDao();
	QuyenSachTimDao qstd = new QuyenSachTimDao();

	private JLabel lblIndexTrang;
	private JButton btnTimKiem;
	private JComboBox cbxSach;
	private JComboBox cbxTaiBan;

	DefaultComboBoxModel<TaiBan> modelTaiBan = new DefaultComboBoxModel<>();
	DefaultComboBoxModel<Sach> modelSach = new DefaultComboBoxModel<>();
	DefaultComboBoxModel<ViTri> modelViTri = new DefaultComboBoxModel<>();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JRadioButton rdoViTriSearch;
	private JRadioButton rdoTenSachSearch;

	public QuyenSachJPanel() {
		setLayout(null);

		JLabel lblTitle = new JLabel("Quản Lý Quyển Sách");
		lblTitle.setBounds(494, 10, 350, 37);
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		add(lblTitle);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(21, 108, 516, 318);
		add(panel);
		panel.setLayout(null);

		JLabel lblMSch = new JLabel("Mã QS");
		lblMSch.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMSch.setBounds(10, 75, 104, 19);
		panel.add(lblMSch);

		JLabel lblTenSach = new JLabel("Sách");
		lblTenSach.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenSach.setBounds(10, 10, 104, 19);
		panel.add(lblTenSach);

		txtMaQS = new JTextField();
		txtMaQS.setEnabled(false);
		txtMaQS.setColumns(10);
		txtMaQS.setBounds(10, 104, 264, 19);
		panel.add(txtMaQS);

		JLabel lblNmXutBn = new JLabel("Lần Tái Bản\r\n");
		lblNmXutBn.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNmXutBn.setBounds(302, 10, 144, 19);
		panel.add(lblNmXutBn);

		cbxTinhTrang = new JComboBox();
		cbxTinhTrang.setModel(new DefaultComboBoxModel(new String[] { "Hỏng", "Mới", "Hỏng Nhẹ" }));
		cbxTinhTrang.setBounds(302, 101, 204, 24);
		panel.add(cbxTinhTrang);

		JLabel lblTinhTrang = new JLabel("Tình Trạng");
		lblTinhTrang.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTinhTrang.setBounds(299, 75, 128, 19);
		panel.add(lblTinhTrang);

		JLabel lblGhiChu = new JLabel("Ghi Chú");
		lblGhiChu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGhiChu.setBounds(10, 161, 128, 19);
		panel.add(lblGhiChu);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(10, 190, 496, 118);
		panel.add(scrollPane_1);

		txtGhiChu = new JTextArea();
		scrollPane_1.setViewportView(txtGhiChu);

		cbxSach = new JComboBox();
		cbxSach.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Sach s = (Sach) cbxSach.getSelectedItem();
				int idSach = s.getId();
				fillTaiBan(idSach);

			}
		});
		cbxSach.setBounds(10, 33, 264, 24);
		panel.add(cbxSach);
		cbxSach.setModel(modelSach);

		cbxTaiBan = new JComboBox();
		cbxTaiBan.setBounds(304, 33, 202, 24);
		panel.add(cbxTaiBan);
		cbxTaiBan.setModel(modelTaiBan);

		JPanel pnlButton2 = new JPanel();
		pnlButton2.setBounds(775, 520, 363, 30);
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
		pnlDanhSach.setBounds(549, 83, 755, 378);
		add(pnlDanhSach);

		JLabel lblTimKiem = new JLabel("Tìm Kiếm");
		lblTimKiem.setBounds(10, 20, 55, 13);
		pnlDanhSach.add(lblTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setText("Nhập vào mã quyển sách để tìm vị trí");
		txtTimKiem.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtTimKiem.getText().equals("Nhập vào mã quyển sách để tìm vị trí")) {
					txtTimKiem.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTimKiem.getText().isEmpty()) {
					txtTimKiem.setText("Nhập vào mã quyển sách để tìm vị trí");
				}
			}
		});
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(85, 17, 509, 25);
		pnlDanhSach.add(txtTimKiem);

		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setIcon(new ImageIcon(QuyenSachJPanel.class.getResource("/icon/Zoom.png")));
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtTimKiem.getText().equals(" ")) {
					load(indexTrang);
				} else {
					String keySearch = txtTimKiem.getText();
					if (rdoViTriSearch.isSelected()) {
						QuyenSachTim qst = qstd.selectById(keySearch);
						if (qst != null) {
							new ViTriTimSachJDialog(QuyenSachJPanel.this, true, qst).setVisible(true);

						} else {
							DialogHelper.alert(QuyenSachJPanel.this, "Quyển Sách Chưa Có Vị Trí Cụ Thể ");
						}
					} else {
						search(keySearch);
					}

				}
			}
		});
		btnTimKiem.setBounds(612, 16, 122, 26);
		pnlDanhSach.add(btnTimKiem);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 80, 735, 288);
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
		String[] columns = { "Mã Sách", "Tên Sách", "Lần TB", "Ghi Chú", "Trạng Thái" };
		Object[][] rows = {

		};
		model = new DefaultTableModel(rows, columns);
		table.setModel(model);

		rdoViTriSearch = new JRadioButton("Vị Trí");
		buttonGroup_1.add(rdoViTriSearch);
		rdoViTriSearch.setSelected(true);
		rdoViTriSearch.setBounds(83, 48, 103, 21);
		pnlDanhSach.add(rdoViTriSearch);

		rdoTenSachSearch = new JRadioButton("Tên Sách");
		buttonGroup_1.add(rdoTenSachSearch);
		rdoTenSachSearch.setBounds(232, 48, 103, 21);
		pnlDanhSach.add(rdoTenSachSearch);

		btnPrevList = new JButton("Prev");
		btnPrevList.setIcon(new ImageIcon(QuyenSachJPanel.class.getResource("/icon/Left.png")));
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
		btnPrevList.setBounds(775, 480, 98, 30);
		add(btnPrevList);

		btnNextList = new JButton("Next");
		btnNextList.setIcon(new ImageIcon(QuyenSachJPanel.class.getResource("/icon/Right.png")));
		btnNextList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNextList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nextPage();
			}
		});

		btnNextList.setBounds(1040, 480, 98, 30);
		add(btnNextList);

		lblIndexTrang = new JLabel("1");
		lblIndexTrang.setBounds(958, 489, 39, 13);
		add(lblIndexTrang);

		JButton btnImport = new JButton("Import");
		btnImport.setIcon(new ImageIcon(QuyenSachJPanel.class.getResource("/icon/Import.png")));
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importExcel();
			}
		});
		btnImport.setBounds(549, 476, 114, 30);
		add(btnImport);

		JButton btnExport = new JButton("Export");
		btnExport.setIcon(new ImageIcon(QuyenSachJPanel.class.getResource("/icon/Export.png")));
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportExcel();
			}
		});
		btnExport.setBounds(549, 520, 114, 30);
		add(btnExport);

		btnInsert = new JButton("Insert");
		btnInsert.setBounds(110, 455, 124, 37);
		add(btnInsert);
		btnInsert.setIcon(new ImageIcon(QuyenSachJPanel.class.getResource("/icon/Add.png")));
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});
		btnInsert.setEnabled(true);

		btnDelete = new JButton("Delete");
		btnDelete.setBounds(285, 513, 124, 37);
		add(btnDelete);
		btnDelete.setIcon(new ImageIcon(QuyenSachJPanel.class.getResource("/icon/Delete.png")));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setRolloverIcon(new ImageIcon("src/icon/delete2.png"));

		btnClear = new JButton("Clear");
		btnClear.setBounds(285, 455, 124, 37);
		add(btnClear);
		btnClear.setIcon(new ImageIcon(QuyenSachJPanel.class.getResource("/icon/Trash.png")));

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(110, 513, 124, 37);
		add(btnUpdate);
		btnUpdate.setIcon(new ImageIcon(QuyenSachJPanel.class.getResource("/icon/Upload.png")));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnUpdate.setEnabled(false);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		SwingWorker<Void, Void> wk = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				load(indexTrang);
				fillSach();
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
				return quyenSachDao.loadTrang((soTrang - 1) * 15, 15);
			}

			@Override
			protected void done() {
				try {
					List<QuyenSach> list = get();
					model.setRowCount(0);
					for (QuyenSach qs : list) {
						String trangThai = getTrangThai(qs.getTinhTrang());
						Object[] row = { qs.getMaQS(), qs.getTenQS(), tbd.selectById(qs.getIdTaiBan()).getLanTaiBan(),
								qs.getGhiChu(), trangThai };
						model.addRow(row);
					}
					SwingUtilities.invokeLater(() -> table.repaint());
				} catch (Exception e) {
					DialogHelper.alert(QuyenSachJPanel.this, "Lỗi truy vấn dữ liệu!");
					e.printStackTrace();
				}
			}
		};

		worker.execute();
	}

	String getTrangThai(int tinhTrang) {
		switch (tinhTrang) {
		case 0:
			return "Hỏng";
		case 1:
			return "Mới";
		case 2:
			return "Hỏng Nhẹ";
		default:
			return "Tình Trạng Không Đúng !!!";
		}
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
				String maQS = (String) table.getValueAt(this.index, 0);
				QuyenSach qs = quyenSachDao.selectById(maQS);
				if (qs != null) {
					Sach sach = (Sach) cbxSach.getSelectedItem();
					qs.setTenQS(sach.getTenSach());
					TaiBan taiBan = (TaiBan) cbxTaiBan.getSelectedItem();
					if (taiBan != null) {
						qs.setIdTaiBan(taiBan.getId());
					} else {
						DialogHelper.alert(this, "sách chưa có tái bản");
					}
//					qs.setIdTaiBan(taiBan.getId());
					qs.setGhiChu(txtGhiChu.getText());
					if (cbxTinhTrang.getSelectedIndex() == 0) {
						qs.setTinhTrang(0);
					} else if (cbxTinhTrang.getSelectedIndex() == 1) {
						qs.setTinhTrang(1);
					} else {
						qs.setTinhTrang(2);
					}
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
		cbxTinhTrang.setSelectedIndex(0);
		setStatus(true);
		txtTimKiem.setText("Nhập vào mã quyển sách để tìm vị trí");
		table.clearSelection();
	}

	void setForm(QuyenSach qs) {
		txtMaQS.setText(qs.getMaQS());
		cbxSach.setSelectedItem(sd.selectByIdSach(tbd.selectById(qs.getIdTaiBan()).getIdSach()));
		txtGhiChu.setText(qs.getGhiChu());
		cbxTinhTrang.setSelectedIndex(qs.getTinhTrang());
		TaiBan taiBan = tbd.selectById(qs.getIdTaiBan());
		cbxTaiBan.setSelectedItem(taiBan);
	}

	void setTaiBan() {

	}

	QuyenSach getForm() {
		QuyenSach qs = new QuyenSach();

//		if (txtMaQS.getText().isEmpty()) {
//			DialogHelper.alert(this, "Không để trống mã quyển sách ");
//			return null;
//		} else {
//			qs.setMaQS(txtMaQS.getText());
//		}
		qs.setMaQS(null);

		qs.setTenQS(cbxSach.getSelectedItem().toString());
		TaiBan tb = (TaiBan) cbxTaiBan.getSelectedItem();
		if (tb != null) {
			qs.setIdTaiBan(tb.getId());
		} else {
			DialogHelper.alert(this, "Sách chưa có tái bản");
			return null;
		}
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
//		btnDelete.setEnabled(!insertable);
		boolean first = this.index > 0;
		boolean last = this.index < model.getRowCount() - 1;
		btnFirst.setEnabled(!insertable && first);
		btnPrevEdit.setEnabled(!insertable && first);
		btnNextEdit.setEnabled(!insertable && last);
		btnLast.setEnabled(!insertable && last);
	}

	private void nextPage() {
		indexTrang++;
		int totalPages = calculateTotalPages();
		if (indexTrang > totalPages) {
			DialogHelper.alert(null, "Đây là trang cuối cùng !");
			indexTrang--;
		} else {
			load(indexTrang);
			lblIndexTrang.setText(indexTrang + "");
		}
	}

	private int calculateTotalPages() {
		double totalBooks = quyenSachDao.selectAll().size();
		int booksPerPage = 15;
		return (int) Math.ceil(totalBooks / booksPerPage);
	}

	private void importExcel() {
		File selectedFile;
		FileInputStream excelFIS = null;
		BufferedInputStream excelBuffer = null;
		XSSFWorkbook excelImportTable;

		// Đặt thư mục mặc định
		String defaultDirectory = "H:\\Excel";

		// Tạo một thể hiện JFileChooser
		JFileChooser excelFileChooser = new JFileChooser(defaultDirectory);

		// Mở hộp thoại chọn tệp
		int excelChoose = excelFileChooser.showDialog(this, "Chọn file Excel");

		// Kiểm tra xem đã chọn tệp chưa
		if (excelChoose == JFileChooser.APPROVE_OPTION) {
			try {
				// Lấy tệp đã chọn
				selectedFile = excelFileChooser.getSelectedFile();

				// Mở luồng đầu vào cho tệp đã chọn
				excelFIS = new FileInputStream(selectedFile);

				// Tạo luồng đầu vào được đệm
				excelBuffer = new BufferedInputStream(excelFIS);

				// Tạo một thể hiện XSSFWorkbook
				excelImportTable = new XSSFWorkbook(excelBuffer);

				// Lấy sheet đầu tiên trong workbook
				XSSFSheet excelSheet = excelImportTable.getSheetAt(0);

				// Lặp qua các hàng và ô của bảng tính
				for (int rowNum = 1; rowNum < excelSheet.getLastRowNum(); rowNum++) {
					XSSFRow excelRow = excelSheet.getRow(rowNum);

					if (excelRow != null) {
						if (hasData(excelRow)) {
							// Thực hiện INSERT
							QuyenSach quyenSach = new QuyenSach();

							// Đọc giá trị từ các ô và tạo đối tượng quyển sách
//								String maQS = excelRow.getCell(0).getStringCellValue();
//								if (maQS != null) {
//									quyenSach.setMaQS(maQS);
//								} else {
//									maQS = null;
//									quyenSach.setMaQS(maQS);
//								}
							String maQs = null;
							quyenSach.setMaQS(maQs);
							String tenQS = excelRow.getCell(1).getStringCellValue();
							quyenSach.setTenQS(tenQS);
							int idTaiBan = (int) excelRow.getCell(2).getNumericCellValue();
							quyenSach.setIdTaiBan(idTaiBan);
							int tinhTrang = (int) excelRow.getCell(3).getNumericCellValue();
							quyenSach.setTinhTrang(tinhTrang);
							String ghiChu = excelRow.getCell(4).getStringCellValue();
							quyenSach.setGhiChu(ghiChu);
							quyenSachDao.insert(quyenSach);
						}
						// Tạo đối tượng quyển sách

					}
				}
				JOptionPane.showMessageDialog(this, "Đã đọc xong dữ liệu từ Excel và chèn vào cơ sở dữ liệu.",
						"Thành công", JOptionPane.INFORMATION_MESSAGE);
				load(this.indexTrang);
			} catch (Exception e) {
				// Xử lý bất kỳ ngoại lệ nào xảy ra
//				e.printStackTrace();
//				System.out.println("Error message: " + e.getMessage());
				DialogHelper.alert(this, "File excel có lỗi dữ liệu !\nVui lòng thử lại");
			}
		}
	}

	private boolean hasData(XSSFRow row) {
		if (row == null) {
			return false;
		}

		for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
			XSSFCell cell = row.getCell(cellNum);
			if (cell != null && cell.getCellType() != CellType.BLANK) {
				return true;
			}
		}
		return false;
	}

	private void exportExcel() {
		try {
			// Tạo một workbook mới
			XSSFWorkbook workbook = new XSSFWorkbook();

			// Tạo một sheet mới trong workbook
			XSSFSheet sheet = workbook.createSheet("QuyenSachData");

			// Tạo hàng đầu tiên (tiêu đề)
			Row headerRow = sheet.createRow(0);

			// Tạo các ô tiêu đề
			String[] headers = { "Mã Sách", "Tên Sách", "Lần TB", "Ghi Chú", "Trạng Thái" };
			for (int i = 0; i < headers.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headers[i]);
			}

			// Thêm dữ liệu từ bảng vào sheet
			for (int i = 0; i < model.getRowCount(); i++) {
				Row row = sheet.createRow(i + 1); // Bắt đầu từ hàng thứ 2 vì hàng đầu tiên là tiêu đề

				for (int j = 0; j < model.getColumnCount(); j++) {
					Cell cell = row.createCell(j);
					cell.setCellValue(String.valueOf(model.getValueAt(i, j)));
				}
			}

			// Sử dụng JFileChooser để chọn nơi lưu file
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
			fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx"));
			int userSelection = fileChooser.showSaveDialog(this);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				// Lấy đường dẫn được chọn
				File fileToSave = fileChooser.getSelectedFile();
				String filePath = fileToSave.getAbsolutePath();

				// Nếu không có phần mở rộng .xlsx, thêm nó vào
				if (!filePath.toLowerCase().endsWith(".xlsx")) {
					filePath += ".xlsx";
				}

				try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
					workbook.write(outputStream);
					JOptionPane.showMessageDialog(this, "Dữ liệu đã được xuất thành công vào tệp Excel.", "Thành công",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Xuất Excel thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Xuất Excel thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	void search(String key) {
		SwingWorker<List<QuyenSach>, Void> worker = new SwingWorker<List<QuyenSach>, Void>() {

			@Override
			protected List<QuyenSach> doInBackground() throws Exception {
				return quyenSachDao.selectByKeyword(key);
			}

			@Override
			protected void done() {
				try {
					List<QuyenSach> list = get();
					if (list.isEmpty()) {
						DialogHelper.alert(QuyenSachJPanel.this, "Không tồn tại");
					} else {
						model.setRowCount(0);
						for (QuyenSach qs : list) {
							String trangThai = getTrangThai(qs.getTinhTrang());
							Object[] row = { qs.getMaQS(), qs.getTenQS(),
									tbd.selectById(qs.getIdTaiBan()).getLanTaiBan(), qs.getGhiChu(), trangThai };
							model.addRow(row);
						}
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
}
