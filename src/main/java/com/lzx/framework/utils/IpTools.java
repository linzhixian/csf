package com.lzx.framework.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpTools {

	public static String findMyIp() {
		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface nif = netInterfaces.nextElement();
				Enumeration<InetAddress> iparray = nif.getInetAddresses();
				while (iparray.hasMoreElements()) {
					String ip = iparray.nextElement().getHostAddress();

					Pattern pattern = Pattern.compile("(\\d{1,3})[.](\\d{1,3})[.](\\d{1,3})[.](\\d{1,3})",
							Pattern.CASE_INSENSITIVE);
					Matcher matcher = pattern.matcher(ip);
					while (matcher.find()) {
						String findIp = matcher.group(0);
						if (findIp.startsWith("127"))
							continue;
						System.out.println("find IP:" + findIp);
						return findIp;

					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "0";
	}

}
