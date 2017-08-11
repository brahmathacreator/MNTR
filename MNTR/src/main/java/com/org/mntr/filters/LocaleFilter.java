package com.org.mntr.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.LocaleResolver;

public class LocaleFilter extends GenericFilterBean {

	private static final Logger logger = Logger.getLogger(LocaleFilter.class);

	@Autowired
	private LocaleResolver localeResolver;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			LocaleContextHolder.setLocale(localeResolver.resolveLocale((HttpServletRequest) request));
		} catch (Exception ex) {
			logger.error("Filter Error : " + ex);
		}
		chain.doFilter(request, response);
	}

}
