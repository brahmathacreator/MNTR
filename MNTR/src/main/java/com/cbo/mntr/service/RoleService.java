package com.cbo.mntr.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.cbo.mntr.dto.RoleDto;

public interface RoleService {

	public List<RoleDto> getAllRoles(Pageable pageRequest);

	public List<RoleDto> getRoleById(Long roleId);

	public List<RoleDto> getAllRolesByNameWithPagination(String roleName, Pageable pageRequest);

}
