package com.org.mntr.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.org.mntr.constants.MsgConstants;
import com.org.mntr.dto.UserRoleDto;
import com.org.mntr.entity.UserRole;
import com.org.mntr.exceptions.CustomException;
import com.org.mntr.jpa.RoleRepository;
import com.org.mntr.jpa.specs.RoleSpec;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	@Qualifier("roleSpec")
	private RoleSpec rolespec;

	@Override
	public DataTablesOutput<UserRole> getAllData(DataTablesInput dtInput, Long eId) throws Exception {
		DataTablesOutput<UserRole> page = null;
		try {
			page = roleRepository.findAll(dtInput, rolespec.excludeId(eId));
			return page;
		} finally {
			page = null;
		}
	}

	@Override
	public UserRoleDto getDataByKeyAndStatusAndExcludeKey(Long id, Long eId) throws Exception {
		UserRoleDto roleDto = null;
		UserRole role = null;
		try {
			role = roleRepository.findByRoleIdAndRoleIdNot(id, eId);
			if (role != null) {
				roleDto = new UserRoleDto();
				BeanUtils.copyProperties(role, roleDto);
			} else {
				throw new CustomException(MsgConstants.noRecordsFound);
			}
			return roleDto;
		} finally {
			roleDto = null;
			role = null;
		}
	}

	@Override
	public void saveOrEdit(UserRoleDto object) throws Exception {
		UserRole role = null;
		try {
			role = new UserRole();
			BeanUtils.copyProperties(object, role);
			roleRepository.save(role);
		} finally {
			object = null;
			role = null;
		}
	}

	@Override
	public void delete(Long rcdKey) throws Exception {
		roleRepository.delete(rcdKey);
	}

	@Override
	public UserRoleDto getDataByKeyAndStatus(Long id) throws Exception {
		return null;
	}

	@Override
	public DataTablesOutput<UserRole> getAllData(DataTablesInput dtInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}