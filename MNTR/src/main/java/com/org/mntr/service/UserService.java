package com.org.mntr.service;

import java.util.Locale;

import com.org.mntr.dto.UserInfoDto;

public interface UserService {

	public UserInfoDto getUserByNameToLogin(String userName, Locale locale) throws Exception;

	public Long getUserCount() throws Exception;

	public void saveSystemUser(UserInfoDto user) throws Exception;

}
