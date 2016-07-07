package com.patientinfo.server;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateData {
	PatientInfo patient;
	
	public String updatePatuient(PatientInfo patient) {
		String sql = "update patientinfo set patientinfo.Name = '"+patient.getName()+"', patientinfo.Age = '"+patient.getAge()+"', " +
				"patientinfo.Sex = '"+patient.getSex()+"', patientinfo.LXFS = '"+patient.getPhone()+"', patientinfo.Brzz = '"+patient.getBrzz()+"'," +
				"patientinfo.YZ = '"+patient.getYz()+"', patientinfo.ZYBCH = '"+patient.getZybch()+"'," +
						"patientinfo.HYZK = '"+patient.getHyzk()+"', patientinfo.YXCK = '"+patient.getYxck()+"'," +
								"patientinfo.TSSM = '"+patient.getTssm()+"', patientinfo.SHZ = '"+patient.getShz()+"', " +
										"patientinfo.SHTIME = '"+patient.getShsj()+"' where patientinfo.ID = '"+patient.getId()+"' ";
		Connection conn = DB.getconn();
		Statement stmt = DB.getStatement(conn);
		
		try {
			stmt.executeUpdate(sql);
			return "ÐÞ¸Ä³É¹¦";
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
		
		return "";
	}

}
