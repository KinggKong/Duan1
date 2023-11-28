package com.thuvien.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thuvien.entity.TienPhat;
import com.thuvien.entity.TienThe;
import com.thuvien.utils.JDBCHelper;

public class ThongKeDao {
	public TienPhat getTienPhat(int thang, int nam) {
		TienPhat tp = null;
		try {
			Connection con = JDBCHelper.getConnection();
			String sql = "select sum(TienPhat)  from phieutra where MONTH(NgayTraThucTe) = ? and YEAR(NgayTraThucTe) = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, thang);
			pst.setInt(2, nam);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				tp = new TienPhat();
				tp.setTienPhat(rs.getFloat(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tp;
	}

	public TienThe getTienThe(int thang, int nam) {
		TienThe tp = null;
		try {
			Connection con = JDBCHelper.getConnection();
			String sql = "select  SUM(HTV.DonGia) from TheThanhVien TTV\r\n"
					+ "inner join HangThanhVien HTV on HTV.ID = TTV.IDHangTV where MONTH(TTV.NgayCap) = ?  and YEAR(TTV.NgayCap) = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, thang);
			pst.setInt(2, nam);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				tp = new TienThe();
				tp.setTienThe(rs.getFloat(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tp;
	}

	public List<Integer> getAllYear() {
		List<Integer> listYear = new ArrayList<>();
		try {
			Connection con = JDBCHelper.getConnection();
			String sql = "select DISTINCT YEAR(NgayCap) as 'Year' from TheThanhVien ";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Integer year = rs.getInt(1);
				listYear.add(year);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listYear;
	}

	public List<Integer> getAllMonth() {
		List<Integer> listMonth = new ArrayList<>();
		try {
			Connection con = JDBCHelper.getConnection();
			String sql = "select DISTINCT Month(NgayCap) as 'Year' from TheThanhVien ";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Integer year = rs.getInt(1);
				listMonth.add(year);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listMonth;
	}

}
