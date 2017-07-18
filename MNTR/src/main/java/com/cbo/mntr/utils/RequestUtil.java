package com.cbo.mntr.utils;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.LocaleResolver;

public class RequestUtil {

	@Autowired
	private static LocaleResolver localeResolver;

	public static String getBaseUrl(HttpServletRequest request) {
		final String scheme = request.getScheme() + "://";
		final String serverName = request.getServerName();
		final String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
		final String contextPath = request.getContextPath();
		return scheme + serverName + serverPort + contextPath + "/";
	}

	public static Locale getCurrentLocale(HttpServletRequest request) {
		return localeResolver.resolveLocale(request);
	}
}
