package com.cbo.mntr.service.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbo.mntr.dto.ActualUser;
import com.cbo.mntr.entity.RoleMenuMapping;
import com.cbo.mntr.entity.UserInfo;
import com.cbo.mntr.entity.UserRoleMapping;
import com.cbo.mntr.service.UserService;

@Service("authenticationService")
public class AuthenticationService implements UserDetailsService {
	private static final Logger logger = Logger.getLogger(AuthenticationService.class);

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = null;
		Set<UserRoleMapping> userRoleMap = null;
		Set<RoleMenuMapping> roleMenuMap = null;
		List<String> roles = null;
		List<String> urls = null;
		ActualUser actUser = null;
		try {
			userInfo = userService.getUserByName(username);
			if (userInfo == null) {
				throw new UsernameNotFoundException("User Not Found.");
			}
			userRoleMap = userInfo.getURMapSet();
			roles = new ArrayList<String>();
			urls = new ArrayList<String>();
			for (UserRoleMapping ur : userRoleMap) {
				roles.add(ur.getUserRole().getRoleName());
				roleMenuMap = ur.getUserRole().getRoleMenuMapping();
				for (RoleMenuMapping rm : roleMenuMap) {
					urls.add(rm.getMenuDetails().getMenuName());
				}
			}
			actUser = new ActualUser(userInfo, roles, urls);
			return actUser;
		} catch (Exception ex) {
			logger.debug("ERROR : [AuthenticationService][UserDetails] : " + ex);
		} finally {
			userInfo = null;
			userRoleMap = null;
			roles = null;
			actUser = null;
		}
		return null;
	}

}
