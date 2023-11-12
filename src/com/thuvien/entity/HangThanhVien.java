package com.thuvien.entity;

public class HangThanhVien {
	private int id;
	private String maHTV, tenHang;
	private float donGia, phiThueSach;
	private int soThangHieuLuc, tuoiMin, tuoiMax;

	public HangThanhVien(int id, String maHTV, String tenHang, float donGia, float phiThueSach, int soThangHieuLuc,
			int tuoiMin, int tuoiMax) {
		this.id = id;
		this.maHTV = maHTV;
		this.tenHang = tenHang;
		this.donGia = donGia;
		this.phiThueSach = phiThueSach;
		this.soThangHieuLuc = soThangHieuLuc;
		this.tuoiMin = tuoiMin;
		this.tuoiMax = tuoiMax;
	}

	public HangThanhVien(String maHTV, String tenHang, float donGia, float phiThueSach, int soThangHieuLuc, int tuoiMin,
			int tuoiMax) {
		this.maHTV = maHTV;
		this.tenHang = tenHang;
		this.donGia = donGia;
		this.phiThueSach = phiThueSach;
		this.soThangHieuLuc = soThangHieuLuc;
		this.tuoiMin = tuoiMin;
		this.tuoiMax = tuoiMax;
	}

	public HangThanhVien() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaHTV() {
		return maHTV;
	}

	public void setMaHTV(String maHTV) {
		this.maHTV = maHTV;
	}

	public String getTenHang() {
		return tenHang;
	}

	public void setTenHang(String tenHang) {
		this.tenHang = tenHang;
	}

	public float getDonGia() {
		return donGia;
	}

	public void setDonGia(float donGia) {
		this.donGia = donGia;
	}

	public float getPhiThueSach() {
		return phiThueSach;
	}

	public void setPhiThueSach(float phiThueSach) {
		this.phiThueSach = phiThueSach;
	}

	public int getSoThangHieuLuc() {
		return soThangHieuLuc;
	}

	public void setSoThangHieuLuc(int soThangHieuLuc) {
		this.soThangHieuLuc = soThangHieuLuc;
	}

	public int getTuoiMin() {
		return tuoiMin;
	}

	public void setTuoiMin(int tuoiMin) {
		this.tuoiMin = tuoiMin;
	}

	public int getTuoiMax() {
		return tuoiMax;
	}

	public void setTuoiMax(int tuoiMax) {
		this.tuoiMax = tuoiMax;
	}

	@Override
	public String toString() {
		return tenHang;
	}

}
