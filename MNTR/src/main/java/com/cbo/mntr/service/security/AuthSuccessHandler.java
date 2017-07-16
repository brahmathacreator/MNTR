package com.cbo.mntr.service.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.cbo.mntr.dto.ActualUser;

@Component("authSuccHandler")
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(AuthSuccessHandler.class);
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	@Qualifier("sessionActiveUser")
	private SessionActiveUser activeUserList;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		handle(request, response, authentication);
		final HttpSession session = request.getSession(false);
		if (session != null) {
			session.setMaxInactiveInterval(30 * 60);
			UserSessionAttach user = new UserSessionAttach(authentication.getName(), activeUserList);
			session.setAttribute("user", user);
			user = null;
		}
	}

	protected void handle(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication) throws IOException {
		final String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(final Authentication authentication) {
		final ActualUser au = (ActualUser) (authentication.getPrincipal());
		logger.debug("Response has already been committed. Unable to redirect to " + au.getUserInfo().getUserName());
		return "/home";
	}

	protected void clearAuthenticationAttributes(final HttpServletRequest request) {
		final HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

}
