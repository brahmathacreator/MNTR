package com.org.mntr.events;

import java.io.StringWriter;
import java.util.Date;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import com.org.mntr.constants.MailConstants;
import com.org.mntr.constants.StatusConstants;
import com.org.mntr.constants.ViewConstants;
import com.org.mntr.dto.UserInfoDTO;
import com.org.mntr.entity.MailDetails;
import com.org.mntr.events.eventcaster.AsyncListner;
import com.org.mntr.service.MailDetailsService;
import com.org.mntr.service.UserService;
import com.org.mntr.service.security.AESWithPBKDFHashCryptoUtil;

@AsyncListner
@Component
public class MailEventListener implements ApplicationListener<MailEvent> {

	private static final Logger logger = Logger.getLogger(MailEventListener.class);

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Properties mntrProperties;

	@Autowired
	private VelocityEngine velocityEngine;

	@Autowired
	@Qualifier("AESUtil")
	private AESWithPBKDFHashCryptoUtil aesUtil;

	@Autowired
	@Qualifier("mailDetailsService")
	private MailDetailsService mailService;

	@Override
	public void onApplicationEvent(final MailEvent event) {
		logger.info("Inside [MailEventListner][onApplicationEvent]");
		if (event.getMailType() == MailConstants.regConfirmationMail) {
			this.sendSystemUserRegConfirmationMail(event);
		} else {
			logger.error("EVNT ERROR : Invalid Mail Type [" + event.getMailType() + "].");
		}
	}

	private void sendSystemUserRegConfirmationMail(final MailEvent event) {
		logger.info("Inside [MailEventListner][constructMail]");
		UserInfoDTO user = null;
		SimpleMailMessage mail = null;
		Template template = null;
		VelocityContext context = null;
		StringWriter writer = null;
		StringBuilder sb = null;
		MailDetails mailDetails = new MailDetails();
		Date d = new Date();
		try {
			user = ((UserInfoDTO) event.getObject());
			mail = new SimpleMailMessage();
			template = velocityEngine.getTemplate(mntrProperties.getProperty(MailConstants.velocityTemplatePathKey)
					+ messageSource.getMessage(MailConstants.velocityTemplateKey, null, event.getLocale()));
			context = new VelocityContext();
			context.put(MailConstants.velocityRegTemplatekey1, user.getUserId());
			sb = new StringBuilder();
			sb.append(event.getAppBaseURL()).append(ViewConstants.regConfirmationURL)
					.append(ViewConstants.rootParemSeperator).append(ViewConstants.regConfirmationParam1)
					.append(ViewConstants.paremValueSeperator)
					.append(aesUtil.encryptData(
							user.getPwdUUID() + ViewConstants.applicationDataSeperator + event.getMailType()
									+ ViewConstants.applicationDataSeperator + event.getPwdChangeFlag(),
							user.getPwdUUID()))
					.append(ViewConstants.paremSeperator).append(ViewConstants.regConfirmationParam2)
					.append(ViewConstants.paremValueSeperator).append(user.getUserKey());
			context.put(MailConstants.velocityRegTemplatekey2, sb.toString());
			context.put(MailConstants.velocityRegTemplatekey3, event.getAppBaseURL());
			writer = new StringWriter();
			template.merge(context, writer);
			mail.setFrom(mntrProperties.getProperty(MailConstants.supportMailkey));
			mail.setTo(user.getEmail());
			mail.setSubject(
					messageSource.getMessage(MailConstants.velocityTemplateRegSubjectKey, null, event.getLocale()));
			mail.setText(writer.toString());
			mailSender.send(mail);
			mailDetails.setDeliveryStatus(StatusConstants.mailDeliverySuccStatus);

		} catch (Exception ex) {
			logger.error("EVNT ERROR : " + ex);
			mailDetails.setDeliveryStatus(StatusConstants.mailDeliveryFailStatus);
		} finally {
			try {
				mailDetails.setFromAddress(mntrProperties.getProperty(MailConstants.supportMailkey));
				mailDetails.setToAddress(user.getEmail());
				mailDetails.setSentDt(d);
				mailService.saveMailDetails(mailDetails);
			} catch (Exception ex) {
				logger.error("EVNT ERROR : [Mail Details Info Failed] " + ex);
			}
			user = null;
			mail = null;
			template = null;
			context = null;
			writer = null;
			sb = null;
			mailDetails = null;
			d = null;

		}
	}

}
