/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thuvien.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thuvien.entity.TheLoai;
import com.thuvien.utils.JDBCHelper;

/**
 *
 * @author nguye
 */
public class TheLoaiDao extends QLTVDao<TheLoai, String>{

    @Override
    public void insert(TheLoai tl) {
        String sql = "INSERT INTO TheLoai(MaTL,TenTL) VALUES (?,?)";
        JDBCHelper.executeUpdate(sql, tl.getMaTL(),tl.getTenTL());
    }

    @Override
    public void update(TheLoai tl) {
            String sql = "UPDATE TheLoai SET TenTL =? where MaTL = ?";
        JDBCHelper.executeUpdate(sql,tl.getTenTL(), tl.getMaTL());
    }

    @Override
    public void delete(String maTL) {
        String sql = "DELETE TheLoai where MaTL = ?";
        JDBCHelper.executeUpdate(sql, maTL);
    }

    @Override
    public List<TheLoai> selectAll() {
        String sql = "SELECT * from TheLoai";
        return select(sql);
    }

    @Override
    public TheLoai selectById(String maTl) {
        String sql = "Select * from TheLoai where MaTL = ?";
        TheLoai tl = null;
        try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, maTl);
				while (rs.next()) {
					tl = readFromResultSet(rs);

				}
			} finally {
				rs.getStatement().getConnection().close();
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return tl;
    }
    
    public List<TheLoai> loadTrang(int indexTrang, int limit) {
		String sql = "select MaTL,TenTL from TheLoai order by MaTL offset ? rows fetch next ? rows only ";
		List<TheLoai> list = new ArrayList<>();
		return list = select(sql, indexTrang, limit);
	}

    private List<TheLoai> select(String sql,Object... args) {
                List<TheLoai> list = new ArrayList<>();
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, args);
				while (rs.next()) {
					TheLoai model = readFromResultSet(rs);
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

    private TheLoai readFromResultSet(ResultSet rs) throws SQLException{
        TheLoai model = new TheLoai();
		model.setMaTL(rs.getString("MaTL"));
		model.setTenTL(rs.getString("TenTL"));
		return model;
    }
    public List<TheLoai> selectByKeyword(String keyword) {
		String sql = "SELECT * FROM TheLoai WHERE MaTL LIKE ? or TenTL LIKE ?";
		return select(sql, "%" + keyword + "%", "%" + keyword + "%");
	}
    
}
