package com.thuvien.entity;

import java.util.Objects;

public class TheLoai {
	private int id;
	private String maTL, tenTL;

	public TheLoai(String maTL, String tenTL) {
		this.maTL = maTL;
		this.tenTL = tenTL;
	}

	public TheLoai(int id, String maTL, String tenTL) {
		this.id = id;
		this.maTL = maTL;
		this.tenTL = tenTL;
	}

	public TheLoai() {
	}

	public String getMaTL() {
		return maTL;
	}

	public void setMaTL(String maTL) {
		this.maTL = maTL;
	}

	public String getTenTL() {
		return tenTL;
	}

	public void setTenTL(String tenTL) {
		this.tenTL = tenTL;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return maTL + " " + tenTL;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, maTL, tenTL);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TheLoai other = (TheLoai) obj;
		return id == other.id && Objects.equals(maTL, other.maTL) && Objects.equals(tenTL, other.tenTL);
	}

}
