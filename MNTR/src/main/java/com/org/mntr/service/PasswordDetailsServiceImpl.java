package com.org.mntr.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.org.mntr.constants.MsgConstants;
import com.org.mntr.dao.PasswordDetailsDAO;
import com.org.mntr.dto.PasswordDetailsDto;
import com.org.mntr.entity.PasswordDetails;
import com.org.mntr.exceptions.CustomException;

@Service("pwdService")
public class PasswordDetailsServiceImpl implements PasswordDetailsService {

	private static final Logger logger = Logger.getLogger(PasswordDetailsServiceImpl.class);

	@Autowired
	@Qualifier("pwdDetailsDAO")
	private PasswordDetailsDAO pwdDetailsDao;

	@Autowired
	@Qualifier("passwordEncoder")
	private BCryptPasswordEncoder bcrypt;

	@Override
	@Transactional
	public PasswordDetailsDto getPasswordByUserKey(Long userKey) throws Exception {
		logger.info("Inside [PasswordDetailsServiceImpl][PasswordDetailsDTO]");
		PasswordDetailsDto pwdDetails = null;
		PasswordDetails passwordDetails = null;
		try {
			passwordDetails = pwdDetailsDao.getPasswordByUserKey(userKey);
			if (passwordDetails != null && passwordDetails.getHashPwd() != null) {
				throw new CustomException(MsgConstants.passwordCtrlrMsgCode4);
			}
			pwdDetails = new PasswordDetailsDto();
			pwdDetails.setPwdUuid(passwordDetails.getPwdUuid());
			pwdDetails.setUuidGenerationDt(passwordDetails.getUuidGenerationDt());
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
	@Transactional
	public void updatePassword(PasswordDetailsDto passwordDetails) throws Exception {
		PasswordDetails pwdDetails = null;
		Date d = null;
		try {
			pwdDetails = pwdDetailsDao.getPasswordByPwdRefId(passwordDetails.getPassRefId());
			d = new Date();
			pwdDetails.setHashPwd(bcrypt.encode(passwordDetails.getcPassword()));
			pwdDetails.setModifiedBy(passwordDetails.getModifiedBy());
			pwdDetails.setModifiedDt(d);
			pwdDetailsDao.updatePassword(pwdDetails);
		} finally {
			pwdDetails = null;
			d = null;
		}

	}

}
