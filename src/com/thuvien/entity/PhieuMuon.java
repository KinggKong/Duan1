package com.thuvien.entity;

import java.util.Date;

public class PhieuMuon {
	private int id;
	private String maPhieuMuon;
	private ThanhVien idThanhVien;
	private NhanVien idNhanVien;
	private Date ngayMuon;
	private Date ngayPhaiTra;
	private float tienCoc;

	public PhieuMuon(int id, String maPhieuMuon, ThanhVien idThanhVien, NhanVien idNhanVien, Date ngayMuon,
			Date ngayPhaiTra, float tienCoc) {
		this.id = id;
		this.maPhieuMuon = maPhieuMuon;
		this.idThanhVien = idThanhVien;
		this.idNhanVien = idNhanVien;
		this.ngayMuon = ngayMuon;
		this.ngayPhaiTra = ngayPhaiTra;
		this.tienCoc = tienCoc;
	}

	public PhieuMuon(String maPhieuMuon, ThanhVien idThanhVien, NhanVien idNhanVien, Date ngayMuon, Date ngayPhaiTra,
			float tienCoc) {
		this.maPhieuMuon = maPhieuMuon;
		this.idThanhVien = idThanhVien;
		this.idNhanVien = idNhanVien;
		this.ngayMuon = ngayMuon;
		this.ngayPhaiTra = ngayPhaiTra;
		this.tienCoc = tienCoc;
	}

	public PhieuMuon() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ThanhVien getIdThanhVien() {
		return idThanhVien;
	}

	public void setIdThanhVien(ThanhVien idThanhVien) {
		this.idThanhVien = idThanhVien;
	}

	public NhanVien getIdNhanVien() {
		return idNhanVien;
	}

	public void setIdNhanVien(NhanVien idNhanVien) {
		this.idNhanVien = idNhanVien;
	}

	public Date getNgayMuon() {
		return ngayMuon;
	}

	public void setNgayMuon(Date ngayMuon) {
		this.ngayMuon = ngayMuon;
	}

	public Date getNgayPhaiTra() {
		return ngayPhaiTra;
	}

	public void setNgayPhaiTra(Date ngayPhaiTra) {
		this.ngayPhaiTra = ngayPhaiTra;
	}

	public float getTienCoc() {
		return tienCoc;
	}

	public void setTienCoc(float tienCoc) {
		this.tienCoc = tienCoc;
	}

	public String getMaPhieuMuon() {
		return maPhieuMuon;
	}

	public void setMaPhieuMuon(String maPhieuMuon) {
		this.maPhieuMuon = maPhieuMuon;
	}

	@Override
	public String toString() {
		return "PhieuMuon [idThanhVien=" + idThanhVien + ", idNhanVien=" + idNhanVien + ", ngayMuon=" + ngayMuon
				+ ", ngayPhaiTra=" + ngayPhaiTra + ", tienCoc=" + tienCoc + "]";
	}

}
