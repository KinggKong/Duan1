package com.thuvien.entity;

public class SachTopLuotMuon {
	private String tenSach;
	private int luotMuon;

	public SachTopLuotMuon(String tenSach, int luotMuon) {
		this.tenSach = tenSach;
		this.luotMuon = luotMuon;
	}

	public SachTopLuotMuon() {
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public int getLuotMuon() {
		return luotMuon;
	}

	public void setLuotMuon(int luotMuon) {
		this.luotMuon = luotMuon;
	}
}
