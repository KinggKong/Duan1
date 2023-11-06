package com.thuvien.entity;

public class TheLoai {
	private String maTL, tenTL;

	public TheLoai(String maTL, String tenTL) {
		this.maTL = maTL;
		this.tenTL = tenTL;
	}

	public TheLoai() {
	}

	public String getMaTL() {
		return maTL;
	}

	public void setMaTL(String maTL) {
		this.maTL = maTL;
	}

	public String getTenTL() {
		return tenTL;
	}

	public void setTenTL(String tenTL) {
		this.tenTL = tenTL;
	}

	@Override
	public String toString() {
		return "TheLoai [maTL=" + maTL + ", tenTL=" + tenTL + "]";
	}

}
