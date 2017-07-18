package com.cbo.mntr.service;

import com.cbo.mntr.dto.PasswordDetailsDTO;
import com.cbo.mntr.entity.PasswordDetails;

public interface PasswordDetailsService {

	public PasswordDetailsDTO getPasswordByUserKey(Long userKey) throws Exception;

	public void savePassword(PasswordDetails passwordDetails) throws Exception;

	public void updatePassword(PasswordDetailsDTO passwordDetails) throws Exception;

}
