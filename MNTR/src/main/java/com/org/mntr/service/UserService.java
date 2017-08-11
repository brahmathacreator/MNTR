package com.org.mntr.service;

import java.util.Locale;

import com.org.mntr.dto.UserInfoDTO;

public interface UserService {

	public UserInfoDTO getUserByNameToLogin(String userName, Locale locale) throws Exception;

	public Long getUserCount() throws Exception;

	public void saveSystemUser(UserInfoDTO user) throws Exception;

}
