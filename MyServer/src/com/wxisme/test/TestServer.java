package com.wxisme.test;

import org.junit.Before;
import org.junit.Test;

import com.wxisme.server.Server;

/**
 *
 *@author wxisme
 *@time 2015-8-30 上午12:16:28
 */
public class TestServer {
	
	private Server server;
	
	@Before
	public void setUp() {
		server = new Server();
	}

	@Test
	public void test() {
		server.start();
		server.receive();
		server.stop();
	}

}
