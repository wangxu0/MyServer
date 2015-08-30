package com.wxisme.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *打印控制台信息
 *@author wxisme
 *@time 2015-8-30 下午4:59:20
 */
public class PrintInfo {
	
	public static void printInfo(String info) {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String str = s.format(date);
		System.out.println("信息：" + str + " " + info);
	}

}
