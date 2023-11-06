package com.thuvien.entity;

import java.sql.Date;

public class TheThanhVien {
	private String maTTV;
	private int idHangTV;
	private Date ngayCap, ngayHieuLuc;
	private int tgHieuLuc, idThanhVien, oldID;
	private boolean trangThai;

	public TheThanhVien(String maTTV, int idHangTV, Date ngayCap, Date ngayHieuLuc, int tgHieuLuc, int idThanhVien,
			int oldID, boolean trangThai) {
		this.maTTV = maTTV;
		this.idHangTV = idHangTV;
		this.ngayCap = ngayCap;
		this.ngayHieuLuc = ngayHieuLuc;
		this.tgHieuLuc = tgHieuLuc;
		this.idThanhVien = idThanhVien;
		this.oldID = oldID;
		this.trangThai = trangThai;
	}

	public TheThanhVien() {
	}

	public String getMaTTV() {
		return maTTV;
	}

	public void setMaTTV(String maTTV) {
		this.maTTV = maTTV;
	}

	public int getIdHangTV() {
		return idHangTV;
	}

	public void setIdHangTV(int idHangTV) {
		this.idHangTV = idHangTV;
	}

	public Date getNgayCap() {
		return ngayCap;
	}

	public void setNgayCap(Date ngayCap) {
		this.ngayCap = ngayCap;
	}

	public Date getNgayHieuLuc() {
		return ngayHieuLuc;
	}

	public void setNgayHieuLuc(Date ngayHieuLuc) {
		this.ngayHieuLuc = ngayHieuLuc;
	}

	public int getTgHieuLuc() {
		return tgHieuLuc;
	}

	public void setTgHieuLuc(int tgHieuLuc) {
		this.tgHieuLuc = tgHieuLuc;
	}

	public int getIdThanhVien() {
		return idThanhVien;
	}

	public void setIdThanhVien(int idThanhVien) {
		this.idThanhVien = idThanhVien;
	}

	public int getOldID() {
		return oldID;
	}

	public void setOldID(int oldID) {
		this.oldID = oldID;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "TheThanhVien [maTTV=" + maTTV + ", idHangTV=" + idHangTV + ", ngayCap=" + ngayCap + ", ngayHieuLuc="
				+ ngayHieuLuc + ", tgHieuLuc=" + tgHieuLuc + ", idThanhVien=" + idThanhVien + ", oldID=" + oldID
				+ ", trangThai=" + trangThai + "]";
	}

}
