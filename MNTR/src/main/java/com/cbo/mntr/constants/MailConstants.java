package com.cbo.mntr.constants;

import java.io.Serializable;

public class MailConstants implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String supportMailkey = "support.email";
	public final static String velocityTemplatePathKey = "mail.template.path";
	public final static String velocityTemplateKey = "mail.template.name";
	public final static String velocityTemplateRegSubjectKey = "mail.regconfirm.subject";
	public final static String velocityRegTemplatekey1 = "userid";
	public final static String velocityRegTemplatekey2 = "regConfirmURL";
	public final static String velocityRegTemplatekey3 = "appURL";
	public final static int regConfirmationMail = 1;
	public final static int firstTimePasswordChange = 1;
	public final static int generalPasswordChange = 2;
}
