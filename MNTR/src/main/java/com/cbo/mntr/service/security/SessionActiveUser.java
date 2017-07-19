package com.cbo.mntr.service.security;

import java.util.ArrayList;
import java.util.List;

public class SessionActiveUser {

	public List<String> users;

	public SessionActiveUser() {
		users = new ArrayList<String>();
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

}
