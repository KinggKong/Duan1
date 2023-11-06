package com.thuvien.entity;

public class TacGia {
	private String maTG;
	private String hoTen;
	private String quocTich;
	private boolean gioiTinh;

	public TacGia(String maTG, String hoTen, String quocTich, boolean gioiTinh) {
		this.maTG = maTG;
		this.hoTen = hoTen;
		this.quocTich = quocTich;
		this.gioiTinh = gioiTinh;
	}

	public TacGia() {
	}

	public String getMaTG() {
		return maTG;
	}

	public void setMaTG(String maTG) {
		this.maTG = maTG;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getQuocTich() {
		return quocTich;
	}

	public void setQuocTich(String quocTich) {
		this.quocTich = quocTich;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	@Override
	public String toString() {
		return "TacGia [maTG=" + maTG + ", hoTen=" + hoTen + ", quocTich=" + quocTich + ", gioiTinh=" + gioiTinh + "]";
	}

}
