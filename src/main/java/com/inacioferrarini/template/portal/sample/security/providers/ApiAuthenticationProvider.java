package com.inacioferrarini.template.portal.sample.security.providers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.RestTemplate;

import com.inacioferrarini.template.portal.sample.security.api.factories.ApiRequestFactory;
import com.inacioferrarini.template.portal.sample.security.api.requests.AuthenticateApiRequestDto;
import com.inacioferrarini.template.portal.sample.security.dtos.ApiUserDto;
import com.inacioferrarini.template.portal.sample.security.dtos.JWTTokenDto;
import com.inacioferrarini.template.portal.sample.security.errors.factories.ApiErrorFactory;
import com.inacioferrarini.template.portal.sample.security.resources.SecurityResources;

@Component
public class ApiAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApiRequestFactory apiRequestFactory;

	@Autowired
	private ApiErrorFactory apiErrorFactory;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		AuthenticateApiRequestDto authenticateRequest = new AuthenticateApiRequestDto(
			authentication.getName(),
			authentication.getCredentials().toString()
		);

		HttpEntity<AuthenticateApiRequestDto> apiRequest = apiRequestFactory
			.requestEntity(authenticateRequest);
		try {
			ResponseEntity<JWTTokenDto> apiResponse = restTemplate.postForEntity(
				SecurityResources.Paths.Api.AUTHENTICATE,
				apiRequest,
				JWTTokenDto.class
			);

			ApiUserDto apiUser = new ApiUserDto(authentication.getName(), apiResponse.getBody());

			return new UsernamePasswordAuthenticationToken(apiUser, null, new ArrayList<>());
		} catch (BadRequest exception) {
			apiErrorFactory.parseException(exception)
				.filter(AuthenticationException.class::isInstance)
				.map(AuthenticationException.class::cast)
				.ifPresent(apiException -> {
					throw apiException;
				});
			// TODO: Log Error
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
