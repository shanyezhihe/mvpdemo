package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





/**
 * Servlet implementation class AppVersionServlet
 */
@WebServlet("/AppVersionServlet")
public class AppVersionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppVersionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//普通测试
//		response.setCharacterEncoding("utf-8");
//		PrintWriter writer=response.getWriter();
//		writer.write("你好，我是来自本地服务器的数据");
//		writer.flush();
//		writer.close();
		
		//带参测试
//		response.setCharacterEncoding("utf-8");
//		PrintWriter writer=response.getWriter();
//		String currentVersion=request.getParameter("currentversion");
//		if (currentVersion!=null) {
//			if (!currentVersion.equals("1.6")) {
//				writer.write("你好，你的App当前版本过低，请到应用市场进行更新！");
//			}else {
//				writer.write("你好，你的App当前版本是最新版本，无需更新！");
//			}
//		}else {
//			writer.write("获取当前版本失败！");
//		}
//		writer.flush();
//		writer.close();
//		
		//带参JSON格式数据测试
		response.setCharacterEncoding("utf-8");
		PrintWriter writer=response.getWriter();
		String currentVersion=request.getParameter("currentversion");
		Map<String, Object> map=new HashMap<>();
		map.put("latest_version", "1.6");
		if (currentVersion!=null) {
			if (!currentVersion.equals("1.6")) {
				map.put("hasNewVersion", String.valueOf(true));
				map.put("desc", "你好，你的App当前版本过低，请到应用市场进行更新！");
			}else {
				map.put("hasNewVersion", String.valueOf(true));
				map.put("desc", "你好，你的App当前版本是最新版本，无需更新！");
			}
		}else {
			map.put("desc", "获取当前版本失败！");
		}
		com.alibaba.fastjson.JSONObject jsonObject=new com.alibaba.fastjson.JSONObject(map);
		writer.write(jsonObject.toString());
		writer.flush();
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
