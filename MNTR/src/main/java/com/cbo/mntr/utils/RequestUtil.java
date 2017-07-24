package com.cbo.mntr.utils;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.LocaleResolver;

public class RequestUtil {

	private static final Logger logger = Logger.getLogger(RequestUtil.class);

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

	public static boolean removeSessionUser(HttpServletRequest request) {
		logger.info("Inside [RequestUtil][removeSessionUser]");
		try {
			final HttpSession session = request.getSession();
			if (session != null) {
				session.removeAttribute("user");
				return true;
			}
		} catch (Exception ex) {
			logger.error("UTIL Error: " + ex);
		}
		return false;
	}
}
