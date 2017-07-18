package com.cbo.mntr.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.cbo.mntr.constants.NavigationConstants;
import com.cbo.mntr.constants.ViewConstants;

@ControllerAdvice
public class GeneralExceptionHandler {

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ModelAndView handleErrorStat405(HttpServletRequest request, Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject(NavigationConstants.errmsg, e);
		mav.setViewName(ViewConstants.redirect + ViewConstants.accessDenied);
		return mav;
	}

}
