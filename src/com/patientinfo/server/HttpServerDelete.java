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
 * 删除数据接口
 * @author xudawang
 *
 */
public class HttpServerDelete extends HttpServlet {
	private DeleteData deleteDatas = new DeleteData();
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
			System.out.println("删除请求传入的参数" + result);
			JSONTokener json = new JSONTokener(result);
			JSONObject p_delete = (JSONObject) json.nextValue();
			
			deletPatientInfo(p_delete);
			
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
	private void deletPatientInfo(JSONObject p_delete) throws JSONException {
		String Id = p_delete.getJSONObject("病人信息").getString("Id");
		sendMessage = deleteDatas.deletePatient(Id);
		json_send.put("删除标志", sendMessage);
	}

}
