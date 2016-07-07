package com.patientinfo.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoadData {
	private List<PatientInfo> datas = new ArrayList<PatientInfo>();
	private String id;
	private String name;
	private PatientInfo patient;

	public List<PatientInfo> selectDatas(String id) {
		this.id = id;
		String sql = "select * from patientinfo where ID = '" + id + "'";

		Connection conn = DB.getconn();
		Statement stmt = DB.getStatement(conn);
		ResultSet rs = DB.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				setPatientDatas(rs);
				return datas;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datas;

	}

	private void setPatientDatas(ResultSet rs) throws SQLException {
		patient = new PatientInfo();
		patient.setName(rs.getString("Name"));
		patient.setAge(rs.getString("Age"));
		patient.setSex(rs.getString("Sex"));
		patient.setId(rs.getString("ID"));
		patient.setPhone(rs.getString("LXFS"));
		patient.setBrzz(rs.getString("Brzz"));
		patient.setYz(rs.getString("YZ"));
		patient.setZybch(rs.getString("ZYBCH"));
		patient.setHyzk(rs.getString("HYZK"));
		patient.setYxck(rs.getString("YXCK"));
		patient.setTssm(rs.getString("TSSM"));
		patient.setShz(rs.getString("SHZ"));
		patient.setShsj(rs.getString("SHTIME"));
		datas.add(patient);
	}

	public List<PatientInfo> selectDatas_name(String name) {
		this.name = name;
		String sql = "select * from patientinfo where Name = '" + name + "'";

		Connection conn = DB.getconn();
		Statement stmt = DB.getStatement(conn);
		ResultSet rs = DB.getResultSet(stmt, sql);

		try {
			datas.clear();
			while (rs.next()) {
				setPatientDatas(rs);
				datas.add(patient);
				return datas;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.cancel();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return datas;

	}

	public List<PatientInfo> selectDatas_ALL() {
		String sql = "select * from patientinfo";

		Connection conn = DB.getconn();
		Statement stmt = DB.getStatement(conn);
		ResultSet rs = DB.getResultSet(stmt, sql);

		try {
			datas.clear();
			while (rs.next()) {
				setPatientDatas(rs);
			}
			return datas;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.cancel();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return datas;
	}
	
	public List<PatientInfo> selectDatas_New() {
		return datas;
		
	}


}
