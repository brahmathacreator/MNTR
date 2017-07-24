package com.cbo.mntr.service.security;

import java.util.List;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class UserSessionAttach implements HttpSessionBindingListener {

	private String userId;
	private SessionActiveUser sessionActiveUser;
	private boolean alreadyLoggedIn;

	public UserSessionAttach(final String userId, final SessionActiveUser sessionActiveUser) {
		super();
		this.userId = userId;
		this.sessionActiveUser = sessionActiveUser;
	}

	@Override
	public void valueBound(final HttpSessionBindingEvent event) {
		final List<String> users = sessionActiveUser.getUsers();
		final UserSessionAttach user = (UserSessionAttach) event.getValue();
		alreadyLoggedIn = false;
		if (users != null && !users.contains(user.getUserId())) {
			users.add(user.getUserId());
		} else {
			alreadyLoggedIn = true;
		}
	}

	@Override
	public void valueUnbound(final HttpSessionBindingEvent event) {
		final List<String> users = sessionActiveUser.getUsers();
		final UserSessionAttach user = (UserSessionAttach) event.getValue();
		if (user != null && users.contains(user.getUserId())) {
			users.remove(user.getUserId());
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isAlreadyLoggedIn() {
		return alreadyLoggedIn;
	}

	public void setAlreadyLoggedIn(boolean alreadyLoggedIn) {
		this.alreadyLoggedIn = alreadyLoggedIn;
	}

}
