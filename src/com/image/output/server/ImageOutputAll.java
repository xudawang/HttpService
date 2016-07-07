package com.image.output.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.JSONTokener;

public class ImageOutputAll extends HttpServlet {
	private String tag;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * String result = ""; try { BufferedReader br = new BufferedReader(new
		 * InputStreamReader( (ServletInputStream) req.getInputStream(),
		 * "utf-8")); StringBuffer sb = new StringBuffer(); String temp = "";
		 * while((temp = br.readLine()) != null) { sb.append(temp); }
		 * br.close(); result = sb.toString(); System.out.println("请求报文：" +
		 * result); JSONTokener json = new JSONTokener(result); JSONObject
		 * img_tx_name = (JSONObject) json.nextValue(); tag =
		 * img_tx_name.getString("下载全图"); }catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		ServletOutputStream sos = resp.getOutputStream();
		File file = new File("J:\\大学学习资料\\大四下\\毕设材料\\素材\\测试");
		File[] files = file.listFiles();
		
		for(int i=0; i<1; i++) {
			FileInputStream fis = new FileInputStream(files[i]);
			int size = (int) files[i].length();
			byte[] buffer = new byte[size];
			fis.read(buffer, 0, size);
			
			sos.write(files[i].getName().getBytes());
			sos.write('|');
			sos.write(buffer);
			sos.write(',');
		}
		sos.flush();
		
		sos.close();
		
	}

}
