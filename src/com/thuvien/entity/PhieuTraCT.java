package com.thuvien.entity;

public class PhieuTraCT {
	private int id;
	private PhieuMuonCT idPhieuMuonCT;
	private PhieuTra idPhieuTra;
	private boolean tinhTrangSach;
	private String ghiChu;

	public PhieuTraCT(int id, PhieuMuonCT idPhieuMuonCT, PhieuTra idPhieuTra, boolean tinhTrangSach, String ghiChu) {
		this.id = id;
		this.idPhieuMuonCT = idPhieuMuonCT;
		this.idPhieuTra = idPhieuTra;
		this.tinhTrangSach = tinhTrangSach;
		this.ghiChu = ghiChu;
	}

	public PhieuTraCT() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PhieuMuonCT getIdPhieuMuonCT() {
		return idPhieuMuonCT;
	}

	public void setIdPhieuMuonCT(PhieuMuonCT idPhieuMuonCT) {
		this.idPhieuMuonCT = idPhieuMuonCT;
	}

	public PhieuTra getIdPhieuTra() {
		return idPhieuTra;
	}

	public void setIdPhieuTra(PhieuTra idPhieuTra) {
		this.idPhieuTra = idPhieuTra;
	}

	public boolean isTinhTrangSach() {
		return tinhTrangSach;
	}

	public void setTinhTrangSach(boolean tinhTrangSach) {
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
