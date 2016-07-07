package com.patientinfo.server;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteData {
	
	public String deletePatient(String Id) {
		String sql = "delete from patientinfo where ID = '"+Id+"'";
		Connection conn = DB.getconn();
		Statement stmt = DB.getStatement(conn);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return "É¾³ý³É¹¦";
	}

}
