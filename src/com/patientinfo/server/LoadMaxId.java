package com.patientinfo.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoadMaxId {
	public static String ID;
	
	public LoadMaxId() {}

	public static String selectMaxId() {
		String sql = "SELECT ID from patientinfo where ID = (SELECT max(ID) FROM patientinfo); ";
		Connection conn = DB.getconn();
		Statement stmt = DB.getStatement(conn);
		ResultSet rs = DB.getResultSet(stmt, sql);
		
		try {
			while (rs.next()) {
				ID = rs.getString("ID");
			}
			return ID;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
