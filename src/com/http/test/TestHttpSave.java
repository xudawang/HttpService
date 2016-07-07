package com.http.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

public class TestHttpSave extends HttpServlet {

	private String name;

	private List<String> list = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		list = new ArrayList<String>();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=utf-8");
		String jsonStr = "{\"name\":\"type\", \"type\", \"����\"}";
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
	 * result = sb.toString(); System.out.println("������" + result);
	 * 
	 * }catch(Exception e) { e.printStackTrace(); }finally {
	 * System.out.println("���ر��ģ�" + result); PrintWriter pw = new
	 * PrintWriter(new BufferedWriter( new
	 * OutputStreamWriter((ServletOutputStream)resp.getOutputStream(),
	 * "utf-8")), true); pw.write(result); pw.flush(); pw.close(); } }
	 */
	
	
	/*
	 // TODO Auto-generated method stub
		File file = new File("J:\\��ѧѧϰ����\\������\\�������\\�ز�\\����\\001.png");
		FileOutputStream fileOutputStream = new FileOutputStream("J:\\��ѧѧϰ����\\������\\�������\\�ز�\\����\\001.png");
		byte[] buffer = new byte[1024];
		int len = -1;
		ServletInputStream inputStream = req.getInputStream();
		while((len = inputStream.read(buffer)) != -1) {
			fileOutputStream.write(buffer, 0, len);
		}
		fileOutputStream.flush();
		inputStream.close();
		fileOutputStream.close(); 
	 */

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ServletInputStream inStream = req.getInputStream();
		int size = req.getContentLength();
		byte[] buffer = new byte[size];	//����ÿ�ζ�ȡ������
		byte[] result = new byte[size];	//��Ž��������
		
		int count = 0;
		int rbyte = 0;
		while(count < size) {
			rbyte = inStream.read(buffer);
			for(int i=0; i<rbyte; i++) {
				result[count + i] = buffer[i];
			}
			count += rbyte;
		}
		
		//�ҵ��ļ�����ͼƬ���ı�־λ
		int index = 0;
		for(int i=0; i<result.length; i++) {
			byte b = result[i];
			if(b == '|') {
				index = i;
				break;
			}
		}
		
		//����ļ���
		byte name[] = new byte[index + 1];
		//���ͼƬ�ֽ�
		byte[] img = new byte[size - index];
		for(int i=0; i<result.length; i++) {
			if(i < index) {
				name[i] = result[i];
			}
			if(i > index) {
				img[i - index - 1] = result[i];
			}
		}
		
		String fileName = new String(name);
		inStream.close();
		
		try {
		FileOutputStream fos = new FileOutputStream("J:\\��ѧѧϰ����\\������\\�������\\�ز�\\����\\" + fileName.toString().trim());
		fos.write(img, 0, img.length);
		fos.flush();
		fos.close();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
