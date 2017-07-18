package com.cbo.mntr.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = FieldValidator.class)
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldValid {

	String message()

	default "{FieldValid}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String firstField();

	String secondField();

	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
	@interface List {
		FieldValid[] value();
	}

}
