package com.thuvien.entity;

public class QuyenSach {
	private String maQS;
	private int idViTri, idTaiBan;
	private String tinhTrang;

	public QuyenSach(String maQS, int idViTri, int idTaiBan, String tinhTrang) {
		this.maQS = maQS;
		this.idViTri = idViTri;
		this.idTaiBan = idTaiBan;
		this.tinhTrang = tinhTrang;
	}

	public QuyenSach() {
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

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	@Override
	public String toString() {
		return "QuyenSach [maQS=" + maQS + ", idViTri=" + idViTri + ", idTaiBan=" + idTaiBan + ", tinhTrang="
				+ tinhTrang + "]";
	}

}
