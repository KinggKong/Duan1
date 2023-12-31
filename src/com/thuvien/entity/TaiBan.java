package com.thuvien.entity;

import java.util.Date;
import java.util.Objects;

public class TaiBan {
	private int id;
	private int idSach;
	private int lanTaiBan;
	private Date thoiGianTB;

	public TaiBan(int id, int idSach, int lanTaiBan, Date thoiGianTB) {
		this.id = id;
		this.idSach = idSach;
		this.lanTaiBan = lanTaiBan;
		this.thoiGianTB = thoiGianTB;
	}

	public TaiBan(int idSach, int lanTaiBan, Date thoiGianTB) {
		this.idSach = idSach;
		this.lanTaiBan = lanTaiBan;
		this.thoiGianTB = thoiGianTB;
	}

	public TaiBan() {
	}

	public int getIdSach() {
		return idSach;
	}

	public void setIdSach(int idSach) {
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
		return Objects.hash(id, lanTaiBan);
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
		return id == other.id && lanTaiBan == other.lanTaiBan;
	}



}
