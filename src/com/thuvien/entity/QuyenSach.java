package com.thuvien.entity;

import java.util.Date;

public class QuyenSach {
	private String maQS;
	private String tenQS;
	private ViTri idViTri;
	private TaiBan idTaiBan;
	private int tinhTrang;
	private String ghiChu;

	public QuyenSach() {
	}

	public QuyenSach(String maQS, String tenQS, ViTri idViTri, TaiBan idTaiBan, int tinhTrang, String ghiChu) {
		this.maQS = maQS;
		this.tenQS = tenQS;
		this.idViTri = idViTri;
		this.idTaiBan = idTaiBan;
		this.tinhTrang = tinhTrang;
		this.ghiChu = ghiChu;
	}

	public String getTenQS() {
		return tenQS;
	}

	public void setTenQS(String tenQS) {
		this.tenQS = tenQS;
	}

	public String getMaQS() {
		return maQS;
	}

	public void setMaQS(String maQS) {
		this.maQS = maQS;
	}

	public ViTri getIdViTri() {
		return idViTri;
	}

	public void setIdViTri(ViTri idViTri) {
		this.idViTri = idViTri;
	}

	public TaiBan getIdTaiBan() {
		return idTaiBan;
	}

	public void setIdTaiBan(TaiBan idTaiBan) {
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
