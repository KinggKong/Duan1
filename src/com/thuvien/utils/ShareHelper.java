package com.thuvien.utils;

import com.thuvien.entity.NhanVien;

public class ShareHelper {

	public static NhanVien USER = null;

	public static void logoff() {
		ShareHelper.USER = null;
	}

	public static boolean authenticated() {
		return ShareHelper.USER != null;
	}

	public static int idNhanVien() {
		return USER.getId();
	}

	public static boolean isManager() {
		return Auth.isLogin() && USER.isVaiTro();
	}
}
