package com.org.mntr.service;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.org.mntr.dto.TemplateDto;
import com.org.mntr.entity.UserRole;

public interface TemplateService extends GenericService<TemplateDto> {

	public DataTablesOutput<UserRole> getAllRoles(DataTablesInput dtInput, Long eId) throws Exception;

}
