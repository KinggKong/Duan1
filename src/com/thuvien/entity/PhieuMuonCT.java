package com.thuvien.entity;

public class PhieuMuonCT {
	private int id;
	private PhieuMuon idPhieuMuon;
	private QuyenSach idQuyenSach;

	public PhieuMuonCT(PhieuMuon idPhieuMuon, QuyenSach idQuyenSach) {
		this.idPhieuMuon = idPhieuMuon;
		this.idQuyenSach = idQuyenSach;
	}

	public PhieuMuonCT(int id, PhieuMuon idPhieuMuon, QuyenSach idQuyenSach) {
		this.id = id;
		this.idPhieuMuon = idPhieuMuon;
		this.idQuyenSach = idQuyenSach;
	}

	public PhieuMuonCT() {
	}

	public PhieuMuon getIdPhieuMuon() {
		return idPhieuMuon;
	}

	public void setIdPhieuMuon(PhieuMuon idPhieuMuon) {
		this.idPhieuMuon = idPhieuMuon;
	}

	public QuyenSach getMaQuyenSach() {
		return idQuyenSach;
	}

	public void setMaQuyenSach(QuyenSach maQuyenSach) {
		this.idQuyenSach = maQuyenSach;
	}

	@Override
	public String toString() {
		return "PhieuMuonCT [idPhieuMuon=" + idPhieuMuon + ", maQuyenSach=" + idQuyenSach + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public QuyenSach getIdQuyenSach() {
		return idQuyenSach;
	}

	public void setIdQuyenSach(QuyenSach idQuyenSach) {
		this.idQuyenSach = idQuyenSach;
	}

}
