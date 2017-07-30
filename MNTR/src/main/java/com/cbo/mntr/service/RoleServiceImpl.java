package com.cbo.mntr.service;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
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

	@Override
	public DataTablesOutput<RoleDto> getAllRoles(DataTablesInput dtInput) throws Exception {
		DataTablesOutput<UserRole> page = null;
		DataTablesOutput<RoleDto> roleDto = null;
		RoleDto tRoleDto = null;
		try {
			page = roleRepository.findAll(dtInput);
			roleDto = new DataTablesOutput<RoleDto>();
			for (UserRole ur : page.getData()) {
				tRoleDto = new RoleDto();
				BeanUtils.copyProperties(ur, tRoleDto);
				roleDto.getData().add(tRoleDto);
				tRoleDto = null;
			}
			return roleDto;
		} finally {
			page = null;
			roleDto = null;
			tRoleDto = null;

		}
	}

	@Override
	public List<RoleDto> getRoleById(Long roleId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleDto> getAllRolesByNameWithPagination(String roleName, Pageable pageRequest) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
