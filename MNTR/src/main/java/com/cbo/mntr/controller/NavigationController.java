package com.cbo.mntr.controller;

import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.cbo.mntr.constants.NavigationConstants;
import com.cbo.mntr.constants.StatusConstants;
import com.cbo.mntr.constants.UserConstants;
import com.cbo.mntr.constants.ViewConstants;
import com.cbo.mntr.dto.ActualUser;
import com.cbo.mntr.dto.URLProps;
import com.cbo.mntr.utils.RequestUtil;
import ch.lambdaj.Lambda;

@Controller
public class NavigationController {

	private static final Logger logger = Logger.getLogger(NavigationController.class);

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = { ViewConstants.navURL })
	public String navigate(Model model, Locale locale, HttpServletRequest request,
			@RequestParam(NavigationConstants.navParam) String navParam,
			@RequestParam(NavigationConstants.navMenuId) Long navMenuId,
			@RequestParam(NavigationConstants.navMenuType) Integer navMenuType,
			@RequestParam(NavigationConstants.CURDOpt) Integer curdOpt) {
		URLProps urlProps = null;
		ActualUser au = null;
		List<URLProps> props = null;
		String sessionUser = null;
		try {
			try {
				sessionUser = ((ActualUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
						.getUserInfo().getUserId();
			} catch (Exception ex) {
				logger.error("CTRLR Error : Session Failure Exception: " + ex);
				return ViewConstants.redirect + ViewConstants.sessionFailure;
			}
			if (sessionUser != null && RequestUtil.checkSessionUser(request, sessionUser)) {
				urlProps = new URLProps();
				if (ViewConstants.home.equals(navParam)) {
					urlProps = new URLProps();
					urlProps.setMenuId(0L);
					urlProps.setMenuName(messageSource.getMessage("menu.home.menu", null, locale));
					urlProps.setMenuDesc(messageSource.getMessage("menu.home.menu.desc", null, locale));
					urlProps.setIconName("fa fa-fw fa-dashboard");
					urlProps.setOpsType(StatusConstants.dashboard);
					urlProps.setMenuType(navMenuType);
					urlProps.setMenuURL(navParam);
					urlProps.setMenuMaster(0);
					((ActualUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
							.setCurrentUrlDetails(urlProps);
					return ViewConstants.redirect + ViewConstants.home;
				} else {
					au = ((ActualUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
					if (UserConstants.parentMenu == navMenuType) {
						props = Lambda.select(au.getUserInfo().getParentURLList(),
								Lambda.having(Lambda.on(URLProps.class).getMenuId(), Matchers.equalTo(navMenuId)));
					} else {
						props = Lambda.select(au.getUserInfo().getChildURLList(),
								Lambda.having(Lambda.on(URLProps.class).getMenuId(), Matchers.equalTo(navMenuId)));
					}
					props.get(0).setOpsType(curdOpt);
					props.get(0).setMenuType(navMenuType);
					((ActualUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
							.setCurrentUrlDetails(props.get(0));
					return ViewConstants.redirect + navParam;
				}
			} else {
				logger.error("CTRLR Error : Session Expired. ");
			}
		} catch (Exception ex) {
			logger.error("CTRLR Error : " + ex);
		} finally {
			urlProps = null;
		}
		return ViewConstants.redirect + ViewConstants.accessDenied;
	}

	@RequestMapping(value = { ViewConstants.navAuthSuccess })
	public String navigateAuthSuccess(Model model, Locale locale, HttpServletRequest request,
			@RequestParam(NavigationConstants.navParam) String navParam,
			@RequestParam(NavigationConstants.navMenuId) Long navMenuId,
			@RequestParam(NavigationConstants.navMenuType) Integer navMenuType,
			@RequestParam(NavigationConstants.CURDOpt) Integer curdOpt) {
		URLProps urlProps = null;
		try {
			urlProps = new URLProps();
			if (ViewConstants.home.equals(navParam)) {
				urlProps = new URLProps();
				urlProps.setMenuId(0L);
				urlProps.setMenuName(messageSource.getMessage("menu.home.menu", null, locale));
				urlProps.setMenuDesc(messageSource.getMessage("menu.home.menu.desc", null, locale));
				urlProps.setIconName("fa fa-fw fa-dashboard");
				urlProps.setOpsType(StatusConstants.dashboard);
				urlProps.setMenuType(navMenuType);
				urlProps.setMenuURL(navParam);
				urlProps.setMenuMaster(0);
				((ActualUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
						.setCurrentUrlDetails(urlProps);
				return ViewConstants.redirect + ViewConstants.home;
			}
		} catch (Exception ex) {
			logger.error("CTRLR Error : " + ex);
		} finally {
			urlProps = null;
		}
		return ViewConstants.redirect + ViewConstants.accessDenied;
	}

	@RequestMapping(value = { ViewConstants.rootView }, method = RequestMethod.GET)
	public String loginPage(ModelMap model) {
		logger.info("Inside [LoginController][loginPage]");
		return ViewConstants.redirect + ViewConstants.rootView + ViewConstants.login;
	}

	@RequestMapping(value = { ViewConstants.accessDenied })
	public String accessDeniedPage(ModelMap model, HttpServletRequest request) {
		logger.info("Inside [LoginController][accessDeniedPage]");
		final boolean stat = RequestUtil.removeSessionUser(request);
		logger.info("Session Removal Stat : " + stat);
		return ViewConstants.accessDenied;
	}

	@RequestMapping(value = { ViewConstants.sessionFailure })
	public String sessionExpiredPage(ModelMap model, HttpServletRequest request) {
		logger.info("Inside [LoginController][sessionExpiredPage]");
		final boolean stat = RequestUtil.removeSessionUser(request);
		logger.info("Session Removal Stat : " + stat);
		if (stat)
			return ViewConstants.sessionFailure;
		else
			return ViewConstants.accessDenied;
	}

	@RequestMapping(value = { ViewConstants.logout })
	public String logoutPage(ModelMap model) {
		return ViewConstants.logout;
	}

}
