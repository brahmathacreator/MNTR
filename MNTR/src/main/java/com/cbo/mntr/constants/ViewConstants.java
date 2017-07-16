package com.cbo.mntr.constants;

import java.io.Serializable;

public class ViewConstants implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String rootView = "/";
	public static final String redirect = "redirect:";
	public static final String actionURL = "actionURL";
	public static final String home = "home";
	public static final String login = "login";
	public static final String accessDenied = "accessDenied?failed=true";
	public static final String sessionFailure = "invalidSession";
	public static final String registerSystemUser = "registerSystemUser";
	public static final String saveSystemUser = "saveSystemUser";
	public static final String regConfirmationURL = "registrationConfirm?key=";

}
