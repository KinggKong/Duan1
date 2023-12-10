package com.thuvien.entity;

import java.util.Objects;

public class TheLoaiCT {
	private int id,idSach,idTheLoai;

	public TheLoaiCT(int id, int idSach, int idTheLoai) {
		this.id = id;
		this.idSach = idSach;
		this.idTheLoai = idTheLoai;
	}

	public TheLoaiCT(int idSach, int idTheLoai) {
		this.idSach = idSach;
		this.idTheLoai = idTheLoai;
	}

	public TheLoaiCT() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdSach() {
		return idSach;
	}

	public void setIdSach(int idSach) {
		this.idSach = idSach;
	}

	public int getIdTheLoai() {
		return idTheLoai;
	}

	public void setIdTheLoai(int idTheLoai) {
		this.idTheLoai = idTheLoai;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, idSach, idTheLoai);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TheLoaiCT other = (TheLoaiCT) obj;
		return id == other.id && idSach == other.idSach && idTheLoai == other.idTheLoai;
	}
	
}
