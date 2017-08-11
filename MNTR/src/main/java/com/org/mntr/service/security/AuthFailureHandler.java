package com.org.mntr.service.security;

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

import com.org.mntr.constants.MsgConstants;
import com.org.mntr.constants.NavigationConstants;
import com.org.mntr.constants.ViewConstants;
import com.org.mntr.utils.MsgResolver;

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
		logger.info("Inside [AuthSuccessHandler][onAuthenticationFailure]");
		setDefaultFailureUrl(ViewConstants.rootView + ViewConstants.login);
		super.onAuthenticationFailure(request, response, exception);
		final Locale locale = localeResolver.resolveLocale(request);

		String errorMessage = messageSource.getMessage(MsgResolver.getMsgCodeKey(MsgConstants.authMsgCode1), null,
				locale);
		if (exception.getMessage().equalsIgnoreCase("User is disabled")) {
			errorMessage = messageSource.getMessage(MsgResolver.getMsgCodeKey(MsgConstants.authMsgCode2), null, locale);
		} else if (exception.getMessage().equalsIgnoreCase("User account has expired")) {
			errorMessage = messageSource.getMessage(MsgResolver.getMsgCodeKey(MsgConstants.authMsgCode3), null, locale);
		} else if (exception.getMessage().equalsIgnoreCase("blocked")) {
			errorMessage = messageSource.getMessage(MsgResolver.getMsgCodeKey(MsgConstants.authMsgCode4), null, locale);
		}
		request.setAttribute(NavigationConstants.errmsg, errorMessage);
		request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
	}

}
