package com.login.server;

import java.sql.Connection;
import java.sql.Statement;

public class AddUserData {
	private DNLoger data;

	public AddUserData(DNLoger data) {
		this.data = data;
	}

	public String addUser() {
		String sql = "insert into user (name, password, Sex, ZY) values('"
				+ data.getName() + "', '" + data.getPassword() + "', '"
				+ data.getSex() + "', '" + data.getCareer() + "')";
		Connection conn = DB.getconn();
		Statement stmt = DB.getStatement(conn);
		try {
			DB.getUpdate(stmt, sql);
			return "�����ɹ�";
		}catch(Exception e) {
			e.printStackTrace();
			return "���ʧ��";
		}finally {
			DB.close(conn);
			DB.close(stmt);
		}
	}

}
