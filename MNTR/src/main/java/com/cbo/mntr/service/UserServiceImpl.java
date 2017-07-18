package com.cbo.mntr.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbo.mntr.constants.MsgConstants;
import com.cbo.mntr.constants.StatusConstants;
import com.cbo.mntr.constants.UserConstants;
import com.cbo.mntr.dao.MenuDetailsDAO;
import com.cbo.mntr.dao.PasswordDetailsDAO;
import com.cbo.mntr.dao.RoleMenuMappingDAO;
import com.cbo.mntr.dao.UserDAO;
import com.cbo.mntr.dao.UserRoleMappingDAO;
import com.cbo.mntr.dto.UserInfoDTO;
import com.cbo.mntr.entity.MenuDetails;
import com.cbo.mntr.entity.PasswordDetails;
import com.cbo.mntr.entity.RoleMenuMapping;
import com.cbo.mntr.entity.UserInfo;
import com.cbo.mntr.entity.UserRole;
import com.cbo.mntr.entity.UserRoleMapping;
import com.cbo.mntr.exceptions.CustomException;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	@Qualifier("userDAO")
	private UserDAO userDao;

	@Autowired
	@Qualifier("pwdDetailsDAO")
	private PasswordDetailsDAO pwdDetailsDao;

	@Autowired
	@Qualifier("menuDetailsDAO")
	private MenuDetailsDAO menuDetailsDao;

	@Autowired
	@Qualifier("urMappingDAO")
	private UserRoleMappingDAO urMappingDao;

	@Autowired
	@Qualifier("rmMappingDAO")
	private RoleMenuMappingDAO rmMappingDao;

	@Override
	@Transactional
	public UserInfo getUserByName(String userName) throws Exception {
		try {
			logger.info("Inside [UserServiceImpl][getUserByName]");
			return userDao.getUserByName(userName);
		} catch (Exception ex) {
			logger.error("SVCE ERROR: " + ex);
		}
		return null;
	}

	@Override
	@Transactional
	public Long getUserCount() throws Exception {
		try {
			logger.info("Inside [UserServiceImpl][getUserByUserType]");
			return userDao.getUserCount();
		} catch (Exception ex) {
			logger.error("SVCE ERROR: " + ex);
		}
		return null;
	}

	@Override
	@Transactional
	public void saveSystemUser(final UserInfoDTO user) throws Exception {
		UserInfo userInfo = null;
		PasswordDetails passwordDetails = null;
		UserRole userRole = null;
		UserRoleMapping urMapping = null;
		RoleMenuMapping rmMapping = null;
		Date d = new Date();
		List<MenuDetails> menuDetailsList = null;
		try {
			logger.info("Inside [UserServiceImpl][saveSuperAdminUser]");
			userInfo = new UserInfo();
			userInfo.setUserId(user.getUserId());
			userInfo.setUserName(user.getUserName());
			userInfo.setEmail(user.getEmail());
			userInfo.setPhno(user.getPhno());
			userInfo.setCreatedBy(user.getCreatedBy());
			userInfo.setCreatedDT(d);
			userInfo.setModifiedBy(user.getModifiedBy());
			userInfo.setInvalidPassAtmpts(0);
			userInfo.setLastLoginDT(d);
			userInfo.setLockStatus(StatusConstants.active);
			userInfo.setModifiedDT(d);
			userInfo.setStatus(StatusConstants.active);
			userInfo.setUserLogoName(user.getUserLogo());
			userInfo.setLoginType(UserConstants.systemLogin);

			userRole = new UserRole();
			userRole.setRoleName(UserConstants.systemUserRole);
			userRole.setStatus(StatusConstants.active);
			userRole.setCreatedBy(user.getCreatedBy());
			userRole.setCreatedDT(d);
			userRole.setModifiedBy(user.getModifiedBy());
			userRole.setModifiedDT(d);

			urMapping = new UserRoleMapping();
			urMapping.setUserDetails(userInfo);
			menuDetailsList = menuDetailsDao.getAllMenuDetails();
			if (menuDetailsList == null) {
				throw new CustomException(MsgConstants.menuDetailsMsgCode1);
			}
			for (MenuDetails md : menuDetailsList) {
				rmMapping = new RoleMenuMapping();
				rmMapping.setUserRole(userRole);
				rmMapping.setMenuDetails(md);
				userRole.getRoleMenuMapping().add(rmMapping);
				rmMapping = null;
			}
			urMapping.setUserRole(userRole);
			userInfo.getURMapSet().add(urMapping);

			passwordDetails = new PasswordDetails();
			passwordDetails.setUser(userInfo);
			passwordDetails.setStatus(StatusConstants.active);
			passwordDetails.setCreatedBy(user.getCreatedBy());
			passwordDetails.setCreatedDT(d);
			passwordDetails.setModifiedBy(user.getModifiedBy());
			passwordDetails.setModifiedDT(d);
			passwordDetails.setPwdUUID(user.getPwdUUID());
			passwordDetails.setUuidGenDT(d);

			pwdDetailsDao.savePassword(passwordDetails);

			user.setUserKey(userInfo.getUserKey());

		} finally {
			userInfo = null;
			passwordDetails = null;
			userRole = null;
			d = null;
		}
	}

}
