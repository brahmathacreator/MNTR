package com.org.mntr.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.org.mntr.constants.NavigationConstants;
import com.org.mntr.constants.ViewConstants;
import com.org.mntr.entity.UserRole;
import com.fasterxml.jackson.annotation.JsonView;

public interface GenericController<T> {

	public abstract String navigateMainPage(Model model);

	public abstract String viewAddNavigate(Model model, Locale locale, Authentication au,
			@RequestParam(NavigationConstants.rcdKey) Long rcdKey,
			@RequestParam(NavigationConstants.CURDOpt) Integer curdOpt);

	@JsonView(DataTablesOutput.View.class)
	@ResponseBody
	public abstract DataTablesOutput<UserRole> loadList(Model model, @Valid @RequestBody DataTablesInput dtInput,
			Authentication au);

	public abstract String userOps(Model model, Locale locale, Authentication au,
			@RequestParam(NavigationConstants.CURDOpt) Integer curdOpt,
			@ModelAttribute(ViewConstants.modelAttribute) @Valid T object, BindingResult result);

}
