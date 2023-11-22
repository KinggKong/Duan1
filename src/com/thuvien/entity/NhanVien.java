package com.thuvien.entity;

public class NhanVien {
	private int id;
	private String tenNV;
	private String maNV;
	private String sdt;
	private String userName;
	private String passWord;
	private boolean vaiTro;
	private boolean trangThai;

	public NhanVien(int id, String tenNV, String maNV, String sdt, String userName, String passWord, boolean vaiTro,
			boolean trangThai) {
		this.id = id;
		this.tenNV = tenNV;
		this.maNV = maNV;
		this.sdt = sdt;
		this.userName = userName;
		this.passWord = passWord;
		this.vaiTro = vaiTro;
		this.trangThai = trangThai;
	}

	public NhanVien(String tenNV, String maNV, String sdt, String userName, String passWord, boolean vaiTro,
			boolean trangThai) {
		this.tenNV = tenNV;
		this.maNV = maNV;
		this.sdt = sdt;
		this.userName = userName;
		this.passWord = passWord;
		this.vaiTro = vaiTro;
		this.trangThai = trangThai;
	}

	public NhanVien() {
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public boolean isVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(boolean vaiTro) {
		this.vaiTro = vaiTro;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return maNV + " " + tenNV;
	}

}
