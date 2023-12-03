/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thuvien.dao;

import com.thuvien.entity.ThanhVien;
import com.thuvien.utils.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nguye
 */
public class ThanhVienDao extends QLTVDao<ThanhVien, String> {

	@Override
	public void insert(ThanhVien tv) {
		String sql = "INSERT INTO ThanhVien(MaTV,TenTV,SDT,DiaChi,Email,CCCD,NgaySinh,NgayDK) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		JDBCHelper.executeUpdate(sql, tv.getMaTV(), tv.getTenTV(), tv.getSDT(), tv.getDiaChi(), tv.getEmail(),
				tv.getCCCD(), tv.getNgaySinh(), tv.getNgayDK());
	}

	@Override
	public void update(ThanhVien tv) {
		String sql = "UPDATE ThanhVien SET TenTV = ?, SDT =? ,DiaChi =?, Email = ?,CCCD= ?,NgaySinh=?,NgayDK=? where MaTV = ?";
		JDBCHelper.executeUpdate(sql, tv.getTenTV(), tv.getSDT(), tv.getDiaChi(), tv.getEmail(), tv.getCCCD(),
				tv.getNgayDK(), tv.getNgaySinh(), tv.getMaTV());
	}

	@Override
	public void delete(String maTV) {
		String sql = "DELETE FROM ThanhVien where MaTV = ?";
		JDBCHelper.executeUpdate(sql, maTV);
	}

	@Override
	public List<ThanhVien> selectAll() {
		String sql = "SELECT * FROM ThanhVien";
		return select(sql);
	}

	@Override
	public ThanhVien selectById(String maTV) {
		String sql = "Select * from ThanhVien where MaTV = ?";
		ThanhVien tv = null;
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, maTV);
				while (rs.next()) {
					tv = readFromResultSet(rs);

				}
			} finally {
				rs.getStatement().getConnection().close();
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return tv;
	}

	public ThanhVien selectById2(int id) {
		String sql = "Select * from ThanhVien where id =?";
		ThanhVien tv = null;
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, id);
				while (rs.next()) {
					tv = readFromResultSet(rs);

				}
			} finally {
				rs.getStatement().getConnection().close();
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return tv;
	}

	public List<ThanhVien> loadTrang(int indexTrang, int limit) {
		String sql = "select * from ThanhVien order by ID offset ? rows fetch next ? rows only ";
		List<ThanhVien> list = new ArrayList<>();
		return list = select(sql, indexTrang, limit);
	}

	private List<ThanhVien> select(String sql, Object... args) {
		List<ThanhVien> list = new ArrayList<>();
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, args);
				while (rs.next()) {
					ThanhVien model = readFromResultSet(rs);
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

	private ThanhVien readFromResultSet(ResultSet rs) throws SQLException {
		ThanhVien model = new ThanhVien();
		model.setId(rs.getInt("ID"));
		model.setMaTV(rs.getString("MaTV"));
		model.setTenTV(rs.getString("TenTV"));
		model.setSDT(rs.getString("SDT"));
		model.setDiaChi(rs.getString("DiaChi"));
		model.setEmail(rs.getString("Email"));
		model.setCCCD(rs.getString("CCCD"));
		model.setNgayDK(rs.getDate("NgayDK"));
		model.setNgaySinh(rs.getDate("NgaySinh"));
		return model;
	}

	public List<ThanhVien> selectByKeyword(String keyword) {
		String sql = "SELECT * FROM ThanhVien WHERE MaTV LIKE ? or TenTV LIKE ?";
		return select(sql, "%" + keyword + "%", "%" + keyword + "%");
	}

}