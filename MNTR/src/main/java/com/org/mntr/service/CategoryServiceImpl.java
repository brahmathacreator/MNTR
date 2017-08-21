package com.org.mntr.service;

import java.util.Properties;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.org.mntr.constants.MsgConstants;
import com.org.mntr.constants.SSValidationConfig;
import com.org.mntr.dto.CategoryDto;
import com.org.mntr.entity.Category;
import com.org.mntr.exceptions.CustomException;
import com.org.mntr.jpa.CategoryRepository;
import com.org.mntr.service.security.AESWithPBKDFHashCryptoUtil;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Autowired
	@Qualifier("AESUtil")
	private AESWithPBKDFHashCryptoUtil aesUtil;

	@Autowired
	private Properties mntrProperties;

	@Override
	public DataTablesOutput<Category> getAllData(DataTablesInput dtInput) throws Exception {
		DataTablesOutput<Category> page = null;
		try {
			page = repository.findAll(dtInput);
			return page;
		} finally {
			page = null;
		}
	}

	@Override
	public CategoryDto getDataByKeyAndStatus(Long id) throws Exception {
		CategoryDto dto = null;
		Category entity = null;
		try {
			entity = repository.findOne(id);
			if (entity != null) {
				dto = new CategoryDto();
				BeanUtils.copyProperties(entity, dto);
				dto.setMasterPassword(aesUtil.URLDecodeAndDecryptData(entity.getMasterPassword(),
						mntrProperties.getProperty(SSValidationConfig.masterPassCode)));
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
	public CategoryDto getDataByKeyAndStatusAndExcludeKey(Long id, Long eId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrEdit(CategoryDto object) throws Exception {
		Category entity = null;
		try {
			entity = new Category();
			BeanUtils.copyProperties(object, entity);
			entity.setMasterPassword(aesUtil.encryptData(object.getMasterPassword(),
					mntrProperties.getProperty(SSValidationConfig.masterPassCode)));
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

	@Override
	public DataTablesOutput<Category> getAllData(DataTablesInput dtInput, Long eId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
