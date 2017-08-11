package com.org.mntr.utils;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.LocaleResolver;

import com.org.mntr.dto.ActualUser;
import com.org.mntr.service.security.UserSessionAttach;

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

	public static boolean checkSessionUser(HttpServletRequest request, String sessionUser) {
		logger.info("Inside [RequestUtil][removeSessionUser]");
		UserSessionAttach user = null;
		HttpSession session = null;
		try {
			session = request.getSession();
			if (session != null) {
				user = (UserSessionAttach) session.getAttribute("user");
				if (user != null && !user.getUserId().trim().isEmpty() && sessionUser != null
						&& !sessionUser.trim().isEmpty() && user.getUserId().equals(sessionUser))
					return true;
			}
		} catch (Exception ex) {
			logger.error("UTIL Error: " + ex);
		} finally {
			user = null;
			session = null;
		}
		return false;
	}

	public static Long getUserKeyFromAuth(Authentication au) throws Exception {
		logger.info("Inside [RequestUtil][getUserKeyFromAuth]");
		ActualUser user = null;
		try {
			user = ((ActualUser) au.getPrincipal());
			return user.getUserInfo().getUserKey();
		} finally {
			user = null;
		}
	}
}
