package com.login.server;

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

public class HttpDNRegisterServer extends HttpServlet {
	private DNLoger data = new DNLoger();
	private AddUserData addUserData;
	private JSONObject json_send = new JSONObject();
	private String sendMessage;

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
			System.out.println("传入的实体：" + result);
			JSONTokener json = new JSONTokener(result);
			JSONObject u_add = (JSONObject) json.nextValue();

			initJSON2User(u_add);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(json_send.toString());
			PrintWriter pw = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(
							(ServletOutputStream) resp.getOutputStream(),
							"utf-8")), true);
			pw.write(json_send.toString());
			pw.flush();
			pw.close();

		}
	}

	private void initJSON2User(JSONObject u_add) {
		try {
			data.setName(u_add.getJSONObject("用户信息").getString("Name"));
			data.setSex(u_add.getJSONObject("用户信息").getString("Sex"));
			data.setCareer(u_add.getJSONObject("用户信息").getString("Career"));
			data.setPassword(u_add.getJSONObject("用户信息").getString("PassWord"));

			addUserData = new AddUserData(data);
			sendMessage = addUserData.addUser();

			json_send.put("返回信息", sendMessage);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
