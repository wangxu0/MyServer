package com.wxisme.test;

import com.wxisme.server.Request;
import com.wxisme.server.Response;
import com.wxisme.servlet.Servlet;

/**
 *测试
 *@author wxisme
 *@time 2015-8-30 下午4:03:04
 */
public class TestServlet extends Servlet {
	
	
	@Override
	protected void doPost(Request req, Response resp) throws Exception {
		resp.println("Hello World!");
	}
	
	@Override
	protected void doGet(Request req, Response resp) throws Exception {
		resp.println("Hello World!");
	}

}
