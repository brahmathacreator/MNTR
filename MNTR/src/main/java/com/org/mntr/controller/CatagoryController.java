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
import com.org.mntr.dto.CategoryDto;
import com.org.mntr.entity.Category;
import com.org.mntr.exceptions.CustomException;
import com.org.mntr.service.CategoryService;
import com.org.mntr.utils.MsgResolver;

@Controller
public class CatagoryController {

	private static final Logger logger = Logger.getLogger(CatagoryController.class);

	@Autowired
	@Qualifier("categoryService")
	private CategoryService service;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = { (ViewConstants.catagoryURL1) })
	public String navigateMainPage(Model model) {
		logger.info("Inside [CatagoryController][navigateMainPage]");
		return ViewConstants.catagoryURL1;
	}

	@RequestMapping(value = { (ViewConstants.catagoryURL1 + ViewConstants.select),
			(ViewConstants.catagoryURL1 + ViewConstants.add) })
	public String viewAddNavigate(Model model, Locale locale, Authentication au,
			@RequestParam(NavigationConstants.rcdKey) Long rcdKey,
			@RequestParam(NavigationConstants.CURDOpt) Integer curdOpt) {
		logger.info("Inside [CatagoryController][viewAddNavigate]");
		CategoryDto dto = null;
		try {
			if (StatusConstants.insert == curdOpt) {
				dto = new CategoryDto();
				model.addAttribute(ViewConstants.actionURL, (ViewConstants.catagoryURL1 + ViewConstants.save));
			} else {
				dto = service.getDataByKeyAndStatus(rcdKey);
				if (StatusConstants.edit == curdOpt) {
					model.addAttribute(ViewConstants.actionURL, (ViewConstants.catagoryURL1 + ViewConstants.edit));
				} else if (StatusConstants.delete == curdOpt) {
					model.addAttribute(ViewConstants.actionURL, (ViewConstants.catagoryURL1 + ViewConstants.delete));
				} else if (StatusConstants.view == curdOpt) {
					model.addAttribute(ViewConstants.actionURL, ViewConstants.catagoryURL1);
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
		return ViewConstants.catagoryURL1;

	}

	@JsonView(DataTablesOutput.View.class)
	@ResponseBody
	@RequestMapping(value = { (ViewConstants.catagoryURL1 + ViewConstants.list) })
	public DataTablesOutput<Category> loadList(Model model, @Valid @RequestBody DataTablesInput dtInput,
			Authentication au) {
		logger.info("Inside [CatagoryController][loadList]");
		try {
			return service.getAllData(dtInput);
		} catch (Exception ex) {
			logger.error("CTRLR Error : " + ex);
		}
		return null;
	}

	@RequestMapping(value = { (ViewConstants.catagoryURL1 + ViewConstants.save),
			(ViewConstants.catagoryURL1 + ViewConstants.edit), (ViewConstants.catagoryURL1 + ViewConstants.delete) })
	public String userOps(Model model, Locale locale, Authentication au,
			@RequestParam(NavigationConstants.CURDOpt) Integer curdOpt,
			@ModelAttribute(ViewConstants.modelAttribute) @Valid CategoryDto object, BindingResult result) {
		try {
			if (result.hasErrors()) {
				return ViewConstants.catagoryURL1;
			}
			if (StatusConstants.insert == curdOpt || StatusConstants.edit == curdOpt) {
				service.saveOrEdit(object);
			} else if (StatusConstants.delete == curdOpt) {
				service.delete(object.getCategoryId());
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
		return ViewConstants.catagoryURL1;

	}

}
