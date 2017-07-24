package com.cbo.mntr.constants;

import java.io.Serializable;

public class UserConstants implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int systemLogin = 1;
	public static final int ldapLogin = 2;

	public static final int parentMenu = 0;
	public static final int childMenu = 1;

	public static final String genericRole = "ROLE_";
	public static final String defaultLogoPathKey = "user.logo.path";
	public static final Long anonymousUser = 0L;
	public static final String systemUserRole = "SYSTEM USER ROLE";
	public static final String suaCount = "suaCount";

}
