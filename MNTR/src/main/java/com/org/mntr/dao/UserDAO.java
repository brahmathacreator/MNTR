package com.org.mntr.dao;

import com.org.mntr.entity.UserInfo;

public interface UserDAO {

	public UserInfo getUserByName(String userName);

	public Long getUserCount();

	public void saveSystemUser(UserInfo user);

}
