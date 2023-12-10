package com.thuvien.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thuvien.entity.TacGia;
import com.thuvien.entity.TacGiaChiTiet;
import com.thuvien.utils.JDBCHelper;

public class TacGiaChiTietDao extends QLTVDao<TacGiaChiTiet, Integer> {

	@Override
	public void insert(TacGiaChiTiet entity) {
		String sql = "insert into TacGiaSach(IDSach,IDTacGia,VaiTro) values(?,?,?)";
		JDBCHelper.executeUpdate(sql, entity.getIdSach(), entity.getIdTacGia(), entity.getVaiTro());
	}

	@Override
	public void update(TacGiaChiTiet entity) {
		String sql = "Update TacGiaSach set IDsach = ? , IDTacGia = ?, VaiTro =? Where ID = ?";
		JDBCHelper.executeUpdate(sql, entity.getIdSach(), entity.getIdTacGia(), entity.getVaiTro(), entity.getId());
	}

	@Override
	public void delete(Integer key) {
		String sql = "delete from TacGiaSach where ID =?";
		JDBCHelper.executeUpdate(sql, key);
	}

	@Override
	public List<TacGiaChiTiet> selectAll() {
		String sql = "select * from TacGiaSach";
		return select(sql);
	}

	public List<TacGiaChiTiet> loadTrang(int indexTrang, int limit) {
		String sql = "select * from TacGiaSach order by ID offset ? rows fetch next ? rows only ";
		return select(sql, indexTrang, limit);
	}

	@Override
	public TacGiaChiTiet selectById(Integer key) {
		String sql = "select * from TacGiaSach where id = ?";
		List<TacGiaChiTiet> list = select(sql, key);
		return list.size() > 0 ? list.get(0) : null;
	}

	private TacGiaChiTiet readFromResultSet(ResultSet rs) throws SQLException {
		TacGiaChiTiet model = new TacGiaChiTiet();
		model.setId(rs.getInt("ID"));
		model.setIdSach(rs.getInt("IDSach"));
		model.setIdTacGia(rs.getInt("IDTacGia"));
		model.setVaiTro(rs.getString("VaiTro"));
		return model;
	}

	private List<TacGiaChiTiet> select(String sql, Object... args) {
		List<TacGiaChiTiet> list = new ArrayList<>();
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, args);
				while (rs.next()) {
					TacGiaChiTiet model = readFromResultSet(rs);
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
