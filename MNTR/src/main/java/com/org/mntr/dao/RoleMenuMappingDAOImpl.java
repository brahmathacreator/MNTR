package com.org.mntr.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import com.org.mntr.dao.common.EntityManagerAbstractDAO;
import com.org.mntr.entity.RoleMenuMapping;

@Repository("rmMappingDAO")
public class RoleMenuMappingDAOImpl extends EntityManagerAbstractDAO<Serializable, RoleMenuMapping> implements RoleMenuMappingDAO {

	@Override
	public void saveRoleMenuMapping(RoleMenuMapping roleMenuMapping) throws Exception {
		save(roleMenuMapping);
	}

}
