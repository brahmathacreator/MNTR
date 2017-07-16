package com.cbo.mntr.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

import com.cbo.mntr.constants.MailConstants;
import com.cbo.mntr.constants.MsgConstants;
import com.cbo.mntr.constants.NavigationConstants;
import com.cbo.mntr.constants.StatusConstants;
import com.cbo.mntr.constants.UserConstants;
import com.cbo.mntr.constants.ViewConstants;
import com.cbo.mntr.dto.UserInfoDTO;
import com.cbo.mntr.events.MailEvent;
import com.cbo.mntr.service.UserService;
import com.cbo.mntr.utils.MsgResolver;
import com.cbo.mntr.utils.RequestUtil;
import com.cbo.mntr.utils.UniqueReferenceGenerator;

@Controller
public class RegistrationController {
	private static final Logger logger = Logger.getLogger(RegistrationController.class);

	@Autowired
	private LocaleResolver localeResolver;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	@Qualifier("jobLauncher")
	private JobLauncher launcher;

	@Autowired
	@Qualifier("menuDetailsJob")
	private Job job;

	@RequestMapping(value = { ViewConstants.registerSystemUser }, method = RequestMethod.POST)
	public String superAdminRegPage(ModelMap model) {
		logger.info("Inside [RegistrationController][superAdminRegPage]");
		UserInfoDTO user = null;
		try {
			user = new UserInfoDTO();
			model.addAttribute("user", user);
			model.addAttribute(ViewConstants.actionURL, ViewConstants.saveSystemUser);
		} catch (Exception ex) {
			logger.error("Error : [RegistrationController][superAdminRegPage] : " + ex);
		} finally {
			user = null;
		}
		return ViewConstants.registerSystemUser;
	}

	@RequestMapping(value = { ViewConstants.saveSystemUser }, method = RequestMethod.POST)
	public String saveSuperAdmin(@ModelAttribute("user") @Valid UserInfoDTO user, BindingResult validationResult,
			ModelMap model, @RequestParam("CURDOpt") Integer curdOpt, HttpServletRequest request) {
		logger.info("Inside [RegistrationController][CURDSuperAdmin]");
		Locale locale = null;
		Integer stat = null;
		JobExecution exec = null;
		try {
			locale = localeResolver.resolveLocale(request);
			if (curdOpt != null) {
				model.addAttribute("user", user);
				model.addAttribute(ViewConstants.actionURL, ViewConstants.saveSystemUser);
				if (curdOpt == StatusConstants.insert) {
					if (validationResult.hasErrors()) {
						return ViewConstants.registerSystemUser;
					}
					exec = launcher.run(job, new JobParameters());
					logger.info("Job Status : " + exec.getStatus());
					if (BatchStatus.COMPLETED == exec.getStatus()) {
						user.setUserLogo(messageSource.getMessage(UserConstants.defaultLogoPathKey, null, locale));
						user.setCreatedBy(UserConstants.anonymousUser);
						user.setModifiedBy(UserConstants.anonymousUser);
						user.setPwdUUID(UniqueReferenceGenerator.getUUIDvalue());
						stat = userService.saveSystemUser(user);
						if (stat == MsgConstants.successMsgCode) {
							try {
								eventPublisher.publishEvent(new MailEvent(this, user, locale,
										RequestUtil.getBaseUrl(request), MailConstants.regConfirmationMail));
							} catch (Exception ex) {
								logger.error("CTRLR Error : Publish Registration Confirmation Event Failed : " + ex);
							}
							user = new UserInfoDTO();
							model.addAttribute("user", user);
							model.addAttribute(NavigationConstants.successMsg, messageSource
									.getMessage(MsgResolver.getMsgCodeKey(MsgConstants.successMsgCode), null, locale));
						} else {
							model.addAttribute(NavigationConstants.errmsg,
									messageSource.getMessage(MsgResolver.getMsgCodeKey(stat), null, locale));
						}
						logger.info("CTRLR info : Action [" + curdOpt + "] Completed Successfully.");
					} else {
						logger.error("CTRLR Error : Menu Meta JOB Process Failed");
						model.addAttribute(NavigationConstants.errmsg, messageSource.getMessage(
								MsgResolver.getMsgCodeKey(MsgConstants.problemOccouredMsgCode), null, locale));
					}
				} else {
					model.addAttribute(NavigationConstants.errmsg, messageSource
							.getMessage(MsgResolver.getMsgCodeKey(MsgConstants.operationMsgCode), null, locale));
					logger.error("CTRLR Error :  Invalid CURD Parameter : " + curdOpt);
					return ViewConstants.registerSystemUser;
				}
			} else {
				model.addAttribute(NavigationConstants.errmsg, messageSource
						.getMessage(MsgResolver.getMsgCodeKey(MsgConstants.operationMsgCode), null, locale));
				logger.error("CTRLR Error :  CURD Parameter Missing.");
			}
		} catch (Exception ex) {
			model.addAttribute(NavigationConstants.errmsg, messageSource
					.getMessage(MsgResolver.getMsgCodeKey(MsgConstants.problemOccouredMsgCode), null, locale));
			logger.error("CTRLR Error : " + ex);
		} finally {
			locale = null;
			user = null;
			stat = null;
			exec = null;
		}
		return ViewConstants.registerSystemUser;
	}

}
