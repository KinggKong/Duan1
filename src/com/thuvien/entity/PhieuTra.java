package com.thuvien.entity;

import java.util.Date;

public class PhieuTra {
	private int id;
	private String maPT;
	private ThanhVien idThanhVien;
	private NhanVien idNhanVien;
	private boolean trangThai;
	private float tienPhat;
	private String liDoPhat;
	private Date ngayTraThucTe;
	private float tienTra;

	public PhieuTra(String maPT, ThanhVien idThanhVien, NhanVien idNhanVien, boolean trangThai, float tienPhat,
			String liDoPhat, Date ngayTraThucTe, float tienTra) {
		this.maPT = maPT;
		this.idThanhVien = idThanhVien;
		this.idNhanVien = idNhanVien;
		this.trangThai = trangThai;
		this.tienPhat = tienPhat;
		this.liDoPhat = liDoPhat;
		this.ngayTraThucTe = ngayTraThucTe;
		this.tienTra = tienTra;
	}

	public PhieuTra(int id, String maPT, ThanhVien idThanhVien, NhanVien idNhanVien, boolean trangThai, float tienPhat,
			String liDoPhat, Date ngayTraThucTe, float tienTra) {
		this.id = id;
		this.maPT = maPT;
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

	public ThanhVien getIdThanhVien() {
		return idThanhVien;
	}

	public void setIdThanhVien(ThanhVien idThanhVien) {
		this.idThanhVien = idThanhVien;
	}

	public NhanVien getIdNhanVien() {
		return idNhanVien;
	}

	public void setIdNhanVien(NhanVien idNhanVien) {
		this.idNhanVien = idNhanVien;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaPT() {
		return maPT;
	}

	public void setMaPT(String maPT) {
		this.maPT = maPT;
	}

}
