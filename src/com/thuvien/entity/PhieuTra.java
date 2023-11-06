package com.thuvien.entity;

import java.sql.Date;

public class PhieuTra {
	private int idThanhVien, idNhanVien;
	private String trangThai;
	private float tienPhat;
	private String liDoPhat;
	private Date ngayTraThucTe;
	private float tienTra;
	public PhieuTra(int idThanhVien, int idNhanVien, String trangThai, float tienPhat, String liDoPhat,
			Date ngayTraThucTe, float tienTra) {
		this.idThanhVien = idThanhVien;
		this.idNhanVien = idNhanVien;
		this.trangThai = trangThai;
		this.tienPhat = tienPhat;
		this.liDoPhat = liDoPhat;
		this.ngayTraThucTe = ngayTraThucTe;
		this.tienTra = tienTra;
	}
	public PhieuTra() {
	}
	public int getIdThanhVien() {
		return idThanhVien;
	}
	public void setIdThanhVien(int idThanhVien) {
		this.idThanhVien = idThanhVien;
	}
	public int getIdNhanVien() {
		return idNhanVien;
	}
	public void setIdNhanVien(int idNhanVien) {
		this.idNhanVien = idNhanVien;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public float getTienPhat() {
		return tienPhat;
	}
	public void setTienPhat(float tienPhat) {
		this.tienPhat = tienPhat;
	}
	public String getLiDoPhat() {
		return liDoPhat;
	}
	public void setLiDoPhat(String liDoPhat) {
		this.liDoPhat = liDoPhat;
	}
	public Date getNgayTraThucTe() {
		return ngayTraThucTe;
	}
	public void setNgayTraThucTe(Date ngayTraThucTe) {
		this.ngayTraThucTe = ngayTraThucTe;
	}
	public float getTienTra() {
		return tienTra;
	}
	public void setTienTra(float tienTra) {
		this.tienTra = tienTra;
	}
	@Override
	public String toString() {
		return "PhieuTra [idThanhVien=" + idThanhVien + ", idNhanVien=" + idNhanVien + ", trangThai=" + trangThai
				+ ", tienPhat=" + tienPhat + ", liDoPhat=" + liDoPhat + ", ngayTraThucTe=" + ngayTraThucTe
				+ ", tienTra=" + tienTra + "]";
	}
	
}
