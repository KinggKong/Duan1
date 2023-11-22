package com.thuvien.entity;

import java.util.Date;
import java.util.Objects;

public class ThanhVien {
	private int id;
	private String maTV, tenTV, SDT, diaChi, email, CCCD;
	private Date ngaySinh, ngayDK;

	public ThanhVien(int id, String maTV, String tenTV, String sDT, String diaChi, String email, String cCCD,
			Date ngaySinh, Date ngayDK) {
		this.id = id;
		this.maTV = maTV;
		this.tenTV = tenTV;
		SDT = sDT;
		this.diaChi = diaChi;
		this.email = email;
		CCCD = cCCD;
		this.ngaySinh = ngaySinh;
		this.ngayDK = ngayDK;
	}

	public ThanhVien(String maTV, String tenTV, String sDT, String diaChi, String email, String cCCD, Date ngaySinh,
			Date ngayDK) {
		this.maTV = maTV;
		this.tenTV = tenTV;
		SDT = sDT;
		this.diaChi = diaChi;
		this.email = email;
		CCCD = cCCD;
		this.ngaySinh = ngaySinh;
		this.ngayDK = ngayDK;
	}

	public ThanhVien() {
	}

	public String getMaTV() {
		return maTV;
	}

	public void setMaTV(String maTV) {
		this.maTV = maTV;
	}

	public String getTenTV() {
		return tenTV;
	}

	public void setTenTV(String tenTV) {
		this.tenTV = tenTV;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCCCD() {
		return CCCD;
	}

	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public Date getNgayDK() {
		return ngayDK;
	}

	public void setNgayDK(Date ngayDK) {
		this.ngayDK = ngayDK;
	}

	@Override
	public String toString() {
		return maTV + " " + tenTV;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maTV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ThanhVien other = (ThanhVien) obj;
		return Objects.equals(maTV, other.maTV);
	}

}
