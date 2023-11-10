package com.thuvien.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thuvien.entity.TheThanhVien;
import com.thuvien.utils.JDBCHelper;

public class TheThanhVienDao extends QLTVDao<TheThanhVien, String> {

	@Override
	public void insert(TheThanhVien entity) {
		String sql = " insert into TheThanhVien (MaTheTV,IDHangTV,NgayCap,NgayHieuLuc,TgHieuLuc,IDThanhVien,OldID,TrangThai)\r\n"
				+ " values(?,?,?,?,?,?,?,?);";
		JDBCHelper.executeUpdate(sql, entity.getMaTTV(), entity.getIdHangTV(), entity.getNgayCap(),
				entity.getNgayHieuLuc(), entity.getTgHieuLuc(), entity.getIdThanhVien(), entity.getOldID(),
				entity.isTrangThai());
	}

	@Override
	public void update(TheThanhVien entity) {
		String sql = " update  TheThanhVien  set IDHangTV=?,NgayCap=?,NgayHieuLuc=?,TgHieuLuc=?,IDThanhVien=?,OldID=?,TrangThai=? where MaTheTV =?";
		JDBCHelper.executeUpdate(sql, entity.getIdHangTV(), entity.getNgayCap(), entity.getNgayHieuLuc(),
				entity.getTgHieuLuc(), entity.getIdThanhVien(), entity.getOldID(), entity.isTrangThai(),
				entity.getMaTTV());

	}

	@Override
	public void delete(String key) {
		String sql = "delete from TheThanhVien where MaTheTV =?";
		JDBCHelper.executeUpdate(sql, key);
	}

	@Override
	public List<TheThanhVien> selectAll() {
		String sql = "select * from TheThanhVien";
		return select(sql);
	}

	@Override
	public TheThanhVien selectById(String key) {
		String sql = "select MaTheTV,IDHangTV,NgayCap,NgayHieuLuc,TgHieuLuc,IDThanhVien,OldID,TrangThai where MaTheTV =?";
		TheThanhVien ttv = null;
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, key);
				while (rs.next()) {
					ttv = readFromResultSet(rs);

				}
			} finally {
				rs.getStatement().getConnection().close();
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return ttv;
	}

	public List<TheThanhVien> loadTrang(int indexTrang, int limit) {
		String sql = " select MaTheTV,IDHangTV,NgayCap,NgayHieuLuc,TgHieuLuc,IDThanhVien,OldID,TrangThai from TheThanhVien order by MaTheTV offset ? rows fetch next ? rows only ";
		List<TheThanhVien> list = new ArrayList<>();
		return list = select(sql, indexTrang, limit);
	}

	private TheThanhVien readFromResultSet(ResultSet rs) throws SQLException {
		TheThanhVien model = new TheThanhVien();
		model.setMaTTV(rs.getString(1));
		model.setIdHangTV(rs.getInt(2));
		model.setNgayCap(rs.getDate(3));
		model.setNgayHieuLuc(rs.getDate(4));
		model.setTgHieuLuc(rs.getInt(5));
		model.setIdThanhVien(rs.getInt(6));
		model.setOldID(rs.getInt(7));
		model.setTrangThai(rs.getBoolean(8));
		return model;
	}

	private List<TheThanhVien> select(String sql, Object... args) {
		List<TheThanhVien> list = new ArrayList<>();
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, args);
				while (rs.next()) {
					TheThanhVien model = readFromResultSet(rs);
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

	public List<TheThanhVien> selectByKeyword(String keyword) {
		String sql = "SELECT * FROM TheThanhVien WHERE MaTheTV LIKE ? or  LIKE ?";
		return select(sql, "%" + keyword + "%", "%" + keyword + "%");
	}

}
