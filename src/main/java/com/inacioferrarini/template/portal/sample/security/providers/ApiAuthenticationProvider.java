package com.inacioferrarini.template.portal.sample.security.providers;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.LocaleResolver;

import com.inacioferrarini.template.portal.sample.core.api.errors.exceptions.WrapperException;
import com.inacioferrarini.template.portal.sample.core.api.factories.ApiRequestFactory;
import com.inacioferrarini.template.portal.sample.security.api.requests.AuthenticateApiRequestDto;
import com.inacioferrarini.template.portal.sample.security.api.responses.AuthenticateResponseDto;
import com.inacioferrarini.template.portal.sample.security.dtos.ApiUserDto;
import com.inacioferrarini.template.portal.sample.security.errors.exceptions.CompoundSecurityException;
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

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private LocaleResolver localeResolver;

	@Override
	public Authentication authenticate(
		Authentication authentication
	) throws AuthenticationException {

		AuthenticateApiRequestDto authenticateRequest = new AuthenticateApiRequestDto(
			authentication.getName(),
			authentication.getCredentials().toString()
		);

		HttpEntity<AuthenticateApiRequestDto> apiRequest = apiRequestFactory
			.requestEntity(authenticateRequest);
		try {
			ResponseEntity<AuthenticateResponseDto> apiResponse = restTemplate.postForEntity(
				SecurityResources.Paths.Api.AUTHENTICATE,
				apiRequest,
				AuthenticateResponseDto.class
			);

			final Locale userIdiom = Locale.forLanguageTag(apiResponse.getBody().getIdiom());

			ApiUserDto apiUser = new ApiUserDto(
				authentication.getName(),
				userIdiom,
				apiResponse.getBody().getToken()
			);

			localeResolver.setLocale(request, response, userIdiom);

			return new UsernamePasswordAuthenticationToken(apiUser, null, new ArrayList<>());
		} catch (BadRequest exception) {
			apiErrorFactory.parseException(exception)
				.filter(WrapperException.class::isInstance)
				.map(WrapperException.class::cast)
				.ifPresent(apiExceptionWrapper -> {
					throw new CompoundSecurityException(
						"CompoundSecurityException",
						apiExceptionWrapper.getExceptions()
					);
				});

			apiErrorFactory.parseException(exception)
				.filter(AuthenticationException.class::isInstance)
				.map(AuthenticationException.class::cast)
				.ifPresent(apiException -> {
					throw apiException;
				});

			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
