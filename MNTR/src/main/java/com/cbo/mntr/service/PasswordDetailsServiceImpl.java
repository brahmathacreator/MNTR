package com.cbo.mntr.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cbo.mntr.constants.MsgConstants;
import com.cbo.mntr.dao.PasswordDetailsDAO;
import com.cbo.mntr.dto.PasswordDetailsDTO;
import com.cbo.mntr.entity.PasswordDetails;
import com.cbo.mntr.exceptions.CustomException;

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
	public PasswordDetailsDTO getPasswordByUserKey(Long userKey) throws Exception {
		logger.info("Inside [PasswordDetailsServiceImpl][PasswordDetailsDTO]");
		PasswordDetailsDTO pwdDetails = null;
		PasswordDetails passwordDetails = null;
		try {
			passwordDetails = pwdDetailsDao.getPasswordByUserKey(userKey);
			if (passwordDetails != null && passwordDetails.getHashPwd() != null) {
				throw new CustomException(MsgConstants.passwordCtrlrMsgCode4);
			}
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
	@Transactional
	public void updatePassword(PasswordDetailsDTO passwordDetails) throws Exception {
		PasswordDetails pwdDetails = null;
		Date d = null;
		try {
			pwdDetails = pwdDetailsDao.getPasswordByPwdRefId(passwordDetails.getPassRefId());
			d = new Date();
			pwdDetails.setHashPwd(bcrypt.encode(passwordDetails.getcPassword()));
			pwdDetails.setModifiedBy(passwordDetails.getModifiedBy());
			pwdDetails.setModifiedDT(d);
			pwdDetailsDao.updatePassword(pwdDetails);
		} finally {
			pwdDetails = null;
			d = null;
		}

	}

}
