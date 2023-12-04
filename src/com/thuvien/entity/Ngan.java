package com.thuvien.entity;

import java.util.Objects;

public class Ngan {
	private String idNgan;
	private String tenNgan;
	private String parent_ID;

	public Ngan(String idNgan, String tenNgan, String parent_ID) {
		this.idNgan = idNgan;
		this.tenNgan = tenNgan;
		this.parent_ID = parent_ID;
	}

	public Ngan() {
	}

	public String getIdNgan() {
		return idNgan;
	}

	public void setIdNgan(String idNgan) {
		this.idNgan = idNgan;
	}

	public String getTenNgan() {
		return tenNgan;
	}

	public void setTenNgan(String tenNgan) {
		this.tenNgan = tenNgan;
	}

	public String getParent_ID() {
		return parent_ID;
	}

	public void setParent_ID(String parent_ID) {
		this.parent_ID = parent_ID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idNgan, tenNgan);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ngan other = (Ngan) obj;
		return idNgan == other.idNgan && Objects.equals(tenNgan, other.tenNgan);
	}

	@Override
	public String toString() {
		return idNgan + " " + tenNgan;
	}

}
