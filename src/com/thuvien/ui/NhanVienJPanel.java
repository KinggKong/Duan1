package com.thuvien.ui;

import com.thuvien.dao.NhanVienDao;
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
import com.thuvien.entity.NXB;
import com.thuvien.entity.NhanVien;
import com.thuvien.entity.TacGia;
import com.thuvien.utils.DialogHelper;
import com.thuvien.utils.JDBCHelper;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class NhanVienJPanel extends JPanel {

    private JTextField txtMaNV;
    private JTextField txtHoTen;
    private JTextField txtSDT;
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
    private JRadioButton rdoNhanVien;
    private JRadioButton rdoAdmin;
    private JRadioButton raCon;
    private JRadioButton raKhong;
    private JButton btnFirst;
    private JButton btnPrevEdit;
    private JButton btnNextEdit;
    private JButton btnLast;
    private JLabel lblIndexTrang;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private final ButtonGroup buttonGroup_1 = new ButtonGroup();
    NhanVienDao nvDao = new NhanVienDao();

    public NhanVienJPanel() {
        setLayout(null);
        JLabel lblTitle = new JLabel("Quản Lý Nhân Viên");
        lblTitle.setForeground(Color.BLUE);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblTitle.setBounds(534, 10, 327, 37);
        add(lblTitle);

        JPanel pnlThongTinTG = new JPanel();
        pnlThongTinTG.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        pnlThongTinTG.setBounds(72, 81, 429, 338);
        add(pnlThongTinTG);
        pnlThongTinTG.setLayout(null);

        JLabel lblMaNV = new JLabel("Mã Nhân Viên");
        lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblMaNV.setBounds(10, 16, 138, 19);
        pnlThongTinTG.add(lblMaNV);

        txtMaNV = new JTextField();
        txtMaNV.setColumns(10);
        txtMaNV.setBounds(10, 38, 296, 19);
        pnlThongTinTG.add(txtMaNV);

        JLabel lblTenNV = new JLabel("Họ Và Tên");
        lblTenNV.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTenNV.setBounds(10, 65, 104, 19);
        pnlThongTinTG.add(lblTenNV);

        txtHoTen = new JTextField();
        txtHoTen.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtHoTen.getText().equals("VD: Nguyễn Văn A...")) {
                    txtHoTen.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtHoTen.getText().isEmpty()) {
                    txtHoTen.setText("VD: Nguyễn Văn A...");
                }
            }
        });
        txtHoTen.setColumns(10);
        txtHoTen.setBounds(10, 86, 296, 19);
        txtHoTen.setText("VD: Nguyễn Văn A...");
        pnlThongTinTG.add(txtHoTen);

        JLabel lblSDT = new JLabel("Số Điện Thoại");
        lblSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblSDT.setBounds(10, 115, 186, 19);
        pnlThongTinTG.add(lblSDT);

        txtSDT = new JTextField();
        txtSDT.setColumns(10);
        txtSDT.setBounds(10, 138, 296, 19);
        pnlThongTinTG.add(txtSDT);

        JLabel lblGioiTinh = new JLabel("Giới Tính");
        lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblGioiTinh.setBounds(10, 286, 104, 19);
        pnlThongTinTG.add(lblGioiTinh);

        rdoNhanVien = new JRadioButton("Nhân Viên");
        buttonGroup.add(rdoNhanVien);
        rdoNhanVien.setSelected(true);
        rdoNhanVien.setBounds(10, 311, 104, 21);
        pnlThongTinTG.add(rdoNhanVien);

        rdoAdmin = new JRadioButton("Admin");
        buttonGroup.add(rdoAdmin);
        rdoAdmin.setBounds(115, 311, 81, 21);
        pnlThongTinTG.add(rdoAdmin);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblUsername.setBounds(10, 162, 186, 19);
        pnlThongTinTG.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setColumns(10);
        txtUsername.setBounds(10, 191, 296, 19);
        pnlThongTinTG.add(txtUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblPassword.setBounds(10, 220, 186, 19);
        pnlThongTinTG.add(lblPassword);

        txtPassword = new JTextField();
        txtPassword.setColumns(10);
        txtPassword.setBounds(10, 249, 296, 19);
        pnlThongTinTG.add(txtPassword);

        JLabel lblHoatDong = new JLabel("Hoạt Động");
        lblHoatDong.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblHoatDong.setBounds(233, 286, 104, 19);
        pnlThongTinTG.add(lblHoatDong);

        raCon = new JRadioButton("Còn");
        buttonGroup_1.add(raCon);
        raCon.setSelected(true);
        raCon.setBounds(233, 311, 65, 21);
        pnlThongTinTG.add(raCon);

        raKhong = new JRadioButton("Không");
        buttonGroup_1.add(raKhong);
        raKhong.setBounds(304, 311, 103, 21);
        pnlThongTinTG.add(raKhong);

        JPanel pnlDanhSach = new JPanel();
        pnlDanhSach.setLayout(null);
        pnlDanhSach.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                "Danh S\u00E1ch", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
        pnlDanhSach.setBounds(576, 81, 700, 378);
        add(pnlDanhSach);

        JLabel lblTimKiem = new JLabel("Tìm Kiếm");
        lblTimKiem.setBounds(10, 20, 55, 13);
        pnlDanhSach.add(lblTimKiem);

        txtTimKiem = new JTextField();
        txtTimKiem.setText("Nhập vào mã hoặc tên của nhân viên");
        txtTimKiem.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtTimKiem.getText().equals("Nhập vào mã hoặc tên của tác giả")) {
                    txtTimKiem.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtTimKiem.getText().isEmpty()) {
                    txtTimKiem.setText("Nhập vào mã hoặc tên của tác giả");
                }
            }
        });
        txtTimKiem.setBounds(86, 17, 467, 19);
        pnlDanhSach.add(txtTimKiem);
        txtTimKiem.setColumns(10);

        JButton btnTimKiem = new JButton("Tìm Kiếm");
        btnTimKiem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });
        btnTimKiem.setBounds(575, 16, 97, 21);
        pnlDanhSach.add(btnTimKiem);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 71, 680, 297);
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
        String[] columns = {"Mã NV", "Họ Tên", "SDT", "Username", "Password", "Vai Trò", "Trạng Thái"};
        Object[][] rows = {};
        model = new DefaultTableModel(rows, columns);
        table.setModel(model);

        JPanel pnlButton2 = new JPanel();
        pnlButton2.setBounds(110, 501, 350, 30);
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
        pnlButton1.setBounds(110, 448, 350, 30);
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
        btnPrevList.setBounds(761, 501, 85, 21);
        add(btnPrevList);

        btnNextList = new JButton("Next");
        btnNextList.setBounds(1047, 497, 85, 21);
        add(btnNextList);
        btnNextList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                indexTrang++;

                if (indexTrang > (Math.ceil(nvDao.selectAll().size() * 1.0 / 5))) {
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
        lblIndexTrang.setBounds(951, 505, 39, 13);
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
        setStatus(true);
    }

    void load(int soTrang) {
        SwingWorker<List<NhanVien>, Void> worker = new SwingWorker<List<NhanVien>, Void>() {
            @Override
            protected List<NhanVien> doInBackground() throws Exception {
                return nvDao.loadTrang((soTrang - 1) * 5, 5);
            }

            @Override
            protected void done() {
                try {
                    List<NhanVien> list = get();
                    model.setRowCount(0);
                    for (NhanVien nv : list) {
                        Object[] row = {nv.getMaNV(), nv.getTenNV(), nv.getSdt(), nv.getUserName(),
                            nv.getPassWord(), nv.isVaiTro() ? "admin" : "nhanvien", nv.isTrangThai() ? "Còn Hoạt Động" : "Nghỉ hoạt động"
                        };
                        model.addRow(row);
                    }
                } catch (Exception e) {
                    DialogHelper.alert(NhanVienJPanel.this, "Lỗi truy vấn dữ liệu!");
                }
            }
        };

        worker.execute();
    }

    void insert() {
        try {
            NhanVien tg = getForm();
            if (tg != null) {
                nvDao.insert(tg);
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
                NhanVien tg = getForm();
                nvDao.delete(tg.getMaNV());
                load(indexTrang);
                clear();
                DialogHelper.alert(this, "Delete Successful");
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Delete Failed");
            e.printStackTrace();
        }
    }

    void update() {
        try {
            if (DialogHelper.confirm(this, "Bạn có chắc chắn muốn Update không ?")) {
                NhanVien tg = getForm();
                nvDao.update(tg);
                load(indexTrang);
                clear();
                DialogHelper.alert(this, "Update Successful");
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Update Failed");
        }
    }

    void clear() {
        txtMaNV.setText("");
        txtHoTen.setText("");
        txtSDT.setText("");
        txtPassword.setText("");
        txtUsername.setText("");
        rdoAdmin.setSelected(true);
        raCon.setSelected(true);
        btnInsert.setEnabled(true);
        txtMaNV.setEditable(true);

    }

    void setForm(NhanVien tg) {
        txtMaNV.setText(tg.getMaNV());
        txtHoTen.setText(tg.getTenNV());
        txtSDT.setText(tg.getSdt());
        if (tg.isVaiTro()) {
            rdoAdmin.setSelected(true);
        } else {
            rdoNhanVien.setSelected(true);
        }
        if (tg.isTrangThai()) {
            raCon.setSelected(true);
        } else {
            raKhong.setSelected(true);

        }
        txtPassword.setText(tg.getPassWord());
        txtUsername.setText(tg.getUserName());

    }

    NhanVien getForm() {
        NhanVien tg = new NhanVien();
        if (txtMaNV.getText().isEmpty()) {
            DialogHelper.alert(this, "Không để trống mã NhanVien");
            return tg = null;
        } else {
            tg.setMaNV(txtMaNV.getText());
        }

        if (txtHoTen.getText().isEmpty()) {
            DialogHelper.alert(this, "Không để trống họ tên Nhân Viên");
            return null;
        } else {
            tg.setTenNV(txtHoTen.getText());
        }

        if (txtSDT.getText().isEmpty()) {

            DialogHelper.alert(this, "Không để trống SDT");
            return null;
        } else {
            try {
                Integer.parseInt(txtSDT.getText());

                if (txtSDT.getText().length() == 10 && txtSDT.getText().startsWith("0")) {
                    tg.setSdt(txtSDT.getText());
                } else {
                    DialogHelper.alert(this, "Số điện thoại không hợp lệ");
                    return null;
                }

            } catch (Exception e) {

                DialogHelper.alert(this, "số điện thoại phải là số ");
                return null;
            }

        }

        if (txtUsername.getText().isEmpty()) {
            DialogHelper.alert(this, "Không được để trống uername");
        } else {
            tg.setUserName(txtUsername.getText());
        }
        if (txtPassword.getText().isEmpty()) {
            DialogHelper.alert(this, "không được để trống pass");

        } else if (txtPassword.getText().length() < 8) {
            DialogHelper.alert(this, "pass phải từ 8 ký tự chở lên");

        } else {
            tg.setPassWord(txtPassword.getText());

        }
        tg.setVaiTro(rdoAdmin.isSelected());
        tg.setTrangThai(raCon.isSelected());
        return tg;
    }

    void edit() {
        try {
            String MaNv = (String) table.getValueAt(this.index, 0);
            NhanVien tg = nvDao.selectById(MaNv);
            if (tg != null) {
                setForm(tg);
                setStatus(false);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void setStatus(boolean insertable) {
        txtMaNV.setEditable(insertable);
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

        // Thực hiện tìm kiếm trong một SwingWorker
        SwingWorker<List<NhanVien>, Void> worker;
        worker = new SwingWorker<List<NhanVien>, Void>() {
            @Override
            protected List<NhanVien> doInBackground() throws Exception {
                return nvDao.selectByKeyword(keyword);
            }

            @Override
            protected void done() {
                try {
                    List<NhanVien> list = get();
                    if (list.size() == 0) {
                        DialogHelper.alert(null, "Không tồn tại ");
                    } else {
                        model.setRowCount(0);
                        for (NhanVien tg : list) {
                            Object[] row = {tg.getMaNV(), tg.getTenNV(), tg.getSdt(), tg.getUserName(), tg.getPassWord(),
                                tg.isVaiTro() ? "NhanVien" : "Admin", tg.isTrangThai() ? "Còn Hoạt Động" : "Nghỉ hoạt động"
                            };
                            model.addRow(row);
                        }
                    }

                } catch (Exception e) {
                    DialogHelper.alert(NhanVienJPanel.this, "Lỗi truy vấn dữ liệu!");
                }
            }
        };

        worker.execute();
    }
}
