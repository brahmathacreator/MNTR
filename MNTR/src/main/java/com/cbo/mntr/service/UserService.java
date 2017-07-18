package com.cbo.mntr.service;

import com.cbo.mntr.dto.UserInfoDTO;
import com.cbo.mntr.entity.UserInfo;

public interface UserService {

	public UserInfo getUserByName(String userName) throws Exception;

	public Long getUserCount() throws Exception;

	public void saveSystemUser(UserInfoDTO user) throws Exception;

}
