package com.wxisme.server;

import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.wxisme.servlet.Servlet;

/**
 *解析XML获取相应元素
 *@author wxisme
 *@time 2015-8-30 下午3:11:08
 */
public class Application {
	
	private static ServletContext context;
	/**
	 * 静态初始化，解析XML文件
	 */
	static{
		try {
			//获取解析工厂
			SAXParserFactory factory = SAXParserFactory.newInstance();
			//获取解析器
			SAXParser parser = factory.newSAXParser();
			Handler handler = new Handler();
			parser.parse(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("WEB-INF/web.xml"), handler);
			
			
			context =new ServletContext();
			Map<String,String> servletMap =context.getServletMap();
			
			//获取解析到的Servlet信息
			for(Entity entity : handler.getEntityList()){
				servletMap.put(entity.getName(), entity.getClazz());
			}
			
			//获取解析到的url信息
			Map<String,String> mapping =context.getMapping();
			for(Mapping mapp : handler.getMappingList()){
				List<String> urls =mapp.getUrlPattern();
				for(String url : urls){
					mapping.put(url, mapp.getName());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 通过url获取Servlet实例
	 * @param url
	 * @return Servlet
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static Servlet getServlet(String url)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if((url==null)||(url=url.trim()).equals("")){
			return null;
		}
		//获取类名
		
		String name = context.getServletMap().get(context.getMapping().get(url));
		//通过反射获取类的实例
		return (Servlet)Class.forName(name).newInstance();
	}

}
