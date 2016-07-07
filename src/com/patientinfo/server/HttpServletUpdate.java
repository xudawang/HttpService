package com.patientinfo.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * 修改数据接口
 * @author xudawang
 *
 */
public class HttpServletUpdate extends HttpServlet {
	private PatientInfo patient = new PatientInfo();
	private UpdateData updateData = new UpdateData();
	private JSONObject json_send = new JSONObject();
	private String sendMessage;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String result = "";
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader((ServletInputStream)req.getInputStream(), "utf-8"));
			StringBuffer sb = new StringBuffer();
			String temp = "";
			while((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			br.close();
			result = sb.toString();
			
			JSONTokener json = new JSONTokener(result);
			JSONObject p_update = (JSONObject) json.nextValue();
			updatePatientInfo(p_update);

			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			PrintWriter pw = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									(ServletOutputStream)resp.getOutputStream(), 
									"utf-8")), true);
			pw.println(json_send.toString());
			pw.flush();
			pw.close();
			
		}
	}


	private void updatePatientInfo(JSONObject p_update) throws JSONException {
		String Id = p_update.getJSONObject("病人信息").getString("Id");
		patient.setId(Id);
		patient.setName(p_update.getJSONObject("病人信息").getString("Name"));
		patient.setAge(p_update.getJSONObject("病人信息").getString("Age"));
		patient.setSex(p_update.getJSONObject("病人信息").getString("Sex"));
		patient.setPhone(p_update.getJSONObject("病人信息").getString("Lxfs"));
		patient.setBrzz(p_update.getJSONObject("病人信息").getString("Brzz"));
		patient.setYz(p_update.getJSONObject("病人信息").getString("Yz"));
		patient.setZybch(p_update.getJSONObject("病人信息").getString("Zybch"));
		patient.setHyzk(p_update.getJSONObject("病人信息").getString("Hyzk"));
		patient.setYxck(p_update.getJSONObject("病人信息").getString("Yxck"));
		patient.setTssm(p_update.getJSONObject("病人信息").getString("Tssm"));
		patient.setShz(p_update.getJSONObject("病人信息").getString("Shz"));
		patient.setShsj(p_update.getJSONObject("病人信息").getString("Shsj"));
		
		sendMessage = updateData.updatePatuient(patient);
		json_send.put("修改标志", sendMessage);
	}

}
