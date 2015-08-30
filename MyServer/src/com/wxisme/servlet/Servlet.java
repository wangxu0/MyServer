package com.wxisme.servlet;

import com.wxisme.server.Request;
import com.wxisme.server.Response;

/**
 *servlet抽象类
 *@author wxisme
 *@time 2015-8-30 下午3:06:37
 */
public abstract class Servlet {
	
	public void service(Request req,Response resp) throws Exception{
		this.doGet(req,resp);
		this.doPost(req,resp);
	}
	
	protected abstract void doGet(Request req,Response resp) throws Exception;
	protected abstract void doPost(Request req,Response resp) throws Exception;

}
