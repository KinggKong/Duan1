
package com.thuvien.utils;

import com.thuvien.entity.NhanVien;

public class Auth {

	public static NhanVien user = null;

	public static void clear() {
		Auth.user = null;
	}

	public static boolean isLogin() {
		return Auth.user != null;
	}

	public static String maNhanVien() {
		return user.getMaNV();
	}

	public static boolean isManager() {
		return Auth.isLogin() && user.isVaiTro();
	}
}
