package com.thuvien.dao;

import com.thuvien.entity.Sach;
import com.thuvien.entity.TacGia;
import java.util.ArrayList;
import java.util.List;

import com.thuvien.entity.TaiBan;
import com.thuvien.utils.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiBanDao extends QLTVDao<TaiBan, Integer> {
	SachDao sd = new SachDao();

	@Override
	public void insert(TaiBan model) {
		String sql = "insert into TaiBan(IDSach,LanTB,NgayTB) values(?,?,?)";
		JDBCHelper.executeUpdate(sql, model.getIdSach().getId(), model.getLanTaiBan(), model.getThoiGianTB());

	}

	@Override
	public void update(TaiBan model) {
		String sql = "update TaiBan set IDSach = ?,LanTB = ?, NgayTB=? where id = ?";
		JDBCHelper.executeUpdate(sql, model.getIdSach().getId(), model.getLanTaiBan(), model.getThoiGianTB(),
				model.getId());

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
		TaiBan tg = null;
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, id);
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

	public List<TaiBan> loadTrang(int indexTrang, int limit) {
		String sql = "select * from TaiBan order by ID offset ? rows fetch next ? rows only ";
		List<TaiBan> list = new ArrayList<>();
		return list = select(sql, indexTrang, limit);
	}

	private TaiBan readFromResultSet(ResultSet rs) throws SQLException {
		TaiBan model = new TaiBan();
		model.setId(rs.getInt("ID"));
		int id = rs.getInt("IDSach");
		Sach s = sd.selectByIdSach(id);
		model.setIdSach(s);
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
		String sql = "SELECT * FROM TacGia WHERE MaTG LIKE ? or TenTG LIKE ?";
		return select(sql, "%" + keyword + "%", "%" + keyword + "%");
	}

}
