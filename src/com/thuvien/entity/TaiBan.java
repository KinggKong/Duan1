package com.thuvien.entity;

import java.util.Date;

public class TaiBan {
	private String maSach;
	private int lanTaiBan;
	private Date thoiGianTB;
	private boolean trangThai;
	
	
	
	public TaiBan(String maSach, int lanTaiBan, Date thoiGianTB, boolean trangThai) {
		this.maSach = maSach;
		this.lanTaiBan = lanTaiBan;
		this.thoiGianTB = thoiGianTB;
		this.trangThai = trangThai;
	}
	
	
	
	public TaiBan() {
	}



	public String getMaSach() {
		return maSach;
	}
	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}
	public int getLanTaiBan() {
		return lanTaiBan;
	}
	public void setLanTaiBan(int lanTaiBan) {
		this.lanTaiBan = lanTaiBan;
	}
	public Date getThoiGianTB() {
		return thoiGianTB;
	}
	public void setThoiGianTB(Date thoiGianTB) {
		this.thoiGianTB = thoiGianTB;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	
}
