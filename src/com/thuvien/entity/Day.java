package com.thuvien.entity;

import java.util.Objects;

public class Day {
	private String idDay;
	private String tenDay;

	public Day(String idDay, String tenDay) {
		this.idDay = idDay;
		this.tenDay = tenDay;
	}

	public Day() {
	}

	public String getIdDay() {
		return idDay;
	}

	public void setIdDay(String idDay) {
		this.idDay = idDay;
	}

	public String getTenDay() {
		return tenDay;
	}

	public void setTenDay(String tenDay) {
		this.tenDay = tenDay;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idDay, tenDay);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Day other = (Day) obj;
		return Objects.equals(idDay, other.idDay) && Objects.equals(tenDay, other.tenDay);
	}

	@Override
	public String toString() {
		return idDay + " " + tenDay;
	}

}
