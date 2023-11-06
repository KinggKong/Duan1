package com.thuvien.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thuvien.entity.NhanVien;
import com.thuvien.utils.JDBCHelper;

public class NhanVienDao extends QLTVDao<NhanVien, String> {

	@Override
	public void insert(NhanVien entity) {

	}

	@Override
	public void update(NhanVien entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String key) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<NhanVien> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NhanVien selectById(String key) {
		NhanVien nv = new NhanVien();
		String sql = "select * from NhanVien where MaNV =?";
		List<NhanVien> list = select(sql, key);
		return list.size() > 0 ? list.get(0) : null;

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
		model.setTenNV(rs.getString(1));
		model.setMaNV(rs.getString(2));
		model.setSdt(rs.getString(3));
		model.setUserName(rs.getString(4));
		model.setPassWord(rs.getString(5));
		model.setVaiTro(rs.getBoolean(6));
		model.setTrangThai(rs.getBoolean(7));
		return model;
	}
}
