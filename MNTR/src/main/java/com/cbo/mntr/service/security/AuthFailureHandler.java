package com.cbo.mntr.service.security;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import com.cbo.mntr.constants.ViewConstants;

@Component("authFailureHandler")
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private static final Logger logger = Logger.getLogger(AuthFailureHandler.class);

	@Autowired
	private LocaleResolver localeResolver;

	@Autowired
	private MessageSource messageSource;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.info("Inside [onAuthenticationFailure]");
		setDefaultFailureUrl(ViewConstants.rootView + ViewConstants.accessDenied);
		super.onAuthenticationFailure(request, response, exception);
		final Locale locale = localeResolver.resolveLocale(request);

		String errorMessage = messageSource.getMessage("message.badCredentials", null, locale);

		if (exception.getMessage().equalsIgnoreCase("User is disabled")) {
			errorMessage = messageSource.getMessage("auth.message.disabled", null, locale);
		} else if (exception.getMessage().equalsIgnoreCase("User account has expired")) {
			errorMessage = messageSource.getMessage("auth.message.expired", null, locale);
		} else if (exception.getMessage().equalsIgnoreCase("blocked")) {
			errorMessage = messageSource.getMessage("auth.message.blocked", null, locale);
		}

		request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
	}

}
