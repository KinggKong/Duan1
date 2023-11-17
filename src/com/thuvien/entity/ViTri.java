package com.thuvien.entity;

public class ViTri {

	private int daySo, iD;

	public ViTri(int daySo, int iD) {
		this.daySo = daySo;
		this.iD = iD;
	}

	public ViTri() {
	}

	public int getDaySo() {
		return daySo;
	}

	public void setDaySo(int daySo) {
		this.daySo = daySo;
	}

	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	@Override
	public String toString() {
		return "ViTri [daySo=" + daySo + "]";
	}

}
