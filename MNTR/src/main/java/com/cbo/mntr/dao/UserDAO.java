package com.cbo.mntr.dao;

import com.cbo.mntr.entity.UserInfo;

public interface UserDAO {

	public UserInfo getUserByName(String userName);

	public Long getUserCount();

	public void saveSystemUser(UserInfo user);

}
