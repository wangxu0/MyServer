package com.wxisme.server;

import java.io.IOException;
import java.net.Socket;

import com.wxisme.servlet.Servlet;
import com.wxisme.util.IOUtil;
import com.wxisme.util.PrintInfo;

/**
 *请求分发,采用并发处理
 *@author wxisme
 *@time 2015-8-30 下午2:41:58
 */
public class Dispatcher implements Runnable {

	private Socket client;
	private Request req;
	private Response resp;
	private int code=200;
	public Dispatcher(Socket client){
		this.client=client;
		try {
			req =new Request(client.getInputStream());
			resp =new Response(client.getOutputStream());
		} catch (IOException e) {
			//内部错误
			code =500;
			return ;
		}
	}
	
	
	
	
	@Override
	public void run() {
		
		try {
			Servlet servelt = Application.getServlet(req.getUrl());
			PrintInfo.printInfo("处理请求成功!");
			if(servelt==null){
				//not found
				this.code=404;
			}else{
				servelt.service(req, resp);
			}
			resp.pushToClient(code);
		}catch (Exception e) {
			//e.printStackTrace();
			//内部错误
			this.code=500;
		}	
		try {
			resp.pushToClient(500);
		} catch (IOException e) {
			//e.printStackTrace();
		}
		req.close();
		resp.close();
		IOUtil.close(client);
	}

}
