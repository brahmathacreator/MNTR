package com.org.mntr.controller;

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
import com.fasterxml.jackson.annotation.JsonView;
import com.org.mntr.constants.MsgConstants;
import com.org.mntr.constants.NavigationConstants;
import com.org.mntr.constants.StatusConstants;
import com.org.mntr.constants.ViewConstants;
import com.org.mntr.dto.ActualUser;
import com.org.mntr.dto.TemplateDto;
import com.org.mntr.entity.Template;
import com.org.mntr.exceptions.CustomException;
import com.org.mntr.service.TemplateService;
import com.org.mntr.utils.MsgResolver;

@Controller
public class TemplateController {

	private static final Logger logger = Logger.getLogger(TemplateController.class);

	@Autowired
	@Qualifier("templateService")
	private TemplateService service;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = { (ViewConstants.templateURL1) })
	public String navigateMainPage(Model model) {
		logger.info("Inside [TemplateController][navigateMainPage]");
		return ViewConstants.templateURL1;
	}

	@RequestMapping(value = { (ViewConstants.templateURL1 + ViewConstants.select),
			(ViewConstants.templateURL1 + ViewConstants.add) })
	public String viewAddNavigate(Model model, Locale locale, Authentication au,
			@RequestParam(NavigationConstants.rcdKey) Long rcdKey,
			@RequestParam(NavigationConstants.CURDOpt) Integer curdOpt) {
		logger.info("Inside [TemplateController][viewAddNavigate]");
		TemplateDto dto = null;
		try {
			if (StatusConstants.insert == curdOpt) {
				dto = new TemplateDto();
				model.addAttribute(ViewConstants.actionURL, (ViewConstants.templateURL1 + ViewConstants.save));
			} else {
				dto = service.getDataByKeyAndStatus(rcdKey);
				if (StatusConstants.edit == curdOpt) {
					model.addAttribute(ViewConstants.actionURL, (ViewConstants.templateURL1 + ViewConstants.edit));
				} else if (StatusConstants.delete == curdOpt) {
					model.addAttribute(ViewConstants.actionURL, (ViewConstants.templateURL1 + ViewConstants.delete));
				} else if (StatusConstants.view == curdOpt) {
					model.addAttribute(ViewConstants.actionURL, ViewConstants.templateURL1);
				}
			}
			model.addAttribute(ViewConstants.modelAttribute, dto);
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
			dto = null;
		}
		return ViewConstants.templateURL1;

	}

	@JsonView(DataTablesOutput.View.class)
	@ResponseBody
	@RequestMapping(value = { (ViewConstants.templateURL1 + ViewConstants.list) })
	public DataTablesOutput<Template> loadList(Model model, @Valid @RequestBody DataTablesInput dtInput,
			Authentication au) {
		logger.info("Inside [TemplateController][loadList]");
		try {
			return service.getAllData(dtInput);
		} catch (Exception ex) {
			logger.error("CTRLR Error : " + ex);
		}
		return null;
	}

	@RequestMapping(value = { (ViewConstants.templateURL1 + ViewConstants.save),
			(ViewConstants.templateURL1 + ViewConstants.edit), (ViewConstants.templateURL1 + ViewConstants.delete) })
	public String userOps(Model model, Locale locale, Authentication au,
			@RequestParam(NavigationConstants.CURDOpt) Integer curdOpt,
			@ModelAttribute(ViewConstants.modelAttribute) @Valid TemplateDto object, BindingResult result) {
		try {
			if (result.hasErrors()) {
				return ViewConstants.templateURL1;
			}
			if (StatusConstants.insert == curdOpt || StatusConstants.edit == curdOpt) {
				service.saveOrEdit(object);
			} else if (StatusConstants.delete == curdOpt) {
				service.delete(object.getTemplateId());
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
		return ViewConstants.templateURL1;

	}

}
