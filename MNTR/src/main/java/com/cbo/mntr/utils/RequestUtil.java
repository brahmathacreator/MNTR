package com.cbo.mntr.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	public static String getBaseUrl(HttpServletRequest request) {
		final String scheme = request.getScheme() + "://";
		final String serverName = request.getServerName();
		final String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
		final String contextPath = request.getContextPath();
		return scheme + serverName + serverPort + contextPath + "/";
	}

}
