package com.cbo.mntr.service.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.cbo.mntr.constants.ViewConstants;
import com.cbo.mntr.utils.RequestUtil;

@Component
public class LogoutHandler implements LogoutSuccessHandler {

	private static final Logger logger = Logger.getLogger(LogoutHandler.class);

	@Override
	public void onLogoutSuccess(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication auth) throws IOException, ServletException {
		logger.info("Inside [LogoutHandler][onLogoutSuccess]");
		final boolean stat = RequestUtil.removeSessionUser(request);
		logger.info("Session Removal Stat : " + stat);
		response.sendRedirect(ViewConstants.logout);
	}

}
