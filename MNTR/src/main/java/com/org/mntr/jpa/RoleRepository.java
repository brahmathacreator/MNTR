package com.org.mntr.jpa;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.org.mntr.entity.UserRole;

public interface RoleRepository extends DataTablesRepository<UserRole, Long> {

	public UserRole findByRoleIdAndRoleIdNot(Long roleId, Long eRoleId) throws Exception;

}
