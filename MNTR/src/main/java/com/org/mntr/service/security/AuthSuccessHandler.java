package com.org.mntr.service.security;

import java.io.IOException;
import java.util.Properties;

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
import com.org.mntr.constants.SSValidationConfig;
import com.org.mntr.constants.ViewConstants;

@Component("authSuccHandler")
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(AuthSuccessHandler.class);
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	@Qualifier("sessionActiveUser")
	private SessionActiveUser activeUserList;

	@Autowired
	private Properties mntrProperties;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		logger.info("Inside [AuthSuccessHandler][onAuthenticationSuccess]");
		final HttpSession session = request.getSession(false);
		if (session != null) {
			session.setMaxInactiveInterval(
					Integer.parseInt(mntrProperties.getProperty(SSValidationConfig.sessionExpiryPeriodKey)) * 60);
			final UserSessionAttach user = new UserSessionAttach(authentication.getName(), activeUserList);
			session.setAttribute("user", user);
			handle(request, response, authentication, user.isAlreadyLoggedIn());
		} else {
			logger.info("Handler session is null.");
		}

	}

	protected void handle(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication, final boolean alreadyLoggedIn) throws IOException {
		logger.info("Inside [AuthSuccessHandler][handle]");
		final String targetUrl = determineTargetUrl(authentication, alreadyLoggedIn);
		if (response.isCommitted()) {
			logger.info("SEC : Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(final Authentication authentication, final boolean alreadyLoggedIn) {
		try {
			if (alreadyLoggedIn) {
				return ViewConstants.rootView + ViewConstants.logout;
			} else {
				return ViewConstants.authSuccessPage;
			}

		} finally {

		}
	}

	protected void clearAuthenticationAttributes(final HttpServletRequest request) {
		logger.info("Inside [AuthSuccessHandler][clearAuthenticationAttributes]");
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
