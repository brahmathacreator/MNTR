package com.cbo.mntr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cbo.mntr.constants.ViewConstants;

@Controller
public class UserController {

	@RequestMapping(value = { ViewConstants.userURL1 }, method = RequestMethod.POST)
	public String rolePage(Model model) {
		return ViewConstants.userView;
	}

}
