package com.thuvien.utils;

import java.awt.Component;

import javax.swing.JOptionPane;

public class DialogHelper {
	public static void alert(Component parent, String content) {
		JOptionPane.showMessageDialog(parent, content, "Quản Lý Thư Viện", JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean confirm(Component parent, String content) {
		int choose = JOptionPane.showConfirmDialog(parent, content, "Quản Lý Thư Viện", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		return choose == JOptionPane.YES_OPTION;
	}

	public static String promt(Component parent, String content) {
		return JOptionPane.showInputDialog(parent, content, "Quản Lý Thư Viện", JOptionPane.INFORMATION_MESSAGE);
	}
}
