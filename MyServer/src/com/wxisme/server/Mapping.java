package com.wxisme.server;

import java.util.ArrayList;
import java.util.List;

/**
 *映射servlet-mapping
 *@author wxisme
 *@time 2015-8-30 上午10:20:40
 */
public class Mapping {
	
	private String name;
	private List<String> urlPattern;
	
	public Mapping(){
		urlPattern =new ArrayList<String>();
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getUrlPattern() {
		return urlPattern;
	}
	public void setUrlPattern(List<String> urlPattern) {
		this.urlPattern = urlPattern;
	}

}
