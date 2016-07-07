package com.brLogin.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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

import com.patientinfo.server.LoadData;
import com.patientinfo.server.PatientInfo;

/**
 * ���˵�¼�鿴��Ϣ�ӿ�
 * @author xudawang
 *
 */
public class HttpBrLoginServer extends HttpServlet {
	private PatientInfo patient = new PatientInfo();
	private LoadData loadData = new LoadData();
	private JSONObject json_send = new JSONObject();
	private String sendMessage;

	private List<PatientInfo> datas = new ArrayList<PatientInfo>();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String reslut = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(ServletInputStream) req.getInputStream(), "utf-8"));
			StringBuffer sb = new StringBuffer();
			String temp = "";
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			br.close();
			reslut = sb.toString();
			JSONTokener json = new JSONTokener(reslut);
			JSONObject b_info = (JSONObject) json.nextValue();
			loadBrInfo(b_info);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

	private void loadBrInfo(JSONObject b_info) throws JSONException {
		String name = b_info.getJSONObject("������Ϣ").getString("Name");
		datas = loadData.selectDatas_name(name);
		patient = datas.get(0);

		if (patient.getYxck().equals("��")) {
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

			json_send.put("������Ϣ", patient_sent);
		} else {
			sendMessage = "�ò��˲������鿴";
			json_send.put("������Ϣ", sendMessage);
		}
	}

}
