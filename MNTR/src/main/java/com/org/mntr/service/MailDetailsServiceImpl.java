package com.org.mntr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.mntr.dao.MailDetailsDAO;
import com.org.mntr.entity.MailDetails;

@Service("mailDetailsService")
public class MailDetailsServiceImpl implements MailDetailsService {

	@Autowired
	@Qualifier("mailDetailsDAO")
	private MailDetailsDAO mailDetailsDao;

	@Override
	@Transactional
	public void saveMailDetails(MailDetails mailDetails) throws Exception {
		mailDetailsDao.saveMailDetails(mailDetails);
	}

}
