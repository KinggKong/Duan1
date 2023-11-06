package com.thuvien.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thuvien.entity.TacGia;
import com.thuvien.utils.JDBCHelper;

public class TacGiaDao extends QLTVDao<TacGia, String> {

	@Override
	public void insert(TacGia tg) {
		String sql = "INSERT INTO TacGia(MaTG,TenTG,QuocTich,GioiTinh) VALUES(?, ?, ?,?)";
		JDBCHelper.executeUpdate(sql, tg.getMaTG(), tg.getHoTen(), tg.getQuocTich(), tg.isGioiTinh());
	}

	@Override
	public void update(TacGia tg) {
		String sql = "UPDATE TacGia SET TenTG = ?, QuocTich =? ,GioiTinh =? where MaTG = ?";
		JDBCHelper.executeUpdate(sql, tg.getHoTen(), tg.getQuocTich(), tg.isGioiTinh(), tg.getMaTG());
	}

	@Override
	public void delete(String maTG) {
		String sql = "DELETE FROM TacGia where MaTG = ?";
		JDBCHelper.executeUpdate(sql, maTG);
	}

	@Override
	public List<TacGia> selectAll() {
		String sql = "SELECT * FROM TacGia";
		return select(sql);
	}

	@Override
	public TacGia selectById(String maTG) {
		String sql = "Select * from TacGia where MaTG = ?";
		TacGia tg = null;
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

	public List<TacGia> loadTrang(int indexTrang, int limit) {
		String sql = "select * from TacGia order by ID offset ? rows fetch next ? rows only ";
		List<TacGia> list = new ArrayList<>();
		return list = select(sql, indexTrang, limit);
	}

	private TacGia readFromResultSet(ResultSet rs) throws SQLException {
		TacGia model = new TacGia();
		model.setMaTG(rs.getString("MaTG"));
		model.setHoTen(rs.getString("TenTG"));
		model.setQuocTich(rs.getString("QuocTich"));
		model.setGioiTinh(rs.getBoolean("GioiTinh"));
		return model;
	}

	private List<TacGia> select(String sql, Object... args) {
		List<TacGia> list = new ArrayList<>();
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, args);
				while (rs.next()) {
					TacGia model = readFromResultSet(rs);
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

	public List<TacGia> selectByKeyword(String keyword) {
		String sql = "SELECT * FROM TacGia WHERE MaTG LIKE ? or TenTG LIKE ?";
		return select(sql, "%" + keyword + "%", "%" + keyword + "%");
	}
}
