package com.wxisme.server;

import java.util.HashMap;
import java.util.Map;

/**
 *servlet上下文类
 *@author wxisme
 *@time 2015-8-30 上午10:45:33
 */
public class ServletContext {

	private Map<String,String> servletMap;

	private Map<String,String> mapping;
	
	ServletContext(){
		servletMap =new HashMap<String,String>();
		mapping =new HashMap<String,String>();
	}
	
	
	public Map<String, String> getServletMap() {
		return servletMap;
	}
	public void setServletMap(Map<String, String> servlet) {
		this.servletMap = servlet;
	}
	public Map<String, String> getMapping() {
		return mapping;
	}
	public void setMapping(Map<String, String> mapping) {
		this.mapping = mapping;
	}
	
}
