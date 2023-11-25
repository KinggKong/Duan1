package com.thuvien.entity;

import java.util.Date;
import java.util.Objects;

public class TaiBan {
	private int id;
	private Sach idSach;
	private int lanTaiBan;
	private Date thoiGianTB;

	public TaiBan(int id, Sach idSach, int lanTaiBan, Date thoiGianTB) {
		this.id = id;
		this.idSach = idSach;
		this.lanTaiBan = lanTaiBan;
		this.thoiGianTB = thoiGianTB;
	}

	public TaiBan(Sach idSach, int lanTaiBan, Date thoiGianTB) {
		this.idSach = idSach;
		this.lanTaiBan = lanTaiBan;
		this.thoiGianTB = thoiGianTB;
	}

	public TaiBan() {
	}

	public Sach getIdSach() {
		return idSach;
	}

	public void setIdSach(Sach idSach) {
		this.idSach = idSach;
	}

	public int getLanTaiBan() {
		return lanTaiBan;
	}

	public void setLanTaiBan(int lanTaiBan) {
		this.lanTaiBan = lanTaiBan;
	}

	public Date getThoiGianTB() {
		return thoiGianTB;
	}

	public void setThoiGianTB(Date thoiGianTB) {
		this.thoiGianTB = thoiGianTB;
	}

	@Override
	public String toString() {
		return lanTaiBan + "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, idSach, lanTaiBan);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiBan other = (TaiBan) obj;
		return id == other.id && Objects.equals(idSach, other.idSach) && lanTaiBan == other.lanTaiBan;
	}

}
