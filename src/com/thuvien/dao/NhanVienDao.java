package com.thuvien.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thuvien.entity.NhanVien;
import com.thuvien.entity.TacGia;
import com.thuvien.utils.JDBCHelper;

public class NhanVienDao extends QLTVDao<NhanVien, String> {

    @Override
    public void insert(NhanVien entity) {
        String sql = "insert into NhanVien values(?,?,?,?,?,?,?)";
        JDBCHelper.executeUpdate(sql, entity.getMaNV(), entity.getTenNV(), entity.getSdt(), entity.getUserName(), entity.getPassWord(),
                entity.isVaiTro(), entity.isTrangThai());
    }

    @Override
    public void update(NhanVien entity) {
        String sql = "UPDATE NhanVien SET TenNV =? ,SDT =?,Username = ?,PassWord=?,vaitro=?,Trangthai=? where MaNV= ?";
        JDBCHelper.executeUpdate(sql, entity.getTenNV(), entity.getSdt(), entity.getUserName(), entity.getPassWord(),
                entity.isVaiTro(), entity.isTrangThai(),entity.getMaNV());

    }

    @Override
    public void delete(String key) {
        String sql = "DELETE FROM NhanVien where MaNV = ?";
        JDBCHelper.executeUpdate(sql, key);

    }

    @Override
    public List<NhanVien> selectAll() {
        String sql = "SELECT * FROM NhanVien";
        return select(sql);
    }

    @Override
    public NhanVien selectById(String key) {
        NhanVien nv = new NhanVien();
        String sql = "select * from NhanVien where MaNV =?";
        List<NhanVien> list = select(sql, key);
        return list.size() > 0 ? list.get(0) : null;

    }

    public List<NhanVien> loadTrang(int indexTrang, int limit) {
        String sql = "select * from NhanVien order by ID offset ? rows fetch next ? rows only ";
        List<NhanVien> list = new ArrayList<>();
        return list = select(sql, indexTrang, limit);
    }

    private List<NhanVien> select(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    NhanVien model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private NhanVien readFromResultSet(ResultSet rs) throws SQLException {
        NhanVien model = new NhanVien();

        model.setMaNV(rs.getString("MaNV"));
        
        model.setTenNV(rs.getString("TenNV"));
        model.setSdt(rs.getString("SDT"));
        
        model.setUserName(rs.getString("UserName"));
        model.setPassWord(rs.getString("PassWord"));
        model.setVaiTro(rs.getBoolean("VaiTro"));
        model.setTrangThai(rs.getBoolean("trangthai"));
        return model;
    }

    public List<NhanVien> selectByKeyword(String keyword) {
       
        String sql = "SELECT * FROM NhanVien WHERE MaNV LIKE ? or TenNV LIKE ?";
        return select(sql, "%" + keyword + "%", "%" + keyword + "%");
    
    }
}
