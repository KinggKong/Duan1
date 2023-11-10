package com.thuvien.dao;

import java.util.List;

import com.thuvien.entity.NXB;
import com.thuvien.utils.JDBCHelper;

public class NXBDao extends QLTVDao<NXB, Integer> {

	@Override
	public void insert(NXB entity) {
		String sql = "insert into NhaXuatBan values(?)";
		JDBCHelper.executeQuery(sql, entity.getTenNXB());

	}

	@Override
	public void update(NXB entity) {
		String sql = "update NhaXuatBan set tenNXB =? where id=?";
		JDBCHelper.executeUpdate(sql, entity.getTenNXB());

	}

	@Override
	public void delete(Integer key) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<NXB> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NXB selectById(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

}
