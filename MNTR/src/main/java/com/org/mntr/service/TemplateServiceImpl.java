package com.org.mntr.service;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.org.mntr.constants.MsgConstants;
import com.org.mntr.dto.TemplateDto;
import com.org.mntr.entity.Template;
import com.org.mntr.exceptions.CustomException;
import com.org.mntr.jpa.TemplateRepository;

@Service("templateService")
@Transactional
public class TemplateServiceImpl implements TemplateService {

	@Autowired
	private TemplateRepository repository;

	@Override
	public DataTablesOutput<Template> getAllData(DataTablesInput dtInput, Long eId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataTablesOutput<Template> getAllData(DataTablesInput dtInput) throws Exception {
		DataTablesOutput<Template> page = null;
		try {
			page = repository.findAll(dtInput);
			return page;
		} finally {
			page = null;
		}
	}

	@Override
	public TemplateDto getDataByKeyAndStatus(Long id) throws Exception {
		TemplateDto dto = null;
		Template entity = null;
		try {
			entity = repository.findOne(id);
			if (entity != null) {
				dto = new TemplateDto();
				BeanUtils.copyProperties(entity, dto);
			} else {
				throw new CustomException(MsgConstants.noRecordsFound);
			}
			return dto;
		} finally {
			dto = null;
			entity = null;
		}
	}

	@Override
	public TemplateDto getDataByKeyAndStatusAndExcludeKey(Long id, Long eId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrEdit(TemplateDto object) throws Exception {
		Template entity = null;
		try {
			entity = new Template();
			BeanUtils.copyProperties(object, entity);
			repository.save(entity);
		} finally {
			object = null;
			entity = null;
		}

	}

	@Override
	public void delete(Long rcdKey) throws Exception {
		repository.delete(rcdKey);

	}

}
