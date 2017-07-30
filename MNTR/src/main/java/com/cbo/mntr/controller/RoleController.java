package com.cbo.mntr.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbo.mntr.constants.ViewConstants;
import com.cbo.mntr.dto.RoleDto;
import com.cbo.mntr.service.RoleService;
import com.fasterxml.jackson.annotation.JsonView;

@Controller
public class RoleController {

	private static final Logger logger = Logger.getLogger(RoleController.class);

	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;

	@RequestMapping(value = { ViewConstants.roleURL1 })
	public String rolePage(Model model) {
		logger.info("Inside [RoleController][rolePage]");
		return ViewConstants.roleURL1;
	}

	@RequestMapping(value = { ViewConstants.roleURL2 + "*" })
	@ResponseBody
	@JsonView(DataTablesOutput.View.class)
	public DataTablesOutput<RoleDto> loadRoleList(Model model, @Valid DataTablesInput dtInput) {
		logger.info("Inside [RoleController][loadRoleList]");
		try {
			return roleService.getAllRoles(dtInput);
		} catch (Exception ex) {
			logger.error("CTRLR Error : " + ex);
		}
		return null;
	}

}
