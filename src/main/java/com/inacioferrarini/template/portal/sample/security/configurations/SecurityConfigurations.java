package com.inacioferrarini.template.portal.sample.security.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.inacioferrarini.template.portal.sample.samplefeature.controllers.SampleController;
import com.inacioferrarini.template.portal.sample.security.controllers.SecurityController;
import com.inacioferrarini.template.portal.sample.security.providers.ApiAuthenticationProvider;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfigurations {

	@Autowired
	private ApiAuthenticationProvider authenticationProvider;

	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
			.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(authenticationProvider);
		return authenticationManagerBuilder.build();
	}

	// Authorization Configuration
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		final String loginPageUrl = SecurityController.BASE_MAPPING.concat(SecurityController.LOGIN_MAPPING);
		final String loginProcessUrl = SecurityController.BASE_MAPPING.concat(SecurityController.LOGIN_MAPPING);
		final String loginSuccessUrl = SampleController.BASE_MAPPING.concat(SampleController.INDEX_MAPPING);
		final String logoutUrl = SecurityController.BASE_MAPPING.concat(SecurityController.LOGOUT_MAPPING);
		final String logoutSuccessUrl = SecurityController.BASE_MAPPING.concat(SecurityController.LOGIN_MAPPING);

		http.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.formLogin(
				form -> form
					.loginPage(loginPageUrl)
					.loginProcessingUrl(loginProcessUrl)
					.defaultSuccessUrl(loginSuccessUrl, true)
					.permitAll()
			)
			.logout(logout -> {
				logout
					.logoutUrl(logoutUrl)
					.logoutSuccessUrl(logoutSuccessUrl);
			})
			.csrf().disable();

		return http.build();
	}

}
