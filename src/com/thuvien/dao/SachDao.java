package com.thuvien.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thuvien.entity.NXB;
import com.thuvien.entity.Sach;
import com.thuvien.utils.JDBCHelper;

public class SachDao extends QLTVDao<Sach, String> {
	NXBDao nxbd = new NXBDao();

	@Override
	public void insert(Sach entity) {
		String sql = "insert into Sach(MaSach,TenSach,NamXB,NgayNhap,TinhTrang,IDNXB) values(?,?,?,?,?,?)";
		JDBCHelper.executeUpdate(sql, entity.getMaSach(), entity.getTenSach(), entity.getNamXB(), entity.getNgayNhap(),
				entity.isTinhTrang(), entity.getIdNXB().getId());
	}

	@Override
	public void update(Sach entity) {
		String sql = "Update Sach set TenSach=?,NamXB=?,NgayNhap=?,TinhTrang=?,IDNXB=? from Sach WHERE MaSach=?";
		JDBCHelper.executeUpdate(sql, entity.getTenSach(), entity.getNamXB(), entity.getNgayNhap(),
				entity.isTinhTrang(), entity.getIdNXB().getId(), entity.getMaSach());
	}

	@Override
	public void delete(String key) {
		String sql = "delete from Sach where MaSach=?";
		JDBCHelper.executeUpdate(sql, key);

	}

	@Override
	public List<Sach> selectAll() {
		String sql = "select * from Sach";
		return select(sql);
	}

	@Override
	public Sach selectById(String key) {
		String sql = "select * from Sach WHERE MaSach=?";
		List<Sach> list = select(sql, key);
		return list.size() > 0 ? list.get(0) : null;
	}

	public Sach selectByIdSach(int key) {
		String sql = "select * from Sach WHERE id=?";
		List<Sach> list = select(sql, key);
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<Sach> loadTrang(int indexTrang, int limit) {
		String sql = "select * from Sach order by ID DESC offset ? rows fetch next ? rows only ";
		List<Sach> list = new ArrayList<>();
		return list = select(sql, indexTrang, limit);

	}

	private Sach readFromResultSet(ResultSet rs) throws SQLException {
		Sach model = new Sach();
		model.setId(rs.getInt("ID"));
		model.setMaSach(rs.getString("MaSach"));
		model.setTenSach(rs.getString("TenSach"));
		model.setNamXB(rs.getInt("NamXB"));
		model.setNgayNhap(rs.getDate("NgayNhap"));
		model.setTinhTrang(rs.getBoolean("TinhTrang"));
		int idNXB = rs.getInt("IDNXB");
		NXB nxb = nxbd.selectById(idNXB);
		model.setIdNXB(nxb);
		return model;
	}

	private List<Sach> select(String sql, Object... args) {
		List<Sach> list = new ArrayList<>();
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, args);
				while (rs.next()) {
					Sach model = readFromResultSet(rs);
					list.add(model);
				}
			} finally {
				if (rs != null) {
					rs.getStatement().getConnection().close();
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return list;
	}

	public List<Sach> selectByKeyword(String keyword) {
		String sql = "SELECT * FROM TacGia WHERE MaTG LIKE ? or TenTG LIKE ?";
		return select(sql, "%" + keyword + "%", "%" + keyword + "%");
	}
}
