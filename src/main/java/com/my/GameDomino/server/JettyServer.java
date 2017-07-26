package com.my.GameDomino.server;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyServer {

	private int port = 8080;
	private Server server;

	public JettyServer(int port) {
		this.port = port;
	}

	public void start() throws Exception {
		server = new Server(port);

		WebAppContext context = new WebAppContext();
		context.setDescriptor(context + "/WEB-INF/web.xml");
		context.setResourceBase("/src/main/webapp");
		context.setContextPath("/");
		context.setParentLoaderPriority(true);

		server.setHandler(context);

		server.start();
	}

	public void stop() throws Exception {
		server.stop();
	}

	public void waitForInterrupt() throws InterruptedException {
		server.join();
	}
}
