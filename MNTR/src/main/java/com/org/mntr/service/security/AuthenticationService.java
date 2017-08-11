package com.org.mntr.service.security;

import java.util.Locale;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.mntr.constants.MsgConstants;
import com.org.mntr.dto.ActualUser;
import com.org.mntr.dto.UserInfoDTO;
import com.org.mntr.service.UserService;
import com.org.mntr.utils.MsgResolver;

@Service("authenticationService")
public class AuthenticationService implements UserDetailsService {
	private static final Logger logger = Logger.getLogger(AuthenticationService.class);

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	private MessageSource messageSource;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfoDTO userInfo = null;
		ActualUser actUser = null;
		Locale locale = null;
		try {
			locale = LocaleContextHolder.getLocale();
			userInfo = userService.getUserByNameToLogin(username, locale);
			if (userInfo == null) {
				throw new UsernameNotFoundException(messageSource
						.getMessage(MsgResolver.getMsgCodeKey(MsgConstants.userInfoMsgCode1), null, locale));
			}
			actUser = new ActualUser(userInfo);
			return actUser;
		} catch (Exception ex) {
			logger.debug("ERROR : [AuthenticationService][UserDetails] : " + ex);
		} finally {
			userInfo = null;
			actUser = null;
			locale = null;

		}
		return null;
	}

}
