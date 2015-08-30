package com.wxisme.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

import com.wxisme.util.IOUtil;

/**
 *封装响应
 *@author wxisme
 *@time 2015-8-30 上午9:42:15
 */
public class Response {
	   
	public static final String CRLF="\r\n";
	public static final String BLANK=" ";

	private BufferedWriter writer ;
	
	//正文
	private StringBuilder content;
	
	
	//存储头信息
	private StringBuilder headInfo;
	//存储正文长度
	private int len =0;
	public Response(){
		headInfo =new StringBuilder();
		content =new StringBuilder();
		len =0;
	}
	public Response(Socket client){
		this();
		try {
			writer= new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			headInfo=null;
		}
	}
	public Response(OutputStream outPutStream){
		this();
		writer= new BufferedWriter(new OutputStreamWriter(outPutStream));
	}
	/**
	 * 构建正文
	 */
	public Response print(String info){
		content.append(info);
		len+=info.getBytes().length;
		return this;
	}
	
	/**
	 * 构建正文+回车
	 */
	public Response println(String info){
		content.append(info).append(CRLF);
		len+=(info+CRLF).getBytes().length;
		return this;
	}
	
	/**
	 * 构建响应头
	 */
	private void createHeadInfo(int code){
		//HTTP协议版本、状态代码、描述
		headInfo.append("HTTP/1.1").append(BLANK).append(code).append(BLANK);
		switch(code){
			case 200:
				headInfo.append("OK");
				break;
			case 404:
				headInfo.append("NOT FOUND");
				break;
			case 505:
				headInfo.append("SEVER ERROR");
				break;	
		}
		headInfo.append(CRLF);
		//响应头(Response Head)
		headInfo.append("Server:MyServer/0.0.1").append(CRLF);
		headInfo.append("Date:").append(new Date()).append(CRLF);
		headInfo.append("Content-type:text/html;charset=GBK").append(CRLF);
		//正文长度 ：字节长度
		headInfo.append("Content-Length:").append(len).append(CRLF);
		headInfo.append(CRLF); //分隔符
	} 
	//推送到客户端
	public void pushToClient(int code) throws IOException{
		if(null==headInfo){
			code =500;
		}
		createHeadInfo(code);
		//头信息+分割符
		writer.append(headInfo.toString());
		//正文
		writer.append(content.toString());
		writer.flush();
	}
	public void close(){
		IOUtil.close(writer);
	}

}
