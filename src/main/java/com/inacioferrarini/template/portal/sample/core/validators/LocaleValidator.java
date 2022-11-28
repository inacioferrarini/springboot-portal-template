package com.inacioferrarini.template.portal.sample.core.validators;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LocaleValidator implements ConstraintValidator<Locale, String> {

	private Set<String> locales;

	public void initialize(Locale constraint) {
		locales = Arrays.stream(constraint.locales()).collect(Collectors.toSet());
	}

	@Override
	public boolean isValid(
		String value,
		ConstraintValidatorContext context
	) {
		List<String> validLocales = Arrays.asList(java.util.Locale.getAvailableLocales())
			.stream().map(java.util.Locale::toLanguageTag)
			.filter(locales::contains)
			.filter(value::equals)
			.collect(Collectors.toList());
		return validLocales.size() == 1;
	}

}
