package com.thuvien.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thuvien.entity.TacGiaChiTiet;
import com.thuvien.entity.TheLoaiCT;
import com.thuvien.utils.JDBCHelper;

public class TheLoaiChiTietDao extends QLTVDao<TheLoaiCT, Integer> {

	@Override
	public void insert(TheLoaiCT entity) {
		String sql = "Insert into TheLoaiSach(IdTheLoai,IDSach) values (?,?)";
		JDBCHelper.executeUpdate(sql, entity.getIdTheLoai(), entity.getIdSach());
	}

	@Override
	public void update(TheLoaiCT entity) {
		String sql = "Update TheLoaiSach set IdSach =? , IDTheLoai =? where ID =?";
		JDBCHelper.executeUpdate(sql, entity.getIdSach(), entity.getIdTheLoai(), entity.getId());
	}

	@Override
	public void delete(Integer key) {
		String sql = "delete from TheLoaiSach where Id = ?";
		JDBCHelper.executeUpdate(sql, key);
	}

	@Override
	public List<TheLoaiCT> selectAll() {
		String sql = "select * from TheLoaiSach";
		return select(sql);
	}

	@Override
	public TheLoaiCT selectById(Integer key) {
		String sql = "select * from TheLoaiSach where id = ?";
		List<TheLoaiCT> list = select(sql, key);
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<TheLoaiCT> loadTrang(int indexTrang, int limit) {
		String sql = "select * from TheLoaiSach order by ID offset ? rows fetch next ? rows only ";
		return select(sql, indexTrang, limit);
	}

	private TheLoaiCT readFromResultSet(ResultSet rs) throws SQLException {
		TheLoaiCT model = new TheLoaiCT();
		model.setId(rs.getInt("ID"));
		model.setIdSach(rs.getInt("IDSach"));
		model.setIdTheLoai(rs.getInt("IDTheLoai"));
		return model;
	}

	private List<TheLoaiCT> select(String sql, Object... args) {
		List<TheLoaiCT> list = new ArrayList<>();
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, args);
				while (rs.next()) {
					TheLoaiCT model = readFromResultSet(rs);
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
}
