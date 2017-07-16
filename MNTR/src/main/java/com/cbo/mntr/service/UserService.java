package com.cbo.mntr.service;

import com.cbo.mntr.dto.UserInfoDTO;
import com.cbo.mntr.entity.UserInfo;

public interface UserService {

	public UserInfo getUserByName(String userName);

	public Integer getUserCount();

	public Integer saveSystemUser(UserInfoDTO user);

}
