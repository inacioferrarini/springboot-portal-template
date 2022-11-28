package com.inacioferrarini.template.portal.sample.core.configurations;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MessageSourceConfigurations {

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames(
			"classpath:/messages/temp",
			"classpath:/messages/security/api-error-messages",
			"classpath:/messages/security/ui-actions",
			"classpath:/messages/security/ui-fields",
			"classpath:/messages/security/ui-titles"
		);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

}
