package com.cbo.mntr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cbo.mntr.constants.ViewConstants;

@Controller
public class UserController {

	@RequestMapping(value = { ViewConstants.userURL1 })
	public String rolePage(Model model) {
		return ViewConstants.userURL1;
	}

}
