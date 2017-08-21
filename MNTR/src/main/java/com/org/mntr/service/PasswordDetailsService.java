package com.org.mntr.service;

import com.org.mntr.dto.PasswordDetailsDto;
import com.org.mntr.entity.PasswordDetails;

public interface PasswordDetailsService {

	public PasswordDetailsDto getPasswordByUserKey(Long userKey) throws Exception;

	public void savePassword(PasswordDetails passwordDetails) throws Exception;

	public void updatePassword(PasswordDetailsDto passwordDetails) throws Exception;

}
