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
	public static final String curdOpt = "CURDOpt";
	public static final String modelAttribute = "mObject";
	public static final String authSuccessPage = "/navigate?navParam=home&navMenuId=0&navMenuType=1&CURDOpt=4";
	public static final String home = "home";
	public static final String login = "login";
	public static final String logout = "logout";
	public static final String navURL = "navigate";
	public static final String accessDenied = "accessDenied";
	public static final String sessionFailure = "invalidSession";
	public static final String registerSystemUser = "registerSystemUser";
	public static final String saveSystemUser = "saveSystemUser";
	public static final String regConfirmationURL = "registrationConfirm";
	public static final String regConfirmationParam1 = "key1";
	public static final String regConfirmationParam2 = "key2";
	public static final String changePasswordView = "changePassword";
	public static final String changePasswordURL1 = "savePassword";
	public static final String catagoryView = "catagory";
	public static final String catagoryURL1 = "createCatagory";
	public static final String machineView = "machine";
	public static final String machineURL1 = "createMachine";
	public static final String roleView = "role";
	public static final String roleURL1 = "createRole";
	public static final String userView = "users";
	public static final String userURL1 = "createuser";
	public static final String permissionView = "permission";
	public static final String permissionURL1 = "createPermission";

}
