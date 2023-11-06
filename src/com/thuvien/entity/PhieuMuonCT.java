package com.thuvien.entity;

public class PhieuMuonCT {
	private int idPhieuMuon, idQuyenSach;

	public PhieuMuonCT(int idPhieuMuon, int idQuyenSach) {
		this.idPhieuMuon = idPhieuMuon;
		this.idQuyenSach = idQuyenSach;
	}

	public PhieuMuonCT() {
	}

	public int getIdPhieuMuon() {
		return idPhieuMuon;
	}

	public void setIdPhieuMuon(int idPhieuMuon) {
		this.idPhieuMuon = idPhieuMuon;
	}

	public int getIdQuyenSach() {
		return idQuyenSach;
	}

	public void setIdQuyenSach(int idQuyenSach) {
		this.idQuyenSach = idQuyenSach;
	}

	@Override
	public String toString() {
		return "PhieuMuonCT [idPhieuMuon=" + idPhieuMuon + ", idQuyenSach=" + idQuyenSach + "]";
	}

}
