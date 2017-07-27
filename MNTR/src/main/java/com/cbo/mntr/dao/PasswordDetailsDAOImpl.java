package com.cbo.mntr.dao;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cbo.mntr.constants.StatusConstants;
import com.cbo.mntr.dao.common.EntityManagerAbstractDAO;
import com.cbo.mntr.entity.PasswordDetails;

@Repository("pwdDetailsDAO")
public class PasswordDetailsDAOImpl extends EntityManagerAbstractDAO<Serializable, PasswordDetails> implements PasswordDetailsDAO {

	@Override
	public void savePassword(PasswordDetails passwordDetails) throws Exception {
		save(passwordDetails);
	}

	@Override
	public PasswordDetails getPasswordByUserKey(Long userKey) throws Exception {
		Criteria c = null;
		try {
			c = createEntityCriteria();
			c.add(Restrictions.eq("user.userKey", userKey));
			c.add(Restrictions.eq("status", StatusConstants.active));
			return (PasswordDetails) c.uniqueResult();
		} finally {
			c = null;
		}
	}

	@Override
	public void updatePassword(PasswordDetails passwordDetails) throws Exception {
		update(passwordDetails);
	}

	@Override
	public PasswordDetails getPasswordByPwdRefId(Long pwdRefId) throws Exception {
		Criteria c = null;
		try {
			c = createEntityCriteria();
			c.add(Restrictions.eq("passRefId", pwdRefId));
			c.add(Restrictions.eq("status", StatusConstants.active));
			return (PasswordDetails) c.uniqueResult();
		} finally {
			c = null;
		}
	}

}
