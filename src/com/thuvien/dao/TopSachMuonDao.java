package com.thuvien.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thuvien.entity.SachTopLuotMuon;
import com.thuvien.utils.JDBCHelper;

public class TopSachMuonDao {
	public List<SachTopLuotMuon> getTopSachLuotMuon(Integer key) {
		List<SachTopLuotMuon> listTopSachMuon = new ArrayList<>();
		try {
			Connection con = JDBCHelper.getConnection();
			String sql = "select top(10) QS.TenQS,COUNT(QS.TenQS) as 'LuotMuon' from Sach S \r\n"
					+ "inner join TaiBan TB on TB.IDSach = S.ID\r\n"
					+ "inner join QuyenSach QS on QS.IDTaiBan = TB.ID\r\n"
					+ "inner join PhieuMuonCT PMCT on PMCT.IDQuyenSach = QS.ID\r\n"
					+ "inner join PhieuMuon PM on PM.ID = PMCT.IDPhieuMuon \r\n" + "where Year(PM.NgayMuon ) = ?\r\n"
					+ "group by  QS.TenQS order by COUNT(QS.TenQS) desc";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, key);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				SachTopLuotMuon sachTopLuotMuon = new SachTopLuotMuon();
				sachTopLuotMuon.setTenSach(rs.getString("TenQS"));
				sachTopLuotMuon.setLuotMuon(rs.getInt("LuotMuon"));
				listTopSachMuon.add(sachTopLuotMuon);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listTopSachMuon;
	}
}
