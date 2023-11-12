package com.thuvien.ui;

import com.thuvien.dao.NXBDao;
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
import com.thuvien.entity.NXB;
import com.thuvien.entity.TacGia;
import com.thuvien.utils.DialogHelper;


public class NhaXuatBanJPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtTenNXB;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JTextField txtTimKiem;
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
    private JTextField txtIDNhaXuatBan;
    NXBDao nXBDao = new NXBDao();

    public NhaXuatBanJPanel() {
        setLayout(null);
        JLabel lblTitle = new JLabel("Quản Lý Nhà Xuất Bản");
        lblTitle.setForeground(Color.BLUE);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblTitle.setBounds(328, 10, 400, 37);
        add(lblTitle);

        JPanel pnlThongTinTG = new JPanel();
        pnlThongTinTG.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        pnlThongTinTG.setBounds(27, 96, 429, 135);
        add(pnlThongTinTG);
        pnlThongTinTG.setLayout(null);

        JLabel lblTenNXB = new JLabel("Tên Nhà Xuất Bản");
        lblTenNXB.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTenNXB.setBounds(131, 77, 206, 19);
        pnlThongTinTG.add(lblTenNXB);

        txtTenNXB = new JTextField();
        txtTenNXB.setColumns(10);
        txtTenNXB.setBounds(73, 106, 296, 19);
        pnlThongTinTG.add(txtTenNXB);

        txtIDNhaXuatBan = new JTextField();
        txtIDNhaXuatBan.setEditable(false);
        txtIDNhaXuatBan.setEnabled(false);
        txtIDNhaXuatBan.setColumns(10);
        txtIDNhaXuatBan.setBounds(73, 48, 296, 19);
        pnlThongTinTG.add(txtIDNhaXuatBan);

        JLabel lblIdNhXutt = new JLabel("ID Nhà Xuátt Bản");
        lblIdNhXutt.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblIdNhXutt.setBounds(131, 19, 206, 19);
        pnlThongTinTG.add(lblIdNhXutt);

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
        String[] columns = {"ID", "Tên Nhà Xuất Bản"};
        Object[][] rows = {};
        model = new DefaultTableModel(rows, columns);
        table.setModel(model);

        JPanel pnlButton2 = new JPanel();
        pnlButton2.setBounds(67, 335, 350, 30);
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
        pnlButton1.setBounds(67, 264, 350, 30);
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
        add(btnNextList);
        btnNextList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                indexTrang++;

                if (indexTrang > (Math.ceil(nXBDao.selectAll().size() * 1.0 / 5))) {
                    DialogHelper.alert(null, "Đây là trang cuối cùng !");
                    indexTrang--;
                } else {

                    load(indexTrang);
                    lblIndexTrang.setText(indexTrang + "");
                }
            }
        });
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
        SwingWorker<List<NXB>, Void> worker = new SwingWorker<List<NXB>, Void>() {
            @Override
            protected List<NXB> doInBackground() throws Exception {
                return nXBDao.loadTrang((soTrang - 1) * 5, 5);
            }

            @Override
            protected void done() {
                try {
                    List<NXB> list = get();
                    model.setRowCount(0);
                    for (NXB nxb : list) {
                        Object[] row = {nxb.getId(), nxb.getTenNXB()
                        };
                        model.addRow(row);
                    }
                } catch (Exception e) {
                    DialogHelper.alert(NhaXuatBanJPanel.this, "Lỗi truy vấn dữ liệu!");
                }
            }
        };

        worker.execute();
    }

    void insert() {
        try {
            NXB nxb = getInsert();
            if (nxb != null) {
                nXBDao.insert(nxb);
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
                NXB nxb = getForm();
                nXBDao.delete(nxb.getId());
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
                NXB nxb = getForm();
                nXBDao.update(nxb);
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
        txtIDNhaXuatBan.setText("");
        txtTenNXB.setText("");
        btnInsert.setEnabled(true);
    }

    void setForm(NXB Nxb) {
        txtTenNXB.setText(Nxb.getTenNXB());
        txtIDNhaXuatBan.setText(Nxb.getId() + "");
    }

    NXB getForm() {
        NXB nxb = new NXB();
        nxb.setId(Integer.parseInt(txtIDNhaXuatBan.getText()));
        if (txtTenNXB.getText().isEmpty()) {
            DialogHelper.alert(this, "Không để trống họ tên tác giả");
            return null;
        } else {
            nxb.setTenNXB(txtTenNXB.getText());
        }

        return nxb;
        
    }
      NXB getInsert(){
      NXB nxb ;
        if (txtTenNXB.getText().isEmpty()) {
            DialogHelper.alert(this, "Không để trống họ tên tác giả");
            return null;
        } else {
            nxb = new NXB(txtTenNXB.getText());
        }

        return nxb;
      }
    void edit() {
        try {
            int maNxb = (int) table.getValueAt(this.index, 0);
            NXB tg = nXBDao.selectById(maNxb);
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

    void search() {
        String keyword =txtTimKiem.getText().trim() ;
        // Thực hiện tìm kiếm trong một SwingWorker
        SwingWorker<List<NXB>, Void> worker;
        worker = new SwingWorker<List<NXB>, Void>() {
            @Override
            protected List<NXB> doInBackground() throws Exception {
                return nXBDao.selectByKeyword(keyword);
            }

            @Override
            protected void done() {
                try {
                    List<NXB> list = get();
                    if (list.size() == 0) {
                        DialogHelper.alert(null, "Không tồn tại ");
                    } else {
                        model.setRowCount(0);
                        for (NXB tg : list) {
                            Object[] row = {tg.getId(), tg.getTenNXB()
                            };
                            model.addRow(row);
                        }
                    }

                } catch (Exception e) {
                    DialogHelper.alert(NhaXuatBanJPanel.this, "Lỗi truy vấn dữ liệu!");
                }
            }
        };

        worker.execute();
    }
}
