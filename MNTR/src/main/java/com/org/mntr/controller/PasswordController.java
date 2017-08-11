package com.org.mntr.controller;

import java.util.Locale;
import java.util.Properties;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.mntr.constants.MailConstants;
import com.org.mntr.constants.MsgConstants;
import com.org.mntr.constants.NavigationConstants;
import com.org.mntr.constants.SSValidationConfig;
import com.org.mntr.constants.StatusConstants;
import com.org.mntr.constants.ViewConstants;
import com.org.mntr.dto.PasswordDetailsDTO;
import com.org.mntr.exceptions.CustomException;
import com.org.mntr.service.PasswordDetailsService;
import com.org.mntr.service.security.AESWithPBKDFHashCryptoUtil;
import com.org.mntr.utils.MsgResolver;

@Controller
public class PasswordController {

	private static final Logger logger = Logger.getLogger(PasswordController.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	@Qualifier("AESUtil")
	private AESWithPBKDFHashCryptoUtil aesUtil;

	@Autowired
	@Qualifier("pwdService")
	private PasswordDetailsService pwdService;

	@Autowired
	private Properties mntrProperties;

	@RequestMapping(value = { ViewConstants.regConfirmationURL })
	public String generateDefaultPassword(ModelMap model, Locale locale,
			@RequestParam(ViewConstants.regConfirmationParam1) String encryptedData,
			@RequestParam(ViewConstants.regConfirmationParam2) Long userKey) {
		logger.info("Inside [PasswordController][generateDefaultPassword]");
		String decryptedData = null;
		String[] decryptedDataArr = null;
		PasswordDetailsDTO passwordDetails = null;
		DateTime currentTime = null, generatedTime = null;
		try {
			model.addAttribute(ViewConstants.regConfirmationParam1, encryptedData);
			model.addAttribute(ViewConstants.regConfirmationParam2, userKey);
			if (encryptedData != null && !encryptedData.trim().isEmpty() && userKey != null && userKey != 0) {
				passwordDetails = pwdService.getPasswordByUserKey(userKey);
				if (passwordDetails != null && passwordDetails.getUuid() != null) {
					decryptedData = aesUtil.decryptData(encryptedData, passwordDetails.getUuid());
					decryptedDataArr = decryptedData
							.split(ViewConstants.applicationDataEscaper + ViewConstants.applicationDataSeperator);
					currentTime = new DateTime();
					generatedTime = new DateTime(passwordDetails.getUuidDT());
					if (currentTime.isAfter(generatedTime) && currentTime.isBefore(generatedTime.plusHours(Integer
							.parseInt(mntrProperties.getProperty(SSValidationConfig.pwdLinkExpiryPeriodKey).trim())))) {
						if (decryptedDataArr[0].equals(passwordDetails.getUuid())) {
							if (Integer.parseInt(decryptedDataArr[1]) == MailConstants.regConfirmationMail
									&& Integer.parseInt(decryptedDataArr[2]) == MailConstants.firstTimePasswordChange) {
								model.addAttribute(ViewConstants.actionURL, ViewConstants.changePasswordURL1);
								model.addAttribute(NavigationConstants.CURDOpt, StatusConstants.insert);
								model.addAttribute(ViewConstants.modelAttribute, passwordDetails);
								return ViewConstants.changePasswordView;
							} else {
								model.addAttribute(NavigationConstants.errmsg, messageSource.getMessage(
										MsgResolver.getMsgCodeKey(MsgConstants.problemOccouredMsgCode), null, locale));
							}
						} else {
							logger.error("CTRLR Error : UUID Mismatch.");
							model.addAttribute(NavigationConstants.errmsg, messageSource.getMessage(
									MsgResolver.getMsgCodeKey(MsgConstants.passwordCtrlrMsgCode2), null, locale));
						}
					} else {
						logger.error("CTRLR Error : Link Expired.");
						model.addAttribute(NavigationConstants.errmsg, messageSource.getMessage(
								MsgResolver.getMsgCodeKey(MsgConstants.passwordCtrlrMsgCode3), null, locale));
					}
				} else {
					logger.error("CTRLR Error : Encrypted data / User Key not found.");
					model.addAttribute(NavigationConstants.errmsg, messageSource
							.getMessage(MsgResolver.getMsgCodeKey(MsgConstants.passwordCtrlrMsgCode1), null, locale));
				}
			} else {
				logger.error("CTRLR Error : Encrypted data / User Key not found.");
				model.addAttribute(NavigationConstants.errmsg, messageSource
						.getMessage(MsgResolver.getMsgCodeKey(MsgConstants.parameterMismatch), null, locale));
			}
		} catch (CustomException c) {
			model.addAttribute(NavigationConstants.errmsg,
					messageSource.getMessage(MsgResolver.getMsgCodeKey(c.getMsgCode()), null, locale));
			logger.error("CTRLR Error : " + c);
		} catch (Exception ex) {
			model.addAttribute(NavigationConstants.errmsg, messageSource
					.getMessage(MsgResolver.getMsgCodeKey(MsgConstants.problemOccouredMsgCode), null, locale));
			logger.error("CTRLR Error : " + ex);
		} finally {
			decryptedData = null;
			decryptedDataArr = null;
			passwordDetails = null;
			currentTime = null;
			generatedTime = null;
		}
		return ViewConstants.errorURL;
	}

	@RequestMapping(value = { ViewConstants.changePasswordURL1 }, method = RequestMethod.POST)
	public String savePassword(@ModelAttribute(ViewConstants.modelAttribute) @Valid PasswordDetailsDTO pwdDetails,
			BindingResult result, Locale locale, ModelMap model,
			@RequestParam(ViewConstants.regConfirmationParam1) String encryptedData,
			@RequestParam(ViewConstants.regConfirmationParam2) Long userKey) {
		logger.info("Inside [PasswordController][generateDefaultPassword]");
		try {
			model.addAttribute(NavigationConstants.CURDOpt, StatusConstants.insert);
			model.addAttribute(ViewConstants.modelAttribute, pwdDetails);
			model.addAttribute(ViewConstants.actionURL, ViewConstants.changePasswordURL1);
			model.addAttribute(ViewConstants.regConfirmationParam1, encryptedData);
			model.addAttribute(ViewConstants.regConfirmationParam2, userKey);
			pwdDetails.setModifiedBy(userKey);
			if (pwdDetails.getPassRefId() != null && !pwdDetails.getPassRefId().equals(0)) {
				if (result.hasErrors()) {
					return ViewConstants.changePasswordView;
				}
				pwdService.updatePassword(pwdDetails);
				logger.info("CTRLR info : Action Completed Successfully.");
				model.addAttribute("succFlg", 1);
				model.addAttribute(NavigationConstants.successMsg,
						messageSource.getMessage(MsgResolver.getMsgCodeKey(MsgConstants.successMsgCode), null, locale));
				return ViewConstants.changePasswordView;
			} else {
				logger.error("CTRLR Error : Password Refference Id Not Available.");
				model.addAttribute(NavigationConstants.errmsg, messageSource
						.getMessage(MsgResolver.getMsgCodeKey(MsgConstants.problemOccouredMsgCode), null, locale));
			}
		} catch (CustomException c) {
			model.addAttribute(NavigationConstants.errmsg,
					messageSource.getMessage(MsgResolver.getMsgCodeKey(c.getMsgCode()), null, locale));
			logger.error("CTRLR Error : " + c);
		} catch (Exception ex) {
			model.addAttribute(NavigationConstants.errmsg, messageSource
					.getMessage(MsgResolver.getMsgCodeKey(MsgConstants.problemOccouredMsgCode), null, locale));
			logger.error("CTRLR Error : " + ex);
		}
		return ViewConstants.errorURL;
	}

}
