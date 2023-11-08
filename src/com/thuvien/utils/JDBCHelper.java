package com.thuvien.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCHelper {
	public static Connection getConnection() {
		Connection con = null;
		try {
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			String url = "jdbc:sqlserver://localhost:1433;databaseName=QUANLITHUVIEN;encrypt=false";
			String user = "sa";
			String pass = "sa123";
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		} else {
			System.out.println("huy ket noi that bai");
		}
	}

	public static PreparedStatement getPreparedStatement(String sql, Object... args) throws SQLException {
		Connection con = JDBCHelper.getConnection();
		PreparedStatement pstmt = null;
		if (sql.trim().startsWith("{")) {
			pstmt = con.prepareCall(sql);
		} else {
			pstmt = con.prepareStatement(sql);
		}
		for (int i = 0; i < args.length; i++) {
			pstmt.setObject(i + 1, args[i]);
		}
		return pstmt;
	}

	public static void executeUpdate(String sql, Object... args) {
		try {
			PreparedStatement pstmt = getPreparedStatement(sql, args);
			try {
				pstmt.executeUpdate();
			} finally {
				pstmt.getConnection().close();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static ResultSet executeQuery(String sql, Object... args) {
		try {
			PreparedStatement stmt = getPreparedStatement(sql, args);
			return stmt.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
