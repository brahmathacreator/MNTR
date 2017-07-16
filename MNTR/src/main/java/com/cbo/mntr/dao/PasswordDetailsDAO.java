package com.cbo.mntr.dao;

import com.cbo.mntr.entity.PasswordDetails;

public interface PasswordDetailsDAO {

	public PasswordDetails getPasswordByUserKey(Long userKey) throws Exception;

	public void savePassword(PasswordDetails passwordDetails) throws Exception;

}
