package com.cbo.mntr.events;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

public class MailEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private final Locale locale;
	private final Object Object;
	private final String appBaseURL;
	private final int mailType;

	public MailEvent(Object source, final Object user, final Locale locale, final String appBaseURL,
			final int mailType) {
		super(source);
		this.locale = locale;
		this.Object = user;
		this.appBaseURL = appBaseURL;
		this.mailType = mailType;
	}

	public Locale getLocale() {
		return locale;
	}

	public Object getObject() {
		return Object;
	}

	public String getAppBaseURL() {
		return appBaseURL;
	}

	public int getMailType() {
		return mailType;
	}

}
