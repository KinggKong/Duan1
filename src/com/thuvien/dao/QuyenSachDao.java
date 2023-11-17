package com.thuvien.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.thuvien.entity.QuyenSach;
import com.thuvien.repo.QuyenSachRepo;
import com.thuvien.utils.JDBCHelper;

public class QuyenSachDao extends QLTVDao<QuyenSach, String> {

	@Override
	public void insert(QuyenSach entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(QuyenSach entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String key) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<QuyenSach> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuyenSach selectById(String key) {
		String sql = "Select * from QuyenSach where MaQS = ?";
		QuyenSach quyenSach = null;
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, key);
				while (rs.next()) {
					quyenSach = readFromResultSet(rs);

				}
			} finally {
				rs.getStatement().getConnection().close();
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return quyenSach;
	}

	private QuyenSach readFromResultSet(ResultSet rs) throws SQLException {
		QuyenSach qs = new QuyenSach();
		qs.setMaQS(rs.getString("MaQS"));
		qs.setIdViTri(rs.getInt("IDViTri"));
		qs.setIdTaiBan(rs.getInt("IDTaiBan"));
		qs.setTinhTrang(rs.getInt("TinhTrang"));
		qs.setGhiChu(rs.getString("GhiChu"));
		return qs;
	}
}
