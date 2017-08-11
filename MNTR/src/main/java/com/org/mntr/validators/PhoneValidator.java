package com.org.mntr.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.org.mntr.constants.SSValidationConfig;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	@Override
	public void initialize(final Phone constraintAnnotation) {
	}

	@Override
	public boolean isValid(final String phoneNo, final ConstraintValidatorContext context) {
		if (phoneNo == null) {
			return false;
		}
		if (phoneNo.matches(SSValidationConfig.phnoGeneralPatteren))
			return true;
		else
			return false;
	}

}
