package com.thuvien.entity;

import java.util.Date;

public class QuyenSach {
	private String maQS;
	private int idViTri;
	private int idTaiBan;
	private int tinhTrang;
	private String ghiChu;

	public QuyenSach() {
	}

	public QuyenSach(String maQS, int idViTri, int idTaiBan, int tinhTrang, String ghiChu) {
		this.maQS = maQS;
		this.idViTri = idViTri;
		this.idTaiBan = idTaiBan;
		this.tinhTrang = tinhTrang;
		this.ghiChu = ghiChu;
	}

	public String getMaQS() {
		return maQS;
	}

	public void setMaQS(String maQS) {
		this.maQS = maQS;
	}

	public int getIdViTri() {
		return idViTri;
	}

	public void setIdViTri(int idViTri) {
		this.idViTri = idViTri;
	}

	public int getIdTaiBan() {
		return idTaiBan;
	}

	public void setIdTaiBan(int idTaiBan) {
		this.idTaiBan = idTaiBan;
	}

	public int getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(int tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

}
