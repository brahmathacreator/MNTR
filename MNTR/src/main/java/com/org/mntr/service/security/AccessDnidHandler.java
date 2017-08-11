package com.org.mntr.service.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.org.mntr.constants.ViewConstants;
import com.org.mntr.utils.RequestUtil;

public class AccessDnidHandler implements AccessDeniedHandler {

	private static final Logger logger = Logger.getLogger(AccessDnidHandler.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception)
			throws IOException, ServletException {
		logger.info("Inside [AccessDnidHandler][handle]");
		final boolean stat = RequestUtil.removeSessionUser(request);
		logger.info("Session Removal Stat : " + stat);
		response.sendRedirect(ViewConstants.accessDeniedURL);

	}

}
