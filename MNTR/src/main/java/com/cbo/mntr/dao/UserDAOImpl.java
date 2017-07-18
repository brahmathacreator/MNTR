package com.cbo.mntr.dao;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.cbo.mntr.constants.StatusConstants;
import com.cbo.mntr.dao.common.AbstractDAO;
import com.cbo.mntr.entity.UserInfo;

@Repository("userDAO")
public class UserDAOImpl extends AbstractDAO<Serializable, UserInfo> implements UserDAO {

	@Override
	public UserInfo getUserByName(String userName) {
		Criteria c = createEntityCriteria();
		c.add(Restrictions.eq("userId", userName));
		c.add(Restrictions.eq("status", StatusConstants.active));
		return (UserInfo) c.uniqueResult();
	}

	@Override
	public Long getUserCount() {
		Criteria c = createEntityCriteria();
		c.add(Restrictions.eq("status", StatusConstants.active));
		c.setProjection(Projections.rowCount());
		return (Long) c.uniqueResult();
	}

	@Override
	public void saveSystemUser(UserInfo user) {
		save(user);
	}

}
