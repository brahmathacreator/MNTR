package com.cbo.mntr.jpa;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.cbo.mntr.entity.UserRole;

public interface RoleRepository extends DataTablesRepository<UserRole, Long> {

	public UserRole findByRoleIdAndStatusAndRoleIdNot(Long roleId, Integer status, Long eRoleId) throws Exception;

}
