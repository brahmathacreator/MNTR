package com.cbo.mntr.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbo.mntr.dao.PasswordDetailsDAO;
import com.cbo.mntr.dto.PasswordDetailsDTO;
import com.cbo.mntr.entity.PasswordDetails;

@Service("pwdService")
public class PasswordDetailsServiceImpl implements PasswordDetailsService {

	private static final Logger logger = Logger.getLogger(PasswordDetailsServiceImpl.class);

	@Autowired
	@Qualifier("pwdDetailsDAO")
	private PasswordDetailsDAO pwdDetailsDao;

	@Override
	@Transactional
	public PasswordDetailsDTO getPasswordByUserKey(Long userKey) throws Exception {
		logger.info("Inside [PasswordDetailsServiceImpl][PasswordDetailsDTO]");
		PasswordDetailsDTO pwdDetails = null;
		PasswordDetails passwordDetails = null;
		try {
			passwordDetails = pwdDetailsDao.getPasswordByUserKey(userKey);
			pwdDetails = new PasswordDetailsDTO();
			pwdDetails.setUuid(passwordDetails.getPwdUUID());
			pwdDetails.setUuidDT(passwordDetails.getUuidGenDT());
			pwdDetails.setPassRefId(passwordDetails.getPassRefId());
			return pwdDetails;
		} finally {
			pwdDetails = null;
			passwordDetails = null;
		}
	}

	@Override
	@Transactional
	public void savePassword(PasswordDetails passwordDetails) throws Exception {
		pwdDetailsDao.savePassword(passwordDetails);

	}

	@Override
	public void updatePassword(PasswordDetails passwordDetails) throws Exception {
		pwdDetailsDao.updatePassword(passwordDetails);
	}

}
