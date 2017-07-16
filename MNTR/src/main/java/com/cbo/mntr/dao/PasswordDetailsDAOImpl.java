package com.cbo.mntr.dao;

import java.io.Serializable;
import org.springframework.stereotype.Repository;
import com.cbo.mntr.dao.common.AbstractDAO;
import com.cbo.mntr.entity.PasswordDetails;

@Repository("pwdDetailsDAO")
public class PasswordDetailsDAOImpl extends AbstractDAO<Serializable, PasswordDetails> implements PasswordDetailsDAO {

	@Override
	public void savePassword(PasswordDetails passwordDetails) throws Exception {
		save(passwordDetails);
	}

	@Override
	public PasswordDetails getPasswordByUserKey(Long userKey) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
