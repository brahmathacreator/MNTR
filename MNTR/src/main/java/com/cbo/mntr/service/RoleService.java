package com.cbo.mntr.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.cbo.mntr.dto.RoleDto;

public interface RoleService {

	public DataTablesOutput<RoleDto> getAllRoles(DataTablesInput dtInput) throws Exception;

	public List<RoleDto> getRoleById(Long roleId) throws Exception;

	public List<RoleDto> getAllRolesByNameWithPagination(String roleName, Pageable pageRequest) throws Exception;

}
