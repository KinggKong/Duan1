/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thuvien.dao;

import com.thuvien.entity.ViTri;
import com.thuvien.utils.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author th231
 */
public class ViTriDao extends QLTVDao<ViTri, Integer>{
    @Override
	public void insert(ViTri entity) {
		String sql = "insert into ViTri values(?)";
		JDBCHelper.executeUpdate(sql, entity.getDaySo());

	}

	@Override
	public void update(ViTri entity) {
		String sql = "update ViTri set daySo =? where id=?";
		JDBCHelper.executeUpdate(sql, entity.getDaySo(),entity.getiD());

	}

	@Override
	public void delete(Integer key) {
		String sql = "delete from ViTri where id=?";
                JDBCHelper.executeUpdate(sql, key);

	}

	@Override
	public List<ViTri> selectAll() {
		String sql = "select *from ViTri";
		return select(sql);
	}

	@Override
	public ViTri selectById(Integer key) {
		
		String sql = "Select * from ViTri where Id = ?";
		ViTri Nxb = null;
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
        
        public List<ViTri> loadTrang(int indexTrang, int limit) {
		String sql = "select * from ViTri order by ID offset ? rows fetch next ? rows only ";
		List<ViTri> list = new ArrayList<>();
		return list = select(sql, indexTrang, limit);
	}
        private ViTri readFromResultSet(ResultSet rs) throws SQLException {
		ViTri model = new ViTri();
                model.setiD(rs.getInt("ID"));
                
		model.setDaySo(rs.getInt("daySo"));
		return model;
	}
        private List<ViTri> select(String sql, Object... args) {
           
		List<ViTri> list = new ArrayList<>();
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, args);
				while (rs.next()) {
					ViTri nxb = readFromResultSet(rs);
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

    public List<ViTri> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM ViTri WHERE Id LIKE ? or daySo LIKE ?";
		return select(sql, "%" + keyword + "%", "%" + keyword + "%");
    }
    
}
