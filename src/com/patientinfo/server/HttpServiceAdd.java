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
 * ���Ӳ�����Ϣ�ӿ�
 * @author xudawang
 *
 */
public class HttpServiceAdd extends HttpServlet {
	private PatientInfo patient_add = new PatientInfo();
	private AddDatas datas = new AddDatas();
	private JSONObject json_send = new JSONObject();
	private String sendMessage;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String result = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(ServletInputStream) req.getInputStream(), "utf-8"));
			StringBuffer sb = new StringBuffer();
			String temp = "";
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			br.close();
			result = sb.toString();
			System.out.println("����ʵ��:" + result);
			JSONTokener json = new JSONTokener(result);
			JSONObject p_Info = (JSONObject) json.nextValue();
			
			
			String ID = p_Info.getJSONObject("������Ϣ").getString("Id");
			// ������������ʵ��ת��Ϊbean
			addPatientInfo(p_Info);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(json_send.toString());
			PrintWriter pw = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(
							(ServletOutputStream) resp.getOutputStream(),
							"utf-8")), true);
			pw.println(json_send.toString());
			pw.flush();
			pw.close();

		}

	}

	private void addPatientInfo(JSONObject p_Info) throws JSONException {
		String Id = p_Info.getJSONObject("������Ϣ").getString("Id");
		patient_add.setId(Id);
		patient_add.setName(p_Info.getJSONObject("������Ϣ").getString("Name"));
		patient_add.setAge(p_Info.getJSONObject("������Ϣ").getString("Age"));
		patient_add.setSex(p_Info.getJSONObject("������Ϣ").getString("Sex"));
		patient_add.setPhone(p_Info.getJSONObject("������Ϣ").getString("Lxfs"));
		patient_add.setBrzz(p_Info.getJSONObject("������Ϣ").getString("Brzz"));
		patient_add.setYz(p_Info.getJSONObject("������Ϣ").getString("Yz"));
		patient_add.setZybch(p_Info.getJSONObject("������Ϣ").getString("Zybch"));
		patient_add.setHyzk(p_Info.getJSONObject("������Ϣ").getString("Hyzk"));
		patient_add.setYxck(p_Info.getJSONObject("������Ϣ").getString("Yxck"));
		patient_add.setTssm(p_Info.getJSONObject("������Ϣ").getString("Tssm"));
		patient_add.setShz(p_Info.getJSONObject("������Ϣ").getString("Shz"));
		patient_add.setShsj(p_Info.getJSONObject("������Ϣ").getString("Shsj"));

		sendMessage = datas.addPatient(patient_add);
		json_send.put("��ӱ�־", sendMessage);
	}
}
