package com.thuvien.entity;

import java.util.Objects;

public class TacGia {
	private int id;
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

	public TacGia(int id, String maTG, String hoTen, String quocTich, boolean gioiTinh) {
		this.id = id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return maTG + " " + hoTen;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gioiTinh, hoTen, maTG, quocTich);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TacGia other = (TacGia) obj;
		return gioiTinh == other.gioiTinh && Objects.equals(hoTen, other.hoTen) && Objects.equals(maTG, other.maTG)
				&& Objects.equals(quocTich, other.quocTich);
	}

}
