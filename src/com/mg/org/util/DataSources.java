package com.mg.org.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mh.value.ConValues;

public class DataSources {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(ConValues.sqlClass);
			conn = DriverManager.getConnection(ConValues.sqlUrl,ConValues.sqlUser,ConValues.sqlPass);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void doClose(ResultSet rs,PreparedStatement pstmt,Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
		}
	}
}
