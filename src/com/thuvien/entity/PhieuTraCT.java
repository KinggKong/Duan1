package com.thuvien.entity;

public class PhieuTraCT {
	private int idPhieuMuonCT;
	private int idPhieuTra;
	private String tinhTrangSach, ghiChu;

	public PhieuTraCT(int idPhieuMuonCT, int idPhieuTra, String tinhTrangSach, String ghiChu) {
		this.idPhieuMuonCT = idPhieuMuonCT;
		this.idPhieuTra = idPhieuTra;
		this.tinhTrangSach = tinhTrangSach;
		this.ghiChu = ghiChu;
	}

	public PhieuTraCT() {
	}

	public int getIdPhieuMuonCT() {
		return idPhieuMuonCT;
	}

	public void setIdPhieuMuonCT(int idPhieuMuonCT) {
		this.idPhieuMuonCT = idPhieuMuonCT;
	}

	public int getIdPhieuTra() {
		return idPhieuTra;
	}

	public void setIdPhieuTra(int idPhieuTra) {
		this.idPhieuTra = idPhieuTra;
	}

	public String getTinhTrangSach() {
		return tinhTrangSach;
	}

	public void setTinhTrangSach(String tinhTrangSach) {
		this.tinhTrangSach = tinhTrangSach;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@Override
	public String toString() {
		return "PhieuTraCT [idPhieuMuonCT=" + idPhieuMuonCT + ", idPhieuTra=" + idPhieuTra + ", tinhTrangSach="
				+ tinhTrangSach + ", ghiChu=" + ghiChu + "]";
	}

}
