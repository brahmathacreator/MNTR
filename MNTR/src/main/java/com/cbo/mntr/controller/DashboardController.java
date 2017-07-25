package com.cbo.mntr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cbo.mntr.constants.ViewConstants;

@Controller
public class DashboardController {

	@RequestMapping(value = { ViewConstants.home })
	public String machinePage(Model model) {
		return ViewConstants.home;
	}

}
