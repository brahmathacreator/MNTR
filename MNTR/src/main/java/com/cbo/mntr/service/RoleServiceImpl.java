package com.cbo.mntr.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbo.mntr.dto.RoleDto;
import com.cbo.mntr.entity.UserRole;
import com.cbo.mntr.jpa.RoleRepository;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleDto> getAllRoles(Pageable pageRequest) {
		List<RoleDto> roleDtos = null;
		List<UserRole> userRoles = null;
		try {
			userRoles = (List<UserRole>) roleRepository.findAll();
			BeanUtils.copyProperties(userRoles, roleDtos);
			return roleDtos;
		} finally {
			roleDtos = null;
			userRoles = null;
		}
	}

	@Override
	public List<RoleDto> getRoleById(Long roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleDto> getAllRolesByNameWithPagination(String roleName, Pageable pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
