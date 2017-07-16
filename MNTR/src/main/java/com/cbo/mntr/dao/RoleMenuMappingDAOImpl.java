package com.cbo.mntr.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import com.cbo.mntr.dao.common.AbstractDAO;
import com.cbo.mntr.entity.RoleMenuMapping;

@Repository("rmMappingDAO")
public class RoleMenuMappingDAOImpl extends AbstractDAO<Serializable, RoleMenuMapping> implements RoleMenuMappingDAO {

	@Override
	public void saveRoleMenuMapping(RoleMenuMapping roleMenuMapping) throws Exception {
		save(roleMenuMapping);
	}

}
