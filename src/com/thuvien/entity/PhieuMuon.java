package com.thuvien.entity;

import java.sql.Date;

public class PhieuMuon {
	private int idThanhVien;
	private int idNhanVien;
	private Date ngayMuon;
	private Date ngayPhaiTra;
	private float tienCoc;

	public PhieuMuon(int idThanhVien, int idNhanVien, Date ngayMuon, Date ngayPhaiTra, float tienCoc) {
		this.idThanhVien = idThanhVien;
		this.idNhanVien = idNhanVien;
		this.ngayMuon = ngayMuon;
		this.ngayPhaiTra = ngayPhaiTra;
		this.tienCoc = tienCoc;
	}

	public PhieuMuon() {
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

	public Date getNgayMuon() {
		return ngayMuon;
	}

	public void setNgayMuon(Date ngayMuon) {
		this.ngayMuon = ngayMuon;
	}

	public Date getNgayPhaiTra() {
		return ngayPhaiTra;
	}

	public void setNgayPhaiTra(Date ngayPhaiTra) {
		this.ngayPhaiTra = ngayPhaiTra;
	}

	public float getTienCoc() {
		return tienCoc;
	}

	public void setTienCoc(float tienCoc) {
		this.tienCoc = tienCoc;
	}

	@Override
	public String toString() {
		return "PhieuMuon [idThanhVien=" + idThanhVien + ", idNhanVien=" + idNhanVien + ", ngayMuon=" + ngayMuon
				+ ", ngayPhaiTra=" + ngayPhaiTra + ", tienCoc=" + tienCoc + "]";
	}

}
