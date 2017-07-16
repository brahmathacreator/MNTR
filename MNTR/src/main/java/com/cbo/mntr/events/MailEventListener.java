package com.cbo.mntr.events;

import java.io.StringWriter;
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

import com.cbo.mntr.constants.MailConstants;
import com.cbo.mntr.constants.ViewConstants;
import com.cbo.mntr.dto.UserInfoDTO;
import com.cbo.mntr.events.eventcaster.AsyncListner;
import com.cbo.mntr.service.UserService;

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

	@Override
	public void onApplicationEvent(final MailEvent event) {
		logger.info("Inside [MailEventListner][onApplicationEvent]");
		if (event.getMailType() == MailConstants.regConfirmationMail) {
			this.sendRegConfirmationMail(event);
		} else {
			logger.error("EVNT ERROR : Invalid Mail Type [" + event.getMailType() + "].");
		}
	}

	private void sendRegConfirmationMail(final MailEvent event) {
		logger.info("Inside [MailEventListner][constructMail]");
		UserInfoDTO user = null;
		SimpleMailMessage mail = null;
		Template template = null;
		VelocityContext context = null;
		StringWriter writer = null;
		try {
			user = ((UserInfoDTO) event.getObject());
			mail = new SimpleMailMessage();
			template = velocityEngine.getTemplate(mntrProperties.getProperty(MailConstants.velocityTemplatePathKey)
					+ messageSource.getMessage(MailConstants.velocityTemplateKey, null, event.getLocale()));
			context = new VelocityContext();
			context.put(MailConstants.velocityRegTemplatekey1, user.getUserId());
			context.put(MailConstants.velocityRegTemplatekey2,
					event.getAppBaseURL() + ViewConstants.regConfirmationURL + user.getPwdUUID());
			context.put(MailConstants.velocityRegTemplatekey3, event.getAppBaseURL());
			writer = new StringWriter();
			template.merge(context, writer);
			mail.setFrom(mntrProperties.getProperty(MailConstants.supportMailkey));
			mail.setTo(user.getEmail());
			mail.setSubject(
					messageSource.getMessage(MailConstants.velocityTemplateRegSubjectKey, null, event.getLocale()));
			mail.setText(writer.toString());
			mailSender.send(mail);
		} catch (Exception ex) {
			logger.error("EVNT ERROR : " + ex);
		} finally {
			user = null;
			mail = null;
			template = null;
			context = null;
			writer = null;
		}
	}

}
