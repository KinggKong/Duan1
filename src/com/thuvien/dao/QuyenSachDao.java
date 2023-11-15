package com.thuvien.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thuvien.entity.QuyenSach;
import com.thuvien.entity.TacGia;
import com.thuvien.utils.JDBCHelper;

public class QuyenSachDao extends QLTVDao<QuyenSach, String> {

	@Override
	public void insert(QuyenSach tg) {
		String sql = "INSERT INTO TacGia(MaTG,TenTG,QuocTich,GioiTinh) VALUES(?, ?, ?,?)";

	}

	@Override
	public void update(QuyenSach tg) {
		String sql = "UPDATE TacGia SET TenTG = ?, QuocTich =? ,GioiTinh =? where MaTG = ?";

	}

	@Override
	public void delete(String maTG) {
		String sql = "DELETE FROM TacGia where MaTG = ?";
		JDBCHelper.executeUpdate(sql, maTG);
	}

	@Override
	public List<QuyenSach> selectAll() {
		String sql = "SELECT * FROM QuyenSach";
		return select(sql);
	}

	@Override
	public QuyenSach selectById(String maTG) {
		String sql = "Select * from QuyenSach where MaTG = ?";
		QuyenSach tg = null;
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

	public List<QuyenSach> loadTrang(int indexTrang, int limit) {
		String sql = "select QS.MaQS,S.TenSach,VT.DaySo,TB.LanTB,TB.NgayTB,S.NamXB, NXB.TenNXB,QS.GhiChu,QS.TinhTrang from QuyenSach QS\r\n"
				+ "inner join TaiBan TB on QS.IDTaiBan = TB.ID\r\n" + "inner join Sach S on S.ID = TB.IDSach\r\n"
				+ "inner join ViTri VT on QS.IDViTri = VT.ID\r\n"
				+ "inner join NhaXuatBan NXB on S.IDNXB = NXB.ID order by QS.MaQS offset ? rows fetch next ? rows only ";
		List<QuyenSach> list = new ArrayList<>();
		return list = select(sql, indexTrang, limit);
	}

	private QuyenSach readFromResultSet(ResultSet rs) throws SQLException {
		QuyenSach model = new QuyenSach();
		model.setMaQS(rs.getString(1));
		model.setTenSach(rs.getString(2));
		model.setDaySo(rs.getInt(3));
		model.setLanTB(rs.getInt(4));
		model.setNgayTB(rs.getDate(5));
		model.setNamXB(rs.getInt(6));
		model.setGhiChu(rs.getString(7));
		model.setTinhTrang(rs.getInt(8));
		return model;
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

	public List<QuyenSach> selectByKeyword(String keyword) {
		String sql = "SELECT * FROM TacGia WHERE MaTG LIKE ? or TenTG LIKE ?";
		return select(sql, "%" + keyword + "%", "%" + keyword + "%");
	}
}
