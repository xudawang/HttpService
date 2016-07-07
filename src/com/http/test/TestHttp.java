package com.http.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.JSONTokener;

public class TestHttp extends HttpServlet {

	private String name;
	private String fileName;

	private List<String> list = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		list = new ArrayList<String>();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=utf-8");
		String jsonStr = "{\"name\":\"type\", \"type\", \"虫子\"}";
		PrintWriter out = resp.getWriter();
		out.write("this is my first webAPI");
		out.flush();
		out.close();
	}

	/*
	 * @Override protected void doPost(HttpServletRequest req,
	 * HttpServletResponse resp) throws ServletException, IOException { String
	 * result = ""; try { BufferedReader br = new BufferedReader(new
	 * InputStreamReader((ServletInputStream)req.getInputStream(), "utf-8"));
	 * StringBuffer sb = new StringBuffer("");
	 * 
	 * String temp; while((temp = br.readLine()) != null) { sb.append(temp); }
	 * br.close();
	 * 
	 * result = sb.toString(); System.out.println("请求报文" + result);
	 * 
	 * }catch(Exception e) { e.printStackTrace(); }finally {
	 * System.out.println("返回报文：" + result); PrintWriter pw = new
	 * PrintWriter(new BufferedWriter( new
	 * OutputStreamWriter((ServletOutputStream)resp.getOutputStream(),
	 * "utf-8")), true); pw.write(result); pw.flush(); pw.close(); } }
	 */

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
			while((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			br.close();
			result = sb.toString();
			System.out.println("请求头像：" + result);
			JSONTokener json = new JSONTokener(result);
			JSONObject img_tx_name = (JSONObject) json.nextValue();
			fileName = img_tx_name.getString("头像名");
			
			InputStream inputStream = null;
			String Name = "J:\\大学学习资料\\大四下\\毕设材料\\素材\\测试\\" + fileName + ".jpg";
			inputStream = ImageUtil
					.getImageByte(Name);
			File f = new File(Name);

			ServletOutputStream outputStream = resp.getOutputStream();
			try {
				byte[] buffer = new byte[(int) f.length()];
				int i = -1;
				while ((i = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, i);
				}
				outputStream.flush();
				outputStream.close();
				inputStream.close();
				outputStream = null;

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		

	}
}
