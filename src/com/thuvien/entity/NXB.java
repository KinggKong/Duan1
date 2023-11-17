package com.thuvien.entity;

public class NXB {
	private int id;
	private String tenNXB;

	public NXB(int id, String tenNXB) {
		this.id = id;
		this.tenNXB = tenNXB;
	}

	public NXB(String tenNXB) {
		this.tenNXB = tenNXB;
	}

	public NXB() {
	}

	public String getTenNXB() {
		return tenNXB;
	}

	public void setTenNXB(String tenNXB) {
		this.tenNXB = tenNXB;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return tenNXB;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		NXB other = (NXB) obj;
		// So sánh bằng tên NXB
		return this.tenNXB != null && this.tenNXB.equalsIgnoreCase(other.tenNXB);
	}

}
