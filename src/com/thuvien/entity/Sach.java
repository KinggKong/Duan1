package com.thuvien.entity;

import java.util.Date;
import java.util.Objects;

public class Sach {
	private int id;
	private String maSach;
	private String tenSach;
	private int namXB;
	private Date ngayNhap;
	private boolean tinhTrang;
	private NXB idNXB;

	public Sach(int id, String maSach, String tenSach, int namXB, Date ngayNhap, boolean tinhTrang, NXB idNXB) {
		this.id = id;
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.namXB = namXB;
		this.ngayNhap = ngayNhap;
		this.tinhTrang = tinhTrang;
		this.idNXB = idNXB;
	}

	public Sach(String maSach, String tenSach, int namXB, Date ngayNhap, boolean tinhTrang, NXB idNXB) {
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.namXB = namXB;
		this.ngayNhap = ngayNhap;
		this.tinhTrang = tinhTrang;
		this.idNXB = idNXB;
	}

	public Sach() {
	}

	public String getMaSach() {
		return maSach;
	}

	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public int getNamXB() {
		return namXB;
	}

	public void setNamXB(int namXB) {
		this.namXB = namXB;
	}

	public Date getNgayNhap() {
		return ngayNhap;
	}

	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

	public boolean isTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public NXB getIdNXB() {
		return idNXB;
	}

	public void setIdNXB(NXB idNXB) {
		this.idNXB = idNXB;
	}

	@Override
	public String toString() {
		return tenSach;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maSach);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sach other = (Sach) obj;
		return Objects.equals(maSach, other.maSach);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
