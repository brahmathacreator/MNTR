package com.cbo.mntr.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cbo.mntr.constants.StatusConstants;
import com.cbo.mntr.constants.UserConstants;
import com.cbo.mntr.constants.ViewConstants;
import com.cbo.mntr.service.UserService;

@Controller
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@RequestMapping(value = { ViewConstants.login }, method = RequestMethod.GET)
	public String loginPagewithURL(ModelMap model) {
		logger.info("Inside [LoginController][loginPagewithURL]");
		Long suaCount = null;
		try {
			model.addAttribute(ViewConstants.actionURL, ViewConstants.registerSystemUser);
			suaCount = userService.getUserCount();
			if (suaCount == null || suaCount == 0)
				model.addAttribute(UserConstants.suaCount, StatusConstants.inActive);
			else
				model.addAttribute(UserConstants.suaCount, StatusConstants.active);
		} catch (Exception ex) {
			logger.error("CTRLR Error: " + ex);
		}
		return ViewConstants.login;
	}

}
