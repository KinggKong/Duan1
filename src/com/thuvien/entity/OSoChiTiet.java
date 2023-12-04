package com.thuvien.entity;

import java.util.Objects;

public class OSoChiTiet {
	private String idO;
	private String tenO;
	private String parent_ID;
	private String maQS;

	public OSoChiTiet(String idO, String tenO, String parent_ID, String maQS) {
		this.idO = idO;
		this.tenO = tenO;
		this.parent_ID = parent_ID;
		this.maQS = maQS;
	}

	public OSoChiTiet() {
	}

	public String getIdO() {
		return idO;
	}

	public void setIdO(String idO) {
		this.idO = idO;
	}

	public String getTenO() {
		return tenO;
	}

	public void setTenO(String tenO) {
		this.tenO = tenO;
	}

	public String getParent_ID() {
		return parent_ID;
	}

	public void setParent_ID(String parent_ID) {
		this.parent_ID = parent_ID;
	}

	public String getMaQS() {
		return maQS;
	}

	public void setMaQS(String maQS) {
		this.maQS = maQS;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idO, maQS, parent_ID, tenO);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OSoChiTiet other = (OSoChiTiet) obj;
		return Objects.equals(idO, other.idO) && Objects.equals(maQS, other.maQS)
				&& Objects.equals(parent_ID, other.parent_ID) && Objects.equals(tenO, other.tenO);
	}

	@Override
	public String toString() {
		return idO + " " + tenO;
	}

}
