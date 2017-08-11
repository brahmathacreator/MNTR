package com.org.mntr.service;

import com.org.mntr.dto.PasswordDetailsDTO;
import com.org.mntr.entity.PasswordDetails;

public interface PasswordDetailsService {

	public PasswordDetailsDTO getPasswordByUserKey(Long userKey) throws Exception;

	public void savePassword(PasswordDetails passwordDetails) throws Exception;

	public void updatePassword(PasswordDetailsDTO passwordDetails) throws Exception;

}
