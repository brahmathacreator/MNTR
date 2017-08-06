package com.cbo.mntr.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cbo.mntr.constants.MsgConstants;
import com.cbo.mntr.constants.NavigationConstants;
import com.cbo.mntr.constants.StatusConstants;
import com.cbo.mntr.constants.ViewConstants;
import com.cbo.mntr.dto.ActualUser;
import com.cbo.mntr.dto.RoleDto;
import com.cbo.mntr.entity.UserRole;
import com.cbo.mntr.exceptions.CustomException;
import com.cbo.mntr.service.RoleService;
import com.cbo.mntr.utils.MsgResolver;
import com.cbo.mntr.utils.RequestUtil;
import com.fasterxml.jackson.annotation.JsonView;

@Controller
public class RoleController {

	private static final Logger logger = Logger.getLogger(RoleController.class);

	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = { ViewConstants.roleURL1 })
	public String rolePage(Model model) {
		logger.info("Inside [RoleController][rolePage]");
		return ViewConstants.roleURL1;
	}

	@RequestMapping(value = { ViewConstants.roleURL2 })
	@JsonView(DataTablesOutput.View.class)
	@ResponseBody
	public DataTablesOutput<UserRole> loadRoleList(Model model, @Valid @RequestBody DataTablesInput dtInput,
			Authentication au) {
		logger.info("Inside [RoleController][loadRoleList]");
		try {
			return roleService.getAllRoles(dtInput, ((ActualUser) au.getPrincipal()).getUserInfo().getRoleId());
		} catch (Exception ex) {
			logger.error("CTRLR Error : " + ex);
		}
		return null;
	}

	@RequestMapping(value = { (ViewConstants.roleURL1 + ViewConstants.select),
			(ViewConstants.roleURL1 + ViewConstants.add) })
	public String viewRole(Model model, Locale locale, Authentication au,
			@RequestParam(NavigationConstants.rcdKey) Long rcdKey,
			@RequestParam(NavigationConstants.CURDOpt) Integer curdOpt) {
		logger.info("Inside [RoleController][viewRole]");
		RoleDto roleDto = null;
		try {
			if (StatusConstants.insert == curdOpt) {
				roleDto = new RoleDto();
				model.addAttribute(ViewConstants.actionURL, (ViewConstants.roleURL1 + ViewConstants.save));
			} else {
				roleDto = roleService.getDataByKeyAndStatusAndExcludeKey(rcdKey,
						((ActualUser) au.getPrincipal()).getUserInfo().getRoleId());
				if (StatusConstants.edit == curdOpt) {
					model.addAttribute(ViewConstants.actionURL, (ViewConstants.roleURL1 + ViewConstants.edit));
				} else if (StatusConstants.delete == curdOpt) {
					model.addAttribute(ViewConstants.actionURL, (ViewConstants.roleURL1 + ViewConstants.delete));
				} else if (StatusConstants.view == curdOpt) {
					model.addAttribute(ViewConstants.actionURL, ViewConstants.roleURL4);
				}
			}
			model.addAttribute(ViewConstants.modelAttribute, roleDto);
			((ActualUser) au.getPrincipal()).getCurrentUrlDetails().setOpsType(curdOpt);
		} catch (CustomException c) {
			model.addAttribute(NavigationConstants.errmsg,
					messageSource.getMessage(MsgResolver.getMsgCodeKey(c.getMsgCode()), null, locale));
			logger.error("CTRLR Error : " + c);
		} catch (Exception ex) {
			model.addAttribute(NavigationConstants.errmsg, messageSource
					.getMessage(MsgResolver.getMsgCodeKey(MsgConstants.problemOccouredMsgCode), null, locale));
			logger.error("CTRLR Error : " + ex);
		} finally {
			roleDto = null;
		}
		return ViewConstants.roleURL1;
	}

	@RequestMapping(value = { (ViewConstants.roleURL1 + ViewConstants.save),
			(ViewConstants.roleURL1 + ViewConstants.edit), (ViewConstants.roleURL1 + ViewConstants.delete) })
	public String userOps(Model model, Locale locale, Authentication au,
			@RequestParam(NavigationConstants.CURDOpt) Integer curdOpt,
			@ModelAttribute(ViewConstants.modelAttribute) @Valid RoleDto roleDto, BindingResult result) {
		try {
			if (result.hasErrors()) {
				return ViewConstants.roleURL1;
			}
			if (StatusConstants.insert == curdOpt) {
				roleService.save(roleDto, RequestUtil.getUserKeyFromAuth(au));
			} else if (StatusConstants.edit == curdOpt) {
				roleService.edit(roleDto, RequestUtil.getUserKeyFromAuth(au));
			} else if (StatusConstants.delete == curdOpt) {
				roleService.delete(roleDto.getRoleId());
			}
			model.addAttribute(NavigationConstants.successMsg,
					messageSource.getMessage(MsgResolver.getMsgCodeKey(MsgConstants.successMsgCode), null, locale));
			logger.info("CTRLR info : Action [" + curdOpt + "] Completed Successfully.");
			((ActualUser) au.getPrincipal()).getCurrentUrlDetails().setOpsType(StatusConstants.viewAll);
		} catch (CustomException c) {
			model.addAttribute(NavigationConstants.errmsg,
					messageSource.getMessage(MsgResolver.getMsgCodeKey(c.getMsgCode()), null, locale));
			logger.error("CTRLR Error : " + c);
		} catch (Exception ex) {
			model.addAttribute(NavigationConstants.errmsg, messageSource
					.getMessage(MsgResolver.getMsgCodeKey(MsgConstants.problemOccouredMsgCode), null, locale));
			logger.error("CTRLR Error : " + ex);
		}
		return ViewConstants.roleURL1;
	}

}
