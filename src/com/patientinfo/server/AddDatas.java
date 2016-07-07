package com.patientinfo.server;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddDatas {
	private PatientInfo patient;
	private String id;

	public String addPatient(PatientInfo patient) {
		this.patient = patient;
		id = LoadMaxId.selectMaxId();
		if(!(patient.getId().compareTo(id) > 0)) {
			id = String.valueOf((Integer.parseInt(id) + 1));
			patient.setId(id);
		}
		
		String sql = "insert into patientinfo (ID,Name,Age,Sex,LXFS,Brzz,YZ,ZYBCH,HYZK,YXCK,TSSM,SHZ,SHTIME)"
				+ " values('"
				+ patient.getId().toString()
				+ "', "
				+ "'"
				+ patient.getName().toString()
				+ "', "
				+ "'"
				+ patient.getAge().toString()
				+ "',"
				+ " '"
				+ patient.getSex().toString()
				+ "', "
				+ "'"
				+ patient.getPhone().toString()
				+ "', "
				+ "'"
				+ patient.getBrzz().toString()
				+ "', "
				+ "'"
				+ patient.getYz().toString()
				+ "',"
				+ "'"
				+ patient.getZybch().toString()
				+ "', "
				+ "'"
				+ patient.getHyzk().toString()
				+ "', "
				+ "'"
				+ patient.getYxck().toString()
				+ "', "
				+ "'"
				+ patient.getTssm().toString()
				+ "',"
				+ "'"
				+ patient.getShz().toString()
				+ "', "
				+ "'"
				+ patient.getShsj().toString() + "')";
		Connection conn = DB.getconn();
		Statement stmt = DB.getStatement(conn);
		try {
			stmt.executeUpdate(sql);
			return "添加成功";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "添加失败";
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
