package com.cbo.mntr.constants;

import java.io.Serializable;

public class ViewConstants implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String rootView = "/";
	public static final String rootParemSeperator = "?";
	public static final String paremSeperator = "&";
	public static final String paremValueSeperator = "=";
	public static final String applicationDataSeperator = "|";
	public static final String applicationDataEscaper = "\\";
	public static final String redirect = "redirect:";
	public static final String forward = "forward:";
	public static final String actionURL = "actionURL";
	public static final String modelAttribute = "mObject";
	public static final String authSuccessPage = "/navigateAuthSucc?navParam=home&navMenuId=0&navMenuType=0&CURDOpt=4";
	public static final String home = "home";
	public static final String login = "login";
	public static final String logout = "logout";
	public static final String navURL = "navigate";
	public static final String list = "LIST";
	public static final String add = "ADD";
	public static final String edit = "EDIT";
	public static final String delete = "DELETE";
	public static final String save = "SAVE";
	public static final String select = "SELECT";
	public static final String navAuthSuccess = "navigateAuthSucc";
	public static final String accessDeniedURL = "accessDenied";
	public static final String errorURL = "errorPage";
	public static final String sessionFailure = "invalidSession";
	public static final String registerSystemUser = "registerSystemUser";
	public static final String saveSystemUser = "saveSystemUser";
	public static final String regConfirmationURL = "registrationConfirm";
	public static final String regConfirmationParam1 = "key1";
	public static final String regConfirmationParam2 = "key2";
	public static final String changePasswordView = "changePassword";
	public static final String changePasswordURL1 = "savePassword";
	public static final String catagoryURL1 = "createCatagory";
	public static final String machineURL1 = "createMachine";
	public static final String roleURL1 = "createrole";
	public static final String roleURL2 = "getRoleList";
	public static final String roleURL3 = "selectRole";
	public static final String roleURL4 = "viewRole";
	public static final String userURL1 = "createuser";
	public static final String permissionURL1 = "createPermission";
	public static final String templateURL1 = "template";

}
