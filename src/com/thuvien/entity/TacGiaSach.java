package com.thuvien.entity;

public class TacGiaSach {
	private int idSach, inTacGia;
	private String vaiTro;

	public TacGiaSach(int idSach, int inTacGia, String vaiTro) {
		this.idSach = idSach;
		this.inTacGia = inTacGia;
		this.vaiTro = vaiTro;
	}

	public TacGiaSach() {
	}

	public int getIdSach() {
		return idSach;
	}

	public void setIdSach(int idSach) {
		this.idSach = idSach;
	}

	public int getInTacGia() {
		return inTacGia;
	}

	public void setInTacGia(int inTacGia) {
		this.inTacGia = inTacGia;
	}

	public String getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(String vaiTro) {
		this.vaiTro = vaiTro;
	}

	@Override
	public String toString() {
		return "TacGiaSach [idSach=" + idSach + ", inTacGia=" + inTacGia + ", vaiTro=" + vaiTro + "]";
	}

}
