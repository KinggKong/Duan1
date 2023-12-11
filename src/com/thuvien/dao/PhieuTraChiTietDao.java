
package com.thuvien.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thuvien.entity.PhieuMuonCT;
import com.thuvien.entity.PhieuTra;
import com.thuvien.entity.PhieuTraCT;
import com.thuvien.utils.JDBCHelper;

public class PhieuTraChiTietDao extends QLTVDao<PhieuTraCT, Integer> {
	PhieuTraDao ptd = new PhieuTraDao();
	PhieuMuonChiTietDao pmctd = new PhieuMuonChiTietDao();

	@Override
	public void insert(PhieuTraCT entity) {
		String sql = "INSERT INTO PhieuTraCT\r\n"
				+ "                  (IDPhieuMuonCT, IDPhieuTra, TinhTrang, GhiChu)\r\n" + "VALUES (?,?,?,?)";
		JDBCHelper.executeUpdate(sql, entity.getIdPhieuMuonCT().getId(), entity.getIdPhieuTra().getId(),
				entity.isTinhTrangSach(), entity.getGhiChu());
	}

	@Override
	public void update(PhieuTraCT entity) {

	}

	@Override
	public void delete(Integer key) {
		String sql = "delete from PhieuTraCT where id = ?";
		JDBCHelper.executeUpdate(sql, key);
	}

	public int soLuongTraMuonTheoPhieuTra(int key) {
		int soLuong = 0;
		String sql = "select PTCT.* from PhieuTraCT PTCT\r\n"
				+ "inner join PhieuTra PT on PT.ID = PTCT.IDPhieuTra where TinhTrang = 0 and PTCT.IDPhieuTra = ? ";
		List<PhieuTraCT> listSize = select(sql, key);
		soLuong = listSize.size();
		return soLuong;
	}

	@Override
	public List<PhieuTraCT> selectAll() {
		return null;
	}

	public List<PhieuTraCT> selectAllTheoIDPhieuMuon(int key) {
		String sql = "select PTCT.* from PhieuTraCT PTCT\r\n"
				+ "inner join PhieuMuonCT PMCT on PMCT.ID = PTCT.IDPhieuMuonCT\r\n"
				+ "inner join PhieuMuon PM on PM.ID = PMCT.IDPhieuMuon where PTCT.TinhTrang = 1  and  PM.ID = ?";
		return select(sql, key);
	}

	public List<PhieuTraCT> selectAllTheoPhieuTra(int key) {
		String sql = "select PTCT.* from PhieuTraCT PTCT\r\n"
				+ "inner join PhieuTra PT on PT.ID = PTCT.IDPhieuTra where PTCT.IDPhieuTra = ?";
		return select(sql, key);
	}

	@Override
	public PhieuTraCT selectById(Integer key) {
		return null;
	}

	public PhieuTraCT selectById2(String key) {
		return null;
	}

	public List<PhieuTraCT> loadTrang(int indexTrang, int limit) {
		return null;
	}

	private PhieuTraCT readFromResultSet(ResultSet rs) throws SQLException {
		PhieuTraCT model = new PhieuTraCT();
		model.setId(rs.getInt("ID"));
		PhieuTra pt = ptd.selectById(rs.getInt("IDPhieuTra"));
		model.setIdPhieuTra(pt);
		PhieuMuonCT pmct = pmctd.selectById(rs.getInt("IDPhieuMuonCT"));
		model.setIdPhieuMuonCT(pmct);
		model.setTinhTrangSach(rs.getBoolean("TinhTrang"));
		model.setGhiChu(rs.getString("GhiChu"));
		return model;
	}

	private List<PhieuTraCT> select(String sql, Object... args) {

		List<PhieuTraCT> list = new ArrayList<>();
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, args);
				while (rs.next()) {
					PhieuTraCT nxb = readFromResultSet(rs);
					list.add(nxb);

				}
			} finally {
				rs.getStatement().getConnection().close();
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return list;
	}

//	public List<PhieuMuon> selectByKeyword(String keyword) {
//		String sql = "SELECT * FROM ViTri WHERE Id LIKE ? or daySo LIKE ?";
//		return select(sql, "%" + keyword + "%", "%" + keyword + "%");
//	}

}
