/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thuvien.dao;

import com.thuvien.entity.Day;
import com.thuvien.entity.Ngan;
import com.thuvien.entity.OSoChiTiet;
import com.thuvien.entity.QuyenSach;
import com.thuvien.entity.ViTri;
import com.thuvien.utils.JDBCHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author th231
 */
public class ViTriDao {
	public List<Day> getAllDay() {
		List<Day> listDay = new ArrayList<>();
		try {
			Connection con = JDBCHelper.getConnection();
			String sql = "select ID,TenVT from ViTriQuyenSach where Parent_ID is null and TenVT like N'%Dãy%'";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Day day = new Day();
				day.setIdDay(rs.getString("ID"));
				day.setTenDay(rs.getString("TenVT"));
				listDay.add(day);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listDay;
	}

	public List<Ngan> getAllNgan(String day) {
		List<Ngan> listNgan = new ArrayList<>();
		try {
			Connection con = JDBCHelper.getConnection();
			String sql = "select ID,TenVT,Parent_ID from ViTriQuyenSach where Parent_ID = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, day);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Ngan ngan = new Ngan();
				ngan.setIdNgan(rs.getString("ID"));
				ngan.setTenNgan(rs.getString("TenVT"));
				ngan.setParent_ID(rs.getString("Parent_ID"));
				listNgan.add(ngan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listNgan;
	}

	public List<OSoChiTiet> getAllO(String day) {
		List<OSoChiTiet> listOSoChiTiet = new ArrayList<>();
		try {
			Connection con = JDBCHelper.getConnection();
			String sql = "select ID,TenVT,Parent_ID,MaQuyenSach from ViTriQuyenSach where Parent_ID =  ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, day);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				OSoChiTiet soChiTiet = new OSoChiTiet();
				soChiTiet.setIdO(rs.getString("ID"));
				soChiTiet.setTenO(rs.getString("TenVT"));
				soChiTiet.setParent_ID(rs.getString("Parent_ID"));
				soChiTiet.setMaQS(rs.getString("MaQuyenSach"));
				listOSoChiTiet.add(soChiTiet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOSoChiTiet;
	}

	public List<QuyenSach> getQuyenSachChuaCoO() {
		String sql = "select * from QuyenSach where MaQS not in (select MaQuyenSach from ViTriQuyenSach )";
		return select(sql);
	}

	private List<QuyenSach> select(String sql, Object... args) {
		List<QuyenSach> list = new ArrayList<>();
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.executeQuery(sql, args);
				while (rs.next()) {
					QuyenSach model = readFromResultSet(rs);
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

	private QuyenSach readFromResultSet(ResultSet rs) throws SQLException {
		QuyenSach qs = new QuyenSach();
		qs.setId(rs.getInt("ID"));
		qs.setMaQS(rs.getString("MaQS"));
		qs.setTenQS(rs.getString("TenQS"));
//		int id = rs.getInt("IDTaiBan");
//		TaiBan tb = tbd.selectById(id);
		qs.setIdTaiBan(rs.getInt("IDTaiBan"));
		qs.setTinhTrang(rs.getInt("TinhTrang"));
		qs.setGhiChu(rs.getString("GhiChu"));
		return qs;
	}

	public int update(String idO, String maQS) {
		int kq = 0;
		try {
			Connection con = JDBCHelper.getConnection();
			String sql = "update ViTriQuyenSach set MaQuyenSach= ? where ID =?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, maQS);
			pst.setString(2, idO);
			kq = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kq;
	}
}
