package com.wxisme.server;

import java.io.IOException;
import java.net.ServerSocket;

import com.wxisme.util.PrintInfo;

/**
 *Server类
 *@author wxisme
 *@time 2015-8-29 下午11:49:40
 */

@SuppressWarnings("all")
public class Server {
	private ServerSocket serverSocket;
	
	//server是否已经down掉
	private boolean isDown = false;
	
	
	/**
	 * 开启server
	 */
	public void start() {
		//默认端口号8080
		start(8080);
	}
	
	public void start(int port) {
		try {
			serverSocket = new ServerSocket(port);
			PrintInfo.printInfo("服务器开启成功！");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭server
	 */
	public void stop() {
		try {
			isDown = true;
			serverSocket.close();
			PrintInfo.printInfo("服务器已关闭！");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 接收客户端请求
	 * @throws IOException 
	 */
	public void receive() {
		//并发处理请求
		try {
			while(!isDown) {
				new Thread(new Dispatcher(serverSocket.accept())).start();
			}
		} catch (IOException e) {
			stop();
			e.printStackTrace();
		}
		
	}

}
