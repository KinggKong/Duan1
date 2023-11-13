package com.thuvien.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.thuvien.dao.TacGiaDao;
import com.thuvien.dao.TheLoaiDao;
import com.thuvien.entity.TacGia;
import com.thuvien.entity.TheLoai;
import com.thuvien.utils.DialogHelper;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TheLoaiJPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtMaTheLoai;
    private JTextField txtTenTheLoai;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JTextField txtTimKiem;
    private JTable table;
    DefaultTableModel model;
    TheLoaiDao tld = new TheLoaiDao();
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

    public TheLoaiJPanel() {
        setLayout(null);
        JLabel lblTitle = new JLabel("Quản Lý Thể Loại");
        lblTitle.setForeground(Color.BLUE);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblTitle.setBounds(406, 10, 327, 37);
        add(lblTitle);

        JPanel pnlThongTinTG = new JPanel();
        pnlThongTinTG.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        pnlThongTinTG.setBounds(30, 125, 429, 163);
        add(pnlThongTinTG);
        pnlThongTinTG.setLayout(null);

        JLabel lblMaTG = new JLabel("Mã Thể Loại");
        lblMaTG.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblMaTG.setBounds(10, 16, 104, 19);
        pnlThongTinTG.add(lblMaTG);

        txtMaTheLoai = new JTextField();
        txtMaTheLoai.setColumns(10);
        txtMaTheLoai.setBounds(10, 38, 296, 19);
        pnlThongTinTG.add(txtMaTheLoai);

        JLabel lblTenTheLoai = new JLabel("Tên Thể Loại");
        lblTenTheLoai.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTenTheLoai.setBounds(10, 92, 104, 19);
        pnlThongTinTG.add(lblTenTheLoai);

        txtTenTheLoai = new JTextField();

        txtTenTheLoai.setColumns(10);
        txtTenTheLoai.setBounds(10, 113, 296, 19);
        pnlThongTinTG.add(txtTenTheLoai);

        JPanel pnlDanhSach = new JPanel();
        pnlDanhSach.setLayout(null);
        pnlDanhSach.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                "Danh S\u00E1ch", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
        pnlDanhSach.setBounds(527, 81, 491, 378);
        add(pnlDanhSach);

        JLabel lblTimKiem = new JLabel("Tìm Kiếm");
        lblTimKiem.setBounds(10, 20, 55, 13);
        pnlDanhSach.add(lblTimKiem);

        txtTimKiem = new JTextField();
        txtTimKiem.setBounds(85, 17, 266, 19);
        pnlDanhSach.add(txtTimKiem);
        txtTimKiem.setColumns(10);

        JButton btnTimKiem = new JButton("Tìm Kiếm");
        btnTimKiem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });
        btnTimKiem.setBounds(361, 16, 97, 21);
        pnlDanhSach.add(btnTimKiem);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 61, 471, 307);
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
        String[] columns = {"Mã Thể Loại", "Tên Thể Loại"};
        Object[][] rows = {};
        model = new DefaultTableModel(rows, columns);
        table.setModel(model);

        JPanel pnlButton2 = new JPanel();
        pnlButton2.setBounds(67, 415, 350, 30);
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
        pnlButton1.setBounds(67, 344, 350, 30);
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
        btnPrevList.setBounds(645, 487, 85, 21);
        add(btnPrevList);

        btnNextList = new JButton("Next");
        btnNextList.setBounds(849, 487, 85, 21);
        btnNextList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                indexTrang++;

                if (indexTrang > (Math.ceil(tld.selectAll().size() * 1.0 / 5))) {
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
        lblIndexTrang.setBounds(785, 491, 39, 13);
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

    void load(int soTrang) {
        SwingWorker<List<TheLoai>, Void> worker = new SwingWorker<List<TheLoai>, Void>() {
            @Override
            protected List<TheLoai> doInBackground() throws Exception {
                return tld.loadTrang((soTrang - 1) * 5, 5);
            }

            @Override
            protected void done() {
                try {
                    List<TheLoai> list = get();
                    model.setRowCount(0);
                    for (TheLoai tl : list) {
                        Object[] row = {tl.getMaTL(), tl.getTenTL()};
                        model.addRow(row);
                    }
                } catch (Exception e) {
                    DialogHelper.alert(TheLoaiJPanel.this, "Lỗi truy vấn dữ liệu!");
                }
            }
        };

        worker.execute();
    }

    void insert() {
        try {
            TheLoai tl = getForm();
            if (tl != null) {
                tld.insert(tl);
                load(indexTrang);
                clear();
                DialogHelper.alert(this, "Insert Successful");
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Insert Failed");
        }

    }

    void delete() {
        try {
            if (DialogHelper.confirm(this, "Bạn có chắc chắn muốn xóa không ?")) {
                TheLoai tl = getForm();
                tld.delete(tl.getMaTL());
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
                TheLoai tl = getForm();
                tld.update(tl);
                load(indexTrang);
                clear();
                DialogHelper.alert(this, "Update Successful");
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Update Failed");
        }
    }

    void clear() {
        txtMaTheLoai.setText("");
        txtTenTheLoai.setText("");
        txtMaTheLoai.setEditable(true);
        setStatus(true);
    }

    void setForm(TheLoai tl) {
        txtMaTheLoai.setText(tl.getMaTL());
        txtTenTheLoai.setText(tl.getTenTL());
    }

    TheLoai getForm() {
        TheLoai tl = new TheLoai();
        if (txtMaTheLoai.getText().isEmpty()) {
            DialogHelper.alert(this, "Không để trống mã the loai");
            return tl = null;
        } else {
            tl.setMaTL(txtMaTheLoai.getText());
        }

        if (txtTenTheLoai.getText().isEmpty()) {
            DialogHelper.alert(this, "Không để trống họ tên the loai");
            return null;
        } else {
            tl.setTenTL(txtTenTheLoai.getText());
        }
        return tl;
    }

    void edit() {
        try {
            String maTL = (String) table.getValueAt(this.index, 0);
            TheLoai tl = tld.selectById(maTL);
            if (tl != null) {
                setForm(tl);
                setStatus(false);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void setStatus(boolean insertable) {
        txtMaTheLoai.setEditable(insertable);
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

    void search() {
        String keyword = txtTimKiem.getText().trim();
        // Thực hiện tìm kiếm trong một SwingWorker
        SwingWorker<List<TheLoai>, Void> worker = new SwingWorker<List<TheLoai>, Void>() {
            @Override
            protected List<TheLoai> doInBackground() throws Exception {
                return tld.selectByKeyword(keyword);
            }

            @Override
            protected void done() {
                try {
                    List<TheLoai> list = get();
                    if (list.size() == 0) {
                        DialogHelper.alert(null, "Không tồn tại ");
                    } else {
                        model.setRowCount(0);
                        for (TheLoai tl : list) {
                            Object[] row = {tl.getMaTL(), tl.getTenTL()};
                            model.addRow(row);
                        }
                    }

                } catch (Exception e) {
                    DialogHelper.alert(TheLoaiJPanel.this, "Lỗi truy vấn dữ liệu!");
                }
            }
        };

        worker.execute();
    }
}
