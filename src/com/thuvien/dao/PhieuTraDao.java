package com.thuvien.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thuvien.entity.NhanVien;
import com.thuvien.entity.PhieuMuon;
import com.thuvien.entity.PhieuTra;
import com.thuvien.entity.ThanhVien;
import com.thuvien.utils.JDBCHelper;

public class PhieuTraDao extends QLTVDao<PhieuTra, Integer> {
	ThanhVienDao tvd = new ThanhVienDao();
	NhanVienDao nvd = new NhanVienDao();

	@Override
	public void insert(PhieuTra entity) {
		String sql = "INSERT INTO PhieuTra\r\n"
				+ "                  (IDThanhVien, IDNhanVien, TienPhat, TrangThaiTra, LyDoPhat, NgayTraThucTe, TienTra, MaPhieuTra)\r\n"
				+ "VALUES (?,?,?,?,?,?,?,?)";
		JDBCHelper.executeUpdate(sql, entity.getIdThanhVien().getId(), entity.getIdNhanVien().getId(),
				entity.getTienPhat(), entity.isTrangThai(), entity.getLiDoPhat(), entity.getNgayTraThucTe(),
				entity.getTienTra(), entity.getMaPT());
	}

	@Override
	public void update(PhieuTra entity) {
		String sql = "update PhieuTra set IDThanhVien=?,IDNhanVien =?, TrangThaiTra =?, TienPhat =?, LyDoPhat =?, NgayTraThucTe =?, TienTra =? where MaPhieuTra =?";
		JDBCHelper.executeUpdate(sql, entity.getIdThanhVien().getId(), entity.getIdNhanVien().getId(),
				entity.isTrangThai(), entity.getTienPhat(), entity.getLiDoPhat(), entity.getNgayTraThucTe(),
				entity.getTienTra(), entity.getMaPT());

	}

	@Override
	public void delete(Integer key) {
		String sql = "delete from PhieuTra where id =?";
		JDBCHelper.executeUpdate(sql, key);
	}

	@Override
	public List<PhieuTra> selectAll() {
		String sql = "select * from PhieuTra";
		return select(sql);
	}

	@Override
	public PhieuTra selectById(Integer key) {
		String sql = "select * from PhieuTra where id =?";
		List<PhieuTra> list = select(sql, key);
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<PhieuTra> loadTrang(int indexTrang, int limit) {
		String sql = "select * from PhieuTra order by ID offset ? rows fetch next ? rows only ";
		List<PhieuTra> list = new ArrayList<>();
		return list = select(sql, indexTrang, limit);
	}

	private PhieuTra readFromResultSet(ResultSet rs) throws SQLException {
		PhieuTra model = new PhieuTra();
		model.setId(rs.getInt("ID"));
		model.setMaPT(rs.getString("MaPhieuTra"));
		NhanVien nv = nvd.selectById2(rs.getInt("IDNhanVien"));
		model.setIdNhanVien(nv);
		ThanhVien tv = tvd.selectById2(rs.getInt("IDThanhVien"));
		model.setIdThanhVien(tv);
		model.setLiDoPhat(rs.getString("LyDoPhat"));
		model.setNgayTraThucTe(rs.getDate("NgayTraThucTe"));
		model.setTienPhat(rs.getFloat("TienPhat"));
		model.setTienTra(rs.getFloat("TienPhat"));
		model.setTrangThai(rs.getBoolean("TrangThaiTra"));
		return model;
	}

	private List<PhieuTra> select(String sql, Object... args) {

		List<PhieuTra> list = new ArrayList<>();
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, args);
				while (rs.next()) {
					PhieuTra nxb = readFromResultSet(rs);
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

	public List<PhieuTra> selectByKeyword(String keyword) {
		String sql = "select PT.* from PhieuTra PT\r\n"
				+ "inner join ThanhVien TV on TV.ID = PT.IDThanhVien where TV.MaTV = ? or TV.TenTV like ?";
		return select(sql, keyword, "%" + keyword + "%");
	}
}
