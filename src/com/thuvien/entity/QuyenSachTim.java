package com.thuvien.entity;

public class QuyenSachTim {
	private QuyenSach maQuyenSacch;
	private QuyenSach tenSach;
	private String daySo, nganSo, soViTri;

	public QuyenSachTim(QuyenSach maQuyenSacch, QuyenSach tenSach, String daySo, String nganSo, String soViTri) {
		this.maQuyenSacch = maQuyenSacch;
		this.tenSach = tenSach;
		this.daySo = daySo;
		this.nganSo = nganSo;
		this.soViTri = soViTri;
	}

	public QuyenSachTim() {
	}

	public QuyenSach getMaQuyenSacch() {
		return maQuyenSacch;
	}

	public void setMaQuyenSacch(QuyenSach maQuyenSacch) {
		this.maQuyenSacch = maQuyenSacch;
	}

	public QuyenSach getTenSach() {
		return tenSach;
	}

	public void setTenSach(QuyenSach tenSach) {
		this.tenSach = tenSach;
	}

	public String getDaySo() {
		return daySo;
	}

	public void setDaySo(String daySo) {
		this.daySo = daySo;
	}

	public String getNganSo() {
		return nganSo;
	}

	public void setNganSo(String nganSo) {
		this.nganSo = nganSo;
	}

	public String getSoViTri() {
		return soViTri;
	}

	public void setSoViTri(String soViTri) {
		this.soViTri = soViTri;
	}

}
