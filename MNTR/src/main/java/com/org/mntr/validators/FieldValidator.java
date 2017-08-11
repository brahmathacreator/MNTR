package com.org.mntr.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

public class FieldValidator implements ConstraintValidator<FieldValid, Object> {

	private static final Logger logger = Logger.getLogger(FieldValidator.class);

	private String firstField;
	private String secondField;

	@Override
	public void initialize(final FieldValid constraintAnnotation) {
		this.firstField = constraintAnnotation.firstField();
		this.secondField = constraintAnnotation.secondField();
	}

	@Override
	public boolean isValid(final Object value, final ConstraintValidatorContext context) {
		logger.info("Inside [FieldValidator][isValid]");
		try {
			Object firstObj = BeanUtils.getProperty(value, firstField);
			Object secondObj = BeanUtils.getProperty(value, secondField);
			return (firstObj != null && secondObj != null && firstObj.equals(secondObj));
		} catch (Exception ex) {
			logger.error("VLDTR ERROR : " + ex);
		}
		return false;
	}

}
