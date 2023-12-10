package com.thuvien.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thuvien.entity.HangThanhVien;
import com.thuvien.utils.JDBCHelper;

public class HangTheThanhVienDao extends QLTVDao<HangThanhVien, Integer> {

	@Override
	public void insert(HangThanhVien entity) {
		String sql = "insert into HangThanhVien(MaHTV,TenHang,DonGia,PhiThueSach,SoThangHieuLuc,TuoiMin,TuoiMax) values(?,?,?,?,?,?,?)";
		JDBCHelper.executeUpdate(sql, entity.getMaHTV(), entity.getTenHang(), entity.getDonGia(),
				entity.getPhiThueSach(), entity.getSoThangHieuLuc(), entity.getTuoiMin(), entity.getTuoiMax());
	}

	@Override
	public void update(HangThanhVien entity) {
		String sql = "update HangThanhVien set TenHang = ?, DonGia =?,PhiThueSach =?, SoThangHieuLuc =?, TuoiMin =?, TuoiMax =? where MaHTV = ?";
		JDBCHelper.executeUpdate(sql, entity.getTenHang(), entity.getDonGia(), entity.getPhiThueSach(),
				entity.getSoThangHieuLuc(), entity.getTuoiMin(), entity.getTuoiMax(), entity.getMaHTV());
	}

	@Override
	public void delete(Integer key) {
		String sql = "delete from HangThanhVien where id=?";
		JDBCHelper.executeUpdate(sql, key);
	}

	@Override
	public List<HangThanhVien> selectAll() {
		String sql = "select * from HangThanhVien ";
		return select(sql);
	}

	@Override
	public HangThanhVien selectById(Integer key) {
		String sql = "Select * from HangThanhVIen where iD = ?";
		HangThanhVien tg = null;
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, key);
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

	public HangThanhVien selectById2(String key) {
		String sql = "Select * from HangThanhVIen where MaHTV = ?";
		HangThanhVien tg = null;
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, key);
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

	public List<HangThanhVien> loadTrang(int indexTrang, int limit) {
		String sql = "select * from HangThanhVien order by ID offset ? rows fetch next ? rows only ";
		List<HangThanhVien> list = new ArrayList<>();
		return list = select(sql, indexTrang, limit);
	}

	private HangThanhVien readFromResultSet(ResultSet rs) throws SQLException {
		HangThanhVien model = new HangThanhVien();
		model.setId(rs.getInt(1));
		model.setMaHTV(rs.getString(2));
		model.setTenHang(rs.getString(3));
		model.setDonGia(rs.getFloat(4));
		model.setPhiThueSach(rs.getFloat(5));
		model.setSoThangHieuLuc(rs.getInt(6));
		model.setTuoiMin(rs.getInt(7));
		model.setTuoiMax(rs.getInt(8));
		return model;
	}

	private List<HangThanhVien> select(String sql, Object... args) {
		List<HangThanhVien> list = new ArrayList<>();
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, args);
				while (rs.next()) {
					HangThanhVien model = readFromResultSet(rs);
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

	public List<HangThanhVien> selectByKeyword(String keyword) {
		String sql = "SELECT * FROM HangThanhVien WHERE MaTG LIKE ? or TenTG LIKE ?";
		return select(sql, "%" + keyword + "%", "%" + keyword + "%");
	}

}
