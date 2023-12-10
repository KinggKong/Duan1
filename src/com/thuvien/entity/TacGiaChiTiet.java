package com.thuvien.entity;

import java.util.Objects;

public class TacGiaChiTiet {
	private int id, idSach, idTacGia;
	private String vaiTro;

	public TacGiaChiTiet(int id, int idSach, int idTacGia, String vaiTro) {
		this.id = id;
		this.idSach = idSach;
		this.idTacGia = idTacGia;
		this.vaiTro = vaiTro;
	}

	public TacGiaChiTiet() {
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

	public int getIdTacGia() {
		return idTacGia;
	}

	public void setIdTacGia(int idTacGia) {
		this.idTacGia = idTacGia;
	}

	public String getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(String vaiTro) {
		this.vaiTro = vaiTro;
	}

	@Override
	public String toString() {
		return "TacGiaChiTiet [id=" + id + ", idSach=" + idSach + ", idTacGia=" + idTacGia + ", vaiTro=" + vaiTro + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, idSach, idTacGia, vaiTro);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TacGiaChiTiet other = (TacGiaChiTiet) obj;
		return id == other.id && idSach == other.idSach && idTacGia == other.idTacGia
				&& Objects.equals(vaiTro, other.vaiTro);
	}

}
