package com.login.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.Servlet;
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
 * 医护登录查看信息接口
 * @author xudawang
 *
 */
public class HttpDNLoginServer extends HttpServlet {
	private DNLoger dnLoger = new DNLoger();
	private LoadDNData loadDNData = new LoadDNData();
	private String sendMessage;
	private JSONObject json_send = new JSONObject();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
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
			JSONTokener json = new JSONTokener(result);
			JSONObject l_info = (JSONObject) json.nextValue();
			loadDNLogerInfo(l_info);
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
		}
	}

	private void loadDNLogerInfo(JSONObject l_info) throws JSONException {
		String name = l_info.getJSONObject("登录信息").getString("Name");
		String password = l_info.getJSONObject("登录信息").getString("Password");
		sendMessage = loadDNData.loadDNDate(name, password);
		json_send.put("返回信息", sendMessage);
		json_send.put("职业", loadDNData.dnLoger.getCareer());
	}

}
