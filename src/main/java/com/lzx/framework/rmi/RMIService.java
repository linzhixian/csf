package com.lzx.framework.rmi;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMISocketFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.remoting.rmi.RmiServiceExporter;

public class RMIService {
	public static int port = 10099;// 端口
	public static String url = "localhost";// 地址localhost
	public static int port_client = 9797;

	public void startup(ApplicationContext applicationContext) throws IOException {
		System.setProperty("java.rmi.server.hostname", url);
		System.out.println("url:"+url+"---port_client:"+port_client+",---port:"+port);
		RMISocketFactory.setSocketFactory(new MyRMISocket(port_client));
		LocateRegistry.createRegistry(port);
	}
	public void startup() throws IOException {
		startup(null);
	}

	public void bind(String name, Remote obj) {
		try {
			Registry registry = LocateRegistry.getRegistry(url, port);
			registry.rebind(name, obj);
			System.out.println(name + " regist success");
//			registry.rebind("rmi://" + url + ":"+ port+"/" +name, obj);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Registry getRegistry() throws RemoteException {
		return  LocateRegistry.getRegistry(url, port);
	}
	public void bindRmi(Class serviceInterface,Object serviceBean) throws RemoteException {
		RmiServiceExporter rmiExporter=new RmiServiceExporter();
		rmiExporter.setServiceName(serviceInterface.getName());
		rmiExporter.setService(serviceBean);
		rmiExporter.setServiceInterface(serviceInterface);
		rmiExporter.setRegistryPort(RMIService.port);
		rmiExporter.setRegistry(RMIService.getRegistry());
		rmiExporter.afterPropertiesSet();
	}
}
