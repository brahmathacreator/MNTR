package com.cbo.mntr.controller;

import java.util.Locale;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cbo.mntr.constants.ViewConstants;
import com.cbo.mntr.dto.TemplateDto;
import com.cbo.mntr.entity.UserRole;

@Controller
public class TemplateController implements GenericController<TemplateDto> {

	@Override
	@RequestMapping(value = { (ViewConstants.templateURL1) })
	public String navigateMainPage(Model model) {
		return ViewConstants.templateURL1;
	}

	@Override
	@RequestMapping(value = { (ViewConstants.templateURL1 + ViewConstants.select),
			(ViewConstants.templateURL1 + ViewConstants.add) })
	public String viewAddNavigate(Model model, Locale locale, Authentication au, Long rcdKey, Integer curdOpt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping(value = { (ViewConstants.templateURL1 + ViewConstants.list) })
	public DataTablesOutput<UserRole> loadList(Model model, DataTablesInput dtInput, Authentication au) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping(value = { (ViewConstants.templateURL1 + ViewConstants.save),
			(ViewConstants.templateURL1 + ViewConstants.edit), (ViewConstants.templateURL1 + ViewConstants.delete) })
	public String userOps(Model model, Locale locale, Authentication au, Integer curdOpt, TemplateDto object,
			BindingResult result) {
		// TODO Auto-generated method stub
		return null;
	}

}
