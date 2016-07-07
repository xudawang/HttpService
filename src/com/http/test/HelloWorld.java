package com.http.test;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HelloWorld extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html><head><title>");
		out.println("This is my first Servlet");
		out.println("</title></head><body>");
		out.println("<h1>Hello, World!</h1>");
		out.println("</body></html>");
		
		out.flush();
		out.close();
		super.doGet(req, resp);
	}

}
