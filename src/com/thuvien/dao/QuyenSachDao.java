package com.thuvien.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thuvien.entity.QuyenSach;
import com.thuvien.utils.JDBCHelper;

public class QuyenSachDao extends QLTVDao<QuyenSach, String> {
	TaiBanDao tbd = new TaiBanDao();
	ViTriDao vtd = new ViTriDao();

	@Override
	public void insert(QuyenSach entity) {
		String sql = "INSERT INTO QuyenSach\r\n" + "                  (MaQS, TenQS,IDTaiBan, TinhTrang , GhiChu)\r\n"
				+ "VALUES (?,?,?,?,?)";
		JDBCHelper.executeUpdate(sql, entity.getMaQS(), entity.getTenQS(), entity.getIdTaiBan(), entity.getTinhTrang(),
				entity.getGhiChu());
	}

	@Override
	public void update(QuyenSach entity) {
		String sql = "UPDATE QuyenSach SET TenQS =?, IDTaiBan =?, TinhTrang =?, GhiChu =?  where MaQS =?";
		JDBCHelper.executeUpdate(sql, entity.getTenQS(), entity.getIdTaiBan(), entity.getTinhTrang(),
				entity.getGhiChu(), entity.getMaQS());
	}

	@Override
	public void delete(String key) {
		String sql = "DELETE FROM QuyenSach where MaQS =?";
		JDBCHelper.executeUpdate(sql, key);

	}

	@Override
	public List<QuyenSach> selectAll() {
		String sql = "SELECT * FROM  QuyenSach";
		return select(sql);
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

	public QuyenSach selectById2(int key) {
		String sql = "select * from QuyenSach where ID =?";
		List<QuyenSach> list = select(sql, key);
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<QuyenSach> sachMuonDuoc() {
		String sql = "SELECT *\r\n" + "FROM QuyenSach qs\r\n" + "WHERE qs.TinhTrang IN (1, 2)\r\n"
				+ "AND qs.ID NOT IN (\r\n" + "    SELECT DISTINCT qs.ID\r\n" + "    FROM QuyenSach qs\r\n"
				+ "    JOIN PhieuMuonCT pmct ON qs.ID = pmct.IDQuyenSach\r\n"
				+ "    LEFT JOIN PhieuTraCT ptct ON pmct.ID = ptct.IDPhieuMuonCT\r\n"
				+ "    WHERE ptct.TinhTrang = 0 OR ptct.TinhTrang IS NULL\r\n" + ");";
		return select(sql);
	}

	public List<QuyenSach> loadTrang(int indexTrang, int limit) {
		String sql = "SELECT * FROM QuyenSach order by ID DESC offset ? rows fetch next ? rows only ";
		List<QuyenSach> list = new ArrayList<>();
		return list = select(sql, indexTrang, limit);
	}

	public List<QuyenSach> loadTrangCBX(int indexTrang, int limit) {
		String sql = "SELECT * FROM QuyenSach order by ID DESC offset ? rows fetch next ? rows only ";
		List<QuyenSach> list = new ArrayList<>();
		return list = select(sql, indexTrang, limit);
	}

	private List<QuyenSach> select(String sql, Object... args) {
		List<QuyenSach> list = new ArrayList<>();
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, args);
				while (rs.next()) {
					QuyenSach model = readFromResultSet(rs);
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

	private QuyenSach readFromResultSet(ResultSet rs) throws SQLException {
		QuyenSach qs = new QuyenSach();
		qs.setId(rs.getInt("ID"));
		qs.setMaQS(rs.getString("MaQS"));
		qs.setTenQS(rs.getString("TenQS"));
//		int id = rs.getInt("IDTaiBan");
//		TaiBan tb = tbd.selectById(id);
		qs.setIdTaiBan(rs.getInt("IDTaiBan"));
		qs.setTinhTrang(rs.getInt("TinhTrang"));
		qs.setGhiChu(rs.getString("GhiChu"));
		return qs;
	}
}
