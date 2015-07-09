package com.lzx.framework.boot;

import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lzx.framework.server.IServer;

public class Main {

	private static ApplicationContext context;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// System.out.println("getLocalHost" + InetAddress.getLocalHost());
		// System.out.println("System.setProperty java.rmi.server.hostname as:"+"sa.rmi.com");
		// System.setProperty("java.rmi.server.hostname", "db.rmi.com");
		// System.out.println("RMISocketFactory.setSocketFactory port as:9796");
		// RMISocketFactory.setSocketFactory(new MyRMISocket(9796));
		System.out.println("ready to main....");
		try {
		context = new ClassPathXmlApplicationContext("server.xml");
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		IServer server = (IServer) context.getBean("server");
		if (args.length == 0 || args[0].equals("start")) {
			startServer(server);
		}
		if (args.length != 0 && args[0].equals("stop")) {
			stopServer(server);
		}

	}

	public static void startServer(IServer server) {

		String serverName = server.getName();
		printServerStart(serverName);
		server.setApplicationContext(context);
		try {
			printSystemEcho("initBeforeStart");
			server.initBeforeStart();
			printSystemEcho("starting");
			server.start();
			printSystemEcho("initAfterStarted");
			server.initAfterStarted();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static void stopServer(IServer server) {
		try {
			printSystemEcho("beforeStop");
			server.beforeStop();
			printSystemEcho("stop");
			server.stop();
			printSystemEcho("afterStop");
			server.afterStop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void printSystemEcho(String msg) {
		System.out.println("++++++++---" + msg + " ......");
	}

	public static void printServerStart(String serverName) {
		String successMsg = "Start Server \"" + serverName + "\" at " + new Timestamp(System.currentTimeMillis());
		System.out.println("      " + successMsg + "");
		System.out.println("      Designed by linZhiXian,fly_go@hotmail.com");
	}
}
