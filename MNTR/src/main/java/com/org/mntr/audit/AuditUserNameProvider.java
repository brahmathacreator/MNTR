package com.org.mntr.audit;

import org.apache.log4j.Logger;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.org.mntr.constants.UserConstants;
import com.org.mntr.dto.ActualUser;

public class AuditUserNameProvider implements AuditorAware<String> {

	private static final Logger logger = Logger.getLogger(AuditUserNameProvider.class);

	@Override
	public String getCurrentAuditor() {
		logger.info("Inside [UserAuditorAware][getCurrentAuditor]");
		Authentication au = null;
		try {
			au = SecurityContextHolder.getContext().getAuthentication();
			if (au == null || !au.isAuthenticated()) {
				logger.info("Authentication is null or not authenticated.");
			}
			return ((ActualUser) au.getPrincipal()).getUsername();
		} catch (Exception ex) {
			logger.error("AUDIT Error : " + ex);
		} finally {
			au = null;
		}
		return UserConstants.anonymousUser;
	}

}
