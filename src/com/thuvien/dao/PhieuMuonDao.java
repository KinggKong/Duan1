
package com.thuvien.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thuvien.entity.NhanVien;
import com.thuvien.entity.PhieuMuon;
import com.thuvien.entity.ThanhVien;
import com.thuvien.utils.JDBCHelper;

public class PhieuMuonDao extends QLTVDao<PhieuMuon, Integer> {
	ThanhVienDao tvd = new ThanhVienDao();
	NhanVienDao nvd = new NhanVienDao();

	@Override
	public void insert(PhieuMuon entity) {
		String sql = "INSERT INTO PhieuMuon\r\n"
				+ "                  (MaPhieuMuon, IDThanhVien, IDNhanVien, NgayMuon, NgayPhaiTra, TienCoc)\r\n"
				+ "VALUES (?,?,?,?,?,?)";
		JDBCHelper.executeUpdate(sql, entity.getMaPhieuMuon(), entity.getIdThanhVien().getId(),
				entity.getIdNhanVien().getId(), entity.getNgayMuon(), entity.getNgayPhaiTra(), entity.getTienCoc());
	}

	@Override
	public void update(PhieuMuon entity) {
		String sql = "SET IDNhanVien =?,IDThanhVien =?, NgayMuon =?, NgayPhaiTra =?, TienCoc =?r\n"
				+ "WHERE MaPhieuMuon=?";
		JDBCHelper.executeQuery(sql, entity.getIdNhanVien(), entity.getIdThanhVien(), entity.getNgayMuon(),
				entity.getNgayPhaiTra(), entity.getTienCoc(), entity.getMaPhieuMuon());
	}

	@Override
	public void delete(Integer key) {
		String sql = "delete from PhieuMuon where id =?";
		JDBCHelper.executeUpdate(sql, key);
	}

	@Override
	public List<PhieuMuon> selectAll() {
		String sql = "select * from PhieuMuon";
		return select(sql);
	}

	@Override
	public PhieuMuon selectById(Integer key) {
		String sql = "select * from PhieuMuon where ID =?";
		List<PhieuMuon> list = select(sql, key);
		return list.size() > 0 ? list.get(0) : null;
	}

	public PhieuMuon selectById2(String key) {
		PhieuMuon nv = new PhieuMuon();
		String sql = "select * from PhieuMuon where MaPhieuMuon =?";
		List<PhieuMuon> list = select(sql, key);
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<PhieuMuon> selectTheoThanhVien(int key) {
//		String sql = "select * from PhieuMuon where IDThanhVien =?";
		String sql = "select * from PhieuMuon PM\r\n"
				+ "inner join PhieuMuonCT PMCT on PMCT.IDPhieuMuon = PM.ID and  PMCT.ID not  in (select IDPhieuMuonCT from PhieuTraCT) and PM.IDThanhVien = ?";
		return select(sql, key);
	}

	public List<PhieuMuon> loadTrang(int indexTrang, int limit) {
		String sql = "select * from PhieuMuon order by ID offset ? rows fetch next ? rows only ";
		List<PhieuMuon> list = new ArrayList<>();
		return list = select(sql, indexTrang, limit);
	}

	private PhieuMuon readFromResultSet(ResultSet rs) throws SQLException {
		PhieuMuon model = new PhieuMuon();
		model.setId(rs.getInt("ID"));
		model.setMaPhieuMuon(rs.getString("MaPhieuMuon"));
		int idThanhVien = rs.getInt("IDThanhVien");
		ThanhVien tv = tvd.selectById2(idThanhVien);
		model.setIdThanhVien(tv);
		int idNhanVien = rs.getInt("IDNhanVien");
		NhanVien nv = nvd.selectById2(idNhanVien);
		model.setIdNhanVien(nv);
		model.setNgayMuon(rs.getDate("NgayMuon"));
		model.setNgayPhaiTra(rs.getDate("NgayPhaiTra"));
		model.setTienCoc(rs.getFloat("TienCoc"));
		return model;
	}

	private List<PhieuMuon> select(String sql, Object... args) {

		List<PhieuMuon> list = new ArrayList<>();
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, args);
				while (rs.next()) {
					PhieuMuon nxb = readFromResultSet(rs);
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

	public List<PhieuMuon> selectByKeyword(String keyword) {
		String sql = "select PM.* from PhieuMuon PM\r\n"
				+ "inner join ThanhVien TV on TV.ID = PM.IDThanhVien where TV.MaTV = ? or TV.TenTV like ?";
		return select(sql, keyword, "%" + keyword + "%");
	}

}
