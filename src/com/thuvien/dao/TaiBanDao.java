package com.thuvien.dao;

import java.util.ArrayList;
import java.util.List;

import com.thuvien.entity.TaiBan;
import com.thuvien.utils.JDBCHelper;

public class TaiBanDao extends QLTVDao<TaiBan, String> {

	@Override
	public void insert(TaiBan model) {
		String sql = "insert into TaiBan(maSach,lanTB,thoiGian,trangThai) values(?,?,?,?)";
		JDBCHelper.executeUpdate(sql, model.getMaSach(), model.getLanTaiBan(), model.getThoiGianTB(),
				model.isTrangThai());

	}

	@Override
	public void update(TaiBan model) {
		String sql = "update TaiBan set lanTB = ?, thoiGian=?,trangThai=? where maSach = ?";
		JDBCHelper.executeUpdate(sql, model.getLanTaiBan(), model.getThoiGianTB(), model.isTrangThai(),
				model.getMaSach());

	}

	@Override
	public void delete(String key) {
		String sql = "delete from TaiBan where maSach = ?";
		JDBCHelper.executeUpdate(sql, key);

	}

	@Override
	public List<TaiBan> selectAll() {
		String sql="select * from TaiBan";
		return selectAll();
	}

	@Override
	public TaiBan selectById(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
