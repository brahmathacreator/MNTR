package com.cbo.mntr.service;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.cbo.mntr.dto.RoleDto;
import com.cbo.mntr.entity.UserRole;

public interface RoleService extends CommonService<RoleDto> {

	public DataTablesOutput<UserRole> getAllRoles(DataTablesInput dtInput, Long eId) throws Exception;

}
