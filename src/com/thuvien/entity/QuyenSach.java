package com.thuvien.entity;

import java.util.Date;
import java.util.Objects;

public class QuyenSach {
	private int id;
	private String maQS;
	private String tenQS;
	private int idTaiBan;
	private int tinhTrang;
	private String ghiChu;

	public QuyenSach(int id, String maQS, String tenQS, int idTaiBan, int tinhTrang, String ghiChu) {
		this.id = id;
		this.maQS = maQS;
		this.tenQS = tenQS;
		this.idTaiBan = idTaiBan;
		this.tinhTrang = tinhTrang;
		this.ghiChu = ghiChu;
	}

	public QuyenSach() {
	}

	public QuyenSach(String maQS, String tenQS, int idTaiBan, int tinhTrang, String ghiChu) {
		this.maQS = maQS;
		this.tenQS = tenQS;
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

//	public TaiBan getIdTaiBan() {
//		return idTaiBan;
//	}
//
//	public void setIdTaiBan(TaiBan idTaiBan) {
//		this.idTaiBan = idTaiBan;
//	}

	public int getTinhTrang() {
		return tinhTrang;
	}

	public int getIdTaiBan() {
		return idTaiBan;
	}

	public void setIdTaiBan(int idTaiBan) {
		this.idTaiBan = idTaiBan;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return maQS + " " + tenQS;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, maQS);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuyenSach other = (QuyenSach) obj;
		return id == other.id && Objects.equals(maQS, other.maQS);
	}

}
