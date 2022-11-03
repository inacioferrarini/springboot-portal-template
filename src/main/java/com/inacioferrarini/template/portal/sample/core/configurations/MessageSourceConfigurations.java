package com.inacioferrarini.template.portal.sample.core.configurations;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageSourceConfigurations {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames(
			"classpath:/messages/security/ui-actions",
			"classpath:/messages/security/ui-fields-names",
			"classpath:/messages/security/ui-titles"
		);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

}
