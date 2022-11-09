package com.inacioferrarini.template.portal.sample.security.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.inacioferrarini.template.portal.sample.security.providers.ApiAuthenticationProvider;
import com.inacioferrarini.template.portal.sample.security.resources.SecurityResources;

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

		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, SecurityResources.Paths.Configuration.ACTIVATE_ACCOUNT).permitAll()
			.antMatchers(SecurityResources.Paths.Configuration.FORGOT_USERNAME).permitAll()
			.antMatchers(SecurityResources.Paths.Configuration.FORGOT_PASSWORD).permitAll()
			.antMatchers(SecurityResources.Paths.Configuration.PASSWORD_RESET).permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin(
				form -> form
					.loginPage(SecurityResources.Paths.Configuration.LOGIN_PAGE)
					.loginProcessingUrl(SecurityResources.Paths.Configuration.LOGIN_PROCESS)
					.defaultSuccessUrl(SecurityResources.Paths.Configuration.LOGIN_SUCCESS, true)
					.permitAll()
			)
			.logout(logout -> {
				logout
					.logoutUrl(SecurityResources.Paths.Configuration.LOGOUT)
					.logoutSuccessUrl(SecurityResources.Paths.Configuration.LOGOUT_SUCCESS);
			})
			.csrf().disable();

		return http.build();
	}

}
