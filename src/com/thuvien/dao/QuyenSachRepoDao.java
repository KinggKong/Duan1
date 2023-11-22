//package com.thuvien.dao;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.thuvien.entity.QuyenSach;
//import com.thuvien.entity.ViTri;
//import com.thuvien.repo.QuyenSachRepo;
//import com.thuvien.utils.JDBCHelper;
//
//public class QuyenSachRepoDao extends QLTVDao<QuyenSachRepo, String> {
//	ViTriDao vtd = new ViTriDao();
//
//	@Override
//	public void insert(QuyenSachRepo tg) {
//		String sql = "INSERT INTO TacGia(MaTG,TenTG,QuocTich,GioiTinh) VALUES(?, ?, ?,?)";
//
//	}
//
//	@Override
//	public void update(QuyenSachRepo tg) {
//		String sql = "UPDATE TacGia SET TenTG = ?, QuocTich =? ,GioiTinh =? where MaTG = ?";
//
//	}
//
//	@Override
//	public void delete(String maTG) {
//		String sql = "DELETE FROM TacGia where MaTG = ?";
//		JDBCHelper.executeUpdate(sql, maTG);
//	}
//
//	@Override
//	public List<QuyenSachRepo> selectAll() {
//		String sql = "select QS.MaQS,S.TenSach,VT.Day,TB.LanTB, NXB.TenNXB,QS.GhiChu,QS.TinhTrang from QuyenSach QS\r\n"
//				+ "inner join TaiBan TB on QS.IDTaiBan = TB.ID\r\n" + "inner join Sach S on S.ID = TB.IDSach\r\n"
//				+ "inner join ViTri VT on QS.IDViTri = VT.ID\r\n" + "inner join NhaXuatBan NXB on S.IDNXB = NXB.ID";
//		return select(sql);
//	}
//
//	@Override
//	public QuyenSachRepo selectById(String maTG) {
//		String sql = "Select * from QuyenSach where MaTG = ?";
//		QuyenSachRepo tg = null;
//		try {
//			ResultSet rs = null;
//			try {
//				rs = JDBCHelper.executeQuery(sql, maTG);
//				while (rs.next()) {
//					tg = readFromResultSet(rs);
//
//				}
//			} finally {
//				rs.getStatement().getConnection().close();
//			}
//		} catch (SQLException ex) {
//			throw new RuntimeException(ex);
//		}
//		return tg;
//	}
//
//	public List<QuyenSachRepo> loadTrang(int indexTrang, int limit) {
//		String sql = "select QS.MaQS,S.TenSach,VT.Day,TB.LanTB, NXB.TenNXB,QS.GhiChu,QS.TinhTrang from QuyenSach QS\r\n"
//				+ "inner join TaiBan TB on QS.IDTaiBan = TB.ID\r\n" + "inner join Sach S on S.ID = TB.IDSach\r\n"
//				+ "inner join ViTri VT on QS.IDViTri = VT.ID\r\n"
//				+ "inner join NhaXuatBan NXB on S.IDNXB = NXB.ID order by QS.MaQS offset ? rows fetch next ? rows only ";
//		List<QuyenSachRepo> list = new ArrayList<>();
//		return list = select(sql, indexTrang, limit);
//	}
//
//	private QuyenSachRepo readFromResultSet(ResultSet rs) throws SQLException {
//		QuyenSachRepo model = new QuyenSachRepo();
//		model.setMaQS(rs.getString("MaQS"));
//		model.setTenSach(rs.getString("TenSach"));
//		model.setday(rs.getString("Day"));
//		model.setLanTB(rs.getInt("LanTB"));
//		model.setTenNXB(rs.getString("TenNXB"));
//		model.setGhiChu(rs.getString("GhiChu"));
//		model.setTinhTrang(rs.getInt("TinhTrang"));
//		return model;
//	}
//
//	private List<QuyenSachRepo> select(String sql, Object... args) {
//		List<QuyenSachRepo> list = new ArrayList<>();
//		try {
//			ResultSet rs = null;
//			try {
//				rs = JDBCHelper.executeQuery(sql, args);
//				while (rs.next()) {
//					QuyenSachRepo model = readFromResultSet(rs);
//					list.add(model);
//				}
//			} finally {
//				rs.getStatement().getConnection().close();
//			}
//		} catch (SQLException ex) {
//			throw new RuntimeException(ex);
//		}
//		return list;
//	}
//
//	public List<QuyenSachRepo> selectByKeyword(String keyword) {
//		String sql = "SELECT * FROM TacGia WHERE MaTG LIKE ? or TenTG LIKE ?";
//		return select(sql, "%" + keyword + "%", "%" + keyword + "%");
//	}
//}
