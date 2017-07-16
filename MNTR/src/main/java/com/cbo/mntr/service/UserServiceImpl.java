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
	public UserInfo getUserByName(String userName) {
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
	public Integer getUserCount() {
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
	public Integer saveSystemUser(final UserInfoDTO user) {
		UserInfo userInfo = null;
		PasswordDetails passwordDetails = null;
		UserRole userRole = null;
		UserRoleMapping urMapping = null;
		RoleMenuMapping rmMapping = null;
		Date d = new Date();
		Integer status = null;
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
			urMapping.setUserRole(userRole);

			status = urMappingDao.saveUserRoleMapping(urMapping);

			if (MsgConstants.successMsgCode.equals(status)) {
				rmMapping = new RoleMenuMapping();
				rmMapping.setUserRole(userRole);

				menuDetailsList = menuDetailsDao.getAllMenuDetails();
				if (menuDetailsList == null) {
					return MsgConstants.menuDetailsMsgCode1;
				}

				passwordDetails = new PasswordDetails();
				passwordDetails.setUser(userInfo);
				passwordDetails.setStatus(StatusConstants.active);
				passwordDetails.setCreatedBy(user.getCreatedBy());
				passwordDetails.setCreatedDT(d);
				passwordDetails.setModifiedBy(user.getModifiedBy());
				passwordDetails.setModifiedDT(d);
				passwordDetails.setPwdUUID(user.getPwdUUID());
				passwordDetails.setUuidGenDT(d);
			}
			return status;
		} catch (Exception ex) {
			logger.error("SVCE ERROR: " + ex);
		} finally {
			userInfo = null;
			passwordDetails = null;
			userRole = null;
			d = null;
			status = null;

		}
		return MsgConstants.problemOccouredMsgCode;
	}

}
