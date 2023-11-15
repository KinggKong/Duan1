package com.thuvien.entity;

import java.util.Date;

public class QuyenSach {
	private String maQS;
	private String tenSach;
	private int daySo;
	private int lanTB;
	private Date ngayTB;
	private int namXB;
	private String tenNXB, ghiChu;
	private int tinhTrang;

	public QuyenSach() {
	}

	public QuyenSach(String maQS, String tenSach, int daySo, int lanTB, Date ngayTB, int namXB, String tenNXB,
			String ghiChu, int tinhTrang) {
		this.maQS = maQS;
		this.tenSach = tenSach;
		this.daySo = daySo;
		this.lanTB = lanTB;
		this.ngayTB = ngayTB;
		this.namXB = namXB;
		this.tenNXB = tenNXB;
		this.ghiChu = ghiChu;
		this.tinhTrang = tinhTrang;
	}

	public String getMaQS() {
		return maQS;
	}

	public void setMaQS(String maQS) {
		this.maQS = maQS;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public int getDaySo() {
		return daySo;
	}

	public void setDaySo(int daySo) {
		this.daySo = daySo;
	}

	public int getLanTB() {
		return lanTB;
	}

	public void setLanTB(int lanTB) {
		this.lanTB = lanTB;
	}

	public Date getNgayTB() {
		return ngayTB;
	}

	public void setNgayTB(Date ngayTB) {
		this.ngayTB = ngayTB;
	}

	public int getNamXB() {
		return namXB;
	}

	public void setNamXB(int namXB) {
		this.namXB = namXB;
	}

	public String getTenNXB() {
		return tenNXB;
	}

	public void setTenNXB(String tenNXB) {
		this.tenNXB = tenNXB;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public int getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(int tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

}
