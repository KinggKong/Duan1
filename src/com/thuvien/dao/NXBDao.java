package com.thuvien.dao;

import com.thuvien.dao.QLTVDao;
import java.util.List;

import com.thuvien.entity.NXB;
import com.thuvien.entity.TacGia;
import com.thuvien.utils.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class NXBDao extends QLTVDao<NXB, Integer> {

	@Override
	public void insert(NXB entity) {
		String sql = "insert into NhaXuatBan values(?)";
		JDBCHelper.executeUpdate(sql, entity.getTenNXB());

	}

	@Override
	public void update(NXB entity) {
		String sql = "update NhaXuatBan set tenNXB =? where id=?";
		JDBCHelper.executeUpdate(sql, entity.getTenNXB(),entity.getId());

	}

	@Override
	public void delete(Integer key) {
		String sql = "delete from nhaxuatban where id=?";
                JDBCHelper.executeUpdate(sql, key);

	}

	@Override
	public List<NXB> selectAll() {
		String sql = "select *from NhaXuatBan";
		return select(sql);
	}

	@Override
	public NXB selectById(Integer key) {
		
		String sql = "Select * from NhaXuatBan where Id = ?";
		NXB Nxb = null;
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, key);
				while (rs.next()) {
					Nxb = readFromResultSet(rs);

				}
			} finally {
				rs.getStatement().getConnection().close();
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return Nxb;
	}
        
        public List<NXB> loadTrang(int indexTrang, int limit) {
		String sql = "select * from NhaXuatBan order by ID offset ? rows fetch next ? rows only ";
		List<NXB> list = new ArrayList<>();
		return list = select(sql, indexTrang, limit);
	}
        private NXB readFromResultSet(ResultSet rs) throws SQLException {
		NXB model = new NXB();
                model.setId(rs.getInt(1));
                
		model.setTenNXB(rs.getString(2));
		return model;
	}
        private List<NXB> select(String sql, Object... args) {
           
		List<NXB> list = new ArrayList<>();
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, args);
				while (rs.next()) {
					NXB nxb = readFromResultSet(rs);
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

    public List<NXB> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM NhaXuatBan WHERE Id LIKE ? or tenNXB LIKE ?";
		return select(sql, "%" + keyword + "%", "%" + keyword + "%");
    }

        
    

}
