package com.inacioferrarini.template.portal.sample.core.validators;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = LocaleValidator.class)
@Documented
public @interface Locale {

	String message() default "{locale.invalid.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String[] locales() default {};

}
