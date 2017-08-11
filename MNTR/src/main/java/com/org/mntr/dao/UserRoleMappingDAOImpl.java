package com.org.mntr.dao;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.org.mntr.constants.MsgConstants;
import com.org.mntr.dao.common.EntityManagerAbstractDAO;
import com.org.mntr.entity.UserRoleMapping;

@Repository("urMappingDAO")
public class UserRoleMappingDAOImpl extends EntityManagerAbstractDAO<Serializable, UserRoleMapping> implements UserRoleMappingDAO {

	private static final Logger logger = Logger.getLogger(UserRoleMappingDAOImpl.class);

	@Override
	public Integer saveUserRoleMapping(UserRoleMapping userRoleMapping) {
		logger.info("Inside [UserRoleMappingDAOImpl][saveUserRoleMapping]");
		try {
			save(userRoleMapping);
			return MsgConstants.successMsgCode;
		} catch (Exception ex) {
			logger.error("DAO ERROR: " + ex);
		}
		return MsgConstants.problemOccouredMsgCode;
	}

}
