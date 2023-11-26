package com.thuvien.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thuvien.entity.QuyenSach;
import com.thuvien.entity.QuyenSachTim;

import com.thuvien.utils.JDBCHelper;

public class QuyenSachTimDao extends QLTVDao<QuyenSachTim, String> {
	QuyenSachDao qsd = new QuyenSachDao();

	@Override
	public void insert(QuyenSachTim entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(QuyenSachTim entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String key) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<QuyenSachTim> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuyenSachTim selectById(String key) {
		QuyenSachTim nv = new QuyenSachTim();
		String sql = "WITH  ViTriCTE AS (\r\n" + "    SELECT\r\n" + "        v.ID,\r\n" + "        v.TenVT,\r\n"
				+ "        v.Parent_ID,\r\n" + "        v.MaQuyenSach\r\n" + "    FROM\r\n"
				+ "        ViTriQuyenSach v\r\n" + "    WHERE\r\n"
				+ "        v.MaQuyenSach = ? -- IDQuyenSach của quyển sách cụ thể\r\n" + "    UNION ALL\r\n"
				+ "    SELECT\r\n" + "        v.ID,\r\n" + "        v.TenVT,\r\n" + "        v.Parent_ID,\r\n"
				+ "        v.MaQuyenSach\r\n" + "    FROM\r\n" + "        ViTriQuyenSach v\r\n" + "    JOIN\r\n"
				+ "        ViTriCTE c ON v.ID = c.Parent_ID\r\n" + ")\r\n" + "SELECT\r\n"
				+ "    ViTriCTE.MaQuyenSach,\r\n" + "    QuyenSach.TenQS,\r\n" + "    ViTriCTE.TenVT AS TenO,\r\n"
				+ "    DVT1.TenVT AS TenNgan,\r\n" + "    DVT2.TenVT AS TenDay\r\n" + "FROM\r\n" + "    ViTriCTE\r\n"
				+ "JOIN\r\n" + "    QuyenSach ON ViTriCTE.MaQuyenSach = QuyenSach.MaQS\r\n" + "LEFT JOIN\r\n"
				+ "    ViTriQuyenSach AS DVT1 ON ViTriCTE.Parent_ID = DVT1.ID\r\n" + "LEFT JOIN\r\n"
				+ "    ViTriQuyenSach AS DVT2 ON DVT1.Parent_ID = DVT2.ID\r\n" + "WHERE\r\n"
				+ "    ViTriCTE.Parent_ID IS NOT NULL;";
		List<QuyenSachTim> list = select(sql, key);
		return list.size() > 0 ? list.get(0) : null;
	}

	private List<QuyenSachTim> select(String sql, Object... args) {
		List<QuyenSachTim> list = new ArrayList<>();
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, args);
				while (rs.next()) {
					QuyenSachTim model = readFromResultSet(rs);
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

	private QuyenSachTim readFromResultSet(ResultSet rs) throws SQLException {
		QuyenSachTim qst = new QuyenSachTim();
		QuyenSach qs = qsd.selectById(rs.getString("MaQuyenSach"));
		qst.setMaQuyenSacch(qs);
		qst.setTenSach(qs);
		qst.setDaySo(rs.getString("TenDay"));
		qst.setNganSo(rs.getString("TenNgan"));
		qst.setSoViTri(rs.getString("TenO"));
		return qst;
	}
}
