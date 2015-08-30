package com.wxisme.util;

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *io相关的工具类
 *@author wxisme
 *@time 2015-8-30 上午9:51:02
 */
public class IOUtil {
	
	public static <T extends Closeable> void close(T... io) {
		for(T t : io) {
			if(t != null) {
				try {
					t.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public static void close(ServerSocket serverSocket) {
		if(serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Socket socket) {
		if(socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(DatagramSocket datagramSocket) {
		if(datagramSocket != null) {
			datagramSocket.close();
		}
	}

}
