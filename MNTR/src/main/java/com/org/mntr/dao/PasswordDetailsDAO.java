package com.org.mntr.dao;

import com.org.mntr.entity.PasswordDetails;

public interface PasswordDetailsDAO {

	public PasswordDetails getPasswordByUserKey(Long userKey) throws Exception;

	public PasswordDetails getPasswordByPwdRefId(Long pwdRefId) throws Exception;

	public void savePassword(PasswordDetails passwordDetails) throws Exception;

	public void updatePassword(PasswordDetails passwordDetails) throws Exception;

}
