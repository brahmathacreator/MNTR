package com.cbo.mntr.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cbo.mntr.constants.SSValidationConfig;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	@Override
	public void initialize(Phone constraintAnnotation) {
	}

	@Override
	public boolean isValid(String phoneNo, ConstraintValidatorContext context) {
		if (phoneNo == null) {
			return false;
		}
		if (phoneNo.matches(SSValidationConfig.phnoGeneralPatteren))
			return true;
		else
			return false;
	}

}
