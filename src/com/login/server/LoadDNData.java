package com.login.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoadDNData {
	public static DNLoger dnLoger = new DNLoger();

	public String loadDNDate(String name, String password) {
		dnLoger.clear();
		String sql = "select * from user where name = '" + name
				+ "' and password = '" + password + "'";
		Connection conn = DB.getconn();
		Statement stmt = DB.getStatement(conn);
		try {
			ResultSet rs = DB.getResultSet(stmt, sql);

			while (rs.next()) {
				String nameTest = rs.getString("name");
				dnLoger.setName(rs.getString("name"));
				dnLoger.setPassword(rs.getString("password"));
				dnLoger.setSex(rs.getString("Sex"));
				dnLoger.setCareer(rs.getString("ZY"));
			}
			DB.close(rs);
			
			if(dnLoger.getName().equals("")) {
				return "登录失败，请重新输入";
			}else {
				return "登录成功";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.close(conn);
			DB.close(stmt);
		}
		return "";

	}

}
