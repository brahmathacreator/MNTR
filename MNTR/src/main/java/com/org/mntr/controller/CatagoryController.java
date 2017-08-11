package com.org.mntr.controller;

import java.util.Locale;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.mntr.constants.ViewConstants;
import com.org.mntr.dto.CatagoryDto;
import com.org.mntr.entity.UserRole;

@Controller
public class CatagoryController implements GenericController<CatagoryDto> {

	@Override
	@RequestMapping(value = { (ViewConstants.catagoryURL1) })
	public String navigateMainPage(Model model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping(value = { (ViewConstants.catagoryURL1 + ViewConstants.select),
			(ViewConstants.catagoryURL1 + ViewConstants.add) })
	public String viewAddNavigate(Model model, Locale locale, Authentication au, Long rcdKey, Integer curdOpt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping(value = { (ViewConstants.catagoryURL1 + ViewConstants.list) })
	public DataTablesOutput<UserRole> loadList(Model model, DataTablesInput dtInput, Authentication au) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping(value = { (ViewConstants.catagoryURL1 + ViewConstants.save),
			(ViewConstants.catagoryURL1 + ViewConstants.edit), (ViewConstants.catagoryURL1 + ViewConstants.delete) })
	public String userOps(Model model, Locale locale, Authentication au, Integer curdOpt, CatagoryDto object,
			BindingResult result) {
		// TODO Auto-generated method stub
		return null;
	}

}
