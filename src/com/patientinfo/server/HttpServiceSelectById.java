package com.patientinfo.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
 * ��ѯ������Ϣ�ӿ�
 * @author xudawang
 *
 */
public class HttpServiceSelectById extends HttpServlet {
	private LoadData loadData = new LoadData();
	private PatientInfo patient;

	private List<PatientInfo> datas = new ArrayList<PatientInfo>();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		selectById(req, resp);
	}

	private void selectById(HttpServletRequest req, HttpServletResponse resp)
			throws UnsupportedEncodingException, IOException {
		JSONObject json_send = new JSONObject();
		String id = "";
		String result = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(ServletInputStream) req.getInputStream(), "utf-8"));
			StringBuffer sb = new StringBuffer("");

			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			br.close();

			result = sb.toString();
			System.out.println("������" + result);
			JSONTokener json = new JSONTokener(result);
			JSONObject p_id = (JSONObject) json.nextValue();
			id = p_id.getString("ID��");

			setJSON1(json_send, id);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("���ر��ģ�" + json_send.toString());
			PrintWriter pw = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(
							(ServletOutputStream) resp.getOutputStream(),
							"utf-8")), true);
			pw.println(json_send.toString());
			pw.flush();
			pw.close();
			datas.clear();
		}
	}

	private void setJSON1(JSONObject json_send, String id) throws JSONException {
		JSONObject patient_sent = new JSONObject();
		datas = loadData.selectDatas(id);	
		if(datas.size() == 0) {
			json_send.put("������Ϣ", "�޼�¼");
		}else {
			patient = datas.get(0);
			patient_sent.put("id", patient.getId());
			patient_sent.put("name", patient.getName());
			patient_sent.put("age", patient.getAge());
			patient_sent.put("sex", patient.getSex());
			patient_sent.put("phone", patient.getPhone());
			patient_sent.put("brzz", patient.getBrzz());
			patient_sent.put("yz", patient.getYz());
			patient_sent.put("zybch", patient.getZybch());
			patient_sent.put("hyzk", patient.getHyzk());
			patient_sent.put("yxck", patient.getYxck());
			patient_sent.put("tssm", patient.getTssm());
			patient_sent.put("shz", patient.getShz());
			patient_sent.put("shtime", patient.getShsj());		
			json_send.put("������Ϣ", patient_sent);
		}
		
	}
}
