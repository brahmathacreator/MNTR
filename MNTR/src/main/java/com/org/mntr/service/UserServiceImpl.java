package com.org.mntr.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.mntr.constants.MsgConstants;
import com.org.mntr.constants.StatusConstants;
import com.org.mntr.constants.UserConstants;
import com.org.mntr.dao.MenuDetailsDAO;
import com.org.mntr.dao.PasswordDetailsDAO;
import com.org.mntr.dao.RoleMenuMappingDAO;
import com.org.mntr.dao.UserDAO;
import com.org.mntr.dao.UserRoleMappingDAO;
import com.org.mntr.dto.MenuDetailsDto;
import com.org.mntr.dto.UserInfoDto;
import com.org.mntr.entity.MenuDetails;
import com.org.mntr.entity.PasswordDetails;
import com.org.mntr.entity.RoleMenuMapping;
import com.org.mntr.entity.UserInfo;
import com.org.mntr.entity.UserRole;
import com.org.mntr.entity.UserRoleMapping;
import com.org.mntr.exceptions.CustomException;

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

	@Autowired
	private MessageSource messageSource;

	@Override
	@Transactional
	public UserInfoDto getUserByNameToLogin(String userName, Locale locale) throws Exception {
		UserInfoDto userInfo = null;
		UserInfo userInfoEntity = null;
		Set<UserRoleMapping> userRoleMap = null;
		Set<RoleMenuMapping> roleMenuMap = null;
		MenuDetailsDto urlProps = null;
		try {
			logger.info("Inside [UserServiceImpl][getUserByName]");
			userInfoEntity = userDao.getUserByName(userName);
			if (userInfoEntity != null) {
				userInfo = new UserInfoDto();
				BeanUtils.copyProperties(userInfoEntity, userInfo);
				userRoleMap = userInfoEntity.getUserRoleMappings();
				for (UserRoleMapping ur : userRoleMap) {
					userInfo.getRoles().add(UserConstants.genericRole + ur.getUserRole().getRoleName());
					userInfo.setRoleId(ur.getUserRole().getRoleId());
					roleMenuMap = ur.getUserRole().getRoleMenuMappings();
					for (RoleMenuMapping rm : roleMenuMap) {
						urlProps = new MenuDetailsDto();
						BeanUtils.copyProperties(rm.getMenuDetails(), urlProps);
						urlProps.setMenuName(messageSource.getMessage(urlProps.getMenuName(), null, locale));
						urlProps.setMenuDesc(messageSource.getMessage(urlProps.getMenuDesc(), null, locale));
						if (urlProps.getMenuType().equals(UserConstants.parentMenu))
							userInfo.getParentURLList().add(urlProps);
						if (urlProps.getMenuType().equals(UserConstants.childMenu))
							userInfo.getChildURLList().add(urlProps);
						urlProps = null;
					}
				}
				Collections.sort(userInfo.getParentURLList());
				Collections.sort(userInfo.getChildURLList());
				userInfo.setConCatRoles(StringUtils.join(userInfo.getRoles(), ","));
				userInfo.setHashPwd(userInfoEntity.getPasswordDetailses().getHashPwd());
			} else {
				throw new CustomException(MsgConstants.userInfoMsgCode1);
			}
			return userInfo;
		} finally {
			userInfo = null;
			userInfoEntity = null;
		}
	}

	@Override
	@Transactional
	public Long getUserCount() throws Exception {
		return userDao.getUserCount();
	}

	@Override
	@Transactional
	public void saveSystemUser(final UserInfoDto user) throws Exception {
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
			userInfo.setUsername(user.getUsername());
			userInfo.setEmail(user.getEmail());
			userInfo.setPhoneNo(user.getPhoneNo());
			userInfo.setCreatedBy(user.getCreatedBy());
			userInfo.setCreatedDt(d);
			userInfo.setModifiedBy(user.getModifiedBy());
			userInfo.setInvalidPassAttempts(0);
			userInfo.setLastLoginDt(d);
			userInfo.setLockStatus(StatusConstants.active);
			userInfo.setModifiedDt(d);
			userInfo.setStatus(StatusConstants.active);
			userInfo.setUserLogoName(user.getUserLogoName());
			userInfo.setLoginType(UserConstants.systemLogin);

			userRole = new UserRole();
			userRole.setRoleName(UserConstants.systemUserRole);
			userRole.setStatus(StatusConstants.active);
			userRole.setCreatedBy(user.getCreatedBy());
			userRole.setCreatedDt(d);
			userRole.setModifiedBy(user.getModifiedBy());
			userRole.setModifiedDt(d);

			urMapping = new UserRoleMapping();
			urMapping.setUserInfo(userInfo);
			menuDetailsList = menuDetailsDao.getAllMenuDetails();
			if (menuDetailsList == null) {
				throw new CustomException(MsgConstants.menuDetailsMsgCode1);
			}
			for (MenuDetails md : menuDetailsList) {
				rmMapping = new RoleMenuMapping();
				rmMapping.setUserRole(userRole);
				rmMapping.setMenuDetails(md);
				userRole.getRoleMenuMappings().add(rmMapping);
				rmMapping = null;
			}
			urMapping.setUserRole(userRole);
			userInfo.getUserRoleMappings().add(urMapping);

			passwordDetails = new PasswordDetails();
			passwordDetails.setUserInfo(userInfo);
			passwordDetails.setStatus(StatusConstants.active);
			passwordDetails.setCreatedBy(user.getCreatedBy());
			passwordDetails.setCreatedDt(d);
			passwordDetails.setModifiedBy(user.getModifiedBy());
			passwordDetails.setModifiedDt(d);
			passwordDetails.setPwdUuid(user.getPwdUuid());
			passwordDetails.setUuidGenerationDt(d);

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
