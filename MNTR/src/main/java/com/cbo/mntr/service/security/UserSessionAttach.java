package com.cbo.mntr.service.security;

import java.util.List;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class UserSessionAttach implements HttpSessionBindingListener {

	private String userId;
	private SessionActiveUser sessionActiveUser;

	public UserSessionAttach(String userId, SessionActiveUser sessionActiveUser) {
		super();
		this.userId = userId;
		this.sessionActiveUser = sessionActiveUser;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		List<String> users = sessionActiveUser.getUsers();
		UserSessionAttach user = (UserSessionAttach) event.getValue();
		if (!users.contains(user.getUserId())) {
			users.add(user.getUserId());
		}
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		List<String> users = sessionActiveUser.getUsers();
		UserSessionAttach user = (UserSessionAttach) event.getValue();
		if (users.contains(user.getUserId())) {
			users.remove(user.getUserId());
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}