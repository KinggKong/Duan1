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

}
