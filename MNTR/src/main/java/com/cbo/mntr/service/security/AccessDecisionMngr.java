package com.cbo.mntr.service.security;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;

public class AccessDecisionMngr extends AbstractAccessDecisionManager {

	private static final Logger logger = Logger.getLogger(AccessDecisionMngr.class);

	protected AccessDecisionMngr(List<AccessDecisionVoter<? extends Object>> decisionVoters) {
		super(decisionVoters);
	}

	@Override
	public void decide(Authentication authentication, Object filter, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {

		if ((filter == null) || !this.supports(filter.getClass())) {
			logger.debug("ERROR : [AccessDecisionMngr][decide] : Invalid Filter error.");
			throw new IllegalArgumentException();
		}

	}

}
