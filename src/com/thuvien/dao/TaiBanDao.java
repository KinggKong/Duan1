package com.thuvien.dao;

import com.thuvien.entity.TacGia;
import java.util.ArrayList;
import java.util.List;

import com.thuvien.entity.TaiBan;
import com.thuvien.utils.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiBanDao extends QLTVDao<TaiBan, String> {

	@Override
	public void insert(TaiBan model) {
		String sql = "insert into TaiBan(maSach,lanTB,thoiGian,trangThai) values(?,?,?,?)";
		JDBCHelper.executeUpdate(sql, model.getMaSach(), model.getLanTaiBan(), model.getThoiGianTB(),
				model.isTrangThai());

	}

	@Override
	public void update(TaiBan model) {
		String sql = "update TaiBan set lanTB = ?, thoiGian=?,trangThai=? where maSach = ?";
		JDBCHelper.executeUpdate(sql, model.getLanTaiBan(), model.getThoiGianTB(), model.isTrangThai(),
				model.getMaSach());

	}

	@Override
	public void delete(String key) {
		String sql = "delete from TaiBan where maSach = ?";
		JDBCHelper.executeUpdate(sql, key);

	}

	@Override
	public List<TaiBan> selectAll() {
		String sql="select * from TaiBan";
		return selectAll();
	}

	@Override
        public TaiBan selectById(String maTG) {
        String sql = "Select * from TaiBan where MaTG = ?";
        TaiBan tg = null;
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, maTG);
                while (rs.next()) {
                    tg = readFromResultSet(rs);

                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return tg;
    }

    public List<TaiBan> loadTrang(int indexTrang, int limit) {
        String sql = "select * from TaiBan order by ID offset ? rows fetch next ? rows only ";
        List<TaiBan> list = new ArrayList<>();
        return list = select(sql, indexTrang, limit);
    }

    private TaiBan readFromResultSet(ResultSet rs) throws SQLException {
        TaiBan model = new TaiBan();
        model.setLanTaiBan(rs.getInt("MaTG"));
        model.setMaSach(rs.getString("TenTG"));
        model.setThoiGianTB(rs.getDate("QuocTich"));
        model.setTrangThai(rs.getBoolean("GioiTinh"));
        return model;
    }

    private List<TaiBan> select(String sql, Object... args) {
        List<TaiBan> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    TaiBan model = readFromResultSet(rs);
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

    public List<TaiBan> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM TacGia WHERE MaTG LIKE ? or TenTG LIKE ?";
        return select(sql, "%" + keyword + "%", "%" + keyword + "%");
    }

}
