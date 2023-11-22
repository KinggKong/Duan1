package com.thuvien.entity;

import java.util.Objects;

public class ViTri {

	private int ID;
	private String day;

	public ViTri(int iD, String day) {
		ID = iD;
		this.day = day;
	}

	public ViTri() {
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return day;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID, day);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ViTri other = (ViTri) obj;
		return ID == other.ID && Objects.equals(day, other.day);
	}

}
