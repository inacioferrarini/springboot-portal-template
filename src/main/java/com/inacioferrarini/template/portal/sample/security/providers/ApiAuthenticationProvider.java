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
import com.inacioferrarini.template.portal.sample.security.api.requests.AuthenticateApiRequestDTO;
import com.inacioferrarini.template.portal.sample.security.dto.ApiUserDTO;
import com.inacioferrarini.template.portal.sample.security.dto.JWTTokenDTO;
import com.inacioferrarini.template.portal.sample.security.resources.SecurityResources;

@Component
public class ApiAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApiRequestFactory apiRequestFactory;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		AuthenticateApiRequestDTO authenticateRequestDTO = new AuthenticateApiRequestDTO(
			authentication.getName(),
			authentication.getCredentials().toString()
		);

		HttpEntity<AuthenticateApiRequestDTO> requestEntity = apiRequestFactory
			.requestEntity(authenticateRequestDTO);
		try {
			ResponseEntity<JWTTokenDTO> tokenResponse = restTemplate.postForEntity(
				SecurityResources.Paths.Api.AUTHENTICATE,
				requestEntity,
				JWTTokenDTO.class
			);

			ApiUserDTO apiUserDTO = new ApiUserDTO(authentication.getName(), tokenResponse.getBody());

			return new UsernamePasswordAuthenticationToken(apiUserDTO, null, new ArrayList<>());
		} catch (BadRequest exception) {
			// Throw and Exception and throw it, so it can be catch and redirected to login
			// form
			System.out.println("@@" + exception.getResponseBodyAsString() + "@@");
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
