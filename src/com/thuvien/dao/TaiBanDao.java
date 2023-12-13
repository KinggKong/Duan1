package com.thuvien.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thuvien.entity.TaiBan;
import com.thuvien.utils.JDBCHelper;

public class TaiBanDao extends QLTVDao<TaiBan, Integer> {
	SachDao sd = new SachDao();

	@Override
	public void insert(TaiBan model) {
		String sql = "insert into TaiBan(IDSach,LanTB,NgayTB) values(?,?,?)";
		JDBCHelper.executeUpdate(sql, model.getIdSach(), model.getLanTaiBan(), model.getThoiGianTB());

	}

	@Override
	public void update(TaiBan model) {
		String sql = "update TaiBan set IDSach = ?,LanTB = ?, NgayTB=? where id = ?";
		JDBCHelper.executeUpdate(sql, model.getIdSach(), model.getLanTaiBan(), model.getThoiGianTB(), model.getId());

	}

	@Override
	public void delete(Integer key) {
		String sql = "delete from TaiBan where id = ?";
		JDBCHelper.executeUpdate(sql, key);

	}

	@Override
	public List<TaiBan> selectAll() {
		String sql = "select * from TaiBan";
		return select(sql);
	}

	public List<TaiBan> selectAllTheoIDSach(int idSach) {
		String sql = "select * from TaiBan where IDSach =?";
		return select(sql, idSach);
	}

	@Override
	public TaiBan selectById(Integer id) {
		String sql = "Select * from TaiBan where id = ?";
		List<TaiBan> list = select(sql, id);
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<TaiBan> loadTrang(int indexTrang, int limit) {
		String sql = "select * from TaiBan order by ID DESC offset ? rows fetch next ? rows only ";
		List<TaiBan> list = new ArrayList<>();
		return list = select(sql, indexTrang, limit);
	}

	private TaiBan readFromResultSet(ResultSet rs) throws SQLException {
		TaiBan model = new TaiBan();
		model.setId(rs.getInt("ID"));
		model.setIdSach(rs.getInt("IDSach"));
		model.setLanTaiBan(rs.getInt("LanTB"));
		model.setThoiGianTB(rs.getDate("NgayTB"));
		return model;
	}

	private List<TaiBan> select(String sql, Object... args) {
		List<TaiBan> list = new ArrayList<>();
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, args);
				while (rs.next()) {
					TaiBan model = readFromResultSet(rs);
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

	public List<TaiBan> selectByKeyword(String keyword) {
		String sql = "select * from TaiBan \r\n"
				+ "inner join Sach on Sach.ID = TaiBan.IDSach where Sach.TenSach like ?";
		return select(sql, "%" + keyword + "%");
	}

}
