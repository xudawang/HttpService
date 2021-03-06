package com.login.server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DB {
	public static Connection getconn() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1352148395");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static Statement getStatement(Connection conn) {
		Statement stmt = null;
		try {
			if(conn != null) {
			stmt = conn.createStatement();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return stmt;
	}
	
	public static ResultSet getResultSet(Statement stmt, String sql) {
		ResultSet rs = null;
		
		try {
			if(stmt != null) {
			rs = stmt.executeQuery(sql);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public static void getUpdate(Statement stmt, String sql) {
		try {
			if(stmt != null) {
				stmt.executeUpdate(sql);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt != null) {
				stmt.close();
				stmt = null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
				rs = null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
				conn = null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
