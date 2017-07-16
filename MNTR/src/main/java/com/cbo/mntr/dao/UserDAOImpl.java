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
		c.add(Restrictions.eq("USER_ID", userName));
		c.add(Restrictions.eq("STATUS", StatusConstants.active));
		return (UserInfo) c.uniqueResult();
	}

	@Override
	public Integer getUserCount() {
		Criteria c = createEntityCriteria();
		c.add(Restrictions.eq("STATUS", StatusConstants.active));
		c.setProjection(Projections.rowCount());
		return (Integer) c.uniqueResult();
	}

	@Override
	public void saveSystemUser(UserInfo user) {
		save(user);
	}

}
