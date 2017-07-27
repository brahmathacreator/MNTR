package com.cbo.mntr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbo.mntr.constants.ViewConstants;
import com.cbo.mntr.dto.RoleDto;
import com.cbo.mntr.service.RoleService;

@Controller
public class RoleController {

	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;

	@RequestMapping(value = { ViewConstants.roleURL1 })
	public String rolePage(Model model) {
		return ViewConstants.roleURL1;
	}

	@RequestMapping(value = { ViewConstants.roleURL2 })
	@ResponseBody
	public List<RoleDto> loadRoleList(Model model) {
		return roleService.getAllRoles(null);
	}

}
