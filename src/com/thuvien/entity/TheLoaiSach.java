package com.thuvien.entity;

public class TheLoaiSach {
	private int idTheLoai, idSach;

	public TheLoaiSach(int idTheLoai, int idSach) {
		this.idTheLoai = idTheLoai;
		this.idSach = idSach;
	}

	public TheLoaiSach() {
	}

	public int getIdTheLoai() {
		return idTheLoai;
	}

	public void setIdTheLoai(int idTheLoai) {
		this.idTheLoai = idTheLoai;
	}

	public int getIdSach() {
		return idSach;
	}

	public void setIdSach(int idSach) {
		this.idSach = idSach;
	}

	@Override
	public String toString() {
		return "TheLoaiSach [idTheLoai=" + idTheLoai + ", idSach=" + idSach + "]";
	}

}
