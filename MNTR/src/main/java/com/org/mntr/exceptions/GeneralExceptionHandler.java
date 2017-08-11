package com.org.mntr.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.org.mntr.constants.NavigationConstants;
import com.org.mntr.constants.ViewConstants;

@ControllerAdvice
public class GeneralExceptionHandler {

	private static final Logger logger = Logger.getLogger(GeneralExceptionHandler.class);

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ModelAndView handleErrorStat405(HttpServletRequest request, Exception e) {
		logger.info("Inside [GeneralExceptionHandler][handleErrorStat405]");
		ModelAndView mav = new ModelAndView();
		mav.addObject(NavigationConstants.errmsg, e);
		mav.setViewName(ViewConstants.redirect + ViewConstants.errorURL);
		return mav;
	}

}
