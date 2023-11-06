package com.thuvien.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.thuvien.dao.TacGiaDao;
import com.thuvien.entity.TacGia;
import com.thuvien.utils.DialogHelper;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuanLyTacGiaJDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaTG;
	private JTextField txtHoTen;
	private JTextField txtQuocTich;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtTimKiem;
	private JTable table;
	DefaultTableModel model;
	TacGiaDao tgd = new TacGiaDao();
	private JRadioButton rdoNam;
	private JRadioButton rdoNu;
	int index = 0;
	private JTabbedPane tabbedPane;
	int indexTrang = 1;
	private JLabel lblIndexTrang;
	private AbstractButton btnFirst;
	private JButton btnPrevEdit;
	private JButton btnNextEdit;
	private JButton btnLast;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyTacGiaJDialog dialog = new QuanLyTacGiaJDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public QuanLyTacGiaJDialog() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				load(indexTrang);
			}
		});
		setBounds(100, 100, 597, 448);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Quản Lý Tác Giả");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(20, 10, 179, 21);
		getContentPane().add(lblNewLabel);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 47, 563, 354);
		getContentPane().add(tabbedPane);

		JPanel pnlEdit = new JPanel();
		tabbedPane.addTab("Cập Nhật", null, pnlEdit, null);
		pnlEdit.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Mã Tác Giả");
		lblNewLabel_1.setBounds(10, 10, 104, 13);
		pnlEdit.add(lblNewLabel_1);

		txtMaTG = new JTextField();
		txtMaTG.setBounds(10, 33, 296, 19);
		pnlEdit.add(txtMaTG);
		txtMaTG.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Họ Và Tên");
		lblNewLabel_1_1.setBounds(10, 82, 104, 13);
		pnlEdit.add(lblNewLabel_1_1);

		txtHoTen = new JTextField();
		txtHoTen.setBounds(10, 105, 296, 19);
		pnlEdit.add(txtHoTen);
		txtHoTen.setColumns(10);

		JLabel lblNewLabel_1_1_1 = new JLabel("Quốc Tịch");
		lblNewLabel_1_1_1.setBounds(10, 156, 104, 13);
		pnlEdit.add(lblNewLabel_1_1_1);

		txtQuocTich = new JTextField();
		txtQuocTich.setColumns(10);
		txtQuocTich.setBounds(10, 178, 296, 19);
		pnlEdit.add(txtQuocTich);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Giới Tính");
		lblNewLabel_1_1_1_1.setBounds(10, 219, 104, 13);
		pnlEdit.add(lblNewLabel_1_1_1_1);

		rdoNam = new JRadioButton("Nam");
		rdoNam.setSelected(true);
		buttonGroup.add(rdoNam);
		rdoNam.setBounds(10, 238, 81, 21);
		pnlEdit.add(rdoNam);

		rdoNu = new JRadioButton("Nữ");
		buttonGroup.add(rdoNu);
		rdoNu.setBounds(102, 238, 103, 21);
		pnlEdit.add(rdoNu);

		JPanel pnlButton = new JPanel();
		pnlButton.setBounds(398, 33, 81, 168);
		pnlEdit.add(pnlButton);
		pnlButton.setLayout(new GridLayout(4, 1, 5, 20));

		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});
		pnlButton.add(btnInsert);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		pnlButton.add(btnDelete);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		pnlButton.add(btnUpdate);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		pnlButton.add(btnClear);

		JPanel panel = new JPanel();
		panel.setBounds(260, 249, 288, 27);
		pnlEdit.add(panel);
		panel.setLayout(new GridLayout(1, 4, 10, 0));

		btnFirst = new JButton("First");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edit();
			}
		});
		panel.add(btnFirst);

		btnPrevEdit = new JButton("Prev");
		btnPrevEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index--;
				edit();
			}
		});
		panel.add(btnPrevEdit);

		btnNextEdit = new JButton("Next");
		btnNextEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index++;
				edit();
			}
		});
		panel.add(btnNextEdit);

		btnLast = new JButton("Last");
		panel.add(btnLast);

		JPanel pnlList = new JPanel();
		tabbedPane.addTab("Danh Sách", null, pnlList, null);
		pnlList.setLayout(null);

		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(10, 20, 401, 19);
		pnlList.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		JButton btnNewButton_8 = new JButton("Search");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnNewButton_8.setBounds(447, 19, 85, 21);
		pnlList.add(btnNewButton_8);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 49, 548, 237);
		pnlList.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					index = table.rowAtPoint(e.getPoint());
					edit();
					tabbedPane.setSelectedIndex(0);
				}
			}
		});

		String[] columns = { "Mã Tác Giả", "Tên", "Quốc Tịch", "Giới Tính" };
		Object[][] rows = {

		};
		model = new DefaultTableModel(rows, columns);
		table.setModel(model);
		scrollPane.setViewportView(table);

		JButton btnPrevList = new JButton("Prev");
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
		btnPrevList.setBounds(159, 296, 85, 21);
		pnlList.add(btnPrevList);

		JButton btnNextList = new JButton("Next");
		btnNextList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indexTrang++;

				if (indexTrang > (Math.ceil(tgd.selectAll().size() * 1.0 / 5))) {
					DialogHelper.alert(null, "Đây là trang cuối cùng !");
					indexTrang--;
				} else {

					load(indexTrang);
					lblIndexTrang.setText(indexTrang + "");
				}
			}
		});
		btnNextList.setBounds(303, 296, 85, 21);
		pnlList.add(btnNextList);

		lblIndexTrang = new JLabel("1");
		lblIndexTrang.setBounds(269, 300, 39, 13);
		pnlList.add(lblIndexTrang);
		setLocationRelativeTo(null);
	}

	void load(int soTrang) {
		List<TacGia> list = tgd.loadTrang((soTrang - 1) * 5, 5);
		model.setRowCount(0);
		for (TacGia tg : list) {
			Object[] row = { tg.getMaTG(), tg.getHoTen(), tg.getQuocTich(), tg.isGioiTinh() ? "Nam" : "Nữ" };
			model.addRow(row);
		}
	}

	void insert() {
		try {
			TacGia tg = getForm();
			tgd.insert(tg);
			load(indexTrang);
			clear();
			DialogHelper.alert(this, "Insert Successful");
		} catch (Exception e) {
			DialogHelper.alert(this, "Insert Failed");
		}

	}

	void delete() {
		try {
			if (DialogHelper.confirm(this, "Bạn có chắc chắn muốn xóa không ?")) {
				TacGia tg = getForm();
				tgd.delete(tg.getMaTG());
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
				TacGia tg = getForm();
				tgd.update(tg);
				load(indexTrang);
				clear();
				DialogHelper.alert(this, "Update Successful");
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Update Failed");
		}

	}

	void clear() {
		txtHoTen.setText("");
		txtMaTG.setText("");
		txtQuocTich.setText("");
		rdoNam.setSelected(true);
	}

	void setForm(TacGia tg) {
		txtMaTG.setText(tg.getMaTG());
		txtHoTen.setText(tg.getHoTen());
		txtQuocTich.setText(tg.getQuocTich());
		if (tg.isGioiTinh()) {
			rdoNam.setSelected(true);
		} else {
			rdoNu.setSelected(true);
		}
	}

	TacGia getForm() {
		TacGia tg = new TacGia();
		tg.setMaTG(txtMaTG.getText());
		tg.setHoTen(txtHoTen.getText());
		tg.setQuocTich(txtQuocTich.getText());
		tg.setGioiTinh(rdoNam.isSelected());
		return tg;
	}

	void edit() {
		try {
			String maTG = (String) table.getValueAt(this.index, 0);
			TacGia tg = tgd.selectById(maTG);
			if (tg != null) {
				setForm(tg);
				setStatus(false);
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
		}

	}

	void setStatus(boolean insertable) {
		boolean first = this.index > 0;
		boolean last = this.index < model.getRowCount() - 1;
		btnFirst.setEnabled(!insertable && first);
		btnPrevEdit.setEnabled(!insertable && first);
		btnNextEdit.setEnabled(!insertable && last);
		btnLast.setEnabled(!insertable && last);
	}

	void search() {
		model.setRowCount(0);
		try {
			List<TacGia> list = tgd.selectByKeyword(txtTimKiem.getText());
			for (TacGia tg : list) {
				Object[] row = { tg.getMaTG(), tg.getHoTen(), tg.getQuocTich(), tg.isGioiTinh() ? "Nam" : "Nữ" };
				model.addRow(row);
			}
		} catch (Exception e) {
			DialogHelper.alert(this, "Lỗi truy vấn dữ liệu !");
		}
	}
}
