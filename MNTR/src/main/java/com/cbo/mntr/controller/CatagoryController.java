package com.cbo.mntr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cbo.mntr.constants.ViewConstants;

@Controller
public class CatagoryController {

	@RequestMapping(value = { ViewConstants.catagoryURL1 })
	public String catagoryPage(Model model) {
		return ViewConstants.catagoryURL1;
	}

}
