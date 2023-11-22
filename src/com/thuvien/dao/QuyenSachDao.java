package com.thuvien.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thuvien.entity.QuyenSach;
import com.thuvien.entity.TaiBan;
import com.thuvien.utils.JDBCHelper;

public class QuyenSachDao extends QLTVDao<QuyenSach, String> {
	TaiBanDao tbd = new TaiBanDao();
	ViTriDao vtd = new ViTriDao();

	@Override
	public void insert(QuyenSach entity) {
		String sql = "INSERT INTO QuyenSach\r\n"
				+ "                  (MaQS, TenQS, IDViTri, TinhTrang, IDTaiBan, GhiChu)\r\n" + "VALUES (?,?,?,?,?,?)";
		JDBCHelper.executeUpdate(sql, entity.getMaQS(), entity.getTenQS(), entity.getIdViTri().getID(),
				entity.getTinhTrang(), entity.getIdTaiBan().getId(), entity.getGhiChu());
	}

	@Override
	public void update(QuyenSach entity) {
		String sql = "UPDATE QuyenSach SET TenQS =?, IDViTri =?, IDTaiBan =?, TinhTrang =?, GhiChu =?  where MaQS =?";
		JDBCHelper.executeUpdate(sql, entity.getTenQS(), entity.getIdViTri().getID(),
				entity.getIdTaiBan().getLanTaiBan(), entity.getTinhTrang(), entity.getGhiChu(), entity.getMaQS());
	}

	@Override
	public void delete(String key) {
		String sql = "DELETE FROM QuyenSach where MaQS =?";
		JDBCHelper.executeUpdate(sql, key);

	}

	@Override
	public List<QuyenSach> selectAll() {
		String sql = "SELECT MaQS, TenQS, IDViTri, IDTaiBan,GhiChu, TinhTrang\r\n" + "FROM  QuyenSach";
		return select(sql);
	}

	@Override
	public QuyenSach selectById(String key) {
		String sql = "Select MaQS, TenQS, IDViTri, IDTaiBan,GhiChu, TinhTrang from QuyenSach where MaQS = ?";
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

	public List<QuyenSach> loadTrang(int indexTrang, int limit) {
		String sql = "SELECT MaQS, TenQS, IDTaiBan, IDViTri, TinhTrang,GhiChu FROM QuyenSach order by MaQS offset ? rows fetch next ? rows only ";
		List<QuyenSach> list = new ArrayList<>();
		return list = select(sql, indexTrang, limit);
	}

	public List<QuyenSach> loadTrangCBX(int indexTrang, int limit) {
		String sql = "SELECT MaQS, TenQS, IDTaiBan, IDViTri, TinhTrang,GhiChu FROM QuyenSach order by MaQS offset ? rows fetch next ? rows only ";
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
		qs.setMaQS(rs.getString("MaQS"));
		qs.setTenQS(rs.getString("TenQS"));
		int viTri = rs.getInt("IDViTri");
		qs.setIdViTri(vtd.selectById(viTri));
		int id = rs.getInt("IDTaiBan");
		TaiBan tb = tbd.selectById(id);
		qs.setIdTaiBan(tb);
		qs.setTinhTrang(rs.getInt("TinhTrang"));
		qs.setGhiChu(rs.getString("GhiChu"));
		return qs;
	}
}
