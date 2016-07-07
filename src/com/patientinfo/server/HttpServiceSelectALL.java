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
 * 查询病人信息接口
 * 
 * @author xudawang
 * 
 */
public class HttpServiceSelectALL extends HttpServlet {
	private LoadData loadData = new LoadData();
	private PatientInfo patient;

	private List<PatientInfo> datas = new ArrayList<PatientInfo>();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		selectByName(req, resp);
	}

	private void selectByName(HttpServletRequest req, HttpServletResponse resp)
			throws UnsupportedEncodingException, IOException {
		JSONObject json_send = new JSONObject();
		String cz = "";
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
			System.out.println("请求报文" + result);
			JSONTokener json = new JSONTokener(result);
			JSONObject p_name = (JSONObject) json.nextValue();
			cz = p_name.getString("SELECTALL");

			if (cz.equals("SELECTALL")) {
				setJSON3(json_send);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("返回报文：" + json_send.toString());
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

	private void setJSON3(JSONObject json_send) throws JSONException {
		datas = loadData.selectDatas_ALL();
		for (int i = 0; i < datas.size(); i++) {
			patient = datas.get(i);

			JSONObject patient_sent = new JSONObject();

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

			json_send.put("病人信息" + i, patient_sent);
		}

		json_send.put("病人信息总数", datas.size());
	}
}
