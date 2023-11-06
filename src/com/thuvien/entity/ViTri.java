package com.thuvien.entity;

public class ViTri {
	private int daySo;

	public ViTri(int daySo) {
		this.daySo = daySo;
	}

	public ViTri() {
	}

	public int getDaySo() {
		return daySo;
	}

	public void setDaySo(int daySo) {
		this.daySo = daySo;
	}

	@Override
	public String toString() {
		return "ViTri [daySo=" + daySo + "]";
	}

}
