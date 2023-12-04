
package com.thuvien.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thuvien.entity.PhieuMuon;
import com.thuvien.entity.PhieuMuonCT;
import com.thuvien.entity.QuyenSach;
import com.thuvien.utils.JDBCHelper;

public class PhieuMuonChiTietDao extends QLTVDao<PhieuMuonCT, Integer> {
	PhieuMuonDao pmd = new PhieuMuonDao();
	QuyenSachDao qsd = new QuyenSachDao();

	@Override
	public void insert(PhieuMuonCT entity) {
		String sql = "insert into PhieuMuonCT (IDPhieuMuon,IDQuyenSach) values(?,?)";
		JDBCHelper.executeUpdate(sql, entity.getIdPhieuMuon().getId(), entity.getIdQuyenSach().getId());
	}

	@Override
	public void update(PhieuMuonCT entity) {
		String sql = "SET IDNhanVien =?,IDThanhVien =?, NgayMuon =?, NgayPhaiTra =?, TienCoc =?r\n"
				+ "WHERE MaPhieuMuon=?";

	}

	@Override
	public void delete(Integer key) {
		String sql = "delete from PhieuMuon where id =?";
		JDBCHelper.executeUpdate(sql, key);
	}

	@Override
	public List<PhieuMuonCT> selectAll() {
		String sql = "select * from PhieuMuonCT";
		return select(sql);
	}

	public List<PhieuMuonCT> selectAllTheoPhieuMuon(int key) {
		String sql = "select * from PhieuMuonCT where IDPhieuMuon=?";
		return select(sql, key);
	}

	@Override
	public PhieuMuonCT selectById(Integer key) {
		String sql = "select * from PhieuMuonCT where ID =?";
		List<PhieuMuonCT> list = select(sql, key);
		return list.size() > 0 ? list.get(0) : null;
	}

	public PhieuMuonCT selectById2(String key) {
		PhieuMuonCT nv = new PhieuMuonCT();
		String sql = "select * from PhieuMuon where MaPhieuMuon =?";
		List<PhieuMuonCT> list = select(sql, key);
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<PhieuMuonCT> selectPhieuMuonCTChuaTra(int key) {
		String sql = "select PMCT.* from PhieuMuonCT PMCT\r\n"
				+ "inner join PhieuMuon PM on PM.ID = PMCT.IDPhieuMuon\r\n"
				+ "where  PM.ID = ? and PMCT.ID not in (select PhieuTraCT.IDPhieuMuonCT from PhieuTraCT)";
		return select(sql, key);
	}

	public List<PhieuMuonCT> loadTrang(int indexTrang, int limit) {
		String sql = "select * from PhieuMuon order by ID offset ? rows fetch next ? rows only ";
		List<PhieuMuonCT> list = new ArrayList<>();
		return list = select(sql, indexTrang, limit);
	}

	private PhieuMuonCT readFromResultSet(ResultSet rs) throws SQLException {
		PhieuMuonCT model = new PhieuMuonCT();
		model.setId(rs.getInt("ID"));
		PhieuMuon pm = pmd.selectById(rs.getInt("IDphieuMuon"));
		model.setIdPhieuMuon(pm);
		QuyenSach qs = qsd.selectById2(rs.getInt("IDQuyenSach"));
		model.setIdQuyenSach(qs);
		return model;
	}

	private List<PhieuMuonCT> select(String sql, Object... args) {

		List<PhieuMuonCT> list = new ArrayList<>();
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, args);
				while (rs.next()) {
					PhieuMuonCT nxb = readFromResultSet(rs);
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
